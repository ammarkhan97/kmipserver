package kmip.aws.kmipserver.services;

import java.util.concurrent.ExecutionException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonObject;

import org.springframework.stereotype.Service;

import kmip.aws.kmipserver.objects.ManagedObject;

@Service
public class FirebaseService {
    
    public String saveManagedObject(ManagedObject managedObject) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("managedObjects").document(managedObject.getId().toString()).set(managedObject);
        JsonObject response = new JsonObject();
        response.addProperty("id", managedObject.getId().toString());
        response.addProperty("createdTime", collectionsApiFuture.get().getUpdateTime().toString());
             
        return response.toString();
    }

    public ManagedObject getManagedObject(String uuid) throws InterruptedException, ExecutionException {
        Firestore dbfFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbfFirestore.collection("managedObjects").document(uuid);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();

        DocumentSnapshot documentSnapshot = apiFuture.get();

        ManagedObject managedObject = null;

        if(documentSnapshot.exists()) {
            managedObject = documentSnapshot.toObject(ManagedObject.class);
            return managedObject;
        }

        return null;

    }
}
