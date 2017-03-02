package com.sun.type.divide;

/**
 * 子数组对象
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class SubArray {

    public SubArray(int low, int high, int sum) {
        super();
        this.low = low;
        this.high = high;
        this.sum = sum;
    }

    /**
     * 该子数组的开始位置
     */
    private int low;

    /**
     * 该子数组的结束位置
     */
    private int high;

    /**
     * 该子数组的所有元素的和
     */
    private int sum;

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "SubArray [low=" + low + ", high=" + high + ", sum=" + sum + "]";
    }
}
