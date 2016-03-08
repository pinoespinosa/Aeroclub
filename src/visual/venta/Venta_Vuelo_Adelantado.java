package visual.venta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import base_datos.managerDB;
import data.Avion;
import data.Piloto;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;

public class Venta_Vuelo_Adelantado extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton chequeButton;
	private JComboBox<Avion> avionComboBox;
	private JLabel lblAvion;
	private JLabel lblCantidadHoras;
	private JLabel etiqueta;
	private JComboBox<Piloto> pilotoComboBox;
	private JLabel lblPiloto;
	private JLabel monto;
	private JLabel lblNewLabel;
	private JSpinner cantidadHorasSpinner;
	private Date fechaMaximaVuelo;
	private JPanel panel;
	private JLabel label;
	private JLabel lblFormaDePago;
	private JComboBox<Precios.TYPE_PAGO> formaDePago;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Venta_Vuelo_Adelantado(Window parent) {
		super(parent);
		setTitle("Nueva venta de horas por adelantado");

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 30, 0, 0, 30, 20, 20, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblAvion = new JLabel("Avion");
			GridBagConstraints gbc_lblAvion = new GridBagConstraints();
			gbc_lblAvion.insets = new Insets(0, 0, 5, 5);
			gbc_lblAvion.anchor = GridBagConstraints.WEST;
			gbc_lblAvion.gridx = 1;
			gbc_lblAvion.gridy = 1;
			contentPanel.add(lblAvion, gbc_lblAvion);
		}
		{
			avionComboBox = new JComboBox<Avion>();
			GridBagConstraints gbc_avionComboBox = new GridBagConstraints();
			gbc_avionComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_avionComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_avionComboBox.gridx = 2;
			gbc_avionComboBox.gridy = 1;
			contentPanel.add(avionComboBox, gbc_avionComboBox);
		}
		{
			lblPiloto = new JLabel("Piloto");
			GridBagConstraints gbc_lblPiloto = new GridBagConstraints();
			gbc_lblPiloto.insets = new Insets(0, 0, 5, 5);
			gbc_lblPiloto.anchor = GridBagConstraints.WEST;
			gbc_lblPiloto.gridx = 1;
			gbc_lblPiloto.gridy = 2;
			contentPanel.add(lblPiloto, gbc_lblPiloto);
		}
		{
			pilotoComboBox = new JComboBox<Piloto>();
			GridBagConstraints gbc_pilotoComboBox = new GridBagConstraints();
			gbc_pilotoComboBox.insets = new Insets(0, 0, 5, 5);
			gbc_pilotoComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_pilotoComboBox.gridx = 2;
			gbc_pilotoComboBox.gridy = 2;
			contentPanel.add(pilotoComboBox, gbc_pilotoComboBox);
		}
		{
			lblCantidadHoras = new JLabel("Cantidad Horas");
			GridBagConstraints gbc_lblCantidadHoras = new GridBagConstraints();
			gbc_lblCantidadHoras.anchor = GridBagConstraints.WEST;
			gbc_lblCantidadHoras.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidadHoras.gridx = 1;
			gbc_lblCantidadHoras.gridy = 3;
			contentPanel.add(lblCantidadHoras, gbc_lblCantidadHoras);
		}
		{
			cantidadHorasSpinner = new JSpinner();
			cantidadHorasSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			GridBagConstraints gbc_cantidadHorasSpinner = new GridBagConstraints();
			gbc_cantidadHorasSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_cantidadHorasSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_cantidadHorasSpinner.gridx = 2;
			gbc_cantidadHorasSpinner.gridy = 3;
			contentPanel.add(cantidadHorasSpinner, gbc_cantidadHorasSpinner);
		}
		{
			lblFormaDePago = new JLabel("Forma de pago");
			GridBagConstraints gbc_lblFormaDePago = new GridBagConstraints();
			gbc_lblFormaDePago.anchor = GridBagConstraints.EAST;
			gbc_lblFormaDePago.insets = new Insets(0, 0, 5, 5);
			gbc_lblFormaDePago.gridx = 1;
			gbc_lblFormaDePago.gridy = 4;
			contentPanel.add(lblFormaDePago, gbc_lblFormaDePago);
		}
		{
			formaDePago = new JComboBox<Precios.TYPE_PAGO>();
			GridBagConstraints gbc_formaDePago = new GridBagConstraints();
			gbc_formaDePago.insets = new Insets(0, 0, 5, 5);
			gbc_formaDePago.fill = GridBagConstraints.HORIZONTAL;
			gbc_formaDePago.gridx = 2;
			gbc_formaDePago.gridy = 4;
			contentPanel.add(formaDePago, gbc_formaDePago);
		}
		{
			etiqueta = new JLabel("validez");
			GridBagConstraints gbc_etiqueta = new GridBagConstraints();
			gbc_etiqueta.anchor = GridBagConstraints.EAST;
			gbc_etiqueta.gridwidth = 2;
			gbc_etiqueta.insets = new Insets(0, 0, 5, 5);
			gbc_etiqueta.gridx = 1;
			gbc_etiqueta.gridy = 6;
			contentPanel.add(etiqueta, gbc_etiqueta);
		}
		{
			lblNewLabel = new JLabel("luego se reajustar\u00E1n al precio actual.");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 7;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.EAST;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.VERTICAL;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 9;
			contentPanel.add(panel, gbc_panel);
			{
				label = new JLabel("$");
				panel.add(label);
			}
			{
				monto = new JLabel("0");
				panel.add(monto);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				chequeButton = new JButton("Crear Venta por adelantado");
				chequeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if ((int)cantidadHorasSpinner.getValue()==0)
							return;
												
						managerDB.executeScript_Void("INSERT INTO `"+MainController.getEsquema()+"`.`horas_vendida_adelantado` VALUES " +
								"('" +managerDB.getNextId("horas_vendida_adelantado")    + "'," +
								"'" + ((Piloto)pilotoComboBox.getSelectedItem()).getId() + "'," +
								"'" + ((Avion)avionComboBox.getSelectedItem()).getId()   + "'," +
								"'" + fechaMaximaVuelo.getTime() + "', " +
								"'" + cantidadHorasSpinner.getValue() + "',' " 
								+ monto.getText() + "','"+monto.getText()+"','"+((Precios.TYPE_PAGO)formaDePago.getSelectedItem()).ordinal()+"','"+System.currentTimeMillis()+"');");
						JOptionPane.showMessageDialog(null, "Se cargaron "+cantidadHorasSpinner.getValue() + " hora/s de vuelo adelantado a " + pilotoComboBox.getSelectedItem().toString() + ".");
						Venta_Vuelo_Adelantado.this.dispose();
					}
				});
				chequeButton.setActionCommand("OK");
				buttonPane.add(chequeButton);
				getRootPane().setDefaultButton(chequeButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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

	@PostConstruct
	private void inic() {

		List<Avion> aviones = Avion.loadFromDB();
		for (Avion avion : aviones) {
			avionComboBox.addItem(avion);
		}

		List<Piloto> pilotos = Piloto.loadFromDB();
		for (Piloto piloto : pilotos) {
			pilotoComboBox.addItem(piloto);
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		long valor = 24*60*60;
		fechaMaximaVuelo = new Date (valor*1000*30 + System.currentTimeMillis());
		etiqueta.setText("Las horas vendidas son validas hasta el " + formato.format(fechaMaximaVuelo) + ",");

		avionComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					updateUi();
				}
			}
		});

		cantidadHorasSpinner.addChangeListener( new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateUi();
			}
		});
		
		for ( Precios.TYPE_PAGO forma : Precios.TYPE_PAGO.values()) {
			formaDePago.addItem(forma);	
		}
	}

	@Override
	public void updateUi() {
		float valor = ((int) cantidadHorasSpinner.getValue()) * ((Avion) avionComboBox.getSelectedItem()).getPrecio();
		monto.setText(valor+"");

	}
}
