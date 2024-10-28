package unit16_thread.juc_completableFuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 学习文章：
 * 1、https://liaoxuefeng.com/books/java/threading/completable-future/index.html#0
 * 2、https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
 */
public class CFTest {
    
    /**
     * cf1\cf2 并行执行，等到cf1、cf2全部执行完成返回
     */
    @Test
    public void cfTest1() {
        CompletableFuture<Void> cf = newCompletableFuture(1);
        CompletableFuture<Void> cf2 = newCompletableFuture(2);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(cf, cf2);
        voidCompletableFuture.join();
        System.out.println("the end.");
    }

    /**
     * cf1\cf2 并行执行
     * cf1-cf3 串行执行
     * 等到cf1、cf2、cf3全部执行完成返回
     */
    @Test
    public void cfTest2() {
        CompletableFuture<Void> cf = newCompletableFuture(1);
        CompletableFuture<Void> cf2 = newCompletableFuture(2);
        CompletableFuture<Void> cf3 = cf.thenComposeAsync(v -> newCompletableFuture(3));
        CompletableFuture<Void> result = CompletableFuture.allOf(cf2, cf3);
        System.out.println("编排完成");
        result.join();
        System.out.println("the end.");
    }
    
    @Test
    public void cfTest3() {
        CompletableFuture<Void> cf = newCompletableFuture(1);
        CompletableFuture<Void> cf2 = newCompletableFuture(2);

        CompletableFuture<Void> cf3 = cf.thenComposeAsync(v -> newCompletableFuture(3));

        CompletableFuture<Void> cf12 = CompletableFuture.allOf(cf, cf2);
        CompletableFuture<Void> cf4 = cf12.thenComposeAsync(v -> newCompletableFuture(4));
        
        
        CompletableFuture<Void> cf5 = cf2.thenComposeAsync(v -> newCompletableFuture(5));

        CompletableFuture<Void> cf345 = CompletableFuture.allOf(cf3, cf4, cf5);
        cf345.join();
        
        System.out.println("the end.");
    }
    
    // thenCombine ??
    
    /**
     * 创建CompletableFuture：supplyAsync
     */
    static CompletableFuture<Void> newCompletableFuture(int i) {
        return CompletableFuture
                // 创建异步执行任务
                .supplyAsync(() -> fetchPrice("cf " + i + "..."))
                // 如果执行成功
                .thenAccept((result) -> {
                    System.out.println("price" + i + ": " + result);
                })
                // 如果执行异常
                .exceptionally((e) -> {
                    System.out.println("exceptionally" + i + "...");
                    return null;
                });
    }
    
    
    static Double fetchPrice(String msg) {
        System.out.println(msg + "begin");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            
        }
        System.out.println(msg + "end");
        if (Math.random() < 0.5) {
            throw new RuntimeException("fetch price failed");
        }
        return 5 + Math.random() * 20;
    }
}
