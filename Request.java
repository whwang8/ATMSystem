
/**
 * Class Request
 * This class represents a transaction request made by the user
 *
 * @author Ling Qi
 * @version Oct.19th
 */
public class Request
{
    private Account fromAcct;
    private Account toAcct;
    private int amount;
    //private boolean valid;

    /**
     * Constructors for objects of class Request
     */
    public Request(Account fromAcct){
        this.fromAcct = fromAcct;
    }
    public Request(Account fromAcct, int amount){
        this.fromAcct = fromAcct;
        this.amount = amount;
    }
    public Request(Account fromAcct, Account toAcct, int amount){
        this.fromAcct = fromAcct;
        this.toAcct = toAcct;
        this.amount = amount;
    }

    /**
     * getAmount
     *
     * @param None
     * @return the requested amount of transaction, if any
     */
    public int getAmount()
    {
        return amount;
    }
    
    /**
     * getFromAccount
     *
     * @param None
     * @return the account that initiates the transaction
     */
    public Account getFromAccount()
    {
        return fromAcct;
    }
    
    /**
     * getToAccount
     *
     * @param None
     * @return the account that receives the transaction, if any
     */
    public Account getToAccount()
    {
        return toAcct;
    }
    
    // public boolean isValid(){
        // return valid;
    // }
    
    // public void setValid(boolean v){
        // this.valid = v;
    // }
}
