package kmip.aws.kmipserver.objects;

public enum RevocationReason {
    UNSPECIFIED,
    KEY_COMPROMISE,
    CA_COMPROMISE,
    AFFILIATION_CHANGED,
    SUPERSEDED,
    CESSATION_OF_OPERATION,
    PRIVILEGE_WITHDRAWN,
    EXTENSIONS
}
