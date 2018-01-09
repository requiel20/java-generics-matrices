import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import JaMa2.*;
import JaMa2.exceptions.*;

public class Demo {

	static Scanner scanner = new Scanner(System.in);

	static boolean exit = false;

	public static void main(String args[]) throws IOException, ElementOutOfBoundsMatrixException {
		Matrix A = null;
		Matrix B = null;
		int scelta = -1;

		print("Welcome in the J Matrix demo! Insert a matrix to begin"); 
		while(A == null && !exit) {
			A = insertMatrix();
			if(A == null && !exit) 
				print("Welcome in J Matrix! Insert a matrix to begin");
		}

		while(scelta != 0 && !exit) {
			print("Select an operation for the matrix: ");
			print("1 - Insert a new matrix");
			print("2 - Show the matrix");
			print("3 - Transpose");
			print("4 - Determinant (only square matrices)");
			print("5 - Rank");
			print("6 - Inverse (only square matrices)");
			print("7 - Sum");
			print("8 - Multiply");
			print("0 - Exit");
			print("Your choice: ");
			if(scanner.hasNextInt())
			{
				scelta = scanner.nextInt();
				/*Consuma qualsiasi cosa sia rimasta nello standard input*/
				while(scanner.findInLine("") != null)
					scanner.next();
				if(scelta < 0 || scelta > 8)
					print("\nWrong operation: insert a number between 0 and 8\n");
				else {
					switch (scelta) {
					case 1:
						B = A;
						A = insertMatrix();
						if(A == null)
							A = B;
						exit = false;
						break;
					case 2 :
						MatrixToolBox.printMatrix(A);
						break;
					case 3 :
						MatrixToolBox.printMatrix(A.transpose());
						break;
					case 4 :
						try {
							System.out.println(A.det());
						} catch (NotSquareMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						}
						break;
					case 5 :
						System.out.println(A.rank());
						break;
					case 6 :
						try {
							MatrixToolBox.printMatrix(A.inverse());
						} catch (ZeroDetMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						} catch (NotSquareMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						} catch (SubMatrixOutOfBoundsMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						}
						break;
					case 7 :
						print("Insert the second matrix");
						B = insertMatrix();
						try {
							outputMatrix(A.plus(B));
						} catch (SameDimensionsMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						} catch (NullMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						}
						break;
					case 8 :
						print("Insert the second matrix");
						B = insertMatrix();
						try {
							outputMatrix(A.times(B));
						} catch (ColumnRowNotEqualsMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						} catch (NullMatrixException e) {
							print("\n" + e.getMessage() + "\n");
						}
						break;
					default :
						break;
					}
				}
			} else {
				scanner.nextLine();
				print("\nWrong operation: insert a number between 0 and 8\n");
			}

		}
	}

