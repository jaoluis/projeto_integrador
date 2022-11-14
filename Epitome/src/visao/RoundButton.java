package visao;

import javax.swing.JButton;
import java.awt.*;

public class RoundButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor = Color.BLACK;
	
	public RoundButton() {
	    this(null);
	}
	
	public RoundButton(String text) {
	    super(text);
	    super.setFocusPainted(false);
	    super.setContentAreaFilled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    if (getModel().isPressed()) {
	        g.setColor(pressedBackgroundColor);
//	    } else if (getModel().isRollover()) {
//	        g.setColor(hoverBackgroundColor);
	    } else {
	        g.setColor(getBackground());
	    }
	    g.fillRect(0, 0, getWidth(), getHeight());
	    super.paintComponent(g);
	}
	
	@Override
	public void setContentAreaFilled(boolean b) {
	}
	
//	public Color getHoverBackgroundColor() {
//	    return hoverBackgroundColor;
//	}
//	
//	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
//	    this.hoverBackgroundColor = hoverBackgroundColor;
//	}
	
	public Color getPressedBackgroundColor() {
	    return pressedBackgroundColor;
	}
	
	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
	    this.pressedBackgroundColor = pressedBackgroundColor;
	}
}