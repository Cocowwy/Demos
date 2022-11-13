package cn.cocowwy.spring.cache;

import cn.cocowwy.common.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Spring Cache demo
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache">Spring缓存</a>
 * CacheManager的缓存结构
 * 内部的
 * cacheMap的数据结构是一个ConcurrentHashMap
 * 其中的key为 cacheNames 而value为 concurrentHashMapCache {@link org.springframework.cache.concurrent.ConcurrentMapCache} 包装了缓存的键值对
 * concurrentHashMapCache 内部的 store（是ConcurrentHashMap） 存储着 k-v 键值对的缓存
 * 结构：
 *
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class CacheDemo {
    @Autowired
    private CacheManager cacheManager;

    /**
     * In addition to the condition parameter, you can use the unless parameter to veto the adding of a value to the cache.
     * Unlike condition, unless expressions are evaluated after the method has been invoked.
     * To expand on the previous example, perhaps we only want to cache paperback books, as the following example does:
     * @param name
     * @return
     */
//    @Cacheable(cacheNames = "lib1", condition = "#name.length() < 32", unless = "#result.valid")
    @Cacheable(cacheNames = "lib1", key = "#name")
    public Book findBookDemo1(String name) {
        Book rt = new Book();
        System.out.println("findBookDemo1 in");
        return rt;
    }

    /**
     * The cache abstraction supports java.util.Optional return types. If an Optional value is present,
     * it will be stored in the associated cache. If an Optional value is not present, null will be stored in the associated cache.
     * #result always refers to the business entity and never a supported wrapper, so the previous example can be rewritten as follows:
     * @param name
     * @return
     */
    @Cacheable(cacheNames = "lib2", condition = "#name.length() < 32", unless = "#result?.valid")
    public Optional<Book> findBookDemo2(String name) {
        Book rt = new Book();
        System.out.println("findBookDemo2 in");
        return Optional.ofNullable(rt);
    }

    /**
     * allEntries = true ,clean all k-v where cacheNames = "xxx" (all key)
     * allEntries = false , just clean the same cacheNames and key
     * @param name
     */
    @CacheEvict(cacheNames = "lib1", allEntries = false, key = "#name")
    public void cleanNodeCacheByBookName(String name) {
        System.out.println("clean " + name + " cache");
    }

    public void watchCacheManager() {
        System.out.println(cacheManager);
    }
}

@Component
class CacheTest implements CommandLineRunner {
    @Autowired
    private CacheDemo cacheDemo;

    @Override
    public void run(String... args) throws Exception {
        cacheDemo.findBookDemo1("t1");
        cacheDemo.findBookDemo1("t2");
        cacheDemo.findBookDemo1("t3");
        cacheDemo.watchCacheManager();
//        cacheDemo.findBookDemo2("t2");
        cacheDemo.cleanNodeCacheByBookName("t2");
        cacheDemo.watchCacheManager();
    }
}
