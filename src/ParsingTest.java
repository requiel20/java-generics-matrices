import java.io.IOException;
import java.nio.file.InvalidPathException;

import JaMa2.Matrix;
import JaMa2.MatrixToolBox;
import JaMa2.exceptions.ElementOutOfBoundsMatrixException;
import JaMa2.exceptions.EmptyFileMatrixException;
import JaMa2.exceptions.FileNotFoundMatrixException;
import JaMa2.exceptions.TextFormattingMatrixException;
import JaMa2.exceptions.TextSeparatorMatrixException;


public class ParsingTest {
	public static void main(String[] args) throws FileNotFoundMatrixException, TextFormattingMatrixException, EmptyFileMatrixException, TextSeparatorMatrixException, InvalidPathException, IOException, ElementOutOfBoundsMatrixException {
		Matrix A = MatrixToolBox.txtParse("/home/username/Desktop/matrixB.txt", " ");
		MatrixToolBox.printMatrix(A);
	}
}
