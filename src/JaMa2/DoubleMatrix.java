package JaMa2;

import JaMa2.exceptions.ColumnRowNotEqualsMatrixException;
import JaMa2.exceptions.ElementOutOfBoundsMatrixException;
import JaMa2.exceptions.NullMatrixException;
import JaMa2.exceptions.SameDimensionsMatrixException;
import JaMa2.exceptions.SubMatrixOutOfBoundsMatrixException;

/**
 * 
 * @author Jacopo Binchi, Luca Battistelli, Vittorio Cipriani, Gianluca Francalancia
 *  Ryhor Paulitus, Federico Stacchietti, 
 *  
 *  <P>
 *  The Double class represent a matrix of doubles
 *  This class implements all the getters and setters for single elements
 *  of the matrix and overrides some methods inherited from AbstractMatrix,
 *  in order to implements the typization layer correctly so that, for instance,
 *  the sum between an IntegerMatrix and a DoubleMatrix gives a DoubleMatrix as
 *  it should
 *  </P> 
 *
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class DoubleMatrix extends AbstractMatrix<Double> {

	private static final long serialVersionUID = 8847888231452474189L;

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/** Construct a matrix from a 2-D array.
	   @param A    Two-dimensional array of doubles.
	 */
	protected DoubleMatrix(double[][] A) {
		this.m = A.length;
		this.n = A[0].length;
		this.A = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				this.A[i][j] = A[i][j];
			}
		}
	}

	/** Construct an m-by-n matrix of zeros. 
	   @param m    Number of rows.
	   @param n    Number of colums.
	 */
	protected DoubleMatrix(int m, int n) {
		this.m = m;
		this.n = n;
		this.A = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = 0;
			}
		}
	}

	/** Get an element.
	   @param i    			row index
	   @param j  			column index
	   @return              the element in (i,j)
	 * @throws ElementOutOfBoundsMatrixException
	 */
	@Override
	public Double get(int i, int j) throws ElementOutOfBoundsMatrixException {
		try{
			return A[i][j];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ElementOutOfBoundsMatrixException(i, j);
		}
	}

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public Matrix<Double> getMatrix (int i0, int i1, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException {
		Matrix X = Matrices.newDoubleMatrix(i1-i0+1,j1-j0+1);
		double[][] B = X.getArray();
		try {
			for (int i = i0; i <= i1; i++) {
				for (int j = j0; j <= j1; j++) {
					B[i-i0][j-j0] = A[i][j];
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new SubMatrixOutOfBoundsMatrixException(i0, i1, j0, j1);
		}
		return X;
	}

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @return     A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public Matrix<Double> getMatrix (int[] r, int[] c) throws SubMatrixOutOfBoundsMatrixException {
		Matrix X = Matrices.newDoubleMatrix(r.length,c.length);
		double[][] B = X.getArray();
		try {
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < c.length; j++) {
					B[i][j] = A[r[i]][c[j]];
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new SubMatrixOutOfBoundsMatrixException(r, c);
		}
		return X;
	}

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(r(:),j0:j1) 
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public Matrix<Double> getMatrix (int[] r, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException {
		Matrix X = Matrices.newDoubleMatrix(r.length,j1-j0+1);
		double[][] B = X.getArray();
		try {
			for (int i = 0; i < r.length; i++) {
				for (int j = j0; j <= j1; j++) {
					B[i][j-j0] = A[r[i]][j];
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new SubMatrixOutOfBoundsMatrixException(r, j0, j1);
		}
		return X;
	}

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @return     A(i0:i1,c(:))
	 * @throws SubMatrixOutOfBoundsMatrixException
	 */

	@Override
	public Matrix<Double> getMatrix (int i0, int i1, int[] c) throws SubMatrixOutOfBoundsMatrixException {
		Matrix X = Matrices.newDoubleMatrix(i1-i0+1,c.length);
		double[][] B = X.getArray();
		try {
			for (int i = i0; i <= i1; i++) {
				for (int j = 0; j < c.length; j++) {
					B[i-i0][j] = A[i][c[j]];
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new SubMatrixOutOfBoundsMatrixException(i0, i1, c);
		}
		return X;
	}

	/** Set an element.
	   @param i    			row index
	   @param j  			column index
	   @param element 		element to set
	 * @throws ElementOutOfBoundsMatrixException
	 */
	@Override
	public void set(int i, int j, Double element) throws ElementOutOfBoundsMatrixException {
		try {
			A[i][j] = element;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ElementOutOfBoundsMatrixException(i, j);
		}
	}

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public void setMatrix (int i0, int i1, int j0, int j1, Matrix<Double> M) throws SubMatrixOutOfBoundsMatrixException {
		try {
			for (int i = i0; i <= i1; i++) {
				for (int j = j0; j <= j1; j++) {
					A[i][j] = M.get(i-i0,j-j0);
				}
			}
		} catch(ArrayIndexOutOfBoundsException | ElementOutOfBoundsMatrixException e) {
			throw new SubMatrixOutOfBoundsMatrixException(i0, i1, j0, j1);
		}
	}

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @param M    A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public void setMatrix (int[] r, int[] c, Matrix<Double> M) throws SubMatrixOutOfBoundsMatrixException {
		try {
			for (int i = 0; i < r.length; i++) {
				for (int j = 0; j < c.length; j++) {
					A[i][j] = M.get(i,j);
				}
			}
		} catch(ArrayIndexOutOfBoundsException | ElementOutOfBoundsMatrixException e) {
			throw new SubMatrixOutOfBoundsMatrixException(r, c);
		}
	}

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(r(:),j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public void setMatrix (int[] r, int j0, int j1, Matrix<Double> M) throws SubMatrixOutOfBoundsMatrixException {
		try {
			for (int i = 0; i < r.length; i++) {
				for (int j = j0; j <= j1; j++) {
					A[i][j] = M.get(i,j-j0);
				}
			}
		} catch(ArrayIndexOutOfBoundsException | ElementOutOfBoundsMatrixException e) {
			throw new SubMatrixOutOfBoundsMatrixException(r, j0, j1);
		}
	}

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @param M    A(i0:i1,c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */

	@Override
	public void setMatrix (int i0, int i1, int[] c, Matrix<Double> M) throws SubMatrixOutOfBoundsMatrixException {
		try {
			for (int i = i0; i <= i1; i++) {
				for (int j = 0; j < c.length; j++) {
					A[i][j] = M.get(i-i0,j);
				}
			}
		} catch(ArrayIndexOutOfBoundsException | ElementOutOfBoundsMatrixException e) {
			throw new SubMatrixOutOfBoundsMatrixException(i0, i1, c);
		}
	}

	/** Make a deep copy of a matrix
	 * @return a copy of the matrix
	 */
	@Override
	public Matrix<Double> copy() {
		return super.copy();
	}

	/** Matrix transpose.
	@return    A'
	 */
	@Override
	public Matrix<Double> transpose() {
		return super.transpose();
	}

	/**  Unary minus
	   @return    -A
	 */
	@Override
	public Matrix<Double> uminus() {
		return super.uminus();
	}

	/** C = A + B
	   @param B    another matrix
	   @return     A + B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> plus(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.plus(B);
	}

	/** A = A + B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix plusEquals (Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.plusEquals(B);
	}

	/** C = A - B
	   @param B    another matrix
	   @return     A - B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> minus(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.minus(B);

	}
	
	/** A = A - B
	   @param B    another matrix
	   @return A
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix minusEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.minusEquals(B);
	}

	/** Element-by-element multiplication, C = A.*B
	   @param B    another matrix
	   @return     A.*B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> arrayTimes(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayTimes(B);

	}
	
	/** Element-by-element multiplication in place, A = A.*B
	   @param B    another matrix
	   @return A
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix arrayTimesEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayTimesEquals(B);
	}
	
	/** Element-by-element right division, C = A./B
	   @param B    another matrix
	   @return     A./B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> arrayRightDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayRightDivide(B);

	}
	
	/** Element-by-element right division in place, A = A./B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix arrayRightDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayRightDivideEquals(B);
	}

	/** Element-by-element left division, C = A.\B
	   @param B    another matrix
	   @return     A.\B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> arrayLeftDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayLeftDivide(B);

	}
	/** Element-by-element left division in place, A = A.\B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix arrayLeftDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.arrayLeftDivideEquals(B);
	}

	/** Multiply a matrix by a scalar, C = s*A
	   @param s    scalar
	   @return     s*A
	 */
	@Override
	public Matrix<Double> times(double s) {
		return (Matrix<Double>) super.times(s);
	}

	/** Multiply a matrix by a scalar in place, A = s*A
	   @param s    scalar
	   @return A
	 */
	@Override
	public Matrix timesEquals(double s) {
		return (Matrix<Double>) super.timesEquals(s);
	}

	/** Linear algebraic matrix multiplication, A * B
	   @param B    another matrix
	   @return     Matrix product, A * B
	    @throws ColumnRowNotEqualsMatrixException, NullMatrixException
	 */
	@Override
	public Matrix<Double> times(Matrix B) throws ColumnRowNotEqualsMatrixException, NullMatrixException {
		if(B == null)
			throw new NullMatrixException();
		return (Matrix<Double>) super.times(B);

	}
}
