local isLock = redis.call("set", KEYS[1], ARGV[1], "NX", "PX", ARGV[4])
if (isLock and (isLock["ok"] == "OK"))
then
	local oldToken = redis.call("hget", KEYS[2], ARGV[2])
	redis.call("hset", KEYS[3], ARGV[3], ARGV[2])
	if (oldToken)
	then
		redis.call("hdel", KEYS[3], oldToken)
	end
	return 0
else
	return -1
end