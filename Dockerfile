FROM maven:3.9.6-eclipse-temurin-21 AS build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn clean package -DskipTests
RUN jar xf target/eruservice-1.0.0.jar

RUN jdeps --ignore-missing-deps -q  \
    --recursive  \
    --multi-release 21  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    target/eruservice-1.0.0.jar > deps.info

RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /myjre


FROM debian:bookworm-slim
EXPOSE 8069
ENV JAVA_HOME /user/java/jdk21
ENV PATH $JAVA_HOME/bin:$PATH
COPY --from=build /myjre $JAVA_HOME
RUN apt-get update && apt-get install -y --no-install-recommends dumb-init
RUN mkdir /project
RUN addgroup --system javauser && adduser --system --shell /bin/false --ingroup javauser javauser
COPY --from=build /usr/src/project/target/eruservice-1.0.0.jar /project/
WORKDIR /project
RUN chown -R javauser:javauser /project
USER javauser
ENTRYPOINT dumb-init java -jar eruservice-1.0.0.jar
