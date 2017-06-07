redis.call("hset", KEYS[1], ARGV[1], ARGV[2])
redis.call("zadd", KEYS[2], ARGV[3], ARGV[1])
redis.call("zadd", KEYS[3], 0, ARGV[1])
redis.call("zadd", KEYS[4], 0, ARGV[1])