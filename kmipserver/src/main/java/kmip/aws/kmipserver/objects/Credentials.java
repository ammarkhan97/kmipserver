package kmip.aws.kmipserver.objects;

public class Credentials {

    private String accessKeyId = "";
    private String secretAccessKey = "";
    
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
