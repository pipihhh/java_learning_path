import dataType.Array;
import static java.lang.System.*;


public class Package {
    public static void main(String[] args) {
        out.println("ABC");
    }
}


// java中如果A写了一个Person类,B写了一个Person类,那么如何解决类名冲突的问题呢
// java中使用package解决名字冲突
// 每一个类的全名其实都应该跟一个包的名字
// 包名用package关键字在行首指定,包可以是多层结构,用.隔开
// java文件对应的目录层次要和包的层次一致
// ide中会根据包结构自动编译,自己编译的指令为 javac -d ../bin ming/Person.java mr/jun/Arrays.java  第一个参数为指明编译的源码文件的位置
// 同一个包下面不同的类之间可以直接使用而无需import
// import 可以import dataType此时在使用的时候就可以使用dataType.Array了
// import static可以导入类的静态字段和静态方法,用的极少import static java.lang.System.*
// java编译器编译出来的class文件只使用完整的类名,则如果使用了简单类名,编译器的查找顺序为
// 当前package中,import中查找,java.lang中查找
class Person {
    
}
