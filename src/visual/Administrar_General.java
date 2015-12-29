package visual;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

import extended.JDialogExtended;


public class Administrar_General extends JDialogExtended {

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Administrar_General(JFrame parent) {
		super(parent);
		setTitle("Nueva Compra");
		setBounds(100, 100, 878, 559);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{444, 0};
		gridBagLayout.rowHeights = new int[]{67, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
			gbc_tabbedPane.gridx = 0;
			gbc_tabbedPane.gridy = 0;
			getContentPane().add(tabbedPane, gbc_tabbedPane);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Precios Aviones", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panel.rowHeights = new int[]{20, 0, 0, 0};
				gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JLabel lblPrecioCombustible = new JLabel("Precio Combustible - Socio");
					GridBagConstraints gbc_lblPrecioCombustible = new GridBagConstraints();
					gbc_lblPrecioCombustible.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecioCombustible.gridx = 1;
					gbc_lblPrecioCombustible.gridy = 1;
					panel.add(lblPrecioCombustible, gbc_lblPrecioCombustible);
				}
				{
					JSpinner spinner = new JSpinner();
					GridBagConstraints gbc_spinner = new GridBagConstraints();
					gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinner.insets = new Insets(0, 0, 5, 0);
					gbc_spinner.gridx = 2;
					gbc_spinner.gridy = 1;
					panel.add(spinner, gbc_spinner);
				}
				{
					JLabel lblPrecioCombustible_1 = new JLabel("Precio Combustible - Aeroclub");
					GridBagConstraints gbc_lblPrecioCombustible_1 = new GridBagConstraints();
					gbc_lblPrecioCombustible_1.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible_1.insets = new Insets(0, 0, 0, 5);
					gbc_lblPrecioCombustible_1.gridx = 1;
					gbc_lblPrecioCombustible_1.gridy = 2;
					panel.add(lblPrecioCombustible_1, gbc_lblPrecioCombustible_1);
				}
				{
					JSpinner spinner = new JSpinner();
					GridBagConstraints gbc_spinner = new GridBagConstraints();
					gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinner.gridx = 2;
					gbc_spinner.gridy = 2;
					panel.add(spinner, gbc_spinner);
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("New tab", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0};
				gbl_panel.rowHeights = new int[]{0, 0};
				gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JButton btnNewButton = new JButton("New button");
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.gridx = 0;
					gbc_btnNewButton.gridy = 0;
					panel.add(btnNewButton, gbc_btnNewButton);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
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
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
