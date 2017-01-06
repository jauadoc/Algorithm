package com.sun.sort;


/**
 * 分析算法的方式
 * @概念
 * 由于算法在不同运行环境、不同运行状况下实现的效果和效率不同，为了屏蔽这一差异，<br>
 * 使用<b>单处理器计算模型---随机访问机器模型(random-access machine, RAM)</b><br>
 * @算法的代价
 * 分析算法时需要分析算法的执行代价，在给定规的<b>输入规模</b>时计算出每一步的执行代价，并求和<br>
 * 此时将每步的<b>执行代价抽象为c1</b>，n次执行每次代价为ci即nc1为n次执行总的代价<br>
 * ep：算法的总代价：c1*n+c2*(n-1)+c3*0+c4*n^2<br>
 * 计算算法代价时可以计算出算法的最坏情况、最好情况、平均情况<br>
 * <b>一般情况以最坏情况作为算法分析的依据</b>，在某些特定情况也会以平均情况作为分析依据<br>
 * @增长量级
 * 一个更简化的抽象：即运行时间的增长率或增长量级。<br>
 * 所以只考虑公式中最重要的项(如an^2+bn+c中an^2项) ps:个人感觉时间复杂度是不是同理？虽然时间复杂度是数学模型计算出的<br>
 *
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class AnalyzeAlgorithm {

    /**
     * @练习2.2-2
     * 1.2分析算法的方式
     * 选择算法
     * @伪代码如下
     * SelectMin[A]
     *  for j=1 to A.length
     *      changeIndex = j
     *      min = A[j]
     *      i=j+1
     *      while i<A.length
     *          if min > A[i]
     *              min = A[i]
     *      A[changeIndex] = A[j]
     *      A[j] = min
     * 选择排序算法
     */
    public static int[] selectMin(int[] sortArray){
        int runTime = 0;
        for(int i=0; i<sortArray.length; i++){
            int changeIndex = i;
            int min = sortArray[i];
            int j=i+1;
            while(j<=sortArray.length-1){
                if(min>sortArray[j]){
                    min = sortArray[j];
                    changeIndex = j;
                }
                j++;
                runTime++;
            }
            sortArray[changeIndex] = sortArray[i];
            sortArray[i] = min;
        }
        System.out.println("比较次数："+runTime);
        return sortArray;
    }

    public static void main(String[] args) {
        int[] sortArray = SortConstant.sortArray;
        //输入规模大时算法效率较低
//        int[] sortArray = SortConstant.getRandomArray(55);
        int[] sorted = selectMin(sortArray);
        for(int temp : sorted){
            System.out.println(temp);
        }
    }
    /**
     * @练习2.2-3
     * 线性查找最坏情况  最后一个为要查找的或没有找到，查询次数为n
     * 最佳情况为第一个即是要查找的，查询次数为1
     * 平均情况即 (1+n)/2
     * @练习2.2-4
     * 尽量减少循环与嵌套循环、降低循环次数
     *
     * 额外：堆排序
     */

}
