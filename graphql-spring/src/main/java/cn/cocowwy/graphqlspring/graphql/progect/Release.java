package cn.cocowwy.graphqlspring.graphql.progect;

/**
 * @author cocowwy.cn
 * @create 2022-05-05-11:45
 */
public class Release {
    private String version;

    private ReleaseStatus status;

    private String referenceDocUrl;

    private String apiDocUrl;

    private boolean current;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ReleaseStatus getStatus() {
        return status;
    }

    public void setStatus(ReleaseStatus status) {
        this.status = status;
    }

    public String getReferenceDocUrl() {
        return referenceDocUrl;
    }

    public void setReferenceDocUrl(String referenceDocUrl) {
        this.referenceDocUrl = referenceDocUrl;
    }

    public String getApiDocUrl() {
        return apiDocUrl;
    }

    public void setApiDocUrl(String apiDocUrl) {
        this.apiDocUrl = apiDocUrl;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
