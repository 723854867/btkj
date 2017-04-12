if redis.call("get", KEYS[1]) == ARGV[1]
then
	local num = redis.call("del", KEYS[1])
	if (0 == num)
	then
		return -1
	else
		redis.call("set", KEYS[2], ARGV[2], "PX", ARGV[3])
		return 0
	end
else
	return -1
end