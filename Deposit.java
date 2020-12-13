
/**
 * Write a description of class Deposit here.
 *
 * @author Ling Qi
 * @version Oct23,2020
 */
public class Deposit extends Transaction
{
    private Request rq;
    
    /**
     * Constructor for objects of class Deposit
     */
    public Deposit(Request req){
        this.rq = req;
    }

    public boolean isValidRequest(){
        return true;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void performRequest(){
        Account acct = rq.getFromAccount();
        int amount = rq.getAmount();
        acct.add(amount);
    }
    
    public void printReceipt(){
        String info = "Deposited $" + rq.getAmount() + " to account " + rq.getFromAccount().getAccountNumber() + ".";
        Receipt receipt = new Receipt(info, rq.getFromAccount());
    }
}
