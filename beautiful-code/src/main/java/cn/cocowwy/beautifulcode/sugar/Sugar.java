package cn.cocowwy.beautifulcode.sugar;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Sugar {
    public static void main(String[] args) {
        // 分隔符，便于清楚的数位数
        System.out.println(1_000_000);

        // 2的三次方
        System.out.println(1 << 3);

        // break 到 标志的地方；
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    break retry;
                }
                System.out.println(j);
            }
        }
    }

}
