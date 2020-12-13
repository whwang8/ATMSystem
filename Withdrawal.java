import javax.swing.*;
/**
 * Write a description of class Withdrawal here.
 *
 * @author Ling Qi
 * @version Oct23,2020
 */
public class Withdrawal extends Transaction
{
    private Request rq;
    
    /**
     * Constructor for objects of class Deposit
     */
    public Withdrawal(Request req){
        this.rq = req;
    }

    /**
     * validateRequest: check if a request is valid
     *
     * @param int amont: the amount to be processed, if any  
     * @return a boolean confirming if the request is valid
     */
    public boolean isValidRequest(){
        Account acct = rq.getFromAccount();
        int amount = rq.getAmount();
        int availableBalance = acct.getBalance();
        if (acct.getAccountType().equals("savings")) {
            availableBalance = acct.getBalance() - acct.getMinimumBalance();
        }
        if (amount > availableBalance){
            return false;
        }
        if (amount > MAX_AMOUNT){
            return false;
        }
        return true;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void performRequest(){
        Account fromAcct = rq.getFromAccount();
        int amount = rq.getAmount();
        fromAcct.deduct(amount);
    }
    
    public void printReceipt(){
        String info = "Withdrew $" + rq.getAmount() + " from account " + rq.getFromAccount().getAccountNumber() + ".";
        Receipt receipt = new Receipt(info, rq.getFromAccount());
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
