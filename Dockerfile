FROM openjdk:11.0.11-jdk

COPY build/file-upload-0.0.1-SNAPSHOT.jar file-upload.jar

ENTRYPOINT ["java", "-jar", "/file-upload.jar"]