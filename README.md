# springboot-kafka-email-service

A Spring Boot application integrating with Apache Kafka to send email notifications. Consumes Kafka messages and sends emails to predefined recipients. Features scalable and reliable email service with configurable recipient list. Ideal for applications requiring efficient email notifications.

## Prerequisites

- Java 11 or higher
- Apache Kafka
- ZooKeeper
- Maven
- An SMTP server for sending emails

## Setup

1. **Clone the Repository**

    ```bash
    git clone https://github.com/imsameersen/springboot-kafka-email-service.git
    cd springboot-kafka-email-service
    ```

2. **Update Application Properties**

   Update the `src/main/resources/application.properties` file with your Kafka and email server configurations.

    ```properties
    # Kafka Configuration
    spring.kafka.bootstrap-servers=localhost:9092
    spring.kafka.consumer.group-id=group_id
    spring.kafka.consumer.auto-offset-reset=earliest
    spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
    spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
    spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
    spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

    # Email Configuration
    spring.mail.host=smtp.example.com
    spring.mail.port=587
    spring.mail.username=your_email@example.com
    spring.mail.password=your_password {it will be your App Password which you can create in App passwords in Google Account Setting}
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true

    # Predefined Email Recipients
    email.recipients=recipient1@example.com,recipient2@example.com,recipient3@example.com
    ```

3. **Start Kafka and ZooKeeper**

   Make sure Kafka and ZooKeeper are running. If you have Kafka installed locally, you can start them using the following commands:

    ```bash
    zookeeper-server-start.sh /usr/local/etc/kafka/zookeeper.properties
    kafka-server-start.sh /usr/local/etc/kafka/server.properties
    ```

   Alternatively, you can use Docker Compose to start Kafka and ZooKeeper:

    ```yaml
    version: '2'
    services:
      zookeeper:
        image: wurstmeister/zookeeper:3.4.6
        ports:
         - "2181:2181"
      kafka:
        image: wurstmeister/kafka:latest
        ports:
         - "9092:9092"
        environment:
          KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        volumes:
         - /var/run/docker.sock:/var/run/docker.sock
    ```

   Start the services:

    ```bash
    docker-compose up
    ```

4. **Build and Run the Application**

   Use Maven to build and run the Spring Boot application:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

5. **Send Email Notifications**

   You can send email notifications by sending a POST request to the `/api/v1/email/send` endpoint with a `message` parameter. You can use tools like Postman or `curl`:

   **Example Request:**

    - **URL:** `http://localhost:8080/api/v1/email/send`
    - **Method:** `POST`
    - **Parameter:** `message=Your email message`

    ```bash
    curl -X POST "http://localhost:8080/api/v1/email/send?message=Your email message"
    ```

## Project Structure

- **KafkaProducer:** Sends messages to the Kafka topic.
- **KafkaConsumer:** Consumes messages from the Kafka topic and triggers the email sending.
- **EmailService:** Sends emails to the predefined list of recipients.
- **EmailController:** REST controller to handle incoming requests to send email notifications.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## Contact

For any inquiries, please contact [sameersen.net@gmail.com].
