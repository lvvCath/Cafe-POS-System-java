public class MealMenu{
    private Main_ItemMenu[] Meal;
    private int ptr; // pointer
    
    public MealMenu(){
        Meal = new Main_ItemMenu[10];
        ptr = 0;
    }
    
    /**
     * Create Item for Pastry
     *
     * @param       String ItemName, double ItemPrice
     */
    public void create(int ItemID, String ItemName, double ItemPrice, int ItemStock){
        
        if(findItem(ItemID)!=-1){
            return;
        }
        
        if(ptr==Meal.length){
            Main_ItemMenu[] temp = new Main_ItemMenu[Meal.length * 2];
            for(int i = 0; i < Meal.length; i++){
                temp[i] = Meal[i];
            }
            // make the temp array as the current array
            Meal = temp;
        }
        Meal[ptr] = new Main_ItemMenu(ItemID, ItemName, ItemPrice, ItemStock);
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
            if(itemID == (Meal[i].getItemID())){
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
                Meal[i-1] = Meal[i];
            } 
            ptr--;
            Meal[ptr] = null;
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
            Meal[index].modifyName(NewItemName);
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
            Meal[index].modifyPrice(NewPrice); 
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
            Meal[index].modifyStock(NewStock); 
        }  
    }
    
    /**
     * Displays All Item
     *
     * @return  Pastry Array
     */
    public String toString(){
        String value = " ";
        for(int i = 0 ; i < ptr ; i++){
            value += "\n" + Meal[i]  ;
        }
        return value ;
    }
    
    // retrieve contact by using index
    public Main_ItemMenu get(int index){
        if(index < 0 && index >= ptr){
            // return null when invalid index is supplied
            return null;
        }
        return Meal[index];
    }
    // determine size
    public int size(){
        return ptr;
    }
    
    public void StockUpdate(int ItemID, int Qty){
        int index = findItem(ItemID);
        if(index != -1){
            int ItemStock = Meal[index].getItemStock();
            ItemStock -= Qty;
            Meal[index].modifyStock(ItemStock);
        }  
       
    }
    public double findItemPrice(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Meal[i].getItemID())){
                double price = Meal[i].getItemPrice();
                return price;
            }
        }
        return -1;
    }
    public int findItemStock(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Meal[i].getItemID())){
                int stock = Meal[i].getItemStock();
                return stock;
            }
        }
        return -1;
    }
    public String findItemName(int itemID){
        
        for(int i = 0 ; i<ptr ; i++){
            if(itemID == (Meal[i].getItemID())){
                String stock = Meal[i].getItemName();
                return stock;
            }
        }
        return null;
    }
}
