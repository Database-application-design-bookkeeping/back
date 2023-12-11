FROM openjdk:8-jre

EXPOSE 8080

WORKDIR /app

COPY ./target/jizhangpro-1.0-SNAPSHOT.jar /app/jizhang.jar

# 指定容器启动时运行的命令
CMD ["java", "-jar", "jizhang.jar"]

