package math.function;

public abstract class Function {

    public abstract double getValue(double... x);

    public double getDerivative(int var, double... x) {
        throw new UnsupportedOperationException();
    }

    public abstract int getVarCount();

    public boolean isInverse() {
        return false;
    }
}
