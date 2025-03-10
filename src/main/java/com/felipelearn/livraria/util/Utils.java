package com.felipelearn.livraria.util;

public class Utils {
    public static boolean stringNotNullOrEmptyOrBlank(String text){
        return text.equals(null) || text.isBlank() || text.isEmpty();
    }
}
