
package models;

public class TypeCompte {
    
    private int id;
    private String  type , description ;

    public TypeCompte() {
    }
    
    public TypeCompte(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public TypeCompte(String type, String description) {
        this.type = type;
        this.description = description;
    }

        public TypeCompte(String type) {
        this.type = type;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return type ;
    }
       
       
       
}
