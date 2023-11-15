import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MainForm implements ActionListener
{
    private final Calculator calc;
    private TextField resultText;
    private String text = "";
    private Calculator.CalcModes calcModes;

    public MainForm()
    {
        createWindow();
        calc = new Calculator();
        calc.init();
    }

    private void createWindow()
    {
        JFrame frame = new JFrame("Calc");
        frame.setBounds(100, 100, 600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        resultText = new TextField("Hello!");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btn0 = new JButton("0");
        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("-");
        JButton btnMultiplication = new JButton("×");
        JButton btnDivision = new JButton("÷");
        JButton btnAllClear = new JButton("AC");
        JButton btnSquareRoot = new JButton("√");
        JButton btnSin = new JButton("sin");
        JButton btnCos = new JButton("cos");
        JButton btnTan = new JButton("tan");
        JButton btnResult = new JButton("=");
        List<JButton> buttons = Arrays.asList(btn0, btn1, btn2, btn3, btn4, btn5,
                btn6, btn7, btn8, btn9, btnPlus, btnMinus, btnMultiplication, btnDivision, btnAllClear,
                btnSquareRoot, btnSin, btnCos, btnTan, btnResult);

        resultText.setEditable(false);
        resultText.setPreferredSize(new Dimension(500, 200));
        Font defaultFont = new Font("Serif", Font.PLAIN, 24);
        resultText.setFont(defaultFont);
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(resultText, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttons.forEach(i -> {
            i.setFont(defaultFont);
            i.setPreferredSize(new Dimension(100, 100));
            i.addActionListener(this);
            buttonPanel.add(i);
        });
        container.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        final Object source = e.getSource();
        JButton clickedButton;
        String buttonText;
        try
        {
            clickedButton = (JButton) source;
            buttonText = clickedButton.getText();
        }
        catch (Exception exception)
        {
            System.out.println("An error has occurred: " + exception.getMessage());
            return;
        }

        PushButton(buttonText);
    }

    private void PushButton(String buttonText)
    {
        switch (buttonText)
        {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9":
                text += buttonText;
                resultText.setText(text);
                break;
            case "+", "-", "×", "÷":
                ClickBasicArithmeticOperations(buttonText);
                break;
            case "√", "sin", "cos", "tan":
                CreateErrorWindow("未実装の機能です");
                break;
            case "AC":
                AllClear();
                break;
            case "=":
                if (text == "") break;
                calc.SetNum(Double.parseDouble(text));
                if (calc.CheckState() == Calculator.InputState.full)
                {
                    resultText.setText(Double.toString(calc.Calc(calcModes)));
                }
                break;
            default:
        }
    }

    private void ClickBasicArithmeticOperations(String symbol)
    {
        if (text.equals("")) return;
        calc.SetNum(Double.parseDouble(text));
        if (calc.CheckState() == Calculator.InputState.full)
        {
            calc.SetNum(calc.Calc(calcModes), Calculator.InputState.first);
            calc.SetNum(-1, Calculator.InputState.second);
        }
        switch (symbol) {
            case "+":
                calcModes = Calculator.CalcModes.add;
                break;
            case "-":
                calcModes = Calculator.CalcModes.minus;
                break;
            case "×":
                calcModes = Calculator.CalcModes.multiplication;
                break;
            case "÷":
                if (calc.CheckDivisionByZero())
                {
                    AllClear();
                    CreateErrorWindow("計算不能");
                }
                calcModes = Calculator.CalcModes.division;
                break;
        }
        text = "";
        resultText.setText(text);
    }

    private void AllClear()
    {
        text = "";
        calc.init();
        resultText.setText(text);
        calcModes = Calculator.CalcModes.add;
    }

    private void CreateErrorWindow(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
