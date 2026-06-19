import javax.swing.*;
import java.awt.*;

public class SubnetCalculatorGUI extends JFrame {

    public SubnetCalculatorGUI() {
        setTitle("TCP/IP IPv4 Subnet Calculator");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        JPanel panelA = new JPanel(new GridLayout(6, 1));
        JTextField ipA = new JTextField();
        JTextField maskA = new JTextField();
        JTextArea outA = new JTextArea();
        JButton btnA = new JButton("Llogarit");

        panelA.add(new JLabel("IP Address:"));
        panelA.add(ipA);
        panelA.add(new JLabel("Subnet Mask:"));
        panelA.add(maskA);
        panelA.add(btnA);
        panelA.add(new JScrollPane(outA));

        btnA.addActionListener(e -> {
            try {
                if (!IPUtils.isValidIP(ipA.getText()))
                    throw new IllegalArgumentException("IP e pavlefshme");

                if (!IPUtils.isValidSubnetMask(maskA.getText()))
                    throw new IllegalArgumentException("Subnet Mask e pavlefshme");

                outA.setText(
                        PartA.calculate(ipA.getText(), maskA.getText())
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Gabim Inputi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        JPanel panelB = new JPanel(new GridLayout(6, 1));
        JTextField netB = new JTextField();
        JTextField maskB = new JTextField();
        JTextArea outB = new JTextArea();
        JButton btnB = new JButton("Llogarit");

        panelB.add(new JLabel("Network ID:"));
        panelB.add(netB);
        panelB.add(new JLabel("Subnet Mask:"));
        panelB.add(maskB);
        panelB.add(btnB);
        panelB.add(new JScrollPane(outB));

        btnB.addActionListener(e -> {
            try {
                if (!IPUtils.isValidNetworkID(netB.getText(), maskB.getText()))
                    throw new IllegalArgumentException("Network ID nuk është e vlefshme");

                if (!IPUtils.isValidSubnetMask(maskB.getText()))
                    throw new IllegalArgumentException("Subnet Mask e pavlefshme");

                outB.setText(
                        PartB.calculate(netB.getText(), maskB.getText())
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Gabim Inputi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        JPanel panelC = new JPanel(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField netC = new JTextField();
        JTextField maskC = new JTextField();
        JTextField subsC = new JTextField();

        inputPanel.add(new JLabel("Network ID:"));
        inputPanel.add(netC);
        inputPanel.add(new JLabel("Subnet Mask:"));
        inputPanel.add(maskC);
        inputPanel.add(new JLabel("Numri i Subneteve:"));
        inputPanel.add(subsC);

        JTextArea outC = new JTextArea();
        outC.setFont(new Font("Consolas", Font.PLAIN, 14));
        outC.setEditable(false);
        outC.setLineWrap(true);
        outC.setWrapStyleWord(true);

        JScrollPane scrollC = new JScrollPane(outC);
        scrollC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton btnC = new JButton("Gjenero Subnete");
        
        panelC.add(inputPanel, BorderLayout.NORTH);
        panelC.add(scrollC, BorderLayout.CENTER);
        panelC.add(btnC, BorderLayout.SOUTH);


        btnC.addActionListener(e -> {
            try {
                if (!IPUtils.isValidNetworkID(netC.getText(), maskC.getText()))
                    throw new IllegalArgumentException(
                            "Network ID e pavlefshme "
                    );
                if (!IPUtils.isValidSubnetMask(maskC.getText()))
                    throw new IllegalArgumentException("Subnet Mask e pavlefshme");

                int subnets;
                try {
                    subnets = Integer.parseInt(subsC.getText());
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Numri i subneteve duhet të jetë numër");
                }

                if (subnets <= 0)
                    throw new IllegalArgumentException("Numri i subneteve duhet > 0");

                outC.setText(
                        PartC.calculate(netC.getText(), maskC.getText(), subnets)
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Gabim Inputi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        JPanel panelD = new JPanel(new GridLayout(6, 1));
        JTextField ipD = new JTextField();
        JTextField maskD = new JTextField();
        JTextArea outD = new JTextArea();
        JButton btnD = new JButton("Analizo IP");

        panelD.add(new JLabel("IP Address:"));
        panelD.add(ipD);
        panelD.add(new JLabel("Subnet Mask:"));
        panelD.add(maskD);
        panelD.add(btnD);
        panelD.add(new JScrollPane(outD));

        btnD.addActionListener(e -> {
            try {
                if (!IPUtils.isValidIP(ipD.getText()))
                    throw new IllegalArgumentException("IP Address e pavlefshme");

                if (!IPUtils.isValidSubnetMask(maskD.getText()))
                    throw new IllegalArgumentException("Subnet Mask e pavlefshme");

                outD.setText(
                        PartD.analyzeIP(ipD.getText(), maskD.getText())
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Gabim Inputi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        tabs.add("Pika A", panelA);
        tabs.add("Pika B", panelB);
        tabs.add("Pika C", panelC);
        tabs.add("Pika D", panelD);

        add(tabs);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SubnetCalculatorGUI();
    }
}


