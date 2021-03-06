package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import extended.JDialogExtended;
import extended.MainController;


public class Venta extends JDialogExtended {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
 
	/**
	 * Create the dialog.
	 */
	public Venta(final JFrame parent) {
		super(parent);
		setTitle("Tipos de venta");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[]{32, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton btnVuelo = new JButton("Nuevo Vuelo");
			btnVuelo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {	
					Venta_Vuelo dialog = new Venta_Vuelo(parent);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					setAction(MainController.ACTION_CONTINUE);
					Venta.this.dispose();					
				}
			});
			btnVuelo.setFont(MainController.getDefaultFont(MainController.BUTTON));
			GridBagConstraints gbc_btnVuelo = new GridBagConstraints();
			gbc_btnVuelo.insets = new Insets(0, 0, 5, 0);
			gbc_btnVuelo.fill = GridBagConstraints.BOTH;
			gbc_btnVuelo.gridx = 0;
			gbc_btnVuelo.gridy = 0;
			contentPanel.add(btnVuelo, gbc_btnVuelo);
		}
		{
			JButton btnAceite = new JButton("Nuevo Venta de Aceite");
			btnAceite.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Venta_Aceite dialog = new Venta_Aceite(parent);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					setAction(MainController.ACTION_CONTINUE);
					Venta.this.dispose();	
				}
			});
			GridBagConstraints gbc_btnAceite = new GridBagConstraints();
			btnAceite.setFont(MainController.getDefaultFont(MainController.BUTTON));
			gbc_btnAceite.fill = GridBagConstraints.BOTH;
			gbc_btnAceite.insets = new Insets(0, 0, 5, 0);
			gbc_btnAceite.gridx = 0;
			gbc_btnAceite.gridy = 1;
			contentPanel.add(btnAceite, gbc_btnAceite);
		}
		{
			JButton btnCombustible = new JButton("Nuevo Venta de Combustible");
			btnCombustible.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Venta_Combustible dialog = new Venta_Combustible(parent);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					setAction(MainController.ACTION_CONTINUE);
					Venta.this.dispose();	
					
				}
			});
			btnCombustible.setFont(MainController.getDefaultFont(MainController.BUTTON));
			GridBagConstraints gbc_btnCombustibleAceite = new GridBagConstraints();
			gbc_btnCombustibleAceite.fill = GridBagConstraints.BOTH;
			gbc_btnCombustibleAceite.gridx = 0;
			gbc_btnCombustibleAceite.gridy = 2;
			contentPanel.add(btnCombustible, gbc_btnCombustibleAceite);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inic();
	}
	@PostConstruct 
	private void inic()
	{
		setAction(MainController.ACTION_EXIT);
	}
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
