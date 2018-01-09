package JaMa2.exceptions;

public abstract class OperationMatrixException extends MatrixException {

	private static final long serialVersionUID = -722531899491905737L;

	public OperationMatrixException(String message) {
		super("An instruction requiring an operation has raised an error. " + message);
	}
}
