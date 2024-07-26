For Redis Cache : <br>
docker run --name redis-cache -d -p 6379:6379 redis <br>
docker exec -it redis-cache redis-cli<br>
keys *

