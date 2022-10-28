package visao;

import java.awt.Color;
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
		scrollPane.getVerticalScrollBar().setUI(new Rolagem());
		scrollPane.getHorizontalScrollBar().setBackground(new Rolagem().getBg());
		scrollPane.getHorizontalScrollBar().setUI(new Rolagem());
	}
	
	public static void defRolagem(JScrollPane scrollPane, Rolagem rolagemV, Rolagem rolagemH) {
		scrollPane.getVerticalScrollBar().setBackground(rolagemV.getBg());
		scrollPane.getVerticalScrollBar().setUI(rolagemV);
		scrollPane.getHorizontalScrollBar().setBackground(rolagemH.getBg());
		scrollPane.getHorizontalScrollBar().setUI(rolagemH);
	}

	@Override
    protected void configureScrollBarColors() {
        this.thumbColor = fg;
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(bg);
        button.setForeground(null);
        button.setSelectedIcon(null);
        button.setBorder(BorderFactory.createLineBorder(bg, 2));
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(bg);
        button.setForeground(null);
        button.setSelectedIcon(null);
        button.setBorder(BorderFactory.createLineBorder(bg, 2));
        return button;
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
