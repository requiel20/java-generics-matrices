package JaMa2.exceptions;

public abstract class ConversionMatrixExeption extends MatrixException {

	private static final long serialVersionUID = -8569777749621345102L;

	public ConversionMatrixExeption(String message) {
		super("An instruction requiring a conversion has raised an error. " + message);
	}

}
