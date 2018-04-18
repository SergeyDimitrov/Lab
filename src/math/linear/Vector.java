package math.linear;

public class Vector extends Matrix {

    public Vector(int rowsCount) {
        super(rowsCount, 1);
    }

    public double getValue(int x) {
        return getValue(x, 0);
    }

    public int getSize() {
        return getRowsCount();
    }
}
