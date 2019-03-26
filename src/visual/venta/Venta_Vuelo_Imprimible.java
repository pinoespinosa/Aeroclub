package visual.venta;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import base_datos.Utils;
import data.Avion;
import data.Instructor;
import data.Piloto;
import data.Precios;
import data.Vuelo;
import extended.JDialogExtended;

public class Venta_Vuelo_Imprimible extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel fechaVuelo, ordenDeVuelo, avion, fechaPartida, fechaLlegada, piloto, vencimientoPsicofisico, aceite, combustible, instructor, tipoVuelo, montoTotal, tiempoVuelo;

	/**
	 * Create the dialog.
	 * 
	 * @param frmSistemaDeGestin
	 */
	public Venta_Vuelo_Imprimible(final Window parent) {
		super(parent);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));

		setResizable(false);
		setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");
		setBounds(100, 100, 436, 793);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 0, 10, 0, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Venta_Vuelo_Imprimible.class.getResource("/resources/logo.png").getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblNewLabel_1.setIcon(new ImageIcon(img.getScaledInstance(400, 150, Image.SCALE_SMOOTH)));

		JLabel lblTelfono = new JLabel("Tel\u00E9fono: (0249) 465-2842 / 442-4228");
		lblTelfono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelfono.setBackground(Color.WHITE);
		lblTelfono.setFont(new Font("Calibri", Font.ITALIC, 18));
		GridBagConstraints gbc_lblTelfono = new GridBagConstraints();
		gbc_lblTelfono.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelfono.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelfono.gridx = 0;
		gbc_lblTelfono.gridy = 1;
		panel.add(lblTelfono, gbc_lblTelfono);

		JLabel lblAeroclubtandilcomar = new JLabel("aeroclubtandil.com.ar / aeroclubtandil@gmail.com");
		lblAeroclubtandilcomar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAeroclubtandilcomar.setFont(new Font("Calibri", Font.ITALIC, 18));
		lblAeroclubtandilcomar.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblAeroclubtandilcomar = new GridBagConstraints();
		gbc_lblAeroclubtandilcomar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAeroclubtandilcomar.gridx = 0;
		gbc_lblAeroclubtandilcomar.gridy = 2;
		panel.add(lblAeroclubtandilcomar, gbc_lblAeroclubtandilcomar);

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
		gbc_horizontalStrut_2.gridx = 2;
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
		gbl_panel_2.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.WEST;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);

		JLabel lblAvion = new JLabel("Avion:");
		panel_3.add(lblAvion);
		lblAvion.setBackground(Color.WHITE);
		lblAvion.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		avion = new JLabel("New label");
		panel_3.add(avion);
		avion.setBackground(Color.WHITE);
		avion.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.WEST;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 1;
		panel_2.add(panel_4, gbc_panel_4);

		JLabel lblPiloto_1 = new JLabel("Piloto:");
		panel_4.add(lblPiloto_1);
		lblPiloto_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblPiloto_1.setBackground(Color.WHITE);

		piloto = new JLabel("New label");
		panel_4.add(piloto);
		piloto.setBackground(Color.WHITE);
		piloto.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.WEST;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.VERTICAL;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 2;
		panel_2.add(panel_5, gbc_panel_5);

		JLabel lblVencPsicofisico = new JLabel("Venc Psicof\u00EDsico:");
		panel_5.add(lblVencPsicofisico);
		lblVencPsicofisico.setBackground(Color.WHITE);
		lblVencPsicofisico.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		vencimientoPsicofisico = new JLabel("New label");
		panel_5.add(vencimientoPsicofisico);
		vencimientoPsicofisico.setBackground(Color.WHITE);
		vencimientoPsicofisico.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.WEST;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.VERTICAL;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 3;
		panel_2.add(panel_6, gbc_panel_6);

		JLabel lblPiloto = new JLabel("Instructor:");
		panel_6.add(lblPiloto);
		lblPiloto.setBackground(Color.WHITE);
		lblPiloto.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		instructor = new JLabel("New label");
		panel_6.add(instructor);
		instructor.setFont(new Font("Calibri", Font.ITALIC, 25));
		instructor.setBackground(Color.WHITE);

		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.anchor = GridBagConstraints.WEST;
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.VERTICAL;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 4;
		panel_2.add(panel_7, gbc_panel_7);

		JLabel lblPartida = new JLabel("Despegue:");
		panel_7.add(lblPartida);
		lblPartida.setBackground(Color.WHITE);
		lblPartida.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		fechaPartida = new JLabel("New label");
		panel_7.add(fechaPartida);
		fechaPartida.setBackground(Color.WHITE);
		fechaPartida.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.anchor = GridBagConstraints.WEST;
		gbc_panel_8.insets = new Insets(0, 0, 5, 5);
		gbc_panel_8.fill = GridBagConstraints.VERTICAL;
		gbc_panel_8.gridx = 1;
		gbc_panel_8.gridy = 5;
		panel_2.add(panel_8, gbc_panel_8);

		JLabel lblAterrizaje = new JLabel("Aterrizaje:");
		panel_8.add(lblAterrizaje);
		lblAterrizaje.setBackground(Color.WHITE);
		lblAterrizaje.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		fechaLlegada = new JLabel("New label");
		panel_8.add(fechaLlegada);
		fechaLlegada.setBackground(Color.WHITE);
		fechaLlegada.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.anchor = GridBagConstraints.WEST;
		gbc_panel_12.insets = new Insets(0, 0, 5, 5);
		gbc_panel_12.fill = GridBagConstraints.VERTICAL;
		gbc_panel_12.gridx = 1;
		gbc_panel_12.gridy = 6;
		panel_2.add(panel_12, gbc_panel_12);

		JLabel lblTiempoDeVuelo = new JLabel("Tiempo de vuelo:");
		lblTiempoDeVuelo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblTiempoDeVuelo.setBackground(Color.WHITE);
		panel_12.add(lblTiempoDeVuelo);

		tiempoVuelo = new JLabel("New label");
		tiempoVuelo.setFont(new Font("Calibri", Font.ITALIC, 25));
		tiempoVuelo.setBackground(Color.WHITE);
		panel_12.add(tiempoVuelo);

		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.anchor = GridBagConstraints.WEST;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.VERTICAL;
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 7;
		panel_2.add(panel_9, gbc_panel_9);

		JLabel label_5 = new JLabel("Aceite (L):");
		panel_9.add(label_5);
		label_5.setBackground(Color.WHITE);
		label_5.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		aceite = new JLabel("New label");
		panel_9.add(aceite);
		aceite.setBackground(Color.WHITE);
		aceite.setFont(new Font("Calibri", Font.ITALIC, 25));

		JLabel lblCombl = new JLabel("     Comb (L):");
		panel_9.add(lblCombl);
		lblCombl.setBackground(Color.WHITE);
		lblCombl.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		combustible = new JLabel("New label");
		panel_9.add(combustible);
		combustible.setBackground(Color.WHITE);
		combustible.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.anchor = GridBagConstraints.WEST;
		gbc_panel_10.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10.gridx = 1;
		gbc_panel_10.gridy = 8;
		panel_2.add(panel_10, gbc_panel_10);

		JLabel lblTipoDeVuelo = new JLabel("Tipo de vuelo:");
		lblTipoDeVuelo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblTipoDeVuelo.setBackground(Color.WHITE);
		panel_10.add(lblTipoDeVuelo);

		tipoVuelo = new JLabel("New label");
		tipoVuelo.setFont(new Font("Calibri", Font.ITALIC, 25));
		tipoVuelo.setBackground(Color.WHITE);
		panel_10.add(tipoVuelo);

		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.anchor = GridBagConstraints.WEST;
		gbc_panel_11.insets = new Insets(0, 0, 5, 5);
		gbc_panel_11.fill = GridBagConstraints.VERTICAL;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 9;
		panel_2.add(panel_11, gbc_panel_11);

		JLabel lblMonto = new JLabel("Monto: $");
		lblMonto.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblMonto.setBackground(Color.WHITE);
		panel_11.add(lblMonto);

		montoTotal = new JLabel("New label");
		montoTotal.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		montoTotal.setBackground(Color.WHITE);
		panel_11.add(montoTotal);
	}

	public void inic(Vuelo vuelo) {

		SimpleDateFormat formatDay = new SimpleDateFormat("dd/MM/YYYY");
		SimpleDateFormat formatHour = new SimpleDateFormat("dd/MM/YYYY HH:mm");
		this.fechaVuelo.setText(formatDay.format(new Date(System.currentTimeMillis())));
		this.ordenDeVuelo.setText(vuelo.getId() + "");
		this.avion.setText(Avion.getAvionById(vuelo.getIdAvion() + "").toString());
		this.instructor.setText(Instructor.getInstructorById(vuelo.getIdInstructor() + "").toString());

		this.fechaPartida.setText(formatHour.format(new Date(vuelo.getHoraInicio())));

		if (vuelo.getHoraFinal() == 0){
			this.fechaLlegada.setText("En vuelo");
			this.tiempoVuelo.setText("En vuelo");
		}
		else{
			this.fechaLlegada.setText(formatHour.format(new Date(vuelo.getHoraFinal())));
			Float minutos = (float) Utils.minutesBetweenDates(new Date(vuelo.getHoraInicio()), new Date(vuelo.getHoraFinal()));
			minutos = minutos /60 ;
			this.tiempoVuelo.setText( minutos+ " hs");
		}
		
		
		this.piloto.setText(Piloto.getPilotoById(vuelo.getIdPiloto() + "").toString());
		this.vencimientoPsicofisico.setText(formatDay.format(new Date(Piloto.getPilotoById(vuelo.getIdPiloto() + "").getFecha_licencia())));
		this.aceite.setText(vuelo.getCantAceite() + "");
		this.combustible.setText(vuelo.getCantCombustible() + "");
		this.montoTotal.setText(vuelo.getPrecio() + " - " + Precios.getNameTypePagoVuelo(vuelo.getFormaDePago()));
		this.tipoVuelo.setText(Vuelo.TipoVuelo.values()[vuelo.getTipoVuelo()] + "");
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub

	}

	public Container getImprimible() {
		return getContentPane();
	}
}
