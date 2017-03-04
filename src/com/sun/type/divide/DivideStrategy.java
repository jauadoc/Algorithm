package com.sun.type.divide;

/**
 * @分治策略
 * 如果问题可以被分解为子问题，子问题和原问题解决方式相同，只是规模更小，则可采用分治策略来求解。如递归问题<br>
 * @步骤
 * 1.分解：将问题划分为子问题，子问题和原问题一样，只是规模更小<br>
 * 2.解决：步骤地递归求解出子问题，直至问题规模足够小可直接求解<br>
 * 3.合并：将子问题的解组合成原问题的解<br>
 * @递归式
 * 递归式和分治方法紧密相关的<br>
 * @三种求解递归式的方法
 * 1.代入法：猜测一个界，然后用数学归纳法证明这个界是正确的<br>
 * 2.递归树法：将递归问题转化为一棵树，节点表示不同层次递归调用产生的代价，然后采用边界和技术求解递归公式（如第二章归并排序算法的递归式转化递归树）<br>
 * 3.主方法：可求解公式T(n)=aT(n/b)+f(n)的界<br>
 * <li>T(n)=aT(n/b)+f(n)</li>
 * <li>生成a个问题，每个问题的规模是原问题的1/b，分解和合并花费f(n)时间代价。（归并排序既如此，参看第二章归并排序算法）</li>
 * @递归式的技术细节
 * 如同第二章介绍归并排序算法时介绍一样，递归式忽略了一些不重要的细节，且一些定理也证明了这些细节对结果影响不大。（如合并时间、分解时间）<br>
 *
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */
public class DivideStrategy {

    //===================================================================================
    //====================================最大子数组问题==================================
    //===================================================================================
    /**
     * @最大子数组问题
     * 求解存在正数及负数的数组P中所有子数组中，子数组所有元素相加的和最大的那一个。<br>
     * 如：[1,-2,3,-7,5] 最大子数组为[1,-2,3]，期值为2<br>
     * @实际问题：已知股票价格走势状态，求解在已知数据内，何时买入何时卖出获益最大。<br>
     * 将收益问题的时间-价格转换为表转换为时间-价格变化数组，来求解价格变化数组中值最大的子数组。<br>
     * @解决思路
     * 1.将数组二等分（或不等分），求出从中间至最小的最大值及对应位置、中间至最大的最大值及对应位置、左右最大值的和。<br>
     * 2.比较左最大值、右最大值、左右最大值之和，哪个大则返回哪个对应的位置及其值
     * 3.重复问题直至数组被等分为长度为1的最小问题
     *
     */

    /**
     * 寻找跨过mid位置的子arr的最大子数组，通过求从mid->low的左侧所有子数组的最大值及mid+1->high的右侧所有子数组的最大值来求出跨越中间的最大子数组
     * @param arr 求解数组
     * @param low 查找的起始位置
     * @param mid 问题分割的中间位置
     * @param high 查找的结束位置
     * @return
     */
    public static SubArray findMaxCrossingSubarray(int[] arr, int low, int mid, int high){

        //[mid]、[mid-1,mid]、[mid-2,mid-1,mid]、[low,...,mid]这些子数组中数组元素和最大那组的索引位置
        int leftMaxSumIndex = mid;
        //[mid]、[mid-1,mid]、[mid-2,mid-1,mid]、[low,...,mid]这些子数组中数组元素和最大那组的所有元素和
        int leftMaxSum = 0;
        //从mid开始，至low结束，∑index mid->low  arr[index]
        int subArraySum = 0;
        //从中间到最左边进行遍历
        for(int i=mid; i>0; i--){
            subArraySum += arr[i];//[i...mid]的和
            if(i==mid || subArraySum>leftMaxSum){//第一次遍历时直接赋值即可，第二次再比较
                leftMaxSum = subArraySum;
                leftMaxSumIndex = i;
            }
        }

        int rightMaxSumIndex = mid+1;
        int rightMaxSum = 0;
        subArraySum = 0;
        for(int i=mid+1; i<high; i++){
            subArraySum += arr[i];
            if(i==mid+1 || subArraySum>rightMaxSum){//第一次遍历时直接赋值即可，第二次再比较
                rightMaxSum = subArraySum;
                rightMaxSumIndex = i;
            }
        }

        return new SubArray(leftMaxSumIndex, rightMaxSumIndex, leftMaxSum+rightMaxSum);
    }

