FROM adoptopenjdk/openjdk11 AS builder
COPY . /opt/build
WORKDIR /opt/build
RUN ./gradlew assemble -Dorg.gradle.daemon=false


FROM adoptopenjdk/openjdk11:alpine as runner
RUN set -x mkdir -p /app
COPY --from=builder /opt/build/build/libs/api-*-all.jar /app/api-all.jar
EXPOSE 8080
WORKDIR /app

COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

CMD ./wait-for.sh -t 500 ${MYSQL_HOST-localhost}:${MYSQL_PORT-3306} -- \
        java ${JAVA_OPTS} -jar api-all.jar