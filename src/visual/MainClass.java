package visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import visual.venta.Venta;
import base_datos.DateUtils;
import base_datos.Utils;
import base_datos.managerDB;
import data.Vencimiento;
import extended.JDialogExtended;
import extended.MainController;

public class MainClass extends JDialogExtended{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JList<Vencimiento> list;
	private DefaultListModel<Vencimiento> vencimientoList;
	private static Process p;
	private JLabel lblTiempoLicencia;
	private JButton btnNuevaVenta, btnNuevaCompra, btnVerInformes, btnAdministrar;
	private static PrintStream pst;
	
	
	public enum Profiles {	ADMIN, DATAENTRY, VIEWER 	}
	
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				//	pst = new PrintStream(args[0]);  

//					System.setOut(pst);

	//				System.setErr(pst);

					
					// Carga las propiedades y el lookAndFeel
					MainController.loadProperties();
					
					System.out.println(Utils.encript("1475623260000"));
					
					// Regenero la vista de vencimientos por si se modifican los limites minimos
					managerDB.executeScript_Void("DROP VIEW `" + MainController.getEsquema() + "`.vencimientosproximos; ");
					managerDB.executeScript_Void(Vencimiento.getViewStript());
					
					try {	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");		} catch (Exception e) {	};
				
					Login loginView = new Login(null);
					loginView.setAction(MainController.ACTION_CONTINUE);
					loginView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					loginView.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainClass(final String perfil, final Window parent) {
		super(parent);

		// Administrar_General.this.setEnabled(false);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
	
				initialize(perfil);
				setProfilePermission(perfil);
				parent.dispose();

			}
		});
		

	}

	
	private void setProfilePermission(String perfil) {

		Profiles per = Profiles.valueOf(perfil);

		switch (per) {

			case ADMIN :
				break;

			case DATAENTRY : {
				btnAdministrar.setVisible(false);
			}
				break;

			case VIEWER : {
				btnAdministrar.setVisible(false);
				btnNuevaCompra.setVisible(false);
				btnNuevaVenta.setVisible(false);
			}
				break;

			default :
				break;

		}
		this.frame.setVisible(true);
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String perfil) {
		frame = new JFrame(){

			private static final long serialVersionUID = -2459113721298506488L;

			@Override
			public void setEnabled(boolean arg0) {
				super.setEnabled(arg0);
				MainClass.this.updateUi();
			}
			
		};
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

			String pathGit = MainController.getProperties().get("UBICACION_GIT");
				String pathGitScript = MainController.getProperties().get("UBICACION_GIT_SCRIPT");
				
				String[] commands = {pathGit, "-i", pathGitScript};
				ProcessBuilder pBuilder = new ProcessBuilder(commands);
				pBuilder.redirectOutput();
				pBuilder.inheritIO();
				pBuilder.redirectOutput();
				pBuilder.redirectOutput();
				pBuilder.redirectOutput();

				try {
					p = pBuilder.start();
					InputStream in = p.getInputStream();
					final Scanner scanner = new Scanner(in);
					new Thread(new Runnable() {
						public void run() {
							while (scanner.hasNextLine()) {
								System.out.println(scanner.nextLine());
							}
							scanner.close();
						}
					}).start();
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println();

			}
		});

		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		frame.setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");
		frame.setBounds(100, 100, 700, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 50, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		btnNuevaVenta = new JButton("Nueva Venta / Deposito");
		btnNuevaVenta.setIcon(new ImageIcon(MainClass.class.getResource("/resources/icon_nueva_venta.png")));
		btnNuevaVenta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNuevaVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				/*
				 * --------------- Nueva Venta -----------------
				 */
				Venta dialog = new Venta(frame);
				MainController.sleepActualFrameAndCreateNew(MainClass.this, dialog, frame);
			}
		});

		lblTiempoLicencia = new JLabel("La licencia del sistema expira el ");
		lblTiempoLicencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTiempoLicencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblTiempoLicencia, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		frame.getContentPane().add(btnNuevaVenta, gbc_btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 3;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{50, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblVencimientoYAlertas = new JLabel("Vencimiento y alertas");
		lblVencimientoYAlertas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVencimientoYAlertas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		GridBagConstraints gbc_lblVencimientoYAlertas = new GridBagConstraints();
		gbc_lblVencimientoYAlertas.fill = GridBagConstraints.BOTH;
		gbc_lblVencimientoYAlertas.insets = new Insets(0, 0, 5, 0);
		gbc_lblVencimientoYAlertas.gridx = 0;
		gbc_lblVencimientoYAlertas.gridy = 0;
		panel.add(lblVencimientoYAlertas, gbc_lblVencimientoYAlertas);

		list = new JList<Vencimiento>();
		list.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);

		btnNuevaCompra = new JButton("Nueva Compra");
		btnNuevaCompra.setIcon(new ImageIcon(MainClass.class.getResource("/resources/icon_nueva_compra.png")));
		btnNuevaCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * --------------- Nueva Compra -----------------
				 */
				Compra_General dialog = new Compra_General(frame);
				MainController.sleepActualFrameAndCreateNew(MainClass.this, dialog, frame);
			}
		});
		btnNuevaCompra.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		frame.getContentPane().add(btnNuevaCompra, gbc_btnNewButton);

		btnVerInformes = new JButton("Ver informes");
		btnVerInformes.setIcon(new ImageIcon(MainClass.class.getResource("/resources/icon_informes.png")));
		btnVerInformes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnVerInformes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				/*
				 * --------------- Nuevo Informe -----------------
				 */
				
				Informes dialog = new Informes(frame);
				MainController.sleepActualFrameAndCreateNew(MainClass.this, dialog, frame);
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 7;
		frame.getContentPane().add(btnVerInformes, gbc_btnNewButton_2);

		btnAdministrar = new JButton("Administrar");
		btnAdministrar.setIcon(new ImageIcon(MainClass.class.getResource("/resources/icon_admi.png")));
		btnAdministrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnAdministrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				/*
				 * ------------ Administrar ---------------------
				 */
				
				Administrar_General dialog = new Administrar_General(frame);
				MainController.sleepActualFrameAndCreateNew(MainClass.this, dialog, frame);
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 9;
		frame.getContentPane().add(btnAdministrar, gbc_btnNewButton_3);

		postContructor();
	}
	@PostConstruct
	public void postContructor() {

		try {
			verificarFecha();
		} catch (IOException e) {
			ingresarSinValicion();

		}

		MainController.setViewConfig(frame);
		list.setFont(new Font("Consolas", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vencimientoList = new DefaultListModel<Vencimiento>();
		list.setModel(vencimientoList);
		updateVencimientos();
	}

	public void updateVencimientos() {
		List<Vencimiento> listaV = Vencimiento.loadFromDB();
		vencimientoList.removeAllElements();

		for (Vencimiento vencimiento : listaV) {
			vencimientoList.addElement(vencimiento);
		}

	}

	private void deshabilitarSistema() {
		btnAdministrar.setEnabled(false);
		btnNuevaCompra.setEnabled(false);
		btnNuevaVenta.setEnabled(false);
		btnVerInformes.setEnabled(false);
	}

	private void verificarFecha() throws IOException {

		Date horaInternet = DateUtils.getAtomicTime().getTime();

		String fVencLic = managerDB.executeScript_Query("SELECT dato FROM " + MainController.getEsquema() + ".licencia WHERE valor='fecha';", "dato").get(0);
		fVencLic = Utils.decript(fVencLic);

		Date fechaVencLicen = new Date(Long.parseLong(fVencLic));

		long tiempoPendienteLicencia = fechaVencLicen.getTime() - horaInternet.getTime();

		if (tiempoPendienteLicencia > 0) {					
			lblTiempoLicencia.setText("La licencia del sistema expira en " + TimeUnit.DAYS.convert(tiempoPendienteLicencia, TimeUnit.MILLISECONDS) + " dias. El día " + DateUtils.toTraditionalFormat(fechaVencLicen));
			managerDB.executeScript_Void("UPDATE " + MainController.getEsquema() + ".licencia SET dato='10' WHERE valor='intentos';");
		} else {
			lblTiempoLicencia.setText("La licencia del sistema expiró. Consulte con su administrador. Dirección de email: pino.espinosa91@gmail.com");
			deshabilitarSistema();
		}
		MainController.setLicenciaValida(tiempoPendienteLicencia > 0);

	}

	private void ingresarSinValicion() {
		int intentos = Integer.parseInt(managerDB.executeScript_Query("SELECT dato FROM " + MainController.getEsquema() + ".licencia WHERE valor='intentos';", "dato").get(0));

		MainController.setLicenciaValida(intentos > 0);

		intentos--;
		if (intentos > 0) {
			managerDB.executeScript_Void("UPDATE " + MainController.getEsquema() + ".licencia SET dato='" + intentos + "' WHERE valor='intentos';");
			lblTiempoLicencia.setText("El sistema no pudo conectarse a internet para validar la licencia.");
		} else {
			lblTiempoLicencia.setText("El sistema no pudo conectarse a internet para validar la licencia en repetidas veces. Algunas funciones estan temporalmente invalidas");
			deshabilitarSistema();
		}
	}

	@Override
	public void updateUi() {
		updateVencimientos();
	}

}
