/**
 * POS Main Interface.
 *
 * @author (Code Z)
 * @version (2020)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
public class OrderMenuFRAME extends JFrame{
    private JPanel contentPane;
    private JTextField txt_Subtotal;
    private JTextField txt_Discount;
    private JTextField txt_Total;
    private JTextField txt_Change;
    private JTextField txt_Cash;
    private JTextField txt_ItemID;
    private JTextField  spin_Qty;
    private JTextField txt_allItemTotal;
    private JTextArea AreaR, SalesArea;
    private JTextArea txt_Output; 
    private JButton btn_PlaceOrd,btn_ClearOrd;
    private  JButton btn_Clear,btn_Cash,btn_Sales;  
    //Main Panels
    private JPanel pnl_Header, pnl_Receipt, pnl_Menu, pnl_Categories, pnl_Order, pnl_Others;
    //SubPanels: Menu
    private JPanel pnl_Pastry, pnl_Coffee, pnl_Sales;
    //receipt
    private final JTextArea txt_HeadReceipt = new JTextArea();
    private int Transaction; //records transaction no for sales
    
    //Pastry: TABLE COMPONENTS
    private JLabel lblID, lblName, lblPrice,lblStock, lblAction;
    private JLabel[][] lblData;
    private JPanel[] pnlAction; 
    private JRadioButton[] rbEdit, rbDelete;
    private JButton[] btnGo;
    private JButton btnFirst, btnPrevious, btnNext, btnLast, btnAdd;
    private JPanel pnlCenter, pnlSouth;
    private MealFormFrame mealFormFrame;
    private MealMenu M_mgr;
    
    //Coffee: TABLE COMPONENTS
    private JLabel Drinks_lblID, Drinks_lblName, Drinks_lblPrice,Drinks_lblStock, Drinks_lblAction;
    private JLabel[][] Drinks_lblData;
    private JPanel[] Drinks_pnlAction; 
    private JRadioButton[] Drinks_rbEdit, Drinks_rbDelete;
    private JButton[] Drinks_btnGo;
    private JButton Drinks_btnFirst, Drinks_btnPrevious, Drinks_btnNext, Drinks_btnLast, Drinks_btnAdd;
    private JPanel Drinks_pnlCenter, Drinks_pnlSouth;
    private DrinksFormFrame drinksFormFrame;
    private DrinksMenu D_mgr;
    private  double subtotal=0,amountdis=0,discount=0,price=0;
    private String name;
    private int page_size, page_current, page_limit,qty,id;
    private int Drinks_page_size, Drinks_page_current, Drinks_page_limit;
    
    //Sales: TABLE COMPONETS
    private JLabel Sales_lblID,Sales_lblName,Sales_lblPrice,Sales_lblQTY,Sales_lblTotal,Sales_lblDiscount;
    private JLabel[][] Sales_lblData;
    private JButton Sales_btnFirst, Sales_btnPrevious, Sales_btnNext, Sales_btnLast, Sales_btnBack;
    private JPanel Sales_pnlNorth, Sales_pnlCenter,Sales_pnlSouth;
    private OrderMenuFRAME ordermenu;
    private OrderMenuFRAME app;
    private JFrame mainFrame;
    private JFrame loginFRAME;
   
    private boolean isMenuVisible;

    /**
     * Create the frame.
     */
    public OrderMenuFRAME(JFrame loginFRAME) {
        isMenuVisible = false;
        Transaction = 1;
        ActionListener Order = new PlaceOrder();
        ActionListener ClearButton = new ClearOrder();
        ActionListener ClearAll = new ClearFields();
        ActionListener CashButton = new GiveCash();  
        /**
          * Main Panel
          */
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 50, 1350, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        
        /**
          * PANEL#1: Header Panel
          */
        pnl_Header = new JPanel();
            pnl_Header.setBackground(new Color(23, 37, 42));
            pnl_Header.setBounds(0, 0, 1349, 53);
            pnl_Header.setLayout(null);
        contentPane.add(pnl_Header);
            /**
              * PANEL#1: Components
              */
            JLabel lbl_CodeZ = new JLabel("CODE-Z Cafe");
                lbl_CodeZ.setForeground(Color.WHITE);
                lbl_CodeZ.setFont(new Font("Cambria", Font.BOLD, 30));
                lbl_CodeZ.setHorizontalAlignment(SwingConstants.CENTER);
                lbl_CodeZ.setBounds(0, 0, 1332, 53);
            pnl_Header.add(lbl_CodeZ);
            
        /**
          * PANEL#2: Reciept Panel
          */
        pnl_Receipt = new JPanel();
            pnl_Receipt.setBackground(new Color(43, 122, 120));
            pnl_Receipt.setBounds(0, 50, 357, 671);
            pnl_Receipt.setLayout(null);
        contentPane.add(pnl_Receipt);
            /**
              * PANEL#2: Components
              */
             
            JLabel lbl_Receipt = new JLabel("Receipt");
                lbl_Receipt.setHorizontalAlignment(SwingConstants.CENTER);
                lbl_Receipt.setForeground(Color.WHITE);
                lbl_Receipt.setFont(new Font("Cambria", Font.BOLD, 23));
                lbl_Receipt.setBounds(0, 0, 357, 39);
            pnl_Receipt.add(lbl_Receipt);
            //Subtotal Field
            JLabel lbl_Subtotal = new JLabel("Subtotal");
                lbl_Subtotal.setForeground(Color.WHITE);
                lbl_Subtotal.setFont(new Font("Cambria", Font.BOLD, 18));
                lbl_Subtotal.setBounds(10, 513, 82, 24);
            pnl_Receipt.add(lbl_Subtotal);
            txt_Subtotal = new JTextField();
                txt_Subtotal.setFont(new Font("Calibri", Font.BOLD, 18));
                txt_Subtotal.setHorizontalAlignment(SwingConstants.RIGHT);
                txt_Subtotal.setBounds(91, 511, 75, 24);
            pnl_Receipt.add(txt_Subtotal);
            //Discount Field
            JLabel lbl_Discount = new JLabel("Discount");
                lbl_Discount.setForeground(Color.WHITE);
                lbl_Discount.setFont(new Font("Cambria", Font.BOLD, 18));
                lbl_Discount.setBounds(177, 513, 82, 24);
            pnl_Receipt.add(lbl_Discount);
            txt_Discount = new JTextField();
                txt_Discount.setHorizontalAlignment(SwingConstants.RIGHT);
                txt_Discount.setFont(new Font("Calibri", Font.BOLD, 18));
                txt_Discount.setBounds(260, 511, 75, 24);
            pnl_Receipt.add(txt_Discount);
            //Total Field
            JLabel lbl_Total = new JLabel("Total");
                lbl_Total.setForeground(Color.WHITE);
                lbl_Total.setFont(new Font("Cambria", Font.BOLD, 18));
                lbl_Total.setBounds(10, 539, 82, 24);
            pnl_Receipt.add(lbl_Total);
            txt_Total = new JTextField();
                txt_Total.setHorizontalAlignment(SwingConstants.RIGHT);
                txt_Total.setFont(new Font("Calibri", Font.BOLD, 18));
                txt_Total.setColumns(10);
                txt_Total.setBounds(91, 541, 75, 24);
            pnl_Receipt.add(txt_Total);
            //Change Field
            JLabel lbl_Change = new JLabel("Change");
                lbl_Change.setForeground(Color.WHITE);
                lbl_Change.setBounds(10, 576, 77, 24);
                lbl_Change.setFont(new Font("Cambria", Font.BOLD, 20));
            pnl_Receipt.add(lbl_Change);
            txt_Change = new JTextField();
                txt_Change.setHorizontalAlignment(SwingConstants.RIGHT);
                txt_Change.setFont(new Font("Calibri", Font.BOLD, 18));
                txt_Change.setBounds(91, 576, 244, 26);
            pnl_Receipt.add(txt_Change);
            //Cash Field
            JLabel lbl_Cash = new JLabel("Cash");
                lbl_Cash.setForeground(Color.WHITE);
                lbl_Cash.setFont(new Font("Cambria", Font.BOLD, 18));
                lbl_Cash.setBounds(176, 539, 82, 24);
            pnl_Receipt.add(lbl_Cash);
            txt_Cash = new JTextField();
                txt_Cash.setHorizontalAlignment(SwingConstants.RIGHT);
                txt_Cash.setFont(new Font("Calibri", Font.BOLD, 18));
                txt_Cash.setBounds(260, 541, 75, 24);
            pnl_Receipt.add(txt_Cash);
            //Buttons : Cash
            btn_Cash = new JButton("Cash");
                btn_Cash.setForeground(Color.WHITE);
                btn_Cash.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 22));
                btn_Cash.setBackground(Color.DARK_GRAY);
                btn_Cash.setBounds(91, 611, 110, 45);
                  btn_Cash.addActionListener(CashButton);
           pnl_Receipt.add(btn_Cash);
           btn_Clear = new JButton("Clear");
                btn_Clear.setForeground(Color.WHITE);
                btn_Clear.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
                btn_Clear.setBackground(Color.GRAY);
                btn_Clear.setBounds(225, 611, 110, 45);
                 btn_Clear.addActionListener(ClearAll);
            pnl_Receipt.add(btn_Clear);
           
            txt_HeadReceipt.setText("*****************************************************************************************" + 
                                              "\r\n\t                  CODE Z Cafe" + 
                                              "\r\n\t    938 Aurora, Cubao, Quezon City" + 
                                              "\r\n\t             1109 Metro Manila" + 
                                "\r\n*****************************************************************************************" + 
                                "\r\n           ID\t         ITEM                     PRICE               QTY" + 
                                "\r\n-----------------------------------------------------------------------------------------");
        txt_HeadReceipt.setBounds(0, 36, 357, 110);
        pnl_Receipt.add(txt_HeadReceipt);
        
            //SUBPANEL: shows Customer Reciept
            AreaR  =  new JTextArea();
                 // AreaR.setBounds(0, 147, 357, 181);
                 // pnl_Receipt.add(AreaR);
                 JScrollPane scroll = new JScrollPane (AreaR);
                 scroll.setBounds(0, 144, 357, 200);
            pnl_Receipt.add(scroll);
            
            txt_Output = new JTextArea();
        txt_Output.setText("" + 
                                     "\r\n         Subtotal" + 
                                     "\r\n         Discount" + 
                                     "\r\n         Total" + 
                                 "\r\n-----------------------------------------------------------------------------------------" + 
                                     "\r\n         Cash" + 
                                     "\r\n         Change");
        txt_Output.setBounds(0, 340, 357, 115);
        pnl_Receipt.add(txt_Output);    
                 
            JTextArea txt_ThankYou = new JTextArea();
        txt_ThankYou.setText("*****************************************************************************************" + 
                         "\r\n\t         Thank you for Purchasing!" + 
                         "\r\n*****************************************************************************************");
        txt_ThankYou.setBounds(0, 455, 357, 45);
        pnl_Receipt.add(txt_ThankYou);    
             
         /**
          * PANEL#4: Categories Panel
          */
        pnl_Categories = new JPanel();
            pnl_Categories.setBackground(new Color(222, 242, 241));
            pnl_Categories.setForeground(new Color(0, 0, 0));
            pnl_Categories.setBounds(365, 604, 776, 113);
            pnl_Categories.setLayout(null);
        contentPane.add(pnl_Categories);
            /**
              * PANEL#4: Components
              */
            JLabel lbl_Categories = new JLabel("Categories");
                lbl_Categories.setHorizontalAlignment(SwingConstants.CENTER);
                lbl_Categories.setBounds(301, 13, 148, 25);
                lbl_Categories.setForeground(new Color(51, 0, 51));
                lbl_Categories.setFont(new Font("Cambria", Font.BOLD, 23));
            pnl_Categories.add(lbl_Categories);
        
            Button btn_Pastry = new Button("Pastry");
                btn_Pastry.setActionCommand("Pastry");
                btn_Pastry.setBackground(new Color(43, 122, 120));
                btn_Pastry.setForeground(Color.WHITE);
                btn_Pastry.setFont(new Font("Cambria", Font.BOLD, 18));
                btn_Pastry.setBounds(195, 44, 156, 43);
                btn_Pastry.addActionListener(new ShowPastryInventory());
            pnl_Categories.add(btn_Pastry);
        
            Button btn_Coffee = new Button("Coffee");
                btn_Coffee.setActionCommand("Coffee");
                btn_Coffee.setBackground(new Color(43, 122, 120));
                btn_Coffee.setForeground(Color.WHITE);
                btn_Coffee.setFont(new Font("Cambria", Font.BOLD, 18));
                btn_Coffee.setBounds(391, 44, 156, 43);
                btn_Coffee.addActionListener(new ShowCoffeeInventory());
            pnl_Categories.add(btn_Coffee);
             
        /**
          * PANEL#5: Order Panel
          */
        pnl_Order = new JPanel();
            pnl_Order.setBackground(new Color(43, 122, 120));
            pnl_Order.setBounds(1148, 50, 190, 427);
            pnl_Order.setLayout(null);
        contentPane.add(pnl_Order);
            /**
              * PANEL#5: Components
              */
            JLabel lbl_Order = new JLabel("Order");
                lbl_Order.setForeground(Color.WHITE);
                lbl_Order.setHorizontalAlignment(SwingConstants.CENTER);
                lbl_Order.setFont(new Font("Cambria", Font.BOLD, 27));
                lbl_Order.setBounds(0, 36, 178, 42);
            pnl_Order.add(lbl_Order);
            
            txt_ItemID = new JTextField();
                txt_ItemID.setFont(new Font("Cambria", Font.BOLD, 18));
                txt_ItemID.setBounds(100, 123, 71, 42);
                txt_ItemID.setColumns(10);
            pnl_Order.add(txt_ItemID);
            
            JLabel lbl_ItemID = new JLabel("Enter ID");
                lbl_ItemID.setForeground(Color.WHITE);
                lbl_ItemID.setFont(new Font("Cambria", Font.BOLD, 15));
                lbl_ItemID.setBounds(23, 123, 77, 42);
            pnl_Order.add(lbl_ItemID);
            
            JLabel lbl_EnterQnty = new JLabel("Enter Qty");
                lbl_EnterQnty.setForeground(Color.WHITE);
                lbl_EnterQnty.setFont(new Font("Cambria", Font.BOLD, 15));
                lbl_EnterQnty.setBounds(23, 187, 77, 42);
            pnl_Order.add(lbl_EnterQnty);
            
            spin_Qty = new JTextField();
                spin_Qty.setFont(new Font("Cambria", Font.BOLD, 20));
                spin_Qty.setBounds(100, 187, 71, 42);
            pnl_Order.add(spin_Qty);
       
           btn_PlaceOrd= new JButton("Place Order");
                btn_PlaceOrd.setBackground(SystemColor.inactiveCaption);
                btn_PlaceOrd.setBounds(46, 280, 109, 42);
                btn_PlaceOrd.addActionListener(Order);
            pnl_Order.add(btn_PlaceOrd);
            
          btn_ClearOrd = new JButton("Clear");
                btn_ClearOrd.setBounds(46, 338, 109, 42);
            pnl_Order.add(btn_ClearOrd);
               btn_ClearOrd.addActionListener(ClearButton);
        
        /**
          * PANEL#6: Other Buttons [Sales, Logout]
          */
        pnl_Others = new JPanel();
            pnl_Others.setBackground(new Color(58, 176, 169));
            pnl_Others.setBounds(1149, 477, 189, 244);
            pnl_Others.setLayout(null);
        contentPane.add(pnl_Others);
            /**
              * PANEL#6: Components
              */
            JLabel lbl_Sales = new JLabel(" ");
                lbl_Sales.setBounds(0, 0, 215, 22);
                lbl_Sales.setOpaque(true);
                lbl_Sales.setBackground(new Color(23, 37, 42));
                lbl_Sales.setHorizontalAlignment(SwingConstants.CENTER);
                lbl_Sales.setForeground(Color.WHITE);
                lbl_Sales.setFont(new Font("Cambria", Font.BOLD, 27));
            pnl_Others.add(lbl_Sales);
        
             btn_Sales = new JButton("Sales");
                btn_Sales.setBounds(24, 62, 141, 52);
                btn_Sales.setBackground(new Color(222, 242, 241));
                btn_Sales.setForeground(Color.BLACK);
                btn_Sales.setFont(new Font("Cambria", Font.BOLD, 18));
             btn_Sales.addActionListener(new ShowSalesInventory());
            pnl_Others.add(btn_Sales);
            
            Button btn_Logout = new Button("Logout");
                btn_Logout.setBounds(24, 136, 143, 52);
                btn_Logout.setForeground(Color.WHITE);
                btn_Logout.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
                btn_Logout.setBackground(Color.GRAY);
            pnl_Others.add(btn_Logout);
            btn_Logout.addActionListener(new LogOut());
        
        //Panel: Division#1
        JPanel DivPanel = new JPanel();
            DivPanel.setBackground(new Color(23, 37, 42));
            DivPanel.setBounds(356, 50, 10, 722);
        contentPane.add(DivPanel);
        //Panel: Division#2
        JPanel Div2Panel = new JPanel();
            Div2Panel.setBackground(new Color(23, 37, 42));
            Div2Panel.setBounds(1141, 50, 10, 719);
        contentPane.add(Div2Panel);
        
        /**
          * PANEL#3: Menu Panel
          * 
          */
        pnl_Menu = new JPanel();
            pnl_Menu.setBackground(Color.DARK_GRAY);
            pnl_Menu.setBounds(366, 50, 776, 561);
            pnl_Menu.setLayout(null);
        contentPane.add(pnl_Menu);
            /**
              * PANEL#3: Components
              * TABLE-Pastry
              * 
              */
            pnl_Pastry = new JPanel();
                pnl_Pastry.setBounds(0, 3, 775, 557);
                pnl_Pastry.setLayout(new BorderLayout());
            pnl_Menu.add(pnl_Pastry);
            
            page_size = 10;
            page_current = 0;
            page_limit = 1;
            
            Font fontHeader = new Font("Cambria", Font.BOLD, 20),
            fontData = new Font("Cambria", Font.BOLD, 18),
            fontFooter = new Font("Cambria", Font.BOLD, 18);
            Border borderAll = BorderFactory.createLineBorder(Color.GRAY);
            ActionListener navAction = new NavigationAction();
            
            JLabel lbl1 = new JLabel(" ", JLabel.CENTER);
                lbl1.setBackground(new Color(23, 37, 42));
                lbl1.setOpaque(true);
            JLabel lbl2 = new JLabel(" ", JLabel.CENTER);
                lbl2.setBackground(new Color(23, 37, 42));
                lbl2.setOpaque(true);
            JLabel lbl4 = new JLabel(" ", JLabel.CENTER);
                lbl4.setBackground(new Color(23, 37, 42));
                lbl4.setOpaque(true);
            JLabel lbl5 = new JLabel(" ", JLabel.CENTER);
                lbl5.setBackground(new Color(23, 37, 42));
                lbl5.setOpaque(true);
            JLabel lblHead = new JLabel("Pastry", JLabel.CENTER); // apply center alignment using 2nd arg
                lblHead.setFont(fontHeader);
                lblHead.setOpaque(true);
                lblHead.setForeground(Color.WHITE);
                lblHead.setBackground(new Color(23, 37, 42));
            
            // initialize components
            lblID = new JLabel("ID", JLabel.CENTER); // apply center alignment using 2nd arg
                lblID.setFont(fontHeader);
                lblID.setOpaque(true);
                lblID.setForeground(Color.WHITE);
                lblID.setBackground(new Color(43, 122, 120));
            lblName = new JLabel("Name", JLabel.CENTER);
                lblName.setFont(fontHeader);
                lblName.setOpaque(true);
                lblName.setForeground(Color.WHITE);
                lblName.setBackground(new Color(43, 122, 120));
            lblPrice = new JLabel("Price", JLabel.CENTER);
                lblPrice.setFont(fontHeader);
                lblPrice.setOpaque(true);
                lblPrice.setForeground(Color.WHITE);
                lblPrice.setBackground(new Color(43, 122, 120));
            lblStock= new JLabel("Stock", JLabel.CENTER);
                lblStock.setFont(fontHeader);
                lblStock.setOpaque(true);
                lblStock.setForeground(Color.WHITE);
                lblStock.setBackground(new Color(43, 122, 120));
            lblAction = new JLabel("Action", JLabel.CENTER);
                lblAction.setFont(fontHeader);
                lblAction.setOpaque(true);
                lblAction.setForeground(Color.WHITE);
                lblAction.setBackground(new Color(43, 122, 120));
    
            btnFirst = new JButton("<<");
                btnFirst.setFont(fontFooter);
                btnFirst.setForeground(Color.WHITE);
                btnFirst.setBackground(Color.GRAY);
                    btnFirst.addActionListener(navAction);
            btnPrevious = new JButton("<");
                btnPrevious.setFont(fontFooter);
                btnPrevious.setForeground(Color.WHITE);
                btnPrevious.setBackground(Color.GRAY);
                    btnPrevious.addActionListener(navAction);
                    
            JLabel lblMeal = new JLabel("");
                lblMeal.setFont(fontFooter);
                lblMeal.setOpaque(true);
                lblMeal.setForeground(Color.WHITE);
                lblMeal.setBackground(Color.DARK_GRAY);
            
            btnNext = new JButton(">");
                btnNext.setFont(fontFooter);
                btnNext.setForeground(Color.WHITE);
                btnNext.setBackground(Color.GRAY);
                    btnNext.addActionListener(navAction);
            btnLast = new JButton(">>");
                btnLast.setFont(fontFooter);
                btnLast.setForeground(Color.WHITE);
                btnLast.setBackground(Color.GRAY);
                    btnLast.addActionListener(navAction);
                    
            btnAdd = new JButton("ADD");
                btnAdd.setFont(fontFooter);
                btnAdd.setForeground(Color.WHITE);
                btnAdd.setBackground(new Color(43, 122, 120));
                    btnAdd.addActionListener(new AddAction()); // link add action to add button
            
            pnlCenter = new JPanel(new GridLayout(page_size + 3,5)); // set the panel layout upon initialization
            pnlSouth = new JPanel(new GridLayout(1,2));
            
            // Center Panel: row 1
            pnlCenter.add(lbl1);
            pnlCenter.add(lbl2);
            pnlCenter.add(lblHead);
            pnlCenter.add(lbl4);
            pnlCenter.add(lbl5);
            // Center Panel: row 2
            pnlCenter.add(lblID);
            pnlCenter.add(lblName);
            pnlCenter.add(lblPrice);
            pnlCenter.add(lblStock);
            pnlCenter.add(lblAction);
    
            // initialize components for contact details
            lblData = new JLabel[page_size][4];
            pnlAction = new JPanel[page_size];
                rbEdit = new JRadioButton[page_size];
                rbDelete = new JRadioButton[page_size];
                btnGo = new JButton[page_size];
            GridLayout pnlActionLayout = new GridLayout(1,4);
            
            // intialize each item in the array, set component properties
            for(int i=0; i<page_size; i++){
                // initialize labels
                lblData[i][0] = new JLabel();
                    lblData[i][0].setFont(fontData);
                    lblData[i][0].setBorder(borderAll);
                lblData[i][1] = new JLabel();
                    lblData[i][1].setFont(fontData);
                    lblData[i][1].setBorder(borderAll);
                lblData[i][2] = new JLabel();
                    lblData[i][2].setFont(fontData);
                    lblData[i][2].setBorder(borderAll);
                lblData[i][3] = new JLabel();
                    lblData[i][3].setFont(fontData);
                    lblData[i][3].setBorder(borderAll);
                
                    // construct action panel content
                rbEdit[i] = new JRadioButton("Edit");
                    rbEdit[i].setFont(new Font("Calibri", Font.BOLD, 12));
                rbDelete[i] = new JRadioButton("Delete");
                    rbDelete[i].setFont(new Font("Calibri", Font.BOLD, 8));
                btnGo[i] = new JButton("Go");
                    btnGo[i].setForeground(new Color(23, 37, 42));
                    btnGo[i].setBackground(new Color(222, 242, 241));
                    btnGo[i].setBorder(BorderFactory.createLineBorder(new Color(43, 122, 120)));
                        // add action to go button and store index value
                        btnGo[i].addActionListener(new GoAction(i));
                
                // create panel and add its radio buttons and button
                pnlAction[i] = new JPanel(pnlActionLayout);
                    pnlAction[i].setBorder(borderAll);
                pnlAction[i].add(rbEdit[i]);
                pnlAction[i].add(rbDelete[i]);
                pnlAction[i].add(btnGo[i]);
                
                // create ButtonGroup to group the radio buttons
                ButtonGroup radioGroup = new ButtonGroup();
                radioGroup.add(rbEdit[i]);
                radioGroup.add(rbDelete[i]);
                
                // place the data label and action panel to the center panel
                pnlCenter.add(lblData[i][0]);
                pnlCenter.add(lblData[i][1]);
                pnlCenter.add(lblData[i][2]);
                pnlCenter.add(lblData[i][3]);
                pnlCenter.add(pnlAction[i]);
            }
    
            // add footer buttons for navigation
            pnlCenter.add(btnFirst);
            pnlCenter.add(btnPrevious);
            pnlCenter.add(btnAdd);
            pnlCenter.add(btnNext);
            pnlCenter.add(btnLast);
    
            // add panels to frame
            pnl_Pastry.add(pnlCenter);
            pnl_Pastry.add(pnlSouth, "South");
            // initialize contact manager
            M_mgr = new MealMenu();
            updateTable();
        //////////////////////////////
        
            /**
              * PANEL#3: Components
              * TABLE-Coffee
              * 
              */
            Drinks_page_size = 10;
            Drinks_page_current = 0;
            Drinks_page_limit = 1; 
            ActionListener Drinks_navAction = new Drinks_NavigationAction();
            pnl_Coffee = new JPanel();
                pnl_Coffee.setBounds(0, 3, 775, 557);
                pnl_Coffee.setLayout(new BorderLayout());
            pnl_Menu.add(pnl_Coffee);
            

            JLabel Drinks_lbl1 = new JLabel(" ", JLabel.CENTER);
                Drinks_lbl1.setBackground(new Color(23, 37, 42));
                Drinks_lbl1.setOpaque(true);
            JLabel Drinks_lbl2 = new JLabel(" ", JLabel.CENTER);
                Drinks_lbl2.setBackground(new Color(23, 37, 42));
                Drinks_lbl2.setOpaque(true);
            JLabel Drinks_lbl4 = new JLabel(" ", JLabel.CENTER);
                Drinks_lbl4.setBackground(new Color(23, 37, 42));
                Drinks_lbl4.setOpaque(true);
            JLabel Drinks_lbl5 = new JLabel(" ", JLabel.CENTER);
                Drinks_lbl5.setBackground(new Color(23, 37, 42));
                Drinks_lbl5.setOpaque(true);
            JLabel Drinks_lblHead = new JLabel("Coffee", JLabel.CENTER);
                Drinks_lblHead.setFont(fontHeader);
                Drinks_lblHead.setOpaque(true);
                Drinks_lblHead.setForeground(Color.WHITE);
                Drinks_lblHead.setBackground(new Color(23, 37, 42));
            
            // initialize components
            Drinks_lblID = new JLabel("ID", JLabel.CENTER);
                Drinks_lblID.setFont(fontHeader);
                Drinks_lblID.setOpaque(true);
                Drinks_lblID.setForeground(Color.WHITE);
                Drinks_lblID.setBackground(new Color(43, 122, 120));
            Drinks_lblName = new JLabel("Name", JLabel.CENTER);
                Drinks_lblName.setFont(fontHeader);
                Drinks_lblName.setOpaque(true);
                Drinks_lblName.setForeground(Color.WHITE);
                Drinks_lblName.setBackground(new Color(43, 122, 120));
            Drinks_lblPrice = new JLabel("Price", JLabel.CENTER);
                Drinks_lblPrice.setFont(fontHeader);
                Drinks_lblPrice.setOpaque(true);
                Drinks_lblPrice.setForeground(Color.WHITE);
                Drinks_lblPrice.setBackground(new Color(43, 122, 120));
            Drinks_lblStock= new JLabel("Stock", JLabel.CENTER);
                Drinks_lblStock.setFont(fontHeader);
                Drinks_lblStock.setOpaque(true);
                Drinks_lblStock.setForeground(Color.WHITE);
                Drinks_lblStock.setBackground(new Color(43, 122, 120));
            Drinks_lblAction = new JLabel("Action", JLabel.CENTER);
                Drinks_lblAction.setFont(fontHeader);
                Drinks_lblAction.setOpaque(true);
                Drinks_lblAction.setForeground(Color.WHITE);
                Drinks_lblAction.setBackground(new Color(43, 122, 120));
    
            Drinks_btnFirst = new JButton("<<");
                Drinks_btnFirst.setFont(fontFooter);
                Drinks_btnFirst.setForeground(Color.WHITE);
                Drinks_btnFirst.setBackground(Color.GRAY);
                    Drinks_btnFirst.addActionListener(Drinks_navAction);
           Drinks_btnPrevious = new JButton("<");
                Drinks_btnPrevious.setFont(fontFooter);
                Drinks_btnPrevious.setForeground(Color.WHITE);
                Drinks_btnPrevious.setBackground(Color.GRAY);
                    Drinks_btnPrevious.addActionListener(Drinks_navAction);
                    
            JLabel Drinks_lblMeal = new JLabel("");
                Drinks_lblMeal.setFont(fontFooter);
                Drinks_lblMeal.setOpaque(true);
                Drinks_lblMeal.setForeground(Color.WHITE);
                Drinks_lblMeal.setBackground(Color.DARK_GRAY);
            
            Drinks_btnNext = new JButton(">");
                Drinks_btnNext.setFont(fontFooter);
                Drinks_btnNext.setForeground(Color.WHITE);
                Drinks_btnNext.setBackground(Color.GRAY);
                    Drinks_btnNext.addActionListener(Drinks_navAction);
            Drinks_btnLast = new JButton(">>");
                Drinks_btnLast.setFont(fontFooter);
                Drinks_btnLast.setForeground(Color.WHITE);
                Drinks_btnLast.setBackground(Color.GRAY);
                    Drinks_btnLast.addActionListener(Drinks_navAction);
                    
            Drinks_btnAdd = new JButton("ADD");
                Drinks_btnAdd.setFont(fontFooter);
                Drinks_btnAdd.setForeground(Color.WHITE);
                Drinks_btnAdd.setBackground(new Color(43, 122, 120));
                    Drinks_btnAdd.addActionListener(new Drinks_AddAction());
            
            Drinks_pnlCenter = new JPanel(new GridLayout(page_size + 3,5));
            Drinks_pnlSouth = new JPanel(new GridLayout(1,2));
            
            // Center Panel: row 1
            Drinks_pnlCenter.add(Drinks_lbl1);
            Drinks_pnlCenter.add(Drinks_lbl2);
            Drinks_pnlCenter.add(Drinks_lblHead);
            Drinks_pnlCenter.add(Drinks_lbl4);
            Drinks_pnlCenter.add(Drinks_lbl5);
            // Center Panel: row 2
            Drinks_pnlCenter.add(Drinks_lblID);
            Drinks_pnlCenter.add(Drinks_lblName);
            Drinks_pnlCenter.add(Drinks_lblPrice);
            Drinks_pnlCenter.add(Drinks_lblStock);
            Drinks_pnlCenter.add(Drinks_lblAction);
    
            // initialize components for contact details
            Drinks_lblData = new JLabel[page_size][4];
            Drinks_pnlAction = new JPanel[page_size];
                Drinks_rbEdit = new JRadioButton[page_size];
                Drinks_rbDelete = new JRadioButton[page_size];
                Drinks_btnGo = new JButton[page_size];
            GridLayout Drinks_pnlActionLayout = new GridLayout(1,4);
            
            // intialize each item in the array, set component properties
            for(int i=0; i<page_size; i++){
                // initialize labels
                Drinks_lblData[i][0] = new JLabel();
                    Drinks_lblData[i][0].setFont(fontData);
                    Drinks_lblData[i][0].setBorder(borderAll);
                Drinks_lblData[i][1] = new JLabel();
                    Drinks_lblData[i][1].setFont(fontData);
                    Drinks_lblData[i][1].setBorder(borderAll);
                Drinks_lblData[i][2] = new JLabel();
                    Drinks_lblData[i][2].setFont(fontData);
                    Drinks_lblData[i][2].setBorder(borderAll);
                Drinks_lblData[i][3] = new JLabel();
                    Drinks_lblData[i][3].setFont(fontData);
                    Drinks_lblData[i][3].setBorder(borderAll);
                
                    // construct action panel content
                Drinks_rbEdit[i] = new JRadioButton("Edit");
                    Drinks_rbEdit[i].setFont(new Font("Calibri", Font.BOLD, 12));
                Drinks_rbDelete[i] = new JRadioButton("Delete");
                    Drinks_rbDelete[i].setFont(new Font("Calibri", Font.BOLD, 8));
                Drinks_btnGo[i] = new JButton("Go");
                    Drinks_btnGo[i].setForeground(new Color(23, 37, 42));
                    Drinks_btnGo[i].setBackground(new Color(222, 242, 241));
                    Drinks_btnGo[i].setBorder(BorderFactory.createLineBorder(new Color(43, 122, 120)));
                        // add action to go button and store index value
                        Drinks_btnGo[i].addActionListener(new Drinks_GoAction(i));
                
                // create panel and add its radio buttons and button
                Drinks_pnlAction[i] = new JPanel(Drinks_pnlActionLayout);
                    Drinks_pnlAction[i].setBorder(borderAll);
                Drinks_pnlAction[i].add(Drinks_rbEdit[i]);
                Drinks_pnlAction[i].add(Drinks_rbDelete[i]);
                Drinks_pnlAction[i].add(Drinks_btnGo[i]);
                
                // create ButtonGroup to group the radio buttons
                ButtonGroup Drinks_radioGroup = new ButtonGroup();
                Drinks_radioGroup.add(rbEdit[i]);
                Drinks_radioGroup.add(rbDelete[i]);
                
                // place the data label and action panel to the center panel
                Drinks_pnlCenter.add(Drinks_lblData[i][0]);
                Drinks_pnlCenter.add(Drinks_lblData[i][1]);
                Drinks_pnlCenter.add(Drinks_lblData[i][2]);
                Drinks_pnlCenter.add(Drinks_lblData[i][3]);
                Drinks_pnlCenter.add(Drinks_pnlAction[i]);
            }
    
            // add footer buttons for navigation
            Drinks_pnlCenter.add(Drinks_btnFirst);
            Drinks_pnlCenter.add(Drinks_btnPrevious);
            Drinks_pnlCenter.add(Drinks_btnAdd);
            Drinks_pnlCenter.add(Drinks_btnNext);
            Drinks_pnlCenter.add(Drinks_btnLast);
    
            // add panels to frame
            pnl_Coffee.add(Drinks_pnlCenter);
            pnl_Coffee.add(Drinks_pnlSouth, "South");
            // initialize contact manager
            D_mgr = new DrinksMenu();
            Drinks_updateTable();
            
            /**
              * PANEL#3: Components
              * TABLE-Sales
              * 
              */
   
            pnl_Sales = new JPanel();
                 pnl_Sales.setBounds(0, 3, 775, 557);
                 pnl_Sales.setLayout(new BorderLayout());
            pnl_Menu.add(pnl_Sales);
   
            JLabel Sales_lbl1 = new JLabel(" ", JLabel.CENTER);
                Sales_lbl1.setBackground(new Color(23, 37, 42));
                Sales_lbl1.setOpaque(true);
                pnl_Menu.add(Sales_lbl1);
                
            JLabel Sales_lblHead = new JLabel("Sales", JLabel.CENTER);
                Sales_lblHead.setFont(fontHeader);
                Sales_lblHead.setOpaque(true);
                Sales_lblHead.setForeground(Color.WHITE);
                Sales_lblHead.setBackground(new Color(23, 37, 42));
                
            JLabel Sales_lbl2 = new JLabel(" ", JLabel.CENTER);
                Sales_lbl2.setBackground(new Color(23, 37, 42));
                Sales_lbl2.setOpaque(true);
              
            JLabel Sales_lbl4 = new JLabel(" ", JLabel.CENTER);
                Sales_lbl4.setBackground(new Color(23, 37, 42));
                Sales_lbl4.setOpaque(true);
             
            Sales_lblID = new JLabel("ID", JLabel.CENTER);
                Sales_lblID.setFont(fontHeader);
                Sales_lblID.setOpaque(true);
                Sales_lblID.setForeground(Color.WHITE);
                Sales_lblID.setBackground(new Color(43, 122, 120));
                Sales_lblID.setBorder(borderAll);
      
            Sales_lblName = new JLabel("Name", JLabel.CENTER);
                Sales_lblName.setFont(fontHeader);
                Sales_lblName.setOpaque(true);
                Sales_lblName.setForeground(Color.WHITE);
                Sales_lblName.setBackground(new Color(43, 122, 120));
                Sales_lblName.setBorder(borderAll);
                
            Sales_lblPrice = new JLabel("Price", JLabel.CENTER);
                Sales_lblPrice.setFont(fontHeader);
                Sales_lblPrice.setOpaque(true);
                Sales_lblPrice.setForeground(Color.WHITE);
                Sales_lblPrice.setBackground(new Color(43, 122, 120));
                Sales_lblPrice.setBorder(borderAll);
            
            Sales_lblQTY= new JLabel("QTY", JLabel.CENTER);
                Sales_lblQTY.setFont(fontHeader);
                Sales_lblQTY.setOpaque(true);
                Sales_lblQTY.setForeground(Color.WHITE);
                Sales_lblQTY.setBackground(new Color(43, 122, 120));
                Sales_lblQTY.setBorder(borderAll);
      
            Sales_lblTotal = new JLabel("Total Sales ", JLabel.CENTER);
                Sales_lblTotal.setFont(fontHeader);
                Sales_lblTotal.setOpaque(true);
                Sales_lblTotal.setForeground(Color.WHITE);
                Sales_lblTotal.setBackground(new Color(43, 122, 120));
                Sales_lblTotal.setBorder(borderAll);
       
            Sales_pnlNorth = new JPanel(new GridLayout(2,4));
            Sales_pnlCenter = new JPanel(new GridLayout(1,1));
            Sales_pnlSouth = new JPanel(new GridLayout(1,2));
           
            SalesArea =  new JTextArea();
            SalesArea.setBorder(borderAll);
            JScrollPane Scroll = new JScrollPane (SalesArea);
            SalesArea.append("Transaction #"+Transaction);
            
            
            txt_allItemTotal = new JTextField();
                txt_allItemTotal.setBorder(borderAll);
                txt_allItemTotal.setFont(fontHeader);  
            
             // Center Panel: row 1
            Sales_pnlNorth.add(Sales_lbl1);
            Sales_pnlNorth.add(Sales_lblHead);
            Sales_pnlNorth.add(Sales_lbl2);
            Sales_pnlNorth.add(Sales_lbl4);
         
            // Center Panel: row 2
            Sales_pnlNorth.add(Sales_lblID);
            Sales_pnlNorth.add(Sales_lblName);
            Sales_pnlNorth.add(Sales_lblPrice);
            Sales_pnlNorth.add(Sales_lblQTY);  
     
            Sales_pnlCenter.add(Scroll);
            Sales_pnlSouth.add(Sales_lblTotal);
            Sales_pnlSouth.add(txt_allItemTotal);
            txt_allItemTotal.setEditable(false);
            
            pnl_Sales.add(Sales_pnlSouth,"South");
            pnl_Sales.add(Sales_pnlNorth,"North");
            pnl_Sales.add(Sales_pnlCenter);
            
        /////////////////////////////    
        ordermenu = this;
        this.loginFRAME = loginFRAME;    
        app = this;
        
        // hide current frame
        pnl_Coffee.setVisible(false);
        pnl_Sales.setVisible(false);
        // show Coffee frame
        pnl_Pastry.setVisible(true);
        
    }
    
    private class ShowCoffeeInventory implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            isMenuVisible = true;
            // hide current frame
            pnl_Pastry.setVisible(false);
            pnl_Sales.setVisible(false);
            // show Coffee frame
            pnl_Coffee.setVisible(true);
        }
    }
    
    private class ShowPastryInventory implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            isMenuVisible = false;
            // hide current frame
            pnl_Coffee.setVisible(false);
            pnl_Sales.setVisible(false);
            // show Coffee frame
            pnl_Pastry.setVisible(true);
        }
    }
    
     private class ShowSalesInventory implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            String txtPassword = JOptionPane.showInputDialog(null, "Enter Adminś Password" );

            boolean flag=true;
            int trial=0;

            if(!txtPassword.equals("admin")){
                   JOptionPane.showMessageDialog(null,"Invalid Password");
                   flag=false;
            }  
            if(!flag){
                    trial++;
                    if(trial==3){
                    System.exit(0);
                }
                return;
            }       
            if(txtPassword.equals("admin")){   
                pnl_Coffee.setVisible(false);
                pnl_Pastry.setVisible(false);
                pnl_Sales.setVisible(true);

            }
            trial=0;
             
        }
    }
    
    private class PlaceOrder implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            int id = Integer.parseInt(txt_ItemID.getText());
            qty = Integer.parseInt(spin_Qty.getText());
            double items = 0;
            if(isMenuVisible == true){//Gets ID from Coffee Inventory
                double price = D_mgr.findItemPrice(id);
                int currStock = D_mgr.findItemStock(id);
                String name = D_mgr.findItemName(id);
                
                items = price*qty; //Multiply the price and quantity inputed pass value to item
                subtotal += items; // add the items given and pass to subtotal
                txt_Subtotal.setText(subtotal+""); //print the subtotal
                
                if(qty>currStock){//make item stock to get
                    JOptionPane.showMessageDialog(app, "Not Enough Stock!");
                    return;          
                }else{ 
                    D_mgr.StockUpdate(id, qty);
                    Drinks_updateTable();
                    AreaR.append("        C-" + id + "                    " 
                    + name + "\t             " + price 
                    + "\t                " + (spin_Qty.getText()) + "\n" ); 
                }
                
                SalesArea.append("\tC-"+id
                +"                                                  "); 
                SalesArea.append(name);        
                SalesArea.append("\t                                         "
                +price); 
                SalesArea.append("\t                                                     "
                +qty + "\n");
                CreateSales();  
                
                
            }else{//Gets ID from Pastry Inventory
                double price = M_mgr.findItemPrice(id);
                int currStock = M_mgr.findItemStock(id);
                String name = M_mgr.findItemName(id);
                
                items = price*qty; //Multiply the price and quantity inputed pass value to item
                subtotal += items; // add the items given and pass to subtotal
                txt_Subtotal.setText(subtotal+""); //print the subtotal
                
                if(qty>currStock){//make item stock to get
                    JOptionPane.showMessageDialog(app, "Not Enough Stock!");
                    return;          
                }else{ 
                    M_mgr.StockUpdate(id, qty);
                    updateTable(); 
                    AreaR.append("        P-" + id + "                    " 
                    + name + "\t             " + price + "\t                " 
                    + (spin_Qty.getText())+ "\n" ); 
                }
                 
                SalesArea.append("\tP-"+id
                +"                                                  "); 
                SalesArea.append(name);        
                SalesArea.append("\t                                         "
                +price); 
                SalesArea.append("\t                                                     "
                +qty + "\n");
                CreateSales(); 
            }
            
          
        }
    }

    private class ClearOrder implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            JButton source = (JButton)ae.getSource();
            if(source ==  btn_ClearOrd ){
                 // clear the inputed Id and Qty
                 spin_Qty.setText("");
                 txt_ItemID.setText("");
  
            }
        }
    }
    
    private class ClearFields implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            JButton source = (JButton)ae.getSource();
            if(source ==  btn_Clear ){
                //Clear TextArea
                AreaR.setText("");
                //Clear Textfield
                txt_Subtotal.setText("");
                txt_Discount.setText("");
                txt_Total.setText("");
                txt_Change.setText("");
                txt_Cash.setText("");
                //Set Default TextArea
                txt_Output.setText("" + 
                      "\r\n         Subtotal" + 
                      "\r\n         Discount" + 
                      "\r\n         Total" + 
                      "\r\n-----------------------------------------------------------------------------------------" + 
                      "\r\n         Cash" + 
                      "\r\n         Change");
            }
        }
    }

    private class GiveCash implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            double cash = Double.parseDouble(txt_Cash.getText());
            double change = 0;
            discount = Double.parseDouble(txt_Discount.getText());
            amountdis = (subtotal-(subtotal*discount/100));
            
            //Condition to determine the change or money inputed    
            if (cash > amountdis) {
                 change = cash - amountdis;
                 //set change and round decimal place to nearest ones               
                 txt_Change.setText(Math.round(change* 10)/10.0+"");
    
            }else if (cash < amountdis) { 
                 JOptionPane.showMessageDialog(app, "Not Enough Money!");
                 return;
            }else{ 
               txt_Change.setText("0.0");
            }
            txt_Total.setText(amountdis+"");
            
              txt_Output.setText("" + 
                     "\r\n         Subtotal\t\t\t" + subtotal +
                     "\r\n         Discount\t\t\t" + txt_Discount.getText() +"%"+
                     "\r\n         Total\t\t\t" + amountdis +
                     "\r\n-----------------------------------------------------------------------------------------" 
                     + 
                     "\r\n         Cash\t\t\t" + cash +
                     "\r\n         Change\t\t\t" + txt_Change.getText());
               CreateSales();
  
            SalesArea.append("Transaction #"+Transaction 
            + " ------------------------------------------------------------------ Total w/ "
            + txt_Discount.getText() 
            +"% Discount: "+amountdis + "\n");
            Transaction++;
            
            subtotal = 0;      
            amountdis = 0; 
            
            SalesArea.append("\nTransaction #"+Transaction);
        }
    } 
    
    double totalSales = 0;
    public void CreateSales(){
         totalSales += amountdis;
         txt_allItemTotal.setText(totalSales+"");
         
  
    }
    
    /**
      * TABLE-Pastry
      *  Functions
      */
    // code for add button action
    private class AddAction implements ActionListener{
        // define the required method
        public void actionPerformed(ActionEvent ae){
            // hide current frame
            app.setVisible(false);
            // show contact form frame
            showMealFormFrame(null);
        }
    }

    // method to display contact form frame for add or edit
    // add when current parameter is null, edit otherwise
    public void showMealFormFrame(Main_ItemMenu current){
        // check if contact form is already existing
        if(mealFormFrame==null){
            mealFormFrame = new MealFormFrame(app, M_mgr);
            mealFormFrame.setBounds(650, 200, 350, 400);
            mealFormFrame.setResizable(false);
            mealFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        // set frame title
        if(current==null){
            mealFormFrame.setTitle("Add Pastry");
        }else{
           mealFormFrame.setTitle( "Edit Pastry");
        }
        mealFormFrame.setVisible(true);
        mealFormFrame.setMain(current);
    }

    // function to check which buttons must be clickable
    public void checkNavButtons(){
        // disable all navigation buttons first
        btnFirst.setEnabled(false);
        btnPrevious.setEnabled(false);
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
        // check if not yet on last page
        if(page_current<page_limit-1){
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }
        // check if not yet on first page
        if(page_current>0){
            btnPrevious.setEnabled(true);
            btnFirst.setEnabled(true);
        }
    }

    // update display table
    public void updateTable(){
        // determine page_limit
        page_limit = M_mgr.size()/page_size;
        // check if excess then add 1
        if(M_mgr.size()%page_size!=0){
            page_limit++;
        }
        // check if valid page value
        // check if current page is excess from limit
        if(page_current >= page_limit){
            page_current = page_limit - 1;
        }
        // check if page value is not negative
        if(page_current < 0){
            page_current = 0;
        }
        // disable all go buttons, clear labels and remove images first
        for(int i=0; i<page_size; i++){
            lblData[i][0].setText("");
            lblData[i][1].setText("");
            lblData[i][2].setText("");
            lblData[i][3].setText("");
            btnGo[i].setEnabled(false);
        }
        // identify the index of the first contact to be displayed
        int page_index_start = page_current*page_size;
        // iterate for rows to be displayed
        for(int i=0; i<page_size; i++){
            // compute index by adding i to page index start
            int index = page_index_start + i;
            // determine if index is over the size of contact manager list, if over stop update
            if(index >= M_mgr.size()){
                break;
            }
            
            Main_ItemMenu main = M_mgr.get(index);
            // update data displayed on labels
            lblData[i][0].setText(main.getItemID() + "");
            lblData[i][1].setText(main.getItemName());
            lblData[i][2].setText(main.getItemPrice() + "");
            lblData[i][3].setText(main.getItemStock()+ "");
            // enable the go button for the current row
            btnGo[i].setEnabled(true);
        }
        // check if navigation buttons must be enabled or disabled
        checkNavButtons();
    }

    // apply functionality for navigation buttons
    private class NavigationAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            // get which nav button is clicked
            JButton source = (JButton) ae.getSource();
            if(source == btnFirst){
                page_current = 0;
            }else if(source == btnPrevious){
                page_current--;
            }else if(source == btnNext){
                page_current++;
            }else{ 
                page_current = page_limit - 1;
            }
            // update the data displayed on update
            updateTable();
        }
    }

    // apply functionality for go button
    private class GoAction implements ActionListener{
        // save index of contact
        private int index;
        // add constructor to identify to save the index of the contact from manager
        public GoAction(int index){
            this.index = index;
        }

        public void actionPerformed(ActionEvent ae){
            // get index relative to current page being displayed
            int current_item_index = page_current * page_size + index;
            // get current contact selected
           Main_ItemMenu current_main = M_mgr.get(current_item_index);
            // determine action based on selected radio button
            if(rbDelete[current_item_index].isSelected()){
                // delete using name
                M_mgr.delete(current_main.getItemID());
                // update table for changes
                updateTable();
            }else if(rbEdit[current_item_index].isSelected()){
                // hide main frame
                app.setVisible(false);
                // edit current contact
                showMealFormFrame(current_main);
            }
        }
    }
     
    
    /**
      * TABLE-Coffee
      *  Functions
      */
    // code for add button action
    private class Drinks_AddAction implements ActionListener{
        // define the required method
        public void actionPerformed(ActionEvent ae){
            // hide current frame
            app.setVisible(false);
            // show contact form frame
            showDrinksFormFrame(null);
        }
    }

    // method to display contact form frame for add or edit
    // add when current parameter is null, edit otherwise
    public void showDrinksFormFrame(Main_ItemMenu current){
        // check if contact form is already existing
        if(drinksFormFrame==null){
           drinksFormFrame = new DrinksFormFrame(app, D_mgr);
           drinksFormFrame.setBounds(650, 200, 350, 400);
           drinksFormFrame.setResizable(false);
           drinksFormFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        // set frame title
        if(current==null){
           drinksFormFrame.setTitle("Add Coffee");
        }else{
           drinksFormFrame.setTitle( "Edit Coffee");
        }
        drinksFormFrame.setVisible(true);
        drinksFormFrame.setMain(current);
    }

    // function to check which buttons must be clickable
    public void Drinks_checkNavButtons(){
        // disable all navigation buttons first
        Drinks_btnFirst.setEnabled(false);
        Drinks_btnPrevious.setEnabled(false);
        Drinks_btnNext.setEnabled(false);
        Drinks_btnLast.setEnabled(false);
        // check if not yet on last page
        if(Drinks_page_current<Drinks_page_limit-1){
            Drinks_btnNext.setEnabled(true);
            Drinks_btnLast.setEnabled(true);
        }
        // check if not yet on first page
        if(Drinks_page_current>0){
            Drinks_btnPrevious.setEnabled(true);
            Drinks_btnFirst.setEnabled(true);
        }
    }

    // update display table
    public void Drinks_updateTable(){
        // determine page_limit
        Drinks_page_limit = D_mgr.size()/Drinks_page_size;
        // check if excess then add 1
        if(D_mgr.size()%Drinks_page_size!=0){
            Drinks_page_limit++;
        }
        // check if valid page value
        // check if current page is excess from limit
        if(Drinks_page_current >= Drinks_page_limit){
            Drinks_page_current = Drinks_page_limit - 1;
        }
        // check if page value is not negative
        if(Drinks_page_current < 0){
            Drinks_page_current = 0;
        }
        // disable all go buttons, clear labels and remove images first
        for(int i=0; i<page_size; i++){
            Drinks_lblData[i][0].setText("");
            Drinks_lblData[i][1].setText("");
            Drinks_lblData[i][2].setText("");
            Drinks_lblData[i][3].setText("");
            Drinks_btnGo[i].setEnabled(false);
        }
        // identify the index of the first contact to be displayed
        int Drinks_page_index_start = Drinks_page_current*Drinks_page_size;
        // iterate for rows to be displayed
        for(int i=0; i<Drinks_page_size; i++){
            // compute index by adding i to page index start
            int index = Drinks_page_index_start + i;
            
            if(index >= D_mgr.size()){
                break;
            }
            
            Main_ItemMenu main = D_mgr.get(index);
            // update data displayed on labels
            Drinks_lblData[i][0].setText(main.getItemID() + "");
            Drinks_lblData[i][1].setText(main.getItemName());
            Drinks_lblData[i][2].setText(main.getItemPrice() + "");
            Drinks_lblData[i][3].setText(main.getItemStock() + "");
            // enable the go button for the current row
            Drinks_btnGo[i].setEnabled(true);
        }
        // check if navigation buttons must be enabled or disabled
        Drinks_checkNavButtons();
    }

    // apply functionality for navigation buttons
    private class Drinks_NavigationAction implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            // get which nav button is clicked
            JButton source = (JButton) ae.getSource();
            if(source == Drinks_btnFirst){
                Drinks_page_current = 0;
            }else if(source == Drinks_btnPrevious){
                Drinks_page_current--;
            }else if(source == Drinks_btnNext){
                Drinks_page_current++;
            }else{ 
                Drinks_page_current = Drinks_page_limit - 1;
            }
            // update the data displayed on update
            Drinks_updateTable();
        }
    }

    // apply functionality for go button
    private class Drinks_GoAction implements ActionListener{
        // save index of contact
        private int index;
        // add constructor to identify to save the index of the contact from manager
        public Drinks_GoAction(int index){
            this.index = index;
        }

        public void actionPerformed(ActionEvent ae){
            // get index relative to current page being displayed
            int Drinks_current_item_index = Drinks_page_current * Drinks_page_size + index;
            // get current contact selected
            Main_ItemMenu Drinks_current_main = D_mgr.get(Drinks_current_item_index);
            // determine action based on selected radio button
            if(Drinks_rbDelete[Drinks_current_item_index].isSelected()){
                // delete using name
                D_mgr.delete(Drinks_current_main.getItemID());
                // update table for changes
                Drinks_updateTable();
            }else if(Drinks_rbEdit[Drinks_current_item_index].isSelected()){
                // hide main frame
                app.setVisible(false);
                // edit current contact
                showDrinksFormFrame(Drinks_current_main);
            }
        }
    } 
    
    
    private class LogOut implements ActionListener{  
          public void actionPerformed(ActionEvent ae){
                   mainFrame = new LoginFRAME();
                   mainFrame.setResizable(false);
                   mainFrame.setVisible(true);
                   app.setVisible(false); 
                   
          }
    }

}