package visao;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Rolagem extends BasicScrollBarUI{
	
	private Color fg = new Color(197, 197, 197);
	private Color bg = new Color(22, 22, 22);
	

	public Rolagem() {
		this.fg = new Color(197, 197, 197);
		this.bg = new Color(22, 22, 22);
	}
	
	public Rolagem(Color fg, Color bg) {
		super();
		this.fg = fg;
		this.bg = bg;
	}
	
	public static void defRolagem(JScrollPane scrollPane) {
		scrollPane.getVerticalScrollBar().setBackground(new Rolagem().getBg());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getVerticalScrollBar().setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 1));
		
		scrollPane.getVerticalScrollBar().setUI(new Rolagem());
		
		scrollPane.getHorizontalScrollBar().setBackground(new Rolagem().getBg());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
		scrollPane.getHorizontalScrollBar().setBorder(BorderFactory.createEmptyBorder(2, 0, 1, 0));
		scrollPane.getHorizontalScrollBar().setUI(new Rolagem());
	}

	@Override
    protected void configureScrollBarColors() {
        this.thumbColor = fg;
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
//        JButton button = super.createDecreaseButton(orientation);
//        button.setBackground(bg);
//        button.setForeground(null);
//        button.setSelectedIcon(null);
//        button.setBorder(BorderFactory.createLineBorder(bg, 2));
//        return button;
    	return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
//        JButton button = super.createIncreaseButton(orientation);
//        button.setBackground(bg);
//        button.setForeground(null);
//        button.setSelectedIcon(null);
//        button.setBorder(BorderFactory.createLineBorder(bg, 2));
//        return button;
    	return createZeroButton();
    }
    
    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }

	public Color getFg() {
		return fg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}
}
