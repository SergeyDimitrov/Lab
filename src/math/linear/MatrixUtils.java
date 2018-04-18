package math.linear;

public class MatrixUtils {

    public static Matrix inverseMatrix(Matrix matrix) {
        Matrix c = new Matrix(matrix.getRowsCount(), matrix.getColumnsCount() * 2);
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            for (int j = 0; j < matrix.getColumnsCount(); j++) {
                c.setValue(matrix.getValue(i, j), i, j);
            }
            for (int j = matrix.getColumnsCount(); j < matrix.getColumnsCount() * 2; j++) {
                if (j - matrix.getColumnsCount() == i) c.setValue(1, i, j);
                else c.setValue(0.0, i, j);
            }
        }
        for (int i = 0; i < c.getRowsCount(); i++) {
            for (int j = i + 1; j < c.getRowsCount(); j++) {
                int swapIndex = -1;
                for (int k = i; k < c.getRowsCount(); k++) {
                    if (c.getValue(k, i) != 0) {
                        swapIndex = k;
                        break;
                    }
                }
                c.swap(i, swapIndex);
                double ratio = -c.getValue(j, i) / c.getValue(i, i);
                for (int k = i; k < c.getColumnsCount(); k++) {
                    c.setValue(c.getValue(j, k) + ratio * c.getValue(i, k), j, k);
                }
            }
        }
        for (int i = c.getRowsCount() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double ratio = -c.getValue(j, i) / c.getValue(i, i);
                for (int k = i; k < c.getColumnsCount(); k++) {
                    c.setValue(c.getValue(j, k) + ratio * c.getValue(i, k), j, k);
                }
            }
        }
        for (int i = 0; i < c.getRowsCount(); i++) {
            double centralVal = c.getValue(i, i);
            for (int j = 0; j < c.getColumnsCount(); j++) {
                c.setValue(c.getValue(i, j) / centralVal, i, j);
            }
        }
        Matrix answer = new Matrix(matrix.getRowsCount(), matrix.getColumnsCount());
        for (int i = 0; i < answer.getRowsCount(); i++) {
            for (int j = 0; j < answer.getColumnsCount(); j++) {
                answer.setValue(c.getValue(i, j + matrix.getColumnsCount()), i, j);
            }
        }
        return answer;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.getRowsCount(), a.getColumnsCount());
        for (int i = 0; i < c.getRowsCount(); i++) {
            for (int j = 0; j < c.getColumnsCount(); j++) {
                c.setValue(a.getValue(i, j) - b.getValue(i, j), i, j);
            }
        }
        return c;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.getRowsCount(), b.getColumnsCount());
        for (int i = 0; i < c.getRowsCount(); i++) {
            for (int j = 0; j < c.getColumnsCount(); j++) {
                double value = 0;
                for (int k = 0; k < a.getColumnsCount(); k++) {
                    value += a.getValue(i, k) * b.getValue(k, j);
                }
                c.setValue(value, i, j);
            }
        }
        return c;
    }
}
