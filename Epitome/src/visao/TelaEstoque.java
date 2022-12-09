package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controle.FornecedorBD;
import controle.ProdutoBD;
import controle.UsuarioDAO;
import modelo.Fornecedor;
import modelo.Produto;
import modelo.Usuario;

import javax.swing.ListSelectionModel;

public class TelaEstoque extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblProdutos;
	private ArrayList<Produto> produtos;
	private String pesquisar = null;

	public TelaEstoque(Usuario usuarioLogado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clGreen = new Color(168, 198, 51);
		Color clDark = new Color(22, 22, 22);
		Color clLight = new Color(45, 45, 45);
		
		Font poppins, pop10 = null, pop12 = null, pop24 = null;

		UIManager.put("TableHeader.cellBorder", BorderFactory.createCompoundBorder(new LineBorder(clDark, 2), new MatteBorder(0, 0, 1, 0, clGreen)));
		UIManager.put("TableHeader.background", clDark);
		UIManager.put("Button.select", Color.BLACK);
		
		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
			pop24 = poppins.deriveFont(Font.TRUETYPE_FONT, 24);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Dimension r = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) r.getHeight();
		int w = (int) r.getWidth();
		
		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, w, h);
		contentPane = new JPanel();
		contentPane.setBackground(clLight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel main = new JPanel();
		main.setBackground(null);
		main.setOpaque(false);
		main.setBounds(w/2-740, h/2-343, 1480, 686);
		main.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(main);
		main.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(clDark);
		panel.setBounds(w-278, 11, 252, 124);
		panel.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnRelatorio = new JButton("Relat\u00F3rio de Vendas");
		btnRelatorio.setHorizontalAlignment(SwingConstants.LEFT);
		btnRelatorio.setIcon(new ImageIcon("./img/report.png"));
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > relat\u00F3rio de vendas");
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
				TelaPerfilADM telaPerfil = new TelaPerfilADM(usuarioLogado, null);
				telaPerfil.setVisible(true);
			}
		});
		btnPerfil.setBackground(null);
		btnPerfil.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setBounds(39, 36, 175, 23);
		btnPerfil.setFocusPainted(false);
		panel.add(btnPerfil);

		JLabel lblNome = new JLabel(usuarioLogado.getNome_usuario());
		// lblNome.setText(nome do usuario)
		lblNome.setBounds(10, 11, 232, 14);
		panel.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(pop12);

		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > tela inicial");
				TelaInicialADM telainicial = new TelaInicialADM(usuarioLogado);
				telainicial.setVisible(true);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 37, 1480, 602);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(clDark);
		scrollPane.setBackground(clDark);
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new RoundBorder(Color.WHITE, 1, 10));
		Rolagem.defRolagem(scrollPane);
		main.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(null, new String[] { "ID", "NOME", "PRE\u00C7O", "MATERIAL", "DIMENS\u00D5ES", "FORNECEDOR", "QUANTIDADE"});
		
		tblProdutos = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		tblProdutos.setFocusable(false);
		tblProdutos.setSelectionBackground(clGreen);
		tblProdutos.setSelectionForeground(clDark);
		tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdutos.setShowHorizontalLines(false);
		tblProdutos.setShowVerticalLines(false);
		tblProdutos.setShowGrid(false);
		tblProdutos.setIntercellSpacing(new Dimension(0, 0));

		JTableHeader Theader = tblProdutos.getTableHeader();

		Theader.setFont(pop12);
		Theader.setForeground(clGreen);
		Theader.setBackground(clDark);
		Theader.setReorderingAllowed(false);
		Theader.setResizingAllowed(false);
		Theader.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		tblProdutos.setFont(pop12);
		tblProdutos.setForeground(Color.WHITE);
		tblProdutos.setBackground(clDark);
		tblProdutos.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		refresh(tblProdutos);
		
		scrollPane.setViewportView(tblProdutos);
		
		RoundField txtSearch = new RoundField(Color.WHITE, 20);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setCaretColor(Color.WHITE);
		txtSearch.setForeground(Color.WHITE);
		txtSearch.setSelectedTextColor(clDark);
		txtSearch.setSelectionColor(clGreen);
		txtSearch.setBackground(clDark);
		txtSearch.setBorder(BorderFactory.createEmptyBorder());
		txtSearch.setBounds(35, 3, 359, 20);
		txtSearch.setFont(pop12);
		main.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("");
		btnSearch.setFocusPainted(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: pesquisar");
				ProdutoBD produtobd = new ProdutoBD();
				produtos = produtobd.getPesquisar(txtSearch.getText());
				refresh(tblProdutos);
				pesquisar = txtSearch.getText();			}
		});
		btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnSearch.setIcon(new ImageIcon("./img/search.png"));
		btnSearch.setForeground(null);
		btnSearch.setBackground(null);
		btnSearch.setBorder(new RoundBorder(clLight, 1, 25));
		btnSearch.setBounds(0, 0, 27, 27);
		main.add(btnSearch);

		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: editar produto (tela de cadastro de produto)");
				if (tblProdutos.getSelectedRow() == -1) {
					return;
				}
				
				produtos = new ProdutoBD().getListarProdutos();
				
				TelaModificarProduto tmp = new TelaModificarProduto(tblProdutos, produtos.get(tblProdutos.getSelectedRow()));
				tmp.setVisible(true);

			}
		});
		btnEdit.setFocusPainted(false);
		btnEdit.setBorder(new RoundBorder(clLight, 1, 36));
		btnEdit.setIcon(new ImageIcon("./img/edit.png"));
		btnEdit.setBackground(null);
		btnEdit.setOpaque(false);
		btnEdit.setBounds(46, 650, 36, 36);
		main.add(btnEdit);

		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroProduto tcp = new TelaCadastroProduto(tblProdutos);
				tcp.setVisible(true);
			}
		});
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new RoundBorder(clLight, 1, 36));
		btnAdd.setIcon(new ImageIcon("./img/add.png"));
		btnAdd.setBackground(null);
		btnAdd.setOpaque(false);
		btnAdd.setBounds(0, 650, 36, 36);
		main.add(btnAdd);

		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstoque.setForeground(Color.WHITE);
		lblEstoque.setFont(pop24);
		lblEstoque.setBounds(60, 42, 252, 45);
		contentPane.add(lblEstoque);


		
		JButton btnMinimize = new JButton("");
		btnMinimize.setFocusPainted(false);
		btnMinimize.setBorder(new RoundBorder(new Color(45, 45, 45), 1, 18));
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setFont(pop12);
		btnMinimize.setIcon(new ImageIcon("./img/minimize.png"));
		btnMinimize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("minimizar");
				setState(JFrame.ICONIFIED);
