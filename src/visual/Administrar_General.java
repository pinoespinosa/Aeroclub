package visual;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.Precios;
import extended.JDialogExtended;
import extended.MainController;

public class Administrar_General extends JDialogExtended {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JSpinner spinnerPrecioCombustibleSocio, spinnerPrecioCombustibleAeroclub, spinnerPrecioAceiteSocio, spinnerPrecioAceiteAeroclub;
	private boolean dirty = false;

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
						saveChanges();
					}

				}
			}
		});
		setTitle("Nueva Compra");
		setBounds(100, 100, 878, 559);
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
				JPanel panel = new JPanel();
				panel.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentHidden(ComponentEvent arg0) {
						if (dirty) {
							int opcion = JOptionPane.showConfirmDialog(null, "Se realizaron cambios. ¿Desea guardar los cambios?", "Cambios", JOptionPane.YES_NO_OPTION);
							if (opcion == JOptionPane.YES_OPTION) {
								saveChanges();
							} else
								updateUi();

						}
					}
				});
				tabbedPane.addTab("Precios Combustibles/Aceites", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{20, 0, 0, 20, 0};
				gbl_panel.rowHeights = new int[]{20, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
				gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JLabel lblPrecioCombustible = new JLabel("Precio Combustible - Socio");
					GridBagConstraints gbc_lblPrecioCombustible = new GridBagConstraints();
					gbc_lblPrecioCombustible.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecioCombustible.gridx = 1;
					gbc_lblPrecioCombustible.gridy = 1;
					panel.add(lblPrecioCombustible, gbc_lblPrecioCombustible);
				}
				{
					spinnerPrecioCombustibleSocio = new JSpinner();
					GridBagConstraints gbc_spinnerPrecioCombustibleSocio = new GridBagConstraints();
					gbc_spinnerPrecioCombustibleSocio.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerPrecioCombustibleSocio.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerPrecioCombustibleSocio.gridx = 2;
					gbc_spinnerPrecioCombustibleSocio.gridy = 1;
					panel.add(spinnerPrecioCombustibleSocio, gbc_spinnerPrecioCombustibleSocio);
				}
				{
					JLabel lblPrecioCombustible_1 = new JLabel("Precio Combustible - Aeroclub");
					GridBagConstraints gbc_lblPrecioCombustible_1 = new GridBagConstraints();
					gbc_lblPrecioCombustible_1.anchor = GridBagConstraints.WEST;
					gbc_lblPrecioCombustible_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecioCombustible_1.gridx = 1;
					gbc_lblPrecioCombustible_1.gridy = 2;
					panel.add(lblPrecioCombustible_1, gbc_lblPrecioCombustible_1);
				}
				{
					spinnerPrecioCombustibleAeroclub = new JSpinner();
					GridBagConstraints gbc_spinnerPrecioCombustibleAeroclub = new GridBagConstraints();
					gbc_spinnerPrecioCombustibleAeroclub.insets = new Insets(0, 0, 5, 5);
					gbc_spinnerPrecioCombustibleAeroclub.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinnerPrecioCombustibleAeroclub.gridx = 2;
					gbc_spinnerPrecioCombustibleAeroclub.gridy = 2;
					panel.add(spinnerPrecioCombustibleAeroclub, gbc_spinnerPrecioCombustibleAeroclub);
				}
				{
					JButton btnGuardarCambios = new JButton("Guardar Cambios");
					btnGuardarCambios.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							saveChanges();
						}
					});
					{
						JLabel lblPrecioAceite = new JLabel("Precio Aceite - Socio");
						GridBagConstraints gbc_lblPrecioAceite = new GridBagConstraints();
						gbc_lblPrecioAceite.anchor = GridBagConstraints.WEST;
						gbc_lblPrecioAceite.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecioAceite.gridx = 1;
						gbc_lblPrecioAceite.gridy = 4;
						panel.add(lblPrecioAceite, gbc_lblPrecioAceite);
					}
					{
						spinnerPrecioAceiteSocio = new JSpinner();
						spinnerPrecioAceiteSocio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
						GridBagConstraints gbc_spinnerPrecioAceiteSocio = new GridBagConstraints();
						gbc_spinnerPrecioAceiteSocio.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAceiteSocio.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAceiteSocio.gridx = 2;
						gbc_spinnerPrecioAceiteSocio.gridy = 4;
						panel.add(spinnerPrecioAceiteSocio, gbc_spinnerPrecioAceiteSocio);
					}
					{
						JLabel lblPrecioAceite_1 = new JLabel("Precio Aceite - Aeroclub");
						GridBagConstraints gbc_lblPrecioAceite_1 = new GridBagConstraints();
						gbc_lblPrecioAceite_1.anchor = GridBagConstraints.WEST;
						gbc_lblPrecioAceite_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblPrecioAceite_1.gridx = 1;
						gbc_lblPrecioAceite_1.gridy = 5;
						panel.add(lblPrecioAceite_1, gbc_lblPrecioAceite_1);
					}
					{
						spinnerPrecioAceiteAeroclub = new JSpinner();
						GridBagConstraints gbc_spinnerPrecioAceiteAeroclub = new GridBagConstraints();
						gbc_spinnerPrecioAceiteAeroclub.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinnerPrecioAceiteAeroclub.insets = new Insets(0, 0, 5, 5);
						gbc_spinnerPrecioAceiteAeroclub.gridx = 2;
						gbc_spinnerPrecioAceiteAeroclub.gridy = 5;
						panel.add(spinnerPrecioAceiteAeroclub, gbc_spinnerPrecioAceiteAeroclub);
					}
					GridBagConstraints gbc_btnGuardarCambios = new GridBagConstraints();
					gbc_btnGuardarCambios.insets = new Insets(0, 0, 5, 5);
					gbc_btnGuardarCambios.gridwidth = 2;
					gbc_btnGuardarCambios.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnGuardarCambios.gridx = 1;
					gbc_btnGuardarCambios.gridy = 15;
					panel.add(btnGuardarCambios, gbc_btnGuardarCambios);
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Precio aviones", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{20, 0, 0, 0, 0, 20, 0};
				gbl_panel.rowHeights = new int[]{20, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
				gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JLabel lblAvion = new JLabel("Avion");
					GridBagConstraints gbc_lblAvion = new GridBagConstraints();
					gbc_lblAvion.insets = new Insets(0, 0, 5, 5);
					gbc_lblAvion.anchor = GridBagConstraints.EAST;
					gbc_lblAvion.gridx = 1;
					gbc_lblAvion.gridy = 1;
					panel.add(lblAvion, gbc_lblAvion);
				}
				{
					JComboBox comboBox = new JComboBox();
					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.insets = new Insets(0, 0, 5, 5);
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.gridx = 2;
					gbc_comboBox.gridy = 1;
					panel.add(comboBox, gbc_comboBox);
				}
				{
					JLabel lblPrecio = new JLabel("Precio");
					GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
					gbc_lblPrecio.anchor = GridBagConstraints.WEST;
					gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
					gbc_lblPrecio.gridx = 1;
					gbc_lblPrecio.gridy = 2;
					panel.add(lblPrecio, gbc_lblPrecio);
				}
				{
					JSpinner spinner = new JSpinner();
					GridBagConstraints gbc_spinner = new GridBagConstraints();
					gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
					gbc_spinner.insets = new Insets(0, 0, 5, 5);
					gbc_spinner.gridx = 2;
					gbc_spinner.gridy = 2;
					panel.add(spinner, gbc_spinner);
				}
				{
					JButton button = new JButton("Guardar Cambios");
					GridBagConstraints gbc_button = new GridBagConstraints();
					gbc_button.fill = GridBagConstraints.HORIZONTAL;
					gbc_button.gridwidth = 2;
					gbc_button.insets = new Insets(0, 0, 5, 5);
					gbc_button.gridx = 2;
					gbc_button.gridy = 4;
					panel.add(button, gbc_button);
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
				dirty = true;
			}
		};
		spinnerPrecioCombustibleAeroclub.addChangeListener(spinListener);
		spinnerPrecioCombustibleSocio.addChangeListener(spinListener);

	}

	private void saveChanges() {
		Precios.updatePrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB, spinnerPrecioCombustibleAeroclub.getValue() + "");
		Precios.updatePrecio(Precios.PRECIO_COMBUSTIBLE_SOCIO, spinnerPrecioCombustibleSocio.getValue() + "");
		Precios.updatePrecio(Precios.PRECIO_ACEITE_AEROCLUB, spinnerPrecioAceiteAeroclub.getValue() + "");
		Precios.updatePrecio(Precios.PRECIO_ACEITE_SOCIO, spinnerPrecioAceiteSocio.getValue() + "");

		JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente.");
	}

	@Override
	public void updateUi() {
		spinnerPrecioCombustibleAeroclub.setValue(Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB));
		spinnerPrecioCombustibleSocio.setValue(Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_SOCIO));
		spinnerPrecioAceiteAeroclub.setValue(Precios.getPrecio(Precios.PRECIO_ACEITE_AEROCLUB));
		spinnerPrecioAceiteSocio.setValue(Precios.getPrecio(Precios.PRECIO_ACEITE_SOCIO));

		dirty = false;

	}

}
