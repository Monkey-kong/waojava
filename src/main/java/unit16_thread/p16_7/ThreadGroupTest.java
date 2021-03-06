package unit16_thread.p16_7;

class MyThread extends Thread {
    public MyThread(String name){
        super(name);
    }
    public MyThread(ThreadGroup group, String name){
        super(group, name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + " 线程 i 变量" + i);
        }
    }
}

public class ThreadGroupTest{
    public static void main(String[] args) {
        // 获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是后台线程：" + mainGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg 线程组是否是后台线程组：" + tg.isDaemon());
        new MyThread(tg, "tg 组的线程甲").start();
        new MyThread(tg, "tg 组的线程乙").start();
    }
}