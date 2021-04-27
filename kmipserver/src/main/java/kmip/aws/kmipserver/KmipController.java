package kmip.aws.kmipserver;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.CreateKeyRequest;
import com.amazonaws.services.kms.model.CreateKeyResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import kmip.aws.kmipserver.KmsClientBuilder;

@RestController
public class KmipController {

    //Used for creating symmetric keys, return the identifier
    @PostMapping("/create")
    public String createSymmetricKey() {

        AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();
        
        String creatingMasterKey = "Creating Master Key";
        CreateKeyRequest request = new CreateKeyRequest().withDescription(creatingMasterKey);
        CreateKeyResult result = kmsClient.createKey(request);

        return "";
    }

    //Used for creating asymmetric keys, return the identifier
    @PostMapping("/createKeyPair")
    public String createKeyPair() {
    
        AWSKMS kmsClient = AWSKMSClientBuilder.standard().build();
    
        return "";
    }

    //Used for retrieving an object's value given the identifier
    @GetMapping("/get")
    public String get() {
        return "";
    }

    //Used for registering externally generated key values on our server
    @PostMapping("/register")
    public String register(){
        return "";
    }

    //Used for encrypting given data with given key
    @GetMapping("/encrypt")
    public String encrypt() {
        return "";
    }

    //Used for decrypting gien data with given key
    @GetMapping("/decrypt")
    public String decrypt() {
        return "";
    }

}
