package pl.coderslab.gralicjaApp.validator;

public class GameTableNameExistsException extends RuntimeException {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = -3139928975715599782L;

	public GameTableNameExistsException() {
    }

    public GameTableNameExistsException(String message) {
        super(message);
    }

    public GameTableNameExistsException(Throwable cause) {
        super(cause);
    }

    public GameTableNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}