local flag = redis.call("sismember", KEYS[1], ARGV[1])
if (flag == 0)
then
	return redis.call("hkeys", KEYS[2])
else
	return nil
end
