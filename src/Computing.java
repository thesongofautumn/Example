import java.util.Vector;

public abstract class Computing {
    protected double  E; //погрешность
    final protected static double h = 0.1; //итерационный шаг
    protected int i;
    protected Vector<Double> t, u, y;

    public Computing(){
        this.t = new Vector<>();
        this.u = new Vector<>();
        this.y = new Vector<>();
    }

    abstract protected double calculationFunc(double _t, double _y, double _u);
    abstract public boolean conditionsForCoefficients();

    private double calculationK(double _u, double _v) {
        return (h * (_u + _v));
    }

    private double calculationV(double _t, double _y, double _u) {
        return (h * calculationFunc(_t, _y, _u));
    }

    private double calculationD(double array[]) {
        return ((array[0] + 2*array[1] + 2*array[2] + array[3])/6);
    }

    private double calculationSigm(double array[]) {
        return (array[0] - ((9*array[2])/2) + 4*array[3]);
    }

    public void decision() {
        double[] k = new double[4];
        double[] v = new double[4];
        double dy, du, sigm;

        k[0] = calculationK(u.get(i), 0);
        v[0] = calculationV(t.get(i), y.get(i), u.get(i));
        k[1] = calculationK(u.get(i), v[0] / 2);
        v[1] = calculationV(t.get(i) + h / 2, y.get(i) + k[0] / 2, u.get(i) + v[0] / 2);
        k[2] = calculationK(u.get(i), v[1] / 2);
        v[2] = calculationV(t.get(i) + h / 2, y.get(i) + k[1] / 2, u.get(i) + v[1] / 2);
        k[3] = calculationK(u.get(i), v[2]);
        v[3] = calculationV(t.get(i) + h, y.get(i) + k[2], u.get(i) + v[2]);

        dy = calculationD(k);
        du = calculationD(v);
        sigm = calculationSigm(k);

        if (sigm > (4 * E))
            t.add(t.get(i) + h / 2);
        else if (sigm < ((4 * E) / 16))
            t.add(t.get(i) + 2 * h);
        else
            t.add(t.get(i) + h);

        y.add(y.get(i) + dy);
        u.add(u.get(i) + du);
    }

    public void setE(double e) {this.E = e;}
    public double getE() {
        return E;
    }
    public int getI() {return this.i;}
}