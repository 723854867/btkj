redis.call("hset", KEYS[1], ARGV[1], ARGV[3])
redis.call("zadd", KEYS[2], ARGV[2], ARGV[1])
local len = #(ARGV)
if (len == 4)
then
	redis.call("hset", KEYS[3], ARGV[4], ARGV[3])
end