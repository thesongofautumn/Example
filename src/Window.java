import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

public class Window {
    private static Window window;

    final private static int WIDTH = 400, HEIGHT = 300;
    private static JFrame jFrame;
    private static Toolkit toolkit;
    private static Dimension dimension;

    private static JPanel jPanel;
    private static JPanel choicePanel;
    private static JPanel card1;
    private static JPanel card2;
    private static JPanel cards;
    private static JPanel textPanelE1;
    private static JPanel textPanelE2;
    private static JPanel enterPanelE_1;
    private static JPanel enterPanelE_2;
    private static JPanel enterPanelF;
    private static JPanel enterPanel_f1;
    private static JPanel enterPanel_f2;
    private static JPanel errorPanel1;
    private static JPanel errorPanel2;
    private static JPanel buttonPanel;

    private static JComboBox<String> comboBox;
    private static JLabel labelCoefsE1;
    private static JLabel labelCoefsE2;
    private static JLabel labelEnterE_1;
    private static JLabel labelEnterE_2;
    private static JLabel labelEnterF;
    private static JLabel labelEnter_f1;
    private static JLabel labelEnter_f2;
    private static JLabel labelError1;
    private static JLabel labelError2;
    private static JTextField textFieldE_1;
    private static JTextField textFieldE_2;
    private static JTextField textFieldF;
    private static JTextField textField_f1;
    private static JTextField textField_f2;
    private static JButton jButton;

    private static KeyStroke keyStroke;
    private static InputMap inputMap;
    private static ActionMap actionMap;

    final private static String stringCoefOk = "The coefficients satisfy the necessary conditions of the method";
    final private static String stringCoefError = "The coefficients do not satisfy the necessary conditions of the method";
    final private static String stringErrorCoefs = "Error! f greater than or equal to F";
    final private static String stringErrorE = "Error! E greater than or equal to 1";
    final private static String stringErrorSymbols = "Error! Invalid symbols";
    final private static String stringErrorEmpty = "Error! Empty";
    final private static String stringButton = "Computing";
    final private static String stringEnterE = "E: ";
    final private static String stringEnterF = "Choose an f that is less than F = ";
    final private static String stringEnter_f1 = "f1: ";
    final private static String stringEnter_f2 = "f2: ";
    final private static String stringEQUATION1 = "Equation 1";
    final private static String stringEQUATION2 = "Equation 2";

    private JFrame getjFrame() {
        JFrame jf = new JFrame(){};
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(WIDTH, HEIGHT);
        jf.setLocation(dimension.width/2 - WIDTH/2, dimension.height/2 - HEIGHT/2);
        jf.setTitle("Computing");
        return jf;
    }

