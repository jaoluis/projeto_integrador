package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
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
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JComboBox;

public class TelaEstoque extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstoque frame = new TelaEstoque();
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
	public TelaEstoque() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		Color clGreen = new Color(105, 122, 39);
		
		
		Font poppins, pop10 = null, pop12 = null, pop24 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
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
		panelChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnRelatorio = new JButton("Relatório de Vendas");
		btnRelatorio.setFont(pop10);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela inicial adm > relatório de vendas");
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
				System.out.println("debug: tela inicial adm > sair");
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
				System.out.println("debug: tela inicial adm > perfil");
				TelaPerfil telaPerfil = new TelaPerfil();
				telaPerfil.setVisible(true);
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
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(pop12);
		
		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de estoque > tela inicial");
				TelaInicialADM telainicial = new TelaInicialADM();
				telainicial.setVisible(true);
				setVisible(false);
			}
		});
		btnReturn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnReturn.setIcon(new ImageIcon("./img/return.png"));
		btnReturn.setForeground(null);
		btnReturn.setBackground(null);
		btnReturn.setBounds(10, 11, 27, 45);
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
		btnSearch.setBounds(47, 67, 25, 25);
		contentPane.add(btnSearch);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstoque.setForeground(Color.WHITE);
		lblEstoque.setFont(pop24);
		lblEstoque.setBounds(47, 11, 252, 45);
		contentPane.add(lblEstoque);
		
		JTextField txtSearch = new JTextField();
		txtSearch.setForeground(new Color(255, 255, 255));
		txtSearch.setBackground(new Color(22, 22, 22));
		txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSearch.setBounds(80, 67, 202, 25);
		txtSearch.setFont(pop12);
		fieldChisel(txtSearch, new Color(255, 255, 255), 5);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		/*
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(0, 0, 1600, 861);
		contentPane.add(fakeBG);*/
		
	}
	
	private static void buttonChisel(JButton button, Color color, int radius) {
        RoundedBorder LineBorder = new RoundedBorder(color, radius);
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
