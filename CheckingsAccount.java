
/**
 * Write a description of class CheckingsAccount here.
 *
 * @author William Hwang
 * @version (a version number or a date)
 */
public class CheckingsAccount extends Account
{
    // instance variables - replace the example below with your own
    int balance;
    String accountNumber;
    char[] PIN;
    String type = "checkings";
    int minimumBalance = 0;
    
    /**
     * Constructor for objects of class CheckingsAccount
     */
    public CheckingsAccount(int balance, String accountNumber, char[] PIN){
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.PIN = PIN;
        
    }

    /**
     *
     * @param none
     * @return returns the current balance of a checkings account
     */
    public int getBalance()
    {
        return balance;
    }
    
    /**
     * @param none
     * @return returns the security PIN of an account
     */
    public char[] getPIN(){
        return PIN;
    }
    
    /**
     * @param none
     * @return returns the account number
     */
    public String getAccountNumber(){
        return accountNumber;
    }
    
    /**
     * @param none
     * @return returns the account type
     */
    public String getAccountType(){
        return type;
    }
    
    /**
     * @param amount to be deducted from account
     * @return none
     */
    public void deduct(int amount){
        this.balance = this.balance - amount;
    }
    
    /**
     * @param amount to be added to account
     * @return none
     */
    public void add(int amount){
        this.balance = this.balance + amount;
    }
    
}
