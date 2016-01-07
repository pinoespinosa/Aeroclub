package visual;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

import data.Precios;
import extended.JDialogExtended;
import extended.MainController;


public class Administrar_General extends JDialogExtended {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
private JSpinner spinnerPrecioCombustibleSocio, spinnerPrecioCombustibleAeroclub;	
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Administrar_General(JFrame parent) {
		super(parent);
		setTitle("Nueva Compra");
		setBounds(100, 100, 878, 559);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{444, 0};
		gridBagLayout.rowHeights = new int[]{67, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
			gbc_tabbedPane.gridx = 0;
			gbc_tabbedPane.gridy = 0;
			getContentPane().add(tabbedPane, gbc_tabbedPane);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Precios Aviones", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
				gbl_panel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
					gbc_spinnerPrecioCombustibleSocio.insets = new Insets(0, 0, 5, 0);
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
					gbc_spinnerPrecioCombustibleAeroclub.insets = new Insets(0, 0, 5, 0);
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
					GridBagConstraints gbc_btnGuardarCambios = new GridBagConstraints();
					gbc_btnGuardarCambios.gridwidth = 2;
					gbc_btnGuardarCambios.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnGuardarCambios.gridx = 1;
					gbc_btnGuardarCambios.gridy = 15;
					panel.add(btnGuardarCambios, gbc_btnGuardarCambios);
				}
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("New tab", null, panel, null);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0};
				gbl_panel.rowHeights = new int[]{0, 0};
				gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JButton btnNewButton = new JButton("New button");
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.gridx = 0;
					gbc_btnNewButton.gridy = 0;
					panel.add(btnNewButton, gbc_btnNewButton);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
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
	@PostConstruct 
	private void inic()
	{
		setAction(MainController.ACTION_EXIT);
		
		spinnerPrecioCombustibleAeroclub.setValue(Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB));
		spinnerPrecioCombustibleSocio.setValue(Precios.getPrecio(Precios.PRECIO_COMBUSTIBLE_SOCIO));

	}
	
	private void saveChanges(){
		Precios.updatePrecio(Precios.PRECIO_COMBUSTIBLE_AEROCLUB,spinnerPrecioCombustibleAeroclub.getValue()+"");
		Precios.updatePrecio(Precios.PRECIO_COMBUSTIBLE_SOCIO,spinnerPrecioCombustibleSocio.getValue()+"");
		
		JOptionPane.showMessageDialog(null,"Los cambios se guardaron correctamente.");
	}
	
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}

}
