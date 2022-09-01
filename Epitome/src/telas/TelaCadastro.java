package telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class TelaCadastro extends JFrame {

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
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
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
		panel.setBounds(243, 72, 176, 375);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEstamosQuaseL = new JLabel("Criar conta");
		lblEstamosQuaseL.setForeground(new Color(255, 255, 255));
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop10);
		lblCPF.setBounds(10, 216, 156, 14);
		panel.add(lblCPF);
		
		JFormattedTextField txtCPF = new JFormattedTextField(def_mask("###.###.###-##",'•'));
		txtCPF.setForeground(new Color(255, 255, 255));
		txtCPF.setBackground(new Color(45, 45, 45));
		txtCPF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCPF.setBounds(10, 230, 156, 20);
		txtCPF.setFont(pop12);
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		
		JFormattedTextField txtData = new JFormattedTextField(def_mask("##/##/####",'•'));
		txtData.setForeground(new Color(255, 255, 255));
		txtData.setBackground(new Color(45, 45, 45));
		txtData.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtData.setBounds(10, 275, 156, 20);
		txtData.setFont(pop12);
		panel.add(txtData);
		txtData.setColumns(10);
		
		JLabel lblUsername = new JLabel("NOME DE USUÁRIO");
		lblUsername.setForeground(new Color(197, 197, 197));
		lblUsername.setFont(pop10);
		lblUsername.setBounds(10, 126, 156, 14);
		panel.add(lblUsername);
		
		JTextField txtUsername = new JTextField();
		txtUsername.setForeground(new Color(255, 255, 255));
		txtUsername.setBackground(new Color(45, 45, 45));
		txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsername.setBounds(10, 140, 156, 20);
		txtUsername.setFont(pop12);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 171, 156, 14);
		panel.add(lblNome);
		
		JTextField txtNome = new JTextField();
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 185, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setBackground(new Color(45, 45, 45));
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmail.setBounds(10, 50, 156, 20);
		txtEmail.setFont(pop12);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setForeground(new Color(255, 255, 255));
		txtSenha.setBackground(new Color(45, 45, 45));
		txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSenha.setBounds(10, 95, 156, 20);
		txtSenha.setFont(pop12);
		txtSenha.setEchoChar('•');
		panel.add(txtSenha);		
		
		JButton btnContinuar = new JButton("CONTINUAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > cadastrar");
				System.out.println(txtCPF.getText() + txtData.getText());
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clBlue, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 309, 156, 23);
		panel.add(btnContinuar);
		
		JButton btnLogin = new JButton("Fazer login");
		btnLogin.setFont(pop10);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > tela de login");
				TelaLogin telalogin = new TelaLogin();
				telalogin.setVisible(true);
				setVisible(false);
			}
		});
		btnLogin.setBackground(null);
		btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnLogin.setForeground(clRed);
		btnLogin.setBounds(10, 341, 156, 23);
		panel.add(btnLogin);
		
		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setForeground(new Color(197, 197, 197));
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 261, 156, 14);
		panel.add(lblDataDeNascimento);
		
		JButton btnTelaLogin = new JButton("");
		btnTelaLogin.setIcon(new ImageIcon("C:\\Users\\Aluno\\projeto_integrador\\Epitome\\img\\login.png"));
		btnTelaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > tela de login");
				TelaLogin telalogin = new TelaLogin();
				telalogin.setVisible(true);
				setVisible(false);
			}
		});
		
        btnTelaLogin.setBorder(BorderFactory.createEmptyBorder(btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).top, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).left, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).bottom, btnTelaLogin.getBorder().getBorderInsets(btnTelaLogin).right));
		btnTelaLogin.setBackground(clRed);
		btnTelaLogin.setForeground(Color.WHITE);
		btnTelaLogin.setBounds(429, 72, 30, 30);
		contentPane.add(btnTelaLogin);
		
		JButton btnTelaCadastro = new JButton("");
		btnTelaCadastro.setIcon(new ImageIcon("C:\\Users\\Aluno\\projeto_integrador\\Epitome\\img\\cadastro.png"));
		btnTelaCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > tela de cadastro");
				TelaCadastro telaCadastro = new TelaCadastro ();
				telaCadastro.setVisible(true);
				setVisible(false);
			}
		});
		
        btnTelaCadastro.setBorder(BorderFactory.createEmptyBorder(btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).top, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).left, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).bottom, btnTelaCadastro.getBorder().getBorderInsets(btnTelaCadastro).right));
		btnTelaCadastro.setBackground(clBlue);
		btnTelaCadastro.setForeground(Color.WHITE);
		btnTelaCadastro.setBounds(429, 113, 30, 30);
		contentPane.add(btnTelaCadastro);
	}
	
	protected MaskFormatter def_mask(String envolucro, char substituto) {
		MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(envolucro);
            mask.setPlaceholderCharacter(substituto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mask;
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

