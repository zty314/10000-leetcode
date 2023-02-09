--[[
	KEYS[1] 函数名
	KEYS[2] e164号

	ARGV	参数
]]--

--[[
	本脚本提供的功能：
	1》结会时，清空会管自己的redis数据库
	2》获取会管所有正在召开的的会议的e164
]]--


--本脚本内全局变量--
local e164 = KEYS[2]; --E164号--

--e164 = assert(tonumber(e164), "e164 is nil or not a number");

--[[
	脚本私有函数命名空间
]]--
local dao = {};

--[[
	获取所有正在召开的会议的e164号
	@return nil/包含所有满足条件的会议的e164号的列表
]]--
local function getAllE164OfStartedMeeting()
    local result = {};    --list--
    local userDomainMoid = ARGV[1];
    local key_are = "domain/" .. userDomainMoid .. "/ares";
    local ares = redis.call("HGETALL", key_are);
    for index, value in ipairs(ares) do
        if (index % 2 == 0 and tostring(value) == "2") then
            result[#result + 1] = ares[index - 1];
        end
    end
    if (#result == 0) then
        return nil;
    end
    return cjson.encode(result);
end

--[[
	会议结束时清理redis中的数据
	清理的key在下面的keyTypeToDel中定义
]]--
local function meeting_cleanUp()
    local meetingMoid = KEYS[3]; -- meetingMoid
    local keyTypeToDel = {
        "are/" .. e164 .. "/rec/schemas",
        "are/" .. e164 .. "/okok",
        "are/" .. e164 .. "/okok/callers",
        "are/" .. e164 .. "/okok/members",
        "are/" .. e164 .. "/okoksign/callers",
        "are/" .. e164 .. "/okoksign/members",
        "are/" .. e164 .. "/mtorders",
        "are/" .. e164 .. "/info",
        "are/" .. e164 .. "/tvwall/styles",
        "prod_E164andMeetingMoid_" .. meetingMoid,
        "are/" .. meetingMoid .. "/order_rules"
    };

    --[[
		由keyType取出具体的对应的id，拼接成key
	]]--
    local schemas = redis.call('LRANGE', "are/" .. e164 .. "/rec/schemas", 0, -1);
    for idx, keyType in ipairs(schemas) do
        local k = "are/" .. e164 .. "/" .. "rec/" .. keyType
        keyTypeToDel[#keyTypeToDel + 1] = k;
    end

    local okokCallers = redis.call('SMEMBERS', "are/" .. e164 .. "/okok/callers");
    for idx, keyType in ipairs(okokCallers) do
        local k = "are/" .. e164 .. "/okok/callers/" .. keyType
        keyTypeToDel[#keyTypeToDel + 1] = k;
    end

    local okokMembers = redis.call('LRANGE', "are/" .. e164 .. "/okok/members", 0, -1);
    for idx, keyType in ipairs(okokMembers) do
        local k = "are/" .. e164 .. "/okok/members/" .. keyType
        keyTypeToDel[#keyTypeToDel + 1] = k;
    end

    local okoksignCallers = redis.call('SMEMBERS', "are/" .. e164 .. "/okoksign/callers");
    for idx, keyType in ipairs(okoksignCallers) do
        local k = "are/" .. e164 .. "/okoksign/callers/" .. keyType
        keyTypeToDel[#keyTypeToDel + 1] = k;
    end

    local okoksignMembers = redis.call('LRANGE', "are/" .. e164 .. "/okoksign/members", 0, -1);
    for idx, keyType in ipairs(okokMembers) do
        local k = "are/" .. e164 .. "/okoksign/members/" .. keyType
        keyTypeToDel[#keyTypeToDel + 1] = k;
    end

    dao.clearUp(keyTypeToDel);
    return "ok";
end

function dao.clearUp(keysArray)
    if (#keysArray == 0) then
        return ;
    end
    local fnStr = 'return redis.call("DEL", ' .. '"' .. table.concat(keysArray, '", "') .. '")';
    --redis.log(redis.LOG_NOTICE, fnStr);
    return loadstring(fnStr)();
end



--[[
	private method
	把redis返回的结果数组转换成key-value对象
	@return nil（数组的长度为0）/key-value对象
]]--
function dao.convertToObject(array)
    if (array and #array ~= 0) then
        local result = {};
        for index, value in ipairs(array) do
            if index % 2 == 0 then
                result[array[index - 1]] = value;
            end
        end
        return result;
    end
    return nil;

end

local services = {
    meeting_cleanUp = meeting_cleanUp,
    getAllE164OfStartedMeeting = getAllE164OfStartedMeeting
};

local serviceKey = KEYS[1];
local service = services[serviceKey];

return service();

