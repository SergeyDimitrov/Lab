package math.linear;

public class Matrix {
    private double[][] values;

    public Matrix(int rowsCount, int columnsCount) {
        values = new double[rowsCount][columnsCount];
    }

    public int getColumnsCount() {
        return values[0].length;
    }

    public int getRowsCount() {
        return values.length;
    }

    public void setValue(double value, int i, int j) {
        values[i][j] = value;
    }

    public double getValue(int i, int j) {
        return values[i][j];
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void swap(int first, int second) {
        for (int i = 0; i < getColumnsCount(); i++) {
            double tmp = values[first][i];
            values[first][i] = values[second][i];
            values[second][i] = tmp;
        }
    }
}
