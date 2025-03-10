package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Dao.StatisticsDAO;

public class StatisticsFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel createHoverLabel(String text, String iconPath) {
		var label = new JLabel(text, SwingConstants.CENTER);
		if (iconPath != null) {
			label.setIcon(new ImageIcon(iconPath));
		}
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setForeground(new Color(64, 128, 128));
		label.setBackground(new Color(255, 255, 255));
		label.setOpaque(true);
		label.setBorder(createLabelBorder());

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setBackground(new Color(200, 230, 230));
				label.setBorder(createHoverBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setBackground(new Color(255, 255, 255));
				label.setBorder(createLabelBorder());
			}
		});

		return label;
	}

	private Border createLabelBorder() {
		return BorderFactory.createCompoundBorder(new LineBorder(new Color(64, 128, 128), 2, true),
				new EmptyBorder(5, 10, 5, 10) // Lá» trong
		);
	}

	private Border createHoverBorder() {
		return BorderFactory.createCompoundBorder(new MatteBorder(3, 3, 3, 3, new Color(150, 200, 200)),
				new EmptyBorder(5, 10, 5, 10));
	}

	class RoundedShadowLabel extends JLabel {
		private int arcWidth = 30; // Äá»™ cong viá»n
		private int arcHeight = 30;
		private int shadowSize = 5; // KÃ­ch thÆ°á»›c Ä‘á»• bÃ³ng
		private Color defaultColor = Color.WHITE;
		private Color hoverColor = new Color(200, 230, 230);

		public RoundedShadowLabel(String text, String iconPath) {
			super(text, SwingConstants.CENTER);
			if (iconPath != null) {
				setIcon(new ImageIcon(iconPath));
			}
			setFont(new Font("Arial", Font.BOLD, 14));
			setForeground(new Color(64, 128, 128));
			setOpaque(false);

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultColor = hoverColor;
					repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					defaultColor = Color.WHITE;
					repaint();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			var g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Äá»• bÃ³ng
			g2.setColor(new Color(0, 0, 0, 50));
			g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2,
					arcWidth, arcHeight);

			// Váº½ ná»n bo gÃ³c vá»›i mÃ u hover
			g2.setColor(defaultColor);
			g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, arcWidth, arcHeight);

			super.paintComponent(g);
			g2.dispose();
		}
	}

	public StatisticsFrame() {
		setTitle("Statics");
        setBounds(100, 100, 1292, 889);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(64, 128, 128));
		var panelTop = new JPanel(new BorderLayout());
		panelTop.setBackground(new Color(64, 128, 128));
		var lblTitle = new JLabel("STATISTIC MANAGEMENT", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		panelTop.add(lblTitle, BorderLayout.CENTER);
		var btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(64, 128, 128));
		btnBack.setBorder(null);
		btnBack.addActionListener(e -> {
			new QuanLyChungCuGUI().setVisible(true);
			dispose();
		});
		panelTop.add(btnBack, BorderLayout.WEST);
		getContentPane().add(panelTop, BorderLayout.NORTH);

		var dao = new StatisticsDAO();
		var panelBelowTop = new JPanel(new GridLayout(1, 3, 10, 0));
		panelBelowTop.setOpaque(false);
		panelBelowTop.setBorder(new EmptyBorder(10, 10, 10, 10));

		var lbl1 = new RoundedShadowLabel("TOTAL CUSTOMERS: " + dao.getTotalCustomers(),
				"D:\\java\\code\\Apartment\\src\\main\\resources\\image\\total customer.png");
		lbl1.setFont(new Font("Arial", Font.BOLD, 14));
		lbl1.setForeground(new Color(64, 128, 128));
		lbl1.setBorder(new EmptyBorder(5, 10, 5, 10));

		var lbl2 = new RoundedShadowLabel("TOTAL FEEDBACK: " + dao.getTotalFeedback(),
				"D:\\java\\code\\Apartment\\src\\main\\resources\\image\\total feedback.png");
		lbl2.setFont(new Font("Arial", Font.BOLD, 14));
		lbl2.setForeground(new Color(64, 128, 128));
		lbl2.setBorder(new EmptyBorder(5, 10, 5, 10));

		var lbl3 = new RoundedShadowLabel("TOTAL Revenue: " + dao.getRevenue() + " VNĐ",
				"D:\\java\\code\\Apartment\\src\\main\\resources\\image\\Revenue.png");
		lbl3.setFont(new Font("Arial", Font.BOLD, 14));
		lbl3.setForeground(new Color(64, 128, 128));
		lbl3.setBorder(new EmptyBorder(5, 10, 5, 10));
		panelBelowTop.add(lbl1);
		panelBelowTop.add(lbl2);
		panelBelowTop.add(lbl3);

		getContentPane().add(panelBelowTop, BorderLayout.CENTER);
		var mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.setOpaque(false); // LÃ m trong suá»‘t Ä‘á»ƒ tháº¥y mÃ u contentPane
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		var leftPanel = new JPanel(new BorderLayout());
		var dataset = new DefaultCategoryDataset();
		var data = dao.getServiceRegistrationData();

		for (Map.Entry<String, Integer> entry : data.entrySet()) {
			dataset.addValue(entry.getValue(), "Quantity", entry.getKey());
		}

		var barChart = ChartFactory.createBarChart("Statistics on the number of registered services", "Services",
				" Number of registrations", dataset, PlotOrientation.VERTICAL, true, true, false);
		var plot = barChart.getCategoryPlot();
		var axis = plot.getRangeAxis();
		axis.setStandardTickUnits(org.jfree.chart.axis.NumberAxis.createIntegerTickUnits());
		var chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 600));
		leftPanel.add(chartPanel, BorderLayout.CENTER);
		mainPanel.add(leftPanel);

		getContentPane().add(mainPanel, BorderLayout.SOUTH);

		setVisible(true);
    }

}
