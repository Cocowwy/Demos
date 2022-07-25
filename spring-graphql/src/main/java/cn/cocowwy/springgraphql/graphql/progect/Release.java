package cn.cocowwy.springgraphql.graphql.progect;

import lombok.Data;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
@Data
public class Release {
    private String version;

    private ReleaseStatus status;

    private String referenceDocUrl;

    private String apiDocUrl;

    private boolean current;

}
