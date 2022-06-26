import java.lang.instrument.Instrumentation;

/**
 * JVM 会优先加载带 Instrumentation 签名的方法，加载成功忽略第二种；如果第一种没有，则加载第二种方法
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class AgentDemo {
    /**
     * agentArgs 是 premain 函数得到的程序参数，随同 -javaagent一起传入
     *
     * Inst 是一个 java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入，
     * 接口中集中了几乎所有的功能方法，例如类定义的转换和操作等等
     *
     * 是在 main 方法启动前拦截大部分类的加载活动
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("do premain");
    }

    /**
     * 优先级比上面的方法低，即存在上面的方法的情况下不执行下面的方法
     * @param agentOps
     */
    public static void premain(String agentOps) {
    }
}
