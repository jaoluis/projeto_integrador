package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controle.UsuarioDAO;
import modelo.Usuario;

public class TelaLogin extends JFrame {

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
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
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
		setBounds(100, 100, 515, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(149, 111, 176, 190);
		panel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Estamos quase l\u00E1!");
		lblEstamosQuaseL.setForeground(Color.WHITE);
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);

		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);
		
		
		
		RoundField txtEmail = new RoundField();
		txtEmail.setSelectedTextColor(new Color(22, 22, 22));
		txtEmail.setSelectionColor(clRed);
		txtEmail.setCaretColor(Color.WHITE);
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setBackground(new Color(45, 45, 45));
		txtEmail.setFont(pop12);
		txtEmail.setBounds(10, 50, 156, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		RoundPasswordField txtSenha = new RoundPasswordField();
		txtSenha.setSelectedTextColor(new Color(22, 22, 22));
		txtSenha.setSelectionColor(clRed);
		txtSenha.setCaretColor(Color.WHITE);
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setBackground(new Color(45, 45, 45));
		txtSenha.setBounds(10, 95, 156, 20);
		txtSenha.setFont(pop12);
		txtSenha.setEchoChar('\u2022');
		panel.add(txtSenha);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);

		
		//remover, para testes
		txtEmail.setText("gustavo.s07@aluno.com");
		txtSenha.setText("1234");
		
		RoundButton btnEntrar = new RoundButton("ENTRAR");
		btnEntrar.setForeground(clRed);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = txtEmail.getText();
				char[] senha = txtSenha.getPassword();
	 
	        // create object of StringBuilder class
	        StringBuilder sb = new StringBuilder();
	 
	        // Appends characters one by one
	        for (Character ch : senha) {
	            sb.append(ch);
	        }
	 
	        // convert in string
	        String senhaP = sb.toString();
	 
	        Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha_usuario(senhaP);
			
			UsuarioDAO dao;
			dao = new UsuarioDAO();
			Usuario usuarioLogado = dao.verificacao(usuario);
			
			if (usuarioLogado == null) {
				return;
			}
			
			if (usuarioLogado.getCargo().equals("administrador")) {
				TelaInicialADM iniciologinADM = new TelaInicialADM(usuarioLogado);
				iniciologinADM.setVisible(true);
				setVisible(false);
			}else if(usuarioLogado.getCargo().equals("vendedor")){
				TelaInicialVND iniciologinVND = new TelaInicialVND(usuarioLogado);
				iniciologinVND.setVisible(true);
				setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(null, "Usu\u00E0rio desconhecido", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			}
		});
		btnEntrar.setPressedBackgroundColor(new Color(0,0,0));
		btnEntrar.setOpaque(false);
		btnEntrar.setBackground(null);
		btnEntrar.setBorder(new RoundBorder(clRed, 1, 10));
		btnEntrar.setFont(pop12);
		btnEntrar.setBounds(10, 156, 156, 23);
		panel.add(btnEntrar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-495, -286, 1600, 861);
		contentPane.add(fakeBG);
	}
}
