package JaMa2.exceptions;

public class EmptyFileMatrixException extends FileMatrixException {

	private static final long serialVersionUID = -29956097865509028L;

	public EmptyFileMatrixException(String path) {
		super("The file at " + path + " is empty.");
	}
}
