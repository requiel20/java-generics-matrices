package JaMa2;

/**
 * 
 * @author Jacopo Binchi, Luca Battistelli, Vittorio Cipriani, Gianluca Francalancia
 *  Ryhor Paulitus, Federico Stacchietti, 
 *  
 *  <P>
 *  The Matrices class is a simple factory that contains methods to
 *  instantiate matrix objects
 *  This class implements two static methods for each of the four
 *  typization classes, every one calling one of their constructors and 
 *  returning the correct implementation of the generics interface
 *  Matrix\<X\>.
 *  Moreover it implements a method returning the identity matrix and a
 *  method returning a random generated matrix
 *  </P> 
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public final class Matrices {
	/** Construct an m-by-n matrix of zeros. 
	   @param m    Number of rows.
	   @param n    Number of colums.
	 */
	public static Matrix<Boolean> newBooleanMatrix(int m, int n) {
		return new BooleanMatrix(m,n);
	}

	/** Construct an m-by-n matrix of zeros. 
	   @param m    Number of rows.
	   @param n    Number of colums.
	 */
	public static Matrix<Integer> newIntegerMatrix(int m, int n) {
		return new IntegerMatrix(m,n);
	}

	/** Construct an m-by-n matrix of zeros. 
	   @param m    Number of rows.
	   @param n    Number of colums.
	 */
	public static Matrix<Float> newFloatMatrix(int m, int n) {
		return new FloatMatrix(m,n);
	}

	/** Construct an m-by-n matrix of zeros. 
	   @param m    Number of rows.
	   @param n    Number of colums.
	 */
	public static Matrix<Double> newDoubleMatrix(int m, int n) {
		return new DoubleMatrix(m,n);
	}
	
	/** Construct a matrix from a 2-D array.
	   @param A    Two-dimensional array of doubles.
	 */
	public static Matrix<Boolean> newBooleanMatrix(boolean A[][]) {
		return new BooleanMatrix(A);
	}

	/** Construct a matrix from a 2-D array.
	   @param A    Two-dimensional array of doubles.
	 */
	public static Matrix<Integer> newIntegerMatrix(int A[][]) {
		return new IntegerMatrix(A);
	}

	/** Construct a matrix from a 2-D array.
	   @param A    Two-dimensional array of doubles.
	 */
	public static Matrix<Float> newFloatMatrix(float A[][]) {
		return new FloatMatrix(A);
	}

	/** Construct a matrix from a 2-D array.
	   @param A    Two-dimensional array of doubles.
	 */
	public static Matrix<Double> newDoubleMatrix(double A[][]) {
		return new DoubleMatrix(A);
	}

	/** Generate matrix with random elements
	   @param m    Number of rows.
	   @param n    Number of colums.
	   @return     An m-by-n matrix with uniformly distributed random elements.
	 */

	public static Matrix<Double> newRandommatrix (int m, int n) {
		Matrix A = Matrices.newDoubleMatrix(m,n);
		double[][] X = A.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = Math.random();
			}
		}
		return A;
	}

	/** Generate identity matrix
	   @param m    Number of rows.
	   @param n    Number of colums.
	   @return     An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */

	public static Matrix<Integer> newIdentityMatrix (int m, int n) {
		Matrix A = Matrices.newIntegerMatrix(m,n);
		double[][] X = A.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = (i == j ? 1.0 : 0.0);
			}
		}
		return A;
	}
}