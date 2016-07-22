FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#2 - WORKDIR and ENV
RUN /opt/jboss/wildfly/bin/add-user.sh cw cw#2016 --silent

#4- CMD
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
ADD target/cw-service.war /opt/wildfly/standalone/deployments/
