FROM java:8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD css-rest.jar css-rest.jar
RUN bash -c 'touch /css-rest.jar'
ENTRYPOINT ["java","-jar","/css-rest.jar"]