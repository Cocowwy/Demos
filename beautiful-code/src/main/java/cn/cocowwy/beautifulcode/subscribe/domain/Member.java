package cn.cocowwy.beautifulcode.subscribe.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-11:29
 */
public class Member {
    private String name;
    private String mobile;
    private LocalDate birthday;
    private String address;
    private BigDecimal money;
    private String memberNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
