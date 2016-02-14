package visual;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import base_datos.managerDB;
import data.Avion;
import data.Instructor;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;

public class Administrar_General extends JDialogExtended {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JSpinner spinnerPrecioCombustibleSocio, spinnerPrecioCombustibleAeroclub, spinnerPrecioAceiteSocio, spinnerPrecioAceiteAeroclub, spinnerPrecioAvion, precioInstructorSpinner;
	private boolean dirty = false;
	private boolean update = false;
	private DefaultComboBoxModel<Avion> avionesList;
	private JComboBox<Avion> avionComboBox;
	private JButton btnGuardarCambios;
	private JComboBox<Instructor> instructoresComboBox;


	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Administrar_General(JFrame parent) {
		super(parent);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (dirty) {
					int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						saveChangesCombustiblesAceites();
					}

				}
			}
		});
		setTitle("Nueva Compra");
		setBounds(100, 100, 495, 559);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 444, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 67, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
			gbc_tabbedPane.gridx = 1;
			gbc_tabbedPane.gridy = 1;
			getContentPane().add(tabbedPane, gbc_tabbedPane);
			{
				JPanel preciosPanel = new JPanel();
				preciosPanel.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentHidden(ComponentEvent arg0) {
						if (dirty) {
							int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
							if (opcion == JOptionPane.YES_OPTION) {
								saveChangesCombustiblesAceites();
							} else
								updateUi();

						}
					}
				});
				tabbedPane.addTab("Precios Combustibles/Aceites", null, preciosPanel, null);
				GridBagLayout gbl_preciosPanel = new GridBagLayout();
				gbl_preciosPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
				gbl_preciosPanel.rowHeights = new int[]{20, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
				gbl_preciosPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_preciosPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				preciosPanel.setLayout(gbl_preciosPanel);
				{
					JLabel lblPrecioCombustible = new JLabel("Precio Combustible - Socio");
					GridBagConstraints gbc_lblPrecioCombustible = new GridBagConstraints();
					gbc_lblPrecioCombustible.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecioCombustible.gridx = 1;
					gbc_lblPrecioCombustible.gridy = 1;
					preciosPanel.add(lblPrecioCombustible, gbc_lblPrecioCombustible);
				}
				{
					spinnerPrecioCombustibleSocio = new JSpinner();
					GridBagConstraints gbc_spinnerPrecioCombustibleSocio = new GridBagConstraints();
					gbc_spinnerPrecioCombustibleSocio.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerPrecioCombustibleSocio.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerPrecioCombustibleSocio.gridx = 2;
					gbc_spinnerPrecioCombustibleSocio.gridy = 1;
					preciosPanel.add(spinnerPrecioCombustibleSocio, gbc_spinnerPrecioCombustibleSocio);
				}
				{
					JLabel lblPrecioCombustible_1 = new JLabel("Precio Combustible - Aeroclub");
					GridBagConstraints gbc_lblPrecioCombustible_1 = new GridBagConstraints();
					gbc_lblPrecioCombustible_1.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecioCombustible_1.gridx = 1;
					gbc_lblPrecioCombustible_1.gridy = 2;
					preciosPanel.add(lblPrecioCombustible_1, gbc_lblPrecioCombustible_1);
				}
				{
					spinnerPrecioCombustibleAeroclub = new JSpinner();
					GridBagConstraints gbc_spinnerPrecioCombustibleAeroclub = new GridBagConstraints();
					gbc_spinnerPrecioCombustibleAeroclub.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerPrecioCombustibleAeroclub.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerPrecioCombustibleAeroclub.gridx = 2;
					gbc_spinnerPrecioCombustibleAeroclub.gridy = 2;
					preciosPanel.add(spinnerPrecioCombustibleAeroclub, gbc_spinnerPrecioCombustibleAeroclub);
				}
				{
					btnGuardarCambios = new JButton("Guardar Cambios");
					btnGuardarCambios.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							saveChangesCombustiblesAceites();
						}
					});
					{
						JLabel lblPrecioAceite = new JLabel("Precio Aceite - Socio");
						GridBagConstraints gbc_lblPrecioAceite = new GridBagConstraints();
						gbc_lblPrecioAceite.anchor = GridBagConstraints.WEST;
						gbc_lblPrecioAceite.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecioAceite.gridx = 1;
						gbc_lblPrecioAceite.gridy = 4;
						preciosPanel.add(lblPrecioAceite, gbc_lblPrecioAceite);
					}
					{
						spinnerPrecioAceiteSocio = new JSpinner();
						spinnerPrecioAceiteSocio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
						GridBagConstraints gbc_spinnerPrecioAceiteSocio = new GridBagConstraints();
						gbc_spinnerPrecioAceiteSocio.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAceiteSocio.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAceiteSocio.gridx = 2;
						gbc_spinnerPrecioAceiteSocio.gridy = 4;
						preciosPanel.add(spinnerPrecioAceiteSocio, gbc_spinnerPrecioAceiteSocio);
					}
					{
						JLabel lblPrecioAceite_1 = new JLabel("Precio Aceite - Aeroclub");
						GridBagConstraints gbc_lblPrecioAceite_1 = new GridBagConstraints();
						gbc_lblPrecioAceite_1.anchor = GridBagConstraints.WEST;
						gbc_lblPrecioAceite_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecioAceite_1.gridx = 1;
						gbc_lblPrecioAceite_1.gridy = 5;
						preciosPanel.add(lblPrecioAceite_1, gbc_lblPrecioAceite_1);
					}
					{
						spinnerPrecioAceiteAeroclub = new JSpinner();
						GridBagConstraints gbc_spinnerPrecioAceiteAeroclub = new GridBagConstraints();
						gbc_spinnerPrecioAceiteAeroclub.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAceiteAeroclub.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAceiteAeroclub.gridx = 2;
						gbc_spinnerPrecioAceiteAeroclub.gridy = 5;
						preciosPanel.add(spinnerPrecioAceiteAeroclub, gbc_spinnerPrecioAceiteAeroclub);
					}
					GridBagConstraints gbc_btnGuardarCambios = new GridBagConstraints();
					gbc_btnGuardarCambios.insets = new Insets(0, 0, 5, 5);
					gbc_btnGuardarCambios.gridwidth = 2;
					gbc_btnGuardarCambios.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnGuardarCambios.gridx = 1;
					gbc_btnGuardarCambios.gridy = 15;
					preciosPanel.add(btnGuardarCambios, gbc_btnGuardarCambios);
				}
			}
			{
				JPanel avionesPanel = new JPanel();
				avionesPanel.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentHidden(ComponentEvent arg0) {

						if (dirty) {
							int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
							if (opcion == JOptionPane.YES_OPTION) {
								saveChangesAviones();
							}
						}
						update = true;
						if (avionesList.getSize() != 0)
							spinnerPrecioAvion.setValue(((Avion) avionesList.getSelectedItem()).getPrecio());
						else
							btnGuardarCambios.setEnabled(false);
						update = false;
						dirty = false;

					}
				});

				tabbedPane.addTab("Editar aviones", null, avionesPanel, null);
				GridBagLayout gbl_avionesPanel = new GridBagLayout();
				gbl_avionesPanel.columnWidths = new int[]{20, 0, 20, 0};
				gbl_avionesPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0};
				gbl_avionesPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_avionesPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				avionesPanel.setLayout(gbl_avionesPanel);
				{
					avionesList = new DefaultComboBoxModel<Avion>();
				}
				{
					JPanel updatePrecioAviosPanel = new JPanel();
					updatePrecioAviosPanel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Ajustar precio de los aviones", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));

					GridBagConstraints gbc_updatePrecioAviosPanel = new GridBagConstraints();
					gbc_updatePrecioAviosPanel.insets = new Insets(0, 0, 5, 5);
					gbc_updatePrecioAviosPanel.fill = GridBagConstraints.BOTH;
					gbc_updatePrecioAviosPanel.gridx = 1;
					gbc_updatePrecioAviosPanel.gridy = 1;
					avionesPanel.add(updatePrecioAviosPanel, gbc_updatePrecioAviosPanel);
					GridBagLayout gbl_updatePrecioAviosPanel = new GridBagLayout();
					gbl_updatePrecioAviosPanel.columnWidths = new int[]{10, 100, 27, 10, 0};
					gbl_updatePrecioAviosPanel.rowHeights = new int[]{5, 14, 0, 0, 5, 0};
					gbl_updatePrecioAviosPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_updatePrecioAviosPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					updatePrecioAviosPanel.setLayout(gbl_updatePrecioAviosPanel);
					{
						JLabel lblAvion = new JLabel("Avion");
						GridBagConstraints gbc_lblAvion = new GridBagConstraints();
						gbc_lblAvion.insets = new Insets(0, 0, 5, 5);
						gbc_lblAvion.anchor = GridBagConstraints.NORTHWEST;
						gbc_lblAvion.gridx = 1;
						gbc_lblAvion.gridy = 1;
						updatePrecioAviosPanel.add(lblAvion, gbc_lblAvion);
					}
					avionComboBox = new JComboBox<Avion>();
					GridBagConstraints gbc_avionComboBox = new GridBagConstraints();
					gbc_avionComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_avionComboBox.insets = new Insets(0, 0, 5, 5);
					gbc_avionComboBox.gridx = 2;
					gbc_avionComboBox.gridy = 1;
					updatePrecioAviosPanel.add(avionComboBox, gbc_avionComboBox);
					avionComboBox.setModel(avionesList);
					{
						JLabel lblPrecio = new JLabel("Precio");
						GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
						gbc_lblPrecio.anchor = GridBagConstraints.WEST;
						gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecio.gridx = 1;
						gbc_lblPrecio.gridy = 2;
						updatePrecioAviosPanel.add(lblPrecio, gbc_lblPrecio);
					}
					{
						spinnerPrecioAvion = new JSpinner();
						GridBagConstraints gbc_spinnerPrecioAvion = new GridBagConstraints();
						gbc_spinnerPrecioAvion.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAvion.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAvion.gridx = 2;
						gbc_spinnerPrecioAvion.gridy = 2;
						updatePrecioAviosPanel.add(spinnerPrecioAvion, gbc_spinnerPrecioAvion);
					}
					{
						JButton btnActualizarPrecio = new JButton("Actualizar precio");
						GridBagConstraints gbc_btnActualizarPrecio = new GridBagConstraints();
						gbc_btnActualizarPrecio.anchor = GridBagConstraints.EAST;
						gbc_btnActualizarPrecio.insets = new Insets(0, 0, 5, 5);
						gbc_btnActualizarPrecio.gridx = 2;
						gbc_btnActualizarPrecio.gridy = 3;
						updatePrecioAviosPanel.add(btnActualizarPrecio, gbc_btnActualizarPrecio);
						btnActualizarPrecio.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {

								Avion current = (Avion) avionesList.getSelectedItem();
								managerDB.updateAsset(current);
								updateUi();

							}
						});
					}
				}
				{
					JPanel crearAvionPanel = new JPanel();
					crearAvionPanel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Añadir un nuevo avión", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					GridBagConstraints gbc_crearAvionPanel = new GridBagConstraints();
					gbc_crearAvionPanel.insets = new Insets(0, 0, 5, 5);
					gbc_crearAvionPanel.fill = GridBagConstraints.BOTH;
					gbc_crearAvionPanel.gridx = 1;
					gbc_crearAvionPanel.gridy = 3;
					avionesPanel.add(crearAvionPanel, gbc_crearAvionPanel);
					GridBagLayout gbl_crearAvionPanel = new GridBagLayout();
					gbl_crearAvionPanel.columnWidths = new int[]{10, 100, 27, 10, 0};
					gbl_crearAvionPanel.rowHeights = new int[]{5, 0, 5, 0};
					gbl_crearAvionPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_crearAvionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
					crearAvionPanel.setLayout(gbl_crearAvionPanel);
					{
						JButton button = new JButton("Crear nuevo avion");
						GridBagConstraints gbc_button = new GridBagConstraints();
						gbc_button.anchor = GridBagConstraints.EAST;
						gbc_button.insets = new Insets(0, 0, 5, 5);
						gbc_button.gridx = 2;
						gbc_button.gridy = 1;
						crearAvionPanel.add(button, gbc_button);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Añadir una inspección a un avión", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					GridBagConstraints gbc_panel = new GridBagConstraints();
					gbc_panel.insets = new Insets(0, 0, 5, 5);
					gbc_panel.fill = GridBagConstraints.BOTH;
					gbc_panel.gridx = 1;
					gbc_panel.gridy = 5;
					avionesPanel.add(panel, gbc_panel);
					GridBagLayout gbl_panel = new GridBagLayout();
					gbl_panel.columnWidths = new int[]{10, 100, 27, 10, 0};
					gbl_panel.rowHeights = new int[]{5, 0, 0, 0, 0, 5, 0};
					gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					panel.setLayout(gbl_panel);
					
					final JSpinner fechaInspeccionSpinner= new JSpinner();
					final JComboBox<Avion> avionInspeccionComboBox;
					final JComboBox<String> tipoInspeccionComboBox;
					fechaInspeccionSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
					fechaInspeccionSpinner.getModel().setValue( new Date(System.currentTimeMillis()) );
					fechaInspeccionSpinner.setEditor(new JSpinner.DateEditor(fechaInspeccionSpinner, "dd/MM/yyyy"));
					
					{
						JLabel lblFecha = new JLabel("Fecha");
						GridBagConstraints gbc_lblFecha = new GridBagConstraints();
						gbc_lblFecha.anchor = GridBagConstraints.WEST;
						gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
						gbc_lblFecha.gridx = 1;
						gbc_lblFecha.gridy = 1;
						panel.add(lblFecha, gbc_lblFecha);
					}
					{
						GridBagConstraints gbc_fechaInspeccionSpinner = new GridBagConstraints();
						gbc_fechaInspeccionSpinner.fill = GridBagConstraints.HORIZONTAL;
						gbc_fechaInspeccionSpinner.insets = new Insets(0, 0, 5, 5);
						gbc_fechaInspeccionSpinner.gridx = 2;
						gbc_fechaInspeccionSpinner.gridy = 1;
						panel.add(fechaInspeccionSpinner, gbc_fechaInspeccionSpinner);
					}
					{
						JLabel lblAvion_1 = new JLabel("Avion");
						GridBagConstraints gbc_lblAvion_1 = new GridBagConstraints();
						gbc_lblAvion_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblAvion_1.anchor = GridBagConstraints.WEST;
						gbc_lblAvion_1.gridx = 1;
						gbc_lblAvion_1.gridy = 2;
						panel.add(lblAvion_1, gbc_lblAvion_1);
					}
					{
						avionInspeccionComboBox = new JComboBox<Avion>();
						
						for (Avion av : Avion.loadFromDB()) {
							avionInspeccionComboBox.addItem(av);
						}
						GridBagConstraints gbc_comboBox = new GridBagConstraints();
						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBox.gridx = 2;
						gbc_comboBox.gridy = 2;
						panel.add(avionInspeccionComboBox, gbc_comboBox);
					}
					{
						JLabel lblTipoDeInspeccin = new JLabel("Tipo de inspecci\u00F3n");
						GridBagConstraints gbc_lblTipoDeInspeccin = new GridBagConstraints();
						gbc_lblTipoDeInspeccin.insets = new Insets(0, 0, 5, 5);
						gbc_lblTipoDeInspeccin.anchor = GridBagConstraints.WEST;
						gbc_lblTipoDeInspeccin.gridx = 1;
						gbc_lblTipoDeInspeccin.gridy = 3;
						panel.add(lblTipoDeInspeccin, gbc_lblTipoDeInspeccin);
					}
					{
						tipoInspeccionComboBox = new JComboBox<String>();
						tipoInspeccionComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"25 horas", "50 horas", "100 horas"}));
						GridBagConstraints gbc_comboBox = new GridBagConstraints();
						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBox.gridx = 2;
						gbc_comboBox.gridy = 3;
						panel.add(tipoInspeccionComboBox, gbc_comboBox);
					}
					{
						JButton btnRegistrarRevisin = new JButton("Registrar revisi\u00F3n");
						btnRegistrarRevisin.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								Avion selectedAvion =(Avion) avionInspeccionComboBox.getSelectedItem();
								
								managerDB.executeScript_Void("INSERT INTO `"+MainController.getEsquema()+"`.`inspeccion` VALUES ('"+managerDB.getNextId("inspeccion")+"','"+selectedAvion.getId()+"','"+( (Date)fechaInspeccionSpinner.getValue()).getTime()+"','"+tipoInspeccionComboBox.getSelectedItem()+"') ");
	
							}
						});
						GridBagConstraints gbc_btnRegistrarRevisin = new GridBagConstraints();
						gbc_btnRegistrarRevisin.anchor = GridBagConstraints.EAST;
						gbc_btnRegistrarRevisin.insets = new Insets(0, 0, 5, 5);
						gbc_btnRegistrarRevisin.gridx = 2;
						gbc_btnRegistrarRevisin.gridy = 4;
						panel.add(btnRegistrarRevisin, gbc_btnRegistrarRevisin);
					}
				}
			}
			{
				JPanel instructoresPanel = new JPanel();
				tabbedPane.addTab("Editar datos de Instructores", null, instructoresPanel, null);
				GridBagLayout gbl_instructoresPanel = new GridBagLayout();
				gbl_instructoresPanel.columnWidths = new int[]{0, 0, 0, 0};
				gbl_instructoresPanel.rowHeights = new int[]{0, 0, 0, 0};
				gbl_instructoresPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_instructoresPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				instructoresPanel.setLayout(gbl_instructoresPanel);
				{
					JPanel updatePrecioInstructores = new JPanel();
					updatePrecioInstructores.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Ajustar precio de los instructores", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					GridBagConstraints gbc_updatePrecioInstructores = new GridBagConstraints();
					gbc_updatePrecioInstructores.insets = new Insets(0, 0, 5, 5);
					gbc_updatePrecioInstructores.fill = GridBagConstraints.BOTH;
					gbc_updatePrecioInstructores.gridx = 1;
					gbc_updatePrecioInstructores.gridy = 1;
					instructoresPanel.add(updatePrecioInstructores, gbc_updatePrecioInstructores);
					GridBagLayout gbl_updatePrecioInstructores = new GridBagLayout();
					gbl_updatePrecioInstructores.columnWidths = new int[]{10, 100, 27, 10, 0};
					gbl_updatePrecioInstructores.rowHeights = new int[]{5, 14, 0, 0, 5, 0};
					gbl_updatePrecioInstructores.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
					gbl_updatePrecioInstructores.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					updatePrecioInstructores.setLayout(gbl_updatePrecioInstructores);
					{
						JLabel lblInstructor = new JLabel("Instructor");
						GridBagConstraints gbc_lblInstructor = new GridBagConstraints();
						gbc_lblInstructor.anchor = GridBagConstraints.NORTHWEST;
						gbc_lblInstructor.insets = new Insets(0, 0, 5, 5);
						gbc_lblInstructor.gridx = 1;
						gbc_lblInstructor.gridy = 1;
						updatePrecioInstructores.add(lblInstructor, gbc_lblInstructor);
					}
					{
						instructoresComboBox = new JComboBox<Instructor>();
						
						for (Instructor inst : Instructor.loadFromDB()) {
							instructoresComboBox.addItem(inst);
						}
						GridBagConstraints gbc_comboBox = new GridBagConstraints();
						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
						gbc_comboBox.gridx = 2;
						gbc_comboBox.gridy = 1;
						updatePrecioInstructores.add(instructoresComboBox, gbc_comboBox);
					}
					{
						JLabel label = new JLabel("Precio");
						GridBagConstraints gbc_label = new GridBagConstraints();
						gbc_label.anchor = GridBagConstraints.WEST;
						gbc_label.insets = new Insets(0, 0, 5, 5);
						gbc_label.gridx = 1;
						gbc_label.gridy = 2;
						updatePrecioInstructores.add(label, gbc_label);
					}
					{
						
						precioInstructorSpinner = new JSpinner();
						instructoresComboBox.addItemListener(new ItemListener() {

							@Override
							public void itemStateChanged(ItemEvent event) {
								precioInstructorSpinner.setValue( ((Instructor)instructoresComboBox.getSelectedItem()).getPrecio());
								}
							});
						
						precioInstructorSpinner.setValue( ((Instructor)instructoresComboBox.getSelectedItem()).getPrecio());
						GridBagConstraints gbc_spinner = new GridBagConstraints();
						gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinner.insets = new Insets(0, 0, 5, 5);
						gbc_spinner.gridx = 2;
						gbc_spinner.gridy = 2;
						updatePrecioInstructores.add(precioInstructorSpinner, gbc_spinner);
					}
					{
						JButton button = new JButton("Actualizar precio");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								Instructor current = (Instructor) instructoresComboBox.getSelectedItem();
								current.setPrecio( new Float( precioInstructorSpinner.getValue()+"") );
								managerDB.updateAsset(current);
								updateUi();
								JOptionPane.showMessageDialog(null, "Se ajusto el precio del instructor");
							}
						});
						GridBagConstraints gbc_button = new GridBagConstraints();
						gbc_button.anchor = GridBagConstraints.EAST;
						gbc_button.insets = new Insets(0, 0, 5, 5);
						gbc_button.gridx = 2;
						gbc_button.gridy = 3;
						updatePrecioInstructores.add(button, gbc_button);
					}
				}
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
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inic();
	}

	private void inic() {
		setAction(MainController.ACTION_EXIT);

		updateUi();

		ChangeListener spinListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!update)
					dirty = true;
			}
		};
		spinnerPrecioCombustibleAeroclub.addChangeListener(spinListener);
		spinnerPrecioCombustibleSocio.addChangeListener(spinListener);
		spinnerPrecioAceiteAeroclub.addChangeListener(spinListener);
		spinnerPrecioAceiteSocio.addChangeListener(spinListener);
		spinnerPrecioAvion.addChangeListener(spinListener);
		

		avionComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					if (dirty) {
						int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
							saveChangesAviones();
						}
					}
					update = true;
					spinnerPrecioAvion.setValue(((Avion) avionesList.getSelectedItem()).getPrecio());
					update = false;
					dirty = false;

				}
			}
		});

	}

	private void saveChangesAviones() {
		Avion current = (Avion) avionesList.getSelectedItem();
		current.setPrecio(Float.parseFloat(spinnerPrecioAvion.getValue() + ""));
		managerDB.updateAsset(current);
		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");

	}

	private void saveChangesCombustiblesAceites() {
		Precios.updatePrecio(Precios.COMBUSTIBLE_PRECIO_AEROCLUB, spinnerPrecioCombustibleAeroclub.getValue() + "");
		Precios.updatePrecio(Precios.COMBUSTIBLE_PRECIO_SOCIO, spinnerPrecioCombustibleSocio.getValue() + "");
		Precios.updatePrecio(Precios.ACEITE_PRECIO_AEROCLUB, spinnerPrecioAceiteAeroclub.getValue() + "");
		Precios.updatePrecio(Precios.ACEITE_PRECIO_SOCIO, spinnerPrecioAceiteSocio.getValue() + "");

		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");
	}

	@Override
	public void updateUi() {
		spinnerPrecioCombustibleAeroclub.setValue(Precios.getPrecio(Precios.COMBUSTIBLE_PRECIO_AEROCLUB));
		spinnerPrecioCombustibleSocio.setValue(Precios.getPrecio(Precios.COMBUSTIBLE_PRECIO_SOCIO));
		spinnerPrecioAceiteAeroclub.setValue(Precios.getPrecio(Precios.ACEITE_PRECIO_AEROCLUB));
		spinnerPrecioAceiteSocio.setValue(Precios.getPrecio(Precios.ACEITE_PRECIO_SOCIO));

		// Cargo los aviones en el combo
		avionesList.removeAllElements();
		List<Avion> aviones = Avion.loadFromDB();
		for (Avion avion : aviones) {
			avionesList.addElement(avion);
		}

		if (avionesList.getSize() > 0) {
			avionesList.setSelectedItem(avionesList.getElementAt(0));
			spinnerPrecioAvion.setValue(((Avion) avionesList.getSelectedItem()).getPrecio());
		}

		dirty = false;

	}

}
