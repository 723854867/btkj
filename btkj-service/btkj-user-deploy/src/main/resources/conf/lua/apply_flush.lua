redis.call("hset", KEYS[1], ARGV[1], ARGV[3])
redis.call("zadd", KEYS[2], ARGV[2], ARGV[1])
redis.call("zadd", KEYS[3], ARGV[2], ARGV[1])
