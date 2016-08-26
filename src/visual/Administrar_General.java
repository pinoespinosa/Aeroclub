package visual;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import visual.venta.Venta_Campo;
import visual.venta.Venta_Deposito_Dinero;
import base_datos.managerDB;
import data.Avion;
import data.Gasto;
import data.Instructor;
import data.Persona;
import data.Precios;
import extended.JDialogExtended;
import extended.MainController;

public class Administrar_General extends JDialogExtended {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JSpinner spinnerPrecioCombustibleSocio, spinnerPrecioAceiteSocio, spinnerPrecioAvion, precioInstructorSpinner, spinnerPrecioBautismo, montoActualizado;;
	private boolean dirtyAvionCosto = false;
	private boolean dirtyInstructorCosto = false;
	private boolean updateAvion = false, updateInstructor = false;
	private DefaultComboBoxModel<Avion> avionesPreciosList;
	private JComboBox<Avion> avionComboBox;
	private JButton btnGuardarCambios;
	private JComboBox<Instructor> instructoresComboBox;
	private JComboBox<Avion> avionInspeccionComboBox;
	private JComboBox<Persona> personasCuentaCorriente, personas;
	private JComboBox<Gasto> depositosCC;
	private JLabel montoOriginal;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Administrar_General(final JFrame parent) {
		super(parent);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (dirtyAvionCosto) {
					int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						saveChangesCombustiblesAceites();
					}

				}
			}
		});
		setTitle("Administrar");
		setBounds(100, 100, 593, 693);
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
						if (dirtyAvionCosto) {
							int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
							if (opcion == JOptionPane.YES_OPTION) {
								saveChangesCombustiblesAceites();
							} else
								updateUi();

						}
					}
				});
				{
					JPanel panel = new JPanel();
					tabbedPane.addTab("Cargar Base Datos", null, panel, null);
					GridBagLayout gbl_panel = new GridBagLayout();
					gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
					gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 20, 0, 0, 0, 10, 0, 20, 0, 0, 0, 10, 0, 0, 0, 0, 0};
					gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
					gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
					panel.setLayout(gbl_panel);
					{
						JButton btnCrearPiloto = new JButton("Crear Piloto");
						btnCrearPiloto.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Nuevo_Piloto dialog = new Nuevo_Piloto(Administrar_General.this);
								MainController.sleepActualAndCreateNew(Administrar_General.this, dialog);
							}
						});
						GridBagConstraints gbc_btnCrearPiloto = new GridBagConstraints();
						gbc_btnCrearPiloto.gridwidth = 3;
						gbc_btnCrearPiloto.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnCrearPiloto.insets = new Insets(0, 0, 5, 5);
						gbc_btnCrearPiloto.anchor = GridBagConstraints.NORTH;
						gbc_btnCrearPiloto.gridx = 1;
						gbc_btnCrearPiloto.gridy = 1;
						panel.add(btnCrearPiloto, gbc_btnCrearPiloto);
					}
					{
						JButton btnCrearInstructor = new JButton("Crear Instructor");
						btnCrearInstructor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Nuevo_Instructor dialog = new Nuevo_Instructor(Administrar_General.this);
								MainController.sleepActualAndCreateNew(Administrar_General.this, dialog);
							}
						});
						GridBagConstraints gbc_btnCrearInstructor = new GridBagConstraints();
						gbc_btnCrearInstructor.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnCrearInstructor.gridwidth = 3;
						gbc_btnCrearInstructor.insets = new Insets(0, 0, 5, 5);
						gbc_btnCrearInstructor.gridx = 1;
						gbc_btnCrearInstructor.gridy = 2;
						panel.add(btnCrearInstructor, gbc_btnCrearInstructor);
					}
					{
						JButton btnCargarDinero = new JButton("Cargar dinero");
						btnCargarDinero.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								MainController.sleepActualAndCreateNew(Administrar_General.this, new Venta_Deposito_Dinero(Administrar_General.this));
							}
						});
						GridBagConstraints gbc_btnCargarDinero = new GridBagConstraints();
						gbc_btnCargarDinero.gridwidth = 3;
						gbc_btnCargarDinero.insets = new Insets(0, 0, 5, 5);
						gbc_btnCargarDinero.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnCargarDinero.gridx = 1;
						gbc_btnCargarDinero.gridy = 3;
						panel.add(btnCargarDinero, gbc_btnCargarDinero);
					}
					{
						JButton btnVerSaldos = new JButton("Ver detalle");
						btnVerSaldos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								// Create columns names
								String columnNames[] = {"fecha", "detalle", "A_pagar", "A_favor"};

								String script = "SELECT fecha,detalle, A_pagar, A_favor FROM " + MainController.getEsquema() + ".cuenta_corriente WHERE idPersona like'" + ((Persona) personasCuentaCorriente.getSelectedItem()).getId() + "';";

								List<String> campos = Arrays.asList(columnNames);

								List<List<String>> datos = managerDB.executeScript_Query(script, campos);

								if (datos.isEmpty()) {
									JOptionPane.showMessageDialog(null, "No se registran movimientos.");
									return;
								}
								SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm");

								float total = 0;

								for (List<String> list : datos) {
									list.set(0, format.format(new Date(Long.parseLong(list.get(0)))));
									total -= Float.parseFloat(list.get(2));
									total += Float.parseFloat(list.get(3));
									list.set(2, Math.round(Float.parseFloat(list.get(2))) + "");
									list.set(3, Math.round(Float.parseFloat(list.get(3))) + "");
								}

								MainController.sleepActualAndCreateNew(Administrar_General.this, new Informes_Table(Administrar_General.this, campos, datos, "Detalle de cuenta corriente de " + ((Persona) personasCuentaCorriente.getSelectedItem()).getName() + " " + ((Persona) personasCuentaCorriente.getSelectedItem()).getApellido() , "Monto actual: $" + total));
						
							}
						});
						{
							JButton Refresh = new JButton("Refresh");
							Refresh.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									
									personasCuentaCorriente.removeAllItems();
									List<Persona> lper = Persona.loadFromDB();
									for (Persona persona : lper) {
										personasCuentaCorriente.addItem(persona);
									}
									
									
									personas.removeAllItems();
									for (Persona persona : lper) {
										personas.addItem(persona);
									}
								}
							});
							GridBagConstraints gbc_Refresh = new GridBagConstraints();
							gbc_Refresh.insets = new Insets(0, 0, 5, 5);
							gbc_Refresh.gridx = 1;
							gbc_Refresh.gridy = 4;
							panel.add(Refresh, gbc_Refresh);
						}
						{
							personasCuentaCorriente = new JComboBox<Persona>();
							List<Persona> personas = Persona.loadFromDB();
							for (Persona persona : personas) {
								personasCuentaCorriente.addItem(persona);
							}
							
							GridBagConstraints gbc_comboBox = new GridBagConstraints();
							gbc_comboBox.insets = new Insets(0, 0, 5, 5);
							gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
							gbc_comboBox.gridx = 2;
							gbc_comboBox.gridy = 4;
							panel.add(personasCuentaCorriente, gbc_comboBox);
						}
						GridBagConstraints gbc_btnVerSaldos = new GridBagConstraints();
						gbc_btnVerSaldos.insets = new Insets(0, 0, 5, 5);
						gbc_btnVerSaldos.gridx = 3;
						gbc_btnVerSaldos.gridy = 4;
						panel.add(btnVerSaldos, gbc_btnVerSaldos);
					}
					{
						JSeparator separator = new JSeparator();
						GridBagConstraints gbc_separator = new GridBagConstraints();
						gbc_separator.fill = GridBagConstraints.HORIZONTAL;
						gbc_separator.gridwidth = 3;
						gbc_separator.insets = new Insets(0, 0, 5, 5);
						gbc_separator.gridx = 1;
						gbc_separator.gridy = 6;
						panel.add(separator, gbc_separator);
					}
					{
						JLabel lblEliminarPersona = new JLabel("Eliminar persona, piloto, instructor");
						lblEliminarPersona.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
						GridBagConstraints gbc_lblEliminarPersona = new GridBagConstraints();
						gbc_lblEliminarPersona.anchor = GridBagConstraints.WEST;
						gbc_lblEliminarPersona.gridwidth = 2;
						gbc_lblEliminarPersona.insets = new Insets(0, 0, 5, 5);
						gbc_lblEliminarPersona.gridx = 1;
						gbc_lblEliminarPersona.gridy = 8;
						panel.add(lblEliminarPersona, gbc_lblEliminarPersona);
					}
					{
						personas = new JComboBox<Persona>();
						List<Persona> lper = Persona.loadFromDB();
						for (Persona persona : lper) {
							personas.addItem(persona);
						}
						GridBagConstraints gbc_personas = new GridBagConstraints();
						gbc_personas.gridwidth = 2;
						gbc_personas.insets = new Insets(0, 0, 5, 5);
						gbc_personas.fill = GridBagConstraints.HORIZONTAL;
						gbc_personas.gridx = 1;
						gbc_personas.gridy = 10;
						panel.add(personas, gbc_personas);
					}
					{
						JButton btnEliminarPersona = new JButton("Eliminar");
						btnEliminarPersona.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Persona select = (Persona)personas.getSelectedItem();
								
								int opcion = JOptionPane.showConfirmDialog(null, "Está a punto de eliminar a la persona " + select.toString() + " ¿Quiere continuar?", "Eliminar persona", JOptionPane.YES_NO_OPTION);
								if (opcion == JOptionPane.YES_OPTION) {
									managerDB.executeScript_Void("UPDATE `"+MainController.getEsquema()+"`.`persona` SET `dni` = -'"+select.getDni()+"' WHERE `id` = '"+select.getId()+"';");
									
									
									personas.removeAllItems();
									List<Persona> lper = Persona.loadFromDB();
									for (Persona persona : lper) {
										personas.addItem(persona);
									}
									
									personasCuentaCorriente.removeAllItems();
									List<Persona> personas = Persona.loadFromDB();
									for (Persona persona : personas) {
										personasCuentaCorriente.addItem(persona);
									}		
									
								}
								
							}
						});
						GridBagConstraints gbc_btnEliminarPersona = new GridBagConstraints();
						gbc_btnEliminarPersona.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnEliminarPersona.insets = new Insets(0, 0, 5, 5);
						gbc_btnEliminarPersona.gridx = 3;
						gbc_btnEliminarPersona.gridy = 10;
						panel.add(btnEliminarPersona, gbc_btnEliminarPersona);
					}
				
				
				
				
				
				
				
				
				
				{
					JSeparator separator = new JSeparator();
					GridBagConstraints gbc_separator = new GridBagConstraints();
					gbc_separator.fill = GridBagConstraints.HORIZONTAL;
					gbc_separator.gridwidth = 3;
					gbc_separator.insets = new Insets(0, 0, 5, 5);
					gbc_separator.gridx = 1;
					gbc_separator.gridy = 13;
					panel.add(separator, gbc_separator);
				}
				{
					JLabel lblEditarMontoCargado = new JLabel("Editar monto cargado a Cuenta Corriente");
					lblEditarMontoCargado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
					GridBagConstraints gbc_lblEditarMontoCargado = new GridBagConstraints();
					gbc_lblEditarMontoCargado.insets = new Insets(0, 0, 5, 5);
					gbc_lblEditarMontoCargado.gridx = 1;
					gbc_lblEditarMontoCargado.gridy = 14;
					panel.add(lblEditarMontoCargado, gbc_lblEditarMontoCargado);
				}
				{
					depositosCC = new JComboBox<Gasto>();
					GridBagConstraints gbc_depositosCC = new GridBagConstraints();
					gbc_depositosCC.gridwidth = 3;
					gbc_depositosCC.insets = new Insets(0, 0, 5, 5);
					gbc_depositosCC.fill = GridBagConstraints.HORIZONTAL;
					gbc_depositosCC.gridx = 1;
					gbc_depositosCC.gridy = 16;
					panel.add(depositosCC, gbc_depositosCC);
				}
				{
					JLabel lblActualizar = new JLabel("Monto orignal");
					GridBagConstraints gbc_lblActualizar = new GridBagConstraints();
					gbc_lblActualizar.insets = new Insets(0, 0, 5, 5);
					gbc_lblActualizar.gridx = 1;
					gbc_lblActualizar.gridy = 17;
					panel.add(lblActualizar, gbc_lblActualizar);
				}
				{
					JLabel lblMontoActualizado = new JLabel("Monto actualizado");
					GridBagConstraints gbc_lblMontoActualizado = new GridBagConstraints();
					gbc_lblMontoActualizado.insets = new Insets(0, 0, 5, 5);
					gbc_lblMontoActualizado.gridx = 2;
					gbc_lblMontoActualizado.gridy = 17;
					panel.add(lblMontoActualizado, gbc_lblMontoActualizado);
				}
				{
					montoOriginal = new JLabel("$ ");
					GridBagConstraints gbc_montoOriginal = new GridBagConstraints();
					gbc_montoOriginal.insets = new Insets(0, 0, 5, 5);
					gbc_montoOriginal.gridx = 1;
					gbc_montoOriginal.gridy = 18;
					panel.add(montoOriginal, gbc_montoOriginal);
				}
				{
					JButton btnActualizarValorCC = new JButton("Actualizar");
					btnActualizarValorCC.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
									Gasto gasto = (Gasto) depositosCC.getSelectedItem();
									
									int opcion = JOptionPane.showConfirmDialog(null, "Está a punto de editar el valor acreditado a la cuenta corriente. ¿Quiere continuar?", "Eliminar persona", JOptionPane.YES_NO_OPTION);
									if (opcion == JOptionPane.YES_OPTION) {
										managerDB.executeScript_Void("UPDATE `"+MainController.getEsquema()+"`.`gasto` SET `total` = -'"+montoActualizado.getValue() +"' WHERE `id` = '"+gasto.getId()+"';");
										updateUi();
									}
									
								}
							});
					{
						montoActualizado = new JSpinner();
						GridBagConstraints gbc_spinner = new GridBagConstraints();
						gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinner.insets = new Insets(0, 0, 5, 5);
						gbc_spinner.gridx = 2;
						gbc_spinner.gridy = 18;
						panel.add(montoActualizado, gbc_spinner);
					}
							
						
				
					GridBagConstraints gbc_btnActualizarValorCC = new GridBagConstraints();
					gbc_btnActualizarValorCC.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnActualizarValorCC.gridwidth = 3;
					gbc_btnActualizarValorCC.insets = new Insets(0, 0, 5, 5);
					gbc_btnActualizarValorCC.gridx = 1;
					gbc_btnActualizarValorCC.gridy = 19;
					panel.add(btnActualizarValorCC, gbc_btnActualizarValorCC);
				}



















				}

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				tabbedPane.addTab("Precios Comb/Aceites", null, preciosPanel, null);
				GridBagLayout gbl_preciosPanel = new GridBagLayout();
				gbl_preciosPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
				gbl_preciosPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
				gbl_preciosPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_preciosPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				preciosPanel.setLayout(gbl_preciosPanel);
				{
					JLabel lblPrecioCombustible = new JLabel("Precio Combustible");
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
					btnGuardarCambios = new JButton("Guardar Cambios");
					btnGuardarCambios.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							saveChangesCombustiblesAceites();
						}
					});
					{
						JLabel lblPrecioAceite = new JLabel("Precio Aceite");
						GridBagConstraints gbc_lblPrecioAceite = new GridBagConstraints();
						gbc_lblPrecioAceite.anchor = GridBagConstraints.WEST;
						gbc_lblPrecioAceite.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecioAceite.gridx = 1;
						gbc_lblPrecioAceite.gridy = 2;
						preciosPanel.add(lblPrecioAceite, gbc_lblPrecioAceite);
					}
					{
						spinnerPrecioAceiteSocio = new JSpinner();
						spinnerPrecioAceiteSocio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
						GridBagConstraints gbc_spinnerPrecioAceiteSocio = new GridBagConstraints();
						gbc_spinnerPrecioAceiteSocio.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAceiteSocio.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAceiteSocio.gridx = 2;
						gbc_spinnerPrecioAceiteSocio.gridy = 2;
						preciosPanel.add(spinnerPrecioAceiteSocio, gbc_spinnerPrecioAceiteSocio);
					}
					GridBagConstraints gbc_btnGuardarCambios = new GridBagConstraints();
					gbc_btnGuardarCambios.insets = new Insets(0, 0, 5, 5);
					gbc_btnGuardarCambios.gridwidth = 2;
					gbc_btnGuardarCambios.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnGuardarCambios.gridx = 1;
					gbc_btnGuardarCambios.gridy = 13;
					preciosPanel.add(btnGuardarCambios, gbc_btnGuardarCambios);
				}
			}
			{
				JPanel avionesPanel = new JPanel();
				avionesPanel.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentHidden(ComponentEvent arg0) {

						if (dirtyAvionCosto) {
							int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
							if (opcion == JOptionPane.YES_OPTION) {
								saveChangesAviones();
							}
						}
						updateAvion = true;
						if (avionesPreciosList.getSize() != 0)
							spinnerPrecioAvion.setValue(((Avion) avionesPreciosList.getSelectedItem()).getPrecio());
						else
							btnGuardarCambios.setEnabled(false);
						updateAvion = false;
						dirtyAvionCosto = false;

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
					avionesPreciosList = new DefaultComboBoxModel<Avion>();
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
					gbl_updatePrecioAviosPanel.columnWidths = new int[]{10, 100, 0, 0, 10, 0};
					gbl_updatePrecioAviosPanel.rowHeights = new int[]{5, 14, 0, 0, 5, 0};
					gbl_updatePrecioAviosPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
					gbc_avionComboBox.gridwidth = 2;
					gbc_avionComboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_avionComboBox.insets = new Insets(0, 0, 5, 5);
					gbc_avionComboBox.gridx = 2;
					gbc_avionComboBox.gridy = 1;
					updatePrecioAviosPanel.add(avionComboBox, gbc_avionComboBox);
					avionComboBox.setModel(avionesPreciosList);
					{
						JLabel lblPrecio = new JLabel("Precio por hora");
						GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
						gbc_lblPrecio.anchor = GridBagConstraints.WEST;
						gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecio.gridx = 1;
						gbc_lblPrecio.gridy = 2;
						updatePrecioAviosPanel.add(lblPrecio, gbc_lblPrecio);
					}
					{
						spinnerPrecioAvion = new JSpinner();
						spinnerPrecioAvion.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
						GridBagConstraints gbc_spinnerPrecioAvion = new GridBagConstraints();
						gbc_spinnerPrecioAvion.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAvion.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAvion.gridx = 2;
						gbc_spinnerPrecioAvion.gridy = 2;
						updatePrecioAviosPanel.add(spinnerPrecioAvion, gbc_spinnerPrecioAvion);
					}
					{
						JButton btnActualizarPrecio = new JButton("Actualizar precio p/ hora");
						btnActualizarPrecio.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								saveChangesAviones();								
							}
						});
						GridBagConstraints gbc_btnActualizarPrecio = new GridBagConstraints();
						gbc_btnActualizarPrecio.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnActualizarPrecio.insets = new Insets(0, 0, 5, 5);
						gbc_btnActualizarPrecio.gridx = 3;
						gbc_btnActualizarPrecio.gridy = 2;
						updatePrecioAviosPanel.add(btnActualizarPrecio, gbc_btnActualizarPrecio);
						{
							JLabel lblPrecioBautismo = new JLabel("Precio bautismo");
							GridBagConstraints gbc_lblPrecioBautismo = new GridBagConstraints();
							gbc_lblPrecioBautismo.anchor = GridBagConstraints.WEST;
							gbc_lblPrecioBautismo.insets = new Insets(0, 0, 5, 5);
							gbc_lblPrecioBautismo.gridx = 1;
							gbc_lblPrecioBautismo.gridy = 3;
							updatePrecioAviosPanel.add(lblPrecioBautismo, gbc_lblPrecioBautismo);
						}
						{
							spinnerPrecioBautismo = new JSpinner();
							GridBagConstraints gbc_spinnerPrecioBautismo = new GridBagConstraints();
							gbc_spinnerPrecioBautismo.fill = GridBagConstraints.HORIZONTAL;
							gbc_spinnerPrecioBautismo.insets = new Insets(0, 0, 5, 5);
							gbc_spinnerPrecioBautismo.gridx = 2;
							gbc_spinnerPrecioBautismo.gridy = 3;
							updatePrecioAviosPanel.add(spinnerPrecioBautismo, gbc_spinnerPrecioBautismo);
						}
						{
							JButton btnActualizarPrecioBautismo = new JButton("Actualizar precio p/ bautismo");
							btnActualizarPrecioBautismo.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									saveChangesAvionesBautismo();
								}
							});
							GridBagConstraints gbc_btnActualizarPrecioBautismo = new GridBagConstraints();
							gbc_btnActualizarPrecioBautismo.insets = new Insets(0, 0, 5, 5);
							gbc_btnActualizarPrecioBautismo.gridx = 3;
							gbc_btnActualizarPrecioBautismo.gridy = 3;
							updatePrecioAviosPanel.add(btnActualizarPrecioBautismo, gbc_btnActualizarPrecioBautismo);
						}
						btnActualizarPrecio.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								saveChangesAviones();
							}
						});
					}
				}
				{
					JPanel crearAvionPanel = new JPanel();
					crearAvionPanel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Añadir un nuevo avión", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
					GridBagConstraints gbc_crearAvionPanel = new GridBagConstraints();
					gbc_crearAvionPanel.insets = new Insets(0, 0, 5, 5);
					gbc_crearAvionPanel.fill = GridBagConstraints.HORIZONTAL;
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
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Nuevo_Avion dialog = new Nuevo_Avion(Administrar_General.this);
								dialog.setAction(MainController.ACTION_REACTIVAR_PADRE);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
								Administrar_General.this.setEnabled(false);

							}
						});
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

					final JSpinner fechaInspeccionSpinner = new JSpinner();
					final JComboBox<String> tipoInspeccionComboBox;
					fechaInspeccionSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.MINUTE));
					fechaInspeccionSpinner.getModel().setValue(new Date(System.currentTimeMillis()));
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
						tipoInspeccionComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"25 horas", "50 horas", "100 horas"}));
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

								Avion selectedAvion = (Avion) avionInspeccionComboBox.getSelectedItem();
								managerDB.executeScript_Void("INSERT INTO `" + MainController.getEsquema() + "`.`inspeccion` VALUES ('" + managerDB.getNextId("inspeccion") + "','" + selectedAvion.getId() + "','" + ((Date) fechaInspeccionSpinner.getValue()).getTime() + "','" + tipoInspeccionComboBox.getSelectedItem() + "') ");
								JOptionPane.showMessageDialog(null, "Se registro una nueva revisión");
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
				tabbedPane.addTab("Editar datos de Instruct", null, instructoresPanel, null);
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
						if (instructoresComboBox.getSelectedItem()!=null)
							precioInstructorSpinner.setValue(((Instructor) instructoresComboBox.getSelectedItem()).getPrecio());
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

								saveChangesInstructores();

								updateInstructor = true;
								precioInstructorSpinner.setValue(((Instructor) instructoresComboBox.getSelectedItem()).getPrecio());
								updateInstructor = false;
								dirtyInstructorCosto = false;
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
			{
				JPanel campoPanel = new JPanel();
				tabbedPane.addTab("Alquiler del campo", null, campoPanel, null);
				GridBagLayout gbl_campoPanel = new GridBagLayout();
				gbl_campoPanel.columnWidths = new int[]{0, 0};
				gbl_campoPanel.rowHeights = new int[]{0, 0};
				gbl_campoPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_campoPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				campoPanel.setLayout(gbl_campoPanel);
				{
					JButton btnAlquilarCampo = new JButton("Alquilar campo");
					btnAlquilarCampo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							MainController.closeActualAndCreateNew(Administrar_General.this, new Venta_Campo(parent));
						}
					});
					GridBagConstraints gbc_btnAlquilarCampo = new GridBagConstraints();
					gbc_btnAlquilarCampo.gridx = 0;
					gbc_btnAlquilarCampo.gridy = 0;
					campoPanel.add(btnAlquilarCampo, gbc_btnAlquilarCampo);
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
		setAction(MainController.ACTION_REACTIVAR_PADRE);

		updateUi();

		avionComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					if (dirtyAvionCosto) {
						
						Avion avion = (Avion) avionesPreciosList.getSelectedItem();
						
						int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios en el/los precio/s del avion "+avion.getName()+". ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
							saveChangesAviones();
						}
						else
							dirtyAvionCosto=false;
					}
					else{
						updateAvion = true;
						spinnerPrecioAvion.setValue( ((Avion)event.getItem()).getPrecio());
						spinnerPrecioBautismo.setValue(Precios.getPrecio(((Avion)event.getItem()).getId()+"-Bautismo"));
						updateAvion = false;
					}
						
				}
			}
		});

		instructoresComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {

					if (dirtyInstructorCosto) {
						int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios en el precio de los instructores. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
						if (opcion == JOptionPane.YES_OPTION) {
							saveChangesInstructores();
						}
					}
					updateInstructor = true;
					precioInstructorSpinner.setValue(((Instructor) instructoresComboBox.getSelectedItem()).getPrecio());
					updateInstructor = false;
					dirtyInstructorCosto = false;

				}
			}
		});

		depositosCC.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					montoOriginal.setText("$ " + ((Gasto)depositosCC.getSelectedItem()).getTotal());					
				}
			}
		});
	}
	private void saveChangesAvionesBautismo() {
		Avion current = (Avion) avionesPreciosList.getSelectedItem();
		managerDB.executeScript_Void("UPDATE `"+MainController.getEsquema()+"`.`precios` SET `precio`='"+spinnerPrecioBautismo.getValue()+"' WHERE `id`='"+current.getId()+"-Bautismo';");
		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");
		updateAvion = false;
		dirtyAvionCosto = false;
	}
	private void saveChangesAviones() {
		Avion current = (Avion) avionesPreciosList.getSelectedItem();
		current.setPrecio(Float.parseFloat(spinnerPrecioAvion.getValue() + ""));
		managerDB.updateAsset(current);
		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");
		updateAvion = true;
		spinnerPrecioAvion.setValue(((Avion) avionesPreciosList.getSelectedItem()).getPrecio());
		updateAvion = false;
		dirtyAvionCosto = false;
	}

	private void saveChangesInstructores() {
		Instructor current = (Instructor) instructoresComboBox.getSelectedItem();
		current.setPrecio(Float.parseFloat(precioInstructorSpinner.getValue() + ""));
		managerDB.updateAsset(current);
		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");

	}

	private void saveChangesCombustiblesAceites() {
		Precios.updatePrecio(Precios.COMBUSTIBLE, spinnerPrecioCombustibleSocio.getValue() + "");
		Precios.updatePrecio(Precios.ACEITE, spinnerPrecioAceiteSocio.getValue() + "");

		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");
	}

	@Override
	public void updateUi() {
		spinnerPrecioCombustibleSocio.setValue(Precios.getPrecio(Precios.COMBUSTIBLE));
		spinnerPrecioAceiteSocio.setValue(Precios.getPrecio(Precios.ACEITE));

		updateAvion=true;
		// Cargo los aviones en el combo
		avionesPreciosList.removeAllElements();
		avionInspeccionComboBox.removeAllItems();

		List<Avion> aviones = Avion.loadFromDB();
		for (Avion avion : aviones) {
			avionesPreciosList.addElement(avion);
			avionInspeccionComboBox.addItem(avion);
		}

		if (avionesPreciosList.getSize() > 0) {
			avionesPreciosList.setSelectedItem(avionesPreciosList.getElementAt(0));
			spinnerPrecioAvion.setValue(((Avion) avionesPreciosList.getSelectedItem()).getPrecio());
			spinnerPrecioBautismo.setValue(Precios.getPrecio((((Avion) avionesPreciosList.getSelectedItem())).getId()+"-Bautismo"));
		}
		updateAvion=false;
		dirtyAvionCosto = false;

		depositosCC.removeAllItems();
		List<Gasto> gastos = Gasto.loadFromDB();
		for (Gasto gasto : gastos) {
			if (gasto.getClaseDeGasto().equals("DEPOSITO") && gasto.getTipo().equals("DINERO"))
				depositosCC.addItem(gasto);
		}
		
	}

}
