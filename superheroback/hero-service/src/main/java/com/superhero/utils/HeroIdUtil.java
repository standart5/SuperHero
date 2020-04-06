package com.superhero.utils;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@CommonsLog
public class HeroIdUtil {

    @Value(value = "${max-id-value}")
    private int maxIdValue;

    public Set<Integer> getRandomIds(){
        Set<Integer> ids = new HashSet<>();
        Random random = new Random();
        while (ids.size()<25){
            ids.add(getRandomInteger(1,maxIdValue));
        }
        return ids;
    }

    public int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
