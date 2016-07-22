FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#2 - WORKDIR and ENV
ENV BUILDPATH build

#3- Port config
EXPOSE 9990

#4- CMD
ADD ./target/cw-*.war /opt/wildfly/standalone/deployments/