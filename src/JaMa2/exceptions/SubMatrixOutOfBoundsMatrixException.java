package JaMa2.exceptions;

public class SubMatrixOutOfBoundsMatrixException extends IndexMatrixException {

	private static final long serialVersionUID = 4667651101489647973L;

	public SubMatrixOutOfBoundsMatrixException(int i0, int i1, int j0, int j1) {
		super("Can't get or set the submatrix from (" + i0 + "," + j0 + ") to (" + i1 + "," + j1 + "), "
				+ "as it exceed the bounds of the main matrix.");
	}
	
	public SubMatrixOutOfBoundsMatrixException(int r[], int j0, int j1) {
		super("Can't get or set the submatrix from columns " + j0 + " to " + j1 + ", and for " + r.length + 
				" rows between " + r[0] + " and " + r[1] + ", as it exceed the bounds of the main matrix.");
	}
	
	public SubMatrixOutOfBoundsMatrixException(int i0, int i1, int c[]) {
		super("Can't get or set the submatrix for " + c.length + " columns between " + c[0] +  " and " + c[1]
				+ " and from rows " + i0 + " to " + i1 + ", as it exceed the bounds of the main matrix.");
	}
	
	public SubMatrixOutOfBoundsMatrixException(int r[], int c[]) {
		super("Can't get or set the submatrix for " + r.length + 
				" rows between " + r[0] + " and " + r[1] + " and for " + c.length + " columns between " 
				+ c[0] +  " and " + c[1] +", as it exceed the bounds of the main matrix.");
	}
}
