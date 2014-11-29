package model.economy;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

    @SuppressWarnings("deprecation")
    @Test
    public void testManipulateMoneyAmount()
    {

        double startAmount = 1000;
        Money money = new Money(startAmount);
        
        money.removeAmount(100);
        Assert.assertTrue(money.getAmount() < startAmount);
        Assert.assertEquals(900, money.getAmount(), 0);
        
        money.addAmount(300);
        Assert.assertTrue(money.getAmount() > startAmount);
        Assert.assertEquals(1200, money.getAmount(), 0);
        
        money.removeAmount(1300);
        Assert.assertTrue(money.getAmount() > startAmount);
        Assert.assertEquals(1200, money.getAmount(), 0);
        
    }
    
    
    
}
