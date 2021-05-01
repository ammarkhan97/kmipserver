package kmip.aws.kmipserver;

import java.util.concurrent.ExecutionException;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.CreateKeyRequest;
import com.amazonaws.services.kms.model.CreateKeyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import kmip.aws.kmipserver.objects.ManagedObject;
import kmip.aws.kmipserver.services.FirebaseService;

@RestController
public class KmipController {
   
    @Autowired
    FirebaseService firebaseService;
    
    //Used to activate an object
    @PostMapping("/activate")
    public String activate(String uid)
    {
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }

    //Used to add an attribute to an object
    @PostMapping("/addAttribute")
    public String addAttribute(String uid, String attribute, String value)
    { 
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }

    //Used to check if a user can use an object
    @GetMapping("/check")
    public String check(String uid)
    {
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }

    // //Used for creating asymmetric keys, return the identifier
    // @PostMapping("/createKeyPair")
    // public String createKeyPair() {
    
    //     AWSKMS kmsClient = AWSKMSClientBuilder.standard().build();
    
    //     return "Private uid: "+ priuid +"\nPublic uid: " + pubuid +"\n";
    // }  

    //Used for creating symmetric keys, return the identifier
    @PostMapping("/create")
    public String createSymmetricKey() throws InterruptedException, ExecutionException {

        AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();
        
        String creatingMasterKey = "Creating Master Key";
        CreateKeyRequest request = new CreateKeyRequest().withDescription(creatingMasterKey);
        CreateKeyResult result = kmsClient.createKey(request);

        return firebaseService.saveManagedObject(new ManagedObject(
            result.getKeyMetadata().getArn(),
            result.getKeyMetadata().getKeyId(),
            "Symmetric",
            "US-EAST-2"));
    }

    //Used for decrypting given data with given key
    @GetMapping("/decrypt")
    public String decrypt(String uid, String data) {
        return "";
    }

    //Used to delete an attribute
    @PostMapping("/deleteAttribute")
    public String delete(String uid, String attribute)
    {
        String returnVal;
        if(true)
        {
            if(true) //find attribute
            {
                returnVal = "uid: "+ uid + "\n";
            } else
            {
                returnVal = "no such attribute\n";
            }
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }

    //Used to destroy an object
    @PostMapping("/destroy")
    public String destroy(String uid)
    {
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }

    //Used to discover the KMIP version implemented
    @GetMapping("/discover")
    public String discoverVersion()
    {
        return "Version: \n";
    }

    //Used for encrypting given data with given key
    @GetMapping("/encrypt")
    public String encrypt(String uid, String data) {
        return "";
    }

    //Used for retrieving an object's value given the identifier
    @GetMapping("/get")
    public String get(@RequestHeader String uid) throws InterruptedException, ExecutionException {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            //returnVal = "Object Type: "+ objType +"\nuid: "+ uid + "\nObjects(S): ";
            return managedObject.toString();
        }
        return "No object with uid: " + uid + " found";
    }

    // //Used to get the attributes of an object
    // @GetMapping("/getAttributes")
    // public String getAttributes(String[] attributes, String uid)
    // {
    //     String returnVal;
    //     if(true)
    //     {   
    //         returnVal = "Attributes: ";
    //         for(true)
    //         {
    //             if()
    //             {
    //                 returnVal += "";
    //             }
    //         }
    //     } else
    //     {
    //         returnVal = "uid not found\n";
    //     }
    //     return returnVal;
    // }

    //Used to get the list of attributes of an object
    // @GetMapping("/getAttributeList")
    // public String getAttributeList(String uid)
    // {
    //     String returnVal;
    //     if()
    //     {
    //         returnVal = "Attributes: "
    //         for()
    //         {
    //             returnVal += "";
    //         }
    //     } else
    //     {
    //         returnVal = "uid not found\n";
    //     }
    //     return returnVal;
    // }

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

    //Used to modify an attribute
    // @PostMapping("/modifyAttribute")
    // public String modifyAttribute(String uid, String attribute, String value)
    // {
    //     String returnVal;
    //     if()
    //     {
    //         if() //if object has attribute
    //         {
    //             returnVal = "uid: "+ uid + "\n";
    //         } else
    //         {
    //             returnVal = "no such attribute\n";
    //         }
    //     } else
    //     {
    //         returnVal = "uid not found\n";
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

    //Used to add or modify an attribute
    @PostMapping("/setAttribute")
    public String setAttribute(String uid, String attribute, String value)
    {
        String returnVal;
        if(true)
        {
            returnVal = "uid: "+ uid + "\n";
        } else
        {
            returnVal = "uid not found\n";
        }
        return returnVal;
    }
}
