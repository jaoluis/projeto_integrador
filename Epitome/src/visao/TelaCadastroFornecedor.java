package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.FornecedorBD;
import modelo.Contato;
import modelo.Fornecedor;
import modelo.Endereco;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TelaCadastroFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private RoundField txtNome;
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
					TelaCadastroFornecedor frame = new TelaCadastroFornecedor();
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
	public TelaCadastroFornecedor() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(140, 34, 176, 466);
		panel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel endPanel = new JPanel();
		endPanel.setBackground(new Color(22, 22, 22));
		endPanel.setBounds(340, 34, 176, 250);
		endPanel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(endPanel);
		endPanel.setLayout(null);
		
		JPanel cntPanel = new JPanel();
		cntPanel.setBackground(new Color(22, 22, 22));
		cntPanel.setBounds(340, 295, 176, 205);
		cntPanel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(cntPanel);
		cntPanel.setLayout(null);
		
		JLabel lblCriar = new JLabel("Criar Fornecedor");
		lblCriar.setForeground(Color.WHITE);
		lblCriar.setFont(pop12);
		lblCriar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriar.setBounds(10, 11, 156, 14);
		panel.add(lblCriar);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 36, 156, 14);
		panel.add(lblNome);

		txtNome = new RoundField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(Color.WHITE);
		txtNome.setSelectedTextColor(new Color(22, 22, 22));
		txtNome.setSelectionColor(clYellow);
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 50, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCNPJ = new JLabel("CNPJ");
		lblCNPJ.setForeground(new Color(197, 197, 197));
		lblCNPJ.setFont(pop10);
		lblCNPJ.setBounds(10, 81, 156, 14);
		panel.add(lblCNPJ);

		RoundFormattedField txtCNPJ = new RoundFormattedField(def_mask("##.###.###/####-##", '\u2022'));
		txtCNPJ.setCaretColor(Color.WHITE);
		txtCNPJ.setForeground(Color.WHITE);
		txtCNPJ.setSelectedTextColor(new Color(22, 22, 22));
		txtCNPJ.setSelectionColor(clYellow);
		txtCNPJ.setBackground(new Color(45, 45, 45));
		txtCNPJ.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCNPJ.setBounds(10, 95, 156, 20);
		txtCNPJ.setFont(pop12);
		panel.add(txtCNPJ);

		
		
		JLabel lblEndereco = new JLabel("ENDERE\u00C7O(S)");
		lblEndereco.setForeground(new Color(197, 197, 197));
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		lblEndereco.setBounds(10, 124, 130, 14);
		panel.add(lblEndereco);
		
		
		
		JScrollPane endScrollPane = new JScrollPane();
		endScrollPane.setBounds(10, 141, 156, 113);
		Rolagem.defRolagem(endScrollPane);
		endScrollPane.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		
		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setForeground(new Color(197, 197, 197));
		lblContato.setFont(null);
		lblContato.setFont(pop10);
		lblContato.setBounds(10, 261, 130, 14);
		panel.add(lblContato);
	
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setBounds(10, 279, 156, 113);
		Rolagem.defRolagem(cntScrollPane);
		cntScrollPane.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		cntScrollPane.setBackground(null);
		cntScrollPane.setForeground(null);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
		
		JLabel lblDetEndereco = new JLabel("Endere\u00E7o");
		lblDetEndereco.setForeground(Color.WHITE);
		lblDetEndereco.setFont(pop12);
		lblDetEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetEndereco.setBounds(10, 11, 156, 14);
		endPanel.add(lblDetEndereco);
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setForeground(new Color(197, 197, 197));
		lblCidade.setFont(pop10);
		lblCidade.setBounds(10, 36, 156, 14);
		endPanel.add(lblCidade);

		RoundField txtCidade = new RoundField();
		txtCidade.setCaretColor(Color.WHITE);
		txtCidade.setForeground(Color.WHITE);
		txtCidade.setSelectedTextColor(new Color(22, 22, 22));
		txtCidade.setSelectionColor(clYellow);
		txtCidade.setEnabled(false);
		txtCidade.setBackground(new Color(45, 45, 45));
		txtCidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCidade.setBounds(10, 50, 156, 20);
		txtCidade.setFont(pop12);
		endPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblBairro = new JLabel("BAIRRO");
		lblBairro.setForeground(new Color(197, 197, 197));
		lblBairro.setFont(pop10);
		lblBairro.setBounds(10, 81, 156, 14);
		endPanel.add(lblBairro);

		RoundField txtBairro = new RoundField();
		txtBairro.setCaretColor(Color.WHITE);
		txtBairro.setForeground(Color.WHITE);
		txtBairro.setSelectedTextColor(new Color(22, 22, 22));
		txtBairro.setSelectionColor(clYellow);
		txtBairro.setEnabled(false);
		txtBairro.setBackground(new Color(45, 45, 45));
		txtBairro.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBairro.setBounds(10, 95, 156, 20);
		txtBairro.setFont(pop12);
		endPanel.add(txtBairro);
		
		JLabel lblRua = new JLabel("RUA");
		lblRua.setForeground(new Color(197, 197, 197));
		lblRua.setFont(pop10);
		lblRua.setBounds(10, 126, 156, 14);
		endPanel.add(lblRua);

		RoundField txtRua = new RoundField();
		txtRua.setCaretColor(Color.WHITE);
		txtRua.setForeground(Color.WHITE);
		txtRua.setSelectedTextColor(new Color(22, 22, 22));
		txtRua.setSelectionColor(clYellow);
		txtRua.setEnabled(false);
		txtRua.setBackground(new Color(45, 45, 45));
		txtRua.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtRua.setBounds(10, 140, 156, 20);
		txtRua.setFont(pop12);
		endPanel.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00DAMERO");
		lblNumero.setForeground(new Color(197, 197, 197));
		lblNumero.setFont(pop10);
		lblNumero.setBounds(10, 171, 156, 14);
		endPanel.add(lblNumero);

		RoundField txtNumero = new RoundField();
		txtNumero.setCaretColor(Color.WHITE);
		txtNumero.setForeground(Color.WHITE);
		txtNumero.setSelectedTextColor(new Color(22, 22, 22));
		txtNumero.setSelectionColor(clYellow);
		txtNumero.setEnabled(false);
		txtNumero.setBackground(new Color(45, 45, 45));
		txtNumero.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNumero.setBounds(10, 185, 156, 20);
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
		btnAddEndereco.setBackground(null);
		btnAddEndereco.setBorder(new RoundBorder(new Color(22, 22, 22), 1, 22));
		btnAddEndereco.setBounds(135, 129, 22, 22);
		panel.add(btnAddEndereco);
		
		panel.add(endScrollPane);
		
		JLabel lblDetContato = new JLabel("Contato");
		lblDetContato.setForeground(Color.WHITE);
		lblDetContato.setFont(pop12);
		lblDetContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetContato.setBounds(10, 11, 156, 14);
		cntPanel.add(lblDetContato);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		cntPanel.add(lblEmail);

		RoundField txtEmail = new RoundField();
		txtEmail.setCaretColor(Color.WHITE);
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setSelectedTextColor(new Color(22, 22, 22));
		txtEmail.setSelectionColor(clYellow);
		txtEmail.setEnabled(false);
		txtEmail.setBackground(new Color(45, 45, 45));
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtEmail.setBounds(10, 50, 156, 20);
		txtEmail.setFont(pop12);
		cntPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setForeground(new Color(197, 197, 197));
		lblTelefone.setFont(pop10);
		lblTelefone.setBounds(10, 81, 156, 14);
		cntPanel.add(lblTelefone);

		RoundField txtTelefone = new RoundField();
		txtTelefone.setCaretColor(Color.WHITE);
		txtTelefone.setForeground(Color.WHITE);
		txtTelefone.setSelectedTextColor(new Color(22, 22, 22));
		txtTelefone.setSelectionColor(clYellow);
		txtTelefone.setEnabled(false);
		txtTelefone.setBackground(new Color(45, 45, 45));
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(10, 95, 156, 20);
		txtTelefone.setFont(pop12);
		cntPanel.add(txtTelefone);
		
		JList<String> listaContato = new JList<String>();
		listaContato.setFocusable(false);
		listaContato.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listaContato.getSelectedIndex() == -1) {
					return;
				}
				
				Contato con = contatoC.get(listaContato.getSelectedIndex());
				txtEmail.setText(con.getEmail());
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
		btnAddContato.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contatoC.add(new Contato());
				valuesCnt.add(Integer.toString(valuesCnt.size() + 1));
				
				TelaCadastro.updateList(listaContato, valuesCnt);
				
				txtEmail.setEnabled(true);
				txtTelefone.setEnabled(true);
			}
		});
		btnAddContato.setFocusPainted(false);
		btnAddContato.setBackground(null);
		btnAddContato.setBorder(new RoundBorder(new Color(22, 22, 22), 1, 22));
		btnAddContato.setBounds(135, 267, 22, 22);
		panel.add(btnAddContato);
		
		panel.add(cntScrollPane);
		
		JButton btnAlterarContato = new JButton("ALTERAR");
		btnAlterarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText();
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
				contato.setId(contatoC.get(listaContato.getSelectedIndex()).getId());
				contato.setEmail(email);
				contato.setTelefone(telefone);
				contatoC.set(listaContato.getSelectedIndex(), contato);
				valuesCnt.set(listaContato.getSelectedIndex(), email + " / " + telefone);
				
				TelaCadastro.updateList(listaContato, valuesCnt);
				System.out.println("debug: tela de cadastro de fornecedor > cadastro contato");
			}
		});
		btnAlterarContato.setOpaque(false);
		btnAlterarContato.setForeground(new Color(239, 161, 35));
		btnAlterarContato.setFont(pop12);
		btnAlterarContato.setBorder(new RoundBorder(clYellow, 1, 10));
		btnAlterarContato.setFocusPainted(false);
		btnAlterarContato.setBackground((Color) null);
		btnAlterarContato.setBounds(10, 171, 123, 23);
		cntPanel.add(btnAlterarContato);
		
		JButton btnDelContato = new JButton("");
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
		btnDelContato.setBounds(143, 171, 24, 24);
		cntPanel.add(btnDelContato);
		
		JButton btnAlterarEndereco= new JButton("ALTERAR");
		btnAlterarEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					JOptionPane.showMessageDialog(null, "Digite apenas n�meros.", "Aviso", JOptionPane.WARNING_MESSAGE);
					System.out.println("Não converteu para inteiro");
					return;
				}
				

				if (numero.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um n�mero.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("numero vazio");
					return;
				}
				
				Endereco enderecoAdd = new Endereco();
				enderecoAdd.setId(enderecoF.get(listaEndereco.getSelectedIndex()).getId());
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
		btnAlterarEndereco.setForeground(new Color(239, 161, 35));
		btnAlterarEndereco.setFont(pop12);
		btnAlterarEndereco.setBorder(new RoundBorder(clYellow, 1, 10));
		btnAlterarEndereco.setFocusPainted(false);
		btnAlterarEndereco.setBackground((Color) null);
		btnAlterarEndereco.setBounds(10, 216, 123, 23);
		endPanel.add(btnAlterarEndereco);
		
		JButton btnDelEndereco = new JButton("");
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
		btnDelEndereco.setBounds(143, 216, 24, 24);
		endPanel.add(btnDelEndereco);
		
		
		JButton btnContinuar = new JButton("CADASTRAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro de fornecedor > cadastrar fornecedor");
				FornecedorBD fornecedorBD = new FornecedorBD();
				Fornecedor fornecedor =  new Fornecedor();
				
			
			String nome = txtNome.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um Nome.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("nome vazio");
				return;
			}
			
			String cnpj = txtCNPJ.getText();
			
			if (cnpj.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um cnpj", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("cnpj Vazio");
				return;
			}
			
			String cleanCNPJ = cnpj.replace(".","").replace("/", "").replace("-", "");
			
			if(FornecedorBD.isCNPJ(cleanCNPJ) == true) {
			fornecedor.setNome_fornecedor(nome);
			fornecedor.setCnpj_fornecedor(cnpj);
			
			long id = fornecedorBD.insert(fornecedor);
			System.out.println("fk_id_fornecedor: " + id);
			
			for (Endereco enderecoA: enderecoF) {
				System.out.println(enderecoA.getCidade() + ", id: " + enderecoA.getId());
				fornecedorBD.insertEndereco(enderecoA, id);
			}
			for (Contato contatoA : contatoC) {
				fornecedorBD.insertContato(contatoA, id);
			}

			}else {
				JOptionPane.showMessageDialog(null, "CNPJ ou E-mail inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("Email Invalido ou CNPJ");
				return;
			}
			dispose();
			
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		btnContinuar.setForeground(clYellow);
		btnContinuar.setBorder(new RoundBorder(clYellow, 1, 10));
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 432, 156, 23);
		panel.add(btnContinuar);
		
		
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
}
