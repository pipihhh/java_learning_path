import javax.print.attribute.standard.MediaSize.Other;

public class ProcessControl {
    public static void main(String[] args) {
        // 流程控制相关的所有
        if (1) {
            // 根据if中表达式的结果决定是否执行此语句块内的语句
        } else if (1) {
            // 如果if不执行才执行此处
        } else {
            // 如果if不执行else if也不执行才执行此处
        }

        // 在java中,如果想判断引用类型是否相等,需要用equals()
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println(" != ");
        }

        // equals
        if (s1.equals(s2)) {
            System.out.println(" == ");
        }
        // 如果s1为null,则会报NullPointerException的错误,为了避免此错误,用短路运算符

        if (s1 != null && s1.equals(s2)) {
            // 此时即可避免错误
        }
        // 把不可能为null的放前面
        if ("hello".equals(s1)) {
            // 此时即可,使用equals一定要注意null的错误
        }

        // switch,根据switch(表达式),根据结果选择case,从该case开始执行,如果遇到break就跳出
        // 如果没有匹配到的数字,则执行default,对于多个==使用switch更加的清晰,任何switch都可以用if else来改写
        // switch也可以匹配字符串
        // switch如果漏写了default会出现严重的逻辑错误

        int option = 1;
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }

        // 在jdk12的新语法中,switch可以写为一下的形式,不需要使用break
        
        // switch还可以用来赋值,由于此为jdk12的预览特性,在编译时要加上 javac --source 12 --enable-preview xxx.java
        /*
        String fruit = "apple";
        int opt = switch (fruit) {
            case "apple", "pear" -> 1;
            default -> 0;
        }
        */
    }
}