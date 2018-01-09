package JaMa2.exceptions;


public class FileNotFoundMatrixException extends FileMatrixException {

	private static final long serialVersionUID = 1611967763212010515L;

	public FileNotFoundMatrixException(String path) {
		super("The file at " + path + " cannot be found.");
	}
}
