package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import data.Avion;
import data.Vuelo;


public class Venta_Vuelo extends JDialog {
	private JFrame parent;
	private int action;
	private DefaultComboBoxModel<Avion> avionesList;
	private DefaultListModel<Vuelo> vuelossList;
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
			gbl_panel.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 1;
				panel.add(comboBox, gbc_comboBox);
				comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				gbc_btnNewButton.gridx = 3;
				gbc_btnNewButton.gridy = 1;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
			{
				JLabel lblIntructor = new JLabel("Instructor");
				GridBagConstraints gbc_lblIntructor = new GridBagConstraints();
				gbc_lblIntructor.anchor = GridBagConstraints.WEST;
				gbc_lblIntructor.insets = new Insets(0, 0, 5, 5);
				gbc_lblIntructor.gridx = 1;
				gbc_lblIntructor.gridy = 2;
				panel.add(lblIntructor, gbc_lblIntructor);
				lblIntructor.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			{
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 2;
				panel.add(comboBox, gbc_comboBox);
				comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				gbc_btnNuevoInstructor.gridx = 3;
				gbc_btnNuevoInstructor.gridy = 2;
				panel.add(btnNuevoInstructor, gbc_btnNuevoInstructor);
			}
			{
				JLabel lblSocio = new JLabel("Socio");
				lblSocio.setFont(new Font("Tahoma", Font.BOLD, 12));
				GridBagConstraints gbc_lblSocio = new GridBagConstraints();
				gbc_lblSocio.insets = new Insets(0, 0, 5, 5);
				gbc_lblSocio.anchor = GridBagConstraints.WEST;
				gbc_lblSocio.gridx = 1;
				gbc_lblSocio.gridy = 3;
				panel.add(lblSocio, gbc_lblSocio);
			}
			{
				JComboBox comboBox = new JComboBox();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 3;
				panel.add(comboBox, gbc_comboBox);
			}
			{
				JButton btnNuevoSocio = new JButton("");
				btnNuevoSocio.setToolTipText("Nuevo Socio");
				btnNuevoSocio.setIcon(new ImageIcon("I:\\Users\\Pino\\Icono_Nuevo_Instructor1.png"));
				btnNuevoSocio.setPreferredSize(new Dimension(40, 40));
				btnNuevoSocio.setMinimumSize(new Dimension(40, 40));
				btnNuevoSocio.setMaximumSize(new Dimension(40, 40));
				GridBagConstraints gbc_btnNuevoSocio = new GridBagConstraints();
				gbc_btnNuevoSocio.insets = new Insets(0, 0, 5, 5);
				gbc_btnNuevoSocio.gridx = 3;
				gbc_btnNuevoSocio.gridy = 3;
				panel.add(btnNuevoSocio, gbc_btnNuevoSocio);
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
				JComboBox<Avion> comboBox = new JComboBox<Avion>();
				avionesList = new DefaultComboBoxModel<Avion>();
				comboBox.setModel(avionesList);
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 4;
				panel.add(comboBox, gbc_comboBox);
				comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
			}
			{
				JButton button = new JButton("");
				button.setIcon(new ImageIcon("I:\\Users\\Pino\\Icono_Nuevo_Instructor1.png"));
				button.setPreferredSize(new Dimension(40, 40));
				button.setMinimumSize(new Dimension(40, 40));
				button.setMaximumSize(new Dimension(40, 40));
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = 3;
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
				JSpinner spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.BOTH;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 5;
				panel.add(spinner, gbc_spinner);
				spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				JSpinner spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.BOTH;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 6;
				panel.add(spinner, gbc_spinner);
				spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				JSpinner spinner = new JSpinner();
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.BOTH;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 7;
				panel.add(spinner, gbc_spinner);
				spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				spinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.DAY_OF_YEAR));
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
				JSpinner spinner = new JSpinner();
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.BOTH;
				gbc_spinner.insets = new Insets(0, 0, 5, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 8;
				panel.add(spinner, gbc_spinner);
				spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
				spinner.setModel(new SpinnerDateModel(new Date(1447902000000L), null, null, Calendar.DAY_OF_YEAR));
			}
			{
				JButton btnCrearVuelo = new JButton("Crear Vuelo");
				GridBagConstraints gbc_btnCrearVuelo = new GridBagConstraints();
				gbc_btnCrearVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_btnCrearVuelo.fill = GridBagConstraints.BOTH;
				gbc_btnCrearVuelo.gridwidth = 3;
				gbc_btnCrearVuelo.gridx = 1;
				gbc_btnCrearVuelo.gridy = 10;
				panel.add(btnCrearVuelo, gbc_btnCrearVuelo);
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
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JList<Vuelo> list = new JList<Vuelo>();
				DefaultListModel<Vuelo> vuelos = new DefaultListModel<Vuelo>();
				list.setModel(vuelos);
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				vuelos.addElement(new Vuelo(new Long(1222), new Long(555)));
				
				GridBagConstraints gbc_list = new GridBagConstraints();
				gbc_list.gridwidth = 2;
				gbc_list.insets = new Insets(0, 0, 5, 5);
				gbc_list.fill = GridBagConstraints.BOTH;
				gbc_list.gridx = 0;
				gbc_list.gridy = 0;
				panel.add(list, gbc_list);
			}
			{
				JButton btnEditar = new JButton("Editar");
				GridBagConstraints gbc_btnEditar = new GridBagConstraints();
				gbc_btnEditar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEditar.insets = new Insets(0, 0, 0, 5);
				gbc_btnEditar.gridx = 0;
				gbc_btnEditar.gridy = 1;
				panel.add(btnEditar, gbc_btnEditar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
				gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEliminar.gridx = 1;
				gbc_btnEliminar.gridy = 1;
				panel.add(btnEliminar, gbc_btnEliminar);
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

		List<Avion> aviones = Avion.loadFromDB();
		for (Avion avion : aviones) {
			avionesList.addElement(avion);
		}
	}
}
