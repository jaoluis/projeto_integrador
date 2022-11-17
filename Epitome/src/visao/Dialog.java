package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.SwingConstants;

public class Dialog extends JFrame {

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
					Dialog frame = new Dialog("Teste","teste","info");
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
	public Dialog(String title, String message, String icon) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/"+icon+".png"));
		setResizable(false);
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 320, 130);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Font poppins, pop12 = null;
		
		try {
			  
		    poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./font/Poppins-SemiBold.ttf"));
		    pop12 = poppins.deriveFont(Font.TRUETYPE_FONT, 12);
		  
		} catch (Exception e) {
		  e.printStackTrace();
		}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIMG = new JLabel("");
		lblIMG.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIMG.setHorizontalAlignment(SwingConstants.CENTER);
		lblIMG.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblIMG.setIcon(new ImageIcon("./img/"+icon+".png"));
		lblIMG.setBounds(10, 36, 40, 40);
//		lblIMG.setBorder(new RoundBorder(Color.WHITE, 2, 40));
		contentPane.add(lblIMG);
		
		JLabel lblMessage = new JLabel(message);
		lblMessage.setBounds(66, 49, 228, 14);
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(pop12);
		contentPane.add(lblMessage);
		
		RoundButton btnOK = new RoundButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOK.setBackground(null);
		btnOK.setForeground(Color.WHITE);
		btnOK.setBorder(new RoundBorder());
		btnOK.setFont(pop12);
		btnOK.setBounds(114, 87, 89, 23);
		contentPane.add(btnOK);
		
		JLabel fakeBorder = new JLabel("");
		fakeBorder.setBorder(new RoundBorder());
		fakeBorder.setBounds(getBounds());
		contentPane.add(fakeBorder);
		
		JLabel fakeBG = new JLabel("");
		fakeBG.setIcon(new ImageIcon("./img/bg.png"));
		fakeBG.setBounds(0, -600, 1600, 861);
		contentPane.add(fakeBG);
		
		setLocationRelativeTo(null);
		setUndecorated(true);
	}
}
