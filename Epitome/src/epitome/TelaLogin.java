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
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(131, 34, 176, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEstamosQuaseL = new JLabel("Estamos quase l\u00E1!");
		lblEstamosQuaseL.setForeground(new Color(255, 255, 255));
		lblEstamosQuaseL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setBackground(new Color(45, 45, 45));
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmail.setBounds(10, 50, 156, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(255, 255, 255));
		txtSenha.setBackground(new Color(45, 45, 45));
		txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSenha.setBounds(10, 95, 156, 20);
		txtSenha.setEchoChar('*');
		panel.add(txtSenha);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);
		
		JButton btnEsqueci = new JButton("Esqueceu sua senha?");
		btnEsqueci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug > esqueci minha senha");
			}
		});
		btnEsqueci.setBackground(null);
		btnEsqueci.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEsqueci.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnEsqueci.setForeground(clRed);
		btnEsqueci.setHorizontalAlignment(SwingConstants.LEFT);
		btnEsqueci.setBounds(10, 115, 156, 23);
		panel.add(btnEsqueci);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug > verificar dados");
			}
		});
		btnEntrar.setOpaque(false);
        btnEntrar.setBackground(null);
		Chisel(btnEntrar, clRed, 5);
		btnEntrar.setBounds(10, 147, 156, 23);
		panel.add(btnEntrar);
		
		JButton btnCriar = new JButton("Criar uma conta");
		btnCriar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug > tela de cadastro");
			}
		});
		btnCriar.setBackground(null);
		btnCriar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnCriar.setForeground(clBlue);
		btnCriar.setBounds(10, 179, 156, 23);
		panel.add(btnCriar);
		
		JButton btnTelaLogin = new JButton("");
		btnTelaLogin.setIcon(new ImageIcon("C:\\Users\\Aluno\\projeto_integrador\\Epitome\\img\\login.png"));
		btnTelaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug > tela de login");
			}
		});
		
        btnTelaLogin.setBorder(BorderFactory.createEmptyBorder(btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).top, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).left, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).bottom, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).right));
		btnTelaLogin.setBackground(clRed);
		btnTelaLogin.setForeground(Color.WHITE);
		btnTelaLogin.setBounds(317, 34, 30, 30);
		contentPane.add(btnTelaLogin);
		
		JButton btnTelaCadastro = new JButton("");
		btnTelaCadastro.setIcon(new ImageIcon("C:\\Users\\Aluno\\projeto_integrador\\Epitome\\img\\cadastro.png"));
		btnTelaCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug > tela de cadastro");
			}
		});
		
        btnTelaCadastro.setBorder(BorderFactory.createEmptyBorder(btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).top, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).left, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).bottom, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).right));
		btnTelaCadastro.setBackground(clBlue);
		btnTelaCadastro.setForeground(Color.WHITE);
		btnTelaCadastro.setBounds(317, 75, 30, 30);
		contentPane.add(btnTelaCadastro);
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