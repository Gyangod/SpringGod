FROM openjdk:11-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/gyangod-0.0.1.jar springgod.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar springgod.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar springgod.jar
