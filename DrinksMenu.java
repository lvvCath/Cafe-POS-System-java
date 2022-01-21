public class DrinksMenu{
    private Main_ItemMenu[] Drinks;
    private int ptr; // pointer
    
    public DrinksMenu(){
        Drinks = new Main_ItemMenu[10];
        ptr = 0;
    }
    
    /**
     * Create Item for Drinks
     *
     * @param       String ItemName, double ItemPrice
     */
    public void create(int ItemID, String ItemName, double ItemPrice, int ItemStock){
        
        if(findItem(ItemID)!=-1){
            return;
        }
        
        if(ptr==Drinks.length){
            Main_ItemMenu[] temp = new Main_ItemMenu[Drinks.length * 2];
            for(int i = 0; i < Drinks.length; i++){
                temp[i] = Drinks[i];
            }
            // make the temp array as the current array
            Drinks = temp;
        }
        Drinks[ptr] = new Main_ItemMenu(ItemID, ItemName, ItemPrice, ItemStock);
        ptr++;
    }
    
    /**
     * support method for identifying account index
     *
     * @param   int ItemID    
     * @return  Index (if new entered Item name is found inside the array)
     */
    public int findItem(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Drinks[i].getItemID()) ){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * To delete an item in the array
     *
     * @param   int ItemID      
     */
    public void delete(int ItemID){      
        int index = findItem(ItemID);
        if(index != -1){
            for(int i = index+1 ; i < ptr ; i++){
                Drinks[i-1] = Drinks[i];
            } 
            ptr--;
            Drinks[ptr] = null;
        }
    }
    
    /**
     * To Modify an item Name in the array
     *
     * @param   int ItemID, String NewItemName
     */
    public void modifyName(int ItemID, String NewItemName){
        int index = findItem(ItemID);
        if(index != -1){
            Drinks[index].modifyName(NewItemName);
        }
    }
    
    /**
     * To Modify an item Price in the array
     *
     * @param   int ItemID, double NewPrice
     */
    public void modifyPrice(int ItemID, double NewPrice){
        int index = findItem(ItemID);
        if(index != -1){
            Drinks[index].modifyPrice(NewPrice); 
        }  
    }
    
    /**
     * To Modify an item Stock in the array
     *
     * @param   int ItemID, int NewStock
     */
    public void modifyStock(int ItemID, int NewStock){
        int index = findItem(ItemID);
        if(index != -1){
            Drinks[index].modifyStock(NewStock); 
        }  
    }
    
    /**
     * Displays All Item
     *
     * @return  Drinks Array
     */
    public String toString(){
        String value = " ";
        for(int i = 0 ; i < ptr ; i++){
            value += "\n" + Drinks[i]  ;
        }
        return value ;
    }
    
    public Main_ItemMenu get(int index){
        if(index < 0 && index >= ptr){
            // return null when invalid index is supplied
            return null;
        }
        return Drinks[index];
    }
    // determine size
    public int size(){
        return ptr;
    }
    
    public void StockUpdate(int ItemID, int Qty){
        int index = findItem(ItemID);
        if(index != -1){
            int ItemStock = Drinks[index].getItemStock();
            ItemStock -= Qty;
            Drinks[index].modifyStock(ItemStock);
        }  
       
    }
    
    public double findItemPrice(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Drinks[i].getItemID())){
                double price = Drinks[i].getItemPrice();
                return price;
            }
        }
        return -1;
    }
    
    public int findItemStock(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Drinks[i].getItemID())){
                int stock = Drinks[i].getItemStock();
                return stock;
            }
        }
        return -1;
    }
    
    public String findItemName(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Drinks[i].getItemID())){
                String stock = Drinks[i].getItemName();
                return stock;
            }
        }
        return null;
    }    
    
}
