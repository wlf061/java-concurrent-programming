package ch5.s2;

/**
 * 类的描述
 *
 * @author nancy.wang
 * @Time 2019/1/16
 */
public class TestString {
    public static void main(String[] str){
        String c1 = "abc";
        String c2= "abc";
        String c3 = new String("abc");
        System.out.println(c1==c2);
        System.out.println(c1==c3);
        c1="abs";
        System.out.println(c1==c2);
        String c4= c3.replace("a","fff");  //内部重新申请了Stringbuffer。
        System.out.println(c3==c4);
    }
}
