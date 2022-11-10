package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controle.FornecedorBD;
import controle.UsuarioDAO;
import modelo.Contato;
import modelo.Endereco;
import modelo.Fornecedor;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
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
					TelaPerfilFornecedor frame = new TelaPerfilFornecedor(null);
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
	
	public TelaPerfilFornecedor(Fornecedor fornecedor) {
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
		
		setResizable(false);
		setTitle("Perfil - " + fornecedor.getNome_fornecedor());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 708, 249);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(29, 28, 629, 143);
		panelbuttonChisel(panel, Color.WHITE, 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("debug: tela Perfil > Deletar Usuari");
				new FornecedorBD().DeleteByID(fornecedor.getId_fornecedor());
				setVisible(false);
			}
		});
		btnDelete.setIcon(new ImageIcon("./img/delete.png"));
		btnDelete.setOpaque(false);
		btnDelete.setBackground(null);
		Chisel(btnDelete, clRed, 5);
		btnDelete.setFont(pop12);
		btnDelete.setForeground(clRed);
		btnDelete.setBounds(10, 109, 23, 23);
		panel.add(btnDelete);
		
		JButton btnModificar = new JButton("");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFornecedorModificar telaFornecedorModificar = new TelaFornecedorModificar(fornecedor.getId_fornecedor());
				telaFornecedorModificar.setVisible(true);
				setVisible(false);
				System.out.println("debug: tela Perfil > tela de Modificar");
				
			}
		});
		btnModificar.setIcon(new ImageIcon("./img/mini edit.png"));
		btnModificar.setOpaque(false);
		btnModificar.setBackground(null);
		Chisel(btnModificar, clBlue, 5);
		btnModificar.setFont(pop12);
		btnModificar.setForeground(clBlue);
		btnModificar.setBounds(43, 109, 23, 23);
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
		lblCPF.setBounds(10, 84, 56, 14);
		panel.add(lblCPF);
		
		JLabel lblCPFInfo = new JLabel(fornecedor.getCnpj_fornecedor());
		lblCPFInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPFInfo.setForeground(Color.WHITE);
		lblCPFInfo.setFont(pop12);
		lblCPFInfo.setBounds(76, 84, 150, 14);
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
		scrollChisel(endScrollPane, Color.WHITE, 5);
		endScrollPane.setBackground(null);
		endScrollPane.setForeground(null);
		panel.add(endScrollPane);
		
		JList<String> listaEndereco = new JList<String>();
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
		scrollChisel(cntScrollPane, Color.WHITE, 5);
		cntScrollPane.setBackground((Color) null);
		cntScrollPane.setForeground(null);
		cntScrollPane.setBounds(433, 29, 181, 103);
		panel.add(cntScrollPane);
		
		JList<String> listaContato = new JList<String>();
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
	



	private void scrollChisel(JScrollPane scrollPane, Color color, int i) {
		scrollPane.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, i);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
		
	}





private void Chisel(JButton button, Color color, int radius) {
	button.setFocusPainted(false);
	button.setForeground(color);
	RoundedBorder LineBorder = new RoundedBorder(color, radius);
	Border emptyBorder = BorderFactory.createEmptyBorder();
	button.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
		
	}

private static void panelbuttonChisel(JPanel panel, Color color, int radius) {
		
        //panel.setFocusPainted(false);
        panel.setForeground(color);
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
        Border emptyBorder = BorderFactory.createEmptyBorder(417, 124, 417, 124);
        panel.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
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
