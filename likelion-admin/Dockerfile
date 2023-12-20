FROM openjdk:17
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8090

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=${PROFILE}","/app.jar"]
