package cn.cocowwy.spring.event.evt;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;
import java.time.Clock;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class UserRegisterEvent extends ApplicationEvent {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 邮箱号
     */
    private String email;
    /**
     * 钱包
     */
    private BigDecimal wallet;

    public UserRegisterEvent(Object source, String userName, String email, BigDecimal wallet) {
        super(source);
        this.userName = userName;
        this.email = email;
        this.wallet = wallet;
    }

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }
}
