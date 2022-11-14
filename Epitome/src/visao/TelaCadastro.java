package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Endereco;
import modelo.Usuario;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private RoundField txtEmail;
	private RoundPasswordField txtSenha;
	private final ButtonGroup cargoGroup = new ButtonGroup();
	ArrayList<Endereco> enderecoF =  new ArrayList<Endereco>();
	ArrayList<Contato> contatoC =  new ArrayList<Contato>();
	ArrayList<String> valuesEnd = new ArrayList<String>();
	ArrayList<String> valuesCnt = new ArrayList<String>();

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
		Color clYellow = new Color(239, 161, 35);

		Font poppins, pop10 = null, pop12 = null;

		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UIManager.put("Button.select", Color.WHITE);
		
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
		panel.setBorder(new RoundBorder(Color.WHITE,1,10));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel endPanel = new JPanel();
		endPanel.setBackground(new Color(22, 22, 22));
		endPanel.setBounds(282, 34, 345, 250);
		endPanel.setBorder(new RoundBorder(Color.WHITE,1,10));
		contentPane.add(endPanel);
		endPanel.setLayout(null);
		
		JLabel lblEndereco = new JLabel("ENDERE\u00C7O(S)");
		lblEndereco.setBounds(10, 11, 133, 14);
		endPanel.add(lblEndereco);
		lblEndereco.setForeground(new Color(197, 197, 197));
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		
		JScrollPane endScrollPane = new JScrollPane();
		endScrollPane.setBounds(10, 28, 156, 211);
		Rolagem.defRolagem(endScrollPane);
		endScrollPane.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		
		JLabel lblDetEndereco = new JLabel("Endere\u00E7o");
		lblDetEndereco.setForeground(Color.WHITE);
		lblDetEndereco.setFont(pop12);
		lblDetEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetEndereco.setBounds(176, 11, 156, 14);
		endPanel.add(lblDetEndereco);
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setForeground(new Color(197, 197, 197));
		lblCidade.setFont(pop10);
		lblCidade.setBounds(176, 36, 156, 14);
		endPanel.add(lblCidade);

		RoundField txtCidade = new RoundField();
		txtCidade.setCaretColor(Color.WHITE);
		txtCidade.setForeground(Color.WHITE);
		txtCidade.setSelectedTextColor(new Color(22, 22, 22));
		txtCidade.setSelectionColor(clYellow);
		txtCidade.setEnabled(false);
		txtCidade.setBackground(new Color(45, 45, 45));
		txtCidade.setBorder(BorderFactory.createEmptyBorder());
		txtCidade.setBounds(176, 50, 156, 20);
		txtCidade.setFont(pop12);
		endPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblBairro = new JLabel("BAIRRO");
		lblBairro.setForeground(new Color(197, 197, 197));
		lblBairro.setFont(pop10);
		lblBairro.setBounds(176, 81, 156, 14);
		endPanel.add(lblBairro);

		RoundField txtBairro = new RoundField();
		txtBairro.setCaretColor(Color.WHITE);
		txtBairro.setForeground(Color.WHITE);
		txtBairro.setSelectedTextColor(new Color(22, 22, 22));
		txtBairro.setSelectionColor(clYellow);
		txtBairro.setEnabled(false);
		txtBairro.setBackground(new Color(45, 45, 45));
		txtBairro.setBorder(BorderFactory.createEmptyBorder());
		txtBairro.setBounds(176, 95, 156, 20);
		txtBairro.setFont(pop12);
		endPanel.add(txtBairro);
		
		JLabel lblRua = new JLabel("RUA");
		lblRua.setForeground(new Color(197, 197, 197));
		lblRua.setFont(pop10);
		lblRua.setBounds(176, 126, 156, 14);
		endPanel.add(lblRua);

		RoundField txtRua = new RoundField();
		txtRua.setCaretColor(Color.WHITE);
		txtRua.setForeground(Color.WHITE);
		txtRua.setSelectedTextColor(new Color(22, 22, 22));
		txtRua.setSelectionColor(clYellow);
		txtRua.setEnabled(false);
		txtRua.setBackground(new Color(45, 45, 45));
		txtRua.setBorder(BorderFactory.createEmptyBorder());
		txtRua.setBounds(176, 140, 156, 20);
		txtRua.setFont(pop12);
		endPanel.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00DAMERO");
		lblNumero.setForeground(new Color(197, 197, 197));
		lblNumero.setFont(pop10);
		lblNumero.setBounds(176, 171, 156, 14);
		endPanel.add(lblNumero);

		RoundField txtNumero = new RoundField();
		txtNumero.setCaretColor(Color.WHITE);
		txtNumero.setForeground(Color.WHITE);
		txtNumero.setSelectedTextColor(new Color(22, 22, 22));
		txtNumero.setSelectionColor(clYellow);
		txtNumero.setEnabled(false);
		txtNumero.setBackground(new Color(45, 45, 45));
		txtNumero.setBorder(BorderFactory.createEmptyBorder());
		txtNumero.setBounds(176, 185, 156, 20);
		txtNumero.setFont(pop12);
		endPanel.add(txtNumero);
		
		JList<String> listaEndereco = new JList<String>();
		listaEndereco.setFocusable(false);
		listaEndereco.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listaEndereco.getSelectedIndex() == -1) {
					return;
				}
				
				Endereco end = enderecoF.get(listaEndereco.getSelectedIndex());
				txtCidade.setText(end.getCidade());
				txtBairro.setText(end.getBairro());
				txtRua.setText(end.getRua());
				txtNumero.setText(Integer.toString(end.getNumero()));
			}
		});
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(new Color(22,22,22));
		listaEndereco.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaEndereco.setBackground(new Color(22, 22, 22));
		listaEndereco.setForeground(new Color(197, 197, 197));
		listaEndereco.setFont(pop10);
		listaEndereco.setBounds(0, 50, 156, 113);
		endScrollPane.setViewportView(listaEndereco);
		
		JButton btnAddEndereco = new JButton("");
		btnAddEndereco.setBounds(155, 17, 22, 22);
		endPanel.add(btnAddEndereco);
		btnAddEndereco.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				enderecoF.add(new Endereco());
				valuesEnd.add(Integer.toString(valuesEnd.size() + 1));
				
				updateList(listaEndereco, valuesEnd);
				
				txtCidade.setEnabled(true);
				txtBairro.setEnabled(true);
				txtRua.setEnabled(true);
				txtNumero.setEnabled(true);
			}
		});
		btnAddEndereco.setFocusPainted(false);
		btnAddEndereco.setBorder(new RoundBorder(new Color(22, 22, 22), 1, 22));
		btnAddEndereco.setBackground(null);
		
		endPanel.add(endScrollPane);
		
		RoundButton btnAlterarEndereco= new RoundButton("ALTERAR");
		btnAlterarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = listaEndereco.getSelectedIndex();
				if (sel == -1) {
					JOptionPane.showMessageDialog(null, "Favor inserir um endere\u00E7o", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("sem enderecos");	
					return;
				}
				
				String cidade = txtCidade.getText();
				if (cidade.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma cidade", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("cidade Vazio");
					return;
				}
				
				String rua = txtRua.getText();
				if (rua.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma rua.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("rua vazio");
					return;
				}
				
				String bairro = txtBairro.getText();
				if (bairro.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um bairro.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("bairro vazio");
					return;
				}
				String numero = txtNumero.getText();
				int numero1;
				
				try {
					numero1 = Integer.parseInt(txtNumero.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Digite apenas números.", "Aviso", JOptionPane.WARNING_MESSAGE);
					System.out.println("Não converteu para inteiro");
					return;
				}
				

				if (numero.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um número.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("numero vazio");
					return;
				}
				
				Endereco enderecoAdd = new Endereco();
				enderecoAdd.setBairro(bairro);
				enderecoAdd.setCidade(cidade);
				enderecoAdd.setNumero(numero1);
				enderecoAdd.setRua(rua);
				
				enderecoF.set(sel, enderecoAdd);
				valuesEnd.set(sel, cidade + ", " + bairro + ", " + rua + " - " + numero1);

				System.out.println("debug: tela de cadastro de fornecedor > cadastro endereco");
				
				updateList(listaEndereco, valuesEnd);				
			}
		});
		btnAlterarEndereco.setOpaque(false);
		btnAlterarEndereco.setForeground(clYellow);
		btnAlterarEndereco.setFont(pop12);
		btnAlterarEndereco.setBorder(new RoundBorder(clYellow, 1, 10));
		btnAlterarEndereco.setFocusPainted(false);
		btnAlterarEndereco.setBackground((Color) null);
		btnAlterarEndereco.setBounds(176, 216, 123, 23);
		endPanel.add(btnAlterarEndereco);
		
		RoundButton btnDelEndereco = new RoundButton("");
		btnDelEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = listaEndereco.getSelectedIndex();
				
				if (idx == -1) {
					return;
				}
				
				enderecoF.remove(idx);
				valuesEnd.remove(idx);
				updateList(listaEndereco, valuesEnd);
			}
		});
		btnDelEndereco.setIcon(new ImageIcon("./img/delete.png"));
		btnDelEndereco.setOpaque(false);
		btnDelEndereco.setForeground(clRed);
		btnDelEndereco.setFont(pop12);
		btnDelEndereco.setBorder(new RoundBorder(clRed, 1, 10));
		btnDelEndereco.setFocusPainted(false);
		btnDelEndereco.setBackground((Color) null);
		btnDelEndereco.setBounds(309, 216, 23, 23);
		endPanel.add(btnDelEndereco);
		
		
		JPanel cntPanel = new JPanel();
		cntPanel.setBackground(new Color(22, 22, 22));
		cntPanel.setBounds(282, 295, 345, 205);
		cntPanel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(cntPanel);
		cntPanel.setLayout(null);
				
		JLabel lblDetContato = new JLabel("Contato");
		lblDetContato.setForeground(Color.WHITE);
		lblDetContato.setFont(pop12);
		lblDetContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetContato.setBounds(179, 11, 156, 14);
		cntPanel.add(lblDetContato);
		
		JLabel lblEmailCnt = new JLabel("E-MAIL");
		lblEmailCnt.setForeground(new Color(197, 197, 197));
		lblEmailCnt.setFont(pop10);
		lblEmailCnt.setBounds(179, 36, 156, 14);
		cntPanel.add(lblEmailCnt);

		RoundField txtEmailCnt = new RoundField();
		txtEmailCnt.setCaretColor(Color.WHITE);
		txtEmailCnt.setForeground(Color.WHITE);
		txtEmailCnt.setSelectedTextColor(new Color(22, 22, 22));
		txtEmailCnt.setSelectionColor(clYellow);
		txtEmailCnt.setEnabled(false);
		txtEmailCnt.setBackground(new Color(45, 45, 45));
		txtEmailCnt.setBorder(BorderFactory.createEmptyBorder());
		txtEmailCnt.setBounds(179, 50, 156, 20);
		txtEmailCnt.setFont(pop12);
		cntPanel.add(txtEmailCnt);
		txtEmailCnt.setColumns(10);

		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setForeground(new Color(197, 197, 197));
		lblTelefone.setFont(pop10);
		lblTelefone.setBounds(179, 81, 156, 14);
		cntPanel.add(lblTelefone);

		RoundField txtTelefone = new RoundField();
		txtTelefone.setCaretColor(Color.WHITE);
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setSelectedTextColor(new Color(22, 22, 22));
		txtTelefone.setSelectionColor(clYellow);
		txtTelefone.setEnabled(false);
		txtTelefone.setBackground(new Color(45, 45, 45));
		txtTelefone.setBorder(BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(179, 95, 156, 20);
		txtTelefone.setFont(pop12);
		cntPanel.add(txtTelefone);
		

		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setBounds(10, 11, 131, 14);
		cntPanel.add(lblContato);
		lblContato.setForeground(new Color(197, 197, 197));
		lblContato.setFont(null);
		lblContato.setFont(pop10);
		
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setBounds(10, 29, 156, 165);
		cntScrollPane.setBorder(new RoundBorder(Color.WHITE, 1,10));
		cntScrollPane.setBackground(null);
		cntScrollPane.setForeground(null);
		Rolagem.defRolagem(cntScrollPane);
		
		
		
		JList<String> listaContato = new JList<String>();
		listaContato.setFocusable(false);
		listaContato.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listaContato.getSelectedIndex() == -1) {
					return;
				}
				
				Contato con = contatoC.get(listaContato.getSelectedIndex());
				txtEmailCnt.setText(con.getEmail());
				txtTelefone.setText(con.getTelefone());
			}
		});
		listaContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContato.setSelectionBackground(clYellow);
		listaContato.setSelectionForeground(new Color(22,22,22));
		listaContato.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaContato.setBackground(new Color(22, 22, 22));
		listaContato.setForeground(new Color(197, 197, 197));
		listaContato.setFont(pop10);
		listaContato.setBounds(0, 50, 156, 113);
		cntScrollPane.setViewportView(listaContato);
			
		JButton btnAddContato = new JButton("");
		btnAddContato.setBounds(155, 17, 22, 22);
		cntPanel.add(btnAddContato);
		btnAddContato.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contatoC.add(new Contato());
				valuesCnt.add(Integer.toString(valuesCnt.size() + 1));
				
				updateList(listaContato, valuesCnt);
				
				if (contatoC.size() == 1) {
					txtEmailCnt.setText(txtEmail.getText());
				}
				
				txtEmailCnt.setEnabled(true);
				txtTelefone.setEnabled(true);
			}
		});
		btnAddContato.setFocusPainted(false);
		btnAddContato.setBackground(null);
		btnAddContato.setBorder(new RoundBorder(new Color(22, 22, 22), 1, 22));

		cntPanel.add(cntScrollPane);
		
		JLabel lblCriarConta = new JLabel("Criar conta");
		lblCriarConta.setForeground(Color.WHITE);
		lblCriarConta.setFont(pop12);
		lblCriarConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriarConta.setBounds(10, 11, 156, 14);
		panel.add(lblCriarConta);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop10);
		lblCPF.setBounds(10, 167, 156, 14);
		panel.add(lblCPF);

		RoundFormattedField txtCPF = new RoundFormattedField(def_mask("###.###.###-##", '\u2022'));
		txtCPF.setCaretColor(Color.WHITE);
		txtCPF.setForeground(Color.WHITE);
		txtCPF.setSelectedTextColor(new Color(22, 22, 22));
		txtCPF.setSelectionColor(clBlue);
		txtCPF.setBackground(new Color(45, 45, 45));
		txtCPF.setBorder(BorderFactory.createEmptyBorder());
		txtCPF.setBounds(10, 181, 156, 20);
		txtCPF.setFont(pop12);
		panel.add(txtCPF);
		txtCPF.setColumns(10);

		RoundFormattedField txtData = new RoundFormattedField(def_mask("##/##/####", '\u2022'));
		txtData.setCaretColor(Color.WHITE);
		txtData.setForeground(Color.WHITE);
		txtData.setSelectedTextColor(new Color(22, 22, 22));
		txtData.setSelectionColor(clBlue);
		txtData.setBackground(new Color(45, 45, 45));
		txtData.setBorder(BorderFactory.createEmptyBorder());
		txtData.setBounds(10, 226, 156, 20);
		txtData.setFont(pop12);
		panel.add(txtData);
		txtData.setColumns(10);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 126, 156, 14);
		panel.add(lblNome);

		RoundField txtNome = new RoundField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(Color.WHITE);
		txtNome.setSelectedTextColor(new Color(22, 22, 22));
		txtNome.setSelectionColor(clBlue);
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 140, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);

		txtEmail = new RoundField();
		txtEmail.setCaretColor(Color.WHITE);
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setSelectedTextColor(new Color(22, 22, 22));
		txtEmail.setSelectionColor(clBlue);
		txtEmail.setBackground(new Color(45, 45, 45));
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		txtEmail.setBounds(10, 50, 156, 20);
		txtEmail.setFont(pop12);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);

		txtSenha = new RoundPasswordField();
		txtSenha.setCaretColor(Color.WHITE);
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setSelectedTextColor(new Color(22, 22, 22));
		txtSenha.setSelectionColor(clBlue);
		txtSenha.setBackground(new Color(45, 45, 45));
		txtSenha.setBorder(BorderFactory.createEmptyBorder());
		txtSenha.setBounds(10, 95, 156, 20);
		txtSenha.setFont(pop12);
		txtSenha.setEchoChar('\u2022');
		panel.add(txtSenha);

		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setForeground(new Color(197, 197, 197));
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 212, 156, 14);
		panel.add(lblDataDeNascimento);

		JLabel lblCargo = new JLabel("CARGO");
		lblCargo.setForeground(new Color(197, 197, 197));
		lblCargo.setBounds(10, 257, 156, 14);
		lblCargo.setFont(pop10);
		panel.add(lblCargo);

		JRadioButton rdVendedor = new JRadioButton("VENDEDOR");
		cargoGroup.add(rdVendedor);
		rdVendedor.setForeground(Color.WHITE);
		rdVendedor.setBackground(null);
		rdVendedor.setFont(pop10);
		rdVendedor.setIcon(new ImageIcon("./img/radio_button.png"));
		rdVendedor.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdVendedor.setBounds(10, 278, 156, 23);
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
		rdAdministrador.setBounds(10, 304, 156, 23);
		rdAdministrador.setFocusPainted(false);
		panel.add(rdAdministrador);

		RoundButton btnCadastrar = new RoundButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > cadastrar");
				UsuarioDAO dao;
				dao = new UsuarioDAO();
				
				String cpf = txtCPF.getText();
				for (char c: cpf.toCharArray()) {
					if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
						JOptionPane.showMessageDialog(null, "CPF inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
						System.out.println("CPF inv\u00E1lido: " + cpf);
						return;
					}
				}
				
				String email = txtEmail.getText();
				if (email.isBlank()) {
					JOptionPane.showMessageDialog(null, "E-mail inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("E-mail vazio");
					return;
				}
				
				String senha = String.valueOf(txtSenha.getPassword());
				
				if (senha.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma senha", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Senha vazia");
					return;
				}
				
