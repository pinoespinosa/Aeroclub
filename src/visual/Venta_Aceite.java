package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import extended.JDialogExtended;

public class Venta_Aceite extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Venta_Aceite(final JFrame parent) {
		super(parent);
		
		setResizable(false);
	
		setTitle("Venta de Aceite");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblSocio = new JLabel("Destinatario");
			lblSocio.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_lblSocio = new GridBagConstraints();
			gbc_lblSocio.anchor = GridBagConstraints.WEST;
			gbc_lblSocio.insets = new Insets(0, 0, 5, 5);
			gbc_lblSocio.gridx = 1;
			gbc_lblSocio.gridy = 1;
			contentPanel.add(lblSocio, gbc_lblSocio);
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
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 2;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblCantidadlitros = new JLabel("Cantidad (Litros)");
			lblCantidadlitros.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_lblCantidadlitros = new GridBagConstraints();
			gbc_lblCantidadlitros.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidadlitros.anchor = GridBagConstraints.WEST;
			gbc_lblCantidadlitros.gridx = 1;
			gbc_lblCantidadlitros.gridy = 3;
			contentPanel.add(lblCantidadlitros, gbc_lblCantidadlitros);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 3;
			contentPanel.add(spinner, gbc_spinner);
		}
		{
			JButton btnRegistrarVenta = new JButton("Registrar venta");
			GridBagConstraints gbc_btnRegistrarVenta = new GridBagConstraints();
			gbc_btnRegistrarVenta.insets = new Insets(0, 0, 0, 5);
			gbc_btnRegistrarVenta.gridx = 2;
			gbc_btnRegistrarVenta.gridy = 4;
			contentPanel.add(btnRegistrarVenta, gbc_btnRegistrarVenta);
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
