package com.sun.sort;

/**
 * 排序算法类，第一章介绍的几种排序算法以及相应的伪代码和解释
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class Sort {

    /**
     * 运行次数
     */
    protected static int runTime = 0;

    /**
     * 合并次数
     */
    protected static int mergeTime = 0;

    /**
     * 合并排序次数
     */
    protected static int mergeSortTime = 0;

    //===================================================================================
    //====================================插入排序算法====================================
    //===================================================================================
    /**
     * 算法导论第一章
     *【算法种类】
     *【排序算法】1.插入排序算法
     *【算法代价】 Θ(n^2)
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
     *
     * 升序插入排序
     * @param sortArray
     * @return
     */
    public static int[] insertionSortAsc(int[] sortArray){

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
                runTime++;
            }
            //由于currentValue>前面位置的值或前面没有值，则该位置即该值应该在的位置，但由于while将位置-1，所以此处需要+1
            sortArray[beforePoint+1] = currentValue;
        }
        return sortArray;
    }

    //===================================================================================
    //====================================归并排序算法====================================
    //===================================================================================
    /**
     * @分治算法
     * @概念
     * 将原问题分解为几个规模较小但类似于原问题的子问题，递归的求解这些子问题（典型就是递归函数、树状操作），然后再合并这些子问题的解来建立原问题的解<br>
     *【算法种类】分治算法
     *【排序算法】2.归并排序
     *【算法代价】 Θ(nlgn)
     *      //如下为归并算法，将俩个已排序的子数组归并为一个已排序数组
     *@伪代码
     *      MERGE(A,p,q,r)
     *          //求出俩个子数组的长度（俩个数组分别是已排序好的数组）
     *          n1 = q-p+1
     *          n2 = r-q
     *          //定义子数组，长度为上面求出的长度
     *          let L[1..n1+1] and R[1..n2+1] be new arrays
     *          //根据原数组生成俩个子数组
     *          for i=1 to n1
     *              L[i]=A[p+i-1]
     *          for j=1 to n2
     *              R[j]=A[q+j]
     *          //假定数组长度+1位置为哨兵，用来简化判空，看到哨兵表示该数组到最后(实际此处判断如ps)
     *          L[n1+1]=∞
     *          R[n2+1]=∞
     *          i=1
     *          j=1
     *          //从起始位置开始，比较俩个子数组上第一张，小的放入结果数组中
     *          for k=p to r
     *              if L[i]<=R[j]
     *                  A[k]=L[i]
     *                  i++
     *              else
     *                  A[k]=R[j]
     *                  j++
     *      //如下为归并排序算法，递归进行归并排序(直到归并子数组长度为1时递归返回)
     *      MERGE-SORT(A,p,r)
     *          if p<r
     *              q=(p+r)/2
     *              MERGE-SORT(A,p,q)
     *              MERGE-SORT(A,q,r)
     *              MERGE(A,p,q,r)
     *
     *      ps:不引入哨兵的实际判断
     *          for k=p to r
     *              if i<L.length && j<R.length
     *                  if L[i]<=R[j]
     *                      A[k]=L[i]
     *                      i++
     *                  else
     *                      A[k]=R[j]
     *                      j++
     *              else if i<L.length && j>=R.length
     *                  A[k]=L[i]
     *                  i++
     *              else if j<R.length && i>=L.length
     *                  A[k]=R[j]
     *                  j++
     *              else
     *                  break
     *
     * @author sunx(sunxin@strongit.com.cn)<br/>
     * @version V1.0.0<br/>
     */
    public static int[] mergeSort(int[] sortArray, int begin, int end){
        //归并排序次数+1
        mergeSortTime++;
        int middle = (begin+end)/2;

        if(begin<end){
            sortArray = mergeSort(sortArray, begin, middle);
            sortArray = mergeSort(sortArray, middle+1, end);
            sortArray = merge(sortArray, begin, middle, end);
        }

        return sortArray;
    }

    public static int agCount = 0;

    /**
     * 归并俩个数组
     * @param sortArray
     * @param begin
     * @param middle
     * @param end
     * @return
     */
    public static int[] merge(int[] sortArray, int begin, int middle, int end){

        int[] left = new int[middle-begin+1];
        int[] right = new int[end-middle];

        for(int i=0; i<left.length; i++){
            left[i] = sortArray[begin+i];
        }
        for(int i=0; i<right.length; i++){
            right[i] = sortArray[middle+i+1];
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for(int i=begin; i<sortArray.length; i++){
            if(leftIndex>=left.length && rightIndex<right.length){
                sortArray[i] = right[rightIndex];
                rightIndex++;
            }else if(rightIndex>=right.length && leftIndex<left.length){
                sortArray[i] = left[leftIndex];
                leftIndex++;
            }else if(rightIndex<right.length && leftIndex<left.length){
                if(left[leftIndex] < right[rightIndex]){
                    sortArray[i] = left[leftIndex];
                    leftIndex++;
                }else{
                    sortArray[i] = right[rightIndex];
                    agCount += middle - i + 1;
                    rightIndex++;
                }
                //比较俩个数组当前位置值大小，则比较次数+1
                runTime++;
            }else{
                break;
            }
        }
        //执行归并的次数+1
        mergeTime++;
        return sortArray;
    }


    //===================================================================================
    //====================================霍纳规则========================================
    //===================================================================================
    /**
     * 霍纳规则求n次多项式的和，将时间复杂度从n(n-1)/2降低到n（原来是n(n-1)/2次加法+n(n-1)/2次乘法，降低到n次加法+n次乘法）<br>
     * 递归形式霍纳规则<br>
     * @param arr n次多项式的系数数组
     * @param n n次多项式的个数
     * @param i 当前是第几层
     * @param x x变量值
     * @return
     */
    public static int horner(int[]arr,int i, int n, int x){
        int sum = 0;
        if(i == n){//当到达n次时直接返回系数，否则继续递归
            return arr[n];
        }
        sum = arr[i] + x*horner(arr, i+1,n ,x);
        return sum;
    }
    /**
     * 非递归形式霍纳规则<br>
     * @param arr 系数数组
     * @param n n次多项式
     * @param x x变量值
     * @return
     */
    public static int horner3(int[]arr, int n, int x){
        int sum = arr[n]*x+arr[n-1];
        n--;
        for(;n>0;n--){
            sum = sum*x+arr[n-1];
        }
        return sum;
    }
}
