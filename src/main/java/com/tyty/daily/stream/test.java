package com.tyty.daily.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    public static void main(String[] args) throws InterruptedException {
        List<Member> list1 = new ArrayList<>();
        List<Member> list2 = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            Date date = new Date();
            //每个元素有名字
            list1.add(new Member((i + 1), "技术客", (i + 1), date));
//            if (i % 2 == 0) {
            //对2取模无名字
            list2.add(new Member((i + 1), null, (i + 1), date));
//            }
        }

        //stream流方法2
        long s7 = System.currentTimeMillis();
        List<Member> list4 = new ArrayList<>();
        list1.forEach(e1 -> {
                    list2.stream().anyMatch(e2 -> {
                        if (e1.getId().equals(e2.getId())) {
                            list4.add(e1);
                        }
                        return false;
                    });
//                    if (list2.stream().anyMatch(e2 -> e1.getId().equals(e2.getId()))) {
//                        list4.add(e1);
//                    }
                }
        );
//        System.out.println(list1);
        long s8 = System.currentTimeMillis();
        System.out.println("stream流方法2查询时间为：" + (s8 - s7) + "(毫秒)，一共查询出" + list4.size() + "条数据 \n\n\n");


        //双for循环嵌套测试
        long s1 = System.currentTimeMillis();
        List<Member> list5 = new ArrayList<>();
        int forNumber = 0;
        for (Member m2 : list2) {
            if (m2.getName() == null) {
                for (Member m1 : list1) {
                    if (m1.getId().equals(m2.getId())) {
//                      System.out.println(m2.getId()+" Name 值为空!!!");
                        list5.add(m1);
                    }
                }
            }
        }
        long s2 = System.currentTimeMillis();
        System.out.println("双for循环查询时间为：" + (s2 - s1) + "(毫秒)，一共查询出" + list5.size() + "条数据 \n\n\n");
        //TimeUnit.SECONDS.sleep(3);

        //map查询测试
       /* long s3 = System.currentTimeMillis();

        int mapNumber = 0;
        Map<Integer, Member> map = new HashMap<>();
        for (Member m1 : list1) {
            map.put(m1.getId(), m1);
        }
        for (Member m2 : list2) {
            if (m2.getName() == null) {
                Member m = map.get(m2.getId());
                if (m != null) {
//                  System.out.println(m2.getId()+" Name 值为空!!!");
                    mapNumber++;
                }
            }
        }
        long s4 = System.currentTimeMillis();
        System.out.println("使用map结构查询时间为：" + (s4 - s3) + "(毫秒)，一共查询出" + mapNumber + "条数据 \n\n\n");*/


        long s5 = System.currentTimeMillis();
        List<Member> list3 = new ArrayList<>();
        //stream流方法1
//        for (Member e1 : list1) {
        list1.forEach(e1 -> list2.stream().anyMatch(e2 -> {
            if (e1.getId().equals(e2.getId())) {
//                    e1.setName(e2.getName());
                list3.add(e1);
            }
            return false;
        }));
//        }
        long s6 = System.currentTimeMillis();
        System.out.println("stream流方法1查询时间为：" + (s6 - s5) + "(毫秒)，一共查询出" + list3.size() + "条数据 \n\n\n");


    }
}

class Member {
    private Integer id;
    private String name;
    private Integer age;
    private Date addDate;

    public Member() {
    }

    public Member(Integer id, String name, Integer age, Date addDate) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.addDate = addDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

}
