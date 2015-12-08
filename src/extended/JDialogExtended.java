package extended;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;



public class JDialogExtended extends JDialog {
	private JFrame parent;
	private int action;

	/**
	 * Create the dialog.
	 * @param frmSistemaDeGestin 
	 */
	public JDialogExtended(final JFrame parent) {
		super(parent);
		setResizable(false);
		this.parent=parent;	
		action = MainController.ACTION_EXIT;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				switch (action) {
					case MainController.ACTION_EXIT:
					{		
						parent.setEnabled(true);
						parent.setVisible(true);
					}		
						break;
					default:					
						break;
				}
			}
		});
		
	
		setBounds(100, 100, 637, 443);
		
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
