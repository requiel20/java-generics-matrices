package JaMa2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;

import JaMa2.exceptions.BooleanConversionMatrixException;
import JaMa2.exceptions.ElementOutOfBoundsMatrixException;
import JaMa2.exceptions.EmptyFileMatrixException;
import JaMa2.exceptions.FileNotFoundMatrixException;
import JaMa2.exceptions.NotSquareMatrixException;
import JaMa2.exceptions.TextFormattingMatrixException;
import JaMa2.exceptions.TextSeparatorMatrixException;

/**
 * 
 * @author Jacopo Binchi, Luca Battistelli, Vittorio Cipriani, Gianluca Francalancia
 *  Ryhor Paulitus, Federico Stacchietti, 
 *  
 *  <P>
 *  The MatrixToolBox class is container to some useful static methods for 
 *  matrix manipulation
 *  This class implements:
 *  <UL>
 *  <LI>Four static methods checking if the matrix provided
 *  is, respectively, symmetric, positive, square or zero</LI>
 *  <LI>Two static methods txtParse and txtWrite, the frist one reading a matrix
 *  from a text file and the second writing a matrix to a text file</LI>
 *  <LI>A printMatrix method, printing the matrix to the standard output</LI>
 *  <LI>Four static converters methods, each converting a matrix to another typization
 *  class</LI>
 *  <LI>A static hypot method, performing a mathematical calculation useful to some of
 *  the methods in AbstractMatrix</LI>
 *  </P> 
 *
 */


@SuppressWarnings("rawtypes")
public final class MatrixToolBox  {

	/** Check if the matrix is symmetric
	 * @param M    the matrix to check
	 * @return     true if the matrix is symmetric, false otherwise
	 */
	public static boolean isSymmetric(Matrix M) throws NotSquareMatrixException {
		double[][] A = M.getArray();
		if(M.getM() != M.getN())
			throw new NotSquareMatrixException();
		for (int i = 0; i < M.getM(); i++) {
			for (int j = 0; j < M.getM(); j++) {
				if(A[i][j]!=A[j][i])
					return false;
			}
		}
		return true;
	}

	/** Check if the matrix is positive
	 * @param M    the matrix to check
	 * @return     true if the matrix is positive, false otherwise
	 */
	public static boolean isPositive(Matrix M) {
		double[][] A = M.getArray();
		for (int i = 0; i < M.getM(); i++) {
			for (int j = 0; j < M.getN(); j++) {
				if(A[i][j]<0)
					return false;
			}
		}
		return true;
	}

	/** Check if the matrix is square
	 * @param M    the matrix to check
	 * @return     true if the matrix is square, false otherwise
	 */
	public static boolean isSquare(Matrix M) {
		if(M.getM()==M.getN())
			return true;
		else return false;
	}

	/** Check if the matrix is zero
	 * @param M    the matrix to check
	 * @return     true if the matrix is zero, false otherwise
	 */
	public static boolean isZero(Matrix M) {
		double[][] A = M.getArray();
		for (int i = 0; i < M.getM(); i++) {
			for (int j = 0; j < M.getN(); j++) {
				if(A[i][j]!=0)
					return false;
			}
		}
		return true;
	}

	/** Read a matrix from a file
	 * @param path	    a path to a file, formatted in rows each containing a row of the matrix
	 * @param separator	the separator between each value of a row
	 * @return     		the matrix read
	 */

	public static Matrix<Double> txtParse(String path, String separator) throws FileNotFoundMatrixException, 
	TextFormattingMatrixException, EmptyFileMatrixException, IOException, 
	TextSeparatorMatrixException, InvalidPathException {
		String line,regex;
		int columns = -1, rows = 0, i = 0;
		double[][] A;
		ArrayList<String[]> rowsArray = new ArrayList<String[]>();

		if(separator.equals("-") || separator.equals("."))
		{
			throw new TextSeparatorMatrixException(separator);
		}

		/*Si racchiude il separatore tra \Q e \E in modo da evitare che qualsiasi carattere
		 * inserito sia trattato come un carattere speciale delle espressioni regolari*/
		regex = "\\Q" + separator + "\\E";

		/*Si crea un pattern dall'espressione regolare*/
		Pattern p = Pattern.compile(regex);

		/*Si crea un oggetto di tipo Path, cioè una rappresentazione sottoforma di oggetto di un
		 * indirizzo a un file o una cartella, secondo il file system corrente (Windows, Unix, ...)*/
		Path file = FileSystems.getDefault().getPath(path);

		/*Si crea un BufferedReader con accesso al file contenuto nel Path "file" e che usi il 
		 * set di caratteri di default del sistema*/
		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(file, Charset.defaultCharset());
		} catch (IOException e) {
			throw new FileNotFoundMatrixException(path); 
		}

