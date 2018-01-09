package JaMa2.exceptions;


public class SameDimensionsMatrixException extends OperationMatrixException {

	private static final long serialVersionUID = 3248933357799972105L;

	public SameDimensionsMatrixException() {
		super("Matrices must have the same dimensions.");
	}
}
