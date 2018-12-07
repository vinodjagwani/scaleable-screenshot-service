 xxx Screenshot Solution:

xxx Screenshot solution is splitted into one producer (with 2 queue with random selections of queue, for high performance queues can be increased and randomly to be picked up for sending unique message) and second consumer. Consumer can scale up to N instances which consumes the messages from the queue. Not only that you can assign different set of queues to each consumer and scale up to N instances of that consumer with that set of queue.

1. Consumer with (Queue-A) 1→ N instance. 2. Consumer with (Queue-B) 1→ N Instance. 3. Consumer with (Queue-C) 1→ N Instance.
So lets say Consumer with Queue-A have 50 instance and queue receive 100 urls and now 50 consumer will process 50 urls parrelly and each instance process 1 url in 5 second then all 100 urls will be processed in 10 seconds so similarly Queue-B and Queue-C will process in same way.
Producer service is responsible for accepting a list of URLs and sending each url to the random queue (in codebase 2 queues are define for the sake of showing example, no of queues can be pre-defined).
Consumer with assigned queue will listen all the time what arrives on that queue and extract the url and pass to capture service (currently selenium is being used to take screenshot) and pass screenshot to the database (mongodb which is also highly scalable), after saving screenshots into mongob db, you can easily retrieve screenshot from the db using same consumer service running under load balancer(for instance running with service registry or any cloud platform services).

Infrastructure:

Both producer and consumer are microservices and are fully cloud compatible, ideally can be deployed on GCP, AWS or Cloudfoundry for scalability or also can be deployed on own hosted server along with SpringCloud netflix service registry for load balancing.

RabbitMQ:
RabbitMQ is message broker worked based publish/scriber fashion, its highly scalable with durable messages or messages for certain periods, it also process asynchronous request. RabbitMQ is perfect fit for the process millions request a day. Since Queue can be listened by multiple consumer services and many instance consumes messages simultaneously and this is the key of high performance application.
Url​​: ​https://www.rabbitmq.com/tutorials/amqp-concepts.html

MongoDB:
MongoDB is highly scalable and highly available database with replica set consists of two or more copies of the data. Each replica set member may act in the role of primary or secondary replica at any time. All writes and reads are done on the primary replica by default. Secondary replicas maintain a copy of the data of the primary using built-in replication. When a primary replica fails, the replica set automatically conducts an election process to determine which secondary should become the primary. Secondaries can optionally serve read operations, but that data is only eventually consistent by default. MongoDB stores data in the form of document which contains key value. For our screenshots I am using GridSFBucket so instead of storing a
 
file in a single document, GridFS divides the file into parts, or chunks, and stores each chunk as a separate document.
Url: ​​https://www.mongodb.com/mongodb-scale
Limitation in my solution for time constraints:
Since this is test challenge and overall picture of real design for production.
Currently I am not handling when consumer unprocessed the message, so i would ideally send back that message to queue and apply retry logic after certain period of time.
 
 Tools and Framework:
1. Java 8
2. Spring
3. Maven
4. RabbitMQ
5. MongoDB
6. Selenium WebDriver
7. Spring Rest Docs
8. Lombok
