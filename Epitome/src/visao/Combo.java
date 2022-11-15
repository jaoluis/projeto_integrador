package visao;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class Combo extends BasicComboBoxUI{
	
	private Color color = new Color(168, 198, 51);
	
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
    protected ComboPopup createPopup() {
        BasicComboPopup bcp = new BasicComboPopup(comboBox) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected JScrollPane createScroller() {
				list.setSelectionBackground(color);
				list.setSelectionForeground(new Color(22,22,22));
				list.setBackground(new Color(22, 22, 22));
				list.setForeground(new Color(197, 197, 197));
				list.setBorder(BorderFactory.createEmptyBorder());
                JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scroller.setBackground(null);
                scroller.setForeground(null);
                scroller.setBorder(BorderFactory.createEmptyBorder());
                Rolagem.defRolagem(scroller);
                return scroller;
            }
        };
        bcp.setBorder(BorderFactory.createEmptyBorder());
        bcp.setBorder(new LineBorder(Color.WHITE));
        
        return bcp;
    }
    
    @Override
    protected JButton createArrowButton() {
        return new JButton() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public int getWidth() {
                return 0;
            }
        };
    }
}
