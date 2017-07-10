package database.information;


public class InformationUnit {
    private String key;
    private String value;
    
    public InformationUnit(String key,String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
        
}
