Pre Requisite


# Before running service you need to have following things.

# Install

1. Java
2. Maven
3. RabbitMQ


# Run Service

1. mvn clean package on console

2. mvn spring-boot:run

3. Using postman or any tool you can call the the url with following payload

POST http://localhost:8099/v1/xxx/screenshots


{
	"urls": [
		"",
		"https://www.baeldung.com/spring-file-upload",
		"https://stackoverflow.com/questions/33143743/read-data-from-multipartfile-which-has-csv-uploaded-from-browser/33168456",
		"https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html",
		"https://mvnrepository.com/artifact/commons-io/commons-io/2.6"

		]

}

you can as many urls in request body (Note: this is just example it can be also file.)




