package visual.venta;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import extended.JDialogExtended;



public class Venta_Vuelo_Imprimible extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel fechaVuelo, ordenDeVuelo, avion, fechaPartida, fechaLlegada, piloto, vencimientoPsicofisico, aceite, combustible;
	
	/**
	 * Create the dialog.
	 * @param frmSistemaDeGestin 
	 */
	public Venta_Vuelo_Imprimible(final JFrame parent) {
		super(parent);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		
		setResizable(false);
		setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");
		setBounds(100, 100, 522, 606);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 225, 10, 225, 5, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 10, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Venta_Vuelo_Imprimible.class.getResource("/resources/logo.png")));
		
		JLabel lblMarconi = new JLabel("Marconi 1383 - 7000 - Tandil");
		lblMarconi.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblMarconi = new GridBagConstraints();
		gbc_lblMarconi.insets = new Insets(0, 0, 5, 0);
		gbc_lblMarconi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMarconi.gridx = 0;
		gbc_lblMarconi.gridy = 1;
		panel.add(lblMarconi, gbc_lblMarconi);
		lblMarconi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarconi.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono: (0249) 442-2671 / 442-4228     -    aeroclubtandil.com.ar");
		lblTelfono.setBackground(Color.WHITE);
		lblTelfono.setFont(new Font("Calibri", Font.ITALIC, 18));
		GridBagConstraints gbc_lblTelfono = new GridBagConstraints();
		gbc_lblTelfono.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelfono.gridx = 0;
		gbc_lblTelfono.gridy = 2;
		panel.add(lblTelfono, gbc_lblTelfono);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 2;
		getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{5, 30, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 0;
		panel_1.add(label_2, gbc_label_2);
		
		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 0, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 1;
		panel_1.add(lblFecha, gbc_lblFecha);
		lblFecha.setBackground(Color.DARK_GRAY);
		lblFecha.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		fechaVuelo = new JLabel("New label");
		GridBagConstraints gbc_fechaVuelo = new GridBagConstraints();
		gbc_fechaVuelo.anchor = GridBagConstraints.WEST;
		gbc_fechaVuelo.insets = new Insets(0, 0, 0, 5);
		gbc_fechaVuelo.gridx = 2;
		gbc_fechaVuelo.gridy = 1;
		panel_1.add(fechaVuelo, gbc_fechaVuelo);
		fechaVuelo.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblOrdenDeVuelo = new JLabel("Orden de vuelo:");
		GridBagConstraints gbc_lblOrdenDeVuelo = new GridBagConstraints();
		gbc_lblOrdenDeVuelo.insets = new Insets(0, 0, 0, 5);
		gbc_lblOrdenDeVuelo.gridx = 4;
		gbc_lblOrdenDeVuelo.gridy = 1;
		panel_1.add(lblOrdenDeVuelo, gbc_lblOrdenDeVuelo);
		lblOrdenDeVuelo.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		ordenDeVuelo = new JLabel("New label");
		GridBagConstraints gbc_ordenDeVuelo = new GridBagConstraints();
		gbc_ordenDeVuelo.anchor = GridBagConstraints.WEST;
		gbc_ordenDeVuelo.insets = new Insets(0, 0, 0, 5);
		gbc_ordenDeVuelo.gridx = 5;
		gbc_ordenDeVuelo.gridy = 1;
		panel_1.add(ordenDeVuelo, gbc_ordenDeVuelo);
		ordenDeVuelo.setFont(new Font("Calibri", Font.BOLD, 18));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 4;
		gbc_horizontalStrut_2.gridy = 2;
		getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{5, 0, 5, 0};
		gbl_panel_2.rowHeights = new int[]{30, 0, 10, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblAvion = new JLabel("Avion:");
		GridBagConstraints gbc_lblAvion = new GridBagConstraints();
		gbc_lblAvion.anchor = GridBagConstraints.WEST;
		gbc_lblAvion.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvion.gridx = 1;
		gbc_lblAvion.gridy = 0;
		panel_2.add(lblAvion, gbc_lblAvion);
		lblAvion.setBackground(Color.WHITE);
		lblAvion.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		avion = new JLabel("New label");
		avion.setBackground(Color.WHITE);
		GridBagConstraints gbc_avion = new GridBagConstraints();
		gbc_avion.anchor = GridBagConstraints.WEST;
		gbc_avion.insets = new Insets(0, 0, 5, 5);
		gbc_avion.gridx = 1;
		gbc_avion.gridy = 1;
		panel_2.add(avion, gbc_avion);
		avion.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		JLabel lblPiloto = new JLabel("Piloto:");
		GridBagConstraints gbc_lblPiloto = new GridBagConstraints();
		gbc_lblPiloto.anchor = GridBagConstraints.WEST;
		gbc_lblPiloto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPiloto.gridx = 1;
		gbc_lblPiloto.gridy = 3;
		panel_2.add(lblPiloto, gbc_lblPiloto);
		lblPiloto.setBackground(Color.WHITE);
		lblPiloto.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		piloto = new JLabel("New label");
		GridBagConstraints gbc_piloto = new GridBagConstraints();
		gbc_piloto.anchor = GridBagConstraints.WEST;
		gbc_piloto.insets = new Insets(0, 0, 5, 5);
		gbc_piloto.gridx = 1;
		gbc_piloto.gridy = 4;
		panel_2.add(piloto, gbc_piloto);
		piloto.setBackground(Color.WHITE);
		piloto.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		JLabel lblVencPsicofisico = new JLabel("Venc Psicof\u00EDsico:");
		GridBagConstraints gbc_lblVencPsicofisico = new GridBagConstraints();
		gbc_lblVencPsicofisico.anchor = GridBagConstraints.WEST;
		gbc_lblVencPsicofisico.insets = new Insets(0, 0, 5, 5);
		gbc_lblVencPsicofisico.gridx = 1;
		gbc_lblVencPsicofisico.gridy = 5;
		panel_2.add(lblVencPsicofisico, gbc_lblVencPsicofisico);
		lblVencPsicofisico.setBackground(Color.WHITE);
		lblVencPsicofisico.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		vencimientoPsicofisico = new JLabel("New label");
		GridBagConstraints gbc_vencimientoPsicofisico = new GridBagConstraints();
		gbc_vencimientoPsicofisico.anchor = GridBagConstraints.WEST;
		gbc_vencimientoPsicofisico.insets = new Insets(0, 0, 0, 5);
		gbc_vencimientoPsicofisico.gridx = 1;
		gbc_vencimientoPsicofisico.gridy = 6;
		panel_2.add(vencimientoPsicofisico, gbc_vencimientoPsicofisico);
		vencimientoPsicofisico.setBackground(Color.WHITE);
		vencimientoPsicofisico.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 2;
		gbc_horizontalStrut.gridy = 4;
		getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 4;
		getContentPane().add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{5, 0, 5, 0};
		gbl_panel_3.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel label_6 = new JLabel("Llegada:");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 0;
		panel_3.add(label_6, gbc_label_6);
		label_6.setBackground(Color.WHITE);
		label_6.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		fechaLlegada = new JLabel("New label");
		GridBagConstraints gbc_fechaLlegada = new GridBagConstraints();
		gbc_fechaLlegada.anchor = GridBagConstraints.WEST;
		gbc_fechaLlegada.insets = new Insets(0, 0, 5, 5);
		gbc_fechaLlegada.gridx = 1;
		gbc_fechaLlegada.gridy = 1;
		panel_3.add(fechaLlegada, gbc_fechaLlegada);
		fechaLlegada.setBackground(Color.WHITE);
		fechaLlegada.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		JLabel lblPartida = new JLabel("Partida:");
		GridBagConstraints gbc_lblPartida = new GridBagConstraints();
		gbc_lblPartida.anchor = GridBagConstraints.WEST;
		gbc_lblPartida.insets = new Insets(0, 0, 5, 5);
		gbc_lblPartida.gridx = 1;
		gbc_lblPartida.gridy = 2;
		panel_3.add(lblPartida, gbc_lblPartida);
		lblPartida.setBackground(Color.WHITE);
		lblPartida.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		fechaPartida = new JLabel("New label");
		GridBagConstraints gbc_fechaPartida = new GridBagConstraints();
		gbc_fechaPartida.anchor = GridBagConstraints.WEST;
		gbc_fechaPartida.insets = new Insets(0, 0, 5, 5);
		gbc_fechaPartida.gridx = 1;
		gbc_fechaPartida.gridy = 3;
		panel_3.add(fechaPartida, gbc_fechaPartida);
		fechaPartida.setBackground(Color.WHITE);
		fechaPartida.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		JLabel label_5 = new JLabel("Aceite (L):");
		label_5.setBackground(Color.WHITE);
		label_5.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 4;
		panel_3.add(label_5, gbc_label_5);
		
		aceite = new JLabel("New label");
		aceite.setBackground(Color.WHITE);
		aceite.setFont(new Font("Calibri", Font.ITALIC, 18));
		GridBagConstraints gbc_aceite = new GridBagConstraints();
		gbc_aceite.anchor = GridBagConstraints.WEST;
		gbc_aceite.insets = new Insets(0, 0, 5, 5);
		gbc_aceite.gridx = 1;
		gbc_aceite.gridy = 5;
		panel_3.add(aceite, gbc_aceite);
		
		JLabel lblCombl = new JLabel("Combustible (L):");
		lblCombl.setBackground(Color.WHITE);
		lblCombl.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblCombl = new GridBagConstraints();
		gbc_lblCombl.anchor = GridBagConstraints.WEST;
		gbc_lblCombl.insets = new Insets(0, 0, 5, 5);
		gbc_lblCombl.gridx = 1;
		gbc_lblCombl.gridy = 6;
		panel_3.add(lblCombl, gbc_lblCombl);
		
		combustible = new JLabel("New label");
		combustible.setBackground(Color.WHITE);
		combustible.setFont(new Font("Calibri", Font.ITALIC, 18));
		GridBagConstraints gbc_combustible = new GridBagConstraints();
		gbc_combustible.anchor = GridBagConstraints.WEST;
		gbc_combustible.insets = new Insets(0, 0, 0, 5);
		gbc_combustible.gridx = 1;
		gbc_combustible.gridy = 7;
		panel_3.add(combustible, gbc_combustible);
	}


	public void inic(String ordenDeVuelo, String avion, String tipoVuelo,   String fechaPartida, String fechaLlegada, String piloto, String vencimientoPsicofisico, String aceite, String combustible){
		
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");		
		this.fechaVuelo.setText(format.format(new Date( System.currentTimeMillis())));
		this.ordenDeVuelo.setText(ordenDeVuelo);
		this.avion.setText(avion);
	//	this.tipoVuelo.setText(tipoVuelo);
		this.fechaLlegada.setText(fechaLlegada);
		this.fechaPartida.setText(fechaPartida);
		this.piloto.setText(piloto);
		this.vencimientoPsicofisico.setText(vencimientoPsicofisico);
		this.aceite.setText(aceite);
		this.combustible.setText(combustible);
	
		
		
	    
	}

	
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub
		
	}
		
	public Container getImprimible(){
		return getContentPane();
	}
}


