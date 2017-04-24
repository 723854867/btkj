local flag = redis.call("sismember", KEYS[1], ARGV[1])
if (1 == flag)
then
	local set = redis.call("smembers", KEYS[2])
	if (next(set))
	then
		return redis.call("hmget", KEYS[3], unpack(set))
	else
		return set
	end
else
	return nil
end