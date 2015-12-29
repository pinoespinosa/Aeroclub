package visual;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import data.Gasto;
import extended.JDialogExtended;


public class Compra_General extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JComboBox<String> tipoCompra;
	private JRadioButton buttonActivacion, buttonGasto;

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
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 30, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTipoDeCompra = new JLabel("Tipo de compra");
			GridBagConstraints gbc_lblTipoDeCompra = new GridBagConstraints();
			gbc_lblTipoDeCompra.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoDeCompra.anchor = GridBagConstraints.EAST;
			gbc_lblTipoDeCompra.gridx = 1;
			gbc_lblTipoDeCompra.gridy = 1;
			contentPanel.add(lblTipoDeCompra, gbc_lblTipoDeCompra);
		}
		{
			tipoCompra = new JComboBox<String>();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(tipoCompra, gbc_comboBox);
		}
		{
			JButton btnNuevoTipo = new JButton("Nueva cat. de compra");
			GridBagConstraints gbc_btnNuevoTipo = new GridBagConstraints();
			gbc_btnNuevoTipo.insets = new Insets(0, 0, 5, 5);
			gbc_btnNuevoTipo.gridx = 3;
			gbc_btnNuevoTipo.gridy = 1;
			contentPanel.add(btnNuevoTipo, gbc_btnNuevoTipo);
		}
		{
			JLabel lblDestinatario = new JLabel("Detalle de la compra");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.anchor = GridBagConstraints.EAST;
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 2;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 2;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 2;
			gbc_textField.gridy = 2;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblCantidad = new JLabel("Monto $");
			GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
			gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantidad.gridx = 1;
			gbc_lblCantidad.gridy = 3;
			contentPanel.add(lblCantidad, gbc_lblCantidad);
		}
		{
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Float(1), new Float(0), null, new Float(1)));
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.gridwidth = 2;
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 3;
			contentPanel.add(spinner, gbc_spinner);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 3;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
			{
				buttonActivacion = new JRadioButton("Activaci\u00F3n de bien de uso");
				panel.add(buttonActivacion);
			}
			{
				buttonGasto = new JRadioButton("Gasto");
				buttonGasto.setSelected(true);
				panel.add(buttonGasto);
			}
			
			
			ButtonGroup bG = new ButtonGroup();
			   bG.add(buttonActivacion);
			   bG.add(buttonGasto);
		}
		{
			JButton btnCrear = new JButton("Crear");
			GridBagConstraints gbc_btnCrear = new GridBagConstraints();
			gbc_btnCrear.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
			gbc_btnCrear.gridx = 3;
			gbc_btnCrear.gridy = 6;
			contentPanel.add(btnCrear, gbc_btnCrear);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		init();
	}
	
	@PostConstruct
	public void init(){

		List<Gasto> gastos = Gasto.loadFromDB();
		
		Set<String> tiposGasto = new HashSet<String>(); 
		
		for (Gasto gasto : gastos) {
			tiposGasto.add(gasto.getTipo());
		}
		
		for (String string : tiposGasto) {
			tipoCompra.addItem(string);
		}
		
		
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
	

}
