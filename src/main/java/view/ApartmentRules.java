package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ApartmentRules extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblImgRules;
    private JEditorPane editorPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                var frame = new ApartmentRules();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ApartmentRules() {
        setTitle("Apartment Rules");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        var panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 1276, 850);
        contentPane.add(panel);
        panel.setLayout(null);

        // ====== Nút Back ======
        var btnBack = new JButton("◄ Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(64, 128, 128));
        btnBack.setBorder(null);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(10, 10, 100, 30);
        btnBack.addActionListener(e -> {
            new QuanLyChungCuGUI().setVisible(true);
            dispose();
        });
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(new Color(200, 200, 200));
            }
            @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(Color.WHITE);
            }
        });
        panel.add(btnBack);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(
				"""
				<html><body style='font-family:Arial; font-size:14px; text-align:justify;'>\r
				    <h2 style='text-align:center;'>APARTMENT REGULATIONS</h2>\r
				    <b>1. GENERAL REGULATIONS</b><br>All residents and visitors must comply with the apartment regulations.<br><br>\r
				    <b>2. SAFETY AND SECURITY</b><br>Do not cause disturbances or loud noises from <span style='color:red;'>22:00 - 06:00</span>.<br><br>\r
				    <b>3. ENVIRONMENTAL HYGIENE</b><br>Maintain cleanliness, do not litter.<br><br>\r
				    <b>4. USE OF COMMON FACILITIES</b><br>Do not occupy hallways or common areas for personal storage.<br><br>\r
				    <b>5. PET MANAGEMENT</b><br>Pets are only allowed with approval from the apartment management board.<br><br>\r
				    <b>6. FIRE PREVENTION AND CONTROL</b><br>No smoking or use of fire in flammable areas.<br><br>\r
				    <b>7. VISITOR MANAGEMENT</b><br>Visitors must register at the reception or security desk.<br><br>\r
				    <b>8. VIOLATION HANDLING</b><br>Residents violating the regulations will be reminded or handled according to the rules.<br><br>\r
				</body></html>""");

        editorPane.setEditable(false);
        editorPane.setOpaque(false);
        var scrollPane = new JScrollPane(editorPane);
        scrollPane.setBounds(440, 154, 400, 500);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(null);
        scrollPane.getHorizontalScrollBar().setUI(null);


        panel.add(scrollPane);

        lblImgRules = new JLabel("");
        lblImgRules.setIcon(new ImageIcon("C:\\java\\ApartmentManagement\\src\\main\\resources\\image\\rules1.jpg"));
        lblImgRules.setBounds(339, 59, 558, 708);
        panel.add(lblImgRules);
    }
}