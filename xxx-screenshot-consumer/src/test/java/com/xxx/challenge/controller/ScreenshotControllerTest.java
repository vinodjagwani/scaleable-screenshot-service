package com.xxx.challenge.controller;


import com.xxx.challenge.mongo.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ScreenshotController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ScreenshotControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ImageService imageService;


    @Test
    public void testGetScreenshotById() throws Exception {
        when(imageService.downloadFromStream(any(String.class))).thenReturn(Optional.of(getImageStream()));
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/xxx/screenshots/{object_id}", "23424234"))
                .andExpect(status().isOk())
                .andDo(print()).andDo(MockMvcRestDocumentation.document("get-screenshot-by-id", Preprocessors.preprocessRequest(
                Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }


    @Test
    public void testGetScreenshotById_not_found() throws Exception {
        when(imageService.downloadFromStream(any(String.class))).thenReturn(Optional.empty());
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/xxx/screenshot/{object_id}", "23424234"))
                .andExpect(status().isNotFound())
                .andDo(print()).andDo(MockMvcRestDocumentation.document("get-screenshot-by-id", Preprocessors.preprocessRequest(
                Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }


    private InputStreamResource getImageStream() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        IOUtils.copy(getClass().getResourceAsStream("/images/test.png"), stream);
        final byte[] data = stream.toByteArray();
        return new InputStreamResource(new ByteArrayInputStream(data)) {
            @Override
            public long contentLength() {
                return data.length;
            }
        };
    }

}
