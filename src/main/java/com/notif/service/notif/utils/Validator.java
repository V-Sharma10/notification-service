package com.notif.service.notif.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Validator {
    public static boolean isValidIndianMobileNumber(String s)
    {
        Pattern p = Pattern.compile("^[6-9]\\d{9}$");

        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    public static boolean isValidMessage(String s){
        if(s.length()>5){
            return true;
        }
        return false;

    }
}
