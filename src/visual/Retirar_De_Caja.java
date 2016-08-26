package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import data.Gasto;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;


public class Retirar_De_Caja extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner totalSpinner, fechaCompra;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Retirar_De_Caja(JFrame parent) {
		super(parent);
		setTitle("Nuevo retiro de caja");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			fechaCompra = new JSpinner();
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;
			contentPanel.add(fechaCompra, gbc_spinner);
		}
		{
			JLabel lblCantidad = new JLabel("Monto Retirado $");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.anchor = GridBagConstraints.EAST;
			gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 2;
			contentPanel.add(lblCantidad, gbc_lblCantidad);
		}
		{
			totalSpinner = new JSpinner();
			totalSpinner.setModel(new SpinnerNumberModel(new Float(1), new Float(0), null, new Float(1)));
			GridBagConstraints gbc_totalSpinner = new GridBagConstraints();
			gbc_totalSpinner.gridwidth = 2;
			gbc_totalSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_totalSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_totalSpinner.gridx = 2;
			gbc_totalSpinner.gridy = 2;
			contentPanel.add(totalSpinner, gbc_totalSpinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Registrar retiro de caja");
				buttonPane.add(btnCrear);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
											

							
							float precioTotal = Float.parseFloat(totalSpinner.getValue()+"");
								
							boolean done = managerDB.executeScript_Void("INSERT INTO " + MainController.getEsquema() + ".`gasto` VALUES ('" + managerDB.getNextId("gasto") + "','COMPRA - " + "Retiro de Caja Tesorero" + "',' ','" + precioTotal+ "', '1','Extraccion de caja.','" + totalSpinner.getValue() + "','" + Precios.TYPE_PAGO.EFECTIVO.ordinal() + "','" + ((Date) fechaCompra.getValue()).getTime() + "','-1');");

							if (done)
								JOptionPane.showMessageDialog(null, "Se registro la extraccion.");
							
							updateUi();
						}
					
				});
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
		init();
	}
	
	@PostConstruct
	public void init(){

		setAction(MainController.ACTION_REACTIVAR_PADRE);
		
		List<Gasto> gastos = Gasto.loadFromDB();
		
		Set<String> tiposGasto = new HashSet<String>(); 
		
		for (Gasto gasto : gastos) {
			if (gasto.getClaseDeGasto().startsWith("COMPRA"))
				tiposGasto.add(gasto.getTipo());
		}
		
		
		fechaCompra.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
		fechaCompra.getModel().setValue( new Date(System.currentTimeMillis()) );
		fechaCompra.setEditor(new JSpinner.DateEditor(fechaCompra, "dd/MM/yyyy"));
		
	}

	@Override
	public void updateUi() {

		
	

	}
	

}
