package JaMa2.exceptions;

public class BooleanConversionMatrixException extends ConversionMatrixExeption {

	private static final long serialVersionUID = 3750819057253220219L;

	public BooleanConversionMatrixException() {
		super("Can not convert the current matrix in a matrix of boolean values, it must contain only 1 and 0");
	}
}
