package JaMa2.exceptions;


public abstract class FileMatrixException extends MatrixException {

	private static final long serialVersionUID = -2450658558293584635L;

	public FileMatrixException(String message) {
		super("An instruction requiring a file has raised an error. " + message);
	}
}
