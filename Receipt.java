import javax.swing.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Receipt here.
 *
 * @author Ling Qi, Gerasimos Copoulos
 * @version Oct24,2020
 */
public class Receipt
{
    // instance variables - replace the example below with your own
    //private Request rq;
    private JFrame receipt;
    //private JPanel status;
    public static ATMSystem ThisATM;
    /**
     * Constructor for objects of class Receipt
     */
    public Receipt(String msg, Account acct){
        //this.rq = req;
        this.receipt = new JFrame("Transaction Receipt");
        receipt.setSize(400, 300);
        receipt.setResizable(false);
        receipt.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // transaction info
        JPanel status = new JPanel();
        status.setSize(new Dimension(400, 200));
        JLabel completed = new JLabel("Transaction Completed!");
        completed.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        //completed.setAlignmentX(Component.CENTER_ALIGNMENT);
        completed.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        JLabel info = new JLabel(msg);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        JLabel time = new JLabel("Transaction time: " + timeStamp);
        status.add(completed);
        status.add(info);
        status.add(time);
        status.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
        //status.setBorder(BorderFactory.createLineBorder(Color.black));
        status.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(status);
   
        // account info
        JPanel acctInfo = new JPanel();
        JLabel summary = new JLabel("Account Summary:");
        summary.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        summary.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        JLabel acctNum = new JLabel("Account number: " + acct.getAccountNumber());
        JLabel acctBalance = new JLabel("Current balance: " + acct.getBalance());
        acctInfo.add(summary);
        acctInfo.add(acctNum);
        acctInfo.add(acctBalance);
        acctInfo.setLayout(new BoxLayout(acctInfo, BoxLayout.Y_AXIS));
        acctInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        acctInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        mainPanel.add(acctInfo);
        
        // buttons
        JPanel buttons = new JPanel();
        JButton toMainBtn = new JButton("MAIN MENU");
        toMainBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ThisATM.showMenu();
                receipt.dispatchEvent(new WindowEvent(receipt, WindowEvent.WINDOW_CLOSING));
            }  
        });
        JButton exitBtn = new JButton("EXIT ATM");
        exitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }  
        });
        buttons.add(toMainBtn);
        buttons.add(exitBtn);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        mainPanel.add(buttons);
        
        // layout overall
        receipt.add(mainPanel);
        receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        receipt.setVisible(true);
    }
    
    //public void display(){
    //    receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //    receipt.setVisible(true);
    //}

}
