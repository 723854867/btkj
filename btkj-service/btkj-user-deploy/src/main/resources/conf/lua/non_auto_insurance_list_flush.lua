local flag = redis.call("sadd", KEYS[1], ARGV[1])
if (0 == flag)
then
	return
end
local len = #(ARGV)
if (1 == len)
then
	return
end
local hindex = 1
local sindex = 1
local data = {}
local data1 = {}
for i=2, len, 2
do
	data[hindex] = ARGV[i]
	data[hindex + 1] = ARGV[i + 1]
	data1[sindex] = ARGV[i]
	hindex = hindex + 2
	sindex = sindex + 1
end
redis.call("hmset", KEYS[2], unpack(data))
redis.call("sadd", KEYS[3], unpack(data1))
