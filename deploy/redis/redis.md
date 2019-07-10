docker run \
> -p 6379:6379 \
> -v /data/redis/data:/data \
> -v /data/redis/conf/redis.conf:/etc/redis/redis.conf \
> --privileged=true \
> --name myredis \
> -d redis redis-server /etc/redis/redis.conf
