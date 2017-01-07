package com.sun.sort;

/**
 * @分治算法
 * @概念
 * 将原问题分解为几个规模较小但类似于原问题的子问题，递归的求解这些子问题（典型就是递归函数、树状操作），然后再合并这些子问题的解来建立原问题的解<br>
 *
 *【算法2】归并排序
 *      //如下为归并算法，将俩个已排序的子数组归并为一个已排序数组
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

public class DivideAlgorithm {

    private static int runTime = 0;
    private static int mergeTime = 0;
    private static int mergeSortTime = 0;

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

    public static void main(String[] args) {
//        int[] test = SortConstant.sortArray;
        int[] test = SortConstant.getRandomArray();
        for(int value : test){
            System.out.println(value);
        }
        SortConstant.splitLine();
        test = mergeSort(test, 0, test.length-1);
        for(int value : test){
            System.out.println(value);
        }
        System.out.println("归并排序次数："+mergeSortTime);
        System.out.println("归并次数："+mergeTime);
        System.out.println("比较次数："+runTime);
    }
}
