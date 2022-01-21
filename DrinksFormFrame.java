import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DrinksFormFrame extends JFrame{
    //Header Panel
    private JPanel pnlHead;
    private JLabel lblHead;
    //Body Panel
    private JPanel pnlBody;
    private JLabel lblId, lblItemName, lblPrice, lblStocks;
    private JTextField txtId, txtItemName, txtPrice, txtStocks;
    //Bottom Buttons
    private JButton btnSave, btnCancel;
    
    private JFrame app;
    private OrderMenuFRAME orderFrame;
    private DrinksMenu mgr;
    
    // when set, action is edit
    private Main_ItemMenu rrent;
    
    public DrinksFormFrame(OrderMenuFRAME orderFrame, DrinksMenu mgr){
        Font    fontHead = new Font("Cambria", Font.BOLD, 24),
                fontBody = new Font("Cambria", Font.BOLD, 20),
                fontButton = new Font("Calibri", Font.BOLD, 20);
        //LABELS
        lblHead = new JLabel("Coffee Inventory", JLabel.CENTER);
            lblHead.setFont(fontHead);
            lblHead.setForeground(Color.WHITE);
        lblId = new JLabel("Item ID");
            lblId.setFont(fontBody);
            lblId.setForeground(Color.WHITE);
            lblId.setOpaque(true);
            lblId.setBackground(new Color(43, 122, 120));
        lblItemName = new JLabel("Item Name");
            lblItemName.setFont(fontBody);
            lblItemName.setForeground(Color.WHITE);
            lblItemName.setOpaque(true);
            lblItemName.setBackground(new Color(43, 122, 120));
        lblPrice = new JLabel("Item Price");
            lblPrice.setFont(fontBody);
            lblPrice.setForeground(Color.WHITE);
            lblPrice.setOpaque(true);
            lblPrice.setBackground(new Color(43, 122, 120));
        lblStocks = new JLabel("Stocks");
            lblStocks.setFont(fontBody);
            lblStocks.setForeground(Color.WHITE);
            lblStocks.setOpaque(true);
            lblStocks.setBackground(new Color(43, 122, 120));
        //TEXT FIELDS
        txtId = new JTextField();
            txtId.setFont(fontBody);
        txtItemName = new JTextField();
            txtItemName.setFont(fontBody);
        txtPrice = new JTextField();
            txtPrice.setFont(fontBody);
        txtStocks = new JTextField();
            txtStocks.setFont(fontBody);
        //BUTTONS
        btnSave = new JButton("Save");
            btnSave.setFont(fontButton);
            btnSave.setForeground(Color.WHITE);
            btnSave.setBackground(Color.DARK_GRAY);
                btnSave.addActionListener(new SaveAction());
        btnCancel = new JButton("Cancel");
            btnCancel.setFont(fontButton);
            btnCancel.setForeground(Color.WHITE);
            btnCancel.setBackground(Color.GRAY);
                btnCancel.addActionListener(new CancelAction());
        //PANELS      
        pnlHead = new JPanel(new GridLayout(1,1));
            pnlHead.setBackground(new Color(23, 37, 42));
            pnlHead.add(lblHead);
        pnlBody = new JPanel(new GridLayout(5,2));
            pnlBody.add(lblId);
            pnlBody.add(txtId);
            pnlBody.add(lblItemName);
            pnlBody.add(txtItemName);
            pnlBody.add(lblPrice);
            pnlBody.add(txtPrice);
            pnlBody.add(lblStocks);
            pnlBody.add(txtStocks);
            pnlBody.add(btnSave);
            pnlBody.add(btnCancel);
        
        add(pnlHead, "North");
        add(pnlBody);
        // pass manager instance to form instance
        this.mgr = mgr;

        this.orderFrame = orderFrame;
        app = this;
    
    }
    
    private class CancelAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            app.setVisible(false);
            orderFrame.setVisible(true);
            // clear fields
            txtId.setText("");
            txtItemName.setText("");
            txtPrice.setText("");
            txtStocks.setText("");
        }
    }
    
     // add action for saving number
    private class SaveAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            String sId = txtId.getText();
            String sPrice = txtPrice.getText();
            String sStocks =  txtStocks.getText();
            
            int id = Integer.parseInt(txtId.getText());
            String name = txtItemName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int stock = Integer.parseInt(txtStocks.getText());
            
            if(sId.equals("")){
                JOptionPane.showMessageDialog(app, "Please enter Item ID!");
                return;
            }
            if(name.equals("")){
                JOptionPane.showMessageDialog(app, "Please enter Item Name!");
                return;
            }
            if(sPrice.equals("")){
                JOptionPane.showMessageDialog(app, "Please enter Item Price!");
                return;
            }
            if(sStocks.equals("")){
                JOptionPane.showMessageDialog(app, "Please enter Item Stock!");
                return;
            }
            
            if(rrent==null){
                mgr.create(id,name, price, stock);
            }else{
                rrent.modifyID(id);
                rrent.modifyName(name);
                rrent.modifyPrice(price);
                rrent.modifyStock(stock);
            }
            
            // clear fields
            txtId.setText("");
            txtItemName.setText("");
            txtPrice.setText("");
            txtStocks.setText("");

            app.setVisible(false);
            orderFrame.setVisible(true);
            // update table in main frame
            orderFrame.Drinks_updateTable();
        }
    }
    
    // add method to set current contact
    public void setMain(Main_ItemMenu Current){
        // set current passed to frame
        rrent = Current;
        // check if no current value is passed which means a contact will be added
        if(rrent!=null){
            // set details for editing
            txtId.setText(rrent.getItemID() + "");
            txtItemName.setText(rrent.getItemName());
            txtPrice.setText(rrent.getItemPrice() + "");
            txtStocks.setText(rrent.getItemStock() + "");
        }
          
    }
       
}
