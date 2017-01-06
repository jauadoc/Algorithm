package com.sun.sort;

/**
 * 算法导论第一章
 * 算法类别：排序算法
 * 【算法1】.插入排序算法
 *  @注意：
 *  1.其中循环的参数表示位置，而非从0开始
 *      如2-length 即代码中 [1]-[length-1]
 *      while i>0 表示i的位置为第一个及以上   即 代码中i>=0
 *  @概念：
 *      1.循环不变式(loop invariant)
 *          属于数学归纳法，用于判断算法是否正确
 *          本例中
 *              初始时数组[0] 为有序的  （n=1）
 *
 *              for循环第一次执行数组[0][1] 为有序的
 *              ...
 *              for循环第n次执行数组.... 为有序的 (n=m)
 *              for循环第n+1此执行数组.....为有序的 (n=m+1)
 *
 *              for循环结束数组........为有序的 (根据数学归纳法(循环不变式)，得出for循环结束为有序的数组)
 *          但本例中结束并非是严谨的数学归纳法，而是认为控制结束条件（在条件之前为有序即可）
 *
 *
 * @伪代码如下
 * for j = 2 to A.length
 *  key = A[j];
 *  i = j-1;
 *  while i>0 and A[i]>key
 *      A[i+1] = A[i];
 *      i = i-1
 *  A[i+1] = key
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class InsertionSort {

    /**
     * 升序插入排序
     * @param sortArray
     * @return
     */
    public static int[] insertionSortAsc(int[] sortArray){

        int runtime = 0;

        //从数组第二个位置开始遍历
        for(int i=1; i<sortArray.length; i++){
            //记录当前位置的值
            int currentValue = sortArray[i];
            //位置指针，从当前位置前一个位置开始，逐步向前移动
            int beforePoint = i - 1;
            //如果前一个位置存在，且前一个位置的值大于当前位置的值，则前一个位置+1的位置的值为前一个位置的值（即currentValue和前面的值比较，currentValue小则前面的值后移，再和前面的值比较，知道比前面的值大位置）
            while(beforePoint >= 0 && sortArray[beforePoint]>currentValue){
                sortArray[beforePoint+1] = sortArray[beforePoint];
                beforePoint--;
                runtime++;
            }
            //由于currentValue>前面位置的值或前面没有值，则该位置即该值应该在的位置，但由于while将位置-1，所以此处需要+1
            sortArray[beforePoint+1] = currentValue;
        }
        System.out.println("比较次数："+runtime);
        return sortArray;
    }

    /**
     * 降序 插入排序
     * @param sortArray
     * @return
     */
    public static int[] insertionSortDesc(int[] sortArray){

        for(int i=1; i<sortArray.length; i++){
            int currentValue = sortArray[i];
            int beforePoint = i - 1;
            while(beforePoint>=0 && sortArray[beforePoint]<currentValue){
                sortArray[beforePoint+1] = sortArray[beforePoint];
                beforePoint--;
            }
            sortArray[beforePoint+1] = currentValue;
        }

        return sortArray;
    }

    public static void main(String[] args) {
//        int[] test = SortConstant.sortArray;
        //输入规模大时算法效率较低
        int[] test = SortConstant.getRandomArray(55);
        test = insertionSortAsc(test);
        for(int value : test){
            System.out.println(value);
        }
        SortConstant.splitLine();
        test = insertionSortDesc(test);
        for(int value : test){
            System.out.println(value);
        }
    }
    /**
     * 线性查找算法伪代码
    LINEAR-SEARCH(A,v)
    for j = 2 to A.length
        if v == A[j]
            return v
        return NIL
     */
}
