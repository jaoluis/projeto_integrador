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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.Conexao;
import controle.ProdutoBD;
import controle.UsuarioDAO;
import modelo.Produto;
import modelo.Usuario;

public class TelaCadastroProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private final ButtonGroup cargoGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProduto frame = new TelaCadastroProduto();
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
	public TelaCadastroProduto() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		//Color clGreen = new Color(105, 122, 39);
		Color clGreen = new Color(168, 198, 51);

		Font poppins, pop10 = null, pop12 = null;

		try {

			poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
			pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

		setResizable(false);
		setTitle("Sistema de Vendas Ep\u00EDtome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 564);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(243, 48, 176, 466);
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEstamosQuaseL = new JLabel("Criar produto");
		lblEstamosQuaseL.setForeground(new Color(255, 255, 255));
		lblEstamosQuaseL.setFont(pop12);
		lblEstamosQuaseL.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstamosQuaseL.setBounds(10, 11, 156, 14);
		panel.add(lblEstamosQuaseL);

		JLabel lblCPF = new JLabel("MATERIAL");
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop10);
		lblCPF.setBounds(10, 216, 156, 14);
		panel.add(lblCPF);

		JTextField txtMaterial = new JTextField();
		txtMaterial.setForeground(new Color(255, 255, 255));
		txtMaterial.setBackground(new Color(45, 45, 45));
		txtMaterial.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtMaterial.setBounds(10, 230, 156, 20);
		txtMaterial.setFont(pop12);
		panel.add(txtMaterial);
		txtMaterial.setColumns(10);

		JTextField txtDimensoes = new JTextField();
		txtDimensoes.setForeground(new Color(255, 255, 255));
		txtDimensoes.setBackground(new Color(45, 45, 45));
		txtDimensoes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDimensoes.setBounds(10, 275, 156, 20);
		txtDimensoes.setFont(pop12);
		panel.add(txtDimensoes);
		txtDimensoes.setColumns(10);

		JLabel lblUsername = new JLabel("PREÇO DE CUSTO");
		lblUsername.setForeground(new Color(197, 197, 197));
		lblUsername.setFont(pop10);
		lblUsername.setBounds(10, 126, 156, 14);
		panel.add(lblUsername);

		JTextField txtPrecoCusto = new JTextField();
		txtPrecoCusto.setForeground(new Color(255, 255, 255));
		txtPrecoCusto.setBackground(new Color(45, 45, 45));
		txtPrecoCusto.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoCusto.setBounds(10, 140, 156, 20);
		txtPrecoCusto.setFont(pop12);
		panel.add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);

		JLabel lblNome = new JLabel("QUANTIDADE");
		lblNome.setForeground(new Color(197, 197, 197));
		lblNome.setFont(pop10);
		lblNome.setBounds(10, 171, 156, 14);
		panel.add(lblNome);

		JTextField txtQuantidade = new JTextField();
		txtQuantidade.setForeground(new Color(255, 255, 255));
		txtQuantidade.setBackground(new Color(45, 45, 45));
		txtQuantidade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtQuantidade.setBounds(10, 185, 156, 20);
		txtQuantidade.setFont(pop12);
		panel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		JLabel lblEmail = new JLabel("NOME");
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop10);
		lblEmail.setBounds(10, 36, 156, 14);
		panel.add(lblEmail);

		txtNome = new JTextField();
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setBackground(new Color(45, 45, 45));
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNome.setBounds(10, 50, 156, 20);
		txtNome.setFont(pop12);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblSenha = new JLabel("PREÇO DE VENDA");
		lblSenha.setForeground(new Color(197, 197, 197));
		lblSenha.setFont(pop10);
		lblSenha.setBounds(10, 81, 156, 14);
		panel.add(lblSenha);

		JTextField txtPrecoVenda = new JTextField();
		txtPrecoVenda.setForeground(new Color(255, 255, 255));
		txtPrecoVenda.setBackground(new Color(45, 45, 45));
		txtPrecoVenda.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPrecoVenda.setBounds(10, 95, 156, 20);
		txtPrecoVenda.setFont(pop12);
		panel.add(txtPrecoVenda);

		JLabel lblDataDeNascimento = new JLabel("DIMENSÕES");
		lblDataDeNascimento.setForeground(new Color(197, 197, 197));
		lblDataDeNascimento.setFont(pop10);
		lblDataDeNascimento.setBounds(10, 261, 156, 14);
		panel.add(lblDataDeNascimento);

		JLabel lblCargo = new JLabel("FORNECEDOR(ES)");
		lblCargo.setForeground(new Color(197, 197, 197));
		lblCargo.setBounds(10, 306, 156, 14);
		lblCargo.setFont(pop10);
		panel.add(lblCargo);

		JButton btnContinuar = new JButton("CADASTRAR");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("debug: tela de cadastro de produto > cadastrar produto");
				
				String nome = txtNome.getText();
				float precoVenda = 0f;
				float precoCusto = 0f;
				try {
					precoVenda = Float.parseFloat(txtPrecoVenda.getText());
					precoCusto = Float.parseFloat(txtPrecoCusto.getText());
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Preço(s) inválido(s)", "Erro", JOptionPane.ERROR_MESSAGE, null);
				}
				int qtd = Integer.parseInt(txtQuantidade.getText());
				String material = txtMaterial.getText();
				String dimensoes = txtDimensoes.getText();
				
					Produto produto = new Produto();
					produto.setNomeProduto(nome);
					produto.setPrecoCustoProduto(precoCusto);
					produto.setPrecoVendaProduto(precoVenda);
					produto.setDimencoesProduto(dimensoes);
					produto.setMaterialProduto(material);
					produto.setQuantidadeEstoque(qtd);
				
				ProdutoBD produtoBD = new ProdutoBD();
				long id = produtoBD.insert(produto);
				produtoBD.insert2(produto, id);
				
			}
		});
		btnContinuar.setOpaque(false);
		btnContinuar.setBackground(null);
		Chisel(btnContinuar, clGreen, 5);
		btnContinuar.setFont(pop12);
		btnContinuar.setBounds(10, 400, 156, 23);
		panel.add(btnContinuar);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-477, -224, 1600, 861);
		contentPane.add(fakeBG);
	}

	private static void panelbuttonChisel(JPanel panel, Color color, int radius) {
		
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

	private static void Chisel(JButton button, Color color, int radius) {

		button.setFocusPainted(false);
		button.setForeground(color);
		RoundedBorder LineBorder = new RoundedBorder(color, radius);
		Border emptyBorder = BorderFactory.createEmptyBorder(button.getBorder().getBorderInsets(button).top,
				button.getBorder().getBorderInsets(button).left, button.getBorder().getBorderInsets(button).bottom,
				button.getBorder().getBorderInsets(button).right);
		button.setBorder(BorderFactory.createCompoundBorder(LineBorder, emptyBorder));
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