		/*Si legge il file una riga alla volta*/
		try {
			while ((line = reader.readLine()) != null) {
				/*Si divide la linea letta secondo il pattern*/
				String[] items = p.split(line);

				/*Le righe vuote vengono saltate*/
				if(items.length == 1 && items[0].isEmpty()) {continue;}

				/*Se è la prima riga ad essere inserita columns vale -1 e va settato al
				  numero delle colonne*/
				if(columns == -1) {
					columns = items.length;
				}
				/*Se non si sta inserendo la prima riga e se il numero delle colonne
				 * non è quello dell'ultimo inserimento si lancia un eccezione*/
				else if (items.length != columns) {
					throw new TextFormattingMatrixException(path, rows);
				}

				/*Si aggiunge la riga letta nell'ArrayList rowsArray, sottoforma di array di stringhe,
				 * ciascuna contenente un valore*/
				rowsArray.add(items); 
				rows++;
			}
		} catch (IOException e) {
			throw e;
			/*Il blocco finally chiude lo stream*/
		} finally {
			reader.close();
		}

		/*Se il file non è vuoto si crea un array bidimensionale di double dall'ArrayList di righe */
		if(columns > 0) {
			A = new double[rows][columns];
			for(String[] row : rowsArray) {
				for(int j = 0; j < columns; j++) {
					/*Se una riga è vuota si assume 0.0*/
					if(row[j].isEmpty()) {
						A[i][j] = 0;
					}
					else {
						try {
							A[i][j] = Double.parseDouble(row[j]);
						}
						catch (NumberFormatException e) {
							throw new TextFormattingMatrixException(path, i, j);
						}
					}
				}
				i++;
			}
			return Matrices.newDoubleMatrix(A);
		} 
		/*Se il file è vuoto si lancia un eccezione*/
		else {
			throw new EmptyFileMatrixException(path);
		}
	}

	/** Write a matrix to a file, if the file it doesen't exist it will be created
	 * if the file exist will be overwritten
	 * @param path	    a path to a file
	 * @param separator	the separator between each value of a row
	 * @param     		the matrix to write
	 */
	public static void txtWrite(String path, String separator, Matrix M) 
			throws FileNotFoundMatrixException, IOException {
		double[][] A = M.getArray();
		int m = A.length;
		int n = A[0].length;
		/*Si crea un oggetto di tipo Path, cioè una rappresentazione sottoforma di oggetto di un
		 * indirizzo a un file o una cartella, secondo il file system corrente (Windows, Unix, ...)*/
		Path file = FileSystems.getDefault().getPath(path);
		/*Si crea un BufferedWriter con accessoal file contenuto nel Path "file" e che usi il 
		 * set di caratteri di default del sistema*/
		BufferedWriter writer;
		try {
			writer = Files.newBufferedWriter(file, Charset.defaultCharset());
		} catch (IOException e) {
			throw new FileNotFoundMatrixException(path);
		}

		/*Si scrive la matrice*/
		try {
			for (int i = 0; i < m; i++) {
				/*Si inizia una nuova riga ogni volta che l'indice di riga incrementa,
				 * apparte la prima (cioè quando i == 0)*/
				if(i != 0)
					writer.newLine();
				for (int j = 0; j < n; j++) {
					/*Si scrive un separatore ogni volta che l'indice di colonna incrementa,
					 * apparte la prima (cioè quando j == 0)*/
					if(j != 0)
						writer.write(separator);
					writer.write(String.valueOf(A[i][j]));

				}
			}
		} catch (IOException e) {
			throw e;
			/*Il blocco finally chiude lo stream*/
		} finally {
			writer.close();
		}
	}

	/** Print a matrix to the standard output
	 * @param A	    the matrix to print
	 */
	public static void printMatrix(Matrix A) throws ElementOutOfBoundsMatrixException {
		/*Se la matrice è di double o float taglia dalle stringhe mostrate il ".0" finale se presente*/
		if(A instanceof DoubleMatrix || A instanceof FloatMatrix)
		{
			double[][] arr = A.getArray();
			String s;
			for(double[] row : arr) {
				System.out.println();
				for(Double a : row) {
					/*Per ogni elemento lo converte in stringa e controlla se ha una parte decimale
					 * poi, partendo dalla fine, taglia l'ultimo carattere se è un '.' o uno '0'*/
					s = a.toString();
					while(s.indexOf(".")!= -1 && (s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == '0'))
						s = s.substring(0, s.length() - 1);
					/*Stampa l'elemento, eventualmente modificato*/
					System.out.print(s + " ");
				}
			}
			System.out.println("\n");
		}
		/*Altrimenti stampa normalmente*/
		else {
			for (int i = 0; i < A.getM(); i++) {
				if(i != 0) System.out.println();
				for (int j = 0; j < A.getN(); j++) {
					System.out.print(A.get(i, j) + " ");
				}
			}
			System.out.println("\n");
		}
	}

	/** If possible, change the type of the matrix to boolean
	 * @param B	    the matrix to convert
	 * @throws BooleanConversionMatrixException 
	 */
	public static Matrix<Boolean> toBooleanMatrix(Matrix B) throws BooleanConversionMatrixException {
		boolean[][] A = new boolean[B.getM()][B.getN()];
		for (int i = 0; i < B.getM(); i++) {
			for (int j = 0; j < B.getN(); j++) {
				if(B.getArray()[i][j] == 0)
					A[i][j] = false;
				else if(B.getArray()[i][j] == 1)
					A[i][j] = true;
				else
					throw new BooleanConversionMatrixException();
			}
		}
		return Matrices.newBooleanMatrix(A);
	}

	/** If possible, change the type of the matrix to integer
	 * @param B	    the matrix to convert
	 */
	public static Matrix<Integer> toIntegerMatrix(Matrix B) {
		int[][] A = new int[B.getM()][B.getN()];
		for (int i = 0; i < B.getM(); i++) {
			for (int j = 0; j < B.getN(); j++) {
				A[i][j] = (int) B.getArray()[i][j];
			}
		}
		return Matrices.newIntegerMatrix(A);
	}

	/** If possible, change the type of the matrix to float
	 * @param B	    the matrix to convert
	 */
	public static Matrix<Float> toFloatMatrix(Matrix B) {
		float[][] A = new float[B.getM()][B.getN()];
		for (int i = 0; i < B.getM(); i++) {
			for (int j = 0; j < B.getN(); j++) {
				A[i][j] = (float) B.getArray()[i][j];
			}
		}
		return Matrices.newFloatMatrix(A);
	}

	/** If possible, change the type of the matrix to double
	 * @param B	    the matrix to convert
	 */
	public static Matrix<Double> toDoubleMatrix(Matrix B) {
		double[][] A = new double[B.getM()][B.getN()];
		for (int i = 0; i < B.getM(); i++) {
			for (int j = 0; j < B.getN(); j++) {
				A[i][j] = (double) B.getArray()[i][j];
			}
		}
		return Matrices.newDoubleMatrix(A);
	}

	/** 
	 * @param a 	a double value
	 * @param b		a double value
	 * @return 		sqrt(a^2 + b^2) without under/overflow. **/
	public static double hypot(double a, double b) {
		double r;
		if (Math.abs(a) > Math.abs(b)) {
			r = b/a;
			r = Math.abs(a)*Math.sqrt(1+r*r);
		} else if (b != 0) {
			r = a/b;
			r = Math.abs(b)*Math.sqrt(1+r*r);
		} else {
			r = 0.0;
		}
		return r;
	}
}