package JaMa2;

import java.io.Serializable;

import JaMa2.exceptions.ColumnRowNotEqualsMatrixException;
import JaMa2.exceptions.ElementOutOfBoundsMatrixException;
import JaMa2.exceptions.NotSquareMatrixException;
import JaMa2.exceptions.NullMatrixException;
import JaMa2.exceptions.SameDimensionsMatrixException;
import JaMa2.exceptions.SubMatrixOutOfBoundsMatrixException;
import JaMa2.exceptions.ZeroDetMatrixException;

@SuppressWarnings("rawtypes")
public interface Matrix <X> extends Serializable, Cloneable {

	/** Access the internal two-dimensional array.
	@return     Pointer to the two-dimensional array of matrix elements.
	 */
	public double[][] getArray();

	/** Copy the internal two-dimensional array.
	   @return     Two-dimensional array copy of matrix elements.
	 */
	public double[][] getArrayCopy();

	/** Get row dimension.
	@return     m, the number of rows.
	 */
	public int getM();

	/** Get column dimension.
	@return     n, the number of columns.
	 */
	public int getN();

	/** Get an element.
	   @param i    			row index
	   @param j  			column index
	   @return              the element in (i,j)
	 * @throws ElementOutOfBoundsMatrixException
	 */
	public X get(int i, int j) throws ElementOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public Matrix<X> getMatrix (int i0, int i1, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @return     A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public Matrix<X> getMatrix (int[] r, int[] c) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(r(:),j0:j1) 
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public Matrix<X> getMatrix (int[] r, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @return     A(i0:i1,c(:))
	 * @throws SubMatrixOutOfBoundsMatrixException
	 */
	public Matrix<X> getMatrix (int i0, int i1, int[] c) throws SubMatrixOutOfBoundsMatrixException;

	/** Set an element.
	   @param i    			row index
	   @param j  			column index
	   @param element 		element to set
	 * @throws ElementOutOfBoundsMatrixException
	 */
	public void set(int i, int j, X element) throws ElementOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public void setMatrix (int i0, int i1, int j0, int j1, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @param M    A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public void setMatrix (int[] r, int[] c, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(r(:),j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public void setMatrix (int[] r, int j0, int j1, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @param M    A(i0:i1,c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public void setMatrix (int i0, int i1, int[] c, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;


	/** Make a deep copy of a matrix
	 * @return a copy of the matrix
	 */
	public Matrix<X> copy();

	/** Matrix transpose.
		@return    A'
	 */
	public Matrix<X>  transpose(); 

	/** One norm
	   @return    maximum column sum.
	 */
	public double norm1();

	/** Two norm
	   @return    maximum singular value.
	 */
	public double norm2();

	/** Infinity norm
	   @return    maximum row sum.
	 */
	public double normInf();

	/** Frobenius norm
	   @return    sqrt of sum of squares of all elements.	
	 */
	public double normF();

	/**  Unary minus
	   @return    -A
	 */
	public Matrix uminus();

	/** C = A + B
	   @param B    another matrix
	   @return     A + B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix plus(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** A = A + B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix plusEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** C = A - B
	   @param B    another matrix
	   @return     A - B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix minus(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** A = A - B
	   @param B    another matrix
	   @return A
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix minusEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element multiplication, C = A.*B
	   @param B    another matrix
	   @return     A.*B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayTimes(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element multiplication in place, A = A.*B
	   @param B    another matrix
	   @return A
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayTimesEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element right division, C = A./B
	   @param B    another matrix
	   @return     A./B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayRightDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element right division in place, A = A./B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayRightDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element left division, C = A.\B
	   @param B    another matrix
	   @return     A.\B
	    @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayLeftDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Element-by-element left division in place, A = A.\B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */
	public Matrix arrayLeftDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException;

	/** Multiply a matrix by a scalar, C = s*A
	   @param s    scalar
	   @return     s*A
	 */
	public Matrix<Double> times(double s);

	/** Multiply a matrix by a scalar in place, A = s*A
	   @param s    scalar
	   @return A
	 */
	public Matrix<Double> timesEquals(double s);

	/** Linear algebraic matrix multiplication, A * B
	   @param B    another matrix
	   @return     Matrix product, A * B
	    @throws ColumnRowNotEqualsMatrixException, NullMatrixException
	 */
	public Matrix times(Matrix B) throws ColumnRowNotEqualsMatrixException, NullMatrixException;

	/** LU Decomposition
	   @return     LUDecomposition of the matrix
	 */
	public LUDecomposition lu ();

	/** QR Decomposition
	   @return     QRDecomposition of the matrix
	 */
	public QRDecomposition qr ();

	/** Cholesky Decomposition
	   @return     CholeskyDecomposition of the matrix
	 */
	public CholeskyDecomposition chol ();

	/** Singular Value Decomposition
	   @return     SingularValueDecomposition of the matrix
	 */
	public SingularValueDecomposition svd ();

	/** Eigenvalue Decomposition
	   @return     EigenvalueDecomposition of the matrix
	 */
	public EigenvalueDecomposition eig ();

	/** Solve A*X = B
	   @param B    right hand side
	   @return     solution if A is square, least squares solution otherwise
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public Matrix solve(Matrix B) throws SubMatrixOutOfBoundsMatrixException;

	/** Solve X*A = B, which is also A'*X' = B'
	   @param B    right hand side
	   @return     solution if A is square, least squares solution otherwise.
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public Matrix solveTranspose(Matrix BS) throws SubMatrixOutOfBoundsMatrixException ;

	/** Matrix inverse or pseudoinverse
	   @return     inverse(A) if A is square, pseudoinverse otherwise.
	 * @throws NotSquareMatrixException 
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 * @throws ZeroDetMatrixException 
	 */
	public Matrix inverse() throws ZeroDetMatrixException, NotSquareMatrixException, SubMatrixOutOfBoundsMatrixException;

	/** Matrix trace.
	   @return     sum of the diagonal elements.
	   @throws	   NotSquareMatrixException
	 */
	public double trace() throws NotSquareMatrixException;

	/** Matrix determinant
	   @return     determinant
	 * @throws NotSquareMatrixException 
	 */
	public double det() throws NotSquareMatrixException;

	/** Matrix rank
	   @return     effective numerical rank, obtained from SVD.
	 */
	public int rank();

	/** Matrix condition (2 norm)
	   @return     ratio of largest to smallest singular value.
	 */
	public double cond();
}
