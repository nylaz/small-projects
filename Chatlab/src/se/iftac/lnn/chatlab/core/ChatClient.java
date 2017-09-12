package se.iftac.lnn.chatlab.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class ChatClient {

    private JButton sendButton;
    private JPanel panel1;
    private JTextArea Chat;
    private JButton logoutButton;
    private JButton loginButton;
    private JTextField chatInput;
    private JLabel chatClientLabel;
    private JTextArea textArea1;

    ChatUser tester = new ChatUser("nylazz", "Lars Nyman", ChatUserType.SELF, "192.168.1.1", "N/A");
    ChatUser superbot = new ChatUser("SUPER", "SUPERBOT", ChatUserType.FRIEND, "1.2.3.4", "N/A");

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ChatConnection con = new ChatConnection("chatlab.testbed.se", 8000);
        JFrame frame = new JFrame("ChatClient");
        ChatDAO chatDAO = new ChatDAO();
        ChatClient client = new ChatClient(con, chatDAO);
        ChatListener listener = new ChatListener(con, client, chatDAO);
        Thread listenthread = new Thread(listener);
        listenthread.start();

        frame.setContentPane(client.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ChatClient(ChatConnection con, ChatDAO chatDAO) throws SQLException, ClassNotFoundException, IOException {

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (chatInput.getText().length() < 3) {
                    try {
                        con.privateMessage(tester, superbot, chatInput.getText());
                        Chat.setText(Chat.getText() + "\n" + tester.getNickName() + ": " + chatInput.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    chatInput.setText("");
                } else {
                    String ss = chatInput.getText().substring(0, 3);
                    switch (ss) {
                        case "-pf":
                            try {
                                for (int i = 0; i < chatDAO.getFriends().size(); i++) {
                                    Chat.append("\n" + chatDAO.getFriends().get(i).getNickName());
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "-qf":
                            try {
                                ChatUser user = chatDAO.getChatUser(chatInput.getText().substring(4, chatInput.getText().length()));
                                Chat.append("\n" + "Nickname: " + user.getNickName() +
                                        "\n" + "Full name: " + user.getFullName() +
                                        "\n" + "IP: " + user.getIpAddress() +
                                        "\n" + "Info: " + user.getAdditionalInfo());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            try {
                                con.privateMessage(tester, superbot, chatInput.getText());
                                Chat.setText(Chat.getText() + "\n" + tester.getNickName() + ": " + chatInput.getText());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    chatInput.setText("");
                }
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    chatDAO.addChatUser(tester);
                    con.register(tester);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    con.logOut(tester);
                    Chat.setText(Chat.getText() + "\n" + "You are now offline");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void newLogin(ChatUser user) {
        Chat.setText(Chat.getText() + "\n" + user.getNickName() + " has logged on");
    }

    public void newLogout(ChatUser user) {
        Chat.setText(Chat.getText() + "\n" + user.getNickName() + " has logged off");
    }

    public void newPrivateMessage(String nick, String msg) {
        Chat.setText(Chat.getText() + "\n" + nick + ": " + msg);
    }

    public void newPublicMessage(String nick, String msg) {
        Chat.setText(Chat.getText() + "\n" + nick + ": " + msg);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-14134104));
        Chat = new JTextArea();
        Chat.setBackground(new Color(-9325357));
        Chat.setEditable(false);
        Chat.setForeground(new Color(-723457));
        Chat.setLineWrap(false);
        Chat.setMargin(new Insets(0, 0, 0, 0));
        Chat.setToolTipText("");
        panel1.add(Chat, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        logoutButton = new JButton();
        logoutButton.setText("Logout");
        panel1.add(logoutButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setText("Login");
        panel1.add(loginButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chatInput = new JTextField();
        chatInput.setBackground(new Color(-9325357));
        chatInput.setForeground(new Color(-723457));
        panel1.add(chatInput, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        sendButton = new JButton();
        sendButton.setBackground(new Color(-9325357));
        sendButton.setForeground(new Color(-723457));
        sendButton.setText("Send");
        panel1.add(sendButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chatClientLabel = new JLabel();
        chatClientLabel.setBackground(new Color(-14134104));
        Font chatClientLabelFont = this.$$$getFont$$$("Noto Mono", Font.BOLD | Font.ITALIC, -1, chatClientLabel.getFont());
        if (chatClientLabelFont != null) chatClientLabel.setFont(chatClientLabelFont);
        chatClientLabel.setForeground(new Color(-723457));
        chatClientLabel.setText("ChatClient");
        panel1.add(chatClientLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setName("online");
        panel1.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
