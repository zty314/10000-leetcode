package com.tyty.daily.convert;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/5/13 13:56
 */
public class String2Bit {


    public static void main(String[] args) {
        String json = "{\"confE164\": \"1233354\", \"reqheadinfo\": {\"confname\": \"\", \"reqeventid\": \"0\", \"reqsrcid\": \"0\", \"mtreqid\": \"0\", \"confE164\": \"\", \"moid\": \"\", \"reqrpc\": \"meeting.rpcmcu.q:55516f6a-79b8-11ec-b448-a4bf0161fef6_meeting\", \"reqrpcid\": \"9\"}, \"pid\": \"2562907\", \"ip\": \"10.67.18.151\", \"type\": \"CMU_CM_CONFMUTE_ACK\"}";
        byte[] bytes = json.getBytes();
        System.out.println(bytes.length);


    }
}