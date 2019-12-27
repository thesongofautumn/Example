import java.util.Vector;

public class Equation1 extends Computing{
    final private int a = 6, //коэффициенты для оператора
                      b = 1, //
                      c = 3, //
                      d = 4, //------

                      alph = 2, //коэффициент

                      mj = 2, // коэффициенты для уравнения
                      lambda = 7; //------

    private double M, //коэффициент сильной аккретивности
                   L, //кэффициент Липинца
                   F, //асимптота
                   f; //значение оператора


    private Vector<Double> t, u, y;

    public Equation1() {
        this.t = new Vector<>();
        this.u = new Vector<>();
        this.y = new Vector<>();

        this.y.add(new Double(1));
        this.t.add(new Double(1));
        this.u.add(new Double(0));

        this.F = a/b;
        this.M = d;
        this.L = (a/c) + d;
    }

    public boolean conditionsForCoefficients() {
        double gamma = 2*lambda - 4*mj;
        double temp = M - (L*alph)/gamma;

        if (gamma > 0) {
            if (temp > 0) {
                if (temp < ((lambda*lambda)/(8*mj)))
                    return true;
            }
        }
        return false;
    }

    protected double calculationFunc(double _t, double _y, double _u) {
        return (-lambda*_u - mj*(((a*_y)/(b*_y + c)) - f));
    }

    public void decision() {
        super.y = this.y;
        super.u = this.u;
        super.t = this.t;

        i = 0;
        while (true) {
            super.decision();
            this.y = super.y;
            this.u = super.u;
            this.t = super.t;
            i++;
            if (((y.get(i) - y.get(i - 1)) < super.E) &&
                    ((u.get(i) - u.get(i - 1)) < super.E))
                break;
        }
    }

    public void set_f(double f) {
        this.f = f;
    }
    public double get_f() {
        return this.f;
    }
    public double getF() { return F; }
    public double getLast_y() {return this.y.get(i);}
    public double get_yi(int _i) {return this.y.get(_i);}
    public double getTi(int _i) {return this.t.get(_i);}

    public double getFuncBx(double _i) {
        double temp;
        if(_i < 0)
            return 0;
        temp = (a*_i)/(b*_i + c);
        return temp;
    }
}