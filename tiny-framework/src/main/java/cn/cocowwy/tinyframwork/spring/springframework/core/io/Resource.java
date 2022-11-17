package cn.cocowwy.tinyframwork.spring.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
