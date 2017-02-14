package com.sun.function;

/**
 * @标准记号与常用函数
 * <b>单调性</b><br>
 * m<=n f(m)<=g(n) 单调递增，同理>=为单调递减<br>
 * m<n f(m)<g(n) 严格递增，同理>为严格递减<br>
 * <b>向下取整、向上取整</b><br>
 * floor ceil<br>
 * x-1<x向下取整<=x<=x向上取整<x+1<br>
 * <b>模运算</b><br>
 * a mode n=a-n*(a/n向下取整)<br>
 * 即求a/n的余数<br>
 * <b>多项式</b><br>
 * n的d次多项式：i=0,d ∑an^i (i为0开始 d结束  )<br>
 * 当且仅当ad>0多项式p(n)=Θ(n^d)<br>
 * <b>指数</b><br>
 * 任意<b>底数大于1的指数函数比任意多项式函数增长的快</b>。<br>
 * <b>对数</b><br>
 * lgn 计算机中非常多的算法和数据结构设计把一个问题分解为俩个（应该就是树），所以常用lg<br>
 * <b>阶乘</b><br>
 * n! = n*(n-1)*...*3*2*1<br>
 * 阶乘的一个弱上界： n!<=n^n<br>
 * 斯特林近似公式为阶乘的一个更紧确的上界和下界<br>
 * <b>多重函数</b><br>
 * f(n)重复i次在一个初始值上，改函数为多重函数<br>
 * f(n)={n          i=0<br>
 *      {f(f(n))    i>0<br>
 * <b>多重对数函数</b><br>
 * lg*n 多重对数函数<br>
 * lg*n=min{i>=0:lg*n<=1}<br>
 * 多重对数函数是一个<b>增长非常慢</b>的函数<br>
 * <b>斐波那契数列与黄金分割率</b><br>
 * 0 1 1 2 3 5 8 13 21 34 55...<br>
 * 斐波那契数列的序号越大，相邻两项的比值越接近黄金分割率(1+「5)/2<br>
 * x^2=x+1 => 解为黄金分割率与其共轭<br>
 * @author sunx(sunxin@strongit.com.cn)<br/>
 * @version V1.0.0<br/>
 */

public class CommonFunction {
}
