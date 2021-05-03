package kmip.aws.kmipserver.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.CreateKeyRequest;
import com.amazonaws.services.kms.model.CreateKeyResult;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kmip.aws.kmipserver.KmsClientBuilder;
import kmip.aws.kmipserver.objects.Attributes;
import kmip.aws.kmipserver.objects.ManagedObject;
import kmip.aws.kmipserver.objects.State;
import kmip.aws.kmipserver.services.EncryptionService;
import kmip.aws.kmipserver.services.FirebaseService;

@RestController
public class KmipController {
   
    @Autowired
    FirebaseService firebaseService;

    private ArrayList<String> supportedAsymmetricTypes = new ArrayList<>(Arrays.asList("RSA_2048", "RSA_3072", "RSA_4096", "ECC_NIST_P256", "ECC_NIST_P384", "ECC_NIST_P521", "ECC_SECG_P256K1"));
    private AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();
    private EncryptionService encryptionService = new EncryptionService();

    //Used to check if a user can use an object
    @GetMapping("/check")
    public String check(String uid)
    {
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } 
        return returnVal;
    }

    //Used for creating asymmetric keys, return the identifier
    @PostMapping("/createKeyPair")
    public String createKeyPair(@RequestHeader String keySpec, 
                                @RequestHeader String keyUsage,
                                @RequestBody Attributes attributes) throws InterruptedException, ExecutionException {

        if(!supportedAsymmetricTypes.contains(keySpec)){
            return keySpec + " is not supported";
        }
    
        String creatingMasterKey = "Creating Asymmetric Key";
        CreateKeyRequest request = new CreateKeyRequest()
                                        .withDescription(creatingMasterKey)
                                        .withKeyUsage(keyUsage)
                                        .withCustomerMasterKeySpec(keySpec);
        CreateKeyResult result = kmsClient.createKey(request);
    
        return firebaseService.saveManagedObject(new ManagedObject(
            result.getKeyMetadata().getArn(),
            result.getKeyMetadata().getKeyId(),
            "Asymmetric",
            "US-EAST-2",
            attributes));

    }  

    //Used for creating symmetric keys, return the identifier
    @PostMapping("/create")
    public String createSymmetricKey(@RequestBody Attributes attributes) throws InterruptedException, ExecutionException {
        
        String creatingMasterKey = "Creating Symmetric Key";
        CreateKeyRequest request = new CreateKeyRequest().withDescription(creatingMasterKey);
        CreateKeyResult result = kmsClient.createKey(request);

        return firebaseService.saveManagedObject(new ManagedObject(
            result.getKeyMetadata().getArn(),
            result.getKeyMetadata().getKeyId(),
            "Symmetric",
            "US-EAST-2",
            attributes));
    }

    //Used for encrypting given data with given key
    @GetMapping("/encrypt")
    public String encrypt(@RequestHeader String uid, 
                          @RequestHeader String plaintext) throws InterruptedException, ExecutionException {
        
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject.getKeyType().equals("Symmetric")){
            if(managedObject.getAttributes().getState() == State.ACTIVE ){
                return encryptionService.symmetricEncrypt(managedObject, plaintext);
            }

            return "Key is in a state " + managedObject.getAttributes().getState() + " and thus may not be used for cryptographic purposes";
        }

        return encryptionService.asymmetricEncrypt(managedObject, plaintext);
    }

    //Used for decrypting given data with given key
    @GetMapping("/decrypt")
    public String decrypt(@RequestHeader String uid,
                          @RequestHeader String cipherText) throws InterruptedException, ExecutionException {

        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject.getKeyType().equals("Symmetric")){
            return encryptionService.symmetricDecrypt(managedObject, cipherText);
        }
        
        return encryptionService.asymmetricDecrypt(managedObject, cipherText);
    }

    //Used to discover the KMIP version implemented
    @GetMapping("/discover")
    public String discoverVersion()
    {
        return "Version: \n";
    }

    //Used for retrieving an object's value given the identifier
    @GetMapping("/get")
    public String get(@RequestHeader String uid) throws InterruptedException, ExecutionException, JsonProcessingException {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            return managedObject.toString();
        }
        return "No object with uid: " + uid + " found";
    }

    //Used to locate objects with a given attribute
    // @GetMapping("/locate")
    // public String locate(String attribute)
    // {
    //     String returnVal = "uid(s): ";
    //     for()
    //     {
    //         if()
    //         {
    //             if(returnVal.length() == 0)
    //             {
    //                 returnVal = "uid(s): ";
    //             }
    //             returnVal += uid + ", ";
    //         }
    //     }
    //     if(returnVal.length() == 0)
    //     {
    //         returnVal = "None found\n";
    //     }
    //     return returnVal;
    // }

    //Used to query the server capabilities
    @GetMapping("/query")
    public String query(String query)
    {
        String returnVal = "";
        return returnVal;
    }

    //Used for registering externally generated key values on our server
    @PostMapping("/register")
    public String register(String uid, String[] key){
        return "";
    }

}
