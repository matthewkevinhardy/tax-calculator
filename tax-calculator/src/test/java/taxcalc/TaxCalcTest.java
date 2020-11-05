package taxcalc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static taxcalc.TaxCalc.ValueCurrencyPair;

public class TaxCalcTest {

    @Test
    public void canCalculateTax() throws Exception {
        
    	TaxCalc taxCalc = new TaxCalc.TaxCalcBuilder(10,"GBP")
    			.addAmount(new ValueCurrencyPair<Double,String>(40d, "GBP"))
    			.addAmount(new ValueCurrencyPair<Double,String>(50d, "GBP"))
    			.addAmount(new ValueCurrencyPair<Double,String>(60d, "GBP")).build();
    	
    	Double net = taxCalc.netAmount().amount;
    	
        assertEquals(135, net.doubleValue(),0);
    }

    @Test
    public void cannotSumDifferentCurrencies() throws Exception {
    	
    	try {
    		TaxCalc taxCalc = new TaxCalc.TaxCalcBuilder(10,"GBP")
        			.addAmount(new ValueCurrencyPair<Double,String>(40d, "USD")).build();
    		
            fail("didn't throw");
            
        } catch (ApplicationException e) {
        	assertEquals("bad currency",e.getMessage());
        } catch (Exception e) {
        	fail("unexpected excetion: "+e.getMessage());
        }
    }
}