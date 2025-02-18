package view;

import java.awt.*;
import javax.swing.*;

public class StatisticsFrame extends JFrame {
    public StatisticsFrame() {
        setTitle("Thống Kê");
        setBounds(100, 100, 1292, 889);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // ====== PANEL TIÊU ĐỀ (có nút Back) ====== //
        JPanel panelTitle = new JPanel(new BorderLayout());
        panelTitle.setBackground(new Color(64, 128, 128));

        // Nút Back
        JButton btnBack = new JButton("◄ Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(64, 128, 128));
        btnBack.setBorder(null);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            new QuanLyChungCuGUI().setVisible(true); // Thay đổi đối tượng ở đây nếu cần
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

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thống Kê Doanh Thu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(64, 128, 128));

        // Thêm vào panelTitle
        panelTitle.add(btnBack, BorderLayout.WEST);
        panelTitle.add(lblTitle, BorderLayout.CENTER);
        getContentPane().add(panelTitle, BorderLayout.NORTH);

        // ====== Bộ lọc thời gian ====== 
        JPanel panelFilter = new JPanel();
        panelFilter.add(new JLabel("Chọn tháng:"));
        JComboBox<String> cbMonth = new JComboBox<>(
                new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });
        cbMonth.setForeground(new Color(255, 255, 255));
        cbMonth.setBackground(new Color(64, 128, 128));
        panelFilter.add(cbMonth);
        JButton btnFilter = new JButton("Lọc");
        btnFilter.setBackground(new Color(255, 255, 255));
        panelFilter.add(btnFilter);
        getContentPane().add(panelFilter, BorderLayout.WEST);

        // ====== Bảng thống kê ====== 
        String[] columnNames = { "Loại thu nhập", "Số tiền (VNĐ)" };
        Object[][] data = { { "Tiền thuê căn hộ", "120,000,000" }, { "Tiền dịch vụ", "50,000,000" },
                { "Phí bảo trì", "20,000,000" } };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // ====== Xuất báo cáo ====== 
        JPanel panelBottom = new JPanel();
        JButton btnExport = new JButton("Xuất báo cáo");
        btnExport.setBackground(new Color(255, 255, 255));
        panelBottom.add(btnExport);
        getContentPane().add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StatisticsFrame();
    }
}
