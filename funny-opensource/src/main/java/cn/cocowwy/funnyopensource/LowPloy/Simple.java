package cn.cocowwy.funnyopensource.LowPloy;


import java.io.*;
import java.util.Properties;

/**
 * LowPoly 图片生成
 * <a href="https://github.com/zzhoujay/LowPoly">low poly 图片生成</a>
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Simple {

    public static void main(String[] args) throws IOException {
        // 图片输入地址
        String in = "/Users/cocowwy/Desktop/dog1.jpeg";
        // 图片输出地址
        String out = "/Users/cocowwy/Desktop/dog2.jpeg";

        File inFile = new File(in);
        if (inFile.exists()) {
            File outFile = new File(out);
            FileInputStream inputStream = new FileInputStream(inFile);
            FileOutputStream outputStream = new FileOutputStream(outFile);
            long lastTime = System.currentTimeMillis();
            Configuration configuration;
            configuration = new Configuration();
            LowPoly.generate(inputStream, outputStream, configuration);
            System.out.println("目标已保存至:" + outFile.getAbsolutePath());
            System.out.println("用时:" + (System.currentTimeMillis() - lastTime));
        } else {
            System.out.println("源文件不存在");
        }
    }

    private static void createConfiguration(OutputStream outputStream) throws IOException {
        Properties properties = new Properties();
        properties.setProperty(Configuration.ACCURACY, String.valueOf(Configuration.DEFAULT_ACCURACY));
        properties.setProperty(Configuration.SCALE, String.valueOf(Configuration.DEFAULT_SCALE));
        properties.setProperty(Configuration.FILL, String.valueOf(Configuration.DEFAULT_FILL));
        properties.setProperty(Configuration.FORMAT, Configuration.DEFAULT_FORMAT);
        properties.setProperty(Configuration.ANTI_ALIASING, String.valueOf(Configuration.DEFAULT_ANTI_ALIASING));
        properties.setProperty(Configuration.POINT_COUNT, String.valueOf(Configuration.DEFAULT_POINT_COUNT));
        properties.store(outputStream, "default");
    }

    private static Configuration loadConfiguration(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        String accuracy = properties.getProperty(Configuration.ACCURACY, String.valueOf(Configuration.DEFAULT_ACCURACY));
        String scale = properties.getProperty(Configuration.SCALE, String.valueOf(Configuration.DEFAULT_SCALE));
        String fill = properties.getProperty(Configuration.FILL, String.valueOf(Configuration.DEFAULT_FILL));
        String format = properties.getProperty(Configuration.FORMAT, Configuration.DEFAULT_FORMAT);
        String antiAliasing = properties.getProperty(Configuration.ANTI_ALIASING, String.valueOf(Configuration.DEFAULT_ANTI_ALIASING));
        String pointCount = properties.getProperty(Configuration.POINT_COUNT, String.valueOf(Configuration.DEFAULT_POINT_COUNT));

        return new Configuration(Integer.valueOf(accuracy), Float.valueOf(scale), Boolean.valueOf(fill), format, Boolean.valueOf(antiAliasing), Integer.valueOf(pointCount));
    }
}