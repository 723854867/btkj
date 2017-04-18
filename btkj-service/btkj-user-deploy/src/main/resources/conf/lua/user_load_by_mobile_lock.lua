local uid = redis.call("hget", KEYS[1], ARGV[1])
if (uid)
then
	local lock = string.gsub(ARGV[3], "{0}", uid)
	local isLock = redis.call("set", lock, ARGV[4], "NX", "PX", ARGV[5])
	if (isLock and (isLock["ok"] == "OK"))
	then
		local ukey = string.gsub(ARGV[2], "{0}", uid)
		local data = redis.call("hget", ukey, uid)
		if (data)
		then
			return data
		else
			return -2
		end
	else
		return -1
	end
else
	return -2
end