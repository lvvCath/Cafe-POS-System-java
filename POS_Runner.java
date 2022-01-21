/**
 * POS Main Interface.
 *
 * @author (Code Z)
 * @version (2020)
 */
import javax.swing.JFrame;
public class POS_Runner{
    public static void main(String[]args){
        JFrame app = new LoginFRAME();
        app.setTitle("Login");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
