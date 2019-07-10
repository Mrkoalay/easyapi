
docker rm -f css-rest
docker rmi -f css-rest
docker build -t css-rest:${POM_VERSION} .
docker run -v /data:/data  -v /logs:/logs --name css-rest --network css_net --ip 172.18.0.2   --restart=always   -p 8081:8081  -d  css-rest:${POM_VERSION}