

/**
 * The test class of Transaction.
 *
 * @author Ling
 * @version Oct23 2020
 */
public class TransactionTest{
    public TransactionTest(){
    }
    
    public static void main(String args[]){
        Account acct1 = new CheckingsAccount(500, "12", new char[]{3,4});
        Account acct2 = new SavingsAccount(600, "34", new char[]{1,2});
        Request rq = new Request(acct1, acct2, 200);
        Transaction i = new Transfer(rq);
        i.process();
    }
}