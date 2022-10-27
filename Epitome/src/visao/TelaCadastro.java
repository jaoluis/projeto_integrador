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
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.MaskFormatter;

import controle.Conexao;
import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Usuario;
import modelo.endereco;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private final ButtonGroup cargoGroup = new ButtonGroup();
	ArrayList<endereco> enderecoF =  new ArrayList<endereco>();
	ArrayList<Contato> contatoC =  new ArrayList<Contato>();
	String[] valuesCnt = new String[] {"Adicione um contato"};

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clLight = new Color(197, 197, 197);
		Color clYellow = new Color(239, 161, 35);

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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(73, 34, 176, 466);
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel endPanel = new JPanel();
		endPanel.setBackground(new Color(22, 22, 22));
		endPanel.setBounds(282, 34, 345, 250);
		panelbuttonChisel(endPanel, new Color(255, 255, 255), 5);
		contentPane.add(endPanel);
		endPanel.setLayout(null);
		
		JLabel lblEndereco = new JLabel("ENDEREÇO(S)");
		lblEndereco.setBounds(10, 11, 156, 14);
		endPanel.add(lblEndereco);
		lblEndereco.setForeground(new Color(197, 197, 197));
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		
		JButton btnAddEndereco = new JButton("");
		btnAddEndereco.setBounds(150, 11, 16, 16);
		endPanel.add(btnAddEndereco);
		btnAddEndereco.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// adicionar endereço à lista
			}
		});
		btnAddEndereco.setBackground(null);
		btnAddEndereco.setBorder(BorderFactory.createEmptyBorder());
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 156, 211);
		endPanel.add(scrollPane);
		scrollPane.getVerticalScrollBar().setBackground(new Color(22, 22, 22));
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = clLight;
		    }
		    
		    @Override
		    protected JButton createDecreaseButton(int orientation) {
		        JButton button = super.createDecreaseButton(orientation);
		        button.setBackground(new Color(22, 22, 22));
		        button.setForeground(null);
		        button.setSelectedIcon(null);
		        button.setBorder(BorderFactory.createLineBorder(new Color(22,22,22), 2));
		        return button;
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		        JButton button = super.createIncreaseButton(orientation);
		        button.setBackground(new Color(22, 22, 22));
		        button.setForeground(null);
		        button.setSelectedIcon(null);
		        button.setBorder(BorderFactory.createLineBorder(new Color(22,22,22), 2));
		        return button;
		    }
		});

		scrollChisel(scrollPane, new Color(255, 255, 255), 5);
		scrollPane.setBackground(null);
		scrollPane.setForeground(null);
		
		String[] values = new String[] {"Adicione um endereço"};
		
		JList listaEndereco = new JList();
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(new Color(22,22,22));
		listaEndereco.setModel(new AbstractListModel() {
			// sempre que tem um item selecionado, alterar campos do painel endPanel

			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaEndereco.setBackground(new Color(22, 22, 22));
		listaEndereco.setForeground(new Color(197, 197, 197));
		listaEndereco.setFont(pop10);
		listaEndereco.setBounds(0, 50, 156, 113);
		scrollPane.setViewportView(listaEndereco);
		
		JLabel lblDetEndereco = new JLabel("Endereço");
		lblDetEndereco.setForeground(new Color(255, 255, 255));
		lblDetEndereco.setFont(pop12);
		lblDetEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetEndereco.setBounds(176, 11, 156, 14);
		endPanel.add(lblDetEndereco);
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setForeground(new Color(197, 197, 197));
		lblCidade.setFont(pop10);
		lblCidade.setBounds(176, 36, 156, 14);
		endPanel.add(lblCidade);

		JTextField txtCidade = new JTextField();
		txtCidade.setForeground(new Color(255, 255, 255));
		txtCidade.setBackground(new Color(45, 45, 45));
		txtCidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCidade.setBounds(176, 50, 156, 20);
		txtCidade.setFont(pop12);
		endPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblBairro = new JLabel("BAIRRO");
		lblBairro.setForeground(new Color(197, 197, 197));
		lblBairro.setFont(pop10);
		lblBairro.setBounds(176, 81, 156, 14);
		endPanel.add(lblBairro);

		JTextField txtBairro = new JTextField();
		txtBairro.setForeground(new Color(255, 255, 255));
		txtBairro.setBackground(new Color(45, 45, 45));
		txtBairro.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBairro.setBounds(176, 95, 156, 20);
		txtBairro.setFont(pop12);
		endPanel.add(txtBairro);
		
		JLabel lblRua = new JLabel("RUA");
		lblRua.setForeground(new Color(197, 197, 197));
		lblRua.setFont(pop10);
		lblRua.setBounds(176, 126, 156, 14);
		endPanel.add(lblRua);

		JTextField txtRua = new JTextField();
		txtRua.setForeground(new Color(255, 255, 255));
		txtRua.setBackground(new Color(45, 45, 45));
		txtRua.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtRua.setBounds(176, 140, 156, 20);
		txtRua.setFont(pop12);
		endPanel.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("NÚMERO");
		lblNumero.setForeground(new Color(197, 197, 197));
		lblNumero.setFont(pop10);
		lblNumero.setBounds(176, 171, 156, 14);
		endPanel.add(lblNumero);

		JTextField txtNumero = new JTextField();
		txtNumero.setForeground(new Color(255, 255, 255));
		txtNumero.setBackground(new Color(45, 45, 45));
		txtNumero.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNumero.setBounds(176, 185, 156, 20);
		txtNumero.setFont(pop12);
		endPanel.add(txtNumero);
		
		JButton btnAlterarEndereco= new JButton("ALTERAR");
		btnAlterarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean f = true;
				String cidade = txtCidade.getText();
				if (cidade.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma cidade", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("cidade Vazio");
					f =false;
					return;
				}
				
				String Rua = txtRua.getText();
				if (Rua.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma Rua.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("rua vazio");
					f =false;
					return;
				}
				
				String bairro = txtBairro.getText();
				if (bairro.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um bairro.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("bairro vazio");
					f =false;
					return;
				}
				String numero = txtNumero.getText();
				int numero1;
				
				try {
					numero1 = Integer.parseInt(txtNumero.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Digite apenas numeros.", "Aviso", JOptionPane.WARNING_MESSAGE);
					System.out.println("Não converteu para inteiro");
					f =false;
					return;
				}
				

				if (numero.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um numero.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("numero vazio");
					f =false;
					return;
				}
				
				if(f=true) {
				endereco enderecoAdd = new endereco();
				enderecoAdd.setBairro(bairro);
				enderecoAdd.setCidade(cidade);
				enderecoAdd.setNumero(numero1);
				enderecoAdd.setRua(Rua);
				enderecoF.add(enderecoAdd);
				for (endereco enderecoA : enderecoF) {	
		String[] values = new String[] {enderecoA.getRua()+enderecoA.getBairro() + enderecoA.getCidade() + enderecoA.getNumero()};

		JList listaEndereco = new JList();
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(new Color(22,22,22));
		listaEndereco.setModel(new AbstractListModel() {
			// sempre que tem um item selecionado, alterar campos do painel endPanel

			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		Font poppins, pop10 = null, pop12 = null;
		listaEndereco.setBackground(new Color(22, 22, 22));
		listaEndereco.setForeground(new Color(197, 197, 197));
		listaEndereco.setFont(pop10);
		listaEndereco.setBounds(0, 0, 156, 113);
		scrollPane.setViewportView(listaEndereco);
				}

				
				}
				System.out.println("debug: tela de cadastro de fornecedor > cadastro enderecor");
				
			}
		});
		btnAlterarEndereco.setOpaque(false);
		btnAlterarEndereco.setForeground(new Color(239, 161, 35));
		btnAlterarEndereco.setFont(pop12);
		Chisel(btnAlterarEndereco, clYellow, 5);
		btnAlterarEndereco.setFocusPainted(false);
		btnAlterarEndereco.setBackground((Color) null);
		btnAlterarEndereco.setBounds(176, 216, 156, 23);
		endPanel.add(btnAlterarEndereco);
		
		
		JPanel cntPanel = new JPanel();
		cntPanel.setBackground(new Color(22, 22, 22));
		cntPanel.setBounds(282, 295, 345, 205);
		panelbuttonChisel(cntPanel, new Color(255, 255, 255), 5);
		contentPane.add(cntPanel);
		cntPanel.setLayout(null);
				
		JLabel lblDetContato = new JLabel("Contato");
		lblDetContato.setForeground(new Color(255, 255, 255));
		lblDetContato.setFont(pop12);
		lblDetContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetContato.setBounds(179, 11, 156, 14);
		cntPanel.add(lblDetContato);
		
		JLabel lblEmailCnt = new JLabel("E-MAIL");
		lblEmailCnt.setForeground(new Color(197, 197, 197));
		lblEmailCnt.setFont(pop10);
		lblEmailCnt.setBounds(179, 36, 156, 14);
		cntPanel.add(lblEmailCnt);

		JTextField txtEmailCnt = new JTextField();
		txtEmailCnt.setForeground(new Color(255, 255, 255));
		txtEmailCnt.setBackground(new Color(45, 45, 45));
		txtEmailCnt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmailCnt.setBounds(179, 50, 156, 20);
		txtEmailCnt.setFont(pop12);
		cntPanel.add(txtEmailCnt);
		txtEmailCnt.setColumns(10);

		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setForeground(new Color(197, 197, 197));
		lblTelefone.setFont(pop10);
		lblTelefone.setBounds(179, 81, 156, 14);
		cntPanel.add(lblTelefone);

		JTextField txtTelefone = new JTextField();
		txtTelefone.setForeground(new Color(255, 255, 255));
		txtTelefone.setBackground(new Color(45, 45, 45));
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(179, 95, 156, 20);
		txtTelefone.setFont(pop12);
		cntPanel.add(txtTelefone);
		

		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setBounds(10, 15, 156, 14);
		cntPanel.add(lblContato);
		lblContato.setForeground(new Color(197, 197, 197));
		lblContato.setFont(null);
		lblContato.setFont(pop10);
		
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setBounds(10, 29, 156, 165);
		cntPanel.add(cntScrollPane);
		cntScrollPane.getVerticalScrollBar().setBackground(new Color(22, 22, 22));
		scrollChisel(cntScrollPane, new Color(255, 255, 255), 5);
		cntScrollPane.setBackground(null);
		cntScrollPane.setForeground(null);
		cntScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = clLight;
		    }
		    
		    @Override
		    protected JButton createDecreaseButton(int orientation) {
		        JButton button = super.createDecreaseButton(orientation);
		        button.setBackground(new Color(22, 22, 22));
		        button.setForeground(null);
		        button.setSelectedIcon(null);
		        button.setBorder(BorderFactory.createLineBorder(new Color(22,22,22), 2));
		        return button;
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		        JButton button = super.createIncreaseButton(orientation);
		        button.setBackground(new Color(22, 22, 22));
		        button.setForeground(null);
		        button.setSelectedIcon(null);
		        button.setBorder(BorderFactory.createLineBorder(new Color(22,22,22), 2));
		        return button;
		    }
		});
		
		
		
		JList listaContato = new JList();
		listaContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContato.setSelectionBackground(clYellow);
		listaContato.setSelectionForeground(new Color(22,22,22));
		listaContato.setModel(new AbstractListModel() {
			// sempre que tem um item selecionado, alterar campos do painel endPanel

			public int getSize() {
				return valuesCnt.length;
			}
			public Object getElementAt(int index) {
				return valuesCnt[index];
			}
		});
		listaContato.setBackground(new Color(22, 22, 22));
		listaContato.setForeground(new Color(197, 197, 197));
		listaContato.setFont(pop10);
		listaContato.setBounds(0, 50, 156, 113);
		cntScrollPane.setViewportView(listaContato);
			
		JButton btnAddContato = new JButton("");
		btnAddContato.setBounds(150, 11, 16, 16);
		cntPanel.add(btnAddContato);
		btnAddContato.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// adicionar contato à lista
			}
		});
		btnAddContato.setBackground(null);
		btnAddContato.setBorder(BorderFactory.createEmptyBorder());

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
		btnLogin.setBounds(10, 432, 156, 23);
		panel.add(btnLogin);

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
		rdVendedor.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdVendedor.setBounds(10, 327, 156, 23);
		rdVendedor.setFocusPainted(false);
		panel.add(rdVendedor);

		JRadioButton rdAdministrador = new JRadioButton("ADMINISTRADOR");
		cargoGroup.add(rdAdministrador);
		rdAdministrador.setForeground(Color.WHITE);
		rdAdministrador.setFont(null);
		rdAdministrador.setBackground((Color) null);
		rdAdministrador.setFont(pop10);
		rdAdministrador.setIcon(new ImageIcon("./img/radio_button.png"));
		rdAdministrador.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdAdministrador.setBounds(10, 353, 156, 23);
		rdAdministrador.setFocusPainted(false);
		panel.add(rdAdministrador);

		JButton btnTelaLogin = new JButton("");
		btnTelaLogin.setIcon(new ImageIcon("./img/login.png"));
		btnTelaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > tela de login");
				TelaLogin telalogin = new TelaLogin();
				telalogin.setVisible(true);
				setVisible(false);
			}
		});

		btnTelaLogin.setBorder(BorderFactory.createEmptyBorder());
		btnTelaLogin.setBackground(null);
		btnTelaLogin.setForeground(Color.WHITE);
		btnTelaLogin.setBounds(33, 34, 30, 30);
		contentPane.add(btnTelaLogin);

		JButton btnTelaCadastro = new JButton("");
		btnTelaCadastro.setBackground(null);
		btnTelaCadastro.setIcon(new ImageIcon("./img/cadastro.png"));
		btnTelaCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > tela de cadastro");
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
				setVisible(false);
			}
		});

		btnTelaCadastro.setBorder(
				BorderFactory.createEmptyBorder());
		btnTelaCadastro.setBackground(null);
		btnTelaCadastro.setForeground(Color.WHITE);
		btnTelaCadastro.setBounds(33, 75, 30, 30);
		contentPane.add(btnTelaCadastro);

		JButton btnContinuar = new JButton("CONTINUAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > cadastrar");
				UsuarioDAO dao;
				dao = new UsuarioDAO();
				
				String cpf = txtCPF.getText();
				for (char c: cpf.toCharArray()) {
					if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
						JOptionPane.showMessageDialog(null, "CPF inválido", "Erro", JOptionPane.ERROR_MESSAGE);
						System.out.println("CPF inválido: " + cpf);
						return;
					}
				}
				
				String email = txtEmail.getText();
				if (email.isBlank()) {
					JOptionPane.showMessageDialog(null, "E-mail inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("E-mail vazio");
					return;
				}
				
				String senha = String.valueOf(txtSenha.getPassword());
				
				if (senha.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma senha", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Senha vazia");
					return;
				}
				
				String nomeUsuario = txtUsername.getText();
				if (nomeUsuario.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um nome de usuário", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("username vazio");
					return;
				}
				
				String nome = txtNome.getText();
				if (nome.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um nome", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("nome vazio");
					return;
				}
				
				String data = txtData.getText();
				
				String cargo = null;				
				LocalDate date = null;
				
			    StringBuffer cpfSN = new StringBuffer();

			    char [] caracteres = cpf.toCharArray();

			    for (Character caracter : caracteres) {
			        if (Character.isDigit(caracter)) {
			            cpfSN.append(caracter);
			        }
			    }

				if(dao.validarEmail(email) == true && dao.validarCPF(cpfSN.toString()) == true) {
				
				try {
				date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				}catch (Exception erroConversaoStringData) {
					//Joption quando dá erro na data
					
					JOptionPane.showMessageDialog(null, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Deu erro na hora de converter para Data" + erroConversaoStringData);
					return;
				}

				if (rdVendedor.isSelected()) {
					cargo = "vendedor";
				}

				if (rdAdministrador.isSelected()) {
					cargo = "administrador";
				}
				
				if (cargo == null) {
					JOptionPane.showMessageDialog(null, "Favor selecionar cargo", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("cargo vazio");
					return;
				}
				
				Usuario usuario = new Usuario();
				usuario.setNome_usuario(nome);
				usuario.setCargo(cargo);
				usuario.setCpf_usuario(cpf);
				usuario.setEmail(email);
				usuario.setSenha_usuario(senha);
				usuario.setLogin_usuario(nomeUsuario);
				usuario.setNascimento_data(Date.valueOf(date));

				long id = dao.insert(usuario);
				
				for (endereco enderecoA: enderecoF) {
				dao.insertEndereco(enderecoA, id);
				}
				
				for (Contato contatoA : contatoC) {
				dao.insertContato(contatoA, id);
				}
				}else {
					System.out.println("Email ou CPF invalido "+email);
				}
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
		
		JButton btnAlterarContato = new JButton("ALTERAR");
		btnAlterarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean f = true;
				String email = txtEmailCnt.getText();
				if (email.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um email", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("email Vazio");
					f =false;
					return;
				}
				
				String telefone = txtTelefone.getText();
				if (telefone.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um telefone.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("telefone vazio");
					f =false;
					return;
				}
				
				
				if(f=true) {
				Contato contato = new Contato();
				contato.setEmail(email);
				contato.setTelefone(telefone);
				contatoC.add(contato);
				
			
			
			for (Contato contatoA : contatoC) {	
				valuesCnt = new String[] {contatoA.getTelefone()+contatoA.getEmail()};
				
				JList listaEndereco = new JList();
				listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listaEndereco.setSelectionBackground(clYellow);
				listaEndereco.setSelectionForeground(new Color(22,22,22));
				listaEndereco.setModel(new AbstractListModel() {
					// sempre que tem um item selecionado, alterar campos do painel endPanel

					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				
				Font poppins, pop10 = null, pop12 = null;
				listaEndereco.setBackground(new Color(22, 22, 22));
				listaEndereco.setForeground(new Color(197, 197, 197));
				listaEndereco.setFont(pop10);
				listaEndereco.setBounds(0, 0, 156, 113);
				scrollPane.setViewportView(listaEndereco);
						}

				
				}
				System.out.println("debug: tela de cadastro de fornecedor > cadastro contato");
				
			}
		});
		btnAlterarContato.setOpaque(false);
		btnAlterarContato.setForeground(new Color(239, 161, 35));
		btnAlterarContato.setFont(pop12);
		Chisel(btnAlterarContato, clYellow, 5);
		btnAlterarContato.setFocusPainted(false);
		btnAlterarContato.setBackground((Color) null);
		btnAlterarContato.setBounds(179, 171, 156, 23);
		cntPanel.add(btnAlterarContato);
	}

	private void scrollChisel(JScrollPane scrollPane, Color color, int i) {
		scrollPane.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, i);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
		
	}

	private static void panelbuttonChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(417, 124, 417, 124);
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}

	public MaskFormatter def_mask(String envolucro, char substituto) {
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
