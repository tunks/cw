FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#1 - WORKDIR and ENV
ENV BUILDPATH build

#2-COPY war file
ADD ./target/cw-*.jar $BUILDPATH/cw.war
ADD  $BUILDPATH/cw.war /opt/wildfly/standalone/deployments/

#3 - SET ADMIN PERMISSION
RUN /opt/jboss/wildfly/bin/add-user.sh cw cw#2016 --silent

#4- CMD
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
