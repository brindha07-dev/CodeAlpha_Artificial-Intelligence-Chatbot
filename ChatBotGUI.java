import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChatBotGUI {

    private JTextArea chatArea;
    private JTextField inputField;
    private ChatBot bot;
    private final String FILE_NAME = "chat_history.txt";

    public ChatBotGUI() {

        bot = new ChatBot();

        JFrame frame = new JFrame("CodeAlpha AI ChatBot");
        frame.setSize(450, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 60));

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 255));
        chatArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input Field
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        inputField.setBackground(Color.WHITE);

        // Send Button
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(100, 149, 237));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));

        // Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(220, 53, 69));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 60));
        panel.add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        loadChatHistory();

        // Send Button Action
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Enter Key Action
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Clear Button Action
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearChat();
            }
        });

        frame.setVisible(true);
    }

    private void sendMessage() {

        String userText = inputField.getText();

        if (!userText.isEmpty()) {

            appendToChat("You: " + userText, new Color(0, 102, 204));
            inputField.setText("");

            appendToChat("Bot is typing...", Color.GRAY);

            Timer timer = new Timer(1200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    chatArea.setText(chatArea.getText().replace("Bot is typing...\n", ""));

                    String response = bot.getResponse(userText);
                    appendToChat("Bot: " + response, new Color(153, 0, 153));
                }
            });

            timer.setRepeats(false);
            timer.start();
        }
    }

    private void appendToChat(String message, Color color) {

        chatArea.setForeground(color);
        chatArea.append(message + "\n");

        saveChatHistory(message);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void clearChat() {

        chatArea.setText("");

        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    private void saveChatHistory(String message) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = br.readLine()) != null) {
                    chatArea.append(line + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
