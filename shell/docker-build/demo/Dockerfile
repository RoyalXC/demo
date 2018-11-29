FROM myjre8:v1.0
VOLUME /tmp
ARG JAR_FILE
ADD demo-1.0.0.jar app.jar
EXPOSE 30665
RUN mkdir -p /data/logs
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-jar","app.jar"]