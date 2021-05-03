package kmip.aws.kmipserver.objects;

public enum CryptographicUsageMask {
    SIGN,
    VERIFY,
    ENCRYPT,
    DECRYPT,
    WRAP_KEY,
    UNWRAP_KEY,
    MAC_GENERATE,
    MAC_VERIFY,
    DERIVE_KEY,
    CERTIFICATE_SIGN,
    CRL_SIGN,
    AUTHENTICATION,
    UNRESTRICTED,
    FPE_ENCRYPT,
    FPE_DECRYPT
}
