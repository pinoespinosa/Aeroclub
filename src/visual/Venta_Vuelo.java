package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import base_datos.managerDB;
import data.Avion;
import data.Instructor;
import data.Piloto;
import data.Precios;
import data.Vuelo;
import extended.MainController;


public class Venta_Vuelo extends JDialog {
	private JFrame parent;
	private int action;
	private DefaultComboBoxModel<Avion> avionesList;
	private DefaultComboBoxModel<Instructor> instructorList;
	private DefaultComboBoxModel<Piloto> pilotosList;
	private DefaultListModel<Vuelo> vuelosList;
	private JSpinner aceiteSpinner, combustibleSpinner;
	private JSpinner inicioSpinner, finalizacionSpinner;
	private JList<Vuelo> list;
	private Vuelo current;
	private JButton guardarEdicionBtn, cancelarEdicionBtn, crearVueloBtn, editarBtn, eliminarBtn ;
	private JRadioButton pagoEfectivo, pagoCuentaCorriente;
	private JLabel costoVuelo;
	
	private JComboBox<Piloto> pilotoComboBox;
	private JComboBox<Instructor> instructorComboBox;
	private JComboBox<Avion> avionComboBox;
	
	
	/**
	 * Create the dialog.
	 * @param frmSistemaDeGestin 
	 */
	public Venta_Vuelo(final JFrame parent) {
		super(parent);
		setResizable(false);
		this.parent=parent;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				parent.setEnabled(true);
				parent.setVisible(true);
			}
		});
	
		setBounds(100, 100, 800, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 176, 5, 256, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 241, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			getContentPane().add(panel, gbc_panel);
			panel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Nuevo vuelo", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{10, 0, 0, 0, 0, 10, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblPiloto = new JLabel("Piloto");
				GridBagConstraints gbc_lblPiloto = new GridBagConstraints();
				gbc_lblPiloto.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblPiloto.insets = new Insets(0, 0, 5, 5);
				gbc_lblPiloto.gridx = 1;
				gbc_lblPiloto.gridy = 1;
				panel.add(lblPiloto, gbc_lblPiloto);
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
				panel.add(pilotoComboBox, gbc_pilotoComboBox);
				pilotoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JButton btnNewButton = new JButton("");
				btnNewButton.setToolTipText("Nuevo Piloto");
				btnNewButton.setMaximumSize(new Dimension(40, 40));
				btnNewButton.setMinimumSize(new Dimension(40, 40));
				btnNewButton.setPreferredSize(new Dimension(40, 40));
				btnNewButton.setIcon(new ImageIcon("I:\\Users\\Pino\\Icono_Nuevo_Instructor1.png"));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 4;
				gbc_btnNewButton.gridy = 1;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 3;
				gbc_panel_1.gridy = 2;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{109, 0, 0};
				gbl_panel_1.rowHeights = new int[]{23, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					pagoEfectivo = new JRadioButton("Efectivo");
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
				panel.add(lblIntructor, gbc_lblIntructor);
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
				panel.add(instructorComboBox, gbc_instructorComboBox);
				instructorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JButton btnNuevoInstructor = new JButton("");
				btnNuevoInstructor.setIcon(new ImageIcon("I:\\Users\\Pino\\Icono_Nuevo_Instructor1.png"));
				btnNuevoInstructor.setToolTipText("Nuevo Instructor");
				btnNuevoInstructor.setPreferredSize(new Dimension(40, 40));
				btnNuevoInstructor.setMinimumSize(new Dimension(40, 40));
				btnNuevoInstructor.setMaximumSize(new Dimension(40, 40));
				GridBagConstraints gbc_btnNuevoInstructor = new GridBagConstraints();
				gbc_btnNuevoInstructor.insets = new Insets(0, 0, 5, 5);
				gbc_btnNuevoInstructor.gridx = 4;
				gbc_btnNuevoInstructor.gridy = 3;
				panel.add(btnNuevoInstructor, gbc_btnNuevoInstructor);
			}
			{
				JLabel lblAvin = new JLabel("Avi\u00F3n");
				GridBagConstraints gbc_lblAvin = new GridBagConstraints();
				gbc_lblAvin.anchor = GridBagConstraints.WEST;
				gbc_lblAvin.insets = new Insets(0, 0, 5, 5);
				gbc_lblAvin.gridx = 1;
				gbc_lblAvin.gridy = 4;
				panel.add(lblAvin, gbc_lblAvin);
				lblAvin.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				avionComboBox = new JComboBox<Avion>();
				avionesList = new DefaultComboBoxModel<Avion>();
				avionComboBox.setModel(avionesList);
				GridBagConstraints gbc_avionComboBox = new GridBagConstraints();
				gbc_avionComboBox.fill = GridBagConstraints.BOTH;
				gbc_avionComboBox.insets = new Insets(0, 0, 5, 5);
				gbc_avionComboBox.gridx = 3;
				gbc_avionComboBox.gridy = 4;
				panel.add(avionComboBox, gbc_avionComboBox);
				avionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JButton button = new JButton("");
				button.setIcon(new ImageIcon("I:\\Users\\Pino\\Icono_Nuevo_Instructor1.png"));
				button.setPreferredSize(new Dimension(40, 40));
				button.setMinimumSize(new Dimension(40, 40));
				button.setMaximumSize(new Dimension(40, 40));
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = 4;
				gbc_button.gridy = 4;
				panel.add(button, gbc_button);
			}
			{
				JLabel lblAceite = new JLabel("Aceite");
				GridBagConstraints gbc_lblAceite = new GridBagConstraints();
				gbc_lblAceite.anchor = GridBagConstraints.WEST;
				gbc_lblAceite.insets = new Insets(0, 0, 5, 5);
				gbc_lblAceite.gridx = 1;
				gbc_lblAceite.gridy = 5;
				panel.add(lblAceite, gbc_lblAceite);
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
				
				aceiteSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				GridBagConstraints gbc_aceiteSpinner = new GridBagConstraints();
				gbc_aceiteSpinner.fill = GridBagConstraints.BOTH;
				gbc_aceiteSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_aceiteSpinner.gridx = 3;
				gbc_aceiteSpinner.gridy = 5;
				panel.add(aceiteSpinner, gbc_aceiteSpinner);
				aceiteSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JLabel lblCombustible = new JLabel("Combustible");
				GridBagConstraints gbc_lblCombustible = new GridBagConstraints();
				gbc_lblCombustible.anchor = GridBagConstraints.WEST;
				gbc_lblCombustible.insets = new Insets(0, 0, 5, 5);
				gbc_lblCombustible.gridx = 1;
				gbc_lblCombustible.gridy = 6;
				panel.add(lblCombustible, gbc_lblCombustible);
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
				combustibleSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				GridBagConstraints gbc_combustibleSpinner = new GridBagConstraints();
				gbc_combustibleSpinner.fill = GridBagConstraints.BOTH;
				gbc_combustibleSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_combustibleSpinner.gridx = 3;
				gbc_combustibleSpinner.gridy = 6;
				panel.add(combustibleSpinner, gbc_combustibleSpinner);
				combustibleSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JLabel lblHoraInicio = new JLabel("Hora Inicio");
				GridBagConstraints gbc_lblHoraInicio = new GridBagConstraints();
				gbc_lblHoraInicio.anchor = GridBagConstraints.WEST;
				gbc_lblHoraInicio.insets = new Insets(0, 0, 5, 5);
				gbc_lblHoraInicio.gridx = 1;
				gbc_lblHoraInicio.gridy = 7;
				panel.add(lblHoraInicio, gbc_lblHoraInicio);
				lblHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				inicioSpinner = new JSpinner();
				GridBagConstraints gbc_inicioSpinner = new GridBagConstraints();
				gbc_inicioSpinner.fill = GridBagConstraints.BOTH;
				gbc_inicioSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_inicioSpinner.gridx = 3;
				gbc_inicioSpinner.gridy = 7;
				panel.add(inicioSpinner, gbc_inicioSpinner);
				inicioSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				inicioSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.DAY_OF_YEAR));
				
			}
			{
				JLabel lblHoraFinalizacin = new JLabel("Hora Finalizaci\u00F3n");
				GridBagConstraints gbc_lblHoraFinalizacin = new GridBagConstraints();
				gbc_lblHoraFinalizacin.anchor = GridBagConstraints.WEST;
				gbc_lblHoraFinalizacin.insets = new Insets(0, 0, 5, 5);
				gbc_lblHoraFinalizacin.gridx = 1;
				gbc_lblHoraFinalizacin.gridy = 8;
				panel.add(lblHoraFinalizacin, gbc_lblHoraFinalizacin);
				lblHoraFinalizacin.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				finalizacionSpinner = new JSpinner();
				

				GridBagConstraints gbc_finalizacionSpinner = new GridBagConstraints();
				gbc_finalizacionSpinner.fill = GridBagConstraints.BOTH;
				gbc_finalizacionSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_finalizacionSpinner.gridx = 3;
				gbc_finalizacionSpinner.gridy = 8;
				panel.add(finalizacionSpinner, gbc_finalizacionSpinner);
				finalizacionSpinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				finalizacionSpinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.DAY_OF_YEAR));
			}
			{
				JLabel lblCargosPorEl = new JLabel("Cargos por el vuelo:    $");
				GridBagConstraints gbc_lblCargosPorEl = new GridBagConstraints();
				gbc_lblCargosPorEl.anchor = GridBagConstraints.EAST;
				gbc_lblCargosPorEl.insets = new Insets(0, 0, 5, 5);
				gbc_lblCargosPorEl.gridx = 3;
				gbc_lblCargosPorEl.gridy = 9;
				panel.add(lblCargosPorEl, gbc_lblCargosPorEl);
			}
			{
				costoVuelo = new JLabel("");
				GridBagConstraints gbc_costoVuelo = new GridBagConstraints();
				gbc_costoVuelo.anchor = GridBagConstraints.WEST;
				gbc_costoVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_costoVuelo.gridx = 4;
				gbc_costoVuelo.gridy = 9;
				panel.add(costoVuelo, gbc_costoVuelo);
			}
			{
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridwidth = 4;
				gbc_panel_1.insets = new Insets(0, 0, 5, 5);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 10;
				panel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0};
				gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					crearVueloBtn = new JButton("Crear Vuelo");
					
					
					crearVueloBtn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
																				
							if (!modelValidation())
								return;
							
							managerDB.executeScript_Void(getVueloFromView().getCreateScriptDataBase());
							updateListVuelos();
							
						}
					});
					GridBagConstraints gbc_crearVueloBtn = new GridBagConstraints();
					gbc_crearVueloBtn.fill = GridBagConstraints.BOTH;
					gbc_crearVueloBtn.gridx = 0;
					gbc_crearVueloBtn.gridy = 0;
					panel_1.add(crearVueloBtn, gbc_crearVueloBtn);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "Últimos vuelos realizados", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 1;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 35, 35, 35, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				
				vuelosList = new DefaultListModel<Vuelo>();
				updateListVuelos();
			}
			{
				editarBtn = new JButton("Editar");
				editarBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
					if(list.getSelectedValue()!=null){
						setViewFromVueloStored();
					}
						
					}
				});
				{
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.gridwidth = 2;
					gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 0;
					panel.add(scrollPane, gbc_scrollPane);
					list = new JList<Vuelo>();
					scrollPane.setViewportView(list);
					list.setFont(new Font("Consolas", Font.PLAIN, 11));
					list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					list.setModel(vuelosList);
				}
				GridBagConstraints gbc_editarBtn = new GridBagConstraints();
				gbc_editarBtn.fill = GridBagConstraints.BOTH;
				gbc_editarBtn.insets = new Insets(0, 0, 5, 5);
				gbc_editarBtn.gridx = 0;
				gbc_editarBtn.gridy = 1;
				panel.add(editarBtn, gbc_editarBtn);
			}
			{
				eliminarBtn = new JButton("Eliminar");
				GridBagConstraints gbc_eliminarBtn = new GridBagConstraints();
				gbc_eliminarBtn.insets = new Insets(0, 0, 5, 0);
				gbc_eliminarBtn.fill = GridBagConstraints.BOTH;
				gbc_eliminarBtn.gridx = 1;
				gbc_eliminarBtn.gridy = 1;
				panel.add(eliminarBtn, gbc_eliminarBtn);
			}
			{
				guardarEdicionBtn = new JButton("Guardar cambios");
				guardarEdicionBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						if (!modelValidation())
							return;
						
						Vuelo nuevo = getVueloFromView();
						nuevo.setId(current.getId());
						
						nuevo.setPrecioAceite(current.getPrecioAceite());
						nuevo.setPrecioCombustible(current.getPrecioCombustible());
						
						managerDB.updateAsset(nuevo);	
						
						updateListVuelos();
						
						guardarEdicionBtn.setEnabled(false);
						cancelarEdicionBtn.setEnabled(false);
						crearVueloBtn.setEnabled(true);
						editarBtn.setEnabled(true);
						eliminarBtn.setEnabled(true);
						
						current.setPrecioAceite(Precios.getPrecio(Precios.PRECIO_ACEITE_AEROCLUB));
						current.setPrecioCombustible(Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB));
						
						updatePrecio();
						

					}
				});
				guardarEdicionBtn.setEnabled(false);
				GridBagConstraints gbc_guardarEdicionBtn = new GridBagConstraints();
				gbc_guardarEdicionBtn.fill = GridBagConstraints.BOTH;
				gbc_guardarEdicionBtn.insets = new Insets(0, 0, 5, 5);
				gbc_guardarEdicionBtn.gridx = 0;
				gbc_guardarEdicionBtn.gridy = 2;
				panel.add(guardarEdicionBtn, gbc_guardarEdicionBtn);
			}
			{
				cancelarEdicionBtn = new JButton("Cancelar edici\u00F3n");
				cancelarEdicionBtn.setEnabled(false);
				GridBagConstraints gbc_cancelarEdicionBtn = new GridBagConstraints();
				gbc_cancelarEdicionBtn.fill = GridBagConstraints.BOTH;
				gbc_cancelarEdicionBtn.insets = new Insets(0, 0, 0, 5);
				gbc_cancelarEdicionBtn.gridx = 0;
				gbc_cancelarEdicionBtn.gridy = 3;
				panel.add(cancelarEdicionBtn, gbc_cancelarEdicionBtn);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.gridwidth = 3;
			gbc_buttonPane.insets = new Insets(0, 0, 0, 5);
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 1;
			gbc_buttonPane.gridy = 2;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Salir");
				buttonPane.add(cancelButton);
			}
		}
		
		inic();
	}

	@PostConstruct
	public void inic(){

		current=new Vuelo(Precios.getPrecio(Precios.PRECIO_ACEITE_AEROCLUB), Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB));
		
		// Cargo los aviones en el combo
		avionesList.removeAllElements();
		List<Avion> aviones = Avion.loadFromDB();
		for (Avion avion : aviones) {
			avionesList.addElement(avion);
		}
		
		// Cargo los pilotos en el combo
		pilotosList.removeAllElements();
		List<Piloto> pilotos = Piloto.loadFromDB();
		for (Piloto piloto : pilotos) {
			pilotosList.addElement(piloto);
		}
		
		// Cargo los instructores en el combo
		instructorList.removeAllElements();
		List<Instructor> intructores = Instructor.loadFromDB();
		for (Instructor instructor : intructores) {
			instructorList.addElement(instructor);
		}
		
		// Seteo a las fechas el tiempo actual
		inicioSpinner.getModel().setValue( new Date(System.currentTimeMillis()) );
		finalizacionSpinner.getModel().setValue( new Date(System.currentTimeMillis()) );
		
		// Creo el validador para las fechas de inicio y fin
	    ChangeListener spinListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	    }
	    };
	    finalizacionSpinner.addChangeListener(spinListener);
	    inicioSpinner.addChangeListener(spinListener);
	    
	    // Cargo el valor del total en base a los precios
	    updatePrecio();
	    
	}
	
	
	
	public void setViewFromVueloStored(){
		
		Vuelo aux = list.getSelectedValue();

		current=aux;
			
		
		pilotoComboBox.setSelectedIndex(pilotosList.getIndexOf(new Piloto(aux.getIdPiloto())) );

		pagoEfectivo.setSelected(aux.getFormaDePago()==Precios.EFECTIVO);
		pagoCuentaCorriente.setSelected(aux.getFormaDePago()==Precios.CUENTA_CORRIENTE);
		
		instructorComboBox.setSelectedIndex(instructorList.getIndexOf(new Instructor(aux.getIdInstructor())));
		avionComboBox.setSelectedIndex(avionesList.getIndexOf(new Avion(aux.getIdAvion())));
		
		aceiteSpinner.setValue(aux.getCantAceite());
		combustibleSpinner.setValue(aux.getCantCombustible());
		inicioSpinner.setValue(new Date(aux.getHoraInicio()));
		finalizacionSpinner.setValue(new Date(aux.getHoraFinal()));

				
		guardarEdicionBtn.setEnabled(true);
		cancelarEdicionBtn.setEnabled(true);
		editarBtn.setEnabled(false);
		eliminarBtn.setEnabled(false);
		crearVueloBtn.setEnabled(false);
		
		updatePrecio();
		
	}
		
	/**
	 * Crea un vuelo a partir de los datos de la pantalla
	 * @return
	 */
	public Vuelo getVueloFromView(){
		
		Vuelo nuevo = 
				new Vuelo(managerDB.getNextId("vuelo"), 
						((Date)inicioSpinner.getModel().getValue()).getTime(), 
						((Date)finalizacionSpinner.getModel().getValue()).getTime(), 
						Integer.parseInt(aceiteSpinner.getValue()+""), 
						Integer.parseInt(combustibleSpinner.getValue()+""),
						((Avion)avionesList.getSelectedItem()).getId(),
						((Piloto)pilotosList.getSelectedItem()).getId(),
						((Instructor)instructorList.getSelectedItem()).getId(),
						Float.parseFloat(costoVuelo.getText()),
						Precios.getPrecio(Precios.PRECIO_ACEITE_AEROCLUB),
						Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB),
						pagoEfectivo.isSelected() ? Precios.EFECTIVO : Precios.CUENTA_CORRIENTE 
						);
		
		return nuevo;
	}
		
	/**
	 * Actualiza el precio del vuelo al cambiar las cantidades de combustible utilizadas
	 */
	public void updatePrecio(){
		Float valor = Integer.parseInt(combustibleSpinner.getValue()+"")*current.getPrecioCombustible();
		valor += Integer.parseInt(aceiteSpinner.getValue()+"")*current.getPrecioAceite();
		costoVuelo.setText( valor+"" );
	}
	
	/**
	 * Actualiza la lista de vuelos desde la BD
	 */
	public void updateListVuelos(){
		vuelosList.removeAllElements();
		for (Vuelo vuelo : Vuelo.loadFromDB()) 
			vuelosList.addElement(vuelo);
	}
	
	/**
	 * Valida que los datos en el modelo sean apropiados y completos
	 * @return
	 */
	public boolean modelValidation(){
		
    	Date inicio = (Date) inicioSpinner.getModel().getValue();
		Date fin = (Date) finalizacionSpinner.getModel().getValue();
		
		if (inicio.compareTo(fin) == 1) {
			JOptionPane.showMessageDialog(null,"La hora de aterrizaje no puede ser menor que la de despegue.");
			finalizacionSpinner.getModel().setValue(inicioSpinner.getModel().getValue());
			return false;
		}
		
		
		return true;
	}
	
}
