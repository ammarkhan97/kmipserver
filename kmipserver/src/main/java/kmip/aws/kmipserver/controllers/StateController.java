package kmip.aws.kmipserver.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kmip.aws.kmipserver.objects.ManagedObject;
import kmip.aws.kmipserver.objects.State;
import kmip.aws.kmipserver.services.FirebaseService;

@RestController
public class StateController {
    
    @Autowired
    FirebaseService firebaseService;

    private String couldNotFindUuid = "Could not find object with uuid: ";

    //Used to activate an object
    @PostMapping("/activate")
    public String activate(@RequestHeader String uid) throws InterruptedException, ExecutionException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null){
            managedObject.getAttributes().setState(State.ACTIVE);
            LocalDate now = LocalDate.now();
            managedObject.getAttributes().setActivationDate(now.toString());
            return firebaseService.saveManagedObject(managedObject);
        }
        return couldNotFindUuid + uid;
    }

    //Used to destroy an object
    @PostMapping("/destroy")
    public String destroy(@RequestHeader String uid) throws InterruptedException, ExecutionException
    {
        ManagedObject managedObject = firebaseService.getManagedObject(uid);
        if(managedObject != null){
            State currentState = managedObject.getAttributes().getState();
            if(currentState == State.PRE_ACTIVE || currentState == State.DEACTIVATED){
                managedObject.getAttributes().setState(State.DESTROYED);
                return firebaseService.saveManagedObject(managedObject);
            }

            return "Only objects in the PRE ACTIVE or DEACTIVATED state can be destroyed";
        }

        return couldNotFindUuid + uid;

    }

}
