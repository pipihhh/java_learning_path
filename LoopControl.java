public class LoopControl {
    public static void main(String[] args) {
        // 循环控制相关
        int n = 100;
        while (n <= 100 && n >= 0) {
            n--;
        }
        // while循环是先判断条件再循环,有可能什么事情都不做
        // 与之对应的do-while循环
        // do-while会先执行再判断条件

        do {
            n--;
        } while (n <= 100);

        // for 循环
        // 在执行for循环前:执行for括号内的第一句话,并判断第二句话的条件成不成立,如果成立执行代码块内部的语句
        // 在执行完一遍语句后,执行for括号内的第三句话
        // for循环总是会执行括号呢的第一句话,for循环有可能执行0次,如果在for循环内修改i的值,不会报错但会产生逻辑错误
        // for循环执行完毕后退出循环,i无法再使用,
        for (int i=1; i<=100; i++) {
            System.out.println(i);
        }

        // for each循环,可以遍历所有可迭代的数据类型,但是无法得到其索引
        int[] ns = { 1, 2, 3, 4, 5, 6 };
        for (int i : ns) {
            System.out.println(i);
        }
    }
}