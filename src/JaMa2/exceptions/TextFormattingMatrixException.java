package JaMa2.exceptions;


public class TextFormattingMatrixException extends FileMatrixException {

	private static final long serialVersionUID = -2296378676462338402L;

	public TextFormattingMatrixException(String path, int rowIndex) {
		super("The text file at " + path + " contains bad formatted text at row "+ rowIndex +".");
	}
	
	public TextFormattingMatrixException(String path, int rowIndex, int columnIndex) {
		super("The text file at " + path + " contains bad formatted text at row " + rowIndex + " and column " + columnIndex + ".");
	}
}
