package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.ProdutoBD;
import controle.VendaBD;
import modelo.ProdVNDQuant;
import modelo.Produto;
import modelo.Usuario;
import modelo.Venda;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;

public class TelaVenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tblProdutos;
	private JTextField txtCodigo;
	private ArrayList<Produto> all_produtos = new ProdutoBD().getListarProdutos();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	float precoT = 0;
	float troco = (float) 0.00;
	private final ButtonGroup pagGroup = new ButtonGroup();
	Venda venda = new Venda();
	ArrayList<ProdVNDQuant> QuantVND = new ArrayList<ProdVNDQuant>();
	ProdVNDQuant vendaInicial = new ProdVNDQuant();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVenda frame = new TelaVenda(null);
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
	public TelaVenda(Usuario usuarioLogado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clGreen = new Color(168, 198, 51);
		Color clYellow = new Color(239, 161, 35);
		
		
		Font poppins, pop10 = null, pop12 = null, pop16 = null, pop24 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
			pop16 = poppins.deriveFont(Font.TRUETYPE_FONT, 16);
			pop24 = poppins.deriveFont(Font.TRUETYPE_FONT, 24);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		UIManager.put("TableHeader.cellBorder", BorderFactory.createCompoundBorder(new LineBorder(new Color(22,22,22), 2), new MatteBorder(0, 0, 1, 0, clGreen)));
		UIManager.put("TableHeader.background", new javax.swing.plaf.ColorUIResource(new Color(22,22,22)));
		
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
		panel.setBounds(1322, 11, 252, 124);
		panelChisel(panel, Color.WHITE, 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio de Vendas");
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > relat\u00F3rio de vendas");
				TelaRelatorio telaRel = new TelaRelatorio(usuarioLogado);
				telaRel.setVisible(true);
				setVisible(false);
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnRelatorio.setForeground(clBlue);
		btnRelatorio.setBounds(10, 59, 232, 23);
		btnRelatorio.setFocusPainted(false);
		panel.add(btnRelatorio);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(pop10);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > sair");
			}
		});
		btnSair.setBackground(null);
		btnSair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnSair.setForeground(clGreen);
		btnSair.setBounds(10, 82, 232, 23);
		btnSair.setFocusPainted(false);
		panel.add(btnSair);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setFont(pop10);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > perfil");
				TelaPerfilVND telaPerfil = new TelaPerfilVND(usuarioLogado);
				telaPerfil.setVisible(true);
			}
		});
		btnPerfil.setBackground(null);
		btnPerfil.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnPerfil.setForeground(clRed);
		btnPerfil.setBounds(10, 36, 232, 23);
		btnPerfil.setFocusPainted(false);
		panel.add(btnPerfil);
		
		JLabel lblNome = new JLabel(usuarioLogado.getNome_usuario());
		//lblNome.setText(nome do usuario)
		lblNome.setBounds(10, 11, 232, 14);
		panel.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(pop12); 
		
		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usuarioLogado.getCargo().equals("administrador")) {
					TelaInicialADM telainicial = new TelaInicialADM(usuarioLogado);
					telainicial.setVisible(true);
				} else {
					TelaInicialVND telainicial = new TelaInicialVND(usuarioLogado);
					telainicial.setVisible(true);
				}
				
				setVisible(false);
			}
		});
		
		JLabel lblEmail = new JLabel("Codigo");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);
		
		btnReturn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnReturn.setIcon(new ImageIcon("./img/return.png"));
		btnReturn.setForeground(null);
		btnReturn.setBackground(null);
		btnReturn.setBounds(23, 42, 27, 45);
		contentPane.add(btnReturn);
	
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: pesquisar");
			}
		});
		
		btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnSearch.setIcon(new ImageIcon("./img/search.png"));
		btnSearch.setForeground(null);
		btnSearch.setBackground(null);
		btnSearch.setBounds(47, 126, 25, 25);
		contentPane.add(btnSearch);
		
		JLabel lblVenda = new JLabel("Venda");
		lblVenda.setHorizontalAlignment(SwingConstants.LEFT);
		lblVenda.setForeground(Color.WHITE);
		lblVenda.setFont(pop24);
		lblVenda.setBounds(60, 42, 252, 45);
		contentPane.add(lblVenda);
		
		JTextField txtSearch = new JTextField();
		txtSearch.setCaretColor(Color.WHITE);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setBackground(new Color(22, 22, 22));
		txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSearch.setBounds(80, 126, 431, 25);
		txtSearch.setFont(pop12);
		fieldChisel(txtSearch, Color.WHITE, 5);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(47, 162, 715, 502);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(new Color(22, 22, 22));
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollChisel(scrollPane, Color.WHITE, 5);
		Rolagem.defRolagem(scrollPane);
		contentPane.add(scrollPane);		
		
		JPanel dtlProduto = new JPanel();
		dtlProduto.setBounds(772, 162, 545, 114);
		dtlProduto.setBackground(new Color(22, 22, 22));
		panelChisel(dtlProduto, Color.WHITE, 5);
		contentPane.add(dtlProduto);
		dtlProduto.setLayout(null);
		
		// atualizar detalhes do produto quando for selecionado na tabela
		
		JLabel lblID = new JLabel("");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(10, 11, 122, 14);
		lblID.setFont(pop12);
		lblID.setForeground(Color.WHITE);
		dtlProduto.add(lblID);
		
		JLabel lblNomeProduto = new JLabel("");
		lblNomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeProduto.setForeground(Color.WHITE);
		lblNomeProduto.setFont(pop12);
		lblNomeProduto.setBounds(142, 11, 263, 14);
		dtlProduto.add(lblNomeProduto);
		
		JLabel lblPreco = new JLabel("");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setForeground(Color.WHITE);
		lblPreco.setFont(pop12);
		lblPreco.setBounds(415, 11, 120, 14);
		dtlProduto.add(lblPreco);
		
		JLabel lblQuantidade = new JLabel("ESTOQUE:");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(pop10);
		lblQuantidade.setBounds(10, 36, 190, 14);
		dtlProduto.add(lblQuantidade);
		
		JLabel lblMaterial = new JLabel("MATERIAL:");
		lblMaterial.setForeground(Color.WHITE);
		lblMaterial.setFont(pop10);
		lblMaterial.setBounds(10, 61, 190, 14);
		dtlProduto.add(lblMaterial);
		
		JLabel lblDimensoes = new JLabel("DIMENS\u00D5ES:");
		lblDimensoes.setForeground(Color.WHITE);
		lblDimensoes.setFont(pop10);
		lblDimensoes.setBounds(10, 86, 190, 14);
		dtlProduto.add(lblDimensoes);
		
		tblProdutos = new JTable();
		tblProdutos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
					String nm = (String) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1);
					Produto produto = null;
					for (Produto p: all_produtos) {
						if (p.getNomeProduto().equals(nm)) {
							produto = p;
						}
					}

					lblQuantidade.setText("ESTOQUE: "+produto.getQuantidadeEstoque());
					lblDimensoes.setText("DIMENS\u00D5ES: "+produto.getDimensoesProduto());
					lblID.setText("# "+produto.getIdProduto());
					lblPreco.setText("R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','));
					lblMaterial.setText("MATERIAL: "+produto.getMaterialProduto());
					lblNomeProduto.setText(produto.getNomeProduto());
				} catch (Exception x) {
					return;
				}
	        }
	    });
		
		tblProdutos.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "NOME", "PRE\u00C7O"
			}
		));
		
		
		
		JTableHeader Theader = tblProdutos.getTableHeader();
		
		tblProdutos.setFocusable(false);
		tblProdutos.setSelectionBackground(clGreen);
		tblProdutos.setSelectionForeground(new Color(22, 22, 22));
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdutos.setShowHorizontalLines(false);
		tblProdutos.setShowVerticalLines(false);
		tblProdutos.setShowGrid(false);
		tblProdutos.setIntercellSpacing(new Dimension(0, 0));
		
		tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblProdutos.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		Theader.setFont(pop12);
		Theader.setForeground(clGreen);
		Theader.setBackground(new Color(22, 22, 22));
		Theader.setReorderingAllowed(false);
		Theader.setResizingAllowed(false);
		Theader.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		
		
		tblProdutos.setFont(pop12);
		tblProdutos.setForeground(Color.WHITE);
		tblProdutos.setBackground(new Color(22, 22, 22));
		tblProdutos.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(tblProdutos);
		
		JPanel pagamentoPanel = new JPanel();
		pagamentoPanel.setLayout(null);
		pagamentoPanel.setBackground(new Color(22, 22, 22));
		panelChisel(pagamentoPanel, Color.WHITE, 5);
		pagamentoPanel.setBounds(772, 287, 545, 114);
		contentPane.add(pagamentoPanel);
		
		JLabel lblNomeProduto_1 = new JLabel("PAGAMENTO");
		lblNomeProduto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeProduto_1.setForeground(Color.WHITE);
		lblNomeProduto_1.setFont(pop12);
		lblNomeProduto_1.setBounds(10, 11, 161, 14);
		pagamentoPanel.add(lblNomeProduto_1);
		
		JLabel lblPagTotal = new JLabel("R$0,00");
		lblPagTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPagTotal.setForeground(Color.WHITE);
		lblPagTotal.setFont(pop12);
		lblPagTotal.setBounds(415, 11, 120, 14);
		pagamentoPanel.add(lblPagTotal);
		
		JLabel lblPagamentoValor = new JLabel("Pagamento:");
		lblPagamentoValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPagamentoValor.setForeground(Color.WHITE);
		lblPagamentoValor.setFont(pop10);
		lblPagamentoValor.setBounds(176, 40, 88, 14);
		pagamentoPanel.add(lblPagamentoValor);
		
		JLabel lblTotal = new JLabel("TOTAL: R$0,00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(pop16);
		lblTotal.setBounds(1065, 449, 252, 14);
		contentPane.add(lblTotal);
		
		JLabel lblTroco = new JLabel("Troco:  R$"+troco);
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroco.setForeground(Color.WHITE);
		lblTroco.setFont(pop10);
		lblTroco.setBounds(391, 40, 144, 14);
		pagamentoPanel.add(lblTroco);
		
		JButton btnRemover = new JButton("");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nm;
				try {
					nm = (String) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1);
				} catch (ArrayIndexOutOfBoundsException x) {
					return;
				}
				for (Produto p: produtos) {
					int sel = tblProdutos.getSelectedRow();
					if (p.getNomeProduto().equals(nm)) {
						produtos.remove(p);
						DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
						
						model.removeRow(sel);
						if (sel !=-1 && model.getRowCount() > 0) {
							try {
								tblProdutos.setRowSelectionInterval(sel, sel);
							} catch (IllegalArgumentException x) {
								tblProdutos.setRowSelectionInterval(model.getRowCount()-1,model.getRowCount()-1);
							}
							
						} else {
							lblQuantidade.setText("ESTOQUE: ");
							lblDimensoes.setText("DIMENS\u00D5ES: ");
							lblID.setText("");
							lblPreco.setText("");
							lblMaterial.setText("MATERIAL: ");
							lblNomeProduto.setText("");
						}
						tblProdutos.setModel(model);
						
						precoT -= p.getPrecoVendaProduto();
						
						
						//seta o total ali embaixo
						lblTotal.setText(String.valueOf("Total: "+precoT));
						
						//seta o troco ali no pagamento 
						lblPagTotal.setText(String.valueOf("Total: "+ precoT));
						
						return;
					}
				}
			}
		});
		btnRemover.setIcon(new ImageIcon("./img/delete.png"));
		btnRemover.setBounds(512, 82, 23, 23);
		dtlProduto.add(btnRemover);
		btnRemover.setOpaque(false);
		btnRemover.setBackground(null);
		btnRemover.setFont(pop12);
		buttonChisel(btnRemover, clRed, 5);
		dtlProduto.add(btnRemover);
		
		JTextField txtPagamentoValor = new JTextField();
		txtPagamentoValor.setCaretColor(Color.WHITE);
