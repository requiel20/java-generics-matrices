package JaMa2.exceptions;


public class ZeroDetMatrixException extends OperationMatrixException {

	private static final long serialVersionUID = -5670062237446220541L;

	public ZeroDetMatrixException() {
		super("The matrix must have determinant equals to zero.");
	}
}
