/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 *
 * https://leetcode-cn.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (57.39%)
 * Likes:    938
 * Dislikes: 0
 * Total Accepted:    108.1K
 * Total Submissions: 181.3K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
 * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 
 * 
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 
 */

// @lc code=start
class Solution {

    //暴力枚举
    public int maxArea1(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length - 1; i++){
            for(int j = i+1; j < height.length; j++){
                maxArea = Math.max(maxArea, (j-i)*Math.min(height[i], height[j]));
            }
        }
        return maxArea;
    }

    //双指针
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while(i < j){
            maxArea = height[i] < height[j] 
            ? Math.max(maxArea, (j-i)*height[i++]) : Math.max(maxArea, (j-i)*height[j--]);
        }
        return maxArea;
    }
}
// @lc code=end

