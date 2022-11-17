package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import controle.FornecedorBD;
import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Endereco;
import modelo.Fornecedor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class TelaPerfilFornecedor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPerfilFornecedor frame = new TelaPerfilFornecedor(null, null);
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
	UsuarioDAO usuarioDao = new UsuarioDAO();
	
	public TelaPerfilFornecedor(Fornecedor fornecedor, JList<String> lista) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clYellow = new Color(239, 161, 35);
		
		Font poppins, pop10 = null, pop12 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}

		UIManager.put("Button.select", Color.BLACK);
		
		setResizable(false);
		setTitle("Perfil - " + fornecedor.getNome_fornecedor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 249);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(29, 28, 629, 143);
		panel.setBorder(new RoundBorder());
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDelete = new JButton("");
		btnDelete.setFocusPainted(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("debug: tela Perfil > Deletar Usuari");
				new FornecedorBD().DeleteByID(fornecedor.getId_fornecedor());
				dispose();
				new Dialog("Fornecedor", "Fornecedor removido.", "info").setVisible(true);
			}
		});
		btnDelete.setIcon(new ImageIcon("./img/delete.png"));
		btnDelete.setOpaque(false);
		btnDelete.setBackground(null);
		btnDelete.setBorder(new RoundBorder(clRed, 1, 24));
		btnDelete.setFont(pop12);
		btnDelete.setForeground(clRed);
		btnDelete.setBounds(10, 109, 24, 24);
		panel.add(btnDelete);
		
		JButton btnModificar = new JButton("");
		btnModificar.setFocusPainted(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFornecedorModificar telaFornecedorModificar = new TelaFornecedorModificar(fornecedor.getId_fornecedor(), lista);
				telaFornecedorModificar.setVisible(true);
				setVisible(false);
				System.out.println("debug: tela Perfil > tela de Modificar");
				
			}
		});
		btnModificar.setIcon(new ImageIcon("./img/mini edit.png"));
		btnModificar.setOpaque(false);
		btnModificar.setBackground(null);
		btnModificar.setBorder(new RoundBorder(clBlue, 1, 24));
		btnModificar.setFont(pop12);
		btnModificar.setForeground(clBlue);
		btnModificar.setBounds(44, 109, 24, 24);
		panel.add(btnModificar);
		
		JLabel lblCargo = new JLabel("fornecedor #" + fornecedor.getId_fornecedor());
		//lblCargo.setText(cargo do usuario);
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(pop12);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(10, 36, 216, 14);
		panel.add(lblCargo);
		
		JLabel lblNome = new JLabel(fornecedor.getNome_fornecedor());
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(pop12);
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(10, 11, 216, 14);
		panel.add(lblNome);
		
		JLabel lblCPF = new JLabel("CNPJ");
		lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop12);
		lblCPF.setBounds(10, 61, 56, 14);
		panel.add(lblCPF);
		
		JLabel lblCPFInfo = new JLabel(fornecedor.getCnpj_fornecedor());
		lblCPFInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPFInfo.setForeground(Color.WHITE);
		lblCPFInfo.setFont(pop12);
		lblCPFInfo.setBounds(76, 61, 156, 14);
		panel.add(lblCPFInfo);
        
		JLabel lblEndereco = new JLabel("ENDERE\u00C7O(S)");
		lblEndereco.setForeground(new Color(197, 197, 197));
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		lblEndereco.setBounds(242, 11, 130, 14);
		panel.add(lblEndereco);
		
        JScrollPane endScrollPane = new JScrollPane();
		endScrollPane.setBounds(242, 29, 181, 103);
		Rolagem.defRolagem(endScrollPane);
		endScrollPane.setBorder(new RoundBorder());
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		panel.add(endScrollPane);
		
		JList<String> listaEndereco = new JList<String>();
		listaEndereco.setFocusable(false);
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(new Color(22,22,22));
		listaEndereco.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaEndereco.setBackground(new Color(22, 22, 22));
		listaEndereco.setForeground(new Color(197, 197, 197));
		listaEndereco.setFont(pop10);
		listaEndereco.setBounds(0, 50, 156, 113);
		endScrollPane.setViewportView(listaEndereco);
		
		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setForeground(new Color(197, 197, 197));
		lblContato.setFont(pop10);
		lblContato.setBounds(433, 11, 130, 14);
		panel.add(lblContato);
		
		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setForeground(Color.WHITE);
		Rolagem.defRolagem(cntScrollPane);
		cntScrollPane.setBorder(new RoundBorder());
		cntScrollPane.setBackground((Color) null);
		cntScrollPane.setForeground(null);
		cntScrollPane.setBounds(433, 29, 181, 103);
		panel.add(cntScrollPane);
		
		JList<String> listaContato = new JList<String>();
		listaContato.setFocusable(false);
		listaContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContato.setSelectionBackground(clYellow);
		listaContato.setSelectionForeground(new Color(22,22,22));
		listaContato.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaContato.setBackground(new Color(22, 22, 22));
		listaContato.setForeground(new Color(197, 197, 197));
		listaContato.setFont(pop10);
		listaContato.setBackground(new Color(22, 22, 22));
		cntScrollPane.setViewportView(listaContato);
		
		ArrayList<Endereco> enderecos = (ArrayList<Endereco>) new FornecedorBD().getEnderecos(fornecedor.getId_fornecedor());
		ArrayList<String> valuesE = new ArrayList<String>();
		
		for (Endereco e: enderecos) {
			valuesE.add(e.getCidade() + ", " + e.getBairro() + ", " + e.getRua() + " - " + e.getNumero());
		}
		
		TelaCadastro.updateList(listaEndereco, valuesE);
		
		ArrayList<Contato> contatos = (ArrayList<Contato>) new FornecedorBD().getContatos(fornecedor.getId_fornecedor());
		ArrayList<String> valuesC = new ArrayList<String>();
		
		for (Contato c: contatos) {
			valuesC.add(c.getEmail() + " / " + c.getTelefone());
		}
		
		TelaCadastro.updateList(listaContato, valuesC);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-495, -286, 1600, 861);
		contentPane.add(fakeBG);
	}
}
