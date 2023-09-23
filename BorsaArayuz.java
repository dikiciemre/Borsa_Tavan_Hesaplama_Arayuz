package borsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


// Borsa Tavan Hesabı Arayüz Tasarımı Designed By Mustafa Emre Dikici.
// Borsada her tavan hissenin % 10 yükselişi anlamına gelmektedir.
// Yazılan bu kod kullanıcıdan maliyet, Tavan beklediği ya da hesaplamak istediği gün sayısı ve kaç adet (lot) hesaplanacağını sorar.

public class BorsaArayuz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Borsa Hesap Makinesi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel adetLabel = new JLabel("Adeti girin:");
        JTextField adetField = new JTextField();
        JLabel maliyetLabel = new JLabel("Maliyeti girin:");
        JTextField maliyetField = new JTextField();
        JLabel gunSayisiLabel = new JLabel("Gun sayisini girin:");
        JTextField gunSayisiField = new JTextField();
        JButton calculateButton = new JButton("Hesapla");

        inputPanel.add(adetLabel);
        inputPanel.add(adetField);
        inputPanel.add(maliyetLabel);
        inputPanel.add(maliyetField);
        inputPanel.add(gunSayisiLabel);
        inputPanel.add(gunSayisiField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(calculateButton);

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int adet = Integer.parseInt(adetField.getText());
                    double maliyet = Double.parseDouble(maliyetField.getText());
                    int gunSayisi = Integer.parseInt(gunSayisiField.getText());

                    StringBuilder resultText = new StringBuilder();
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");

                    for (int gun = 1; gun <= gunSayisi; gun++) {
                        double borsaTavan = maliyet * Math.pow(1.1, gun);
                        int kar = (int) ((borsaTavan * adet) - (maliyet * adet));
                        resultText.append("Gun ").append(gun).append(": Borsa Tavan ").append(decimalFormat.format(borsaTavan)).append(", Kar ").append(kar).append("\n");
                    }

                    resultTextArea.setText(resultText.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Lütfen geçerli sayısal değerler girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
