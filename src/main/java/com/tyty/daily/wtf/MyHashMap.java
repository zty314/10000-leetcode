package com.tyty.daily.wtf;

/**
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 */
class MyHashMap {

    Integer[][] myHashMap;

    public MyHashMap() {
        myHashMap = new Integer[0][];
    }

    public void put(int key, int value) {
        if (myHashMap.length >= key + 1) {
            myHashMap[key][0] = value;
        } else {
            if(myHashMap.length == 0){
                Integer[][] temp = new Integer[key + 1][1];
                temp[key][0] = value;
                myHashMap = temp;
                return;
            }
            Integer[][] temp = new Integer[key + 1][1];
            for (int i = 0; i < myHashMap.length ; i++) {
                temp[i][0] = myHashMap[i][0];
            }
            temp[key][0] = value;
            myHashMap = temp;
        }
    }

    public int get(int key) {
        if(key >= myHashMap.length){
            return -1;
        }
        return myHashMap[key][0] == null ? -1 : myHashMap[key][0];
    }

    public void remove(int key) {
        if(key >= myHashMap.length){
            return;
        }
        myHashMap[key][0] = null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */