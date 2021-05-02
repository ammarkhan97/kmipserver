package kmip.aws.kmipserver.controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.amazonaws.services.kms.AWSKMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kmip.aws.kmipserver.KmsClientBuilder;
import kmip.aws.kmipserver.objects.ManagedObject;
import kmip.aws.kmipserver.services.FirebaseService;

@RestController
public class AttributesController {
    
    @Autowired
    FirebaseService firebaseService;

    private ArrayList<String> supportedAsymmetricTypes = new ArrayList<>(Arrays.asList("RSA_2048", "RSA_3072", "RSA_4096", "ECC_NIST_P256", "ECC_NIST_P384", "ECC_NIST_P521", "ECC_SECG_P256K1"));
    private AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();

    //Used to get the list of attributes of an object
    @GetMapping("/getAttributeList")
    public List<String> getAttributeList(@RequestHeader String uid) throws InterruptedException, ExecutionException
    {
        return firebaseService.getManagedObject(uid).getAttributes().listAttributes();
    }

    // //Used to get the attributes of an object
    @GetMapping("/getAttributes")
    public String getAttributes(String[] attributes, String uid)
    {
        String returnVal;
        if(true)
        {   
            returnVal = "Attributes: ";
            for(true)
            {
                if()
                {
                    returnVal += "";
                }
            }
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

    //Used to modify an attribute
    @PostMapping("/modifyAttribute")
    public String modifyAttribute(String uid, String attribute, String value)
    {
        String returnVal;
        if()
        {
            if() //if object has attribute
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
}
