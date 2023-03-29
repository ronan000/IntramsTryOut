package gui.commons;

public class Sports {
    private int ID;
    private String description, category, type;

    public Sports(){}
    public Sports(int ID, String description, String category, String type){
        this.ID = ID;
        this.description = description;
        this.category = category;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }
}
