local flag = redis.call("hsetnx", KEYS[1], ARGV[1], ARGV[2])
if (flag == 1)
then
	redis.call("hset", KEYS[2], ARGV[3], ARGV[4])
	redis.call("hset", KEYS[3], ARGV[5], ARGV[3])
end
