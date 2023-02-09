package com.tyty.daily.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/3/21 10:46
 */
public class TestList {
    public static void main(String[] args) {

        List<MemberName> nameList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            nameList.add(new MemberName(i + 1, "a" + 1));
        }
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            memberList.add(new Member(i, nameList, i * 10));
        }

        memberList.forEach(m -> {
            List<MemberName> names = m.getName();
            names.forEach(n -> {
                n.setId(n.getId() * -1);
            });
            m.setName(names);
        });

        System.out.println(memberList);
    }
}

@Data
@AllArgsConstructor
class Member {
    private Integer id;
    private List<MemberName> name;
    private Integer age;
}

@Data
@AllArgsConstructor
class MemberName {
    private Integer id;
    private String name;
}