package com.sun.sort;

/**
 * 测试类，用于测试算法是否正确
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class Client {

    private static long startTime = 0;
    private static long endTime = 0;

    /**
     * 归并算法测试
     */
    public static void doMergeSort(){

        startTime = System.currentTimeMillis();
        int[] test = SortConstant.getRandomArray();
        for(int value : test){
            System.out.println(value);
        }
        SortConstant.splitLine();
        test = Sort.mergeSort(test, 0, test.length-1);
        for(int value : test){
            System.out.println(value);
        }
        endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime)+"ms");
        System.out.println("逆序对数为："+Sort.agCount);
        System.out.println("归并排序次数："+Sort.mergeSortTime);
        System.out.println("归并次数："+Sort.mergeTime);
        System.out.println("比较次数："+Sort.runTime);
    }

    /**
     * 插入排序算法测试
     */
    public static void doInsertSort(){
        startTime = System.currentTimeMillis();
        //输入规模大时算法效率较低
        int[] test = SortConstant.getRandomArray();
        test = Sort.insertionSortAsc(test);
        for(int value : test){
            System.out.println(value);
        }
        endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime)+"ms");
        System.out.println("比较次数："+Sort.runTime);
    }

    /**
     * 霍纳规则
     */
    public static void doHorner(){
        int[] arr = {1,2,3,4,5,6,7};
        System.out.println(Sort.horner(arr, 0, 1, 2));
    }

    public static void main(String[] args) {
        doInsertSort();
//        doMergeSort();
//        doHorner();
    }
}
