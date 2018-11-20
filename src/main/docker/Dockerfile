FROM java:8
VOLUME /tmp
ARG JAR_FILE
ADD demo-0.0.1-SNAPSHOT app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8800
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
