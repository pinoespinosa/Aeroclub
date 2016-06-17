package visual.venta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import data.Instructor;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;

public class Pago_Instructor extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner montoPago;
	private JComboBox<Instructor> instructoresComboBox;
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Pago_Instructor(Window parent) {
		super(parent);
		setTitle("Pago a instructores");
		setBounds(100, 100, 469, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{

			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;

		}
		{
			JLabel lblDestinatario = new JLabel("Intructor");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.anchor = GridBagConstraints.EAST;
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 2;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			instructoresComboBox = new JComboBox<Instructor>();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 2;
			contentPanel.add(instructoresComboBox, gbc_comboBox);
		}
		{
			JLabel lblFormaDePago = new JLabel("Forma de pago");
			GridBagConstraints gbc_lblFormaDePago = new GridBagConstraints();
			gbc_lblFormaDePago.anchor = GridBagConstraints.EAST;
			gbc_lblFormaDePago.insets = new Insets(0, 0, 5, 5);
			gbc_lblFormaDePago.gridx = 1;
			gbc_lblFormaDePago.gridy = 3;
			contentPanel.add(lblFormaDePago, gbc_lblFormaDePago);
		}
		{
			montoPago = new JSpinner();
			montoPago.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			GridBagConstraints gbc_montoPago = new GridBagConstraints();
			gbc_montoPago.fill = GridBagConstraints.HORIZONTAL;
			gbc_montoPago.insets = new Insets(0, 0, 5, 5);
			gbc_montoPago.gridx = 2;
			gbc_montoPago.gridy = 3;
			contentPanel.add(montoPago, gbc_montoPago);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar pago");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Instructor select = (Instructor)instructoresComboBox.getSelectedItem();
						
						managerDB.executeScript_Void("INSERT INTO " + MainController.getEsquema() + ".`gasto` VALUES ('" + managerDB.getNextId("gasto") + "','VENTA', 'EXTRACCION DE DINERO DE CUENTA CORRIENTE', '" + montoPago.getValue()+ "', '" + 0 + "','','" + montoPago.getValue() + "','" + Precios.TYPE_PAGO.CUENTA_CORRIENTE.ordinal() + "','" + System.currentTimeMillis() + "','"+ select.getId()+"');");
						managerDB.executeScript_Void("INSERT INTO " + MainController.getEsquema() + ".`gasto` VALUES ('" + managerDB.getNextId("gasto") + "','COMPRA', 'EXTRACC C CORRIENTE "+ select.toString().substring(0,Math.min(20, select.toString().length())) +"', '" + montoPago.getValue()+ "', '" + 0 + "','','" + montoPago.getValue() + "','" + Precios.TYPE_PAGO.EFECTIVO.ordinal() + "','" + System.currentTimeMillis() + "','"+ select.getId()+"');");
						JOptionPane.showMessageDialog(null, "Se registro exitosamente el pago al instructor.");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inic();
	}

	private void inic() {
		
		List<Instructor> intruct = Instructor.loadFromDB();
		
		for (Instructor instructor : intruct) {
			instructoresComboBox.addItem(instructor);
		}
		
		
	}

	@Override
	public void updateUi() {

	}

}
