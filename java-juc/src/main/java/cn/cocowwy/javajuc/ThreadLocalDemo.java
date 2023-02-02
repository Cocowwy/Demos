package cn.cocowwy.javajuc;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/2/2
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("A");

        System.out.println("主线程：" + threadLocal.get());

        System.gc();

        // 虽然Entry是弱引用，但是没有回收，因为被强引用引用了
        System.out.println("主线程：" + threadLocal.get());

        new Thread(() -> {

            System.out.println("新线程get " + threadLocal.get());

            threadLocal.set("B");

            System.out.println("新线程get " + threadLocal.get());

        }).start();

    }
}
