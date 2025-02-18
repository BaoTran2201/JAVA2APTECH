package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ServiceManagement extends JFrame {
    private JTextField txtMaDichVu, txtTenDichVu, txtGiaDichVu;
    private JComboBox<String> cbLoaiDichVu;
    private JTable table;
    private DefaultTableModel tableModel;

    public ServiceManagement() {
        setTitle("Quản lý Dịch Vụ");
        setBounds(100, 100, 1292, 889);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        JLabel lblTitle = new JLabel("Quản lý Dịch Vụ", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(64, 128, 128));

        // Thêm vào panelTitle
        panelTitle.add(btnBack, BorderLayout.WEST);
        panelTitle.add(lblTitle, BorderLayout.CENTER);
        getContentPane().add(panelTitle, BorderLayout.NORTH);

        // ====== PANEL NHẬP DỮ LIỆU ====== //
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Thông tin dịch vụ"));
        panelInput.setBackground(new Color(64, 128, 128));

        panelInput.add(new JLabel("Mã Dịch Vụ:"));
        txtMaDichVu = new JTextField();
        panelInput.add(txtMaDichVu);

        panelInput.add(new JLabel("Tên Dịch Vụ:"));
        txtTenDichVu = new JTextField();
        panelInput.add(txtTenDichVu);

        panelInput.add(new JLabel("Loại Dịch Vụ:"));
        cbLoaiDichVu = new JComboBox<>(new String[] { "Điện", "Nước", "Internet", "Bảo vệ", "Vệ sinh" });
        panelInput.add(cbLoaiDichVu);

        panelInput.add(new JLabel("Giá Dịch Vụ:"));
        txtGiaDichVu = new JTextField();
        panelInput.add(txtGiaDichVu);

        getContentPane().add(panelInput, BorderLayout.WEST);

        // ====== PANEL NÚT BẤM ====== //
        JPanel panelButtons = new JPanel(new GridLayout(1, 4, 5, 5));
        panelButtons.setBackground(new Color(64, 128, 128));
        JButton btnThem = new JButton("Thêm");
        btnThem.setBackground(new Color(255, 255, 255));
        JButton btnSua = new JButton("Sửa");
        btnSua.setBackground(new Color(255, 255, 255));
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBackground(new Color(255, 255, 255));
        JButton btnLuu = new JButton("Lưu");
        btnLuu.setBackground(new Color(255, 255, 255));
        panelButtons.add(btnThem);
        panelButtons.add(btnSua);
        panelButtons.add(btnXoa);
        panelButtons.add(btnLuu);
        getContentPane().add(panelButtons, BorderLayout.SOUTH);

        // ====== BẢNG DỮ LIỆU ====== //
        String[] columnNames = { "Mã", "Tên", "Loại", "Giá" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServiceManagement().setVisible(true));
    }
}
