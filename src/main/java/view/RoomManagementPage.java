package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class RoomManagementPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RoomManagementPage frame = new RoomManagementPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RoomManagementPage() {
        setTitle("Room Management Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1292, 889);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // ====== PANEL TITLE (có nút Back) ====== //
        JPanel panelTitle = new JPanel(new BorderLayout());
        panelTitle.setBackground(new Color(64, 128, 128));

        JLabel lblTitle = new JLabel("Quản Lý Phòng", JLabel.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        // NÚT BACK
        JButton btnBack = new JButton("◄ Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 16));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(64, 128, 128));
        btnBack.setBorder(null);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        // THÊM CÁC THÀNH PHẦN VÀO panelTitle
        panelTitle.add(btnBack, BorderLayout.WEST);  // Nút back nằm trái
        panelTitle.add(lblTitle, BorderLayout.CENTER); // Tiêu đề nằm giữa

        contentPane.add(panelTitle, BorderLayout.NORTH);

        // ====== BẢNG DỮ LIỆU ====== //
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        String[] columnNames = { "Mã Phòng", "Trạng Thái", "Giá Thuê", "Diện Tích" };
        Object[][] data = { 
            { "P001", "Trống", "5,000,000", "50 m²" }, 
            { "P002", "Đang thuê", "6,000,000", "55 m²" },
            { "P003", "Trống", "5,500,000", "52 m²" } 
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        // ====== PANEL CHỨA NÚT CHỨC NĂNG ====== //
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBottom.setBackground(new Color(64, 128, 128));

        JButton btnThemPhong = new JButton("Thêm phòng");
        JButton btnSuaPhong = new JButton("Sửa phòng");
        JButton btnXoaPhong = new JButton("Xóa phòng");
        JButton btnLamMoi = new JButton("Làm mới");

        // Cập nhật các nút chức năng với màu nền trắng và thêm vào panelBottom
        for (JButton btn : new JButton[]{btnThemPhong, btnSuaPhong, btnXoaPhong, btnLamMoi}) {
            btn.setBackground(Color.WHITE);
            panelBottom.add(btn);
        }

        contentPane.add(panelBottom, BorderLayout.SOUTH);
    }
}
