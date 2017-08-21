local uid = redis.call("hget", KEYS[1], ARGV[1])
if (uid)
then
	return redis.call("hget", KEYS[2], uid)
else
	return nil
end