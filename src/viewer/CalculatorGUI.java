package viewer;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI implements ActionListener {

    private Controller controller = new Controller();
    JFrame frame;
    static JTextField textField;
    JButton[] numButtons = new JButton[10];
    JButton[] functionButtons = new JButton[20];
    JButton addButton, subButton, mulButton, divButton, decButton;
    JButton equButton, tanButton, delButton, clrButton, negButton, sinButton;
    JButton cosButton, facButton, sqButton, sqrButton;
    JButton logButton, lnButton, piButton, euButton, paranButton;
    JPanel panel;
    Font numFont = new Font("Arial Unicode MS", Font.BOLD, 30);
    Font opFont = new Font("Arial Unicode MS", Font.ITALIC, 30);

    public CalculatorGUI() {
        frame = new JFrame("Taschenrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,800);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50,25,800,60);
        textField.setFont(numFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLC");
        negButton = new JButton("(-)");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        facButton = new JButton("!");
        sqButton = new JButton("^2");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        piButton = new JButton("π");
        euButton = new JButton("e");
        paranButton = new JButton("( )");
        sqrButton = new JButton("√");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = sinButton;
        functionButtons[10] = cosButton;
        functionButtons[11] = tanButton;
        functionButtons[12] = paranButton;
        functionButtons[13] = facButton;
        functionButtons[14] = sqButton;
        functionButtons[15] = logButton;
        functionButtons[16] = lnButton;
        functionButtons[17] = piButton;
        functionButtons[18] = euButton;
        functionButtons[19] = sqrButton;

        for (JButton functionButton : functionButtons) {
            functionButton.addActionListener(this);
            functionButton.setFont(opFont);
            functionButton.setFocusable(false);
        }

        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(numFont);
            numButtons[i].setFocusable(false);
        }

        negButton.setBounds(50,730,120,100);
        delButton.setBounds(300, 730, 120, 100);
        clrButton.setBounds(550, 730, 120, 100);

        panel = new JPanel();
        panel.setBounds(50,100,800,600);
        panel.setLayout(new GridLayout(6,6,10,10));

        panel.add(sinButton);
        panel.add(cosButton);
        panel.add(tanButton);
        panel.add(sqButton);
        panel.add(sqrButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(addButton);
        panel.add(facButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(logButton);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(mulButton);
        panel.add(lnButton);
        panel.add(numButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(paranButton);
        panel.add(piButton);
        panel.add(euButton);
        panel.add(negButton);
        panel.add(delButton);
        panel.add(clrButton);

        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        numberAssignment(e, numButtons, textField, decButton);
        if (e.getSource() == addButton) {
            controller.performOperation("+");
        }
        if (e.getSource() == subButton) {
            controller.performOperation("-");
        }
        if (e.getSource() == mulButton) {
            controller.performOperation("*");
        }
        if (e.getSource() == divButton) {
            controller.performOperation("/");
        }
        if (e.getSource() == equButton) {
            String expression = getText();
            String resultText = controller.calculateOperator((expression));
            setText(resultText);
        }
        if (e.getSource() == clrButton) {
            setText("");
        }
        if (e.getSource() == delButton) {
            controller.delete(getText());
        }
        if (e.getSource() == negButton) {
            setText(controller.negate(getText()));
        }
        if (e.getSource() == piButton) {
            setText(getText().concat(controller.getPI()));
        }
        if (e.getSource() == euButton) {
            setText(getText().concat(controller.getE()));
        }
        if (e.getSource() == sinButton) {
            setText(controller.getSin(getText()));
        }
        if (e.getSource() == cosButton) {
            setText(controller.getCos(getText()));
        }
        if (e.getSource() == tanButton) {
            setText(controller.getTan(getText()));
        }
        if (e.getSource() == lnButton) {
            setText(controller.getLn(getText()));
        }
        if (e.getSource() == logButton) {
            double arg = Double.parseDouble(getText());
            double base = 10;
            String baseInput = JOptionPane.showInputDialog(frame, "base: ");
            if (baseInput != null && !baseInput.isEmpty()) {
                if (baseInput.equalsIgnoreCase("e")) {
                    base = Math.E;
                } else {
                    try {
                        base = Double.parseDouble(baseInput);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showInputDialog(frame, "Invalid base. Using base = 10");
                    }
                }
            }
            setText(controller.getLog(String.valueOf(arg),String.valueOf(base)));
        }
        if (e.getSource() == facButton) {
            setText(controller.getFac(getText()));
        }
        if (e.getSource() == sqrButton) {
            setText(controller.getSqrt(getText()));
        }
        if (e.getSource() == sqButton) {
            setText(controller.getSquare(getText()));
        }

        if (e.getSource() == paranButton) {
            String input = JOptionPane.showInputDialog(frame, "Enter an expression: ");
            if (input != null && !input.isEmpty()) {
                setText(controller.evaluateExpression(input));
            }
        }
    }

    private void numberAssignment(ActionEvent e, JButton[] numButtons, JTextField textField, JButton decButton) {
        for (int i = 0; i < numButtons.length; i++) {
            if (e.getSource() == numButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
    }

    public static String getText() {
        return textField.getText();
    }
    public static void setText(String text) {
        textField.setText(text);
    }
}