    private Window() {
        jFrame = this.getjFrame();
        jPanel = new JPanel();
        choicePanel = new JPanel();
        card1 = new JPanel();
        card2 = new JPanel();
        cards = new JPanel();
        textPanelE1 = new JPanel();
        textPanelE2 = new JPanel();
        enterPanelE_1 = new JPanel();
        enterPanelE_2 = new JPanel();
        enterPanelF = new JPanel();
        enterPanel_f1 = new JPanel();
        enterPanel_f2 = new JPanel();
        errorPanel1 = new JPanel();
        errorPanel2 = new JPanel();
        buttonPanel = new JPanel();

        jFrame.add(jPanel);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(choicePanel, BorderLayout.NORTH);
        jPanel.add(cards, BorderLayout.CENTER);
        jPanel.add(buttonPanel, BorderLayout.SOUTH);

        comboBox = new JComboBox<>(new String[]{stringEQUATION1, stringEQUATION2});
        comboBox.setEditable(false);
        comboBox.addItemListener(new MyListener());
        choicePanel.add(comboBox);

        labelCoefsE1 = new JLabel();
        labelCoefsE2 = new JLabel();
        textPanelE1.add(labelCoefsE1);
        textPanelE2.add(labelCoefsE2);

        labelEnterE_1 = new JLabel(stringEnterE);
        enterPanelE_1.add(labelEnterE_1);
        textFieldE_1 = new JTextField("0.000001",7);
        textFieldE_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterPanelE_1.add(textFieldE_1);

        labelEnterE_2 = new JLabel(stringEnterE);
        enterPanelE_2.add(labelEnterE_2);
        textFieldE_2 = new JTextField("0.000001",7);
        textFieldE_2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterPanelE_2.add(textFieldE_2);

        labelEnterF = new JLabel(stringEnterF + new String(new Double(new Equation1().getF()).toString()));
        enterPanelF.add(labelEnterF);
        textFieldF = new JTextField(5);
        textFieldF.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterPanelF.add(textFieldF);
        
        labelEnter_f1 = new JLabel(stringEnter_f1);
        enterPanel_f1.add(labelEnter_f1);
        textField_f1 = new JTextField(5);
        textField_f1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterPanel_f1.add(textField_f1);

        labelEnter_f2 = new JLabel(stringEnter_f2);
        enterPanel_f2.add(labelEnter_f2);
        textField_f2 = new JTextField(5);
        textField_f2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterPanel_f2.add(textField_f2);

        labelError1 = new JLabel();
        labelError1.setForeground(Color.RED); 
        errorPanel1.add(labelError1);
        labelError1.setVisible(false);

        labelError2 = new JLabel();
        labelError2.setForeground(Color.RED);
        errorPanel2.add(labelError2);
        labelError2.setVisible(false);

        card1.setLayout(new GridLayout(4,1,0,0));
        card1.add(textPanelE1);
        card1.add(enterPanelE_1);
        card1.add(enterPanelF);
        card1.add(errorPanel1);

        card2.setLayout(new GridLayout(5,1,0,0));
        card2.add(textPanelE2);
        card2.add(enterPanelE_2);
        card2.add(enterPanel_f1);
        card2.add(enterPanel_f2);
        card2.add(errorPanel2);

        cards.setLayout(new CardLayout());
        cards.add(card1, stringEQUATION1);
        cards.add(card2, stringEQUATION2);

        jButton = new JButton(stringButton);
        buttonPanel.add(jButton);
        jButton.addActionListener(new MyAction());

        jPanel.revalidate();

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap = jPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, "e");
        actionMap = jPanel.getActionMap();
        actionMap.put("e", new MyAction());
    }

    public static void main(String[] args) {
        window = new Window();
        window.conditionsForCoefs();
    }

    private void conditionsForCoefs(){
        if (new Equation1().conditionsForCoefficients())
            labelCoefsE1.setText(stringCoefOk);
        else {
            labelCoefsE1.setText(stringCoefError);
            labelCoefsE1.setBackground(Color.RED);
            enterPanelE_1.setVisible(false);
            enterPanelF.setVisible(false);
            errorPanel1.setVisible(false);
        }

        if (new Equation2().conditionsForCoefficients())
            labelCoefsE2.setText(stringCoefOk);
        else {
            labelCoefsE2.setText(stringCoefError);
            labelCoefsE2.setBackground(Color.RED);
            enterPanelE_2.setVisible(false);
            enterPanel_f1.setVisible(false);
            enterPanel_f2.setVisible(false);
            errorPanel2.setVisible(false);
        }
    }

    static class MyListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            CardLayout cardLayout = (CardLayout)(cards.getLayout());
            cardLayout.show(cards, (String)e.getItem());
        }
    }

    static class MyAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (comboBox.getSelectedItem().toString() == stringEQUATION1) {
                textFieldE_1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                textFieldF.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                double tempE = this.checkTextField(textFieldE_1, labelError1);
                if (tempE == new Equation1().getF() + 1)
                    return;
                if (!checkE(textFieldE_1, labelError1, tempE))
                    return;

                double temp_f = this.checkTextField(textFieldF, labelError1);
                if (temp_f == new Equation1().getF() + 1)
                    return;
                if (temp_f >= new Equation1().getF()) {
                    labelError1.setText(stringErrorCoefs);
                    labelError1.setVisible(true);
                    textFieldF.setBorder(BorderFactory.createLineBorder(Color.RED));
                    return;
                }

                labelError1.setVisible(false);

                Equation1 computing = new Equation1();
                computing.set_f(temp_f);
                computing.setE(tempE);
                computing.decision();

                new WindowResult(computing);
            } else {
                textFieldE_2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                textField_f1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                textField_f2.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                double tempE = this.checkTextField(textFieldE_2, labelError2);
                if (tempE == new Equation1().getF() + 1)
                    return;
                if (!checkE(textFieldE_2, labelError2, tempE))
                    return;

                double temp_f1 = this.checkTextField(textField_f1, labelError2);
                if (temp_f1 == new Equation1().getF() + 1)
                    return;

                double temp_f2 = this.checkTextField(textField_f2, labelError2);
                if (temp_f2 == new Equation1().getF() + 1)
                    return;

                labelError2.setVisible(false);

                Equation2 computing = new Equation2();
                computing.setE(tempE);
                computing.set_f(temp_f1, temp_f2);
                computing.decision();

                new WindowResult(computing);
            }
        }

        private double checkTextField(JTextField jt, JLabel jl){
            double temp ;
            if (jt.getText().trim().length() == 0) {
                jl.setText(stringErrorEmpty);
                jl.setVisible(true);
                jt.setBorder(BorderFactory.createLineBorder(Color.RED));
                return new Equation1().getF() + 1;  //признак ошибки
            }
            try {
                temp = Double.parseDouble(jt.getText());
            } catch (Exception ex) {
                jl.setText(stringErrorSymbols);
                jl.setVisible(true);
                jt.setBorder(BorderFactory.createLineBorder(Color.RED));
                return new Equation1().getF() + 1; //признак ошибки
            }
            return temp;
        }

        private boolean checkE (JTextField jt, JLabel jl, double tempE){
            if ((tempE >= 1) || (tempE <= 0)) {
                jl.setText(stringErrorE);
                jl.setVisible(true);
                jt.setBorder(BorderFactory.createLineBorder(Color.RED));
                return false;
            }
            return true;
        }
    }
}