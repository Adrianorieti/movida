package pacecorradetti;

public class MovidaKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Chiave non trovata";
	}
}
