package visual.venta;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import data.Vuelo;

class FileListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -7799441088157759804L;
	private JLabel label;
	private Color textSelectionColor = Color.BLACK;
	private Color backgroundSelectionColor = Color.CYAN;
	private Color textNonSelectionColor = Color.BLACK;
	private Color backgroundNonSelectionColor = Color.WHITE;

	FileListCellRenderer() {
		label = new JLabel();
		label.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean expanded) {

		FileListCellRenderer aa = (FileListCellRenderer) super.getListCellRendererComponent(list, value, index, selected, expanded);
		
		
		Vuelo vuelo = (Vuelo) value;
		aa.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_vuelo.png")));
		aa.setText(vuelo.toString());
	//	label.setToolTipText(file.getPath());
/*
		if (selected) {
			label.setBackground(backgroundSelectionColor);
			label.setForeground(textSelectionColor);
		} else {
			label.setBackground(backgroundNonSelectionColor);
			label.setForeground(textNonSelectionColor);
		}
*/
		return aa;
	}
}