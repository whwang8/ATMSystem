import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * A class containing the menus and data for an ATM.
 *
 * @author Gerasimos Copoulos
 * @version 1
 */
public class ATMSystem extends JPanel
                             implements ActionListener 
{
    // instance variables 
    public Account ActiveAccount;
    public String ActiveAccountNum;
    public char[] ActiveAccountPIN;
    public boolean activeInquiry = false;
    
    public Account FriendAccount;
    public String FriendAccountNum;
    
    String acctnumFieldString = new String("JTextField");
    String pinFieldString = new String("JPasswordField");
    String WFieldString = new String("WField");
    String DFieldString = new String("DField");
    String TFieldString = new String("TField");
    String T2FieldString = new String("T2Field");
    
    protected JLabel actionLabel;
    protected JFrame InfoFrame;
    protected JFrame MainMenuFrame;
    protected JTextField acctnumField;
    protected JPasswordField pinField;
    
    protected JFrame WFrame;
    protected JFrame DFrame;
    protected JFrame TFrame;
    
    protected JTextField WField;
    protected JTextField DField;
    protected JTextField TField;
    protected JTextField T2Field;
    
    String ExampleAcct = new String("12");
    //String ExamplePINString = new String("34");
    char[] ExamplePIN = new char[] {'3','4'};
    
    Account[] AccountDatabase = new Account[6];
    
   

    /**
     * Constructor for objects of class ATMSystem
     */
    public ATMSystem()
    {
        
        
    }
    
    /**
     * Constructor for ATMSystem objects which species active and 
     * friend accounts on initialization.
     * 
     * @param ACT1  The active account used for this ATM instance
     * @param ACT2 The account used to represent a friend's account in this
     * ATM instance.
     */
    
    public ATMSystem(Account ACT1, Account ACT2){
        this.ActiveAccount = ACT1;
        this.FriendAccount = ACT2;
        this.ActiveAccountNum = ActiveAccount.getAccountNumber();
        this.ActiveAccountPIN = ActiveAccount.getPIN();
        this.FriendAccountNum = FriendAccount.getAccountNumber();
        WFrame = new JFrame();
        this.WFrame.setVisible(false);
        DFrame = new JFrame();
        this.DFrame.setVisible(false);
        TFrame = new JFrame();
        this.TFrame.setVisible(false);
        this.activeInquiry = false;
    }

    /**
     * Gets the account currently used by this ATMObject.
     *
     * @return    The active account object.
     */
    public Account GetActiveAccount()
    {   
        return this.ActiveAccount;
    }
    
    /**
     *  Displays the Main Menu of the ATM.
     *
     * @return    No return, just displays a GUI.
     */
    
    public void showMenu(){
      MainMenuFrame = new JFrame();
      MainMenuFrame.setLayout(new FlowLayout());
      MainMenuFrame.setLocationRelativeTo(null);
      
      JButton b1 = new JButton("Withdraw");
      JButton b2 = new JButton("Deposit");
      JButton b3 = new JButton("Transfer");
      JButton b4 = new JButton("Inquiry");
      JButton ExitB = new JButton("Exit");
      b1.setToolTipText("Click here to withdraw funds.");
      b2.setToolTipText("Click here to deposit funds.");
      b3.setToolTipText("Click here to transfer funds between accounts.");
      b4.setToolTipText("Click here to check the account balance.");
      ExitB.setToolTipText("Click here to exit the ATM program.");
      
      b1.addActionListener(event -> this.actionPerformedW());
      b2.addActionListener(event -> this.actionPerformedD());
      b3.addActionListener(event -> this.actionPerformedT());
      b4.addActionListener(event -> this.actionPerformedI());
      ExitB.addActionListener(event -> this.actionPerformedExit());
      ExitB.setAlignmentX(Component.CENTER_ALIGNMENT);
      ExitB.setAlignmentY(Component.BOTTOM_ALIGNMENT);

      MainMenuFrame.add(b1);
      MainMenuFrame.add(b2);
      MainMenuFrame.add(b3);
      MainMenuFrame.add(b4);
      MainMenuFrame.add(ExitB);
      MainMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      MainMenuFrame.pack();
      MainMenuFrame.setVisible(true);
    }
    
    public void actionPerformedExit(){
        System.exit(0);
    }
    
    public void actionPerformedW(){
        if (!(DFrame.isVisible() || WFrame.isVisible() || TFrame.isVisible() || activeInquiry)){
            InfoFrame.setVisible(false);
            WFrame = new JFrame();
            WFrame.setLayout(new BorderLayout());
            WFrame.setLocationRelativeTo(null);

            //Create a regular text field.
            this.WField = new JTextField(10);
            WField.setActionCommand(WFieldString);
            WField.addActionListener(this);
      
            //Create some labels for the fields.
            JLabel WFieldLabel = new JLabel("Withdrawal Amount:");
            WFieldLabel.setLabelFor(WField);
 
            //Create a label to put messages during an action event.
            this.actionLabel = new JLabel("Enter the desired amount and press Enter.  Close window to cancel.");
            actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
      
            JPanel WPane = new JPanel();
            GridBagLayout Wgridbag = new GridBagLayout();
            GridBagConstraints Wc = new GridBagConstraints();
 
            WPane.setLayout(Wgridbag);
 
            JLabel[] labels = {WFieldLabel};
            JTextField[] textFields = {WField};
            addLabelTextRows(labels, textFields, Wgridbag, WPane);
 
            Wc.gridwidth = GridBagConstraints.REMAINDER; //last
            Wc.anchor = GridBagConstraints.WEST;
            Wc.weightx = 1.0;
            WPane.add(actionLabel, Wc);
            WPane.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Withdrawal"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
      
            WFrame.add(WPane, BorderLayout.PAGE_START);
      
            WFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            WFrame.pack();
            WFrame.setVisible(true);
      }
      else {
            JFrame NewFrame = new JFrame("Transaction already active.");
            NewFrame.setLayout(new BorderLayout());
            JOptionPane.showMessageDialog(NewFrame,"You cannot attempt multiple transactions at once.");
      }
    }
    
    public void actionPerformedD(){
        if (!(DFrame.isVisible() || WFrame.isVisible() || TFrame.isVisible() || activeInquiry)){
            InfoFrame.setVisible(false);
            DFrame = new JFrame();
            DFrame.setLayout(new BorderLayout());
            DFrame.setLocationRelativeTo(null);

            //Create a regular text field.
            this.DField = new JTextField(10);
            DField.setActionCommand(DFieldString);
            DField.addActionListener(this);
      
            //Create some labels for the fields.
            JLabel DFieldLabel = new JLabel("Deposit Amount:");
            DFieldLabel.setLabelFor(DField);
 
            //Create a label to put messages during an action event.
            this.actionLabel = new JLabel("Enter the desired amount and press Enter.  Close window to cancel.");
            actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
      
            JPanel DPane = new JPanel();
                GridBagLayout Dgridbag = new GridBagLayout();
                GridBagConstraints Dc = new GridBagConstraints();
 
            DPane.setLayout(Dgridbag);
 
            JLabel[] labels = {DFieldLabel};
            JTextField[] textFields = {DField};
            addLabelTextRows(labels, textFields, Dgridbag, DPane);
 
            Dc.gridwidth = GridBagConstraints.REMAINDER; //last
            Dc.anchor = GridBagConstraints.WEST;
            Dc.weightx = 1.0;
            DPane.add(actionLabel, Dc);
            DPane.setBorder(
                 BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Deposit"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));
      
            DFrame.add(DPane, BorderLayout.PAGE_START);
      
            DFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            DFrame.pack();
            DFrame.setVisible(true);
    }
    else {
            JFrame NewFrame = new JFrame("Transaction already active.");
            NewFrame.setLayout(new BorderLayout());
            JOptionPane.showMessageDialog(NewFrame,"You cannot attempt multiple transactions at once.");
      }
    }
    
    public void actionPerformedT(){
         if (!(DFrame.isVisible() || WFrame.isVisible() || TFrame.isVisible() || activeInquiry)){
             InfoFrame.setVisible(false);
             TFrame = new JFrame();
             TFrame.setLayout(new BorderLayout());
             TFrame.setLocationRelativeTo(null);

             //Create a regular text field.
             this.TField = new JTextField(10);
             TField.setActionCommand(TFieldString);
             TField.addActionListener(this);
      
             //Create a regular text field.
             this.T2Field = new JTextField(10);
             T2Field.setActionCommand(T2FieldString);
             T2Field.addActionListener(this);
      
             //Create some labels for the fields.
             JLabel TFieldLabel = new JLabel("Destination Account #:");
             TFieldLabel.setLabelFor(TField);
             JLabel T2FieldLabel = new JLabel("Transfer Amount:");
             T2FieldLabel.setLabelFor(T2Field);
 
             //Create a label to put messages during an action event.
             this.actionLabel = new JLabel("Enter a valid destination account and transfer amount and press Enter.  Close window to cancel.");
             actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
      
             JPanel WPane = new JPanel();
             GridBagLayout Wgridbag = new GridBagLayout();
             GridBagConstraints Wc = new GridBagConstraints();
 
             WPane.setLayout(Wgridbag);
 
             JLabel[] labels = {TFieldLabel, T2FieldLabel};
             JTextField[] textFields = {TField, T2Field};
             addLabelTextRows(labels, textFields, Wgridbag, WPane);
 
             Wc.gridwidth = GridBagConstraints.REMAINDER; //last
             Wc.anchor = GridBagConstraints.WEST;
             Wc.weightx = 1.0;
             WPane.add(actionLabel, Wc);
             WPane.setBorder(
                BorderFactory.createCompoundBorder(
                              BorderFactory.createTitledBorder("Transfer"),
                              BorderFactory.createEmptyBorder(5,5,5,5)));
      
             TFrame.add(WPane, BorderLayout.PAGE_START);
      
             TFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             TFrame.pack();
             TFrame.setVisible(true);
    }
    else {
            JFrame NewFrame = new JFrame("Transaction already active.");
            NewFrame.setLayout(new BorderLayout());
            JOptionPane.showMessageDialog(NewFrame,"You cannot attempt multiple transactions at once.");
      }
    }
    
    public void actionPerformedI(){
        this.activeInquiry = true;
        Request IRequest = new Request(ActiveAccount);
        Inquiry thisInquiry = new Inquiry(IRequest);
        thisInquiry.process();
        this.activeInquiry = false;
    }
    
    /**
     *  Displays a prompt for the user to input their PIN and ACCT #.
     *  
     *  @return No return, just checks the inputs and moves on.
     */
    
    public void InfoPrompt(){
      InfoFrame = new JFrame();
      InfoFrame.setLayout(new BorderLayout());
      InfoFrame.setLocationRelativeTo(null);

      //Create a regular text field.
      this.acctnumField = new JTextField(10);
      acctnumField.setActionCommand(acctnumFieldString);
      acctnumField.addActionListener(this);
 
      //Create a password field.
      this.pinField = new JPasswordField(10);
      pinField.setActionCommand(pinFieldString);
      pinField.addActionListener(this);
      
      //Create some labels for the fields.
      JLabel acctnumFieldLabel = new JLabel("Account #:");
      acctnumFieldLabel.setLabelFor(acctnumField);
      JLabel pinFieldLabel = new JLabel("PIN #:");
      pinFieldLabel.setLabelFor(pinField);
 
      //Create a label to put messages during an action event.
      this.actionLabel = new JLabel("Enter your information and press Enter.");
      actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
      
      JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
 
      textControlsPane.setLayout(gridbag);
 
      JLabel[] labels = {acctnumFieldLabel, pinFieldLabel};
      JTextField[] textFields = {acctnumField, pinField};
      addLabelTextRows(labels, textFields, gridbag, textControlsPane);
 
      c.gridwidth = GridBagConstraints.REMAINDER; //last
      c.anchor = GridBagConstraints.WEST;
      c.weightx = 1.0;
      textControlsPane.add(actionLabel, c);
      textControlsPane.setBorder(
              BorderFactory.createCompoundBorder(
                              BorderFactory.createTitledBorder("ATM Login"),
                              BorderFactory.createEmptyBorder(5,5,5,5)));
      
      InfoFrame.add(textControlsPane, BorderLayout.PAGE_START);
      
      //InfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      InfoFrame.pack();
      InfoFrame.setVisible(true);
    }
    
    /**
     *   Formats an input field to have text and labels associated
     *   with that text.
     *   
     *   @return No return, just helps formatting.
     */
    
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;
 
        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; 
            c.fill = GridBagConstraints.NONE;      
            c.weightx = 0.0;                       
            container.add(labels[i], c);
            c.gridwidth = GridBagConstraints.REMAINDER;     
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }
    
    public void DisplayInfo(){
        JFrame TextFrame = new JFrame("Account Login Information");
        TextFrame.setLayout(new BorderLayout());
        String A1PINString = new String(ActiveAccountPIN);
        JOptionPane.showMessageDialog
        (TextFrame,"Acct #: " + ActiveAccountNum + "\nPIN #: " + A1PINString + "\n Friend's Acct #:" + FriendAccountNum);
        this.InfoPrompt();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (acctnumFieldString.equals(e.getActionCommand())) {
            String AcctNumInput = acctnumField.getText();
            char[] PINInput = pinField.getPassword();
            if (AcctNumInput.equals(ActiveAccountNum) && Arrays.equals(PINInput, ActiveAccountPIN)){
                InfoFrame.dispose();
                this.showMenu();
                InfoFrame.dispatchEvent(new WindowEvent(InfoFrame, WindowEvent.WINDOW_CLOSING));
            }
            else {
                this.actionLabel.setText("That information is not valid.");
            }
        } else if (pinFieldString.equals(e.getActionCommand())) {
            String AcctNumInput = acctnumField.getText();
            char[] PINInput = pinField.getPassword();
            if (AcctNumInput.equals(ActiveAccountNum) && Arrays.equals(PINInput, ActiveAccountPIN)){
                InfoFrame.dispose();
                this.showMenu();
                InfoFrame.dispatchEvent(new WindowEvent(InfoFrame, WindowEvent.WINDOW_CLOSING));
            }
            else {
                this.actionLabel.setText("That information is not valid.");
            }
        } else if (WFieldString.equals(e.getActionCommand())) {      
            try {
                String WFieldInput = WField.getText();
                Request WRequest = new Request(ActiveAccount, Integer.parseInt(WFieldInput));
                Withdrawal thisWithdrawal = new Withdrawal(WRequest);
                thisWithdrawal.process();
                WFrame.dispatchEvent(new WindowEvent(WFrame, WindowEvent.WINDOW_CLOSING));
                MainMenuFrame.dispatchEvent(new WindowEvent(MainMenuFrame, WindowEvent.WINDOW_CLOSING));
            }
            catch(NumberFormatException excep){
                this.actionLabel.setText("That is not a valid amount.");
            }
        }  
        else if (DFieldString.equals(e.getActionCommand())) {
            try {
                String DFieldInput = DField.getText();
                Request DRequest = new Request(ActiveAccount, Integer.parseInt(DFieldInput));
                Deposit thisDeposit = new Deposit(DRequest);
                thisDeposit.process();
                DFrame.dispatchEvent(new WindowEvent(DFrame, WindowEvent.WINDOW_CLOSING));
                MainMenuFrame.dispatchEvent(new WindowEvent(MainMenuFrame, WindowEvent.WINDOW_CLOSING));
            }
            catch(NumberFormatException excep){
                this.actionLabel.setText("That is not a valid amount.");
            }
        }
        else if (TFieldString.equals(e.getActionCommand())) {
            try {
                String TFieldInput = TField.getText();
                if (TFieldInput.equals(FriendAccountNum)){
                    String T2FieldInput = T2Field.getText();
                    Request TRequest = new Request(ActiveAccount, FriendAccount, Integer.parseInt(T2FieldInput));
                    Transfer thisTransfer = new Transfer(TRequest);
                    thisTransfer.process();
                    TFrame.dispatchEvent(new WindowEvent(TFrame, WindowEvent.WINDOW_CLOSING));
                    MainMenuFrame.dispatchEvent(new WindowEvent(MainMenuFrame, WindowEvent.WINDOW_CLOSING));
                }
                else {
                    this.actionLabel.setText("That destination account is invalid.");
                }
            }
            catch(NumberFormatException excep){
                this.actionLabel.setText("That is not a valid amount.");
            }
        }
        else if (T2FieldString.equals(e.getActionCommand())) {
            try {
                String TFieldInput = TField.getText();
                if (TFieldInput.equals(FriendAccountNum)){
                    String T2FieldInput = T2Field.getText();
                    Request TRequest = new Request(ActiveAccount, FriendAccount, Integer.parseInt(T2FieldInput));
                    Transfer thisTransfer = new Transfer(TRequest);
                    thisTransfer.process();
                    TFrame.dispatchEvent(new WindowEvent(TFrame, WindowEvent.WINDOW_CLOSING));
                    MainMenuFrame.dispatchEvent(new WindowEvent(MainMenuFrame, WindowEvent.WINDOW_CLOSING));
                }
                else {
                    this.actionLabel.setText("That destination account is invalid.");
                }
            }
            catch(NumberFormatException excep){
                this.actionLabel.setText("That is not a valid amount.");
            }
        }
    }
    
    public static void main(String[] args){
        char[] CA1PIN = new char[] {'2', '4', '2','4'};
        CheckingsAccount CA1 = new CheckingsAccount(2000, "123", CA1PIN);
        char[] FA1PIN = new char[] {'5','3','1','5'};
        CheckingsAccount FA1 = new CheckingsAccount(1000, "876", FA1PIN);
        ATMSystem ATM1 = new ATMSystem(CA1, FA1);
        Receipt.ThisATM = ATM1;
        ATM1.DisplayInfo();
    }
}
