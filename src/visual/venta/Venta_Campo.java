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
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import extended.JDialogExtended;
import extended.MainController;


public class Venta_Campo extends JDialogExtended {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> tipoDeGasto;	
	private JTextField detalleTextField;
	private JSpinner fechaVenta, total; 
	private JRadioButton rdbtnEfectivo;
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Venta_Campo(JFrame parent) {
		super(parent);
		setTitle("Arrendamiento de campo");
		setBounds(100, 100, 469, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
			fechaVenta.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
			fechaVenta.getModel().setValue( new Date(System.currentTimeMillis()) );
			fechaVenta.setEditor(new JSpinner.DateEditor(fechaVenta, "dd/MM/yyyy"));
			
			
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;
			contentPanel.add(fechaVenta, gbc_spinner);
		}
		{
			JLabel lblTipoDeVenta = new JLabel("Tipo de Venta");
			GridBagConstraints gbc_lblTipoDeVenta = new GridBagConstraints();
			gbc_lblTipoDeVenta.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeVenta.gridx = 1;
			gbc_lblTipoDeVenta.gridy = 2;
			contentPanel.add(lblTipoDeVenta, gbc_lblTipoDeVenta);
		}
		{
			tipoDeGasto = new JComboBox();
			tipoDeGasto.setModel(new DefaultComboBoxModel(new String[] {"ARRENDAMIENTO_DE_TIERRA"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 2;
			contentPanel.add(tipoDeGasto, gbc_comboBox);
		}
		{
			JLabel lblDetalle = new JLabel("Detalle");
			GridBagConstraints gbc_lblDetalle = new GridBagConstraints();
			gbc_lblDetalle.insets = new Insets(0, 0, 5, 5);
			gbc_lblDetalle.gridx = 1;
			gbc_lblDetalle.gridy = 3;
			contentPanel.add(lblDetalle, gbc_lblDetalle);
		}
		{
			detalleTextField = new JTextField();
			GridBagConstraints gbc_detalleTextField = new GridBagConstraints();
			gbc_detalleTextField.gridwidth = 2;
			gbc_detalleTextField.insets = new Insets(0, 0, 5, 5);
			gbc_detalleTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_detalleTextField.gridx = 2;
			gbc_detalleTextField.gridy = 3;
			contentPanel.add(detalleTextField, gbc_detalleTextField);
			detalleTextField.setColumns(10);
		}
		{
			JLabel lblValor = new JLabel("Valor ($)");
			GridBagConstraints gbc_lblValor = new GridBagConstraints();
			gbc_lblValor.insets = new Insets(0, 0, 5, 5);
			gbc_lblValor.gridx = 1;
			gbc_lblValor.gridy = 4;
			contentPanel.add(lblValor, gbc_lblValor);
		}
		{
			total = new JSpinner();
			GridBagConstraints gbc_total = new GridBagConstraints();
			gbc_total.gridwidth = 2;
			gbc_total.fill = GridBagConstraints.HORIZONTAL;
			gbc_total.insets = new Insets(0, 0, 5, 5);
			gbc_total.gridx = 2;
			gbc_total.gridy = 4;
			contentPanel.add(total, gbc_total);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 5;
			contentPanel.add(panel, gbc_panel);
			{
				ButtonGroup botones = new ButtonGroup();
				rdbtnEfectivo = new JRadioButton("Efectivo");
				rdbtnEfectivo.setSelected(true);
				panel.add(rdbtnEfectivo);
				botones.add(rdbtnEfectivo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar arrendamiento");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					//	float total = Precios.getPrecio( Utils.tolowerCamelCase(tipoDeGasto.getSelectedItem().toString().replace("_", " "))) * (float)cantidad.getValue();
						
						managerDB.executeScript_Void(
								"INSERT INTO "+MainController.getEsquema()+".`gasto` VALUES ('" +managerDB.getNextId("gasto")+"','VENTA','"	+tipoDeGasto.getSelectedItem()+"','1', '1','" 
											+detalleTextField.getText() +"','"+total.getValue()+"','"+rdbtnEfectivo.isSelected()+"','"+((Date)fechaVenta.getValue()).getTime()+"','-1');");
						
						JOptionPane.showMessageDialog(null,"Se registro el arrendamiento.");
					}
				});
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

	
	private void inic(){
	}
	
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
