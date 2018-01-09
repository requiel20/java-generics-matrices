package JaMa2.exceptions;

public class NullMatrixException extends MatrixException {

	private static final long serialVersionUID = -3337041339963137867L;

	public NullMatrixException() {
		super("A null matrix can not be used as a parameter");
	}

}
