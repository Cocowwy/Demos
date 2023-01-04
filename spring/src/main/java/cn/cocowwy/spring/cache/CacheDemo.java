package cn.cocowwy.spring.cache;

import cn.cocowwy.common.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Spring Cache demo
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache">Spring缓存</a>
 * CacheManager的缓存结构
 * 内部的
 * cacheMap的数据结构是一个ConcurrentHashMap
 * 其中的key为 cacheNames 而value为 concurrentHashMapCache {@link org.springframework.cache.concurrent.ConcurrentMapCache} 包装了缓存的键值对
 * concurrentHashMapCache 内部的 store（是ConcurrentHashMap） 存储着 k-v 键值对的缓存
 * <a href="https://github.com/ben-manes/caffeine/wiki">caffeine文档</a>
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
public class CacheDemo {
    @Autowired
    private List<CacheManager> cacheManagers;

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
        rt.setName(name);
        System.out.println("findBookDemo1 in");
        return rt;
    }

    /**
     * 方法总是会实际执行，之后会更新 cacheNames 下的相同 key 的缓存的value
     * When the cache needs to be updated without interfering with the method execution, you can use the @CachePut annotation.
     * That is, the method is always invoked and its result is placed into the cache (according to the @CachePut options).
     * It supports the same options as @Cacheable and should be used for cache population rather than method flow optimization.
     * The following example uses the @CachePut annotation:
     * @param name
     * @return
     */
    @CachePut(cacheNames = "lib1", key = "#name")
    public Book updateBookDemo1(String name) {
        Book rt = new Book();
        rt.setName("already update name");
        System.out.println("updateBookDemo1 in");
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
    @CacheEvict(cacheNames = "lib1", allEntries = true, key = "#name")
    public void cleanNodeCacheByBookName(String name) {
        System.out.println("clean " + name + " cache");
    }

    public void watchCacheManager() {
        System.out.println(cacheManagers);
    }

    @Cacheable(cacheManager = "hunan", cacheNames = "csc")
    public Book hunanCacheManager(String name) {
        return new Book();
    }

    @Cacheable(cacheManager = "beijing", cacheNames = "lib4")
    public Book beijingCacheManager(String name) {
        return new Book();
    }

    @CacheEvict(cacheManager = "hunan", cacheNames = "csc", allEntries = true)
    public void cleanHunanCache(String name) {
    }

    @Cacheable(cacheManager = "wuhan",cacheNames = "wh")
    public Book wuhanCacheManager(String name) {
        return new Book();
    }
}

@Component
class CacheTest implements CommandLineRunner {
    @Autowired
    private CacheDemo cacheDemo;

    @Override
    public void run(String... args) throws Exception {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 测试是否必须指定 cacheNames
     */
    private void test4() {
        cacheDemo.wuhanCacheManager("test");
    }

    /**
     * 测试使用指定不同的缓存管理器，cacheManager指定name即可
     */
    private void test3() {
        cacheDemo.hunanCacheManager("hunan");
        cacheDemo.beijingCacheManager("beijing");
        cacheDemo.watchCacheManager();
        cacheDemo.cleanHunanCache("csc");
        cacheDemo.watchCacheManager();
    }

    /**
     * 测试 cachePut
     */
    private void test2() {
        cacheDemo.findBookDemo1("test");
        cacheDemo.updateBookDemo1("test");
        cacheDemo.findBookDemo1("test");
    }

    /**
     * 测试cacheable和cacheEvict以及allEntries
     */
    private void test1() {
        cacheDemo.findBookDemo1("t1");
        cacheDemo.findBookDemo1("t2");
        cacheDemo.findBookDemo1("t3");
        cacheDemo.watchCacheManager();
//        cacheDemo.findBookDemo2("t2");
        cacheDemo.cleanNodeCacheByBookName("t2");
        cacheDemo.watchCacheManager();
    }
}

@Configuration
class switch2Caffeine {
    /**
     * 改用 CaffeineCacheManager
     * @return CacheManager
     */
    @Bean("hunan")
    @Primary
    public CacheManager hunanCacheManager() {
        // cacheNames的参数 指Cacheable注解所指定的CacheNames的允许值（即值是固定的，不能指定其他的值） dynamic = false
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("csc");
        return caffeineCacheManager;
    }

    /**
     * 改用 CaffeineCacheManager
     * @return CacheManager
     */
    @Bean("beijing")
    public CacheManager beiJingCacheManager() {
        return new CaffeineCacheManager();
    }

    /**
     * 改用 CaffeineCacheManager
     * @return CacheManager
     */
    @Bean("wuhan")
    public CacheManager wuhanCacheManager() {
        return new CaffeineCacheManager();
    }
}