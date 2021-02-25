FROM openjdk:8-jre-alpine3.9
COPY . /cluster_resource_monitor
WORKDIR /cluster_resource_monitor
CMD ["sh","start.sh"]