//				String nomeUsuario = txtUsername.getText();
//				if (nomeUsuario.isBlank()) {
//					JOptionPane.showMessageDialog(null, "Favor inserir um nome de usuário", "Erro", JOptionPane.ERROR_MESSAGE);
//					System.out.println("username vazio");
//					return;
//				}
				
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

				if(dao.validarEmail(email) == true && UsuarioDAO.validarCPF(cpfSN.toString()) == true) {
				
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
//				usuario.setLogin_usuario(nomeUsuario);
				usuario.setNascimento_data(Date.valueOf(date));

				long id = dao.insert(usuario);
				
				for (Endereco enderecoA: enderecoF) {
					if (!enderecoA.isBlank()) {
						dao.insertEndereco(enderecoA, id);
					}
				}
				
				for (Contato contatoA : contatoC) {
					if (!contatoA.isBlank()) {
						dao.insertContato(contatoA, id);
					}
				}
				}else {
					System.out.println("Email ou CPF inválido " + email + cpf);
				}
			}
				
		});
		btnCadastrar.setOpaque(false);
		btnCadastrar.setBackground(null);
		btnCadastrar.setForeground(clBlue);
		btnCadastrar.setBorder(new RoundBorder(clBlue, 1, 10));
		btnCadastrar.setFont(pop12);
		btnCadastrar.setBounds(10, 432, 156, 23);
		panel.add(btnCadastrar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
		
		JButton btnAlterarContato = new RoundButton("ALTERAR");
		btnAlterarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (listaContato.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Favor inserir um contato", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("sem contatos");	
					return;
				}
				
				String email = txtEmailCnt.getText();
				if (email.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um email", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("email Vazio");
					return;
				}
				
				String telefone = txtTelefone.getText();
				if (telefone.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um telefone.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("telefone vazio");
					return;
				}
			
				Contato contato = new Contato();
				contato.setEmail(email);
				contato.setTelefone(telefone);
				contatoC.set(listaContato.getSelectedIndex(), contato);
				valuesCnt.set(listaContato.getSelectedIndex(), email + " / " + telefone);
				
				updateList(listaContato, valuesCnt);
				System.out.println("debug: tela de cadastro de fornecedor > cadastro contato");
				
			}
		});
		btnAlterarContato.setOpaque(false);
		btnAlterarContato.setForeground(clYellow);
		btnAlterarContato.setFont(pop12);
		btnAlterarContato.setBorder(new RoundBorder(clYellow, 1, 10));
		btnAlterarContato.setFocusPainted(false);
		btnAlterarContato.setBackground((Color) null);
		btnAlterarContato.setBounds(179, 171, 123, 23);
		cntPanel.add(btnAlterarContato);
		
		RoundButton btnDelContato = new RoundButton("");
		btnDelContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = listaContato.getSelectedIndex();
				
				if (idx == -1) {
					return;
				}
				
				contatoC.remove(idx);
				valuesCnt.remove(idx);
				updateList(listaContato, valuesCnt);
			}
		});
		btnDelContato.setIcon(new ImageIcon("./img/delete.png"));
		btnDelContato.setOpaque(false);
		btnDelContato.setForeground(clRed);
		btnDelContato.setFont(null);
		btnDelContato.setFocusPainted(false);
		btnDelContato.setBackground((Color) null);
		btnDelContato.setBorder(new RoundBorder(clRed, 1, 10));
		btnDelContato.setBounds(312, 171, 23, 23);
		cntPanel.add(btnDelContato);
	}

	public static void updateList(JList<String> lista, ArrayList<String> values) {
		
		int sel = lista.getSelectedIndex();
//		System.out.println(sel);
		System.out.println("lista atualizada");
		String[] up = new String[values.size()];
		
		for (int i = 0; i < values.size(); i++) {
            up[i] = values.get(i);
        }
		
		lista.setListData(up);
		
		if (sel !=-1 && lista.getModel().getSize() > 0) {
			try {
				lista.setSelectedIndex(sel);
			} catch (IllegalArgumentException x) {
				lista.setSelectedIndex(lista.getModel().getSize()-2);
			}
		} else if (sel == -1) {
			try {
				lista.setSelectedIndex(0);
			} catch (IllegalArgumentException x) {}
		}
		
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
}