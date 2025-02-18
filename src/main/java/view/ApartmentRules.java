package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ApartmentRules extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblImgRules;
    private JEditorPane editorPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ApartmentRules frame = new ApartmentRules();
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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 1276, 850);
        contentPane.add(panel);
        panel.setLayout(null);

        // ====== Nút Back ======
        JButton btnBack = new JButton("◄ Back");
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(new Color(200, 200, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBack.setForeground(Color.WHITE);
            }
        });
        panel.add(btnBack);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(
            "<html><body style='font-family:Arial; font-size:14px; text-align:justify;'>"
            + "<h2 style='text-align:center;'>NỘI QUY CHUNG CƯ</h2>"
            + "<b>1. QUY ĐỊNH CHUNG</b><br>Tất cả cư dân và khách đến thăm phải tuân thủ nội quy chung cư.<br><br>"
            + "<b>2. AN TOÀN VÀ AN NINH</b><br>Không gây mất trật tự, ồn ào từ <span style='color:red;'>22:00 - 06:00</span>.<br><br>"
            + "<b>3. VỆ SINH MÔI TRƯỜNG</b><br>Giữ gìn vệ sinh chung, không xả rác bừa bãi.<br><br>"
            + "<b>4. SỬ DỤNG TIỆN ÍCH CHUNG</b><br>Không chiếm dụng hành lang, lối đi chung để chứa đồ cá nhân.<br><br>"
            + "<b>5. QUẢN LÝ THÚ CƯNG</b><br>Chỉ nuôi thú cưng khi có sự đồng ý của ban quản lý chung cư.<br><br>"
            + "<b>6. PCCC (PHÒNG CHÁY CHỮA CHÁY)</b><br>Không hút thuốc hoặc sử dụng lửa trong khu vực dễ cháy nổ.<br><br>"
            + "<b>7. QUẢN LÝ KHÁCH RA VÀO</b><br>Khách đến thăm phải đăng ký tại quầy lễ tân hoặc bảo vệ.<br><br>"
            + "<b>8. XỬ LÝ VI PHẠM</b><br>Cư dân vi phạm nội quy sẽ bị nhắc nhở hoặc xử lý theo quy định.<br><br>"
            + "</body></html>");
        
        editorPane.setEditable(false);
        editorPane.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(editorPane);
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