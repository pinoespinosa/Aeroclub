package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import data.Avion;
import extended.JDialogExtended;

public class Nuevo_Avion extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nombreAvionTextField;
	private List<Avion> aviones;
	private JTextPane info;
	private JLabel lblNombre;
	private JButton okButton;
	private JSpinner spinner;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Nuevo_Avion(Window parent) {
		super(parent);
		setTitle("Nuevo Piloto");
		
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
			JLabel lblPersona = new JLabel("Nombre del avion");
			GridBagConstraints gbc_lblPersona = new GridBagConstraints();
			gbc_lblPersona.anchor = GridBagConstraints.WEST;
			gbc_lblPersona.insets = new Insets(0, 0, 5, 5);
			gbc_lblPersona.gridx = 1;
			gbc_lblPersona.gridy = 1;
			contentPanel.add(lblPersona, gbc_lblPersona);
		}
		{
			nombreAvionTextField = new JTextField();

			
			GridBagConstraints gbc_dniTextField = new GridBagConstraints();
			gbc_dniTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dniTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dniTextField.gridx = 2;
			gbc_dniTextField.gridy = 1;
			contentPanel.add(nombreAvionTextField, gbc_dniTextField);
			nombreAvionTextField.setColumns(10);
		}
		
		{
			info = new JTextPane();
			GridBagConstraints gbc_info = new GridBagConstraints();
			gbc_info.fill = GridBagConstraints.HORIZONTAL;
			gbc_info.insets = new Insets(0, 0, 5, 5);
			gbc_info.gridx = 2;
			gbc_info.gridy = 2;
			contentPanel.add(info, gbc_info);
		}
		{
			lblNombre = new JLabel("Precio");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.WEST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 3;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			spinner = new JSpinner();
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 3;
			contentPanel.add(spinner, gbc_spinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Crear Piloto");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		inic();
	}
	
	@PostConstruct
	private void inic() {
		aviones = Avion.loadFromDB();
	
		nombreAvionTextField.addKeyListener(new KeyAdapter(){
	        public void keyReleased(KeyEvent ke){
	          updateView();
	        }
	    });
		
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Validacion
		/*		if (nombreTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"El nombre del piloto no puede ser vacio.");	
					return;
				}
				if (apellidoTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"El apellido del piloto no puede ser vacio.");	
					return;
				}
				
				int dni = Integer.parseInt(dniTextField.getText());
				Persona pe = new Persona(dni);
				
				// Creo la persona si no existia
				if (!aviones.contains(pe)) {
					managerDB.executeScript_Void("INSERT INTO `aviones`.`persona` VALUES ('"+managerDB.getNextId("persona")+"', '"+dni+"' ,'"+nombreTextField.getText()+"','"+apellidoTextField.getText()+"','"+((Date) nacimientoSpinner.getModel().getValue()).getTime()+"');");
					aviones = Persona.loadFromDB();
				}
			*/	
				// Creo el piloto
			//	pe= aviones.get(aviones.indexOf(pe));
			//	managerDB.executeScript_Void(" INSERT INTO `aviones`.`piloto` VALUES ('"+ pe.getId()+"','"+ ((Date) vencimientoLicenciaSpinner.getModel().getValue()).getTime() +"');");
			//	JOptionPane.showMessageDialog(null,"Se creo un nuevo piloto.");					
				Nuevo_Avion.this.dispose();	
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
		
		for (Avion av : aviones) {
			if(av.getName().equals(nombreAvionTextField.getText())){
				setEditable(false);
				return;}
			else
				setEditable(true);
			
		}
		
			
	}
	
	private void setEditable(boolean editable){
		
		
		
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
}
