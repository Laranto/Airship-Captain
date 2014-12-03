package model.economy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {

    @SuppressWarnings("deprecation")
    
    double startAmount;
    Money money;
    
    @Before
    public void init()
    {
        startAmount = 1000;
        money = new Money(startAmount);
    }
    
    @Test
    public void addAmountTest()
    {
        money.addAmount(300);
        Assert.assertTrue(money.getAmount() > startAmount);
        Assert.assertEquals(1300, money.getAmount(), 0);
        
        double currentMoneyAmount = money.getAmount();
        
        money.addAmount(-300);
        Assert.assertEquals(currentMoneyAmount, money.getAmount(), 0);
        
        
    }
    
    @Test
    public void removeAmountTest()
    {
        money.removeAmount(100);
        Assert.assertTrue(money.getAmount() < startAmount);
        Assert.assertEquals(900, money.getAmount(), 0);
        
        money.removeAmount(1300);
        Assert.assertEquals(900, money.getAmount(), 0);
    }
    
}
