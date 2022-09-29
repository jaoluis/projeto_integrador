package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controle.UsuarioDAO;
import modelo.Usuario;

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

public class TelaInicialADM extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialADM frame = new TelaInicialADM();
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
	public TelaInicialADM() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		//Color clGreen = new Color(105, 122, 39);
		Color clGreen = new Color(168, 198, 51);
		
		
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
		setBounds(160, 90, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(592, 388, 417, 224);
		panelChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnVenda = new JButton("VENDA");
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > tela de venda");
			}
		});
		btnVenda.setOpaque(false);
		btnVenda.setBackground(null);
		buttonChisel(btnVenda, new Color(255, 255, 255), 5);
		btnVenda.setFont(pop12);
		btnVenda.setBounds(10, 36, 156, 34);
		panel.add(btnVenda);
		
		JButton btnEstoque = new JButton("ESTOQUE");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > tela de estoque");
				TelaEstoque telaEstoque = new TelaEstoque();
				telaEstoque.setVisible(true);
				setVisible(false);
			}
		});
		btnEstoque.setOpaque(false);
		btnEstoque.setBackground(null);
		buttonChisel(btnEstoque, new Color(255, 255, 255), 5);
		btnEstoque.setFont(pop12);
		btnEstoque.setBounds(10, 71, 156, 34);
		panel.add(btnEstoque);
		
		JButton btnCadastrarUsuarios = new JButton("Cadastrar Usuario");
		btnCadastrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela gerenciar usuario > tela de Cadastro");
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrarUsuarios.setOpaque(false);
		btnCadastrarUsuarios.setBackground(null);
		buttonChisel(btnCadastrarUsuarios, new Color(255, 255, 255), 5);
		btnCadastrarUsuarios.setFont(pop12);
		btnCadastrarUsuarios.setBounds(10, 106, 156, 68);
		panel.add(btnCadastrarUsuarios);
		
		JButton btnListarUsuario = new JButton("Listar Usuarios");
		btnListarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela Listar usuarios > tela de Listar Usuarios");
				TelaListarUsuarios telaListarUsuarios = new TelaListarUsuarios();
				telaListarUsuarios.setVisible(true);
				setVisible(false);
			}
		});
		btnListarUsuario.setOpaque(false);
		btnListarUsuario.setBackground(null);
		buttonChisel(btnListarUsuario, new Color(255, 255, 255), 5);
		btnListarUsuario.setFont(pop12);
		btnListarUsuario.setBounds(10, 176, 156, 34);
		panel.add(btnListarUsuario);
		
		JButton btnRelatorio = new JButton("Relatório de Vendas");
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > relatório de vendas");
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnRelatorio.setForeground(clBlue);
		btnRelatorio.setBounds(176, 59, 231, 23);
		panel.add(btnRelatorio);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(pop10);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > sair");
			}
		});
		btnSair.setBackground(null);
		btnSair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnSair.setForeground(clGreen);
		btnSair.setBounds(176, 82, 231, 23);
		panel.add(btnSair);
		
		JButton btnLogin = new JButton("Perfil");
		btnLogin.setFont(pop10);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > perfil");
				TelaPerfil telaPerfil = new TelaPerfil(0);
				telaPerfil.setVisible(true);
			}
		});
		btnLogin.setBackground(null);
		btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnLogin.setForeground(clRed);
		btnLogin.setBounds(176, 36, 231, 23);
		panel.add(btnLogin);
		
		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setForeground(Color.WHITE);
		lblAdministrador.setFont(null);
		lblAdministrador.setBounds(10, 11, 156, 14);
		lblAdministrador.setFont(pop12);
		panel.add(lblAdministrador);
		
		JLabel lblNome = new JLabel("Fulano da Silva");
		//lblNome.setText(nome do usuario)
		lblNome.setBounds(176, 11, 231, 14);
		panel.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(pop12);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(0, 0, 1600, 861);
		contentPane.add(fakeBG);
		
	}
	
	private static void buttonChisel(JButton button, Color color, int radius) {
		
        button.setFocusPainted(false);
        button.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(button.getBorder().getBorderInsets(button).top, button.getBorder().getBorderInsets(button).left, button.getBorder().getBorderInsets(button).bottom, button.getBorder().getBorderInsets(button).right);
        button.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}
	
	private static void panelChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(417, 124, 417, 124);
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
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
