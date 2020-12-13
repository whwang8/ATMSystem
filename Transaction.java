import javax.swing.*;

/**
 * Abstract class Transaction
 * This class provides basic functions a transaction has.
 *
 * @author Ling Qi
 * @version Oct18,2020
 */
public abstract class Transaction
{
    // the account that the transaction is performed on
    //private Account fromAcct;
    //private Account toAcct;
    private Request rq;
    final int MAX_AMOUNT = 1000;
    ///**
    // * setAccount: assign an account for the current transaction to perform on
    // *
     // * @param Account acct: the chosen account 
     // * @return None
     // */
    //public void setRequest(Request rq){
    //    this.fromAcct = acct;
    //    this.amount = amount;
    //}
    
    public void process(){
        if (this.isValidRequest()){
            performRequest();
            printReceipt();
        } else{
            printErrorMsg();
        }
    }
    
    /**
     * validateRequest: check if a request is valid
     *
     * @param int amont: the amount to be processed, if any  
     * @return a boolean confirming if the request is valid
     */
    public boolean isValidRequest(){
        return false;
    }
    
    /**
     * performRequest: perform the request
     *
     * @param None
     * @return None
     */
    public void performRequest(){
        return;
    }
    
    /**
     * printReceipt: print the change made by this transaction
     *
     * @param None
     * @return None
     */
    public void printReceipt(){
    }
    
    public void printErrorMsg(){
        String msg = "";
        if (rq.getAmount() > MAX_AMOUNT){
            msg = "Transaction rejected. Please keep your transaction amount under " + MAX_AMOUNT + " for each transaction.";
        } else{
            msg = "Transaction rejected. No sufficient balance to support this transaction.";
        }
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
