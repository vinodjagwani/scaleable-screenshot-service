package com.detectify.challenge.controller;


import com.detectify.challenge.dto.ScreenshotUrlData;
import com.detectify.challenge.facade.ScreenshotProducerFacade;
import com.detectify.challenge.utils.MockUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vinodjagwani on 11/27/18.
 */


@RunWith(SpringRunner.class)
@WebMvcTest(ScreenshotProducerController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ScreenshotProducerControllerTest {


    private final ObjectMapper mapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScreenshotProducerFacade screenshotProducerFacade;


    @Test
    public void testSendUrlsForScreenshot() throws Exception {
        final String mockResponse = MockUtils.getResource("mock/screenshot-urls.json", String.class);
        doNothing().when(screenshotProducerFacade).sendUrlsForScreenshot(any(ScreenshotUrlData.class));
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/xxx/screenshots")
                .content(mockResponse).contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent())
                .andDo(print()).andDo(document("screenshot-urls", Preprocessors.preprocessRequest(
                Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    public void testSendUrlsForScreenshot_empty_urls() throws Exception {
        final ScreenshotUrlData mockResponse = MockUtils.getResource("mock/screenshot-urls.json", ScreenshotUrlData.class);
        mockResponse.setUrls(null);
        final String content = mapper.writeValueAsString(mockResponse);
        doNothing().when(screenshotProducerFacade).sendUrlsForScreenshot(any(ScreenshotUrlData.class));
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/xxx/screenshots")
                .content(content).contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("INVALID_FORMAT")))
                .andExpect(jsonPath("$.errors[0].param", is("urls")))
                .andDo(print()).andDo(document("screenshot-urls-invalid-urls", Preprocessors.preprocessRequest(
                Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }


}
