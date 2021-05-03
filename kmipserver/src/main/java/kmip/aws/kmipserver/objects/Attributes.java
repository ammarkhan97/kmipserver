package kmip.aws.kmipserver.objects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Attributes {

    private String activationDate;

    private String alternativeName;

    private boolean alwaysSensitive;

    private String archiveDate;

    private String comment;

    private String compromisedDate;

    private String compromisedOccurrenceDate;

    private String contactInfo;

    private String cryptographicAlgorithm;

    private int cryptographicLength;

    private State state;

    public Attributes(){
        super();
    }

    public Attributes(String activationDate, String alternativeName, boolean alwaysSensitive,
                      String archiveDate, String comment, String compromisedDate,
                      String compromisedOccurrenceDate, String contactInfo,
                      String cryptographicAlgorithm, int cryptographicLength,
                      State state){

        this.activationDate = activationDate;
        this.alternativeName = alternativeName;
        this.alwaysSensitive = alwaysSensitive;
        this.archiveDate = archiveDate;
        this.comment = comment;
        this.compromisedDate = compromisedDate;
        this.compromisedOccurrenceDate = compromisedOccurrenceDate;
        this.contactInfo = contactInfo;   
        this.cryptographicAlgorithm = cryptographicAlgorithm;  
        this.cryptographicLength = cryptographicLength;  
        this.state = state;     
    }

    public String getActivationDate(){
        return activationDate;
    }

    public void setActivationDate(String activationDate){
        this.activationDate = activationDate;
    }

    public String getAlternativeName(){
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName){
        this.alternativeName = alternativeName;
    }

    public boolean getAlwaysSensitive(){
        return alwaysSensitive;
    }

    public void setAlwaysSensitive(boolean alwaysSensitive){
        this.alwaysSensitive = alwaysSensitive;
    }

    public String getArchiveDate(){
        return archiveDate;
    }

    public void setArchiveDate(String archiveDate){
        this.archiveDate = archiveDate;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getCompromisedDate(){
        return compromisedDate;
    }

    public void setCompromisedDate(String compromisedDate){
        this.compromisedDate = compromisedDate;
    }

    public String getCompromisedOccurrenceDate(){
        return compromisedOccurrenceDate;
    }

    public void setCompromisedOccurrenceDate(String compromisedOccurrenceDate){
        this.compromisedOccurrenceDate = compromisedOccurrenceDate;
    }

    public String getContactInfo(){
        return contactInfo;
    }

    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

    public String getCryptographicAlgorithm(){
        return cryptographicAlgorithm;
    }

    public void setCryptographicAlgorithm(String cryptographicAlgorithm){
        this.cryptographicAlgorithm = cryptographicAlgorithm;
    }

    public int getCryptographicLength(){
        return cryptographicLength;
    }

    public void setCryptographicLength(int cryptographicLength){
        this.cryptographicLength = cryptographicLength;
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public List<String> listAttributes(){
        Field[] declaredFields = Attributes.class.getDeclaredFields();
        List<String> listAttributes = new ArrayList<String>();
        for(Field field: declaredFields){
            listAttributes.add(field.getName());
        }
        return listAttributes;
    }
    
}
