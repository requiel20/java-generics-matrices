package JaMa2.exceptions;


public abstract class IndexMatrixException extends MatrixException {

 	private static final long serialVersionUID = -3282445008998629340L;

	public IndexMatrixException(String message) {
		super("An instruction requiring an index has raised an error. " + message);
	}
}