//				dispose();
			}
		});
		btnMinimize.setBackground(null);
		btnMinimize.setBounds(w-24, 4, 20, 20);
		contentPane.add(btnMinimize);

		JLabel fakeBG = new JLabel("");
		fakeBG.setAlignmentX(Component.CENTER_ALIGNMENT);
		fakeBG.setHorizontalTextPosition(SwingConstants.CENTER);
		fakeBG.setHorizontalAlignment(SwingConstants.CENTER);
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(getBounds());
		contentPane.add(fakeBG);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);

	}
	
	private void refresh(JTable tbl) {
		int sel = tbl.getSelectedRow();
		
		DefaultTableModel model = new DefaultTableModel(null, new String[] { "ID", "NOME", "PRE\u00C7O", "MATERIAL", "DIMENS\u00D5ES", "FORNECEDOR", "QUANTIDADE"});
		
		if(pesquisar == null) {
			produtos = new ProdutoBD().getListarProdutos();
		}else {
		produtos = new ProdutoBD().getPesquisar(pesquisar);
		}
		ArrayList<Fornecedor> fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();
		
		for (Produto produto : produtos) {
					String fNome = "Sem fornecedor";
					for (Fornecedor f: fornecedores) {
						if (f.getId_fornecedor() == produto.getFornecedor()) {
							fNome = f.getNome_fornecedor();
						}
					}
					model.addRow(new Object[] {
							"# " + produto.getIdProduto(),
							produto.getNomeProduto(),
							"R$ " + String.format("%.02f", produto.getPrecoVendaProduto()).replace('.',','),
							produto.getMaterialProduto(),
							produto.getDimensoesProduto(),
							fNome,
							produto.getQuantidadeEstoque()});
				
		}
		
		tbl.setModel(model);
		if (sel != -1) {
			try {
				tbl.setRowSelectionInterval(sel, sel);
			} catch (IllegalArgumentException x) {
				tbl.setRowSelectionInterval(tbl.getModel().getRowCount()-1, tbl.getModel().getRowCount()-1);
			}
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
