package unit16_thread.juc_completableFuture;

import io.netty.util.concurrent.CompleteFuture;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类描述：
 *  https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
 * @author 01409616
 * @date 2024/10/29 11:39
 */
public class CFTest2 {

    ExecutorService executor = Executors.newFixedThreadPool(5);
    
    @Test
    public void test() {
        // 1、使用runAsync或supplyAsync创建异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "retult1";
        }, executor);
        // 2、直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("retult2");
        // 3、先初始化一个未完成的CompletableFuture，然后通过complete完成该CompletableFuture
        // 第三种方式的一个典型使用场景，就是将回调方法转为CompletableFuture，然后再依赖CompletableFure的能力进行调用编排
        CompletableFuture<Object> cf = new CompletableFuture<>();
        cf.complete("success");
        
        // 一元依赖。对于单个CompletableFuture的依赖可以通过thenApply、thenAccept、thenCompose等方法来实现
        CompletableFuture<String> cf3 = cf1.thenApply(result1 -> {
            return result1 + " thenApply";
        });
        CompletableFuture<String> cf5 = cf2.thenApply(result2 -> {
            return result2 + " thenApply";
        });
        
        // 二元依赖，CF4同时依赖于两个CF1和CF2，这种二元依赖可以通过thenCombine等回调来实现
        CompletableFuture<String> cf4 = cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println("cf4 ==> " + result1 + " " + result2);
            return "result4";
        });
        
        // 多元依赖
        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        cf6.thenApply(v -> {
            String result3 = cf3.join();
            String result4 = cf4.join();
            String result5 = cf5.join();
            System.out.println("cf4 ==> " + result3 + " " + result4 + " " + result5);
            return "result6";
        });
    }
    
}
