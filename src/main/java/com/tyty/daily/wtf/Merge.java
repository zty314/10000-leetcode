package com.tyty.daily.wtf;

import java.util.*;

class Merge {
    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //由于存在{{1,2}{4,5}{6,7},{1,10}},所以优先排序再对比坐标才能保证最后是唯一区间
        //否则会出现类似：[[1,10]，[1,10]，[1,10]]
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> ranges = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int starti = intervals[i][0];
            int endi = intervals[i][1];
            for (int j = i + 1; j < intervals.length; j++) {
                int startj = intervals[j][0];
                int endj = intervals[j][1];

                if (endi >= startj && endj >= starti) {
                    starti = Math.min(starti, startj);
                    endi = Math.max(endi, endj);
                    i++;
                }
            }
            ranges.add(new int[]{starti, endi});
        }
        //toArray方法中的参数只是为了说明返回数组的元素类型，并不需要开辟空间
        return ranges.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] arr = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        int[][] merge = merge(arr);
        System.out.println(Arrays.deepToString(merge));
    }
}