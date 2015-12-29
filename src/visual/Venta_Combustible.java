package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import extended.JDialogExtended;


public class Venta_Combustible extends JDialogExtended {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Venta_Combustible(JFrame parent) {
		super(parent);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTipoDeVenta = new JLabel("Tipo de Venta");
			GridBagConstraints gbc_lblTipoDeVenta = new GridBagConstraints();
			gbc_lblTipoDeVenta.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeVenta.gridx = 1;
			gbc_lblTipoDeVenta.gridy = 1;
			contentPanel.add(lblTipoDeVenta, gbc_lblTipoDeVenta);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblDestinatario = new JLabel("Destinatario");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.anchor = GridBagConstraints.EAST;
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 2;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			JComboBox comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 2;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(0, 0, 0, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 3;
			contentPanel.add(lblCantidad, gbc_lblCantidad);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 0, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 3;
			contentPanel.add(spinner, gbc_spinner);
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
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
