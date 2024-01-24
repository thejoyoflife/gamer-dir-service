FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder
WORKDIR /home/app
COPY pom.xml ./
COPY src/ ./src
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests
ARG JAR_FILE=target/*.jar
RUN java -Djarmode=layertools \
          -jar ${JAR_FILE} extract \
          --destination ./target/
RUN jdeps --ignore-missing-deps -q \
      --recursive \
      --multi-release 17 \
      --print-module-deps \
      --class-path './target/dependencies/BOOT-INF/lib/*' \
      ${JAR_FILE} > deps.info
      
RUN jlink \
      --add-modules $(cat deps.info) \
      --strip-debug \
      --compress zip-6 \
      --no-header-files \
      --no-man-pages \
      --output /springboot-runtime      

##################

FROM alpine:latest

COPY --from=builder /springboot-runtime /opt/jdk
ENV PATH=$PATH:/opt/jdk/bin

WORKDIR /app

COPY --from=builder /home/app/target/dependencies/ ./
COPY --from=builder /home/app/target/spring-boot-loader/ ./   
COPY --from=builder /home/app/target/snapshot-dependencies/ ./    
COPY --from=builder /home/app/target/application/ ./
      
RUN addgroup -S fortune-group
RUN adduser -S fortune-user -G fortune-group

EXPOSE 8080
USER fortune-user

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]