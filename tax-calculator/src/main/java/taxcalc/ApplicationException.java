package taxcalc;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 9212558376825441167L;
	
	public ApplicationException(String message) {
		super(message);
	}
}