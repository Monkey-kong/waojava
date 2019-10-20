package unit06_oo2.c64_final;

public class StringJoinTest
{
    public static void main(String[] args)
    {
        String s1 = "疯狂Java";
        // 编译时就可以确认，所以 s2 直接引用常量池中已有的"疯狂Java"字符串
        String s2 = "疯狂" + "Java";
        System.out.println(s1 == s2); // true
        String str1 = "疯狂";
        String str2 = "Java";
        // str1、str2 只是两个普通变量，编译器不会进行“宏替换”，因此编译器无法在编译时确定 s3 的值
        String s3 = str1 + str2;
        System.out.println(s1 == s3); // false
        // 所以要使得 s1 == s3 只需，str1、str2 用 final 修饰即可
    }
}
