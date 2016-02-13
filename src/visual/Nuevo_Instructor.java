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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import data.Instructor;
import data.Persona;
import extended.JDialogExtended;
import extended.MainController;

public class Nuevo_Instructor extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner vencimientoLicenciaSpinner;
	private JTextField dniTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private List<Persona> personas;
	private List<Instructor> instructores;
	private JTextPane info;
	private JLabel lblNombre, lblApellido, lblFechaNaciemiento, lblFechaVencimientoLicencia;
	private JSpinner nacimientoSpinner;
	private JButton okButton;
	
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public Nuevo_Instructor(Window parent) {
		super(parent);
		setTitle("Nuevo Instructor");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 20, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblPersona = new JLabel("Dni");
			GridBagConstraints gbc_lblPersona = new GridBagConstraints();
			gbc_lblPersona.anchor = GridBagConstraints.WEST;
			gbc_lblPersona.insets = new Insets(0, 0, 5, 5);
			gbc_lblPersona.gridx = 1;
			gbc_lblPersona.gridy = 1;
			contentPanel.add(lblPersona, gbc_lblPersona);
		}
		{
			dniTextField = new JTextField();
			dniTextField.addKeyListener(new KeyAdapter()
		    {
		        public void keyReleased(KeyEvent ke)
		        {
		          updateView();
		        }
		    });
			
			GridBagConstraints gbc_dniTextField = new GridBagConstraints();
			gbc_dniTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dniTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dniTextField.gridx = 2;
			gbc_dniTextField.gridy = 1;
			contentPanel.add(dniTextField, gbc_dniTextField);
			dniTextField.setColumns(10);
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
			lblNombre = new JLabel("Nombre");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.WEST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 3;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			nombreTextField = new JTextField();
			GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
			gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
			gbc_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nombreTextField.gridx = 2;
			gbc_nombreTextField.gridy = 3;
			contentPanel.add(nombreTextField, gbc_nombreTextField);
			nombreTextField.setColumns(10);
		}
		{
			lblApellido = new JLabel("Apellido");
			GridBagConstraints gbc_lblApellido = new GridBagConstraints();
			gbc_lblApellido.anchor = GridBagConstraints.WEST;
			gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
			gbc_lblApellido.gridx = 1;
			gbc_lblApellido.gridy = 4;
			contentPanel.add(lblApellido, gbc_lblApellido);
		}
		{
			apellidoTextField = new JTextField();
			GridBagConstraints gbc_apellidoTextField = new GridBagConstraints();
			gbc_apellidoTextField.insets = new Insets(0, 0, 5, 5);
			gbc_apellidoTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_apellidoTextField.gridx = 2;
			gbc_apellidoTextField.gridy = 4;
			contentPanel.add(apellidoTextField, gbc_apellidoTextField);
			apellidoTextField.setColumns(10);
		}
		{
			lblFechaNaciemiento = new JLabel("Fecha Naciemiento");
			GridBagConstraints gbc_lblFechaNaciemiento = new GridBagConstraints();
			gbc_lblFechaNaciemiento.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblFechaNaciemiento.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaNaciemiento.gridx = 1;
			gbc_lblFechaNaciemiento.gridy = 5;
			contentPanel.add(lblFechaNaciemiento, gbc_lblFechaNaciemiento);
		}
		{
			nacimientoSpinner = new JSpinner();
			GridBagConstraints gbc_nacimientoSpinner = new GridBagConstraints();
			gbc_nacimientoSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_nacimientoSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_nacimientoSpinner.gridx = 2;
			gbc_nacimientoSpinner.gridy = 5;
			contentPanel.add(nacimientoSpinner, gbc_nacimientoSpinner);
		}
		{
			lblFechaVencimientoLicencia = new JLabel("Fecha Vencimiento Psicof\u00EDsico");
			GridBagConstraints gbc_lblFechaVencimientoLicencia = new GridBagConstraints();
			gbc_lblFechaVencimientoLicencia.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblFechaVencimientoLicencia.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaVencimientoLicencia.gridx = 1;
			gbc_lblFechaVencimientoLicencia.gridy = 6;
			contentPanel.add(lblFechaVencimientoLicencia, gbc_lblFechaVencimientoLicencia);
		}
		{
			vencimientoLicenciaSpinner = new JSpinner();
						
			GridBagConstraints gbc_VencimientoLicenciaSpinner = new GridBagConstraints();
			gbc_VencimientoLicenciaSpinner.insets = new Insets(0, 0, 5, 5);
			gbc_VencimientoLicenciaSpinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_VencimientoLicenciaSpinner.gridx = 2;
			gbc_VencimientoLicenciaSpinner.gridy = 6;
			contentPanel.add(vencimientoLicenciaSpinner, gbc_VencimientoLicenciaSpinner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Crear Instructor");
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
		personas = Persona.loadFromDB();
		instructores = Instructor.loadFromDB();

		nacimientoSpinner.setModel(new SpinnerDateModel(new Date(System.currentTimeMillis()), null, null, Calendar.DAY_OF_YEAR));
		vencimientoLicenciaSpinner.setModel(new SpinnerDateModel(new Date(System.currentTimeMillis()), null, null, Calendar.DAY_OF_YEAR));

		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Validacion
				if (nombreTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"El nombre del instructor no puede ser vacio.");	
					return;
				}
				if (apellidoTextField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"El apellido del instructor no puede ser vacio.");	
					return;
				}
				
				int dni = Integer.parseInt(dniTextField.getText());
				Persona pe = new Persona(dni);
				

				if (!personas.contains(pe)) {
					managerDB.executeScript_Void("INSERT INTO `"+MainController.getEsquema()+"`.`persona` VALUES ('"+managerDB.getNextId("persona")+"', '"+dni+"' ,'"+nombreTextField.getText()+"','"+apellidoTextField.getText()+"','"+((Date) nacimientoSpinner.getModel().getValue()).getTime()+"');");
					personas = Persona.loadFromDB();
				}
				
				
				pe= personas.get(personas.indexOf(pe));
				managerDB.executeScript_Void(" INSERT INTO `"+MainController.getEsquema()+"`.`instructor` VALUES ('"+ pe.getId()+"','"+ ((Date) vencimientoLicenciaSpinner.getModel().getValue()).getTime() +"');");
				JOptionPane.showMessageDialog(null,"Se creo un nuevo instructor.");					
				Nuevo_Instructor.this.dispose();	
			}
		});

		updateView();
	}
	
	private void updateView(){
		
		personas = Persona.loadFromDB();
		instructores = Instructor.loadFromDB();
				
		if (dniTextField.getText().isEmpty()){
			setEditable(false);
			return;
		}
		
		int dni = Integer.parseInt(dniTextField.getText());
		
		Instructor inst = new Instructor();
		inst.setDni(dni);
		
		if (instructores.contains(inst) ){
			
			inst= instructores.get(instructores.indexOf(inst));
			
			setEditable(false);
			info.setText("Ya se encuentra registrado el piloto");
			vencimientoLicenciaSpinner.setEnabled(false);
			lblFechaVencimientoLicencia.setEnabled(false);
			nombreTextField.setText(inst.getName());
			apellidoTextField.setText(inst.getApellido());
			nacimientoSpinner.getModel().setValue( new Date(inst.getNacimiento()) );
			vencimientoLicenciaSpinner.getModel().setValue( new Date(inst.getFecha_licencia()) );
			okButton.setEnabled(false);
			return;
		}
		
		Persona pe = new Persona(dni);
		
		if (personas.contains(pe) ){
			pe= personas.get(personas.indexOf(pe));
			setEditable(false);
			info.setText("Los datos de esta persona ya se encuentran en el sistema. Se registrara a continuación como piloto");
			nombreTextField.setText(pe.getName());
			apellidoTextField.setText(pe.getApellido());
			nacimientoSpinner.getModel().setValue( new Date(pe.getNacimiento()) );
			vencimientoLicenciaSpinner.getModel().setValue( new Date( System.currentTimeMillis() ) );
			
		}
		else{
			setEditable(true);
			info.setText("");
			nombreTextField.setText("");
			apellidoTextField.setText("");
			nacimientoSpinner.getModel().setValue( new Date( System.currentTimeMillis()) );
			vencimientoLicenciaSpinner.getModel().setValue( new Date( System.currentTimeMillis() ) );
			
		}
		
	}
	
	private void setEditable(boolean editable){
				
		nombreTextField.setEnabled(editable);
		lblNombre.setEnabled(editable);
		
		apellidoTextField.setEnabled(editable);
		lblApellido.setEnabled(editable);
		
		nacimientoSpinner.setEnabled(editable);
		lblFechaNaciemiento.setEnabled(editable);
		
		lblFechaVencimientoLicencia.setEnabled(true);
		vencimientoLicenciaSpinner.setEnabled(true);
		
		okButton.setEnabled(true);
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
}
