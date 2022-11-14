package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class TelaInicialADM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialADM frame = new TelaInicialADM(null);
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
	public TelaInicialADM(Usuario usuarioLogado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		
		
		Font poppins, pop10 = null, pop12 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		UIManager.put("Button.select", new Color(0, 0, 0));
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(160, 90, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(592, 388, 471, 191);
//		panelChisel(panel, new Color(255, 255, 255), 5);
		panel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(panel);
		panel.setLayout(null);
		
		RoundButton btnVenda = new RoundButton("VENDA");
		btnVenda.setForeground(Color.WHITE);
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > tela de venda");
				TelaVenda telaVenda = new TelaVenda(usuarioLogado);
				telaVenda.setVisible(true);
				setVisible(false);
			}
		});
		btnVenda.setOpaque(false);
		btnVenda.setBackground(null);
		btnVenda.setFocusPainted(false);
		btnVenda.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		btnVenda.setFont(pop12);
		btnVenda.setBounds(10, 36, 205, 34);
		panel.add(btnVenda);
		
		RoundButton btnListarFornecedor = new RoundButton("FORNECEDORES");
		btnListarFornecedor.setForeground(Color.WHITE);
		btnListarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > tela de listar fornecedor");
				TelaFornecedores telaFornecedores = new TelaFornecedores();
				telaFornecedores.setVisible(true);
			}
		});
		btnListarFornecedor.setOpaque(false);
		btnListarFornecedor.setBackground(null);
		btnListarFornecedor.setFocusPainted(false);
		btnListarFornecedor.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		btnListarFornecedor.setFont(pop12);
		btnListarFornecedor.setBounds(10, 141, 205, 34);
		panel.add(btnListarFornecedor);
		
		RoundButton btnEstoque = new RoundButton("ESTOQUE");
		btnEstoque.setForeground(Color.WHITE);
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > tela de estoque");
				TelaEstoque telaEstoque = new TelaEstoque(usuarioLogado);
				telaEstoque.setVisible(true);
				setVisible(false);
			}
		});
		btnEstoque.setOpaque(false);
		btnEstoque.setBackground(null);
		btnEstoque.setFocusPainted(false);
		btnEstoque.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		btnEstoque.setFont(pop12);
		btnEstoque.setBounds(10, 71, 205, 34);
		panel.add(btnEstoque);
		
		RoundButton btnListarUsuario = new RoundButton("USUÁRIOS");
		btnListarUsuario.setForeground(Color.WHITE);
		btnListarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela Listar usuarios > tela de Listar Usuarios");
				TelaListarUsuarios telaListarUsuarios = new TelaListarUsuarios();
				telaListarUsuarios.setVisible(true);
				//setVisible(false);
			}
		});
		btnListarUsuario.setOpaque(false);
		btnListarUsuario.setBackground(null);
		btnListarUsuario.setFocusPainted(false);
		btnListarUsuario.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		btnListarUsuario.setFont(pop12);
		btnListarUsuario.setBounds(10, 106, 205, 34);
		panel.add(btnListarUsuario);
		
		JButton btnRelatorio = new JButton("Relatório de Vendas");
		btnRelatorio.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorio.setIcon(new ImageIcon("./img/report.png"));
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > relatório de vendas");
				TelaRelatorio telaRel = new TelaRelatorio(usuarioLogado);
				telaRel.setVisible(true);
				setVisible(false);
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(new Color(22,22,22), 2, 23)));
		btnRelatorio.setForeground(Color.WHITE);
		btnRelatorio.setBounds(253, 59, 175, 23);
		btnRelatorio.setFocusPainted(false);
		panel.add(btnRelatorio);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);
		btnSair.setFont(pop10);
		btnSair.setIcon(new ImageIcon("./img/logout.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin tl = new TelaLogin();
				tl.setVisible(true);
				setVisible(false);
			}
		});
		btnSair.setBackground(null);
		btnSair.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(new Color(22,22,22), 2, 23)));
		btnSair.setForeground(Color.WHITE);
		btnSair.setBounds(253, 82, 175, 23);
		btnSair.setFocusPainted(false);
		panel.add(btnSair);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnPerfil.setFont(pop10);
		btnPerfil.setIcon(new ImageIcon("./img/profile.png"));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > perfil");
				TelaPerfilADM telaPerfil = new TelaPerfilADM(usuarioLogado);
				telaPerfil.setVisible(true);
			}
		});
		btnPerfil.setBackground(null);
		btnPerfil.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(new Color(22,22,22), 2, 23)));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBounds(253, 36, 175, 23);
		btnPerfil.setFocusPainted(false);
		panel.add(btnPerfil);
		
		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setForeground(Color.WHITE);
		lblAdministrador.setFont(null);
		lblAdministrador.setBounds(10, 11, 205, 14);
		lblAdministrador.setFont(pop12);
		panel.add(lblAdministrador);
		
		JLabel lblNome = new JLabel(usuarioLogado.getNome_usuario());
		lblNome.setBounds(219, 11, 242, 14);
		panel.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(pop12);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(27, 0, 1600, 861);
		contentPane.add(fakeBG);
		
	}
}
