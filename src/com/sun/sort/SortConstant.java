package com.sun.sort;

import java.util.Random;

/**
 * 算法测试帮助类，提供如随机数组等多处使用的工具。
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 * @see {@link }
 */

public class SortConstant {

    /**
     * 默认排序数组
     */
    public static final int[] sortArray = {7,1,5,11,6,3,14,23,5,18};

    /**
     * 数组数字范围0-1000
     */
    public static final int nextInt = 1024;

    public static final int arrayLength = 66666;

    public static void splitLine(){
        System.out.println("=================================================");
    }

    /**
     * 获得指定长度的随机数生成数组
     * @param length
     * @return
     */
    public static int[] getRandomArray(int length){
        int[] randomArray = new int[length];
        Random random = new Random(17);
        for(int i=0; i<randomArray.length; i++){
            randomArray[i] = random.nextInt(nextInt);
        }
        return randomArray;
    }

    /**
     * 获取随机数生成数组
     * @return
     */
    public static int[] getRandomArray(){
        return getRandomArray(arrayLength);
    }


    //===================================================================================
    //====================================归并排序算法====================================
    //===================================================================================
}
