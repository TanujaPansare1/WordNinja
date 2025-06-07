import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.util.regex.*;

public class WordNinja extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton btnCountChars, btnCountWords, btnCountSentences;
    private JButton btnToUpper, btnToLower, btnRemoveSpecialChars, btnCopy;

    public WordNinja() {
        setTitle("WordNinja – Text Utilities");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputArea = new JTextArea(6, 40);
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(230, 230, 230));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        btnCountChars = new JButton("Count Characters");
        btnCountWords = new JButton("Count Words");
        btnCountSentences = new JButton("Count Sentences");
        btnToUpper = new JButton("To Uppercase");
        btnToLower = new JButton("To Lowercase");
        btnRemoveSpecialChars = new JButton("Remove Special Chars");
        btnCopy = new JButton("Copy Output");

        buttonPanel.add(btnCountChars);
        buttonPanel.add(btnCountWords);
        buttonPanel.add(btnCountSentences);
        buttonPanel.add(btnToUpper);
        buttonPanel.add(btnToLower);
        buttonPanel.add(btnRemoveSpecialChars);
        buttonPanel.add(btnCopy);

        JScrollPane inputScroll = new JScrollPane(inputArea);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(new JLabel("Input Text:"), BorderLayout.NORTH);
        mainPanel.add(inputScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.NORTH);
        contentPane.add(outputPanel, BorderLayout.CENTER);

        // Action listeners
        btnCountChars.addActionListener(e -> {
            String text = inputArea.getText();
            outputArea.setText("Character Count: " + text.length());
        });

        btnCountWords.addActionListener(e -> {
            String text = inputArea.getText().trim();
            if (text.isEmpty()) {
                outputArea.setText("Word Count: 0");
            } else {
                int count = 0;
                Matcher m = Pattern.compile("\\b\\w+\\b").matcher(text);
                while (m.find()) count++;
                outputArea.setText("Word Count: " + count);
            }
        });

        btnCountSentences.addActionListener(e -> {
            String text = inputArea.getText().trim();
            if (text.isEmpty()) {
                outputArea.setText("Sentence Count: 0");
            } else {
                String[] sentences = text.split("[.!?]+");
                outputArea.setText("Sentence Count: " + sentences.length);
            }
        });

        btnToUpper.addActionListener(e -> {
            outputArea.setText(inputArea.getText().toUpperCase());
        });

        btnToLower.addActionListener(e -> {
            outputArea.setText(inputArea.getText().toLowerCase());
        });

        btnRemoveSpecialChars.addActionListener(e -> {
            String cleaned = inputArea.getText().replaceAll("[^a-zA-Z0-9\\s]", "");
            outputArea.setText(cleaned);
        });

        btnCopy.addActionListener(e -> {
            StringSelection selection = new StringSelection(outputArea.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            JOptionPane.showMessageDialog(this, "Output copied to clipboard!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordNinja app = new WordNinja();
            app.setVisible(true);
        });
    }
}
