package visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.sql.Date;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.ProdutoBD;
import controle.VendaBD;
import modelo.ProdVNDQuant;
import modelo.Produto;
import modelo.Usuario;
import modelo.Venda;

public class TelaVenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tblProdutos;
	private RoundField txtCodigo;
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
		Color clYellow = new Color(239, 161, 35);
		Color clDark = new Color(22, 22, 22);
		Color clLight = new Color(45, 45, 45);
		
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
		
		UIManager.put("TableHeader.cellBorder", BorderFactory.createCompoundBorder(new LineBorder(clDark, 2), new MatteBorder(0, 0, 1, 0, clBlue)));
		UIManager.put("TableHeader.background", clDark);
		UIManager.put("Button.select", Color.BLACK);
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(160, 90, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBackground(clLight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(clDark);
		panel.setBounds(1322, 11, 252, 124);
		panel.setBorder(new RoundBorder());
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio de Vendas");
		btnRelatorio.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorio.setIcon(new ImageIcon("./img/report.png"));
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > relat�rio de vendas");
				TelaRelatorio telaRel = new TelaRelatorio(usuarioLogado);
				telaRel.setVisible(true);
				setVisible(false);
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
		btnRelatorio.setForeground(Color.WHITE);
		btnRelatorio.setBounds(39, 59, 175, 23);
		btnRelatorio.setFocusPainted(false);
		panel.add(btnRelatorio);
		
		if (usuarioLogado.getCargo().equals("vendedor")) {
			btnRelatorio.setEnabled(false);
		}
		
		JButton btnSair = new JButton("Sair");
		btnSair.setHorizontalAlignment(SwingConstants.LEFT);
		btnSair.setFont(pop10);
		btnSair.setIcon(new ImageIcon("./img/logout.png"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLogin tl = new TelaLogin();
				tl.setVisible(true);
				setVisible(false);
			}
		});
		btnSair.setBackground(null);
		btnSair.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
		btnSair.setForeground(Color.WHITE);
		btnSair.setBounds(39, 82, 175, 23);
		btnSair.setFocusPainted(false);
		panel.add(btnSair);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnPerfil.setFont(pop10);
		btnPerfil.setIcon(new ImageIcon("./img/profile.png"));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > perfil");
				
				if (usuarioLogado.getCargo().equals("administador")) {
					TelaPerfilADM telaPerfil = new TelaPerfilADM(usuarioLogado, null);
					telaPerfil.setVisible(true);
				} else {
					TelaPerfilVND telaPerfil = new TelaPerfilVND(usuarioLogado);
					telaPerfil.setVisible(true);
				}
				
			}
		});
		btnPerfil.setBackground(null);
		btnPerfil.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBounds(39, 36, 175, 23);
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
		btnReturn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnReturn.setIcon(new ImageIcon("./img/return.png"));
		btnReturn.setForeground(null);
		btnReturn.setBackground(null);
		btnReturn.setBorder(new RoundBorder(clLight, 1, 27));
		btnReturn.setFocusPainted(false);
		btnReturn.setBounds(23, 51, 27, 27);
		contentPane.add(btnReturn);
	
		JButton btnSearch = new JButton("");
		btnSearch.setFocusPainted(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: pesquisar");
			}
		});
		btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnSearch.setIcon(new ImageIcon("./img/search.png"));
		btnSearch.setForeground(null);
		btnSearch.setBackground(null);
		btnSearch.setBorder(new RoundBorder(clLight, 1, 25));
		btnSearch.setBounds(46, 125, 27, 27);
		contentPane.add(btnSearch);
		
		JLabel lblVenda = new JLabel("Venda");
		lblVenda.setHorizontalAlignment(SwingConstants.LEFT);
		lblVenda.setForeground(Color.WHITE);
		lblVenda.setFont(pop24);
		lblVenda.setBounds(60, 42, 252, 45);
		contentPane.add(lblVenda);
		
		RoundField txtSearch = new RoundField(Color.WHITE, 20);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setCaretColor(Color.WHITE);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setSelectedTextColor(clDark);
		txtSearch.setSelectionColor(clBlue);
		txtSearch.setBackground(clDark);
		txtSearch.setBorder(BorderFactory.createEmptyBorder());
		txtSearch.setBounds(82, 128, 359, 20);
		txtSearch.setFont(pop12);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(47, 162, 715, 502);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(clDark);
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane.setBorder(new RoundBorder());
		Rolagem.defRolagem(scrollPane);
		contentPane.add(scrollPane);		
		
		JPanel dtlProduto = new JPanel();
		dtlProduto.setBounds(772, 162, 545, 114);
		dtlProduto.setBackground(clDark);
		dtlProduto.setBorder(new RoundBorder());
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
		tblProdutos.setSelectionBackground(clBlue);
		tblProdutos.setSelectionForeground(clDark);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdutos.setShowHorizontalLines(false);
		tblProdutos.setShowVerticalLines(false);
		tblProdutos.setShowGrid(false);
		tblProdutos.setIntercellSpacing(new Dimension(0, 0));
		
		tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblProdutos.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		Theader.setFont(pop12);
		Theader.setForeground(clBlue);
		Theader.setBackground(clDark);
		Theader.setReorderingAllowed(false);
		Theader.setResizingAllowed(false);
		Theader.setBorder(BorderFactory.createEmptyBorder());
		
		tblProdutos.setFont(pop12);
		tblProdutos.setForeground(Color.WHITE);
		tblProdutos.setBackground(clDark);
		tblProdutos.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(tblProdutos);
		
		JPanel pagamentoPanel = new JPanel();
		pagamentoPanel.setLayout(null);
		pagamentoPanel.setBackground(clDark);
		pagamentoPanel.setBorder(new RoundBorder());
		pagamentoPanel.setBounds(772, 287, 545, 114);
		contentPane.add(pagamentoPanel);
		
		JLabel lblNomeProduto_1 = new JLabel("PAGAMENTO");
		lblNomeProduto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeProduto_1.setForeground(Color.WHITE);
		lblNomeProduto_1.setFont(pop12);
		lblNomeProduto_1.setBounds(10, 11, 161, 14);
		pagamentoPanel.add(lblNomeProduto_1);
		
		JLabel lblPagTotal = new JLabel("R$ 0,00");
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
		lblTotal.setBounds(1161, 449, 156, 14);
		contentPane.add(lblTotal);
		
		JLabel lblTroco = new JLabel("Troco:  R$ 0,00");
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
						lblTotal.setText(String.valueOf("TOTAL: R$ " + String.format("%.02f",precoT).replace('.', ',')));
						
						//seta o troco ali no pagamento 
						lblPagTotal.setText(String.valueOf("R$ " + String.format("%.02f",precoT).replace('.', ',')));
						
						return;
					}
				}
			}
		});
		btnRemover.setFocusPainted(false);
		btnRemover.setIcon(new ImageIcon("./img/delete.png"));
		btnRemover.setBounds(511, 81, 24, 24);
		dtlProduto.add(btnRemover);
		btnRemover.setOpaque(false);
		btnRemover.setBackground(null);
		btnRemover.setFont(pop12);
		btnRemover.setBorder(new RoundBorder(clRed, 1, 24));
		dtlProduto.add(btnRemover);
		
		RoundField txtPagamentoValor = new RoundField();
		txtPagamentoValor.setEnabled(false);
		txtPagamentoValor.setCaretColor(Color.WHITE);
		txtPagamentoValor.getDocument().addDocumentListener(new DocumentListener() {
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
					troco = Float.parseFloat(txtPagamentoValor.getText()) - precoT;
					if (troco > 0) {
						lblTroco.setText("Troco:  R$ " + String.format("%.02f",troco).replace('.', ','));
					} else {
						troco = 0.0f;
						lblTroco.setText("Troco:  R$ " + String.format("%.02f",troco).replace('.', ','));
					}
				} catch (Exception x) {
					troco = 0.0f;
					lblTroco.setText("Troco:  R$ " + String.format("%.02f",troco).replace('.', ','));
				}
			}
		});
		txtPagamentoValor.setForeground(Color.WHITE);
		txtPagamentoValor.setBackground(clLight);
		txtPagamentoValor.setSelectedTextColor(clDark);
		txtPagamentoValor.setSelectionColor(clBlue);
		txtPagamentoValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPagamentoValor.setBounds(274, 37, 107, 20);
		txtPagamentoValor.setFont(pop12);
		pagamentoPanel.add(txtPagamentoValor);
		txtPagamentoValor.setColumns(10);
		
		JRadioButton rdDinheiro = new JRadioButton("DINHEIRO");
		rdDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPagamentoValor.setEnabled(true);
			}
		});
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
		rdCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPagamentoValor.setText("");
				txtPagamentoValor.setEnabled(false);
				lblTroco.setText("R$ 0,00");
			}
		});
		pagGroup.add(rdCartao);
		rdCartao.setFocusPainted(false);
		rdCartao.setForeground(Color.WHITE);
		rdCartao.setFont(pop10);
		rdCartao.setBackground((Color) null);
		rdCartao.setIcon(new ImageIcon("./img/radio_button.png"));
		rdCartao.setSelectedIcon(new ImageIcon("./img/radio_button_checked.png"));
		rdCartao.setBounds(20, 58, 109, 23);
		pagamentoPanel.add(rdCartao);
		
		RoundButton btNEncerrar = new RoundButton("ENCERRAR");
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
		btNEncerrar.setForeground(clYellow);
		btNEncerrar.setOpaque(false);
		btNEncerrar.setFont(pop12);
		btNEncerrar.setBackground((Color) null);
		btNEncerrar.setBorder(new RoundBorder(clYellow));
		btNEncerrar.setBounds(379, 80, 156, 23);
		pagamentoPanel.add(btNEncerrar);
		
		vendaInicial.setId(0);
		vendaInicial.setQuant(0);
		QuantVND.add(vendaInicial);
		
		JPanel prodPanel = new JPanel();
		prodPanel.setBackground(clDark);
		prodPanel.setBounds(772, 412, 379, 51);
		prodPanel.setBorder(new RoundBorder());
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
						lblTotal.setText(String.valueOf("TOTAL: R$ " + String.format("%.02f",precoT).replace('.', ',')));
						
						//seta o troco ali no pagamento 
						lblPagTotal.setText(String.valueOf("R$ " + String.format("%.02f",precoT).replace('.', ',')));
						
						//set a quantidade do estoque do produto selecionado no painel
						lblQuantidade.setText("ESTOQUE: "+produto.getQuantidadeEstoque());
						
						//set as dimesões no painel
						lblDimensoes.setText("DIMENS\u00D5ES: "+produto.getDimensoesProduto());
						
						//set o id no painel
						lblID.setText("# "+produto.getIdProduto());
						
						//set o preço no painel
						lblPreco.setText("R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','));
						
						//set o material no painel
						lblMaterial.setText("MATERIAL: "+produto.getMaterialProduto());
						
						lblNomeProduto.setText(produto.getNomeProduto());
						
						tblProdutos.setRowSelectionInterval(tblProdutos.getModel().getRowCount()-1, tblProdutos.getModel().getRowCount()-1);
						
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
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new RoundBorder(clDark, 1, 36));
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
		
		RoundBorder lightBorder = new RoundBorder(clLight, 1, 10);
		RoundBorder blueBorder = new RoundBorder(clBlue, 1, 10);
		
		JPanel codPanel = new JPanel();
		codPanel.setBackground(clLight);
		codPanel.setBorder(lightBorder);
		codPanel.setBounds(145, 21, 224, 20);;
		prodPanel.add(codPanel);
		codPanel.setLayout(null);
		
		JComboBox<String> cmbProduto = new JComboBox<String>();
		cmbProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtCodigo.setText(Integer.toString(produtos.get(cmbProduto.getSelectedIndex()).getIdProduto()));
				} catch (IndexOutOfBoundsException x) {
					return;
				}
				
				if (cmbProduto.getSelectedItem() != null) {
					codPanel.setBackground(clBlue);
					codPanel.setBorder(blueBorder);
					
					cmbProduto.setBackground(clBlue);
					cmbProduto.setForeground(clDark);
				}
			}
		});
		cmbProduto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				codPanel.setBackground(clLight);
				codPanel.setBorder(lightBorder);
				
				cmbProduto.setBackground(clLight);
				cmbProduto.setForeground(Color.WHITE);
			}
		});
		cmbProduto.setBackground(clLight);
		cmbProduto.setForeground(Color.WHITE);
		cmbProduto.setBounds(3, 1, 218, 18);
		cmbProduto.setModel(model);
		cmbProduto.setSelectedItem(null);
		cmbProduto.setFont(pop12);
		Combo cmbui = new Combo();
		cmbui.setColor(clBlue);
		cmbProduto.setUI(cmbui);
		codPanel.add(cmbProduto);
		
		txtCodigo = new RoundField();
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
		txtCodigo.setSelectionColor(clBlue);
		txtCodigo.setSelectedTextColor(clDark);
		txtCodigo.setCaretColor(Color.WHITE);
		txtCodigo.setForeground(Color.WHITE);
		txtCodigo.setBackground(clLight);
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
		fakeBG.setForeground(Color.BLACK);
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(0, 0, 1600, 861);
		contentPane.add(fakeBG);		
	}
}

