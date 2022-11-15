package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controle.FornecedorBD;
import controle.ProdutoBD;
import modelo.Fornecedor;
import modelo.Produto;

public class TelaModificarProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundField txtNome;
	private static Produto produtoAEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaModificarProduto frame = new TelaModificarProduto(null, produtoAEditar);
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
	public TelaModificarProduto(JTable tblEstoque, Produto produtoAEditar) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clGreen = new Color(168, 198, 51);
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
		panel.setBounds(243, 48, 176, 466);
		panel.setBorder(new RoundBorder());
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Alterar produto");
		lblEstamosQuaseL.setForeground(Color.WHITE);
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);

		JLabel lblMaterial = new JLabel("MATERIAL");
		lblMaterial.setForeground(clLighter);
		lblMaterial.setFont(pop10);
		lblMaterial.setBounds(10, 216, 156, 14);
		panel.add(lblMaterial);
		
		String[] materiais = new String[] {
				"A\u00E7o",
				"Inox",
				"Madeira",
				"Misto", 
				"Outro",
				"Pl\u00E1stico",
				"Porcelana",
				"Vidro"};
		
		RoundBorder lightBorder = new RoundBorder(clLight, 1, 10);
		RoundBorder greenBorder = new RoundBorder(clGreen, 1, 10);
		
		
		JPanel matPanel = new JPanel();
		matPanel.setBackground(clLight);
		matPanel.setBorder(lightBorder);
		matPanel.setBounds(10, 230, 156, 22);
		panel.add(matPanel);
		matPanel.setLayout(null);
		
		JComboBox<String> cmbMaterial = new JComboBox<String>();
		cmbMaterial.setBounds(3, 2, 149, 18);
		matPanel.add(cmbMaterial);
		cmbMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbMaterial.getSelectedItem() != null) {
					matPanel.setBackground(clGreen);
					matPanel.setBorder(greenBorder);
					
					cmbMaterial.setBackground(clGreen);
					cmbMaterial.setForeground(clDark);
				}
			}
		});
		cmbMaterial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				matPanel.setBackground(clLight);
				matPanel.setBorder(lightBorder);
				
				cmbMaterial.setBackground(clLight);
				cmbMaterial.setForeground(Color.WHITE);
			}
		});
		cmbMaterial.setModel(new DefaultComboBoxModel<String>(materiais));
		cmbMaterial.setSelectedItem(null);
		cmbMaterial.setForeground(Color.WHITE);
		cmbMaterial.setFont(pop12);
		cmbMaterial.setUI(new Combo());
		cmbMaterial.setOpaque(false);
		cmbMaterial.setBackground(clLight);
		cmbMaterial.setBorder(BorderFactory.createEmptyBorder());
		
		int selm = 0;
		boolean search = true;
		
		for (String m: materiais) {
			if (m.equals(produtoAEditar.getMaterialProduto())) {
				search = false;
			}
			if (search) {
				selm++;
			}
		}
		try {
			cmbMaterial.setSelectedIndex(selm);
		} catch (IllegalArgumentException e) {
			cmbMaterial.setSelectedItem(null);
		}

		RoundField txtDimensoes = new RoundField();
		txtDimensoes.setCaretColor(Color.WHITE);
		txtDimensoes.setForeground(Color.WHITE);
		txtDimensoes.setSelectedTextColor(clDark);
		txtDimensoes.setSelectionColor(clGreen);
		txtDimensoes.setBackground(clLight);
		txtDimensoes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDimensoes.setBounds(10, 275, 156, 20);
		txtDimensoes.setFont(pop12);
		txtDimensoes.setText(produtoAEditar.getDimensoesProduto());
		panel.add(txtDimensoes);
		txtDimensoes.setColumns(10);

		JLabel lblPrecoCusto = new JLabel("PRE\u00C7O DE CUSTO");
		lblPrecoCusto.setForeground(clLighter);
		lblPrecoCusto.setFont(pop10);
		lblPrecoCusto.setBounds(10, 126, 156, 14);
		panel.add(lblPrecoCusto);

		RoundField txtPrecoCusto = new RoundField();
		txtPrecoCusto.setCaretColor(Color.WHITE);
		txtPrecoCusto.setForeground(Color.WHITE);
		txtPrecoCusto.setSelectedTextColor(clDark);
		txtPrecoCusto.setSelectionColor(clGreen);
		txtPrecoCusto.setBackground(clLight);
		txtPrecoCusto.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoCusto.setBounds(10, 140, 156, 20);
		txtPrecoCusto.setFont(pop12);
		txtPrecoCusto.setText(String.valueOf(produtoAEditar.getPrecoCustoProduto()));
		panel.add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);

		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		lblQuantidade.setForeground(clLighter);
		lblQuantidade.setFont(pop10);
		lblQuantidade.setBounds(10, 171, 156, 14);
		panel.add(lblQuantidade);

		RoundField txtQuantidade = new RoundField();
		txtQuantidade.setCaretColor(Color.WHITE);
		txtQuantidade.setForeground(Color.WHITE);
		txtQuantidade.setSelectedTextColor(clDark);
		txtQuantidade.setSelectionColor(clGreen);
		txtQuantidade.setBackground(clLight);
		txtQuantidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtQuantidade.setBounds(10, 185, 156, 20);
		txtQuantidade.setFont(pop12);
		txtQuantidade.setText(String.valueOf(produtoAEditar.getQuantidadeEstoque()));
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblNome = new JLabel("NOME");
		lblNome.setForeground(clLighter);
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 36, 156, 14);
		panel.add(lblNome);

		txtNome = new RoundField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(Color.WHITE);
		txtNome.setSelectedTextColor(clDark);
		txtNome.setSelectionColor(clGreen);
		txtNome.setBackground(clLight);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 50, 156, 20);
		txtNome.setFont(pop12);
		txtNome.setText(produtoAEditar.getNomeProduto());
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblPrecoVenda = new JLabel("PRE\u00C7O DE VENDA");
		lblPrecoVenda.setForeground(clLighter);
		lblPrecoVenda.setFont(pop10);
		lblPrecoVenda.setBounds(10, 81, 156, 14);
		panel.add(lblPrecoVenda);

		RoundField txtPrecoVenda = new RoundField();
		txtPrecoVenda.setCaretColor(Color.WHITE);
		txtPrecoVenda.setForeground(Color.WHITE);
		txtPrecoVenda.setSelectedTextColor(clDark);
		txtPrecoVenda.setSelectionColor(clGreen);
		txtPrecoVenda.setBackground(clLight);
		txtPrecoVenda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoVenda.setBounds(10, 95, 156, 20);
		txtPrecoVenda.setText(String.valueOf(produtoAEditar.getPrecoVendaProduto()));
		txtPrecoVenda.setFont(pop12);
		
		panel.add(txtPrecoVenda);

		JLabel lblDimensoes = new JLabel("DIMENS\u00D5ES");
		lblDimensoes.setForeground(clLighter);
		lblDimensoes.setFont(pop10);
		lblDimensoes.setBounds(10, 261, 156, 14);
		panel.add(lblDimensoes);

		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setForeground(clLighter);
		lblFornecedor.setBounds(10, 306, 122, 14);
		lblFornecedor.setFont(pop10);
		panel.add(lblFornecedor);
		
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] {});
		for (Fornecedor f: fornecedores) {
			model.addElement(f.getNome_fornecedor());
		}
		
		JPanel forPanel = new JPanel();
		forPanel.setBackground(clLight);
		forPanel.setBorder(lightBorder);
		forPanel.setBounds(10, 321, 156, 22);
		panel.add(forPanel);
		forPanel.setLayout(null);
		
		JComboBox<String> cmbFornecedor = new JComboBox<String>(model);
		cmbFornecedor.setBounds(3, 2, 149, 18);
		
		Combo cmbUI = new Combo();
		cmbUI.setColor(clGreen);
		
		cmbFornecedor.setUI(cmbUI);
		
		cmbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbFornecedor.getSelectedItem() != null) {
					forPanel.setBackground(clGreen);
					forPanel.setBorder(greenBorder);
					
					cmbFornecedor.setBackground(clGreen);
					cmbFornecedor.setForeground(clDark);
				}
			}
		});
		cmbFornecedor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				forPanel.setBackground(clLight);
				forPanel.setBorder(lightBorder);
				
				cmbFornecedor.setBackground(clLight);
				cmbFornecedor.setForeground(Color.WHITE);
			}
		});
		
		cmbFornecedor.setSelectedItem(null);
		cmbFornecedor.setForeground(Color.WHITE);
		cmbFornecedor.setBackground(null);
		cmbFornecedor.setBorder(BorderFactory.createEmptyBorder());
		cmbFornecedor.setFont(pop12);
		forPanel.add(cmbFornecedor);
		int sel = 0;
		search = true;
		int f_id = produtoAEditar.getFornecedor();
		
		for (Fornecedor f: fornecedores) {
			model.addElement(f.getNome_fornecedor());
			if (f.getId_fornecedor() == f_id) {
				search = false;
			}
			if (search) {
				sel++;
			}
		}
		
		try {
			cmbFornecedor.setSelectedIndex(sel);
		} catch (IllegalArgumentException e) {
			cmbFornecedor.setSelectedItem(null);
		}

		RoundButton btnContinuar = new RoundButton("ALTERAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de alteração de produto > alterar produto");
				
				String nome = txtNome.getText();
				float precoVenda = 0f;
				float precoCusto = 0f;
				
				int f = 0;
				try {
					try {
						f = fornecedores.get(cmbFornecedor.getSelectedIndex()).getId_fornecedor();
					} catch (IndexOutOfBoundsException x) {
						JOptionPane.showMessageDialog(null, "Fornecedor inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Fornecedor inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("fornecedor vazio");
					return;
				}
				
				try {
					precoVenda = Float.parseFloat(txtPrecoVenda.getText());
					precoCusto = Float.parseFloat(txtPrecoCusto.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Preço(s) inv\u00E1lido(s)", "Erro", JOptionPane.ERROR_MESSAGE, null);
				}
				int qtd = Integer.parseInt(txtQuantidade.getText());
				String material = (String) cmbMaterial.getSelectedItem();
				String dimensoes = txtDimensoes.getText();
				
				new FornecedorBD();
				
				if(FornecedorBD.VRFornR(f)==false && f !=0) {
					JOptionPane.showMessageDialog(null, "Favor inserir um Fornecedor existente.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Fornecedor não existe");
					return;
				}
				

				Produto produto = new Produto();
					produto.setNomeProduto(nome);
					produto.setPrecoCustoProduto(precoCusto);
					produto.setPrecoVendaProduto(precoVenda);
					produto.setDimensoesProduto(dimensoes);
					produto.setMaterialProduto(material);
					produto.setQuantidadeEstoque(qtd);
					produto.setFornecedor(f);
					produto.setIdProduto(produtoAEditar.getIdProduto());

				
				ProdutoBD produtoBD = new ProdutoBD();
				produtoBD.update(produto);
				System.out.println("update realizado");
				refresh(tblEstoque);
				dispose();
				
			}
		});
		btnContinuar.setForeground(clGreen);
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		btnContinuar.setBorder(new RoundBorder(clGreen));
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 432, 123, 23);
		panel.add(btnContinuar);
		
		
		RoundButton btnDeletar = new RoundButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoBD produtoBD = new ProdutoBD();
				produtoBD.DeleteByID(produtoAEditar.getIdProduto());
				refresh(tblEstoque);
				dispose();
			}
		});
		btnDeletar.setIcon(new ImageIcon("./img/delete.png"));
		btnDeletar.setOpaque(false);
		btnDeletar.setForeground(clRed);
		btnDeletar.setFont(null);
		btnDeletar.setFocusPainted(false);
		btnDeletar.setBackground((Color) null);
		btnDeletar.setBorder(new RoundBorder(clRed, 1, 24));
		btnDeletar.setBounds(142, 431, 24, 24);
		panel.add(btnDeletar);
		
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
		if (sel != -1) {
			tbl.setRowSelectionInterval(sel, sel);
		}
		
		tbl.getColumnModel().getColumn(0).setPreferredWidth(25);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(140);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(240);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(160);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(270);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(100);
	}
}
