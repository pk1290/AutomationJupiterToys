package com.jupiter.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtility {
    public static String generator(int size){
        return RandomStringUtils.randomAlphabetic(size);
    }

    public static String emailGenerator(int size){
       return RandomStringUtils.randomAlphabetic(size)+"@"+"example.com";
    }

}
