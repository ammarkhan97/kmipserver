package kmip.aws.kmipserver.objects;

public class Credentials {

    private String accessKeyId = "AKIAX4TDVXIOAEKOWPAV";
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