	public static Matrix insertMatrix() throws IOException {
		Matrix M = null;
		int scelta = -1;
		int rows = 0, columns = -1;
		boolean charInserted = false;
		double[] A = null;
		double[][] B = null;
		ArrayList<double[]> rowsArray = new ArrayList<>();
		String s = null;
		String path = null, separator = null;
		Pattern p = Pattern.compile("\\s+");
		while(scelta != 3) {
			print("Select an input method");
			print("1 - Keyboard");
			print("2 - File");
			print("3 - Exit");
			print("Your choice: ");
			if(scanner.hasNextInt())
			{
				scelta = scanner.nextInt();
				if(scelta < 1 || scelta > 3)
					print("\nWrong operation: insert a number between 1 and 3\n");
				else {
					switch (scelta) {
					case 1 :
						/*Consuma il carattere di fine linea nello standard input,
						 * che invece non viene consumato con scelta = scanner.nextInt()*/
						scanner.nextLine();
						print("Insert the rows, one at time, separating the values with spaces"
								+ "the last row must contain only \"exit\"");
						do {
							print("Insert row " + (rows + 1));

							/*Legge una riga*/
							s = scanner.nextLine();

							/*Se la riga � "exit" esce*/
							if(!s.equals("exit")) {
								/*Altrimenti la divide ogni volta che trova uno o pi� spazi*/
								String[] items = p.split(s);
								/*Salta le righe vuote*/
								if(items.length == 1 && items[0].isEmpty()) {
									print("Can not insert an empty row");
									continue;
								}
								/*Se � la prima riga ad essere inserita columns vale -1 e va settato al
								  numero delle colonne*/
								if(columns == -1) {
									columns = items.length;
									/*Se non si sta inserendo la prima riga e se il numero delle colonne
									 * non � quello dell'ultimo inserimento si avverte l'utente e la
									 * riga viene ignorata*/
								} else if (items.length != columns) {
									print("The number of columns must always be the same");
									continue;
								}

								/*Si trasforma l'array di stringhe items in un array di double A*/
								A = new double[items.length];
								for(int i = 0; i < items.length; i ++) {
									try {
										A[i] = Double.parseDouble(items[i]);
									}
									catch (NumberFormatException e) {
										/*Se viene inserito qualcosa che non � un numero il flag charInserted
										 * � settato a true e si avverte l'utente*/
										print("One of the values entered is not a number");
										charInserted = true;
									}
								}
								/*L'inserimento della riga nell'ArrayList di righe rowsArray avviene solo
								 * se si sono inseriti tutti numeri cos� come l'incremento del
								 * numero di righe inserite*/
								if(!charInserted) {
									rowsArray.add(A);
									rows++;
								}
								charInserted = false;
							}
							/*Ci� viene ripetuto finch� l'utente non inserisce "exit"*/
						} while(!s.equals("exit"));

						/*Si crea un array bidimensionale M dall'ArrayList di righe rowsArray*/
						B = new double[rows][columns];
						int i = 0;
						for(double[] row : rowsArray) {
							B[i] = row;
							i++;
						}

						print("Matrix inserted correctly");
						return Matrices.newDoubleMatrix(B);

					case 2 :
						print("Insert file path: ");
						path = scanner.next();

						try {
							if(Files.exists(Paths.get(path))) {
								print("Insert the separator, attention \'-\' e \'.\' are not allowed "
										+ "as separators");
								scanner.nextLine();
								separator = scanner.nextLine();


								try {
									M = MatrixToolBox.txtParse(path, separator);
									print("Matrix inserted correctly");
									return M;
								} catch (FileNotFoundMatrixException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								} catch (TextFormattingMatrixException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								} catch (EmptyFileMatrixException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								} catch (TextSeparatorMatrixException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								} catch (InvalidPathException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								}
							} else {
								print("Error: the file doesn't exist");
							}
						} catch(InvalidPathException e) {
							print("Errore: the inserted path is not valid");
						}
						break;
					case 3:
						exit = true;
						return null;
					default:
						return null;
					}
				}
			} else {
				scanner.nextLine();
				print("\nWrong operation: insert a number between 1 and 3\n");
			}
		}
		return null;
	}

	public static void outputMatrix(Matrix A) throws ElementOutOfBoundsMatrixException {
		int scelta = -1;
		String path = null, separator = null;
		while(scelta != 3) {
			print("\nChose output method:");
			print("1 - Standard output");
			print("2 - File");
			print("3 - Exit");
			print("Your choice: ");
			if(scanner.hasNextInt())
			{
				scelta = scanner.nextInt();
				while(scanner.findInLine("") != null)
					scanner.next();
				if(scelta < 1 || scelta > 3)
					print("\nWrong operation: insert a number between 1 and 3\n");
				else {
					switch (scelta) {
					case 1 :
						MatrixToolBox.printMatrix(A);
						break;
					case 2 :
						print("Inserisci il path del file: ");
						path = scanner.next();

						try {
							if(Files.exists(Paths.get(path))) {
								print("Insert the separator, attention \'-\' e \'.\' are not allowed "
										+ "as separators");								
								scanner.nextLine();
								separator = scanner.nextLine();
								try {
									MatrixToolBox.txtWrite(path, separator, A);
								} catch (FileNotFoundMatrixException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								} catch (IOException e) {
									print("\n" + e.getMessage() + "\n");
									scelta = 2;
								}
								scelta = 3;
							} else {
								print("Error: the file doesn't exist");
							}
						} catch(InvalidPathException e) {
							print("Errore: the inserted path is not valid");
						}
						break;
					default :
						break;
					}
				}
			} else {
				scanner.nextLine();
				print("\nWrong operation: insert a number between 1 and 3\n");
			}
		}
	}

	public static void print(String s) {
		System.out.println(s);
	}
}
