package unit20_stream.c1_lambda;

import java.util.function.BiFunction;

public class BIFunctionTest {

    public static void main(String[] args) {
        BIFunctionTest test = new BIFunctionTest();
        B resp = test.getFromSpringFactories(A::create);
        System.out.println(resp);

        BiFunction<Integer, Integer, Integer> add = Integer::sum;
        System.out.println(add.apply(2, 3));
    }

    private <T> T getFromSpringFactories(BiFunction<A, Integer, T> action) {
        A bt = new A2();
        // action是方法引用
        // 编译器会将第一个参数当做调用方法的目标对象
        return action.apply(bt, 2);
    }
    
    public interface A {
        B create(Integer i);
    }
    
    public class A1 implements A {
        @Override
        public B create(Integer i) {
            System.out.println("A1 " + i);
            return new B1();
        }
    }
    
    public class A2 implements A {
        @Override
        public B create(Integer i) {
            System.out.println("A2 " + i);
            return new B2();
        }
    }

    public interface B {
    }
    
    public class B1 implements B {
    }
    
    public class B2 implements B {
    }
}

