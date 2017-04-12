local data = redis.call("hget", KEYS[1], ARGV[1])
if (data)
then
	redis.call("hdel", KEYS[1], ARGV[1])
	redis.call("zrem", KEYS[2], ARGV[1])
	redis.call("hdel", KEYS[3], ARGV[2])
	return data
else
	return nil
end