package visao;

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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.Conexao;
import controle.UsuarioDAO;
import modelo.Usuario;

public class TelaModificar extends JFrame {
	private int idM;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private final ButtonGroup cargoGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaModificar frame = new TelaModificar(0);
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
	public TelaModificar(int idM) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
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
		panel.setBounds(243, 35, 176, 466);
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Alterar conta");
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

		JFormattedTextField txtCPF = new JFormattedTextField(def_mask("###.###.###-##", '•'));
		txtCPF.setForeground(new Color(255, 255, 255));
		txtCPF.setBackground(new Color(45, 45, 45));
		txtCPF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCPF.setBounds(10, 230, 156, 20);
		txtCPF.setFont(pop12);
		panel.add(txtCPF);
		txtCPF.setColumns(10);

		JFormattedTextField txtData = new JFormattedTextField(def_mask("##/##/####", '•'));
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

		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setForeground(new Color(197, 197, 197));
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 261, 156, 14);
		panel.add(lblDataDeNascimento);

		JLabel lblCargo = new JLabel("CARGO");
		lblCargo.setForeground(new Color(197, 197, 197));
		lblCargo.setBounds(10, 306, 156, 14);
		lblCargo.setFont(pop10);
		panel.add(lblCargo);

		JRadioButton rdVendedor = new JRadioButton("VENDEDOR");
		cargoGroup.add(rdVendedor);
		rdVendedor.setForeground(Color.WHITE);
		rdVendedor.setBackground(null);
		rdVendedor.setFont(pop10);
		rdVendedor.setIcon(new ImageIcon("./img/radio_button.png"));
		rdVendedor.setSelectedIcon(
				new ImageIcon("./img/radio_button_checked.png"));
		rdVendedor.setBounds(10, 327, 156, 23);
		panel.add(rdVendedor);

		JRadioButton rdAdministrador = new JRadioButton("ADMINISTRADOR");
		cargoGroup.add(rdAdministrador);
		rdAdministrador.setForeground(Color.WHITE);
		rdAdministrador.setFont(null);
		rdAdministrador.setBackground((Color) null);
		rdAdministrador.setFont(pop10);
		rdAdministrador.setIcon(new ImageIcon("./img/radio_button.png"));
		rdAdministrador.setSelectedIcon(
				new ImageIcon("./img/radio_button_checked.png"));
		rdAdministrador.setBounds(10, 353, 156, 23);
		panel.add(rdAdministrador);

		JButton btnContinuar = new JButton("ALTERAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > cadastrar");
				String email = txtEmail.getText();
				String senha = String.valueOf(txtSenha.getPassword());
				String nomeUsuario = txtUsername.getText();
				String nome = txtNome.getText();
				String cpf = txtCPF.getText();
				String data = txtData.getText();
				String cargo = null;
				LocalDate date = null;
				try {
				date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				}catch (Exception erroConversaoStringData) {
					//Joption quando dá erro na data
					
					JOptionPane.showMessageDialog(null, "Data invalida");
					System.out.println("Deu erro na hora de converter para Data" + erroConversaoStringData);
				}

				if (rdVendedor.isSelected()) {
					cargo = "vendedor";
				}

				if (rdAdministrador.isSelected()) {
					cargo = "administrador";
				}
				
				Usuario usuario = new Usuario();
				usuario.setNome_usuario(nome);
				usuario.setCargo(cargo);
				usuario.setCpf_usuario(cpf);
				usuario.setEmail(email);
				usuario.setSenha_usuario(senha);
				usuario.setLogin_usuario(nomeUsuario);
				usuario.setNascimento_data(Date.valueOf(date));
				usuario.setId_usuario(idM);

				UsuarioDAO dao;
				dao = new UsuarioDAO();
				dao.update(usuario);

				// tratamento de exceções: campos vazios e formatos errados
				// funcao cadastro (email, senha, nomeUsuario, nome, cpf, data, cargo);
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clBlue, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 400, 156, 23);
		panel.add(btnContinuar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
	}

	private static void panelbuttonChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(417, 124, 417, 124);
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
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
		Border emptyBorder = BorderFactory.createEmptyBorder(button.getBorder().getBorderInsets(button).top,
				button.getBorder().getBorderInsets(button).left, button.getBorder().getBorderInsets(button).bottom,
				button.getBorder().getBorderInsets(button).right);
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
