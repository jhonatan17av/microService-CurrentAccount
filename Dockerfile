FROM openjdk:8
VOLUME /tmp
EXPOSE 9090
ADD ./target/microService-CurrentAccount-0.0.1-SNAPSHOT.jar service-currentAccount.jar
ENTRYPOINT ["java","-jar","/service-currentAccount.jar"]
