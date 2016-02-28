package visual.venta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import visual.Nuevo_Instructor;
import visual.Nuevo_Piloto;
import base_datos.Utils;
import base_datos.managerDB;
import data.Avion;
import data.Instructor;
import data.Piloto;
import data.Precios;
import data.Vuelo;
import extended.JDialogExtended;
import extended.MainController;

public class Venta_Vuelo_Editar_Cerrar extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TYPE {	MODE_CREAR, MODE_EDICION, MODE_CERRAR, MODE_VER }
	
	private DefaultComboBoxModel<Avion> avionesList;
	private DefaultComboBoxModel<Instructor> instructorList;
	private DefaultComboBoxModel<Piloto> pilotosList;
	private JSpinner aceiteSpinner, combustibleSpinner;
	private JSpinner inicioSpinner, finalizacionSpinner;
	private Vuelo current;
	private JRadioButton pagoEfectivo, pagoCuentaCorriente;
	private JLabel lblcostoVuelo, costoVuelo;

	private JComboBox<Piloto> pilotoComboBox;
	private JComboBox<Instructor> instructorComboBox;
	private JComboBox<Avion> avionComboBox;

	private JButton btnNuevoPiloto, btnNuevoInstructor;
	
	private JPanel panelIzquierdo;
	private JLabel ordenDeVuelo;
	private JComboBox<String> tipoVueloComboBox;
	private JLabel lblTipoDeVuelo;
	private JButton btnEdition;
	
	private JLabel lblHoraFinalizacin;
	
	/**
	 * Create the dialog.
	 * 
	 * @param frmSistemaDeGestin
	 */
	public Venta_Vuelo_Editar_Cerrar(final Window parent) {
		super(parent);
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");
		setBounds(100, 100, 547, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 176, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 241, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			panelIzquierdo = new JPanel();
			GridBagConstraints gbc_panelIzquierdo = new GridBagConstraints();
			gbc_panelIzquierdo.fill = GridBagConstraints.BOTH;
			gbc_panelIzquierdo.insets = new Insets(0, 0, 5, 5);
			gbc_panelIzquierdo.gridx = 1;
			gbc_panelIzquierdo.gridy = 1;
			getContentPane().add(panelIzquierdo, gbc_panelIzquierdo);
			GridBagLayout gbl_panelIzquierdo = new GridBagLayout();
			gbl_panelIzquierdo.columnWidths = new int[]{10, 0, 20, 0, 0, 0, 10, 0};
			gbl_panelIzquierdo.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0};
			gbl_panelIzquierdo.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelIzquierdo.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			panelIzquierdo.setLayout(gbl_panelIzquierdo);
			{
				ordenDeVuelo = new JLabel("");
				ordenDeVuelo.setFont(new Font("Tahoma", Font.BOLD, 12));
				GridBagConstraints gbc_ordenDeVuelo = new GridBagConstraints();
				gbc_ordenDeVuelo.anchor = GridBagConstraints.EAST;
				gbc_ordenDeVuelo.gridwidth = 2;
				gbc_ordenDeVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_ordenDeVuelo.gridx = 3;
				gbc_ordenDeVuelo.gridy = 0;
				panelIzquierdo.add(ordenDeVuelo, gbc_ordenDeVuelo);
			}
			{
				JLabel lblPiloto = new JLabel("Piloto/Alumno");
				GridBagConstraints gbc_lblPiloto = new GridBagConstraints();
				gbc_lblPiloto.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblPiloto.insets = new Insets(0, 0, 5, 5);
				gbc_lblPiloto.gridx = 1;
				gbc_lblPiloto.gridy = 1;
				panelIzquierdo.add(lblPiloto, gbc_lblPiloto);
				lblPiloto.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				pilotoComboBox = new JComboBox<Piloto>();
				pilotosList = new DefaultComboBoxModel<Piloto>();
				pilotoComboBox.setModel(pilotosList);
				GridBagConstraints gbc_pilotoComboBox = new GridBagConstraints();
				gbc_pilotoComboBox.fill = GridBagConstraints.BOTH;
				gbc_pilotoComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_pilotoComboBox.gridx = 3;
				gbc_pilotoComboBox.gridy = 1;
				panelIzquierdo.add(pilotoComboBox, gbc_pilotoComboBox);
				pilotoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				btnNuevoPiloto = new JButton("");
				btnNuevoPiloto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						Nuevo_Piloto dialog = new Nuevo_Piloto(Venta_Vuelo_Editar_Cerrar.this);
						MainController.sleepActualAndCreateNew(Venta_Vuelo_Editar_Cerrar.this, dialog);
					}
				});
				btnNuevoPiloto.setToolTipText("Nuevo Piloto");
				btnNuevoPiloto.setMaximumSize(new Dimension(40, 40));
				btnNuevoPiloto.setMinimumSize(new Dimension(40, 40));
				btnNuevoPiloto.setPreferredSize(new Dimension(40, 40));
				btnNuevoPiloto.setIcon(new ImageIcon(Venta_Vuelo_Editar_Cerrar.class.getResource("/resources/icon_instructor.png")));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 4;
				gbc_btnNewButton.gridy = 1;
				panelIzquierdo.add(btnNuevoPiloto, gbc_btnNewButton);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 3;
				gbc_panel_1.gridy = 2;
				panelIzquierdo.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{109, 0, 0};
				gbl_panel_1.rowHeights = new int[]{23, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					pagoEfectivo = new JRadioButton("Efectivo");
					pagoEfectivo.setOpaque(false);
					pagoEfectivo.setSelected(true);
					GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
					gbc_rdbtnNewRadioButton.fill = GridBagConstraints.VERTICAL;
					gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
					gbc_rdbtnNewRadioButton.gridx = 0;
					gbc_rdbtnNewRadioButton.gridy = 0;
					panel_1.add(pagoEfectivo, gbc_rdbtnNewRadioButton);
				}
				{

					pagoCuentaCorriente = new JRadioButton("Cuenta Corriente");
					pagoCuentaCorriente.setOpaque(false);
					GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
					gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.VERTICAL;
					gbc_rdbtnNewRadioButton_1.gridx = 1;
					gbc_rdbtnNewRadioButton_1.gridy = 0;
					panel_1.add(pagoCuentaCorriente, gbc_rdbtnNewRadioButton_1);
				}

				ButtonGroup bG = new ButtonGroup();
				bG.add(pagoEfectivo);
				bG.add(pagoCuentaCorriente);

			}
			{
				JLabel lblIntructor = new JLabel("Instructor");
				GridBagConstraints gbc_lblIntructor = new GridBagConstraints();
				gbc_lblIntructor.anchor = GridBagConstraints.WEST;
				gbc_lblIntructor.insets = new Insets(0, 0, 5, 5);
				gbc_lblIntructor.gridx = 1;
				gbc_lblIntructor.gridy = 3;
				panelIzquierdo.add(lblIntructor, gbc_lblIntructor);
				lblIntructor.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				instructorComboBox = new JComboBox<Instructor>();
				instructorList = new DefaultComboBoxModel<Instructor>();
				instructorComboBox.setModel(instructorList);
				GridBagConstraints gbc_instructorComboBox = new GridBagConstraints();
				gbc_instructorComboBox.fill = GridBagConstraints.BOTH;
				gbc_instructorComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_instructorComboBox.gridx = 3;
				gbc_instructorComboBox.gridy = 3;
				panelIzquierdo.add(instructorComboBox, gbc_instructorComboBox);
				instructorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));

				instructorComboBox.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent event) {
						updatePrecio();
					}
				});
			}
			{
				btnNuevoInstructor = new JButton("");
				btnNuevoInstructor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						Nuevo_Instructor dialog = new Nuevo_Instructor(Venta_Vuelo_Editar_Cerrar.this);
						dialog.setAction(MainController.ACTION_REACTIVAR_PADRE);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);

						Venta_Vuelo_Editar_Cerrar.this.setEnabled(false);
					}
				});
				btnNuevoInstructor.setIcon(new ImageIcon(Venta_Vuelo_Editar_Cerrar.class.getResource("/resources/icon_instructor.png")));
				btnNuevoInstructor.setToolTipText("Nuevo Instructor");
				btnNuevoInstructor.setPreferredSize(new Dimension(40, 40));
				btnNuevoInstructor.setMinimumSize(new Dimension(40, 40));
				btnNuevoInstructor.setMaximumSize(new Dimension(40, 40));
				GridBagConstraints gbc_btnNuevoInstructor = new GridBagConstraints();
				gbc_btnNuevoInstructor.insets = new Insets(0, 0, 5, 5);
				gbc_btnNuevoInstructor.gridx = 4;
				gbc_btnNuevoInstructor.gridy = 3;
				panelIzquierdo.add(btnNuevoInstructor, gbc_btnNuevoInstructor);
			}
			{
				JLabel lblAvin = new JLabel("Avi\u00F3n");
				GridBagConstraints gbc_lblAvin = new GridBagConstraints();
				gbc_lblAvin.anchor = GridBagConstraints.WEST;
				gbc_lblAvin.insets = new Insets(0, 0, 5, 5);
				gbc_lblAvin.gridx = 1;
				gbc_lblAvin.gridy = 4;
				panelIzquierdo.add(lblAvin, gbc_lblAvin);
				lblAvin.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				avionComboBox = new JComboBox<Avion>();
				avionComboBox.setSize(new Dimension(0, 40));
				avionComboBox.setPreferredSize(new Dimension(28, 40));
				avionesList = new DefaultComboBoxModel<Avion>();
				avionComboBox.setModel(avionesList);
				GridBagConstraints gbc_avionComboBox = new GridBagConstraints();
				gbc_avionComboBox.fill = GridBagConstraints.BOTH;
				gbc_avionComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_avionComboBox.gridx = 3;
				gbc_avionComboBox.gridy = 4;
				panelIzquierdo.add(avionComboBox, gbc_avionComboBox);
				avionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				lblTipoDeVuelo = new JLabel("Tipo de vuelo");
				lblTipoDeVuelo.setFont(new Font("Tahoma", Font.BOLD, 12));
				GridBagConstraints gbc_lblTipoDeVuelo = new GridBagConstraints();
				gbc_lblTipoDeVuelo.anchor = GridBagConstraints.WEST;
				gbc_lblTipoDeVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblTipoDeVuelo.gridx = 1;
				gbc_lblTipoDeVuelo.gridy = 5;
				panelIzquierdo.add(lblTipoDeVuelo, gbc_lblTipoDeVuelo);
			}
			{
				tipoVueloComboBox = new JComboBox<String>();
				tipoVueloComboBox.setPreferredSize(new Dimension(28, 40));
				tipoVueloComboBox.setSize(new Dimension(28, 40));
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 3;
				gbc_comboBox.gridy = 5;
				panelIzquierdo.add(tipoVueloComboBox, gbc_comboBox);
			}
			{
				JLabel lblAceite = new JLabel("Aceite (Ltrs)");
				GridBagConstraints gbc_lblAceite = new GridBagConstraints();
				gbc_lblAceite.anchor = GridBagConstraints.WEST;
				gbc_lblAceite.insets = new Insets(0, 0, 5, 5);
				gbc_lblAceite.gridx = 1;
				gbc_lblAceite.gridy = 6;
				panelIzquierdo.add(lblAceite, gbc_lblAceite);
				lblAceite.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				aceiteSpinner = new JSpinner();
				aceiteSpinner.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						updatePrecio();
					}
				});

				aceiteSpinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				GridBagConstraints gbc_aceiteSpinner = new GridBagConstraints();
				gbc_aceiteSpinner.fill = GridBagConstraints.BOTH;
				gbc_aceiteSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_aceiteSpinner.gridx = 3;
				gbc_aceiteSpinner.gridy = 6;
				panelIzquierdo.add(aceiteSpinner, gbc_aceiteSpinner);
				aceiteSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JLabel lblCombustible = new JLabel("Combustible (Ltrs)");
				GridBagConstraints gbc_lblCombustible = new GridBagConstraints();
				gbc_lblCombustible.anchor = GridBagConstraints.WEST;
				gbc_lblCombustible.insets = new Insets(0, 0, 5, 5);
				gbc_lblCombustible.gridx = 1;
				gbc_lblCombustible.gridy = 7;
				panelIzquierdo.add(lblCombustible, gbc_lblCombustible);
				lblCombustible.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				combustibleSpinner = new JSpinner();
				combustibleSpinner.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						updatePrecio();
					}
				});
				combustibleSpinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				GridBagConstraints gbc_combustibleSpinner = new GridBagConstraints();
				gbc_combustibleSpinner.fill = GridBagConstraints.BOTH;
				gbc_combustibleSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_combustibleSpinner.gridx = 3;
				gbc_combustibleSpinner.gridy = 7;
				panelIzquierdo.add(combustibleSpinner, gbc_combustibleSpinner);
				combustibleSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JLabel lblHoraInicio = new JLabel("Hora Inicio");
				GridBagConstraints gbc_lblHoraInicio = new GridBagConstraints();
				gbc_lblHoraInicio.anchor = GridBagConstraints.WEST;
				gbc_lblHoraInicio.insets = new Insets(0, 0, 5, 5);
				gbc_lblHoraInicio.gridx = 1;
				gbc_lblHoraInicio.gridy = 8;
				panelIzquierdo.add(lblHoraInicio, gbc_lblHoraInicio);
				lblHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				inicioSpinner = new JSpinner();
				GridBagConstraints gbc_inicioSpinner = new GridBagConstraints();
				gbc_inicioSpinner.fill = GridBagConstraints.BOTH;
				gbc_inicioSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_inicioSpinner.gridx = 3;
				gbc_inicioSpinner.gridy = 8;
				panelIzquierdo.add(inicioSpinner, gbc_inicioSpinner);
				inicioSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				inicioSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));

			}
			{
				lblHoraFinalizacin = new JLabel("Hora Finalizaci\u00F3n");
				GridBagConstraints gbc_lblHoraFinalizacin = new GridBagConstraints();
				gbc_lblHoraFinalizacin.anchor = GridBagConstraints.WEST;
				gbc_lblHoraFinalizacin.insets = new Insets(0, 0, 5, 5);
				gbc_lblHoraFinalizacin.gridx = 1;
				gbc_lblHoraFinalizacin.gridy = 9;
				panelIzquierdo.add(lblHoraFinalizacin, gbc_lblHoraFinalizacin);
				lblHoraFinalizacin.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				finalizacionSpinner = new JSpinner();

				GridBagConstraints gbc_finalizacionSpinner = new GridBagConstraints();
				gbc_finalizacionSpinner.fill = GridBagConstraints.BOTH;
				gbc_finalizacionSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_finalizacionSpinner.gridx = 3;
				gbc_finalizacionSpinner.gridy = 9;
				panelIzquierdo.add(finalizacionSpinner, gbc_finalizacionSpinner);
				finalizacionSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				finalizacionSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
			}
			{
				lblcostoVuelo = new JLabel("Cargos por el vuelo:    $");
				GridBagConstraints gbc_lblCargosPorEl = new GridBagConstraints();
				gbc_lblCargosPorEl.anchor = GridBagConstraints.EAST;
				gbc_lblCargosPorEl.insets = new Insets(0, 0, 5, 5);
				gbc_lblCargosPorEl.gridx = 3;
				gbc_lblCargosPorEl.gridy = 10;
				panelIzquierdo.add(lblcostoVuelo, gbc_lblCargosPorEl);
			}
			{
				costoVuelo = new JLabel("");
				GridBagConstraints gbc_costoVuelo = new GridBagConstraints();
				gbc_costoVuelo.anchor = GridBagConstraints.WEST;
				gbc_costoVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_costoVuelo.gridx = 4;
				gbc_costoVuelo.gridy = 10;
				panelIzquierdo.add(costoVuelo, gbc_costoVuelo);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.insets = new Insets(0, 0, 0, 5);
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 1;
			gbc_buttonPane.gridy = 2;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				{
					btnEdition = new JButton("");
					buttonPane.add(btnEdition);
				}
				cancelButton.setActionCommand("Salir");
				buttonPane.add(cancelButton);
			}
		}
	}

	@PostConstruct
	public void inic(Vuelo vuelo, TYPE modoApertura) {

		
		{
			aceiteSpinner.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			combustibleSpinner.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			inicioSpinner.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			pilotoComboBox.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			instructorComboBox.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			avionComboBox.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			tipoVueloComboBox.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			btnNuevoInstructor.setVisible(modoApertura!=TYPE.MODE_CERRAR);
			btnNuevoPiloto.setVisible(modoApertura!=TYPE.MODE_CERRAR);
			pagoEfectivo.setEnabled(modoApertura!=TYPE.MODE_CERRAR);
			pagoCuentaCorriente.setEnabled(modoApertura!=TYPE.MODE_CERRAR);

			finalizacionSpinner.setVisible(modoApertura==TYPE.MODE_CERRAR);
			lblHoraFinalizacin.setVisible(modoApertura==TYPE.MODE_CERRAR);
			lblcostoVuelo.setVisible(modoApertura==TYPE.MODE_CERRAR);
			costoVuelo.setVisible(modoApertura==TYPE.MODE_CERRAR);
				

			// Cargo los aviones en el combo
			avionesList.removeAllElements();
			List<Avion> aviones = Avion.loadFromDB();
			for (Avion avion : aviones) {
				avionesList.addElement(avion);
			}
			if (!aviones.isEmpty())
				current = new Vuelo(Precios.getPrecio(Precios.ACEITE_PRECIO_AEROCLUB), Precios.getPrecio(Precios.COMBUSTIBLE_PRECIO_AEROCLUB), avionesList.getElementAt(0).getPrecio());
			else
				current = new Vuelo(Precios.getPrecio(Precios.ACEITE_PRECIO_AEROCLUB), Precios.getPrecio(Precios.COMBUSTIBLE_PRECIO_AEROCLUB), 0);

			
			ActionListener precioDirty = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					updatePrecio();
				};
			};
				
			tipoVueloComboBox.addActionListener(precioDirty);
			avionComboBox.addActionListener(precioDirty);

			// Cargo los pilotos en el combo
			pilotosList.removeAllElements();
			List<Piloto> pilotos = Piloto.loadFromDB();
			for (Piloto piloto : pilotos) {
				pilotosList.addElement(piloto);
			}

			// Cargo los instructores en el combo
			instructorList.removeAllElements();
			List<Instructor> intructores = Instructor.loadFromDB();

			instructorList.addElement(new Instructor(-1, "Sin instructor", "", new Long(-1), new Long(-1), -1, new Float(0)));
			for (Instructor instructor : intructores) {
				instructorList.addElement(instructor);
			}

			// Seteo a las fechas el tiempo actual
			Date fecha = new Date(System.currentTimeMillis());
			inicioSpinner.getModel().setValue(fecha);
			finalizacionSpinner.getModel().setValue(fecha);

			// Creo el validador para las fechas de inicio y fin
			ChangeListener spinListener = new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					updatePrecio();
				}
			};
			finalizacionSpinner.addChangeListener(spinListener);
			inicioSpinner.addChangeListener(spinListener);

			// Formateo las fechas
			inicioSpinner.setEditor(new JSpinner.DateEditor(inicioSpinner, "dd/MM/yyyy HH:mm"));
			finalizacionSpinner.setEditor(new JSpinner.DateEditor(finalizacionSpinner, "dd/MM/yyyy HH:mm"));

			for (Vuelo.TipoVuelo tipo : Vuelo.TipoVuelo.values()) {
				tipoVueloComboBox.addItem(tipo.toString());
			}
			
			switch (modoApertura) {
				
				case MODE_CREAR :
				{
					btnEdition.setText("Crear");
					panelIzquierdo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Nuevo vuelo", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					panelIzquierdo.setBackground(new Color(170, 215, 124));
					ordenDeVuelo.setText("Orden de vuelo: " + managerDB.getNextId("vuelo"));
					btnEdition.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							managerDB.executeScript_Void(getVueloFromView().getCreateScriptDataBase());
							JOptionPane.showMessageDialog(null,"Se ha registrado un nuevo vuelo.");
							Venta_Vuelo_Editar_Cerrar.this.dispose();
						}
					});
				}
				break;
				
				case MODE_EDICION :
				{
					btnEdition.setText("Editar");
					panelIzquierdo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Edición de vuelo", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					panelIzquierdo.setBackground(new Color(255, 255, 128));
					setViewFromVueloStored(vuelo);
					btnEdition.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
											
							Vuelo nuevo = getVueloFromView();
							nuevo.setId(current.getId());
							nuevo.setPrecioAceite(current.getPrecioAceite());
							nuevo.setPrecioCombustible(current.getPrecioCombustible());
							
							managerDB.updateAsset(nuevo);	
							
							JOptionPane.showMessageDialog(null,"Se ha editado exitosamente el vuelo.");
							Venta_Vuelo_Editar_Cerrar.this.dispose();
							
						}
					});
				}
				break;
				
				case MODE_CERRAR :
				{
					btnEdition.setText("Cerrar");
					panelIzquierdo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Cerrar vuelo", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					panelIzquierdo.setBackground(new Color(255, 128, 128));
					setViewFromVueloStored(vuelo);
					btnEdition.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Venta_Vuelo_Editar_Cerrar.this.dispose();
						}
					});
				}
				break;
				
				case MODE_VER :
				{
					btnEdition.setVisible(false);
					setViewFromVueloStored(vuelo);
					panelIzquierdo.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Información del vuelo", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					panelIzquierdo.setBackground(new Color(153, 217, 234));
					setViewFromVueloStored(vuelo);
					btnEdition.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Venta_Vuelo_Editar_Cerrar.this.dispose();
						}
					});	
				}
					break;
	
			}
		}
			
	


		// Cargo el valor del total en base a los precios
		updatePrecio();

	}

	/**
	 * Pone en la vista los datos de un vuelo almacenado
	 * 
	 * @return
	 */
	public void setViewFromVueloStored(Vuelo aux) {

		current = aux;

		ordenDeVuelo.setText("Orden de vuelo: " + aux.getId());
		
		pilotoComboBox.setSelectedIndex(pilotosList.getIndexOf(new Piloto(aux.getIdPiloto())));

		pagoEfectivo.setSelected(aux.getFormaDePago() == Precios.EFECTIVO);
		pagoCuentaCorriente.setSelected(aux.getFormaDePago() == Precios.CUENTA_CORRIENTE);

		instructorComboBox.setSelectedIndex(instructorList.getIndexOf(new Instructor(aux.getIdInstructor())));
		avionComboBox.setSelectedIndex(avionesList.getIndexOf(new Avion(aux.getIdAvion())));

		aceiteSpinner.setValue(aux.getCantAceite());
		combustibleSpinner.setValue(aux.getCantCombustible());
		inicioSpinner.setValue(new Date(aux.getHoraInicio()));
		
		if (aux.getHoraFinal()==0)
			finalizacionSpinner.setValue(new Date());
		else
			finalizacionSpinner.setValue(new Date(aux.getHoraFinal()));

		updatePrecio();

	}

	/**
	 * Crea un vuelo a partir de los datos de la pantalla
	 * 
	 * @return
	 */
	public Vuelo getVueloFromView() {

		Vuelo nuevo = new Vuelo(managerDB.getNextId("vuelo"), ((Date) inicioSpinner.getModel().getValue()).getTime(), 0, Float.parseFloat(aceiteSpinner.getValue() + ""), Float.parseFloat(combustibleSpinner.getValue() + ""), ((Avion) avionesList.getSelectedItem()).getId(), ((Piloto) pilotosList.getSelectedItem()).getId(), ((Instructor) instructorList.getSelectedItem()).getId(), Float.parseFloat(costoVuelo.getText()), Precios.getPrecio(Precios.ACEITE_PRECIO_AEROCLUB), Precios.getPrecio(Precios.COMBUSTIBLE_PRECIO_AEROCLUB),
				((Avion) avionesList.getSelectedItem()).getPrecio(), pagoEfectivo.isSelected() ? Precios.EFECTIVO : Precios.CUENTA_CORRIENTE, Vuelo.TipoVuelo.valueOf((String) tipoVueloComboBox.getSelectedItem()).ordinal());

		return nuevo;
	}

	/**
	 * Actualiza el precio del vuelo al cambiar las cantidades de combustible
	 * utilizadas
	 */
	public void updatePrecio() {

		if (avionesList.getSize() == 0)
			return;

		if (instructorList.getSize() == 0)
			return;

		float minutosVuelo = Utils.minutesBetweenDates((Date) inicioSpinner.getModel().getValue(), (Date) finalizacionSpinner.getModel().getValue());

		float valor = (minutosVuelo / 60) * ((Avion) avionesList.getSelectedItem()).getPrecio() + (minutosVuelo / 60) * ((Instructor) instructorList.getSelectedItem()).getPrecio();

		valor = Math.round(valor * 100);
		valor = valor / 100;

		costoVuelo.setText(valor + "");
	}

	/**
	 * Valida que los datos en el modelo sean apropiados y completos
	 * 
	 * @return
	 */
	public boolean modelValidation() {

		Date inicio = (Date) inicioSpinner.getModel().getValue();
		Date fin = (Date) finalizacionSpinner.getModel().getValue();

		if (inicio.compareTo(fin) == 1) {
			JOptionPane.showMessageDialog(null, "La hora de aterrizaje no puede ser menor que la de despegue.");
			finalizacionSpinner.getModel().setValue(inicioSpinner.getModel().getValue());
			return false;
		}

		return true;
	}

	@Override
	public void updateUi() {
	}

}
