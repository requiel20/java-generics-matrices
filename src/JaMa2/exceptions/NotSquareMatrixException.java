package JaMa2.exceptions;


public class NotSquareMatrixException extends OperationMatrixException {

	private static final long serialVersionUID = -8879660294534445529L;

	public NotSquareMatrixException() {
		super("The matrix must be a square matrix.");
	}
}
