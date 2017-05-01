package inv;

public class items{
    private String item, notes, quantity;

    public items(){
        this.item="";
        this.quantity="";
        this.notes="";
    }
    
    public items(String item, String quantity, String notes){
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
    
    public String getQuantityString() {
        return quantity;
    }
    
    public int getQuantityInt() {
        return Integer.parseInt(quantity); 
    }
    
    public void setQuantity(String replace) {
	    if (replace.matches("\\d+"))
		quantity=replace;
    }
    
    
    public void addToQuantity(String quantity)
    {
    int temp;
    temp=Integer.parseInt(this.quantity);
    temp+=Integer.parseInt(quantity);
    this.quantity=Integer.toString(temp);
    }

}
