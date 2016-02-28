package extended;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;



public abstract class JDialogExtended extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int action;

	/**
	 * Create the dialog.
	 * @param frmSistemaDeGestin 
	 */
	public JDialogExtended(final Window parent) {
		super(parent);
		setResizable(false);
		action = getAction();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				switch (action) {
					case MainController.ACTION_REACTIVAR_PADRE:
					{		
						parent.setEnabled(true);
						parent.setVisible(true);
						}
					break;
					
					default:					
						break;
				}
				
				if (parent!=null  && parent instanceof JDialogExtended)
					((JDialogExtended) parent).updateUi();
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
	
	public abstract void updateUi();

}
