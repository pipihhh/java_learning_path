import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        // 数组
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 68;
        ns[2] = 68;
        ns[3] = 68;
        ns[4] = 68;
        System.out.println(ns.length); // 获取数组的大小

        // 数组是引用类型,在使用索引访问数组元素时,如果索引超出范围运行时将报错
        // 数组的另一种初始化方法
        int[] ns2 = { 68, 79, 85, 62 };
        // int[] ns2 = new int[] { 68, 79, 85, 62 };
        // 数组是引用类型,数组的大小不可变
        System.out.println(ns2.length);

        // 如果是字符串数组,数组内保存的实际上是字符串的引用
        String[] as = { "abc", "def" };

        // 每次打印数组都需要循环输出,太麻烦了,所以有一个方法帮助我们
        System.out.println(Arrays.toString(as));
        // 数组内置的排序功能
        Arrays.sort(as);

        // 二维数组的打印
        int[][] dp = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(Arrays.deepToString(dp));
    }
}