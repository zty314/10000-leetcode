package com.tyty.daily.wtf;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/7 15:35
 */
public class DuplicateElements {

    //public static boolean containsDuplicate(int[] nums) {
    //	long t1 = System.currentTimeMillis();
    //	Integer[] integers= Arrays.stream(nums).boxed().toArray(Integer[]::new);
    //	List<Integer> int1 = new ArrayList<>(nums.length);
    //	Collections.addAll(int1, integers);
    //	List<Integer> int2 = new ArrayList<>();
    //	int2.addAll(int1);
    //	int1.remove(0);
    //	Iterator<Integer> iterator = int2.iterator();
    //	while (iterator.hasNext()) {
    //		Integer next = iterator.next();
    //		Iterator<Integer> it = int1.iterator();
    //		while (it.hasNext()) {
    //			Integer compar = it.next();
    //			if (next.equals(compar)) {
    //				return true;
    //			}
    //		}
    //		if(int1.size() > 0){
    //			int1.remove(0);
    //		}
    //	}
    //	long t2 = System.currentTimeMillis();
    //	System.out.println(t2-t1);
    //	return false;
    //}

    public static void main(String[] args) {
        //int[] arr = {2,14,18,22,22};
        int[] arr = new int[9999999];
        for (int i = 0; i <= 9999998; i++) {
            arr[i] = i;
        }
        System.out.println(containsDuplicate(arr));
    }

    public static boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();
        for (int i = 0; i < nums.length; i++)
            if (!set.add(nums[i])) {
                return true;
            }
        return false;
    }
}
