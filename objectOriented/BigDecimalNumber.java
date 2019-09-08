import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalNumber {
    public static void main(String[] args) {
        // BigDecimal可以表示任意一个大小且精度完全准确的浮点数
        BigDecimal bd = new BigDecimal("1234567897657567.34543000");
        System.out.println(bd.scale());  // 表示小数位数   5
        bd = bd.stripTrailingZeros();    // 获得一个去掉了末尾0的decimal
        BigDecimal bd2 = new BigDecimal("123000");
        System.out.println(bd2.scale());   // -3 因为整数且末尾3个0
        bd.setScale(4, RoundingMode.HALF_UP);   // 将bd的精度改为4,并且截断方式为四舍五入
        bd.setScale(3, RoundingMode.DOWN);   // 直接截断
        // BigDeciaml在做加减乘除时精度不会丢失,但是在做除法时存在无法除近的情况
        System.out.println(bd.divide(bd2, 10, RoundingMode.HALF_UP));  // 精确到10位并且四舍五入
        // 在使用equals比较两个decimal是否相等时,不仅在数上面要相等,scale也要相等
        // 必须使用compareTo来比较,根据两个值的大小返回负数,正数,0,表示小于,大于,等于
        // 在BigDecimal中,使用一个BigInteger表示整数部分,使用scale表示小数部分的位数
        // BigDecimal也是继承自Number的不可变对象
    }
}