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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import data.Persona;
import extended.JDialogExtended;
import extended.MainController;


public class Venta_Combustible_Propio extends JDialogExtended {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<Persona> destinatarios; 
	private JComboBox<String> recursoAdministrado, tipoMovimiento;	
	private JTextField detalleTextField;
	private JSpinner cantidad, fechaVenta; 
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Venta_Combustible_Propio(JFrame parent) {
		super(parent);
		setTitle("Administraci\u00F3n de Combustible/Aceite propio");
		setBounds(100, 100, 469, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTipoDeMovimiento = new JLabel("Tipo de movimiento");
			GridBagConstraints gbc_lblTipoDeMovimiento = new GridBagConstraints();
			gbc_lblTipoDeMovimiento.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeMovimiento.gridx = 1;
			gbc_lblTipoDeMovimiento.gridy = 1;
			contentPanel.add(lblTipoDeMovimiento, gbc_lblTipoDeMovimiento);
		}
		{
			tipoMovimiento = new JComboBox<String>();
			tipoMovimiento.setModel(new DefaultComboBoxModel(new String[] {"EXTRACCION", "DEPOSITO"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(tipoMovimiento, gbc_comboBox);
		}
		{
			JLabel lblFecha = new JLabel("Fecha");
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 1;
			gbc_lblFecha.gridy = 2;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			fechaVenta = new JSpinner();
		
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 2;
			contentPanel.add(fechaVenta, gbc_spinner);
		}
		{
			JLabel lblDestinatario = new JLabel("Destinatario");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 3;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			destinatarios = new JComboBox<Persona>();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 3;
			contentPanel.add(destinatarios, gbc_comboBox);
		}
		{
			JLabel lblTipoDeVenta = new JLabel("Tipo de Venta");
			GridBagConstraints gbc_lblTipoDeVenta = new GridBagConstraints();
			gbc_lblTipoDeVenta.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeVenta.gridx = 1;
			gbc_lblTipoDeVenta.gridy = 4;
			contentPanel.add(lblTipoDeVenta, gbc_lblTipoDeVenta);
		}
		{
			recursoAdministrado = new JComboBox();
			recursoAdministrado.setModel(new DefaultComboBoxModel(new String[] {"COMBUSTIBLE_PROPIO", "ACEITE_PRECIO_PROPIO"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 4;
			contentPanel.add(recursoAdministrado, gbc_comboBox);
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad (Litros)");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 5;
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
			gbc_spinner.gridy = 5;
			contentPanel.add(cantidad, gbc_spinner);
		}
		{
			JLabel lblDetalle = new JLabel("Detalle");
			GridBagConstraints gbc_lblDetalle = new GridBagConstraints();
			gbc_lblDetalle.insets = new Insets(0, 0, 5, 5);
			gbc_lblDetalle.gridx = 1;
			gbc_lblDetalle.gridy = 6;
			contentPanel.add(lblDetalle, gbc_lblDetalle);
		}
		{
			detalleTextField = new JTextField();
			GridBagConstraints gbc_detalleTextField = new GridBagConstraints();
			gbc_detalleTextField.gridwidth = 2;
			gbc_detalleTextField.insets = new Insets(0, 0, 5, 5);
			gbc_detalleTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_detalleTextField.gridx = 2;
			gbc_detalleTextField.gridy = 6;
			contentPanel.add(detalleTextField, gbc_detalleTextField);
			detalleTextField.setColumns(10);
		}
		{
			{
				ButtonGroup botones = new ButtonGroup();
			}
		}
		{
			JLabel lblNewLabel = new JLabel("Total: $");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 9;
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
										
						String movimiento ="";
						if (tipoMovimiento.getSelectedItem().equals("EXTRACCION")){
							
							float cantidadMaxima = Float.parseFloat(managerDB.executeScript_Query( 
									"select sum(unidades) as max " +
									"from "+MainController.getEsquema()+".gastos_normalizados " +
									"where " +
										"tipo like '"+recursoAdministrado.getSelectedItem()+"' and '" +
										((Persona) destinatarios.getSelectedItem()).getId()+"' " +
									"like '1' group by tipo;", "max").get(0));

							float cantidadSolicitada = ((float)cantidad.getValue());
							if (cantidadMaxima<cantidadSolicitada){
								JOptionPane.showMessageDialog(null,"No puede extraer más combustible del que tiene depositado. El máximo es " + cantidadMaxima + " litros. Puede extraer el máximo y vender la cantidad restante.");
								return;
							}
							movimiento="VENTA";

						}
						else
							movimiento="COMPRA";
						
						managerDB.executeScript_Void(
								"INSERT INTO "+MainController.getEsquema()+".`gasto` VALUES (" +
										"'" +managerDB.getNextId("gasto")
										+"','"+movimiento+"','"	+recursoAdministrado.getSelectedItem()+"','" 
											+"0', '"+cantidad.getValue()+"','" 
											+detalleTextField.getText() +"','0','-1','"+((Date)fechaVenta.getValue()).getTime()+"','"+((Persona) destinatarios.getSelectedItem()).getId()+"');");
						
						JOptionPane.showMessageDialog(null,"Se registro el movimiento exitosamente.");
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
