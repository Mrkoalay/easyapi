# 运行mysql
docker pull mysql:5.7

mkdir -p /home/docker_mnt/mysql/data /home/docker_mnt/mysql/logs /home/docker_mnt/mysql/conf

docker run  -p 13307:3306 --name css-db --restart=always  --network css_net --ip 172.18.0.3 -v /etc/localtime:/etc/localtime -v /home/docker_mnt/mysql/conf/my.cnf:/etc/mysql/conf.d/my.cnf -v /home/docker_mnt/mysql/logs:/var/log/mysql -v /home/docker_mnt/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=W5v2hxFax2e0Gn1q -d mysql:5.7
