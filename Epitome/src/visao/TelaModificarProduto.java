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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.FornecedorBD;
import controle.ProdutoBD;
import modelo.Fornecedor;
import modelo.Produto;

public class TelaModificarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private final ButtonGroup cargoGroup = new ButtonGroup();
	private static Produto produtoAEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaModificarProduto frame = new TelaModificarProduto(produtoAEditar);
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
	public TelaModificarProduto(Produto produtoAEditar) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
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
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Alterar produto");
		lblEstamosQuaseL.setForeground(new Color(255, 255, 255));
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);

		JLabel lblCPF = new JLabel("MATERIAL");
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop10);
		lblCPF.setBounds(10, 216, 156, 14);
		panel.add(lblCPF);
		
		String[] materiais = new String[] {"A\u00E7o", "Inox", "Madeira", "Misto", "Outro", "Pl\u00E1stico", "Porcelana", "Vidro"};
		
		JComboBox<String> cmbMaterial = new JComboBox<String>();
		cmbMaterial.setModel(new DefaultComboBoxModel<String>(materiais));
		
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
		cmbMaterial.setForeground(Color.WHITE);
		cmbMaterial.setFont(pop12);
		cmbMaterial.setUI(new Combo());
		cmbMaterial.setBorder(BorderFactory.createEmptyBorder());
		cmbMaterial.setBackground(new Color(45, 45, 45));
		cmbMaterial.setBounds(10, 231, 156, 22);
		panel.add(cmbMaterial);

		JTextField txtDimensoes = new JTextField();
		txtDimensoes.setCaretColor(Color.WHITE);
		txtDimensoes.setForeground(new Color(255, 255, 255));
		txtDimensoes.setBackground(new Color(45, 45, 45));
		txtDimensoes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDimensoes.setBounds(10, 275, 156, 20);
		txtDimensoes.setFont(pop12);
		txtDimensoes.setText(produtoAEditar.getDimencoesProduto());
		panel.add(txtDimensoes);
		txtDimensoes.setColumns(10);

		JLabel lblUsername = new JLabel("PRE\u00C7O DE CUSTO");
		lblUsername.setForeground(new Color(197, 197, 197));
		lblUsername.setFont(pop10);
		lblUsername.setBounds(10, 126, 156, 14);
		panel.add(lblUsername);

		JTextField txtPrecoCusto = new JTextField();
		txtPrecoCusto.setCaretColor(Color.WHITE);
		txtPrecoCusto.setForeground(new Color(255, 255, 255));
		txtPrecoCusto.setBackground(new Color(45, 45, 45));
		txtPrecoCusto.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoCusto.setBounds(10, 140, 156, 20);
		txtPrecoCusto.setFont(pop12);
		txtPrecoCusto.setText(String.valueOf(produtoAEditar.getPrecoCustoProduto()));
		panel.add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);

		JLabel lblNome = new JLabel("QUANTIDADE");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 171, 156, 14);
		panel.add(lblNome);

		JTextField txtQuantidade = new JTextField();
		txtQuantidade.setCaretColor(Color.WHITE);
		txtQuantidade.setForeground(new Color(255, 255, 255));
		txtQuantidade.setBackground(new Color(45, 45, 45));
		txtQuantidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtQuantidade.setBounds(10, 185, 156, 20);
		txtQuantidade.setFont(pop12);
		txtQuantidade.setText(String.valueOf(produtoAEditar.getQuantidadeEstoque()));
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblEmail = new JLabel("NOME");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);

		txtNome = new JTextField();
		txtNome.setCaretColor(Color.WHITE);
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 50, 156, 20);
		txtNome.setFont(pop12);
		txtNome.setText(produtoAEditar.getNomeProduto());
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblSenha = new JLabel("PRE\u00C7O DE VENDA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);

		JTextField txtPrecoVenda = new JTextField();
		txtPrecoVenda.setCaretColor(Color.WHITE);
		txtPrecoVenda.setForeground(new Color(255, 255, 255));
		txtPrecoVenda.setBackground(new Color(45, 45, 45));
		txtPrecoVenda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoVenda.setBounds(10, 95, 156, 20);
		txtPrecoVenda.setText(String.valueOf(produtoAEditar.getPrecoVendaProduto()));
		txtPrecoVenda.setFont(pop12);
		
		panel.add(txtPrecoVenda);

		JLabel lblDataDeNascimento = new JLabel("DIMENS\u00D5ES");
		lblDataDeNascimento.setForeground(new Color(197, 197, 197));
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 261, 156, 14);
		panel.add(lblDataDeNascimento);

		JLabel lblFornecedor = new JLabel("FORNECEDOR");
		lblFornecedor.setForeground(new Color(197, 197, 197));
		lblFornecedor.setBounds(10, 306, 122, 14);
		lblFornecedor.setFont(pop10);
		panel.add(lblFornecedor);
		
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] {});
		
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
		
		JComboBox<String> cmbFornecedor = new JComboBox<String>(model);
		try {
			cmbFornecedor.setSelectedIndex(sel);
		} catch (IllegalArgumentException e) {
			cmbFornecedor.setSelectedItem(null);
		}
		cmbFornecedor.setBounds(10, 321, 156, 22);
		cmbFornecedor.setUI(new Combo());
		cmbFornecedor.setForeground(Color.WHITE);
		cmbFornecedor.setBackground(new Color(45, 45, 45));
		cmbFornecedor.setBorder(BorderFactory.createEmptyBorder());
		cmbFornecedor.setFont(pop12);
		panel.add(cmbFornecedor);

		JButton btnContinuar = new JButton("ALTERAR");
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
				
//				String fornecedor = txtFornecedor.getText();
//				int idFornecedor = 0;
//				if (fornecedor.isBlank()==false) {
//					idFornecedor = Integer.parseInt(fornecedor);
//				}
				FornecedorBD fornedorBD = new FornecedorBD();
				
				if(FornecedorBD.VRFornR(f)==false && f !=0) {
					JOptionPane.showMessageDialog(null, "Favor inserir um Fornecedor Existente.", "Erro", JOptionPane.ERROR_MESSAGE);
					System.out.println("Fornecedor não existe");
					return;
				}
				

				Produto produto = new Produto();
					produto.setNomeProduto(nome);
					produto.setPrecoCustoProduto(precoCusto);
					produto.setPrecoVendaProduto(precoVenda);
					produto.setDimencoesProduto(dimensoes);
					produto.setMaterialProduto(material);
					produto.setQuantidadeEstoque(qtd);
					produto.setFornecedor(f);
					produto.setIdProduto(produtoAEditar.getIdProduto());

				
				ProdutoBD produtoBD = new ProdutoBD();
				produtoBD.update(produto);
				System.out.println("update realizado");
				dispose();
				
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clGreen, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 432, 123, 23);
		panel.add(btnContinuar);
		
		
		JButton btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoBD produtoBD = new ProdutoBD();
				produtoBD.DeleteByID(produtoAEditar.getIdProduto());
				dispose();
			}
		});
		btnDeletar.setIcon(new ImageIcon("./img/delete.png"));
		btnDeletar.setOpaque(false);
		btnDeletar.setForeground(clRed);
		btnDeletar.setFont(null);
		btnDeletar.setFocusPainted(false);
		btnDeletar.setBackground((Color) null);
		Chisel(btnDeletar, clRed, 5);
		btnDeletar.setBounds(143, 432, 23, 23);
		panel.add(btnDeletar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
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
