Basic auth
username : sandeep
password : root




<!-- activemq configuiration setup -->

You need to install docker in your machine to run this llocally

Run these commands
docker pull rmohr/activemq - Pulls activemq image from docker
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq  -
 61616 port is the communication channel for client to connect to active mqbroker (JMS communication)
 8161 is for the console
