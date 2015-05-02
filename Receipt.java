
public class Receipt
{
    private ReceiptStrategy strategy;
    
    public Receipt(ReceiptStrategy strategy)
    {
        this.strategy = strategy;
    }
    
    public String executeStrategy(String currentUser)
    {
        return this.strategy.produce(currentUser);
    }
}
