local flag = redis.call("hsetnx", KEYS[1], ARGV[1], ARGV[2])
if (flag == 0)
then
	return redis.call("hkeys", KEYS[2])
else
	return 1
end
