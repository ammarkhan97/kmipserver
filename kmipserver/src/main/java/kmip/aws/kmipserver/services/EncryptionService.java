package kmip.aws.kmipserver.services;

import java.nio.ByteBuffer;
import java.util.Base64;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptResult;

import kmip.aws.kmipserver.KmsClientBuilder;
import kmip.aws.kmipserver.objects.ManagedObject;

public class EncryptionService {
    
    private AWSKMS kmsClient = new KmsClientBuilder().buildKmsClient();

    public String symmetricEncrypt(ManagedObject managedObject, String plaintext){
        EncryptRequest request = new EncryptRequest()
                                    .withKeyId(managedObject.getAwsKeyId())
                                    .withPlaintext(ByteBuffer.wrap(plaintext.getBytes()));

        EncryptResult result = kmsClient.encrypt(request);

        byte[] ciphertext = new byte[result.getCiphertextBlob().remaining()];
        result.getCiphertextBlob().get(ciphertext);    

        return Base64.getEncoder().encodeToString(ciphertext);
    }

    public String symmetricDecrypt(ManagedObject managedObject, String cipherText){
        byte[] cipherTextByteArray = Base64.getDecoder().decode(cipherText);

        DecryptRequest decryptRequest = new DecryptRequest()
                                            .withKeyId(managedObject.getAwsKeyId())
                                            .withCiphertextBlob(ByteBuffer.wrap(cipherTextByteArray));
                            
        DecryptResult decryptResult = kmsClient.decrypt(decryptRequest);

        byte[] plaintextByteArray = new byte[decryptResult.getPlaintext().remaining()];
        decryptResult.getPlaintext().get(plaintextByteArray);                    

        return new String(plaintextByteArray);

    }

}
