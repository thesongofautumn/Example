import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Formatter;

public class WindowResult {
    static private int i = 0;
    final private int WIDTH = 950, HEIGHT = 500;
    private static JFrame jFrame;
    private static JFrame jFrame1;

    private static JPanel jPanel;
    private static JPanel jPanel1;
    private static JPanel panelResult;

    private static JLabel labelTitle;
    private static JLabel labelResult1;
    private static JLabel labelResult2;
    private static JLabel labelResult3;
    private static JLabel labelResult4;
    private static JLabel labelResult5;
    private static JLabel labelResult6;
    private static JLabel labelResult7;
    private static JLabel labelResult8;
    private static JLabel labelResult9;

    private Graph graph;

    final private DecimalFormat decimalFormat = new DecimalFormat("#.0000000000");

    final private static String stringEquation1 = "Equation 1";
    final private static String stringEquation2 = "Equation 2";
    final private static String string_f = "f = ";
    final private static String string_f1 = "f1 = ";
    final private static String string_f2 = "f2 = ";
    final private static String string_yi = "yi = ";
    final private static String string_y1 = "Y1 = ";
    final private static String string_y2 = "Y2 = ";
    final private static String stringT1 = "t(Y1) = ";
    final private static String stringT2 = "t(Y2) = ";
    final private static String stringE = "E = ";
    final private static String stringI = "Quantity iterations = ";

    private JFrame getjFrame(boolean flag) {
        JFrame jf = new JFrame(){};

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setSize(WIDTH, HEIGHT);
        if (flag){
            jf.setLocation(i, i+HEIGHT);
            return jf;
        }
        jf.setLocation(i, i);
        return jf;
    }

    public WindowResult(Equation1 equation1) {
        jPanel = new JPanel();
        jPanel1 = new JPanel();
        panelResult = new JPanel();
        graph = new Graph(equation1);

        this.window1(equation1);
        this.window2();
    }

    private void window1(Equation1 equation1){
        jFrame = this.getjFrame(false);
        jFrame.add(jPanel);
        jPanel.add(panelResult, BorderLayout.EAST);
        jPanel.add(graph.getjPanel(), BorderLayout.CENTER);

        panelResult.setLayout(new GridLayout(5, 1, 0, 2));
        panelResult.setBorder(BorderFactory.createEmptyBorder(5,5,30,30));

        labelTitle = new JLabel(stringEquation1);
        labelResult1 = new JLabel(string_f + new Double(equation1.get_f()).toString());
        labelResult2 = new JLabel(stringE + new Double(equation1.getE()).toString());
        labelResult3 = new JLabel(string_yi + decimalFormat.format(new Double(equation1.getLast_y())));
        labelResult4 = new JLabel(stringI + new Integer(equation1.getI()).toString());

        panelResult.add(labelTitle);
        panelResult.add(labelResult1);
        panelResult.add(labelResult2);
        panelResult.add(labelResult3);
        panelResult.add(labelResult4);

        jPanel.revalidate();
        i+=10;
    }

    private void window2(){
        jFrame1 = this.getjFrame(true);
        jFrame1.add(jPanel1);
        jPanel1.add(graph.getjPanel1());
        jPanel1.revalidate();
    }

    public WindowResult(Equation2 equation2) {
        jFrame = this.getjFrame(false);
        jPanel = new JPanel();
        panelResult = new JPanel();
        graph = new Graph(equation2);

        jFrame.add(jPanel);
        jPanel.add(panelResult, BorderLayout.EAST);
        jPanel.add(graph.getjPanel(), BorderLayout.CENTER);

        panelResult.setLayout(new GridLayout(10, 2, 0, 2));
        panelResult.setBorder(BorderFactory.createEmptyBorder(5,5,30,30));

        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder);
        formatter.format("y = {%s ,   %s}", decimalFormat.format(new Double(equation2.get_y1())),
                                          decimalFormat.format(new Double(equation2.get_y2())));

        labelTitle = new JLabel(stringEquation2);
        labelResult1 = new JLabel(stringE + new Double(equation2.getE()).toString());
        labelResult2 = new JLabel(string_f1 + new Double(equation2.get_f1()).toString());
        labelResult3 = new JLabel(string_f2 + new Double(equation2.get_f2()).toString());
        labelResult4 = new JLabel(string_y1 + decimalFormat.format(new Double(equation2.getY1())));
        labelResult5 = new JLabel(string_y2 + decimalFormat.format(new Double(equation2.getY2())));
        labelResult6 = new JLabel(stringBuilder.toString());
        labelResult7 = new JLabel(stringT1 + decimalFormat.format(new Double(equation2.getT1())));
        labelResult8 = new JLabel(stringT2 + decimalFormat.format(new Double(equation2.getT2())));
        labelResult9 = new JLabel(stringI + new Integer(equation2.getI()).toString());

        panelResult.add(labelTitle);
        panelResult.add(labelResult1);
        panelResult.add(labelResult2);
        panelResult.add(labelResult3);
        panelResult.add(labelResult4);
        panelResult.add(labelResult5);
        panelResult.add(labelResult6);
        panelResult.add(labelResult7);
        panelResult.add(labelResult8);
        panelResult.add(labelResult9);

        jPanel.revalidate();
        i+=10;
    }
}