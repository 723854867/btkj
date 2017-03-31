local flag = redis.call("hexists", KEYS[1], ARGV[1])
if (flag == 1)
then
	redis.call("hset", KEYS[2], ARGV[2], ARGV[3])
	redis.call("hset", KEYS[3], ARGV[4], ARGV[2])
end
