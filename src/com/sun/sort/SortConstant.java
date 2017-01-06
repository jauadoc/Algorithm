package com.sun.sort;

import java.util.Random;

/**
 *
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 * @see {@link }
 */

public class SortConstant {

    public static final int[] sortArray = {7,1,5,11,6,3,14,23,5,18};

    public static final int nextInt = 1000;

    public static void splitLine(){
        System.out.println("=================================================");
    }

    public static int[] getRandomArray(int length){
        int[] randomArray = new int[length];
        Random random = new Random(17);
        for(int i=0; i<randomArray.length; i++){
            randomArray[i] = random.nextInt(nextInt);
        }
        return randomArray;
    }


}
