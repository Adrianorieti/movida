package pacecorradetti;

public class MovidaKeyException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Chiave non trovata";
	}
}
