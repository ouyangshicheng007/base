package com.yimi.oysc;

import java.util.regex.Pattern;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/27 10:15
 **/
public class MainTest {

    public static void main(String[] args) {

        String str = "/permission/list/1";

        Pattern pattern = Pattern.compile("/permission/list/\\d+");

        System.out.println(pattern.matcher(str).matches());

    }
}
