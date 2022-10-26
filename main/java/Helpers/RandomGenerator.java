package Helpers;

import java.util.Random;

public class RandomGenerator {
    
    public int getRandomIntValue(int min, int max){
        Random r = new Random();
        int randomNum = r.nextInt((max - min) + 1) + min;
        
        if(randomNum == 0){
            randomNum = getRandomIntValue(min, max);
        }
        return randomNum;
    }  
}