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
 * PS:<b>渐近精确界记号：Θ</b><br>
 * 当俩个函数趋近于无穷时越来越接近，则可以表示为f(n)=Θ(g(n))
 *
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class AnalyzeAlgorithm {

    public static int runTime = 0;
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
        return sortArray;
    }

    /**
     * @算法分析-归并排序
     * 假定<b>规模为n</b>的问题求解时间如下<br>
     *  1.求解规模为1的时间 Θ(1)<br>
     *  2.分解求解时间 <br>
     *      --将问题分解为a个子问题，每个子问题的规模为1/b，每个执行时间为函数T()，则每个子问题的规模为n/b，则子问题所需时间为T(n/b)，分解问题所需的时间为D(n)，合并问题解所需的时间为C(n)<br>
     *      ---- <b>递归式(递归方程)</b>如下: (当问题小于不可分割值k时，问题无法被分割，直接求解)<br>
     *      ---- =>T(n)=Θ(1)  当n<=k<br>
     *      ---- =>T(n)=aT(n/b)+D(n)+C(n) 当n>k<br>
     *      ----也就是当可递归时进行递归，当不满足递归时直接求解问题<br>
     * @归并排序
     * 假定归并排序的规模为2^n，这样每次分解的子问题都为n/2个，便于递归式分析（而且此假定不影响算法解的增量式）<br>
     * 合并时所需要比较的次数，最多为n次，则C(n)=Θ(n)；分解则直接分解，不需比较为Θ(1) =>C(n)+D(n)≈Θ(n)<br>
     * --则归并排序算法的递归式如下:(当n<=1是无法被分为俩份，则直接求解该次比较时间)<br>
     * ---- =>T(n)=Θ(1)  当n<=1，规模为1的问题 <br>
     * ---- =>T(n)=2T(n/2)+Θ(n) 当n>1 规模为n的问题<br>
     * ------假定规模为1的问题处理时间为c（这里的c和上面的c不同）<br>
     * ------ =>T(n)=c  当n<=1 <br>
     * ------ =>T(n)=2T(n/2)+cn 当n>1<br>
     * --------由上可得出一个递归树<br>
     * --------由于T(n)=2T(n/2)+cn递归执行=> 将递归式分解成为一颗递归树=>T(n)=cnlgn+cn
     * ----------T(n/n)=T(1)=c(分解到最底层的处理时间为假定规模1的处理时间c)=>2T(n/2)=2c(n/2)=>4T(n/4)=4c(n/4))即可以推到出一颗递归树，递归树每级的代价都为cn
     * ----------所以归并排序算法的Θ(T(n))=Θ(nlgn)
     */
    public static void analyzeMergeSort(){}

    public static void main(String[] args) {
        int[] sortArray = SortConstant.getRandomArray();
        int[] sorted = selectMin(sortArray);
        for(int temp : sorted){
            System.out.println(temp);
        }
        System.out.println("比较次数："+runTime);
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
