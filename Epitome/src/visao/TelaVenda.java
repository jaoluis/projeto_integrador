package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.UsuarioDAO;
import modelo.Usuario;

public class TelaVenda extends JFrame {

	private JPanel contentPane;
	private JTable tblProdutos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVenda frame = new TelaVenda();
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
	public TelaVenda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clGreen = new Color(168, 198, 51);
		Color clLight = new Color(197, 197, 197);
		Color clYellow = new Color(239, 161, 35);
		
		BasicScrollBarUI minScrollBar = new BasicScrollBarUI() {
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
		};
		
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
		
		JButton btnRelatorio = new JButton("Relatório de Vendas");
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > relatório de vendas");
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnRelatorio.setForeground(clBlue);
		btnRelatorio.setBounds(10, 59, 232, 23);
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
		panel.add(btnSair);
		
		JButton btnLogin = new JButton("Perfil");
		btnLogin.setFont(pop10);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > perfil");
				//TelaPerfil telaPerfil = new TelaPerfil(0);
				//telaPerfil.setVisible(true);
			}
		});
		btnLogin.setBackground(null);
		btnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnLogin.setForeground(clRed);
		btnLogin.setBounds(10, 36, 232, 23);
		panel.add(btnLogin);
		
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
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: adicionar item por c�digo (txtItem)");
			}
		});
		btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnAdd.setIcon(new ImageIcon("./img/add.png"));
		btnAdd.setForeground(null);
		btnAdd.setBackground(null);
		btnAdd.setBounds(47, 775, 36, 36);
		contentPane.add(btnAdd);
		
		JTextField txtItem = new JTextField();
		txtItem.setForeground(Color.WHITE);
		txtItem.setBackground(new Color(22, 22, 22));
		txtItem.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtItem.setBounds(93, 781, 181, 25);
		txtItem.setFont(pop12);
		fieldChisel(txtItem, Color.WHITE, 5);
		contentPane.add(txtItem);
		txtItem.setColumns(10);
		
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
		scrollPane.setBounds(47, 162, 715, 602);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(new Color(22, 22, 22));
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollChisel(scrollPane, Color.WHITE, 5);
		scrollPane.getVerticalScrollBar().setUI(minScrollBar);
		contentPane.add(scrollPane);
		
		tblProdutos = new JTable();
		tblProdutos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
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
		
		JLabel lblQuantidade = new JLabel("ESTOQUE: 0");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(pop10);
		lblQuantidade.setBounds(10, 36, 190, 14);
		dtlProduto.add(lblQuantidade);
		
		JLabel lblMaterial = new JLabel("MATERIAL: 0");
		lblMaterial.setForeground(Color.WHITE);
		lblMaterial.setFont(pop10);
		lblMaterial.setBounds(10, 61, 190, 14);
		dtlProduto.add(lblMaterial);
		
		JLabel lblDimensoes = new JLabel("DIMENSÕES 0x0x0");
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
		
		JButton btnDinheiro = new JButton("DINHEIRO");
		btnDinheiro.setOpaque(false);
		btnDinheiro.setFont(pop12);
		btnDinheiro.setBackground(null);
		buttonChisel(btnDinheiro, clGreen, 5);
		btnDinheiro.setBounds(10, 36, 156, 23);
		pagamentoPanel.add(btnDinheiro);
		
		JButton btnCartao = new JButton("CARTÃO");
		btnCartao.setOpaque(false);
		btnCartao.setFont(pop12);
		btnCartao.setBackground((Color) null);
		btnCartao.setBounds(10, 70, 156, 23);
		buttonChisel(btnCartao, clBlue, 5);
		pagamentoPanel.add(btnCartao);
		
		JLabel lblTotal = new JLabel("TOTAL: R$0,00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(pop16);
		lblTotal.setBounds(499, 775, 263, 14);
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
		
		JLabel lblTroco = new JLabel("Troco:  R$0,00");
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroco.setForeground(Color.WHITE);
		lblTroco.setFont(pop10);
		lblTroco.setBounds(391, 40, 144, 14);
		pagamentoPanel.add(lblTroco);
		
		JButton btNEncerrar = new JButton("ENCERRAR");
		btNEncerrar.setOpaque(false);
		btNEncerrar.setFont(pop12);
		btNEncerrar.setBackground((Color) null);
		buttonChisel(btNEncerrar, clYellow, 5);
		btNEncerrar.setBounds(379, 70, 156, 23);
		pagamentoPanel.add(btNEncerrar);
		
		JLabel fakeBG = new JLabel("");
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
