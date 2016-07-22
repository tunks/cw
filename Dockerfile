FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#1-COPY war file
COPY target/cw-service.war /opt/wildfly/standalone/deployments/cw.war

#2 - SET ADMIN PERMISSION
RUN /opt/jboss/wildfly/bin/add-user.sh cw cw#2016 --silent

#3- CMD
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
