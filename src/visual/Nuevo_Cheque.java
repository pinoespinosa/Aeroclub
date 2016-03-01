package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import data.Avion;
import extended.JDialogExtended;
import extended.MainController;

public class Nuevo_Cheque extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<Avion> aviones;
	private JLabel lblNombre;
	private JButton chequeButton;
	private JLabel lblNewLabel;
	private JTextField numeroCheque;
	private JTextField bancoCheque;
	private JTextField montoCheque;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Nuevo_Cheque(Window parent) {
		super(parent);
		setTitle("Nuevo Cheque");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 20, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			numeroCheque = new JTextField();
			GridBagConstraints gbc_numeroCheque = new GridBagConstraints();
			gbc_numeroCheque.insets = new Insets(0, 0, 5, 5);
			gbc_numeroCheque.fill = GridBagConstraints.HORIZONTAL;
			gbc_numeroCheque.gridx = 2;
			gbc_numeroCheque.gridy = 1;
			contentPanel.add(numeroCheque, gbc_numeroCheque);
			numeroCheque.setColumns(10);
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
			montoCheque = new JTextField();
			GridBagConstraints gbc_montoCheque = new GridBagConstraints();
			gbc_montoCheque.insets = new Insets(0, 0, 5, 5);
			gbc_montoCheque.fill = GridBagConstraints.HORIZONTAL;
			gbc_montoCheque.gridx = 2;
			gbc_montoCheque.gridy = 3;
			contentPanel.add(montoCheque, gbc_montoCheque);
			montoCheque.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				chequeButton = new JButton("Crear Cheque");
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
		setAction(MainController.ACTION_REACTIVAR_PADRE);
		aviones = Avion.loadFromDB();
		
		chequeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Validacion
				if (nombreAvionTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"El nombre del avion no puede ser vacio.");	
					return;
				}
				
				for (Avion av : aviones) {
					if(av.getName().equals(nombreAvionTextField.getText())){
						setEditable(false);
						JOptionPane.showMessageDialog(null,"Ya existe un avion con ese nombre.");	
						return;}
					else
						setEditable(true);
				}
			// Creo el piloto
				managerDB.executeScript_Void(" INSERT INTO `"+MainController.getEsquema()+"`.`avion` VALUES ('"+ managerDB.getNextId("avion")+"','"+nombreAvionTextField.getText()+"','"+spinnerPrecio.getValue()+"" +"');");
			//	JOptionPane.showMessageDialog(null,"Se creo un nuevo piloto.");					
				Nuevo_Cheque.this.dispose();	
			}
		});
		updateView();
	
	}
	
	private void updateView(){
		
		aviones = Avion.loadFromDB();
				
		if (nombreAvionTextField.getText().isEmpty()){
			setEditable(false);
			return;
		}
		

			
		
		
			
	}
	
	private void setEditable(boolean editable){
		
		
		
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
}
