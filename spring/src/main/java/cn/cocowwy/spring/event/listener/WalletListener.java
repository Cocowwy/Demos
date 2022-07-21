package cn.cocowwy.spring.event.listener;

import cn.cocowwy.spring.event.UserRegisterEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Service
@Order(1)
public class WalletListener implements ApplicationListener<UserRegisterEvent> {
    private static final ObjectMapper om = new ObjectMapper();

    private static Map<String, BigDecimal> usernameMapWallet = new HashMap<>();

    @SneakyThrows
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        if (usernameMapWallet.containsKey(event.getUserName())) {
            usernameMapWallet.put(event.getUserName(), usernameMapWallet.get(event.getUserName()).add(event.getWallet()));
        } else {
            usernameMapWallet.put(event.getUserName(), event.getWallet());
        }

        System.out.println("注册账户成功，账户信息：" + om.writeValueAsString(usernameMapWallet));
    }
}
