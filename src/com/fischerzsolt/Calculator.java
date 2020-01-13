package com.fischerzsolt;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame
{
    ImageIcon img;

    private JPanel mainPanel;
    private JPanel numbersPanel;
    private JPanel buttonsPanel;

    private JLabel firstNumber;
    private JLabel secondNumber;
    private JLabel result;

    private JTextField firstNumberField;
    private JTextField secondNumberField;

    private JButton plus;
    private JButton minus;
    private JButton multi;
    private JButton div;


    public Calculator() throws HeadlessException
    {
        setJFrame();
        initComponents();
        setComponents();
        setActionListeners();
    }

    private void setJFrame()
    {
        setTitle("Számológép");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        img = new ImageIcon("calculator.png");
        setIconImage(img.getImage());
    }

    private void initComponents()
    {
        mainPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        numbersPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        buttonsPanel = new JPanel(new GridLayout(5, 1, 10, 15));

        firstNumber = new JLabel("Első szám");
        secondNumber = new JLabel("Második szám");
        result = new JLabel("Eredmény: -");

        firstNumberField = new JTextField();
        secondNumberField = new JTextField();

        plus = new JButton("+");
        minus = new JButton("-");
        multi = new JButton("*");
        div = new JButton("/");
    }

    private void setComponents()
    {
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        numbersPanel.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 30, 200));
        firstNumberField.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

        result.setHorizontalAlignment(SwingConstants.CENTER);

        numbersPanel.add(firstNumber);
        numbersPanel.add(firstNumberField);
        numbersPanel.add(secondNumber);
        numbersPanel.add(secondNumberField);

        buttonsPanel.add(result);
        buttonsPanel.add(plus);
        buttonsPanel.add(minus);
        buttonsPanel.add(multi);
        buttonsPanel.add(div);

        mainPanel.add(numbersPanel);
        mainPanel.add(buttonsPanel);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void setActionListeners()
    {
        plus.addActionListener(actionEvent -> {
            Double[] numbers = checkValidation();
            if (numbers != null)
            {
                result.setText("Eredmény: " + (numbers[0] + numbers[1]));
            }
        });

        minus.addActionListener(actionEvent -> {
            Double[] numbers = checkValidation();
            if (numbers != null)
            {
                result.setText("Eredmény: " + (numbers[0] - numbers[1]));
            }
        });

        multi.addActionListener(actionEvent -> {
            Double[] numbers = checkValidation();
            if (numbers != null)
            {
                result.setText("Eredmény: " + (numbers[0] * numbers[1]));
            }
        });

        div.addActionListener(actionEvent -> {
            Double[] numbers = checkValidation();
            if (numbers != null)
            {
                result.setText("Eredmény: " + (numbers[0] / numbers[1]));
            }
        });
    }

    private Double[] checkValidation()
    {
        result.setText("Eredmény: ");
        String number1 = firstNumberField.getText();
        String number2 = secondNumberField.getText();
        if (number1 == null || number1.equals("") || number2 == null || number2.equals(""))
        {
            JOptionPane.showOptionDialog(null,
                    "Mindkét szám megadása kötelező!",
                    "Hiba",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    null,
                    null);
        }
        else
        {
            try
            {
                double first = Double.parseDouble(number1);
                double second = Double.parseDouble(number2);

                return new Double[]{first, second};
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showOptionDialog(null,
                        "A megadott értékeknek számoknak kell lenniük!",
                        "Hiba",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        null,
                        null);
            }
        }
        return null;
    }
}
