FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#2 - WORKDIR and ENV
ENV BUILDPATH build

#2- CMD
ADD ./target/cw-*.war /opt/wildfly/standalone/deployments/