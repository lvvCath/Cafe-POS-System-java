import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class LoginFRAME extends JFrame {
    private JPanel contentPane;
    private JPasswordField txtPasswordField;
    private JTextField txtUsername;
    private int trial=0;
    private JFrame app, mainFrame;
    /**
     * Create the frame.
     */
    public LoginFRAME() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, 950, 540);
        setResizable(false);
        
        contentPane = new JPanel();
            contentPane.setBackground(new Color(222, 242, 241));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel("");
            lblNewLabel_3.setIcon(new ImageIcon("icon\\Grey_Box_Photography_Logo-removebg-preview (2).png"));
            lblNewLabel_3.setBounds(105, 122, 250, 250);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel = new JLabel("New label");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setIcon(new ImageIcon("icon\\b1g.jpg"));
            lblNewLabel.setBounds(0, 0, 485, 502);
        contentPane.add(lblNewLabel);
        
        JPanel panel = new JPanel();
            panel.setBackground(new Color(43, 122, 120));
            panel.setBounds(482, 0, 455, 76);
            panel.setLayout(null);
        contentPane.add(panel);
        
            JLabel lblNewLabel_1 = new JLabel("LogIn");
                lblNewLabel_1.setBounds(38, 0, 384, 76);
                lblNewLabel_1.setForeground(new Color(255, 255, 255));
                lblNewLabel_1.setBackground(new Color(0, 102, 102));
                lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 30));
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("User Name");
            lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 22));
            lblNewLabel_2.setBounds(545, 146, 145, 37);
        contentPane.add(lblNewLabel_2);
        
        txtUsername = new JTextField();
            txtUsername.setBounds(544, 181, 345, 45);
            txtUsername.setColumns(10);
        contentPane.add(txtUsername);
        
        JLabel lblPassword = new JLabel("Password");
            lblPassword.setFont(new Font("Cambria", Font.BOLD, 22));
            lblPassword.setBounds(545, 239, 145, 37);
        contentPane.add(lblPassword);
        
        txtPasswordField = new JPasswordField();
            txtPasswordField.setBounds(545, 278, 345, 51);
        contentPane.add(txtPasswordField);
        
        Button btnLogin = new Button("Login");
            btnLogin.setBackground(new Color(43, 122, 120));
            btnLogin.setForeground(new Color(255, 255, 255));
            btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
            btnLogin.setBounds(636, 364, 154, 51);
        btnLogin.addActionListener(new LoginAction());
        contentPane.add(btnLogin);
        
        
        app = this;
        
    }
    private class LoginAction implements ActionListener{  
         public void actionPerformed(ActionEvent ae){
            String Username = txtUsername.getText();
            String Password= new String(txtPasswordField.getText());
            boolean flag=true;
            
             if(!Username.equals("admin")){
                JOptionPane.showMessageDialog(null, "Invalid Username");
                flag=false;
                 
            }
            if(!Password.equals("admin")){
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
            if(Username.equals("admin") && Password.equals("admin")){   
                JFrame admin_interface = new OrderMenuFRAME(app);
                admin_interface.setVisible(true);
                admin_interface.setResizable(false);
                app.setVisible(false);
            }

            
            txtUsername.setText(" ");
            txtPasswordField.setText("");
            trial=0;
         }
     }
    
}
