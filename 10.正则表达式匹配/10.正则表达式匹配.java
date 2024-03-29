/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (24.64%)
 * Likes:    783
 * Dislikes: 0
 * Total Accepted:    40.6K
 * Total Submissions: 160.4K
 * Testcase Example:  '"aa"\n"a"'
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 
 * 
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 
 * 说明:
 * 
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 
 * 
 * 示例 3:
 * 
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 
 * 
 * 示例 4:
 * 
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 
 * 示例 5:
 * 
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * 
 */

// @lc code=start
class Solution {

    //递归解法
    public boolean isMatch1(String s, String p) {
        //p中不含'.'和'*'的情况
        if(s.equals(p)){
            return true;
        }

        boolean isFirstMatch = false;
        //判断首字符是否相同
        if(!s.isEmpty() && !p.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){
            isFirstMatch = true;
        }

        //p第二个字符为*的情况
        if(p.length() >= 2 && p.charAt(1) == '*'){
            //首字符不想同，*取0个，p去掉前两个字符进行比较；首字符相同，*取一个或多个，s去掉第一个字符进行比较
            return isMatch1(s, p.substring(2)) || (isFirstMatch && isMatch1(s.substring(1), p));
        }

        //p第二个字符不为*的情况，则需首字符相同，s和p都去掉首字符后的字符比较
        return isFirstMatch && isMatch1(s.substring(1), p.substring(1));
    }

    //动态规划
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        
        f[0][0] = true;
        for(int i = 2; i <= n; i++){
            f[0][i] = f[0][i - 2] && p.charAt(i - 1) == '*';
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){
                    f[i][j] = f[i - 1][j - 1];
                }
                if(p.charAt(j - 1) == '*'){
                    f[i][j] = f[i][j - 2] || 
                    f[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
                }
            }
        }
        
        return f[m][n];
    }
}
// @lc code=end

