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
import javax.swing.JDialog;
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
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.MaskFormatter;

import controle.Conexao;
import controle.FornecedorBD;
import controle.UsuarioDAO;
import modelo.Fornecedor;
import modelo.Usuario;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class TelaCadastroFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblCidade;

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
	@SuppressWarnings("unchecked")
	public TelaCadastroFornecedor() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clYellow = new Color(239, 161, 35);
		Color clLight = new Color(197, 197, 197);
		
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
		panel.setBounds(140, 34, 176, 466);
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel endPanel = new JPanel();
		endPanel.setBackground(new Color(22, 22, 22));
		endPanel.setBounds(340, 34, 176, 250);
		panelbuttonChisel(endPanel, new Color(255, 255, 255), 5);
		contentPane.add(endPanel);
		endPanel.setLayout(null);
		
		JPanel cntPanel = new JPanel();
		cntPanel.setBackground(new Color(22, 22, 22));
		cntPanel.setBounds(340, 295, 176, 205);
		panelbuttonChisel(cntPanel, new Color(255, 255, 255), 5);
		contentPane.add(cntPanel);
		cntPanel.setLayout(null);
		
		JLabel lblCriar = new JLabel("Criar Fornecedor");
		lblCriar.setForeground(new Color(255, 255, 255));
		lblCriar.setFont(pop12);
		lblCriar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriar.setBounds(10, 11, 156, 14);
		panel.add(lblCriar);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 36, 156, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setForeground(new Color(255, 255, 255));
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

		JTextField txtCNPJ = new JTextField();
		txtCNPJ.setForeground(new Color(255, 255, 255));
		txtCNPJ.setBackground(new Color(45, 45, 45));
		txtCNPJ.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCNPJ.setBounds(10, 95, 156, 20);
		txtCNPJ.setFont(pop12);
		panel.add(txtCNPJ);

		
		
		JLabel lblEndereco = new JLabel("ENDERE�O(S)");
		lblEndereco.setForeground(new Color(197, 197, 197));
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		lblEndereco.setBounds(10, 126, 156, 14);
		panel.add(lblEndereco);
		
		JButton btnAddEndereco = new JButton("");
		btnAddEndereco.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// adicionar endereço à lista
			}
		});
		btnAddEndereco.setBackground(null);
		btnAddEndereco.setBorder(BorderFactory.createEmptyBorder());
		btnAddEndereco.setBounds(150, 124, 16, 16);
		panel.add(btnAddEndereco);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 141, 156, 113);
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
		panel.add(scrollPane);
		
		JList listaEndereco = new JList();
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(new Color(22,22,22));
		listaEndereco.setModel(new AbstractListModel() {
			// sempre que tem um item selecionado, alterar campos do painel endPanel
			String[] values = new String[] {""};
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
		listaEndereco.setBounds(0, 0, 156, 113);
		scrollPane.setViewportView(listaEndereco);
		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setForeground(new Color(197, 197, 197));
		lblContato.setFont(null);
		lblContato.setFont(pop10);
		lblContato.setBounds(10, 265, 156, 14);
	panel.add(lblContato);
	
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setBounds(10, 279, 156, 113);
		cntScrollPane.getVerticalScrollBar().setBackground(new Color(22, 22, 22));
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
		scrollChisel(cntScrollPane, new Color(255, 255, 255), 5);
	cntScrollPane.setBackground(null);
		cntScrollPane.setForeground(null);
		panel.add(cntScrollPane);
		
		JList listaContato = new JList();
		listaContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContato.setSelectionBackground(clYellow);
		listaContato.setSelectionForeground(new Color(22,22,22));
		listaContato.setModel(new AbstractListModel() {
			// sempre que tem um item selecionado, alterar campos do painel endPanel
			String[] values = new String[] {""};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaContato.setBackground(new Color(22, 22, 22));
		listaContato.setForeground(new Color(197, 197, 197));
		listaContato.setFont(pop10);
		listaContato.setBounds(0, 0, 156, 113);
		cntScrollPane.setViewportView(listaContato);
		
		JButton btnAddContato = new JButton("");
		btnAddContato.setIcon(new ImageIcon("./img/add_forn.png"));
		btnAddContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// adicionar contato à lista
			}
		});
		btnAddContato.setBackground(null);
		btnAddContato.setBorder(BorderFactory.createEmptyBorder());
		btnAddContato.setBounds(150, 261, 16, 16);
		panel.add(btnAddContato);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
		
		JLabel lblDetEndereco = new JLabel("Endere�o");
		lblDetEndereco.setForeground(new Color(255, 255, 255));
		lblDetEndereco.setFont(pop12);
		lblDetEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetEndereco.setBounds(10, 11, 156, 14);
		endPanel.add(lblDetEndereco);
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setForeground(new Color(197, 197, 197));
		lblCidade.setFont(pop10);
		lblCidade.setBounds(10, 36, 156, 14);
		endPanel.add(lblCidade);

		JTextField txtCidade = new JTextField();
		txtCidade.setForeground(new Color(255, 255, 255));
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

		JTextField txtBairro = new JTextField();
		txtBairro.setForeground(new Color(255, 255, 255));
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

		JTextField txtRua = new JTextField();
		txtRua.setForeground(new Color(255, 255, 255));
		txtRua.setBackground(new Color(45, 45, 45));
		txtRua.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtRua.setBounds(10, 140, 156, 20);
		txtRua.setFont(pop12);
		endPanel.add(txtRua);
		txtRua.setColumns(10);

		JLabel lblNumero = new JLabel("N�MERO");
		lblNumero.setForeground(new Color(197, 197, 197));
		lblNumero.setFont(pop10);
		lblNumero.setBounds(10, 171, 156, 14);
		endPanel.add(lblNumero);

		JTextField txtNumero = new JTextField();
		txtNumero.setForeground(new Color(255, 255, 255));
		txtNumero.setBackground(new Color(45, 45, 45));
		txtNumero.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNumero.setBounds(10, 185, 156, 20);
		txtNumero.setFont(pop12);
		endPanel.add(txtNumero);
		
		JButton btnAlterarEndereco = new JButton("ALTERAR");
		btnAlterarEndereco.setOpaque(false);
		btnAlterarEndereco.setForeground(new Color(239, 161, 35));
		btnAlterarEndereco.setFont(pop12);
		Chisel(btnAlterarEndereco, clYellow, 5);
		btnAlterarEndereco.setFocusPainted(false);
		btnAlterarEndereco.setBackground((Color) null);
		btnAlterarEndereco.setBounds(10, 216, 156, 23);
		endPanel.add(btnAlterarEndereco);
		
		JLabel lblDetContato = new JLabel("Contato");
		lblDetContato.setForeground(new Color(255, 255, 255));
		lblDetContato.setFont(pop12);
		lblDetContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetContato.setBounds(10, 11, 156, 14);
		cntPanel.add(lblDetContato);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		cntPanel.add(lblEmail);

		JTextField txtEmail = new JTextField();
		txtEmail.setForeground(new Color(255, 255, 255));
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

		JTextField txtTelefone = new JTextField();
		txtTelefone.setForeground(new Color(255, 255, 255));
		txtTelefone.setBackground(new Color(45, 45, 45));
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTelefone.setBounds(10, 95, 156, 20);
		txtTelefone.setFont(pop12);
		cntPanel.add(txtTelefone);
		
		JButton btnAlterarContato = new JButton("ALTERAR");
		btnAlterarContato.setOpaque(false);
		btnAlterarContato.setForeground(new Color(239, 161, 35));
		btnAlterarContato.setFont(pop12);
		Chisel(btnAlterarContato, clYellow, 5);
		btnAlterarContato.setFocusPainted(false);
		btnAlterarContato.setBackground((Color) null);
		btnAlterarContato.setBounds(10, 171, 156, 23);
		cntPanel.add(btnAlterarContato);
		
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
			
			String cidade = txtCidade.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir uma cidade", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("cidade Vazio");
				return;
			}
			
			String Rua = txtRua.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir uma Rua.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("rua vazio");
				return;
			}
			
			String bairro = txtBairro.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um bairro.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("bairro vazio");
				return;
			}
			String numero = txtNumero.getText();
			int numero1;
			
			try {
				numero1 = Integer.parseInt(txtNumero.getText());
			} catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(null, "Digite apenas numeros.", "Aviso", JOptionPane.WARNING_MESSAGE);
				System.out.println("Não converteu para inteiro");
				return;
			}
			

			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um numero.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("numero vazio");
				return;
			}
			
			String email = txtEmail.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um Email.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("Email vazio");
				return;
			}
			
			String telefone = txtTelefone.getText();
			if (nome.isBlank()) {
				JOptionPane.showMessageDialog(null, "Favor inserir um Telefone.", "Erro", JOptionPane.ERROR_MESSAGE);
				System.out.println("Telefone vazio");
				return;
			}
			
			
			if(fornecedorBD.validarEmail(email) == true && fornecedorBD.isCNPJ(cnpj.toString()) == true) {
			fornecedor.setNome_fornecedor(nome);
			fornecedor.setCnpj_fornecedor(cnpj);
			fornecedor.setCidade(cidade);
			fornecedor.setBairro(bairro);
			fornecedor.setRua(Rua);
			fornecedor.setNumero(numero);
			fornecedor.setEmail(email);
			fornecedor.setTelefone(telefone);
			
			long id = fornecedorBD.insert(fornecedor);
			
			fornecedorBD.insert2(fornecedor,id);
			}else {
				System.out.println("Email Invalido ou CNPJ");
			}
			
			
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clYellow, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 432, 156, 23);
		panel.add(btnContinuar);
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
