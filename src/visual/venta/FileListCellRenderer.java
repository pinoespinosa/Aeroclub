package visual.venta;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import data.Vuelo;

class FileListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -7799441088157759804L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean expanded) {

		FileListCellRenderer element = (FileListCellRenderer) super.getListCellRendererComponent(list, value, index, selected, expanded);

		Vuelo vuelo = (Vuelo) value;
		if (vuelo.getHoraFinal()==0)
			element.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_enVuelo.png")));
		else
			element.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_finVuelo.png")));
		element.setText(vuelo.toString());

		return element;
	}
}