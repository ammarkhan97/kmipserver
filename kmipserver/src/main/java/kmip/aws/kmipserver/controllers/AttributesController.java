package kmip.aws.kmipserver.controllers;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.amazonaws.services.kms.AWSKMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kmip.aws.kmipserver.KmsClientBuilder;
import kmip.aws.kmipserver.objects.Attributes;
import kmip.aws.kmipserver.objects.ManagedObject;
import kmip.aws.kmipserver.services.FirebaseService;

import org.apache.commons.beanutils.BeanUtils;

@RestController
public class AttributesController {
    
    @Autowired
    FirebaseService firebaseService;

    private ArrayList<String> supportedAsymmetricTypes = new ArrayList<>(Arrays.asList("RSA_2048", "RSA_3072", "RSA_4096", "ECC_NIST_P256", "ECC_NIST_P384", "ECC_NIST_P521", "ECC_SECG_P256K1"));
    private AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();
    private String couldNotFindUuid = "Could not find object with uuid: ";

    //Used to get the list of attributes of an object
    @GetMapping("/getAttributeList")
    public List<String> getAttributeList(@RequestHeader String uid) throws InterruptedException, ExecutionException
    {
        return firebaseService.getManagedObject(uid).getAttributes().listAttributes();
    }

    //Used to get the attributes of an object
    @GetMapping("/getAttributes")
    public String getAttributes(@RequestHeader String uid) throws InterruptedException, ExecutionException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {   
            return managedObject.getAttributesJson();
        } 

        return couldNotFindUuid + uid;
    }

    //Used to add an attribute to an object
    @PostMapping("/addAttribute")
    public String addAttribute(@RequestHeader String uid, 
                               @RequestHeader String attribute,
                               @RequestHeader String value) throws InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException
    { 

        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            Attributes attributes = managedObject.getAttributes();
            if(attributes.listAttributes().contains(attribute)){
                BeanUtils.setProperty(attributes, attribute, value);
                managedObject.setAttributes(attributes);   
                return firebaseService.saveManagedObject(managedObject);            
            }

            return "Attribute " + attribute + " not supported for object " + uid;

        }

        return couldNotFindUuid + uid;
    }

    //Used to add or modify an attribute
    @PostMapping("/setAttribute")
    public String setAttribute(@RequestHeader String uid, 
                               @RequestHeader String attribute,
                               @RequestHeader String value) throws InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            Attributes attributes = managedObject.getAttributes();
            if(attributes.listAttributes().contains(attribute)){
                BeanUtils.setProperty(attributes, attribute, value);
                managedObject.setAttributes(attributes);   
                return firebaseService.saveManagedObject(managedObject);            
            }

            return "Attribute " + attribute + " not supported for object " + uid;

        }

        return couldNotFindUuid + uid;
    }
    
    //Used to delete an attribute
    @DeleteMapping("/deleteAttribute")
    public String delete(@RequestHeader String uid,
                         @RequestHeader String attribute) throws InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            Attributes attributes = managedObject.getAttributes();
            if(attributes.listAttributes().contains(attribute)){
                BeanUtils.setProperty(attributes, attribute, null);
                managedObject.setAttributes(attributes);   
                return firebaseService.saveManagedObject(managedObject);            
            }

            return "Attribute " + attribute + " not supported for object " + uid;

        }

        return couldNotFindUuid + uid;
    }

    //Used to modify an attribute
    @PostMapping("/modifyAttribute")
    public String modifyAttribute(@RequestHeader String uid,
                                  @RequestHeader String attribute,
                                  @RequestHeader String value) throws InterruptedException, ExecutionException, IllegalAccessException, InvocationTargetException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null)
        {
            Attributes attributes = managedObject.getAttributes();
            if(attributes.listAttributes().contains(attribute)){
                BeanUtils.setProperty(attributes, attribute, value);
                managedObject.setAttributes(attributes);   
                return firebaseService.saveManagedObject(managedObject);            
            }

            return "Attribute " + attribute + " not supported for object " + uid;

        }

        return couldNotFindUuid + uid;
    }
}
