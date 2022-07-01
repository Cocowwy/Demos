package com.example.explainmybatis.mybatis;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public enum MySQLExplainTypeEnum {
    SYSTEM(1, "system"),
    CONST(2, "const"),
    EQ_REF(3, "eq_ref"),
    REF(4, "ref"),
    FULLTEXT(5, "fulltext"),
    REF_OR_NULL(6, "ref_or_null"),
    INDEX_MERGE(7, "index_merge"),
    UNIQUE_SUBQUERY(8, "unique_subquery"),
    INDEX_SUBQUERY(9, "index_subquery"),
    RANGE(10, "range"),
    INDEX(11, "index"),
    ALL(12, "all"),
    ;

    private final Integer index;
    private final String name;

    MySQLExplainTypeEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}