package JaMa2.exceptions;


public class ColumnRowNotEqualsMatrixException extends OperationMatrixException {

	private static final long serialVersionUID = -6958005718952369583L;

	public ColumnRowNotEqualsMatrixException() {
		super("The number of column of the frist matrix must match the number of rows of the second one.");
	}
}