//		txtPagamentoValor.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//				try {
//					System.out.println(txtPagamentoValor.getText());
//					troco = Float.parseFloat(txtPagamentoValor.getText()) - precoT;
//					lblTroco.setText("Troco:  R$" + troco);
//				} catch (Exception x) {
//					troco = 0.0f;
//					lblTroco.setText("Troco:  R$" + troco);
//				}
//			}
//		});
		txtPagamentoValor.setForeground(Color.WHITE);
		txtPagamentoValor.setBackground(new Color(45, 45, 45));
		txtPagamentoValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPagamentoValor.setBounds(274, 37, 107, 20);
		txtPagamentoValor.setFont(pop12);
		pagamentoPanel.add(txtPagamentoValor);
		txtPagamentoValor.setColumns(10);
		
		JRadioButton rdDinheiro = new JRadioButton("DINHEIRO");
		pagGroup.add(rdDinheiro);
		rdDinheiro.setFocusPainted(false);
		rdDinheiro.setForeground(Color.WHITE);
		rdDinheiro.setBackground(null);
		rdDinheiro.setFont(pop10);
		rdDinheiro.setIcon(new ImageIcon("./img/radio_button.png"));
		rdDinheiro.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdDinheiro.setBounds(20, 32, 109, 23);
		pagamentoPanel.add(rdDinheiro);
		
		JRadioButton rdCartao = new JRadioButton("CART\u00C3O");
		pagGroup.add(rdCartao);
		rdCartao.setFocusPainted(false);
		rdCartao.setForeground(Color.WHITE);
		rdCartao.setFont(pop10);
		rdCartao.setBackground((Color) null);
		rdCartao.setIcon(new ImageIcon("./img/radio_button.png"));
		rdCartao.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdCartao.setBounds(20, 58, 109, 23);
		pagamentoPanel.add(rdCartao);
		
		JButton btNEncerrar = new JButton("ENCERRAR");
		btNEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: encerrar compra");
				
				if (rdDinheiro.isSelected()==true) {

					float a =    Float.parseFloat(txtPagamentoValor.getText()) - precoT;
					lblTroco.setText("Troco:  R$"+a);

					
				}
				Date now = new Date(System.currentTimeMillis());
				venda.setDataVenda(now);
				venda.setFk(usuarioLogado.getId_usuario());
				if(rdCartao.isSelected()==true) {
				venda.setPagamento("cartao");
				}else if(rdDinheiro.isSelected()==true) {
					venda.setPagamento("dinheiro");
				}else {
					System.out.println("debug: forma de pagamento não informada");
				}
				venda.setTotal(precoT);
					

				VendaBD vendaBD = new VendaBD();
				Long id = vendaBD.insert(venda);
				
				for (ProdVNDQuant a: QuantVND) {
					if(a.getId()!=0) {
						vendaBD.insert(a, id);
					}

				}
				vendaBD.insert(venda);
			}

		});
		btNEncerrar.setOpaque(false);
		btNEncerrar.setFont(pop12);
		btNEncerrar.setBackground((Color) null);
		buttonChisel(btNEncerrar, clYellow, 5);
		btNEncerrar.setBounds(379, 80, 156, 23);
		pagamentoPanel.add(btNEncerrar);
		
		vendaInicial.setId(0);
		vendaInicial.setQuant(0);
		QuantVND.add(vendaInicial);
		
		JPanel prodPanel = new JPanel();
		prodPanel.setBackground(new Color(22, 22, 22));
		prodPanel.setBounds(772, 412, 379, 51);
		panelChisel(prodPanel, Color.WHITE, 5);
		contentPane.add(prodPanel);
		prodPanel.setLayout(null);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// transformar o texto do do codigo em int
				int codigo = 0;
				try {
					codigo = Integer.parseInt(txtCodigo.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Produto inv\u00E1lido", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//for pra poder ver todos os produtos e pegar o produto de tal id
				for (Produto produto : all_produtos) {
					
					//verifica se o id bateu com o produto da lista e seta os valores na tela e calcula tudo
					if(produto.getIdProduto() == codigo) {
						produtos.add(produto);
						DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
						model.addRow(new Object[] {
								"# " + produto.getIdProduto(),
								produto.getNomeProduto(),
								"R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',',')});
						tblProdutos.setModel(model);
						
						//soma o preco total
						precoT = produto.getPrecoVendaProduto()+precoT;
						
						
						//seta o total ali embaixo
						lblTotal.setText(String.valueOf("Total: "+precoT));
						
						//seta o troco ali no pagamento 
						lblPagTotal.setText(String.valueOf("Total: "+ precoT));
						
						//set a quantidade do estoque do produto selecionado no painel
						lblQuantidade.setText("ESTOQUE: "+produto.getQuantidadeEstoque());
						
						//set as dimesões no painel
						lblDimensoes.setText("DIMENS\u00D5ES: "+produto.getDimensoesProduto());
						
						//set o id no painel
						lblID.setText("#"+produto.getIdProduto());
						
						//set o preço no painel
						lblPreco.setText("R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','));
						
						//set o material no painel
						lblMaterial.setText("MATERIAL: "+produto.getMaterialProduto());
						
						lblNomeProduto.setText(produto.getNomeProduto());
						
						ArrayList<ProdVNDQuant> tempQuantVND = QuantVND;
						
						for (ProdVNDQuant vendaQ : tempQuantVND) {
							if (produto.getIdProduto()==vendaQ.getId()) {
								vendaQ.setQuant(vendaQ.getQuant()+1);
								QuantVND.add(vendaQ);
								System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
							}else {
								ProdVNDQuant vendaQ1 = new ProdVNDQuant();
								vendaQ1.setId(produto.getIdProduto());
								vendaQ1.setQuant(1);
								QuantVND.add(vendaQ1);
								}
							System.out.println(vendaQ.getId());
						}
					}
				}
			}
		});
		btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnAdd.setIcon(new ImageIcon("./img/add.png"));
		btnAdd.setForeground(null);
		btnAdd.setBackground(null);
		btnAdd.setBounds(10, 7, 36, 36);
		prodPanel.add(btnAdd);		
		
		JLabel lblCodigo = new JLabel("C\u00D3DIGO");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(pop10);
		lblCodigo.setBounds(55, 7, 80, 14);
		prodPanel.add(lblCodigo);
		
		ArrayList<Produto> produtos = (ArrayList<Produto>) new ProdutoBD().getListarProdutos();		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] {});
		for (Produto p: produtos) {
			model.addElement(p.getNomeProduto());
		}
		
		JComboBox<String> cmbProduto = new JComboBox<String>();
		cmbProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtCodigo.setText(Integer.toString(produtos.get(cmbProduto.getSelectedIndex()).getIdProduto()));
				} catch (IndexOutOfBoundsException x) {
					return;
				}
			}
		});
		cmbProduto.setBackground(new Color(45, 45, 45));
		cmbProduto.setForeground(Color.WHITE);
		cmbProduto.setBounds(145, 21, 224, 20);
		cmbProduto.setModel(model);
		cmbProduto.setSelectedItem(null);
		cmbProduto.setFont(pop12);
		cmbProduto.setUI(new Combo());
		prodPanel.add(cmbProduto);
		
		txtCodigo = new JTextField();
		txtCodigo.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
					  try {
						  int s = 0;
						  for (Produto p: all_produtos) {
							  if (p.getIdProduto() == Integer.parseInt(txtCodigo.getText())) {
								  cmbProduto.setSelectedIndex(s);
							  }
							  s++;
						  }
					  } catch (Exception x) {
						  return;
					  }
				  }
		});
		txtCodigo.setSelectionColor(clGreen);
		txtCodigo.setSelectedTextColor(new Color(22, 22, 22));
		txtCodigo.setCaretColor(Color.WHITE);
		txtCodigo.setForeground(Color.WHITE);
		txtCodigo.setBackground(new Color(45, 45, 45));
		txtCodigo.setBorder(BorderFactory.createEmptyBorder());
		txtCodigo.setFont(pop12);
		txtCodigo.setBounds(55, 21, 80, 20);
		prodPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setForeground(Color.WHITE);
		lblProduto.setFont(pop10);
		lblProduto.setBounds(145, 7, 80, 14);
		prodPanel.add(lblProduto);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setForeground(new Color(0, 0, 0));
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(0, 0, 1600, 861);
		contentPane.add(fakeBG);		
	}

	private void scrollChisel(JScrollPane scrollPane, Color color, int i) {
		scrollPane.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, i);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
		
	}

	private static void buttonChisel(JButton button, Color color, int radius) {       
        button.setFocusPainted(false);
		button.setForeground(color);
		RoundedBorder LineBorder = new RoundedBorder(color, radius);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		button.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}
	
	private static void panelChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}
	
	private static void fieldChisel(JTextField field, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        field.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(field.getBorder().getBorderInsets(field).top,
				field.getBorder().getBorderInsets(field).left,
				field.getBorder().getBorderInsets(field).bottom,
				field.getBorder().getBorderInsets(field).right);
        field.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
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

