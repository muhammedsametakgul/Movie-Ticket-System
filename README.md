For Redis Cache : <br>
docker run --name redis-cache -d -p 6379:6379 redis
docker exec -it redis-cache redis-cli
keys *

