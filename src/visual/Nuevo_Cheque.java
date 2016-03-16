package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import base_datos.managerDB;
import extended.JDialogExtended;
import extended.MainController;

public class Nuevo_Cheque extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JButton chequeButton;
	private JLabel lblNewLabel;
	private JTextField bancoCheque;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JLabel lblVencimiento;
	private JSpinner montoSpinner;
	private JSpinner numeroCheque;
	private JSpinner vencimientoSpinner;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Nuevo_Cheque(Window parent) {
		super(parent);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBackground(Color.GREEN);
		setUndecorated(true);
		setTitle("Nuevo Cheque");

		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{159, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panel.setOpaque(false);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{40, 256, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 145, 0, 10, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				lblNewLabel_1 = new JLabel("Ingrese los datos del cheque");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 1;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			GridBagConstraints gbc_contentPanel = new GridBagConstraints();
			gbc_contentPanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_contentPanel.anchor = GridBagConstraints.NORTH;
			gbc_contentPanel.insets = new Insets(0, 0, 5, 5);
			gbc_contentPanel.gridx = 1;
			gbc_contentPanel.gridy = 2;
			panel.add(contentPanel, gbc_contentPanel);
			contentPanel.setOpaque(false);
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
			gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 20, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			{
				JLabel lblPersona = new JLabel("N\u00FAmero de cheque");
				GridBagConstraints gbc_lblPersona = new GridBagConstraints();
				gbc_lblPersona.anchor = GridBagConstraints.EAST;
				gbc_lblPersona.insets = new Insets(0, 0, 5, 5);
				gbc_lblPersona.gridx = 1;
				gbc_lblPersona.gridy = 1;
				contentPanel.add(lblPersona, gbc_lblPersona);
			}
			{
				numeroCheque = new JSpinner();
				numeroCheque.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				GridBagConstraints gbc_numeroCheque = new GridBagConstraints();
				gbc_numeroCheque.fill = GridBagConstraints.HORIZONTAL;
				gbc_numeroCheque.insets = new Insets(0, 0, 5, 5);
				gbc_numeroCheque.gridx = 2;
				gbc_numeroCheque.gridy = 1;
				contentPanel.add(numeroCheque, gbc_numeroCheque);
			}
			{
				lblNewLabel = new JLabel("Banco emisor");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 2;
				contentPanel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				bancoCheque = new JTextField();
				GridBagConstraints gbc_bancoCheque = new GridBagConstraints();
				gbc_bancoCheque.insets = new Insets(0, 0, 5, 5);
				gbc_bancoCheque.fill = GridBagConstraints.HORIZONTAL;
				gbc_bancoCheque.gridx = 2;
				gbc_bancoCheque.gridy = 2;
				contentPanel.add(bancoCheque, gbc_bancoCheque);
				bancoCheque.setColumns(10);
			}
			{
				lblNombre = new JLabel("Monto del cheque");
				GridBagConstraints gbc_lblNombre = new GridBagConstraints();
				gbc_lblNombre.anchor = GridBagConstraints.EAST;
				gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
				gbc_lblNombre.gridx = 1;
				gbc_lblNombre.gridy = 3;
				contentPanel.add(lblNombre, gbc_lblNombre);
			}
			{
				montoSpinner = new JSpinner();
				montoSpinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				GridBagConstraints gbc_montoSpinner = new GridBagConstraints();
				gbc_montoSpinner.fill = GridBagConstraints.HORIZONTAL;
				gbc_montoSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_montoSpinner.gridx = 2;
				gbc_montoSpinner.gridy = 3;
				contentPanel.add(montoSpinner, gbc_montoSpinner);
			}
			{
				lblVencimiento = new JLabel("Vencimiento");
				GridBagConstraints gbc_lblVencimiento = new GridBagConstraints();
				gbc_lblVencimiento.anchor = GridBagConstraints.EAST;
				gbc_lblVencimiento.insets = new Insets(0, 0, 0, 5);
				gbc_lblVencimiento.gridx = 1;
				gbc_lblVencimiento.gridy = 4;
				contentPanel.add(lblVencimiento, gbc_lblVencimiento);
			}
			{
				vencimientoSpinner = new JSpinner();
				vencimientoSpinner.setModel(new SpinnerDateModel(new Date(1458010800000L), null, null, Calendar.DAY_OF_YEAR));
				GridBagConstraints gbc_spinner = new GridBagConstraints();
				gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner.insets = new Insets(0, 0, 0, 5);
				gbc_spinner.gridx = 2;
				gbc_spinner.gridy = 4;
				contentPanel.add(vencimientoSpinner, gbc_spinner);
				vencimientoSpinner.setEditor(new JSpinner.DateEditor(vencimientoSpinner, "dd/MM/yyyy"));
			}
			{
				chequeButton = new JButton("Crear Cheque");
				GridBagConstraints gbc_chequeButton = new GridBagConstraints();
				gbc_chequeButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_chequeButton.insets = new Insets(0, 0, 5, 5);
				gbc_chequeButton.gridx = 1;
				gbc_chequeButton.gridy = 3;
				panel.add(chequeButton, gbc_chequeButton);
				chequeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (isValidCheque()){	
							managerDB.executeScript_Void( " INSERT INTO `"+MainController.getEsquema()+"`.`cheque` VALUES ('" + managerDB.getNextId("cheque") + "','" + numeroCheque.getValue() + "','" + bancoCheque.getText() + "','" + montoSpinner.getValue() + "','" + ((Date)vencimientoSpinner.getValue()).getTime() + "');");
							JOptionPane.showMessageDialog(null, "Se han registrados los datos del cheque.");
							Nuevo_Cheque.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "Por favor complete TODOS los datos del cheque.");
					}
				});
				chequeButton.setActionCommand("OK");
				getRootPane().setDefaultButton(chequeButton);
			}
		}
		inic();
	}

	private boolean isValidCheque(){
		return !bancoCheque.getText().isEmpty();	
	}
	
	@PostConstruct
	private void inic() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		

	}

	@Override
	public void updateUi() {
	
	}
}
