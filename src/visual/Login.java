package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import base_datos.managerDB;
import extended.JDialogExtended;
import extended.MainController;

public class Login extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField dniTextField;
	private JLabel lblNombre;
	private JButton loginButton, cancelButton;
	private JPasswordField passwordField;
	private JLabel image;
	private JLabel lblDesarrolladoPorAndres;
	private JLabel wait;
	
	
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Login(Window parent) {
		super(parent);
		setTitle("Ingreso al sistema");

		setBounds(100, 100, 510, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 100, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			image = new JLabel("");
			image.setIcon(new ImageIcon(Login.class.getResource("/resources/logo.png")));
			GridBagConstraints gbc_image = new GridBagConstraints();
			gbc_image.gridwidth = 2;
			gbc_image.insets = new Insets(0, 0, 5, 5);
			gbc_image.gridx = 1;
			gbc_image.gridy = 1;
			contentPanel.add(image, gbc_image);
		}
		{
			JLabel lblPersona = new JLabel("DNI");
			GridBagConstraints gbc_lblPersona = new GridBagConstraints();
			gbc_lblPersona.anchor = GridBagConstraints.EAST;
			gbc_lblPersona.insets = new Insets(0, 0, 5, 5);
			gbc_lblPersona.gridx = 1;
			gbc_lblPersona.gridy = 2;
			contentPanel.add(lblPersona, gbc_lblPersona);
		}
		{
			dniTextField = new JTextField();

			GridBagConstraints gbc_dniTextField = new GridBagConstraints();
			gbc_dniTextField.insets = new Insets(0, 0, 5, 5);
			gbc_dniTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dniTextField.gridx = 2;
			gbc_dniTextField.gridy = 2;
			contentPanel.add(dniTextField, gbc_dniTextField);
			dniTextField.setColumns(10);
		}
		{
			lblNombre = new JLabel("Password");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.EAST;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 3;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 2;
			gbc_passwordField.gridy = 3;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			wait = new JLabel("   cargando . . .");
			wait.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			wait.setVisible(false);
			wait.setIcon(new ImageIcon(Login.class.getResource("/resources/wait.gif")));
			GridBagConstraints gbc_wait = new GridBagConstraints();
			gbc_wait.gridwidth = 2;
			gbc_wait.fill = GridBagConstraints.VERTICAL;
			gbc_wait.insets = new Insets(0, 0, 5, 5);
			gbc_wait.gridx = 1;
			gbc_wait.gridy = 4;
			contentPanel.add(wait, gbc_wait);
		}
		{
			lblDesarrolladoPorAndres = new JLabel("Desarrollado por Andr\u00E9s Espinosa - pino.espinosa91@gmail.com");
			lblDesarrolladoPorAndres.setForeground(Color.GRAY);
			lblDesarrolladoPorAndres.setFont(new Font("Tahoma", Font.ITALIC, 11));
			GridBagConstraints gbc_lblDesarrolladoPorAndres = new GridBagConstraints();
			gbc_lblDesarrolladoPorAndres.anchor = GridBagConstraints.EAST;
			gbc_lblDesarrolladoPorAndres.gridwidth = 4;
			gbc_lblDesarrolladoPorAndres.gridx = 0;
			gbc_lblDesarrolladoPorAndres.gridy = 5;
			contentPanel.add(lblDesarrolladoPorAndres, gbc_lblDesarrolladoPorAndres);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				loginButton = new JButton("Ingresar");
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				cancelButton = new JButton("Salir");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							dispose();
						} catch (Throwable e) {
							e.printStackTrace();
						}
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

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (dniTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Verifique su dni y contraseña.");
					return;
				}
				String script = "SELECT * FROM `" + MainController.getEsquema() + "`.usuario	WHERE dni like " + dniTextField.getText() + " and password like " + passwordField.getText() + ";";
				final List<String> perfil = managerDB.executeScript_Query(script, "perfil");

				if (!perfil.isEmpty()) {
					wait.setVisible(true);
					loginButton.setEnabled(false);
					cancelButton.setEnabled(false);

					try {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {

								MainClass window = new MainClass(perfil.get(0), Login.this);

							}
						});
					} catch (Exception e) {
						e.printStackTrace();

					}
				}

				else
					JOptionPane.showMessageDialog(null, "Verifique su dni y contraseña.");
			}
		});

	}
	
	
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub

	}

	
}

