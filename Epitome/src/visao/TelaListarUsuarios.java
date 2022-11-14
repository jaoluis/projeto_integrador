package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarUsuarios frame = new TelaListarUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListarUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clYellow = new Color(239, 161, 35);

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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 359, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(50, 11, 242, 488);
		contentPane.add(panel);
		panelChisel(panel, Color.WHITE, 5);
		panel.setLayout(null);
		
		JLabel lblUsuarios = new JLabel("USU\u00C1RIOS");
		lblUsuarios.setForeground(new Color(197, 197, 197));
		lblUsuarios.setFont(null);
		lblUsuarios.setFont(pop10);
		lblUsuarios.setBounds(10, 15, 130, 14);
		panel.add(lblUsuarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 174, 445);
		Rolagem.defRolagem(scrollPane);
		scrollChisel(scrollPane, Color.WHITE, 5);
		scrollPane.setBackground(null);
		scrollPane.setForeground(null);
		panel.add(scrollPane);
		
		usuarios = (ArrayList<Usuario>) new UsuarioDAO().getListarUsuarios();
		
		JList<String> listaUsuario = new JList<String>();
		listaUsuario.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listaUsuario.getSelectedIndex() == -1) {
					return;
				}
			}
		});
		listaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuario.setSelectionBackground(clYellow);
		listaUsuario.setSelectionForeground(new Color(22,22,22));
		listaUsuario.setModel(new AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;
			public int getSize() {
				return new String[] {}.length;
			}
			public String getElementAt(int index) {
				return new String[] {}[index];
			}
		});
		listaUsuario.setBackground(new Color(22, 22, 22));
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
				TelaCadastro telaCadastro = new TelaCadastro();
				telaCadastro.setVisible(true);
			}
		});

		btnCadastrar.setBorder(BorderFactory.createEmptyBorder());
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
				Usuario u = usuarios.get(listaUsuario.getSelectedIndex());
				TelaPerfilADM tl = new TelaPerfilADM(u);
				tl.setVisible(true);
			}
		});
		btnDetalhar.setForeground(Color.WHITE);
		btnDetalhar.setBorder(BorderFactory.createEmptyBorder());
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				usuarios = (ArrayList<Usuario>) new UsuarioDAO().getListarUsuarios();
				valuesUsuario.clear();
				
				int t = 0;
				for (Usuario u: usuarios) {
					valuesUsuario.add("");
					valuesUsuario.set(t, u.getNome_usuario());
					t++;
				}
				TelaCadastro.updateList(listaUsuario, valuesUsuario);
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setIcon(new ImageIcon("./img/refresh.png"));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBorder(BorderFactory.createEmptyBorder());
		btnRefresh.setBackground((Color) null);
		btnRefresh.setBounds(194, 126, 36, 36);
		panel.add(btnRefresh);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-281, -138, 968, 663);
		contentPane.add(fakeBG);
		
		}

	private void scrollChisel(JScrollPane scrollPane, Color color, int i) {
		scrollPane.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, i);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}

	private static void panelChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(417, 124, 417, 124);
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
	}

	protected MaskFormatter def_mask(String envolucro, char substituto) {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(envolucro);
			mask.setPlaceholderCharacter(substituto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mask;
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