    /**
     * 将寻找最大子数组的问题分解为三种情况<br>
     * 1.最大子数组在左侧<br>
     * 2.最大子数组在右侧<br>
     * 3.最大子数组跨过中间位置<br>
     * 将数组等分（此处为二等分，个人感觉多等分亦可），按照上述方式分治该问题<br>
     * 每一层级返回的都是当前层级左侧、右侧、跨越中间三种情况中最大的值。<br>
     * @param arr 求解数组
     * @param low 子数组起始位置
     * @param high 子数组结束位置
     * @return
     */
    public static SubArray findMaximumSubarray(int[] arr, int low, int high){
        if(high == low){//如果子数组起止位置相同，则直接返回该数组即可，因为数组只有一个值
            return new SubArray(low, high, arr[low]);
        }else{
            int mid = (high+low)/2;//计算中间位置
            SubArray leftSub = findMaximumSubarray(arr, low, mid);//递归求解左侧最大值
            SubArray rightSub = findMaximumSubarray(arr, mid+1, high);//递归求解右侧最大值
            SubArray crossSub = findMaxCrossingSubarray(arr, low, mid, high);//当前(子)数组跨越中间的最大值
            if((leftSub.getSum() >= rightSub.getSum()) && (leftSub.getSum() >= crossSub.getSum())){//若最大值在左边
                return leftSub;
            }else if((rightSub.getSum() >= leftSub.getSum()) && (rightSub.getSum() >= crossSub.getSum())){//最大值在右边
                return rightSub;
            }else{//最大值在跨越中间时
                return crossSub;
            }
        }
    }

    /**
     * 线性的最大子数组查找<br>
     * 从左至右查找，只要一段子数组和<0，则前面的最大值即前一段数据的最大子数组。<br>
     * 然后再继续查找，找出多段的最大子数组，然后返回这些段内最大子数组中最大的一个。<br>
     * 总结：从左至右，分段找出每一段中的最大子数组，然后返回其中最大的，分段的方法为：若arr[i...j]<0，则进入下一段。
     * @param arr
     */
    public static int maxSubArrayWithLinear(int[] arr){
        //每一段的最大值（直至这一段的和<0，则重新计算下一段）
        int tempSum = 0;
        int maxSum = 0;
        for(int i=0; i<arr.length; i++){
            tempSum += arr[i];
            if(tempSum > maxSum){//如果这一段的最大值大于当前记录的最大值，则覆盖最大值
                maxSum = tempSum;
            }
            if(tempSum < 0){//如果这一段的最大值<0，则本段+下段的值<下段的值（ tempSum+nextSubArr < nextSubArr，因为tempSum<0）
                tempSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 线性的最大子数组查找，返回最大子数组对象（包含起止位置和最大子数组的和）<br>
     * 从左至右查找，只要一段子数组和<0，则前面的最大值区域即前一段子数组的最大子数组。<br>
     * 然后再继续查找，找出多段的最大子数组，然后返回这些段内最大子数组中最大的一个。<br>
     * 总结：从左至右，分段找出每一段中的最大子数组，然后返回其中最大的，分段的方法为：若arr[i...j]<0，则进入下一段。
     * @param arr
     */
    public static SubArray maxSubArrayWithLinearSubObj(int[] arr){
        //每一段的子数组（直至这一段的和<0，则重新计算下一段）
        SubArray tempSubArray = new SubArray(0, 0, 0);
        //所有段子数组中最大的那一个
        SubArray maxSubArray = new SubArray(0, 0, 0);


        for(int i=0; i<arr.length; i++){
            tempSubArray.setSum(tempSubArray.getSum() + arr[i]);
            if(tempSubArray.getSum() > maxSubArray.getSum()){//如果这一段的最大值大于当前记录的最大值，则覆盖最大值
                //更新最大子数组信息
                maxSubArray.setLow(tempSubArray.getLow());
                maxSubArray.setHigh(i);
                maxSubArray.setSum(tempSubArray.getSum());
            }
            if(tempSubArray.getSum() < 0){//如果这一段的最大值<0，则本段+下段的值<下段的值（ tempSum+nextSubArr < nextSubArr，因为tempSum<0）
                //更新当前这段子数组的信息
                tempSubArray.setLow(i+1);
                tempSubArray.setHigh(i+1);
                tempSubArray.setSum(0);
            }
        }
        return maxSubArray;
    }

    public static void main(String[] args) {
        //传递参数起止位置为数组起止位置0-arr.length-1
        //存在bug，待修复
        SubArray maximumSubArray = findMaximumSubarray(new int[]{1,-2,5,-3,6,-4,-1,117,111,2}, 0, 9);
//        SubArray maximumSubArray = findMaximumSubarray(new int[]{-1,-2,-5,-3,-6,-4,-1,-7}, 0, 7);
        System.out.println(maximumSubArray);

        int test =maxSubArrayWithLinear(new int[]{1,-2,5,-3,6,-4,-1,6});
        System.out.println(test);
        System.out.println(maxSubArrayWithLinearSubObj(new int[]{1,-2,5,-3,6,-4,-1,6}));
    }
}
