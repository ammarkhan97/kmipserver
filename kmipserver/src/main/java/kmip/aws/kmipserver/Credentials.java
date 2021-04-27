package kmip.aws.kmipserver;

public class Credentials {

    private String accessKeyId = "AKIAX4TDVXIOAZ5J46AY";
    private String secretAccessKey = "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";
    
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public String getSecretAccessKey() {
        return secretAccessKey;
    }
    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    
}
