package kmip.aws.kmipserver.services;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.stereotype.Service;

@Service
public class FirebaseInit {

    @PostConstruct
    public void initialize(){
        
        try{

            FileInputStream serviceAccount = new FileInputStream("./ServiceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    
}
