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

public class TelaPerfil extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPerfil frame = new TelaPerfil();
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
	public TelaPerfil() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/app_icon_small.png"));
		Color clRed = new Color(226, 0, 54);
		Color clBlue = new Color(113, 206, 236);
		
		Font poppins, pop10 = null, pop12 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop10 = poppins.deriveFont(Font.TRUETYPE_FONT, 10);
			pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 515, 249);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(22, 22, 22));
		panel.setBounds(29, 28, 433, 143);
		panelbuttonChisel(panel, new Color(255, 255, 255), 5);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCargo = new JLabel("Cargo");
		//lblCargo.setText(cargo do usuario);
		lblCargo.setForeground(new Color(255, 255, 255));
		lblCargo.setFont(pop12);
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(10, 11, 156, 14);
		panel.add(lblCargo);
		
		JLabel lblID = new JLabel("#000000");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		//lblID.setText(id do usuario);
		lblID.setForeground(new Color(255, 255, 255));
		lblID.setFont(pop12);
		lblID.setBounds(10, 36, 156, 14);
		panel.add(lblID);
		
		JLabel lblNome = new JLabel("Fulano da Silva");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(pop12);
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(192, 11, 231, 14);
		panel.add(lblNome);
		
		JLabel lblEmailInfo = new JLabel("Fulano da Silva");
		lblEmailInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailInfo.setForeground(Color.WHITE);
		lblEmailInfo.setFont(pop12);
		lblEmailInfo.setBounds(128, 61, 295, 14);
		panel.add(lblEmailInfo);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(new Color(197, 197, 197));
		lblEmail.setFont(pop12);
		lblEmail.setBounds(10, 61, 108, 14);
		panel.add(lblEmail);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPF.setForeground(new Color(197, 197, 197));
		lblCPF.setFont(pop12);
		lblCPF.setBounds(10, 86, 108, 14);
		panel.add(lblCPF);
		
		JLabel lblCPFInfo = new JLabel("000.000.000-00");
		lblCPFInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPFInfo.setForeground(Color.WHITE);
		lblCPFInfo.setFont(pop12);
		lblCPFInfo.setBounds(128, 86, 295, 14);
		panel.add(lblCPFInfo);
		
		JLabel lblNascimento = new JLabel("NASCIMENTO");
		lblNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNascimento.setForeground(new Color(197, 197, 197));
		lblNascimento.setFont(pop12);
		lblNascimento.setBounds(10, 111, 108, 14);
		panel.add(lblNascimento);
		
		JLabel lblNacimentoInfo = new JLabel("00/00/0000");
		lblNacimentoInfo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNacimentoInfo.setForeground(Color.WHITE);
		lblNacimentoInfo.setFont(pop12);
		lblNacimentoInfo.setBounds(128, 111, 295, 14);
		panel.add(lblNacimentoInfo);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(-495, -286, 1600, 861);
		contentPane.add(fakeBG);
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
