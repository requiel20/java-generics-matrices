package JaMa2.exceptions;

public class TextSeparatorMatrixException extends FileMatrixException {

	private static final long serialVersionUID = 5307988005874903592L;

	public TextSeparatorMatrixException(String separator) {
		super("Wrong separator \"" + separator + "\" , every string besides \"-\" and \".\" can be a separator");
	}
}
