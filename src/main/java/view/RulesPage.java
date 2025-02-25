package view;

import javax.swing.*;
import java.awt.*;

public class RulesPage extends JFrame {
    public RulesPage() {
        setTitle("Rules & Regulations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255));

        // ====== TITLE ======
        JLabel lblTitle = new JLabel("Apartment Rules", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 128, 128));
        panel.add(lblTitle, BorderLayout.NORTH);

        // ====== RULES LIST ======
        String[] rules = {
            "1. No loud noise after 10 PM.",
            "2. No pets allowed without permission.",
            "3. Maintain cleanliness, do not litter.",
            "4. Do not modify the apartment structure without approval.",
            "5. Pay fees on time as per regulations.",
            "6. Follow fire safety regulations.",
            "7. No use of illegal substances or drugs.",
            "8. Visitors must register with security.",
            "9. No parties that disturb other residents.",
            "10. Protect shared property and avoid damage."};

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String rule : rules) {
            listModel.addElement(rule);
        }

        JList<String> list = new JList<>(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 18));
        list.setForeground(Color.WHITE);
        list.setBackground(new Color(0, 128, 128));
        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane, BorderLayout.CENTER);

        // ====== BACK BUTTON ======
        JButton btnBack = new JButton("◄ Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(0, 100, 100));
        btnBack.setFocusPainted(false);
        btnBack.setBorder(null);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(new Color(200, 200, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(Color.WHITE);
            }
        });
        
        btnBack.addActionListener(e -> {
            new CustomerPage().setVisible(true);
            dispose();
        });
        
        JPanel panelBottom = new JPanel();
        panelBottom.add(btnBack);
        panel.add(panelBottom, BorderLayout.SOUTH);
        
        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RulesPage().setVisible(true));
    }
}
