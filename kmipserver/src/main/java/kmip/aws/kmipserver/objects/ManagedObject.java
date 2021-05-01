package kmip.aws.kmipserver.objects;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ManagedObject {

    private String id;
    private String awsKeyArn;
    private String awsKeyId;
    private String keyType;
    private String region;

    public ManagedObject (){
        super();
    }

    public ManagedObject (String awsKeyArn, String awsKeyId, String keyType, String region){
        this.setAwsKeyArn(awsKeyArn);
        this.setAwsKeyId(awsKeyId);
        this.setKeyType(keyType);
        this.setRegion(region);
        setId(UUID.randomUUID().toString());
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getAwsKeyId() {
        return awsKeyId;
    }

    public void setAwsKeyId(String awsKeyId) {
        this.awsKeyId = awsKeyId;
    }

    public String getAwsKeyArn() {
        return awsKeyArn;
    }

    public void setAwsKeyArn(String awsKeyArn) {
        this.awsKeyArn = awsKeyArn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("{\n");
        stringBuilder.append("\tid: " + id);
        stringBuilder.append("\n\tawsKeyArn: " + awsKeyArn);
        stringBuilder.append("\n\tawsKeyId: " + awsKeyId);
        stringBuilder.append("\n\tkeyType: " + keyType);
        stringBuilder.append("\n\tRegion: " + region);
        stringBuilder.append("\n}");

        return stringBuilder.toString();
    }
    
}
