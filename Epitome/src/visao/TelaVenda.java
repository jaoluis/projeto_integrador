package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
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

public class TelaVenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tblProdutos;
	private JTextField txtCodigo;
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
		
		JLabel lblNome = new JLabel("Fulano da Silva");
		//lblNome.setText(nome do usuario)
		lblNome.setBounds(10, 11, 232, 14);
		panel.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(pop12); 
		
		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// adm ou vendedor?
				//System.out.println("debug: tela de estoque > tela inicial");
				//TelaInicialADM telainicial = new TelaInicialADM();
				//telainicial.setVisible(true);
				//setVisible(false);
			}
		});
		
		JLabel lblEmail = new JLabel("Codigo");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);

		txtCodigo = new JTextField();
		txtCodigo.setForeground(new Color(255, 255, 255));
		txtCodigo.setBackground(new Color(0, 0, 0));
		txtCodigo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCodigo.setFont(pop12);
		fieldChisel(txtCodigo, Color.WHITE, 5);
		txtCodigo.setBounds(818, 468, 244, 25);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
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
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setBackground(new Color(22, 22, 22));
		txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSearch.setBounds(80, 126, 431, 25);
		txtSearch.setFont(pop12);
		fieldChisel(txtSearch, Color.WHITE, 5);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 162, 715, 502);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(new Color(22, 22, 22));
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollChisel(scrollPane, Color.WHITE, 5);
		Rolagem.defRolagem(scrollPane);
		contentPane.add(scrollPane);
		
		tblProdutos = new JTable();
		tblProdutos.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "NOME", "PRE\u00C7O"
			}
		));
		
		
		
		JTableHeader Theader = tblProdutos.getTableHeader();
		
		Theader.setFont(pop12);
		Theader.setForeground(Color.WHITE);
		Theader.setBackground(new Color(22, 22, 22));
		Theader.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JPanel dtlProduto = new JPanel();
		dtlProduto.setBounds(772, 162, 545, 114);
		dtlProduto.setBackground(new Color(22, 22, 22));
		panelChisel(dtlProduto, Color.WHITE, 5);
		contentPane.add(dtlProduto);
		dtlProduto.setLayout(null);
		
		// atualizar detalhes do produto quando for selecionado na tabela
		
		JLabel lblID = new JLabel("00000000");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(10, 11, 122, 14);
		lblID.setFont(pop12);
		lblID.setForeground(Color.WHITE);
		dtlProduto.add(lblID);
		
		JLabel lblNomeProduto = new JLabel("Nome do Produto");
		lblNomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeProduto.setForeground(Color.WHITE);
		lblNomeProduto.setFont(pop12);
		lblNomeProduto.setBounds(142, 11, 263, 14);
		dtlProduto.add(lblNomeProduto);
		
		JLabel lblPreco = new JLabel("R$0,00");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setForeground(Color.WHITE);
		lblPreco.setFont(pop12);
		lblPreco.setBounds(415, 11, 120, 14);
		dtlProduto.add(lblPreco);
		
		JLabel lblQuantidade = new JLabel("ESTOQUE: 0.00");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(pop10);
		lblQuantidade.setBounds(10, 36, 190, 14);
		dtlProduto.add(lblQuantidade);
		
		JLabel lblMaterial = new JLabel("MATERIAL: 0");
		lblMaterial.setForeground(Color.WHITE);
		lblMaterial.setFont(pop10);
		lblMaterial.setBounds(10, 61, 190, 14);
		dtlProduto.add(lblMaterial);
		
		JLabel lblDimensoes = new JLabel("DIMENS\u00D5ES: 0x0x0");
		lblDimensoes.setForeground(Color.WHITE);
		lblDimensoes.setFont(pop10);
		lblDimensoes.setBounds(10, 86, 190, 14);
		dtlProduto.add(lblDimensoes);
		
		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(379, 80, 156, 23);
		dtlProduto.add(btnRemover);
		btnRemover.setOpaque(false);
		btnRemover.setBackground(null);
		btnRemover.setFont(pop12);
		buttonChisel(btnRemover, clRed, 5);
		dtlProduto.add(btnRemover);
		
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
		lblTotal.setBounds(1065, 474, 252, 14);
		contentPane.add(lblTotal);
		
		JTextField txtPagamentoValor = new JTextField();
		txtPagamentoValor.setForeground(Color.WHITE);
		txtPagamentoValor.setBackground(new Color(22, 22, 22));
		txtPagamentoValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPagamentoValor.setBounds(274, 35, 107, 25);
		txtPagamentoValor.setFont(pop12);
		fieldChisel(txtPagamentoValor, Color.WHITE, 5);
		pagamentoPanel.add(txtPagamentoValor);
		txtPagamentoValor.setColumns(10);
		JLabel lblTroco = new JLabel("Troco:  R$"+troco);
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroco.setForeground(Color.WHITE);
		lblTroco.setFont(pop10);
		lblTroco.setBounds(391, 40, 144, 14);
		pagamentoPanel.add(lblTroco);
		

		
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
		
		JRadioButton rdCartao = new JRadioButton("CARTÃO");
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
		btNEncerrar.setBounds(379, 70, 156, 23);
		pagamentoPanel.add(btNEncerrar);
		
		vendaInicial.setId(0);
		vendaInicial.setQuant(0);
		QuantVND.add(vendaInicial);
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProdutoBD produtoBD = new ProdutoBD();
				
				// transformar o texto do do codigo em int
				int codigo = Integer.parseInt(txtCodigo.getText());
				
				//for pra poder ver todos os produtos e pegar o produto de tal id
				for (Produto produto : produtoBD.getListarProdutos()) {
					
					//verifica se o id bateu com o produto da lista e seta os valores na tela e calcula tudo
					if(produto.getIdProduto() == codigo) {
						DefaultTableModel model = (DefaultTableModel) tblProdutos.getModel();
						model.addRow(new Object[] { produto.getIdProduto(), produto.getNomeProduto(), produto.getPrecoVendaProduto()});
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
						lblDimensoes.setText("DIMENSÕES: "+produto.getDimencoesProduto());
						
						//set o id no painel
						lblID.setText("#"+produto.getIdProduto());
						
						//set o preço no painel
						lblPreco.setText("R$"+produto.getPrecoVendaProduto());
						
						//set o material no painel
						lblMaterial.setText("Material: "+produto.getMaterialProduto());
						
						lblNomeProduto.setText(produto.getNomeProduto());
						
						for (ProdVNDQuant vendaQ : QuantVND) {
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
		btnAdd.setBounds(772, 462, 36, 36);
		contentPane.add(btnAdd);		
		
		JLabel txtDigiteOCodigo = new JLabel("C\u00D3DIGO");
		txtDigiteOCodigo.setForeground(new Color(255, 255, 255));
		txtDigiteOCodigo.setFont(pop10);
		txtDigiteOCodigo.setBounds(818, 453, 128, 14);
		contentPane.add(txtDigiteOCodigo);
		
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

