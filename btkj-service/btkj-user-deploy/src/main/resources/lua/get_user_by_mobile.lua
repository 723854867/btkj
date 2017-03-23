local uid = redis.call("hget", KEYS[1], ARGV[1])
if (not uid)
then
	return nil
else
	return redis.call("hget", KEYS[2], ARGV[1])
end