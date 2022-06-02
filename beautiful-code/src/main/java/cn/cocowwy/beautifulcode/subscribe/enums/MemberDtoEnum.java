package cn.cocowwy.beautifulcode.subscribe.enums;

import cn.cocowwy.beautifulcode.subscribe.domain.dto.MemberBaseDto;
import cn.cocowwy.beautifulcode.subscribe.domain.dto.MemberV1Dto;
import cn.cocowwy.beautifulcode.subscribe.domain.dto.MemberV2Dto;
import cn.cocowwy.beautifulcode.subscribe.domain.dto.MemberV3Dto;

/**
 * @author cocowwy.cn
 * @create 2022-06-06-11:38
 */
public enum MemberDtoEnum {
    BASEDTO(MemberBaseDto.class, "memberNo"),
    MEMBER_V1_DTO(MemberV1Dto.class, "memberNo,name"),
    MEMBER_V2_DTO(MemberV2Dto.class, "memberNo,name,birthday,address"),
    MEMBER_V3_DTO(MemberV3Dto.class, "memberNo,name,birthday,address,money"),
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

    public Class<? extends MemberBaseDto> getClazz() {
        return clazz;
    }
}
