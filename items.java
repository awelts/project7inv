package inv;

public class items{
    private String item, notes;
    private int quantity;

    public items(){
        this.item="";
        this.quantity=0;
        this.notes="";
    }
    
    public items(String item, int quantity, String notes){
        this.item=item;
        this.quantity=quantity;
        this.notes=notes;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
