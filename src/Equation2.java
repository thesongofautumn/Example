import java.util.Vector;

import static java.lang.Math.sqrt;

public class Equation2 extends Computing{
    final private int a1 = 1, //коэффициенты для оператора
                      a2 = 1, //
                      b1 = 4, //
                      b2 = 3, //
                      c1 = -3, //
                      c2 = 3, //
                      d1 = 3, //
                      d2 = 1, //------

                      alph = 2, //коэффициент

                      mj = 1, // коэффициенты для уравнения
                      lambda = 8; //------

    final private double M = 3, //коэффициент сильной аккретивности
                         L = sqrt(85); //кэффициент Липинца

    private double f1, f2; //значение оператора

    private boolean flag;

    private int index;

    private Vector<Double> t1, u1, t2, u2, Y1, Y2, y1, y2;

    public Equation2() {
        this.t1 = new Vector<>();
        this.u1 = new Vector<>();
        this.t2 = new Vector<>();
        this.u2 = new Vector<>();
        this.Y1 = new Vector<>();
        this.Y2 = new Vector<>();
        this.y1 = new Vector<>();
        this.y2 = new Vector<>();

        this.t1.add(new Double(0));
        this.u1.add(new Double(0));
        this.t2.add(new Double(0));
        this.u2.add(new Double(0));
        this.Y1.add(new Double(0));
        this.Y2.add(new Double(0));
        this.y1.add(new Double(0));
        this.y2.add(new Double(0));
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
            if (flag)
                return (-lambda * _u - mj * (_y - f1));
            return (-lambda * _u - mj * (_y - f2));
    }

    private double calculation_y1(double _Y, double _y) {
        return (_Y - a2*_y*_y - c2*_y - d2)/b2;
    }

    private double calculation_y2(double _Y, double _y) {
        return (_Y - a1*_y*_y - b1*_y - d1)/c1;
    }

    private void recordSuper(Vector _y, Vector _u, Vector _t){
        super.y = _y;
        super.u = _u;
        super.t = _t;
    }

    public void decision() {
        i = 0;
        while (true) {
            recordSuper(Y1, u1, t1);
            flag = true;
            super.decision();
            this.Y1 = super.y;
            this.t1 = super.t;
            this.u1 = super.u;

            recordSuper(Y2, u2, t2);
            flag = false;
            super.decision();
            this.Y2 = super.y;
            this.t2 = super.t;
            this.u2 = super.u;

            Vector<Double> temp_y1 = new Vector<>();
            Vector<Double> temp_y2 = new Vector<>();

            temp_y1.add(new Double(0));
            temp_y2.add(new Double(0));

            index = i;
            i = 0;
            while (true) {
                temp_y2.add(calculation_y2(Y1.get(index+1), temp_y1.get(i)));
                temp_y1.add(calculation_y1(Y2.get(index+1), temp_y2.get(i+1)));

                i++;
                if (((temp_y1.get(i) - temp_y1.get(i - 1)) < super.E) &&
                        ((temp_y2.get(i) - temp_y2.get(i - 1)) < super.E))
                    break;
            }
            y2.add(temp_y2.get(temp_y1.size() - 1));
            y1.add(temp_y1.get(temp_y2.size() - 1));

            i = index;
            i++;
            if (((Y1.get(i) - Y1.get(i - 1)) < super.E) &&
                    ((Y2.get(i) - Y2.get(i - 1)) < super.E))
                break;
        }
    }

    public void set_f(double f1 ,double f2) {
        this.f1 = f1;
        this.f2 = f2;
    }
    public double get_f1() {return this.f1;}
    public double get_f2() {return this.f2;}
    public double get_y1() {return this.y1.get(this.y1.size() - 1);}
    public double get_y2() {return this.y2.get(this.y2.size() - 1);}
    public double get_y1i(int _i) {return this.y1.get(_i);}
    public double get_y2i(int _i) {return this.y2.get(_i);}
    public double getY1() {return this.Y1.get(this.Y1.size() - 1);}
    public double getY2() {return this.Y2.get(this.Y2.size() - 1);}
    public double getT1() {return this.t1.get(this.t1.size() - 1);}
    public double getT2() {return this.t2.get(this.t2.size() - 1);}
    public int get_ySize() {return this.y1.size();}
}