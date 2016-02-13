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
import data.Gasto;
import extended.JDialogExtended;
import extended.MainController;


public class Compra_General extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField detalleTextField;
	private JComboBox<String> tipoCompra, tipoDeGasto;
	private JSpinner totalSpinner, fechaCompra, cantidadSpinner;

	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Compra_General(JFrame parent) {
		super(parent);
		setTitle("Nueva Compra");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTipoDeCompra = new JLabel("Tipo de compra");
			GridBagConstraints gbc_lblTipoDeCompra = new GridBagConstraints();
			gbc_lblTipoDeCompra.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeCompra.gridx = 1;
			gbc_lblTipoDeCompra.gridy = 1;
			contentPanel.add(lblTipoDeCompra, gbc_lblTipoDeCompra);
		}
		{
			tipoCompra = new JComboBox<String>();
			tipoCompra.setEditable(true);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(tipoCompra, gbc_comboBox);
		}
		{
			JLabel lblTipoDeGasto = new JLabel("Tipo de gasto");
			GridBagConstraints gbc_lblTipoDeGasto = new GridBagConstraints();
			gbc_lblTipoDeGasto.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeGasto.gridx = 1;
			gbc_lblTipoDeGasto.gridy = 2;
			contentPanel.add(lblTipoDeGasto, gbc_lblTipoDeGasto);
		}
		{
			tipoDeGasto = new JComboBox<String>();
			tipoDeGasto.setModel(new DefaultComboBoxModel(new String[] {"ACTIVACION DE BIEN DE USO", "GASTO"}));
			GridBagConstraints gbc_tipoDeGastoComboBox = new GridBagConstraints();
			gbc_tipoDeGastoComboBox.gridwidth = 2;
			gbc_tipoDeGastoComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_tipoDeGastoComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_tipoDeGastoComboBox.gridx = 2;
			gbc_tipoDeGastoComboBox.gridy = 2;
			contentPanel.add(tipoDeGasto, gbc_tipoDeGastoComboBox);
		}
		{
			JLabel lblCantidad_1 = new JLabel("Cantidad");
			GridBagConstraints gbc_lblCantidad_1 = new GridBagConstraints();
			gbc_lblCantidad_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad_1.gridx = 1;
			gbc_lblCantidad_1.gridy = 3;
			contentPanel.add(lblCantidad_1, gbc_lblCantidad_1);
		}
		{
			cantidadSpinner = new JSpinner();
			GridBagConstraints gbc_cantidadSpinner = new GridBagConstraints();
			gbc_cantidadSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_cantidadSpinner.gridwidth = 2;
			gbc_cantidadSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_cantidadSpinner.gridx = 2;
			gbc_cantidadSpinner.gridy = 3;
			contentPanel.add(cantidadSpinner, gbc_cantidadSpinner);
		}
		{
			JLabel lblDestinatario = new JLabel("Detalle de la compra");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.anchor = GridBagConstraints.EAST;
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 4;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			detalleTextField = new JTextField();
			GridBagConstraints gbc_detalleTextField = new GridBagConstraints();
			gbc_detalleTextField.gridwidth = 2;
			gbc_detalleTextField.insets = new Insets(0, 0, 5, 5);
			gbc_detalleTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_detalleTextField.gridx = 2;
			gbc_detalleTextField.gridy = 4;
			contentPanel.add(detalleTextField, gbc_detalleTextField);
			detalleTextField.setColumns(10);
		}
		{
			JLabel lblFecha = new JLabel("Fecha");
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 1;
			gbc_lblFecha.gridy = 5;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			fechaCompra = new JSpinner();
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 5;
			contentPanel.add(fechaCompra, gbc_spinner);
		}
		{
			JLabel lblCantidad = new JLabel("Monto $");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 6;
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
			gbc_totalSpinner.gridy = 6;
			contentPanel.add(totalSpinner, gbc_totalSpinner);
		}
		{
			
			
			ButtonGroup bG = new ButtonGroup();
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Registrar nueva compra");
				buttonPane.add(btnCrear);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						managerDB.executeScript_Void(
								"INSERT INTO "+MainController.getEsquema()+".`gasto` VALUES ('" +managerDB.getNextId("gasto")+"','COMPRA - "+tipoDeGasto.getSelectedItem()+"','"	+tipoCompra.getSelectedItem()+"','" 
											+"-1', '"+cantidadSpinner.getValue()+"','" + detalleTextField.getText() +"','"+totalSpinner.getValue()+"','true','"+((Date)fechaCompra.getValue()).getTime()+"','-1');");
						
						JOptionPane.showMessageDialog(null,"Se registro la compra.");

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

		setAction(MainController.ACTION_EXIT);
		
		List<Gasto> gastos = Gasto.loadFromDB();
		
		Set<String> tiposGasto = new HashSet<String>(); 
		
		for (Gasto gasto : gastos) {
			if (gasto.getClaseDeGasto().startsWith("COMPRA"))
				tiposGasto.add(gasto.getTipo());
		}
		
		for (String string : tiposGasto) {
			tipoCompra.addItem(string);
		}
		
		fechaCompra.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
		fechaCompra.getModel().setValue( new Date(System.currentTimeMillis()) );
		fechaCompra.setEditor(new JSpinner.DateEditor(fechaCompra, "dd/MM/yyyy"));
		
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
	

}
