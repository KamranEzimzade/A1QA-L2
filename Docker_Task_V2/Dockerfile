FROM tomcat:9.0-alpine

LABEL maintainer="Kamran Azimzada"

WORKDIR /usr/local/tomcat/webapps/lterm

RUN apk update && \
    apk add git 

RUN git clone https://github.com/a1qatraineeship/docker_task.git /usr/local/tomcat/webapps/lterm

EXPOSE 8080/tcp

CMD [ "catalina.sh","run" ]



