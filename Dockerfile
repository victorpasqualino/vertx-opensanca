FROM victorpasqualino/openjdk:9-jre-alpine

ADD target/vertx-opensanca-fat.jar /usr/share/vertx-opensanca-fat.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /usr/share/vertx-opensanca-fat.jar