local eid = redis.call("hget", KEYS[1], ARGV[1])
if (eid)
then
	return redis.call("hget", KEYS[2], eid)
else
	return nil
end