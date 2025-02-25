package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PaymentManagement extends JFrame {
    public PaymentManagement() {
        setTitle("Quản Lý Thanh Toán");
        setBounds(100, 100, 1292, 889);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel chính
        JPanel panelMain = new JPanel(new BorderLayout());

        // ====== Panel tiêu đề có nút Back ======
        JPanel panelTitle = new JPanel(new BorderLayout());
        panelTitle.setBackground(new Color(64, 128, 128));

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

        JLabel lblTitle = new JLabel("Quản Lý Thanh Toán", JLabel.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 25));

        panelTitle.add(btnBack, BorderLayout.WEST);
        panelTitle.add(lblTitle, BorderLayout.CENTER);

        panelMain.add(panelTitle, BorderLayout.NORTH);

        // ====== Panel nhập thông tin ======
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Thông Tin Thanh Toán", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        panelForm.setBackground(new Color(64, 128, 128));

        String[] labels = {"Mã hóa đơn:", "Tên khách hàng:", "Căn hộ thuê:", "Ngày lập hóa đơn:", "Tổng tiền:", "Trạng thái:"};
        JTextField[] textFields = new JTextField[5];
        JComboBox<String> cbCanHo = new JComboBox<>(new String[]{"Căn hộ A", "Căn hộ B"});
        JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Chưa thanh toán", "Đã thanh toán"});

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            panelForm.add(label);
            if (i == 2) panelForm.add(cbCanHo);
            else if (i == 5) panelForm.add(cbTrangThai);
            else {
                textFields[i] = new JTextField(15);
                if (i == 4) textFields[i].setEditable(false);
                panelForm.add(textFields[i]);
            }
        }
        panelMain.add(panelForm, BorderLayout.WEST);

        // ====== Bảng danh sách hóa đơn ======
        String[] columnNames = {"Mã HD", "Tên KH", "Căn hộ", "Ngày lập", "Tổng tiền", "Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panelMain.add(scrollPane, BorderLayout.CENTER);

        // ====== Panel nút chức năng ======
        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.setBackground(new Color(64, 128, 128));
        String[] btnNames = {"Thêm hóa đơn", "Cập nhật", "Xóa", "In hóa đơn"};
        for (String name : btnNames) {
            JButton btn = new JButton(name);
            btn.setBackground(Color.WHITE);
            panelButtons.add(btn);
        }
        panelMain.add(panelButtons, BorderLayout.SOUTH);

        getContentPane().add(panelMain);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentManagement();
    }
}
