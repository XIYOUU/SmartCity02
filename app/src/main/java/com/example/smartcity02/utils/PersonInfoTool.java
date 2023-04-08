package com.example.smartcity02.utils;

public  class PersonInfoTool {
    /**隐藏身份证号码，只保留前2位和后4位*/
    public static String hideIdCard(String cardNumber){
        int len=cardNumber.length();
        return cardNumber.substring(0,2)+"************"+cardNumber.substring(len-4);
    }

    //隐藏手机号后8位
    public static String hintPhoneId(String phoneId){
        return phoneId.substring(0,3)+"XXXXXXXX";
    }
}
