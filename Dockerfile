FROM openjdk:11.0.11-jdk

COPY build/file-upload-0.0.1-SNAPSHOT.jar file-upload.jar

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

ENTRYPOINT ["java", "-jar", "/file-upload.jar"]