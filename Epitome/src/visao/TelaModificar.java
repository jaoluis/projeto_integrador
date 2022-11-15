package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Endereco;
import modelo.Usuario;

public class TelaModificar extends JFrame {
	/**
	 * 
	 */
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
					TelaModificar frame = new TelaModificar(null, -1, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param id 
	 */
	public TelaModificar(Usuario usuarioLogado, int id, JList<String> lista) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clYellow = new Color(239, 161, 35);
		Color clDark = new Color(22, 22, 22);
		Color clLight = new Color(45, 45, 45);
		Color clLighter = new Color(197, 197, 197);

		Font poppins, pop10 = null, pop12 = null;

		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UIManager.put("Button.select", clDark);
		
		UsuarioDAO ubd = new UsuarioDAO(); 
	
		Usuario usuarioOld = UsuarioDAO.getUsuario(id);
		enderecoF = (ArrayList<Endereco>) ubd.getEnderecos(id);	
		contatoC = (ArrayList<Contato>) ubd.getContatos(id);
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(clLight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(clDark);
		panel.setBounds(73, 35, 176, 466);
		panel.setBorder(new RoundBorder());
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel endPanel = new JPanel();
		endPanel.setBackground(clDark);
		endPanel.setBounds(282, 34, 345, 250);
		contentPane.add(endPanel);
		endPanel.setBorder(new RoundBorder());
		endPanel.setLayout(null);
		
		JLabel lblEndereco = new JLabel("ENDERE\u00C7O(S)");
		lblEndereco.setBounds(10, 11, 133, 14);
		endPanel.add(lblEndereco);
		lblEndereco.setForeground(clLighter);
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		
		JScrollPane endScrollPane = new JScrollPane();
		endScrollPane.setBounds(10, 28, 156, 211);
		endPanel.add(endScrollPane);
		Rolagem.defRolagem(endScrollPane);

		endScrollPane.setBorder(new RoundBorder());
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		
		JLabel lblDetEndereco = new JLabel("Endereço");
		lblDetEndereco.setForeground(Color.WHITE);
		lblDetEndereco.setFont(pop12);
		lblDetEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetEndereco.setBounds(176, 11, 156, 14);
		endPanel.add(lblDetEndereco);
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setForeground(clLighter);
		lblCidade.setFont(pop10);
		lblCidade.setBounds(176, 36, 156, 14);
		endPanel.add(lblCidade);

		RoundField txtCidade = new RoundField();
		txtCidade.setCaretColor(Color.WHITE);
		txtCidade.setForeground(Color.WHITE);
		txtCidade.setSelectedTextColor(clDark);
		txtCidade.setSelectionColor(clYellow);
		txtCidade.setBackground(clLight);
		txtCidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCidade.setBounds(176, 50, 156, 20);
		txtCidade.setFont(pop12);
		endPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblBairro = new JLabel("BAIRRO");
		lblBairro.setForeground(clLighter);
		lblBairro.setFont(pop10);
		lblBairro.setBounds(176, 81, 156, 14);
		endPanel.add(lblBairro);

		RoundField txtBairro = new RoundField();
		txtBairro.setCaretColor(Color.WHITE);
		txtBairro.setForeground(Color.WHITE);
		txtBairro.setSelectedTextColor(clDark);
		txtBairro.setSelectionColor(clYellow);
		txtBairro.setBackground(clLight);
		txtBairro.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBairro.setBounds(176, 95, 156, 20);
		txtBairro.setFont(pop12);
		endPanel.add(txtBairro);
		
		JLabel lblRua = new JLabel("RUA");
		lblRua.setForeground(clLighter);
		lblRua.setFont(pop10);
		lblRua.setBounds(176, 126, 156, 14);
		endPanel.add(lblRua);

		RoundField txtRua = new RoundField();
		txtRua.setCaretColor(Color.WHITE);
		txtRua.setForeground(Color.WHITE);
		txtRua.setSelectedTextColor(clDark);
		txtRua.setSelectionColor(clYellow);
		txtRua.setBackground(clLight);
		txtRua.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtRua.setBounds(176, 140, 156, 20);
		txtRua.setFont(pop12);
		endPanel.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("NÚMERO");
		lblNumero.setForeground(clLighter);
		lblNumero.setFont(pop10);
		lblNumero.setBounds(176, 171, 156, 14);
		endPanel.add(lblNumero);

		RoundField txtNumero = new RoundField();
		txtNumero.setCaretColor(Color.WHITE);
		txtNumero.setForeground(Color.WHITE);
		txtNumero.setSelectedTextColor(clDark);
		txtNumero.setSelectionColor(clYellow);
		txtNumero.setBackground(clLight);
		txtNumero.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNumero.setBounds(176, 185, 156, 20);
		txtNumero.setFont(pop12);
		endPanel.add(txtNumero);
		
		JList<String> listaEndereco = new JList<String>();
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
		listaEndereco.setSelectionForeground(clDark);
		listaEndereco.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaEndereco.setBackground(clDark);
		listaEndereco.setForeground(clLighter);
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
				
				TelaCadastro.updateList(listaEndereco, valuesEnd);
				
				txtCidade.setEnabled(true);
				txtBairro.setEnabled(true);
				txtRua.setEnabled(true);
				txtNumero.setEnabled(true);
			}
		});
		btnAddEndereco.setFocusPainted(false);
		btnAddEndereco.setBorder(new RoundBorder(clDark, 1, 22));
		btnAddEndereco.setBackground(null);
		
		endPanel.add(endScrollPane);
		
		RoundButton btnAlterarEndereco= new RoundButton("ALTERAR");
		btnAlterarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (listaEndereco.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Favor inserir um endereço", "Erro", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Favor inserir uma Rua.", "Erro", JOptionPane.ERROR_MESSAGE);
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
				
				Endereco endOld = enderecoF.get(listaEndereco.getSelectedIndex());
				
				Endereco enderecoAdd = new Endereco();
				enderecoAdd.setId(endOld.getId());
				enderecoAdd.setBairro(bairro);
				enderecoAdd.setCidade(cidade);
				enderecoAdd.setNumero(numero1);
				enderecoAdd.setRua(rua);
				
				enderecoF.set(listaEndereco.getSelectedIndex(), enderecoAdd);
				valuesEnd.set(listaEndereco.getSelectedIndex(), cidade + ", " + bairro + ", " + rua + " - " + numero1);

				System.out.println("debug: tela de cadastro de fornecedor > cadastro endereco");
				
				TelaCadastro.updateList(listaEndereco, valuesEnd);
			}
		});
		btnAlterarEndereco.setOpaque(false);
		btnAlterarEndereco.setForeground(clYellow);
		btnAlterarEndereco.setFont(pop12);
		btnAlterarEndereco.setBorder(new RoundBorder(clYellow));
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
				TelaCadastro.updateList(listaEndereco, valuesEnd);
			}
		});
		btnDelEndereco.setIcon(new ImageIcon("./img/delete.png"));
		btnDelEndereco.setOpaque(false);
		btnDelEndereco.setForeground(clRed);
		btnDelEndereco.setFont(pop12);
		btnDelEndereco.setBorder(new RoundBorder(clRed, 1, 24));
		btnDelEndereco.setFocusPainted(false);
		btnDelEndereco.setBackground((Color) null);
		btnDelEndereco.setBounds(308, 215, 24, 24);
		endPanel.add(btnDelEndereco);
		
		
		JPanel cntPanel = new JPanel();
		cntPanel.setBackground(clDark);
		cntPanel.setBounds(282, 295, 345, 205);
		cntPanel.setBorder(new RoundBorder());
		contentPane.add(cntPanel);
		cntPanel.setLayout(null);
				
		JLabel lblDetContato = new JLabel("Contato");
		lblDetContato.setForeground(Color.WHITE);
		lblDetContato.setFont(pop12);
		lblDetContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetContato.setBounds(179, 11, 156, 14);
		cntPanel.add(lblDetContato);
		
		JLabel lblEmailCnt = new JLabel("E-MAIL");
		lblEmailCnt.setForeground(clLighter);
		lblEmailCnt.setFont(pop10);
		lblEmailCnt.setBounds(179, 36, 156, 14);
		cntPanel.add(lblEmailCnt);

		RoundField txtEmailCnt = new RoundField();
		txtEmailCnt.setCaretColor(Color.WHITE);
		txtEmailCnt.setForeground(Color.WHITE);
		txtEmailCnt.setSelectedTextColor(clDark);
		txtEmailCnt.setSelectionColor(clYellow);
		txtEmailCnt.setBackground(clLight);
		txtEmailCnt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmailCnt.setBounds(179, 50, 156, 20);
		txtEmailCnt.setFont(pop12);
		cntPanel.add(txtEmailCnt);
		txtEmailCnt.setColumns(10);

		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setForeground(clLighter);
		lblTelefone.setFont(pop10);
		lblTelefone.setBounds(179, 81, 156, 14);
		cntPanel.add(lblTelefone);

		RoundField txtTelefone = new RoundField();
		txtTelefone.setCaretColor(Color.WHITE);
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setSelectedTextColor(clDark);
		txtTelefone.setSelectionColor(clYellow);
		txtTelefone.setBackground(clLight);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(179, 95, 156, 20);
		txtTelefone.setFont(pop12);
		cntPanel.add(txtTelefone);
		

		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setBounds(10, 11, 131, 14);
		cntPanel.add(lblContato);
		lblContato.setForeground(clLighter);
		lblContato.setFont(null);
		lblContato.setFont(pop10);
		
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setBounds(10, 29, 156, 165);
		cntPanel.add(cntScrollPane);
		cntScrollPane.setBorder(new RoundBorder());
		cntScrollPane.setBackground(null);
		cntScrollPane.setForeground(null);
		Rolagem.defRolagem(cntScrollPane);
		
		
		
		JList<String> listaContato = new JList<String>();
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
		listaContato.setSelectionForeground(clDark);
		listaContato.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaContato.setBackground(clDark);
		listaContato.setForeground(clLighter);
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
				
				TelaCadastro.updateList(listaContato, valuesCnt);
				
				if (contatoC.size() == 1) {
					txtEmailCnt.setText(txtEmail.getText());
				}
				
				txtEmailCnt.setEnabled(true);
				txtTelefone.setEnabled(true);
			}
		});
		btnAddContato.setFocusPainted(false);
		btnAddContato.setBackground(null);
		btnAddContato.setBorder(new RoundBorder(clDark, 1, 22));

		cntPanel.add(cntScrollPane);

		JLabel lblAlterarConta = new JLabel("Modificar conta");
		lblAlterarConta.setForeground(Color.WHITE);
		lblAlterarConta.setFont(pop12);
		lblAlterarConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarConta.setBounds(10, 11, 156, 14);
		panel.add(lblAlterarConta);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setForeground(clLighter);
		lblCPF.setFont(pop10);
		lblCPF.setBounds(10, 167, 156, 14);
		panel.add(lblCPF);

		RoundFormattedField txtCPF = new RoundFormattedField(TelaCadastro.def_mask("###.###.###-##", '\u2022'));
		txtCPF.setText(usuarioOld.getCpf_usuario());
		txtCPF.setCaretColor(Color.WHITE);
		txtCPF.setForeground(Color.WHITE);
		txtCPF.setSelectedTextColor(clDark);
		txtCPF.setSelectionColor(clBlue);
		txtCPF.setBackground(clLight);
		txtCPF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCPF.setBounds(10, 181, 156, 20);
		txtCPF.setFont(pop12);
		panel.add(txtCPF);
		txtCPF.setColumns(10);

		RoundFormattedField txtData = new RoundFormattedField(TelaCadastro.def_mask("##/##/####", '\u2022'));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(usuarioOld.getNascimento_data());
		txtData.setText(dataFormatada);
		txtData.setCaretColor(Color.WHITE);
		txtData.setForeground(Color.WHITE);
		txtData.setSelectedTextColor(clDark);
		txtData.setSelectionColor(clBlue);
		txtData.setBackground(clLight);
		txtData.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtData.setBounds(10, 226, 156, 20);
		txtData.setFont(pop12);
		panel.add(txtData);
		txtData.setColumns(10);
		
		System.out.println("      " + usuarioOld.getLogin_usuario());

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(clLighter);
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 122, 156, 14);
		panel.add(lblNome);

		RoundField txtNome = new RoundField();
		txtNome.setText(usuarioOld.getNome_usuario());
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(Color.WHITE);
		txtNome.setSelectedTextColor(clDark);
		txtNome.setSelectionColor(clBlue);
		txtNome.setBackground(clLight);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 136, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(clLighter);
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);

		txtEmail = new RoundField();
		txtEmail.setText(usuarioOld.getEmail());
		txtEmail.setCaretColor(Color.WHITE);
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setSelectedTextColor(clDark);
		txtEmail.setSelectionColor(clBlue);
		txtEmail.setBackground(clLight);
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmail.setBounds(10, 50, 156, 20);
		txtEmail.setFont(pop12);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setForeground(clLighter);
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);

		txtSenha = new RoundPasswordField();
		txtSenha.setText(usuarioOld.getSenha_usuario());
		txtSenha.setCaretColor(Color.WHITE);
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setSelectedTextColor(clDark);
		txtSenha.setSelectionColor(clBlue);
		txtSenha.setBackground(clLight);
		txtSenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSenha.setBounds(10, 95, 156, 20);
		txtSenha.setFont(pop12);
		txtSenha.setEchoChar('\u2022');
		panel.add(txtSenha);

		JLabel lblDataDeNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataDeNascimento.setForeground(clLighter);
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 212, 156, 14);
		panel.add(lblDataDeNascimento);

		JLabel lblCargo = new JLabel("CARGO");
		lblCargo.setForeground(clLighter);
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

		if (usuarioOld.getCargo().equals("administrador")) {
			rdAdministrador.setSelected(true);
		} else {
			rdVendedor.setSelected(true);
		}
		
		//inserção dos contatos e endereços
		
				int t = 0;
				for (Endereco e: enderecoF) {
					valuesEnd.add("");
					valuesEnd.set(t, e.getCidade() + ", " + e.getBairro() + ", " + e.getRua() + " - " + e.getNumero());
					t++;
				}
				TelaCadastro.updateList(listaEndereco, valuesEnd);
				
				t = 0;
				for (Contato c: contatoC) {
					valuesCnt.add("");
					valuesCnt.set(t, c.getEmail() + " / " + c.getTelefone());
					t++;
				}
				TelaCadastro.updateList(listaContato, valuesCnt);
		
		RoundButton btnAlterar = new RoundButton("MODIFICAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro > cadastrar");
				String email = txtEmail.getText();
				if (email.isBlank()) {
					JOptionPane.showMessageDialog(null, "E-mail invÃ¡lido", "Erro", JOptionPane.ERROR_MESSAGE);
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
//					JOptionPane.showMessageDialog(null, "Favor inserir um nome de usuÃ¡rio", "Erro", JOptionPane.ERROR_MESSAGE);
//					System.out.println("username vazio");
//					return;
//				}
				
				String nome = txtNome.getText();
				if (nome.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um nome", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("nome vazio");
					return;
				}
				
				String cpf = txtCPF.getText();
				for (char c: cpf.toCharArray()) {
					if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
						JOptionPane.showMessageDialog(null, "CPF invÃ¡lido", "Erro", JOptionPane.ERROR_MESSAGE);
						System.out.println("CPF invÃ¡lido: " + cpf);
						return;
					}
				}
				
				String data = txtData.getText();
				String cargo = null;
				LocalDate date = null;
				try {
				date = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				}catch (Exception erroConversaoStringData) {
					//Joption quando dÃ¡ erro na data
					
					JOptionPane.showMessageDialog(null, "Data invÃ¡lida", "Erro", JOptionPane.ERROR_MESSAGE);
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
				usuario.setId_usuario(usuarioLogado.getId_usuario());

				UsuarioDAO dao;
				dao = new UsuarioDAO();
				dao.update(usuario, contatoC);
				dao.updateEnderecoUsuario(usuario, enderecoF);
				
				TelaListarUsuarios.refresh(lista);
				
				dispose();

				// tratamento de exceÃ§Ãµes: campos vazios e formatos errados
				// funcao cadastro (email, senha, nomeUsuario, nome, cpf, data, cargo);
			}
		});
		btnAlterar.setForeground(clBlue);
		btnAlterar.setOpaque(false);
		btnAlterar.setBackground(null);
		btnAlterar.setBorder(new RoundBorder(clBlue));
		btnAlterar.setFont(pop12);
		btnAlterar.setBounds(10, 432, 156, 23);
		panel.add(btnAlterar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
		
		RoundButton btnAlterarContato = new RoundButton("ALTERAR");
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
				
				TelaCadastro.updateList(listaContato, valuesCnt);
				System.out.println("debug: tela de cadastro de fornecedor > cadastro contato");
				
			}
		});
		btnAlterarContato.setOpaque(false);
		btnAlterarContato.setForeground(clYellow);
		btnAlterarContato.setFont(pop12);
		btnAlterarContato.setBorder(new RoundBorder(clYellow));
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
				TelaCadastro.updateList(listaContato, valuesCnt);
			}
		});
		btnDelContato.setIcon(new ImageIcon("./img/delete.png"));
		btnDelContato.setOpaque(false);
		btnDelContato.setForeground(clRed);
		btnDelContato.setFont(null);
		btnDelContato.setFocusPainted(false);
		btnDelContato.setBackground((Color) null);
		btnDelContato.setBorder(new RoundBorder(clRed, 1, 24));
		btnDelContato.setBounds(311, 170, 24, 24);
		cntPanel.add(btnDelContato);
	}
}
