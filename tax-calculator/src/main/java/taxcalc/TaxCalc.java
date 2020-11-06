package taxcalc;

import java.util.LinkedList;
import java.util.List;

public class TaxCalc {

	private double percent;
	private String currency;
	private List<ValueCurrencyPair<Double, String>> amounts;
	
	private TaxCalc(TaxCalcBuilder builder) {
		this.percent = builder.percentage;
		this.amounts = builder.amounts;
		this.currency = builder.currency;
	}
	
	public static class TaxCalcBuilder {
		private List<ValueCurrencyPair<Double, String>> amounts;
		private double percentage;
		private String currency;
		
		public TaxCalcBuilder(double percentage,String currency) {
			this.percentage=percentage;
			this.currency=currency;
			amounts = new LinkedList<ValueCurrencyPair<Double,String>>();
		}
		public TaxCalcBuilder addAmount(ValueCurrencyPair<Double,String> amount) throws ApplicationException{
			if(!this.currency.equals(amount.currency)) {
				throw new ApplicationException("bad currency, expecting: "+this.currency+" recieved: "+amount.currency);
			}
			this.amounts.add(amount);
			return this;
		}
		public TaxCalc build() throws ApplicationException {
			if(this.amounts.isEmpty()) {
				throw new ApplicationException("no amounts");
			}
			TaxCalc taxCalc = new TaxCalc(this);
			return taxCalc;
		}
	}
	
	public ValueCurrencyPair<Double, String> netAmount() {

		double total=0;
		
		for (ValueCurrencyPair<Double,String> value:this.amounts) {
			total += value.amount;
		}

		double tax = total * (percent / 100d);
		ValueCurrencyPair<Double, String> net = new ValueCurrencyPair<>(total-tax, this.currency);

		return net;
	}

	public static class ValueCurrencyPair<A, C> {
		final A amount;
		final C currency;

		ValueCurrencyPair(A amount, C currency) {
			this.amount = amount;
			this.currency = currency;
		}

	}
}