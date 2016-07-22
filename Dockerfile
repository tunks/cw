FROM jboss/wildfly
MAINTAINER ebrimatunkara@gmail.com,dileepmohanan@gmail.com

#2 - WORKDIR and ENV
RUN /opt/jboss/wildfly/bin/add-user.sh cw cw#2016 --silent

#3- Port config
EXPOSE 9990

#4- CMD
ADD ./target/cw-*.war /opt/wildfly/standalone/deployments/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]