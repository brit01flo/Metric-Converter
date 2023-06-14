import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UnitConverterGUI extends JFrame implements ActionListener {
    // GUI Components
    private JLabel fromLabel, toLabel, quantityLabel, resultLabel;
    private JComboBox<String> fromUnitBox, toUnitBox;
    private JTextField quantityField, resultField;
    private JButton convertButton;

    // Conversion Constants
    private static final double METERS_PER_FOOT = 0.3048;
    private static final double KGS_PER_POUND = 0.453592;

    public UnitConverterGUI() {
        super("Unit Converter");

        // Initialize GUI Components
        fromLabel = new JLabel("From:");
        toLabel = new JLabel("To:");
        quantityLabel = new JLabel("Quantity:");
        resultLabel = new JLabel("Result:");

        fromUnitBox = new JComboBox<>(new String[] { "Feet", "Pounds", "Fahrenheit" });
        toUnitBox = new JComboBox<>(new String[] { "Meters", "Kgs", "Celcius" });

        quantityField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        // Layout GUI Components
        JPanel fromPanel = new JPanel(new GridLayout(1, 2));
        fromPanel.add(fromLabel);
        fromPanel.add(fromUnitBox);

        JPanel toPanel = new JPanel(new GridLayout(1, 2));
        toPanel.add(toLabel);
        toPanel.add(toUnitBox);

        JPanel quantityPanel = new JPanel(new GridLayout(1, 2));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityField);

        JPanel resultPanel = new JPanel(new GridLayout(1, 2));
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);

        JPanel inputPanel = new JPanel(new GridLayout(4, 1));
        inputPanel.add(fromPanel);
        inputPanel.add(toPanel);
        inputPanel.add(quantityPanel);
        inputPanel.add(resultPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(convertButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit = (String) toUnitBox.getSelectedItem();
            double quantity = Double.parseDouble(quantityField.getText());

            double result = 0;
            switch (fromUnit) {
                case "Feet":
                    if (toUnit.equals("Meters")) {
                        result = quantity * METERS_PER_FOOT;
                    }
                    break;
                case "Pounds":
                    if (toUnit.equals("Kgs")) {
                        result = quantity * KGS_PER_POUND;
                    }
                    break;
                case "Fahrenheit":
                    if (toUnit.equals("Celcius")) {
                        result = (quantity - 32) * 5 / 9;
                    }
                    break;
            }

            resultField.setText(String.format("%.2f", result));
        }
    }

    public static void main(String[] args) {
        new UnitConverterGUI();
    }
}