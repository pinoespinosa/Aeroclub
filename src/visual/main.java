package visual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import base_datos.DateUtils;
import data.Vencimiento;
import extended.MainController;


public class main {

	private JFrame frame;
	private JList<Vencimiento> list;
	private DefaultListModel<Vencimiento> vencimientoList;
	private static Process p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
			
				 String[] commands = {"I:/Program Files/Git/git-bash.exe", "-i", "I:/Users/Pino/git/Aeroclub/git.sh"};
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
				
				
				/*
				try {
					Runtime.getRuntime().exec("I:/Program Files/Git/git-bash.exe -i I:/Users/Pino/git/Aeroclub/git.sh");
				} catch (IOException e) {
					e.printStackTrace();
				}
			*/
			}
		});

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		};
		
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
		
		JButton btnNewButton_1 = new JButton("Nueva Venta");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	
		/* ------------------------------- Nueva venta ------------------------------- */
				frame.setEnabled(false);
				Venta dialog = new Venta(frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				}
		});
		
		JLabel lblNewLabel = new JLabel("La licencia del sistema expira el ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
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
		
		JButton btnNewButton = new JButton("Nueva Compra");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* ------------------------------- Nueva venta ------------------------------- */
				frame.setEnabled(false);
				Compra_General dialog = new Compra_General(frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Ver informes");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 7;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Administrar");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	
		/* ------------------------------- Nueva venta ------------------------------- */
				frame.setEnabled(false);
				Administrar_General dialog = new Administrar_General(frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 9;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		postContructor();
	}
	@PostConstruct
	public void postContructor(){
		
		
		
		
		
		
		
		
		
	
	    try {
	    	
	    	Date pino = DateUtils.getAtomicTime().getTime();
	    	System.out.println( pino.toLocaleString() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		MainController.setViewConfig(frame);
		list.setFont(new Font("Consolas", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vencimientoList = new DefaultListModel<Vencimiento>();
		list.setModel(vencimientoList);
		updateVencimientos();
	}
	
	public void updateVencimientos(){
		List<Vencimiento> listaV =  Vencimiento.loadFromDB();
		vencimientoList.removeAllElements();
		
		for (Vencimiento vencimiento : listaV) {
			vencimientoList.addElement(vencimiento);
		}
		

	}
	
	
	 

}
