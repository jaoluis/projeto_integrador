package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controle.VendaBD;
import modelo.Usuario;
import modelo.Venda;

public class TelaRelatorio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblProdutos;
	ArrayList<Venda> vendas = new ArrayList<Venda>();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorio frame = new TelaRelatorio(null);
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
	public TelaRelatorio(Usuario usuarioLogado) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clDark = new Color(22, 22, 22);
		Color clGreen = new Color(168, 198, 51);
		Color clLight = new Color(45, 45, 45);

		Font poppins, pop10 = null, pop12 = null, pop24 = null;

		try {
			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
			pop24 = poppins.deriveFont(Font.TRUETYPE_FONT, 24);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UIManager.put("TableHeader.cellBorder",
				BorderFactory.createCompoundBorder(new LineBorder(clDark, 2), new MatteBorder(0, 0, 1, 0, clGreen)));
		UIManager.put("TableHeader.background", clDark);
		UIManager.put("Button.select", Color.BLACK);

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
				System.out.println("debug: tela inicial adm > relat�rio de vendas");
				TelaRelatorio telaRel = new TelaRelatorio(usuarioLogado);
				telaRel.setVisible(true);
				setVisible(false);
			}
		});
		btnRelatorio.setBackground(null);
		btnRelatorio.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
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
		btnSair.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
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
		btnPerfil.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), new RoundBorder(clDark, 2, 23)));
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

		JLabel lblEstoque = new JLabel("Relat\u00F3rio de Vendas");
		lblEstoque.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstoque.setForeground(Color.WHITE);
		lblEstoque.setFont(pop24);
		lblEstoque.setBounds(60, 42, 252, 45);
		contentPane.add(lblEstoque);

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
		btnSearch.setBounds(274, 0, 27, 27);
		main.add(btnSearch);

		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: deletar relatorio");

				int linha = tblProdutos.getSelectedRow();
				int idProduto = (int) tblProdutos.getValueAt(linha, 0);

				for (Venda venda : vendas) {
					if (idProduto == venda.getId()) {
						VendaBD vendaBD = new VendaBD();
						vendaBD.DeleteByID(idProduto);
					}
				}
			}
		});
		btnDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnDelete.setIcon(new ImageIcon("./img/delete.png"));
		btnDelete.setForeground(null);
		btnDelete.setBackground(null);
		btnDelete.setBounds(46, 650, 36, 36);
		main.add(btnDelete);

		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda tcp = new TelaVenda(usuarioLogado);
				tcp.setVisible(true);
				setVisible(false);
			}
		});
		btnAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnAdd.setIcon(new ImageIcon("./img/add.png"));
		btnAdd.setForeground(null);
		btnAdd.setBackground(null);
		btnAdd.setBounds(0, 650, 36, 36);
		main.add(btnAdd);

		RoundFormattedField txtAte = new RoundFormattedField(TelaCadastro.def_mask("##/##/####", '\u2022'), Color.WHITE,
				20);
		txtAte.setHorizontalAlignment(SwingConstants.CENTER);
		txtAte.setForeground(Color.WHITE);
		txtAte.setCaretColor(Color.WHITE);
		txtAte.setBackground(clDark);
		txtAte.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtAte.setBounds(174, 3, 90, 20);
		txtAte.setFont(pop12);
		main.add(txtAte);
		txtAte.setColumns(10);

		JLabel lblAte = new JLabel("AT\u00C9:");
		lblAte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAte.setForeground(Color.WHITE);
		lblAte.setFont(pop12);
		lblAte.setBounds(137, 1, 27, 25);
		main.add(lblAte);

		RoundFormattedField txtDe = new RoundFormattedField(TelaCadastro.def_mask("##/##/####", '\u2022'), Color.WHITE,
				20);
		txtDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtDe.setForeground(Color.WHITE);
		txtDe.setCaretColor(Color.WHITE);
		txtDe.setBackground(clDark);
		txtDe.setBounds(37, 3, 90, 20);
		txtDe.setFont(pop12);
		main.add(txtDe);
		txtDe.setColumns(10);

		JLabel lblDe = new JLabel("DE:");
		lblDe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDe.setForeground(Color.WHITE);
		lblDe.setFont(pop12);
		lblDe.setBounds(0, 1, 27, 25);
		main.add(lblDe);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 37, 1480, 602);
		scrollPane.setFont(pop12);
		scrollPane.setForeground(Color.WHITE);
		scrollPane.setBackground(clDark);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBorder(new RoundBorder());
		main.add(scrollPane);

		tblProdutos = new JTable();
		tblProdutos.setShowHorizontalLines(false);
		tblProdutos.setShowVerticalLines(false);
		tblProdutos.setShowGrid(false);

		DefaultTableModel model = new DefaultTableModel(null,
				new String[] { "ID", "PAGAMENTO", "TOTAL", "DATA", "QUANTIDADE" });
		tblProdutos = new JTable(model);
		tblProdutos.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "ID", "PAGAMENTO", "TOTAL", "DATA", "QUANTIDADE" }));

		VendaBD vendaBD = new VendaBD();

		vendas = vendaBD.getListarVendas();
		for (Venda venda : vendas) {
			model.addRow(
					new Object[] { venda.getId(), venda.getPagamento(), venda.getTotal(), venda.getDataVenda(), "3" });

//			System.out.println(venda.getId() + " " + venda.getPagamento() + " " + venda.getTotal() + " "
//					+ venda.getDataVenda() + " " + "3");

		}
		tblProdutos.setModel(model);
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
		scrollPane.setViewportView(tblProdutos);

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
}
