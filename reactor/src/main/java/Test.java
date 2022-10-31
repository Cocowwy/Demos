import reactor.core.publisher.Flux;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Test {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Flux<String> flux = Flux.just("A", "B", "C");
        flux.subscribe((item) -> {
            if (item.equals("D")) {
                throw new RuntimeException("值为B的时候抛出异常");
            }
            System.out.println("读取到的值为" + item);
        }, (errorItem) -> {
            System.out.println("值为B的时候的回调操作");
        }, () -> {
            System.out.println("元素正常消费完成");
        });

        System.out.println("----》");
    }
}
