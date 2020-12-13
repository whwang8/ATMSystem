import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class Inquiry here.
 *
 * @author Ling Qi, Gerasimos Copoulos
 * @version Oct23,2020
 */
public class Inquiry extends Transaction
{
    Request rq;
    /**
     * Constructor for objects of class Inquiry
     */
    public Inquiry(Request req){
        this.rq = req;
    }

    
    public boolean isValidRequest(){
        return true;
    }
    
    /**
     * printReceipt: looks up the inquired balance and displays it
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void printReceipt() {
        Account curAcct = rq.getFromAccount();
        int thisbalance = curAcct.getBalance();
        JFrame TextFrame = new JFrame("Account Inquiry");
        TextFrame.setLayout(new BorderLayout());
        String thistype = curAcct.getAccountType();
        JOptionPane.showMessageDialog(TextFrame,"This is a " + thistype + " account with a balance of " + thisbalance + " dollars.");
    }
}
