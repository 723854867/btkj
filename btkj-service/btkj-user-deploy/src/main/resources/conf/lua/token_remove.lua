local uid = redis.call("hget", KEYS[1], ARGV[1])
if (uid) 
then
	local lock = string.gsub(ARGV[2], "{0}", uid)
	local isLock = redis.call("set", lock, ARGV[3], "NX", "PX", ARGV[4])
	if (isLock and (isLock["ok"] == "OK"))
	then
		redis.call("hdel", KEYS[1], ARGV[1])
		redis.call("hdel", KEYS[2], uid)
		return tonumber(uid)
	else
		return -1
	end
else
	return -2
end