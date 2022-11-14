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
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.FornecedorBD;
import controle.ProdutoBD;
import modelo.Fornecedor;
import modelo.Produto;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaCadastroProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProduto frame = new TelaCadastroProduto(null);
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
	public TelaCadastroProduto(JTable tblEstoque) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		new Color(226, 0, 54);
		new Color(113, 206, 236);
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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(243, 48, 176, 466);
		panelbuttonChisel(panel, Color.WHITE, 5);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Criar produto");
		lblEstamosQuaseL.setForeground(Color.WHITE);
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);

		JLabel lblMaterial = new JLabel("MATERIAL");
		lblMaterial.setForeground(new Color(197, 197, 197));
		lblMaterial.setFont(pop10);
		lblMaterial.setBounds(10, 216, 156, 14);
		panel.add(lblMaterial);
		
		JComboBox<String> cmbMaterial = new JComboBox<String>();
		cmbMaterial.setModel(new DefaultComboBoxModel<String>(new String[] {"A\u00E7o", "Inox", "Madeira", "Misto", "Outro", "Pl\u00E1stico", "Porcelana", "Vidro"}));
		cmbMaterial.setSelectedItem(null);
		cmbMaterial.setForeground(Color.WHITE);
		cmbMaterial.setFont(pop12);
		cmbMaterial.setUI(new Combo());
		cmbMaterial.setBorder(BorderFactory.createEmptyBorder());
		cmbMaterial.setBackground(new Color(45, 45, 45));
		cmbMaterial.setBounds(10, 231, 156, 22);
		panel.add(cmbMaterial);

		JTextField txtDimensoes = new JTextField();
		txtDimensoes.setCaretColor(Color.WHITE);
		txtDimensoes.setForeground(Color.WHITE);
		txtDimensoes.setBackground(new Color(45, 45, 45));
		txtDimensoes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDimensoes.setBounds(10, 275, 156, 20);
		txtDimensoes.setFont(pop12);
		panel.add(txtDimensoes);
		txtDimensoes.setColumns(10);

		JLabel lblPrecoCusto = new JLabel("PRE\u00C7O DE CUSTO");
		lblPrecoCusto.setForeground(new Color(197, 197, 197));
		lblPrecoCusto.setFont(pop10);
		lblPrecoCusto.setBounds(10, 126, 156, 14);
		panel.add(lblPrecoCusto);

		JTextField txtPrecoCusto = new JTextField();
		txtPrecoCusto.setCaretColor(Color.WHITE);
		txtPrecoCusto.setForeground(Color.WHITE);
		txtPrecoCusto.setBackground(new Color(45, 45, 45));
		txtPrecoCusto.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoCusto.setBounds(10, 140, 156, 20);
		txtPrecoCusto.setFont(pop12);
		panel.add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);

		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		lblQuantidade.setForeground(new Color(197, 197, 197));
		lblQuantidade.setFont(pop10);
		lblQuantidade.setBounds(10, 171, 156, 14);
		panel.add(lblQuantidade);

		JTextField txtQuantidade = new JTextField();
		txtQuantidade.setCaretColor(Color.WHITE);
		txtQuantidade.setForeground(Color.WHITE);
		txtQuantidade.setBackground(new Color(45, 45, 45));
		txtQuantidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtQuantidade.setBounds(10, 185, 156, 20);
		txtQuantidade.setFont(pop12);
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 36, 156, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(Color.WHITE);
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 50, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblPrecoVenda = new JLabel("PRE\u00C7O DE VENDA");
		lblPrecoVenda.setForeground(new Color(197, 197, 197));
		lblPrecoVenda.setFont(pop10);
		lblPrecoVenda.setBounds(10, 81, 156, 14);
		panel.add(lblPrecoVenda);

		JTextField txtPrecoVenda = new JTextField();
		txtPrecoVenda.setCaretColor(Color.WHITE);
		txtPrecoVenda.setForeground(Color.WHITE);
		txtPrecoVenda.setBackground(new Color(45, 45, 45));
		txtPrecoVenda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoVenda.setBounds(10, 95, 156, 20);
		txtPrecoVenda.setFont(pop12);
		panel.add(txtPrecoVenda);

		JLabel lblDimensoes = new JLabel("DIMENS\u00D5ES");
		lblDimensoes.setForeground(new Color(197, 197, 197));
		lblDimensoes.setFont(pop10);
		lblDimensoes.setBounds(10, 261, 156, 14);
		panel.add(lblDimensoes);

		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setForeground(new Color(197, 197, 197));
		lblFornecedor.setBounds(10, 306, 118, 14);
		lblFornecedor.setFont(pop10);
		panel.add(lblFornecedor);

		UIManager.put("ComboBox.selectionBackground", new javax.swing.plaf.ColorUIResource(clGreen));
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		MetalLookAndFeel.setCurrentTheme(new NewTheme());
		
		
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] {});
		for (Fornecedor f: fornecedores) {
			model.addElement(f.getNome_fornecedor());
		}
		
		
		JComboBox<String> cmbFornecedor = new JComboBox<String>(model);
		cmbFornecedor.setBounds(10, 321, 156, 22);
		cmbFornecedor.setUI(new Combo());
		cmbFornecedor.setSelectedItem(null);
		cmbFornecedor.setForeground(Color.WHITE);
		cmbFornecedor.setBackground(new Color(45, 45, 45));
		cmbFornecedor.setBorder(BorderFactory.createEmptyBorder());
		cmbFornecedor.setFont(pop12);
		panel.add(cmbFornecedor);
		
		JButton btnContinuar = new JButton("CADASTRAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro de produto > cadastrar produto");
				
				String nome = txtNome.getText();
				int f = 0;
				try {
					f = fornecedores.get(cmbFornecedor.getSelectedIndex()).getId_fornecedor();
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Fornecedor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("fornecedor vazio");
					return;
				}
				
				if (nome.isBlank()) {
					JOptionPane.showMessageDialog(null, "Nome inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Nome vazio");
					return;
				}
				float precoVenda = 0f;
				float precoCusto = 0f;
				try {
					precoVenda = Float.parseFloat(txtPrecoVenda.getText());
					precoCusto = Float.parseFloat(txtPrecoCusto.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Preço(s) inválido(s).", "Erro", JOptionPane.ERROR_MESSAGE, null);
					return;
				}
				
				int qtd = 0;
				
				try {
					qtd = Integer.parseInt(txtQuantidade.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Quantidade em Estoque definida como 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
					System.out.println("qtd 0");
					return;
				}
				if (qtd == 0) {
					JOptionPane.showMessageDialog(null, "Quantidade em Estoque definida como 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
					System.out.println("qtd 0");
					return;
				}
				
				String material = (String) cmbMaterial.getSelectedItem();
				if (material.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir um material.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("material vazio");
					return;
				}
				
				String dimensoes = txtDimensoes.getText();
				if (dimensoes.isBlank()) {
					JOptionPane.showMessageDialog(null, "Favor inserir dimensões.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("dimensões vazias");
					return;
				}
				
//				String fornecedor = txtFornecedor.getText();
//				int idFornecedor = 0;
//				if (fornecedor.isBlank()==false) {
//					idFornecedor = Integer.parseInt(fornecedor);
//				}
				new FornecedorBD();
				
				if(FornecedorBD.VRFornR(f)==false && f !=0) {
					JOptionPane.showMessageDialog(null, "Favor inserir um Fornecedor Existende.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Fornecedor não existe");
					return;
				}
				
				System.out.println("a");
				
				ProdutoBD produtoBD = new ProdutoBD();
				Produto produto = new Produto();
				produto.setNomeProduto(nome);
				produto.setPrecoCustoProduto(precoCusto);
				produto.setPrecoVendaProduto(precoVenda);
				produto.setDimensoesProduto(dimensoes);
				produto.setMaterialProduto(material);
				produto.setQuantidadeEstoque(qtd);
				produto.setFornecedor(f);

				

				long id = produtoBD.insert(produto);
				produtoBD.insert2(produto, id);
				refresh(tblEstoque);
				dispose();
				
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clGreen, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 432, 156, 23);
		panel.add(btnContinuar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
	}
	
	private void refresh(JTable tbl) {
		int sel = tbl.getSelectedRow();
		
		DefaultTableModel model = new DefaultTableModel(null, new String[] { "ID", "NOME", "PRE\u00C7O", "MATERIAL", "DIMENS\u00D5ES", "FORNECEDOR", "QUANTIDADE"});

		ProdutoBD produtoBD = new ProdutoBD();
		FornecedorBD fbd = new FornecedorBD();
		ArrayList<Produto> produtos = produtoBD.getListarProdutos();
		
		for (Produto produto : produtos) {
				if(produto.getFornecedor()==0) {
					model.addRow(new Object[] {
							"# " + produto.getIdProduto(),
							produto.getNomeProduto(),
							"R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','),
							produto.getMaterialProduto(),
							produto.getDimensoesProduto(),
							"Sem fornecedor",
							produto.getQuantidadeEstoque()});
				}else {
					model.addRow(new Object[] {
							"# " + produto.getIdProduto(),
							produto.getNomeProduto(),
							"R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','),
							produto.getMaterialProduto(),
							produto.getDimensoesProduto(),
							fbd.getFornecedor(produto.getFornecedor()).getNome_fornecedor(),
							produto.getQuantidadeEstoque()});
				}
		}
		
		tbl.setModel(model);
		tbl.setRowSelectionInterval(sel, sel);
		
		tbl.getColumnModel().getColumn(0).setPreferredWidth(25);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(140);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(240);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(270);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(100);
	}
	
	public class NewTheme extends OceanTheme{
		 public  ColorUIResource getControlShadow(){
		    return new ColorUIResource(45, 45, 45);
		 }
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
