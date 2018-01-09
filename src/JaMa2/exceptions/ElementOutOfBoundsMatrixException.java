package JaMa2.exceptions;


public class ElementOutOfBoundsMatrixException extends IndexMatrixException {

	private static final long serialVersionUID = 7895778192747414681L;

	public ElementOutOfBoundsMatrixException(int x, int y) {
		super("The element of index (" + x + "," + y + ") dosen't exist in the matrix.");
	}
}
