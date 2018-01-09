package JaMa2;

import JaMa2.exceptions.*;

/**
 * 
 * @author Jacopo Binchi, Luca Battistelli, Vittorio Cipriani, Gianluca Francalancia
 *  Ryhor Paulitus, Federico Stacchietti, 
 * 
 * <P>
 * The AbstractMatrix class represent a general matrix.
 * This class provides the memory structures and the implementation of
 * some common methods, in order to provide a programmatic representation
 * of a matrix.
 * The methods implements many arithmetics operations and all the 
 * decompositions that are used to compute many matrix functions. For what
 * getters and setters are concerned, this class implements only the ones
 * for the raw memory structures and leave the implementation of the 
 * more sophisticated ones to its concrete subclasses
 * </P>
 * 
 */

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class AbstractMatrix<X> implements Matrix<X>{

	private static final long serialVersionUID = -4868837694679371295L;

	/** Array for internal storage of elements.
	   @serial internal array storage.
	 */
	protected double A[][];

	/** Row and column dimensions.
	   @serial row dimension.
	   @serial column dimension.
	 */
	protected int m,n;

	/** Get row dimension.
		@return     m, the number of rows.
	 */

	public int getM() {
		return m;
	}

	/** Get column dimension.
		@return     n, the number of columns.
	 */

	public int getN() {
		return n;
	}

	/** Get an element.
	   @param i    			row index
	   @param j  			column index
	   @return              the element in (i,j)
	 * @throws ElementOutOfBoundsMatrixException
	 */
	public abstract X get(int i, int j) throws ElementOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract Matrix<X> getMatrix (int i0, int i1, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @return     A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract Matrix<X> getMatrix (int[] r, int[] c) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @return     A(r(:),j0:j1) 
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract Matrix<X> getMatrix (int[] r, int j0, int j1) throws SubMatrixOutOfBoundsMatrixException;

	/** Get a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @return     A(i0:i1,c(:))
	 * @throws SubMatrixOutOfBoundsMatrixException
	 */
	public abstract Matrix<X> getMatrix (int i0, int i1, int[] c) throws SubMatrixOutOfBoundsMatrixException;

	/** Set an element.
	   @param i    			row index
	   @param j  			column index
	   @param element 		element to set
	 * @throws ElementOutOfBoundsMatrixException
	 */
	public abstract void set(int i, int j, X element) throws ElementOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(i0:i1,j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract void setMatrix (int i0, int i1, int j0, int j1, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param c    Array of column indices.
	   @param M    A(r(:),c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract void setMatrix (int[] r, int[] c, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param r    Array of row indices.
	   @param j0   Initial column index
	   @param j1   Final column index
	   @param M    A(r(:),j0:j1)
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract void setMatrix (int[] r, int j0, int j1, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;

	/** Set a submatrix.
	   @param i0   Initial row index
	   @param i1   Final row index
	   @param c    Array of column indices.
	   @param M    A(i0:i1,c(:))
	   @throws SubMatrixOutOfBoundsMatrixException 
	 */
	public abstract void setMatrix (int i0, int i1, int[] c, Matrix<X> M) throws SubMatrixOutOfBoundsMatrixException;


	/** Access the internal two-dimensional array.
		@return     Pointer to the two-dimensional array of matrix elements.
	 */

	public double[][] getArray() {
		return A;
	}

	/** Copy the internal two-dimensional array.
	   @return     Two-dimensional array copy of matrix elements.
	 */

	public double[][]getArrayCopy() {
		double [][] A = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = this.A[i][j];
			}
		}
		return A;
	}

	/** Make a deep copy of a matrix
	 * @return a copy of the matrix
	 */
	public Matrix copy () {
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j];
			}
		}
		return X;
	}

	/** Matrix transpose.
   		@return    A'
	 */

	public Matrix transpose() {
		Matrix X = Matrices.newDoubleMatrix(n,m);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[j][i] = A[i][j];
			}
		}
		return X;
	}

	/** One norm
	   @return    maximum column sum.
	 */

	public double norm1() {
		double f = 0;
		for (int j = 0; j < n; j++) {
			double s = 0;
			for (int i = 0; i < m; i++) {
				s += Math.abs(A[i][j]);
			}
			f = Math.max(f, s);
		}
		return f;
	}

	/** Two norm
	   @return    maximum singular value.
	 */

	public double norm2(){ 
		return (new SingularValueDecomposition((Matrix) this).norm2());
	}

	/** Infinity norm
	   @return    maximum row sum.
	 */

	public double normInf() {
		double f = 0;
		for (int i = 0; i < m; i++) {
			double s = 0;
			for (int j = 0; j < n; j++) {
				s += Math.abs(A[i][j]);
			}
			f = Math.max(f, s);
		}
		return f;
	}

	/** Frobenius norm
	   @return    sqrt of sum of squares of all elements.	
	 */

	public double normF() {
		double f = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				f = MatrixToolBox.hypot(f, A[i][j]);
			}
		}
		return f;
	}

	/**  Unary minus
	   @return    -A
	 */

	public Matrix uminus() {
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = -A[i][j];
			}
		}
		return X;
	}

	/** C = A + B
	   @param B    another matrix
	   @return     A + B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix plus (Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		Matrix X = new DoubleMatrix(m,n);
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] + D[i][j];
			}
		}
		return (Matrix) X;
	}

	/** A = A + B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix plusEquals (Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] + D[i][j];
			}
		}
		return (Matrix) this;
	}

	/** C = A - B
	   @param B    another matrix
	   @return     A - B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix minus (Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] - D[i][j];
			}
		}
		return X;
	}

	/** A = A - B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix minusEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] - D[i][j];
			}
		}
		return (Matrix) this;
	}

	/** Element-by-element multiplication, C = A.*B
	   @param B    another matrix
	   @return     A.*B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayTimes(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		Matrix X = Matrices.newDoubleMatrix(m, n);
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] * D[i][j];
			}
		}
		return X;
	}

	/** Element-by-element multiplication in place, A = A.*B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayTimesEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] * D[i][j];
			}
		}
		return (Matrix) this;
	}

	/** Element-by-element right division, C = A./B
	   @param B    another matrix
	   @return     A./B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayRightDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j] / D[i][j];
			}
		}
		return X;
	}

	/** Element-by-element right division in place, A = A./B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayRightDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = A[i][j] / D[i][j];
			}
		}
		return (Matrix) this;
	}

	/** Element-by-element left division, C = A.\B
	   @param B    another matrix
	   @return     A.\B
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayLeftDivide(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = D[i][j] / A[i][j];
			}
		}
		return X;

	}

	/** Element-by-element left division in place, A = A.\B
	   @param B    another matrix
	   @return A
	   @throws SameDimensionsMatrixException, NullMatrixException
	 */

	public Matrix arrayLeftDivideEquals(Matrix B) throws SameDimensionsMatrixException, NullMatrixException {
		if(this.getM() != B.getM() || this.getN()!= B.getN())
			throw new SameDimensionsMatrixException();
		double[][] D = B.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = D[i][j] / A[i][j];
			}
		}
		return (Matrix) this;
	}

	/** Multiply a matrix by a scalar, C = s*A
	   @param s    scalar
	   @return     s*A
	 */

	public Matrix times(double s) {
		Matrix X = Matrices.newDoubleMatrix(m,n);
		double[][] C = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = s*A[i][j];
			}
		}
		return X;
	}

	/** Multiply a matrix by a scalar in place, A = s*A
	   @param s    scalar
	   @return A
	 */

	public Matrix timesEquals(double s) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = s*A[i][j];
			}
		}
		return (Matrix) this;
	}

	/** Linear algebraic matrix multiplication, A * B
	   @param B    another matrix
	   @return     Matrix product, A * B
	   @throws ColumnRowNotEqualsMatrixException, NullMatrixException
	 */


	public Matrix times(Matrix B) throws ColumnRowNotEqualsMatrixException, NullMatrixException {
		if(n!=B.getM())
			throw new ColumnRowNotEqualsMatrixException();
		Matrix X = Matrices.newDoubleMatrix(m,B.getN());
		double[][] C = X.getArray();
		double[][] D = B.getArray();
		double[] Bcolj = new double[n];
		for (int j = 0; j < B.getN(); j++) {
			for (int k = 0; k < n; k++) {
				Bcolj[k] = D[k][j];
			}
			for (int i = 0; i < m; i++) {
				double[] Arowi = A[i];
				double s = 0;
				for (int k = 0; k < n; k++) {
					s += Arowi[k]*Bcolj[k];
				}
				C[i][j] = s;
			}
		}
		return X;
	}

	/** LU Decomposition
	   @return     LUDecomposition of the matrix 
	 */

	public LUDecomposition lu () {
		return new LUDecomposition((Matrix) this);
	}

	/** QR Decomposition
	   @return     QRDecomposition of the matrix
	 */

	public QRDecomposition qr () {
		return new QRDecomposition((Matrix) this);
	}

	/** Cholesky Decomposition
	   @return     CholeskyDecomposition of the matrix
	 */

	public CholeskyDecomposition chol () {
		return new CholeskyDecomposition((Matrix) this);
	}

	/** Singular Value Decomposition
	   @return     SingularValueDecomposition of the matrix
	 */

	public SingularValueDecomposition svd () {
		return new SingularValueDecomposition((Matrix) this);
	}

	/** Eigenvalue Decomposition
	   @return     EigenvalueDecomposition of the matrix
	 */

	public EigenvalueDecomposition eig () {
		return new EigenvalueDecomposition((Matrix) this);
	}


	/** Solve A*X = B
	   @param B    right hand side
	   @return     solution if A is square, least squares solution otherwise
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 */

	public Matrix solve(Matrix B) throws SubMatrixOutOfBoundsMatrixException {
		return (m == n ? (new LUDecomposition((Matrix) this)).solve(B) :
			(new QRDecomposition((Matrix) this)).solve(B));
	}

	/** Solve X*A = B, which is also A'*X' = B'
	   @param B    right hand side
	   @return     solution if A is square, least squares solution otherwise.
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 */

	public Matrix solveTranspose(Matrix B) throws SubMatrixOutOfBoundsMatrixException {
		return transpose().solve(B.transpose());
	}

	/** Matrix inverse or pseudoinverse
	   @return     inverse(A) if A is square, pseudoinverse otherwise.
	 * @throws NotSquareMatrixException 
	 * @throws SubMatrixOutOfBoundsMatrixException 
	 * @throws ZeroDetMatrixException 
	 */

	public Matrix inverse() throws ZeroDetMatrixException, NotSquareMatrixException, SubMatrixOutOfBoundsMatrixException {
		if(this.det()==0)
			throw new ZeroDetMatrixException();
		return solve(Matrices.newIdentityMatrix(m,m));
	}

	/** Matrix determinant
	   @return     determinant
	 * @throws NotSquareMatrixException 
	 */

	public double det() throws NotSquareMatrixException {
		if(m!=n)
			throw new NotSquareMatrixException();
		return new LUDecomposition((Matrix) this).det();
	}

	/** Matrix rank
	   @return     effective numerical rank, obtained from SVD.
	 */

	public int rank() {
		return new SingularValueDecomposition((Matrix) this).rank();
	}

	/** Matrix condition (2 norm)
	   @return     ratio of largest to smallest singular value.
	 */

	public double cond() {
		return new SingularValueDecomposition((Matrix) this).cond();
	}

	/** Matrix trace.
	   @return     sum of the diagonal elements.
	   @throws	   NotSquareMatrixException
	 */

	public double trace() throws NotSquareMatrixException {
		if (this.getM() != this.getN())
			throw new NotSquareMatrixException();
		double t = 0;
		for (int i = 0; i < Math.min(m,n); i++) {
			t += A[i][i];
		}
		return t;
	}
}
