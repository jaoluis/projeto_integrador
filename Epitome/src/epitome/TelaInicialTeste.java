package epitome;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class TelaInicialTeste extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialTeste frame = new TelaInicialTeste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicialTeste() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Aluno\\projeto_integrador\\Epitome\\img\\app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		
		
		Font poppins, pop10 = null, pop12 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(132, 186, 397, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnContinuar = new JButton("VENDA");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial > tela de venda");
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, new Color(255, 255, 255), 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(21, 71, 156, 23);
		panel.add(btnContinuar);
		
		
		
		JLabel lblNome = new JLabel("Fulano (trocar nome)");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(231, 11, 156, 14);
		lblNome.setFont(pop12);
		panel.add(lblNome);
		
		JButton btnRelatorio = new JButton("Relatório de Vendas");
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial > relatório de vendas");
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnRelatorio.setForeground(clBlue);
		btnRelatorio.setBounds(231, 71, 156, 23);
		panel.add(btnRelatorio);
		
		JButton btnLogin = new JButton("Perfil");
		btnLogin.setFont(pop10);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial > perfil");
			}
		});
		btnLogin.setBackground(null);
		btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnLogin.setForeground(clRed);
		btnLogin.setBounds(231, 36, 156, 23);
		panel.add(btnLogin);
	}
	
	private static void Chisel(JButton button, Color color, int radius) {
		
        button.setFocusPainted(false);
        button.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(button.getBorder().getBorderInsets(button).top, button.getBorder().getBorderInsets(button).left, button.getBorder().getBorderInsets(button).bottom, button.getBorder().getBorderInsets(button).right);
        button.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}
	
	private static class RoundedBorder implements Border {

        private int radius = 10;
        private Color color;

        private RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
