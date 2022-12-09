package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controle.UsuarioDAO;
import modelo.Usuario;

public class TelaListarUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<String> valuesUsuario = new ArrayList<String>();


	public TelaListarUsuarios() {
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
		setResizable(false);
		setTitle("Usu\u00E1rios");
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
		
		JLabel lblUsuarios = new JLabel("USU\u00C1RIOS");
		lblUsuarios.setForeground(clLighter);
		lblUsuarios.setFont(null);
		lblUsuarios.setFont(pop10);
		lblUsuarios.setBounds(10, 15, 130, 14);
		panel.add(lblUsuarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 174, 445);
		Rolagem.defRolagem(scrollPane);
		scrollPane.setBorder(new RoundBorder());
		scrollPane.setBackground(null);
		scrollPane.setForeground(null);
		panel.add(scrollPane);
		
		usuarios = (ArrayList<Usuario>) new UsuarioDAO().getListarUsuarios();
		
		JList<String> listaUsuario = new JList<String>();
		listaUsuario.setFocusable(false);
		listaUsuario.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listaUsuario.getSelectedIndex() == -1) {
					return;
				}
			}
		});
		listaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuario.setSelectionBackground(clYellow);
		listaUsuario.setSelectionForeground(clDark);
		listaUsuario.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaUsuario.setBackground(clDark);
		listaUsuario.setForeground(Color.WHITE);
		listaUsuario.setFont(pop12);
		listaUsuario.setBounds(0, 50, 156, 113);
		scrollPane.setViewportView(listaUsuario);
		
		int t = 0;
		for (Usuario u: usuarios) {
			valuesUsuario.add("");
			valuesUsuario.set(t, u.getNome_usuario());
			t++;
		}
		TelaCadastro.updateList(listaUsuario, valuesUsuario);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.setBounds(194, 32, 36, 36);
		panel.add(btnCadastrar);
		btnCadastrar.setBackground(null);
		btnCadastrar.setFocusPainted(false);
		btnCadastrar.setIcon(new ImageIcon("./img/add.png"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastro telaCadastro = new TelaCadastro(listaUsuario);
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
				if (listaUsuario.getSelectedIndex() == -1) {
					return;
				}
				
				usuarios = (ArrayList<Usuario>) new UsuarioDAO().getListarUsuarios();
				valuesUsuario.clear();
				
				Usuario u = usuarios.get(listaUsuario.getSelectedIndex());
				TelaPerfilADM tl = new TelaPerfilADM(u, listaUsuario);
				tl.setVisible(true);
			}
		});
		btnDetalhar.setBorder(new RoundBorder(clDark, 1, 36));
		btnDetalhar.setForeground(Color.WHITE);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-281, -138, 968, 663);
		contentPane.add(fakeBG);
		
		
		setLocationRelativeTo(null);
		}
	
	static public void refresh(JList<String> list) {
		ArrayList<Usuario> n_usuarios = (ArrayList<Usuario>) new UsuarioDAO().getListarUsuarios();
		ArrayList<String> n_valuesUsuario = new ArrayList<String>();
		
		int t = 0;
		for (Usuario u: n_usuarios) {
			n_valuesUsuario.add("");
			n_valuesUsuario.set(t, u.getNome_usuario());
			t++;
		}
		TelaCadastro.updateList(list, n_valuesUsuario);
	}
}
