package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Endereco;
import modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import java.util.ArrayList;

public class TelaPerfilADM extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private JPanel contentPane;

	UsuarioDAO usuarioDao = new UsuarioDAO();

	public TelaPerfilADM(Usuario usuarioLogado, JList<String> lista) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clYellow = new Color(239, 161, 35);
		Color clDark = new Color(22, 22, 22);
		Color clLight = new Color(45, 45, 45);
		Color clLighter = new Color(197, 197, 197);

		Font poppins, pop12 = null, pop10 = null;

		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);

		} catch (Exception e) {
			e.printStackTrace();
		}

		setResizable(false);
		setTitle("Perfil - " + usuarioLogado.getNome_usuario());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 832, 249);
		contentPane = new JPanel();
		contentPane.setBackground(clLight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(clDark);
		panel.setBounds(29, 28, 760, 143);
		panel.setBorder(new RoundBorder());
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnDelete = new JButton("");
		btnDelete.setFocusPainted(false);
		btnDelete.setIcon(new ImageIcon("./img/delete.png"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("debug: tela Perfil > Deletar Usuari");
				id = usuarioLogado.getId_usuario();
				// chama metodo

				usuarioDao.DeleteByID(id);

				if (lista != null) {
					TelaListarUsuarios.refresh(lista);
				}

				dispose();
				new Dialog("Usu\u00E1rio", "Usu\u00E1rio removido.", "info").setVisible(true);
			}
		});
		btnDelete.setOpaque(false);
		btnDelete.setBackground(null);
		btnDelete.setBorder(new RoundBorder(clRed, 1, 24));
		btnDelete.setFont(pop12);
		btnDelete.setForeground(clRed);
		btnDelete.setBounds(311, 109, 24, 24);
		panel.add(btnDelete);

		JButton btnModificar = new JButton("");
		btnModificar.setFocusPainted(false);
		btnModificar.setIcon(new ImageIcon("./img/mini edit.png"));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaModificar telaModificar = new TelaModificar(usuarioLogado, usuarioLogado.getId_usuario(), lista);
				telaModificar.setVisible(true);
				setVisible(false);
				System.out.println("debug: tela Perfil > tela de Modificar");

			}
		});
		btnModificar.setOpaque(false);
		btnModificar.setBackground(null);
		btnModificar.setBorder(new RoundBorder(clBlue, 1, 24));
		btnModificar.setFont(pop12);
		btnModificar.setForeground(clBlue);
		btnModificar.setBounds(344, 109, 24, 24);
		panel.add(btnModificar);

		JLabel lblCargo = new JLabel(usuarioLogado.getCargo() + " #" + usuarioLogado.getId_usuario());
		// lblCargo.setText(cargo do usuario);
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(pop12);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(10, 36, 357, 14);
		panel.add(lblCargo);

		JLabel lblNome = new JLabel(usuarioLogado.getNome_usuario());
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(pop12);
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(10, 11, 357, 14);
		panel.add(lblNome);

		JLabel lblEmailInfo = new JLabel(usuarioLogado.getEmail());
		lblEmailInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailInfo.setForeground(Color.WHITE);
		lblEmailInfo.setFont(pop12);
		lblEmailInfo.setBounds(128, 61, 295, 14);
		panel.add(lblEmailInfo);

		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(clLighter);
		lblEmail.setFont(pop12);
		lblEmail.setBounds(10, 61, 108, 14);
		panel.add(lblEmail);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPF.setForeground(clLighter);
		lblCPF.setFont(pop12);
		lblCPF.setBounds(10, 86, 108, 14);
		panel.add(lblCPF);

		JLabel lblCPFInfo = new JLabel(usuarioLogado.getCpf_usuario());
		lblCPFInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPFInfo.setForeground(Color.WHITE);
		lblCPFInfo.setFont(pop12);
		lblCPFInfo.setBounds(128, 86, 295, 14);
		panel.add(lblCPFInfo);

		JLabel lblNascimento = new JLabel("NASCIMENTO");
		lblNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNascimento.setForeground(clLighter);
		lblNascimento.setFont(pop12);
		lblNascimento.setBounds(10, 111, 108, 14);
		panel.add(lblNascimento);
		Date dataAtual = usuarioLogado.getNascimento_data();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(dataAtual);
		JLabel lblNacimentoInfo = new JLabel(dataFormatada);
		lblNacimentoInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNacimentoInfo.setForeground(Color.WHITE);
		lblNacimentoInfo.setFont(pop12);
		lblNacimentoInfo.setBounds(128, 111, 295, 14);
		panel.add(lblNacimentoInfo);

		JLabel lblEndereco = new JLabel("ENDERE\u00C7O(S)");
		lblEndereco.setForeground(clLighter);
		lblEndereco.setFont(null);
		lblEndereco.setFont(pop10);
		lblEndereco.setBounds(377, 11, 130, 14);
		panel.add(lblEndereco);

		JScrollPane endScrollPane = new JScrollPane();
		endScrollPane.setBounds(377, 29, 181, 103);
		Rolagem.defRolagem(endScrollPane);
		endScrollPane.setBorder(new RoundBorder());
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		panel.add(endScrollPane);

		JList<String> listaEndereco = new JList<String>();
		listaEndereco.setFocusable(false);
		listaEndereco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaEndereco.setSelectionBackground(clYellow);
		listaEndereco.setSelectionForeground(clDark);
		listaEndereco.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;

			public int getSize() {
				return new String[] {}.length;
			}

			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaEndereco.setBackground(clDark);
		listaEndereco.setForeground(clLighter);
		listaEndereco.setFont(pop10);
		listaEndereco.setBounds(0, 50, 156, 113);
		endScrollPane.setViewportView(listaEndereco);

		JLabel lblContato = new JLabel("CONTATO(S)");
		lblContato.setForeground(clLighter);
		lblContato.setFont(pop10);
		lblContato.setBounds(568, 11, 130, 14);
		panel.add(lblContato);

		JScrollPane cntScrollPane = new JScrollPane();
		cntScrollPane.setForeground(Color.WHITE);
		Rolagem.defRolagem(cntScrollPane);
		cntScrollPane.setBorder(new RoundBorder());
		cntScrollPane.setBackground((Color) null);
		cntScrollPane.setForeground(null);
		cntScrollPane.setBounds(568, 29, 181, 103);
		panel.add(cntScrollPane);

		JList<String> listaContato = new JList<String>();
		listaContato.setFocusable(false);
		listaContato.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContato.setSelectionBackground(clYellow);
		listaContato.setSelectionForeground(clDark);
		listaContato.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;

			public int getSize() {
				return new String[] {}.length;
			}

			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaContato.setBackground(clDark);
		listaContato.setForeground(clLighter);
		listaContato.setFont(pop10);
		cntScrollPane.setViewportView(listaContato);

		ArrayList<Endereco> enderecos = (ArrayList<Endereco>) new UsuarioDAO()
				.getEnderecos(usuarioLogado.getId_usuario());
		ArrayList<String> valuesE = new ArrayList<String>();

		for (Endereco e : enderecos) {
			valuesE.add(e.getCidade() + ", " + e.getBairro() + ", " + e.getRua() + " - " + e.getNumero());
		}

		TelaCadastro.updateList(listaEndereco, valuesE);

		ArrayList<Contato> contatos = (ArrayList<Contato>) new UsuarioDAO().getContatos(usuarioLogado.getId_usuario());
		ArrayList<String> valuesC = new ArrayList<String>();

		for (Contato c : contatos) {
			valuesC.add(c.getEmail() + " / " + c.getTelefone());
		}

		TelaCadastro.updateList(listaContato, valuesC);

		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-495, -286, 1600, 861);
		contentPane.add(fakeBG);
		
		setLocationRelativeTo(null);
		
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setUndecorated(true);
	}
}
