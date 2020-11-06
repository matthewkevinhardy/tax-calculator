package taxcalc;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 9212558376825441167L;
	
	public ApplicationException(String message) {
		super(message);
	}
}