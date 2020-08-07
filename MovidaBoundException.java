package PaceCorradetti;

public class MovidaBoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Spazio per l'inserimento non disponibile";
	}
}
