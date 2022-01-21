import java.util.*;
public class Main_ItemMenu{  
    private int ItemID;
    private String ItemName;
    private double ItemPrice;
    private int ItemStock; 
    public Main_ItemMenu(int ItemID, String ItemName, double ItemPrice, int ItemStock){
        this.ItemID = ItemID;
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemStock = ItemStock;  
    }
    //setter
    public void modifyID(int ItemID){
         this.ItemStock = ItemID;
    }
    public void modifyName(String ItemName){
         this.ItemName = ItemName;
    }
    public void modifyPrice(double ItemPrice){
         this.ItemPrice = ItemPrice;
    }
    public void modifyStock(int ItemStock){
         this.ItemStock = ItemStock;
    }
    //getter
    public String getItemName(){
        return ItemName;
    }
     public Double getItemPrice(){
        return ItemPrice;
    }
    public int getItemStock(){
        return ItemStock;
    }
     public int getItemID(){
        return ItemID;   
    }
    public String toString(){
        return "\n Item ID: " +  ItemID + "\n Item Name: " 
        +  ItemName + "\n Price: " + ItemPrice + "\n Stock: " 
        + ItemStock;
    }
    /**
     * Checks whether 2 objects are equals
     *
     * @param    obj Object to be compared
     * @return   true when account number are the same, false otherwise
     */
    public boolean equals(Object obj){
        try{
            Main_ItemMenu temp = (Main_ItemMenu) obj;
            if(this.ItemID == temp.ItemID){
                return true;
            }
            return false; 
        }catch(ClassCastException ex){
            return false;
        }
    }
}
