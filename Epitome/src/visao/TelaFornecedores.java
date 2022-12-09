package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import controle.FornecedorBD;
import modelo.Fornecedor;

public class TelaFornecedores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private ArrayList<String> valuesFornecedor = new ArrayList<String>();
	

	public TelaFornecedores() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clYellow = new Color(239, 161, 35);
		Color clDark = new Color(22, 22, 22);
		Color clLight = new Color(45, 45, 45);
		Color clLighter = new Color(197, 197, 197);

		Font poppins, pop12 = null, pop10 = null;

		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UIManager.put("Button.select", Color.BLACK);
		
		setResizable(false);
		setTitle("Fornecedores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 564);
		contentPane = new JPanel();
		contentPane.setBackground(clLight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(clDark);
		panel.setBounds(50, 11, 242, 488);
		contentPane.add(panel);
		panel.setBorder(new RoundBorder());
		panel.setLayout(null);
		
		JLabel lblFornecedores = new JLabel("FORNECEDORES");
		lblFornecedores.setForeground(clLighter);
		lblFornecedores.setFont(null);
		lblFornecedores.setFont(pop10);
		lblFornecedores.setBounds(10, 15, 130, 14);
		panel.add(lblFornecedores);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 174, 445);
		Rolagem.defRolagem(scrollPane);
		scrollPane.setBorder(new RoundBorder());
		scrollPane.setBackground(null);
		scrollPane.setForeground(null);
		panel.add(scrollPane);
		
		fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();
		
		JList<String> listaFornecedor = new JList<String>();
		listaFornecedor.setFocusable(false);
		listaFornecedor.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listaFornecedor.getSelectedIndex() == -1) {
					return;
				}
			}
		});
		listaFornecedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaFornecedor.setSelectionBackground(clYellow);
		listaFornecedor.setSelectionForeground(clDark);
		listaFornecedor.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaFornecedor.setBackground(clDark);
		listaFornecedor.setForeground(Color.WHITE);
		listaFornecedor.setFont(pop12);
		listaFornecedor.setBounds(0, 50, 156, 113);
		scrollPane.setViewportView(listaFornecedor);
		
		int t = 0;
		for (Fornecedor f: fornecedores) {
			valuesFornecedor.add("");
			valuesFornecedor.set(t, f.getNome_fornecedor());
			t++;
		}
		TelaCadastro.updateList(listaFornecedor, valuesFornecedor);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.setBounds(194, 32, 36, 36);
		panel.add(btnCadastrar);
		btnCadastrar.setBackground(null);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setIcon(new ImageIcon("./img/add.png"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFornecedor telaCadastro = new TelaCadastroFornecedor(listaFornecedor);
				telaCadastro.setVisible(true);
			}
		});

		btnCadastrar.setBorder(new RoundBorder(clDark, 1, 36));
		btnCadastrar.setForeground(Color.WHITE);
		
		JButton btnDetalhar = new JButton("");
		btnDetalhar.setBounds(194, 79, 36, 36);
		panel.add(btnDetalhar);
		btnDetalhar.setBackground(null);
		btnDetalhar.setFocusPainted(false);
		btnDetalhar.setIcon(new ImageIcon("./img/show.png"));
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaFornecedor.getSelectedIndex() == -1) {
					return;
				}
				
				fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();
				valuesFornecedor.clear();
				
				Fornecedor f = fornecedores.get(listaFornecedor.getSelectedIndex());
				TelaPerfilFornecedor tl = new TelaPerfilFornecedor(f, listaFornecedor);
				tl.setVisible(true);
			}
		});
		btnDetalhar.setForeground(Color.WHITE);
		btnDetalhar.setBorder(new RoundBorder(clDark, 1, 36));
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-281, -138, 968, 663);
		contentPane.add(fakeBG);
		
		setLocationRelativeTo(null);
	}
	
	static public void refresh(JList<String> list) {
		ArrayList<Fornecedor> n_fornecedores = (ArrayList<Fornecedor>) new FornecedorBD().getListarFornecedores();
		ArrayList<String> n_valuesFornecedor = new ArrayList<String>();
		
		int t = 0;
		for (Fornecedor f: n_fornecedores) {
			n_valuesFornecedor.add("");
			n_valuesFornecedor.set(t, f.getNome_fornecedor());
			t++;
		}
		TelaCadastro.updateList(list, n_valuesFornecedor);
	}
}