local id = redis.call("hget", KEYS[1], ARGV[1])
if (id)
then
	return redis.call("hget", KEYS[2], id)
else
	return nil
end