package com.tyty.daily.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/10 10:06
 */
public class PropertyNamingStrategy {

    public static void main(String[] args) {
        //Person person = new Person();
        //person.personId = 21;
        //person.personName="小奋斗教程";
        //person.personEmail="1732482792@qq.com";
        //person.personPhone="156983444xx";

//		String response = "{\"person_id\":21,\"person_name\":\"小奋斗教程\",\"person_email\":\"1732482792@qq.com\",
//		\"person_phone\":\"156983444xx\"}";
        //String response = "{\"personid\":21,\"personname\":\"小奋斗教程\",\"personemail\":\"1732482792@qq.com\",
        // \"personphone\":\"156983444xx\"}";


        //String jsonString = JacksonUtils.getJsonUnderlineHumpConvert().toJsonString(person);
        //System.out.println(jsonString);

        //Person person1 = JacksonUtils.getJsonUnderlineHumpConvert().fromJsonString(response, Person.class);
//		Person person1 = JacksonUtils.getJsonConvertWithoutNull().fromJsonString(response, Person.class);
//		System.out.println(person1);
        System.out.println(new Person());

    }

}

@ToString
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
class Person {

    //ID
    public Integer personId;

    //名称
    @JsonProperty("person_name")
    private String personName;

    // 邮箱
    private String personEmail;

    // 手机号
    private String personPhone;

    protected Boolean bool;
}