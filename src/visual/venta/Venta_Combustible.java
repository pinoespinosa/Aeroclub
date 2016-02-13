package visual.venta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import base_datos.Utils;
import base_datos.managerDB;
import data.Persona;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;


public class Venta_Combustible extends JDialogExtended {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<Persona> destinatarios; 
	private JComboBox<String> tipoDeGasto;	
	private JTextField detalleTextField;
	private JSpinner cantidad, fechaVenta; 
	private JRadioButton rdbtnCuenta, rdbtnEfectivo;
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Venta_Combustible(JFrame parent) {
		super(parent);
		setTitle("Venta de Combustible/Aceite");
		setBounds(100, 100, 469, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFecha = new JLabel("Fecha");
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 1;
			gbc_lblFecha.gridy = 1;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			fechaVenta = new JSpinner();

			
			
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;
			contentPanel.add(fechaVenta, gbc_spinner);
		}
		{
			JLabel lblDestinatario = new JLabel("Destinatario");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 2;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			destinatarios = new JComboBox<Persona>();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 2;
			contentPanel.add(destinatarios, gbc_comboBox);
		}
		{
			JLabel lblTipoDeVenta = new JLabel("Tipo de Venta");
			GridBagConstraints gbc_lblTipoDeVenta = new GridBagConstraints();
			gbc_lblTipoDeVenta.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeVenta.gridx = 1;
			gbc_lblTipoDeVenta.gridy = 3;
			contentPanel.add(lblTipoDeVenta, gbc_lblTipoDeVenta);
		}
		{
			tipoDeGasto = new JComboBox();
			tipoDeGasto.setModel(new DefaultComboBoxModel(new String[] {"COMBUSTIBLE_PRECIO_AEROCLUB", "COMBUSTIBLE_PRECIO_SOCIO", "ACEITE_PRECIO_AEROCLUB", "ACEITE_PRECIO_SOCIO"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 3;
			contentPanel.add(tipoDeGasto, gbc_comboBox);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad (Litros)");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 4;
			contentPanel.add(lblCantidad, gbc_lblCantidad);
		}
		{
			cantidad = new JSpinner();
			cantidad.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.gridwidth = 2;
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 4;
			contentPanel.add(cantidad, gbc_spinner);
		}
		{
			JLabel lblDetalle = new JLabel("Detalle");
			GridBagConstraints gbc_lblDetalle = new GridBagConstraints();
			gbc_lblDetalle.insets = new Insets(0, 0, 5, 5);
			gbc_lblDetalle.gridx = 1;
			gbc_lblDetalle.gridy = 5;
			contentPanel.add(lblDetalle, gbc_lblDetalle);
		}
		{
			detalleTextField = new JTextField();
			GridBagConstraints gbc_detalleTextField = new GridBagConstraints();
			gbc_detalleTextField.gridwidth = 2;
			gbc_detalleTextField.insets = new Insets(0, 0, 5, 5);
			gbc_detalleTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_detalleTextField.gridx = 2;
			gbc_detalleTextField.gridy = 5;
			contentPanel.add(detalleTextField, gbc_detalleTextField);
			detalleTextField.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 6;
			contentPanel.add(panel, gbc_panel);
			{
				ButtonGroup botones = new ButtonGroup();
				rdbtnCuenta = new JRadioButton("Cuenta Corriente");
				rdbtnEfectivo = new JRadioButton("Efectivo");
				rdbtnEfectivo.setSelected(true);
				panel.add(rdbtnCuenta);
				panel.add(rdbtnEfectivo);
				botones.add(rdbtnEfectivo);
				botones.add(rdbtnCuenta);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("Total: $");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar venta");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						float total = Precios.getPrecio( Utils.tolowerCamelCase(tipoDeGasto.getSelectedItem().toString().replace("_", " "))) * (float)cantidad.getValue();
						
						managerDB.executeScript_Void(
								"INSERT INTO "+MainController.getEsquema()+".`gasto` VALUES ('" +managerDB.getNextId("gasto")+"','VENTA','"	+tipoDeGasto.getSelectedItem()+"','" 
											+Precios.getPrecio( Utils.tolowerCamelCase(tipoDeGasto.getSelectedItem().toString().replace("_", " ")) )+"', '"+cantidad.getValue()+"','" 
											+detalleTextField.getText() +"','"+total+"','"+rdbtnEfectivo.isSelected()+"','"+((Date)fechaVenta.getValue()).getTime()+"','"+((Persona) destinatarios.getSelectedItem()).getId()+"');");
						
						JOptionPane.showMessageDialog(null,"Se registro la venta.");
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

	
	private void inic(){
		
		List<Persona> personasList= Persona.loadFromDB(); 
		for (Persona persona : personasList) {
			destinatarios.addItem(persona);
		}
		
		fechaVenta.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
		fechaVenta.getModel().setValue( new Date(System.currentTimeMillis()) );
		fechaVenta.setEditor(new JSpinner.DateEditor(fechaVenta, "dd/MM/yyyy"));
	}
	
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
