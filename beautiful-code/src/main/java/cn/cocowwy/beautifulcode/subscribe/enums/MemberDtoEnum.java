package cn.cocowwy.beautifulcode.subscribe.enums;

import cn.cocowwy.beautifulcode.subscribe.dto.MemberBaseDto;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-11:38
 */
public enum MemberDtoEnum {
    BASEDTO(MemberBaseDto.class, "基础数据，姓名和ID编号"),
    ;

    private final Class<? extends MemberBaseDto> clazz;
    /**
     * 返回值数据类型字段描述
     */
    private final String description;

    MemberDtoEnum(Class<? extends MemberBaseDto> clazz, String description) {
        this.clazz = clazz;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
