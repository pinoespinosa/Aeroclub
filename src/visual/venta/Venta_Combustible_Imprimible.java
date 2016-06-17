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
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import base_datos.DateUtils;
import data.Gasto;
import data.Persona;
import extended.JDialogExtended;

public class Venta_Combustible_Imprimible extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel fechaVuelo, Producto, formaDePago, total, destinatario, cantidad, detalle;

	/**
	 * Create the dialog.
	 * 
	 * @param frmSistemaDeGestin
	 */
	public Venta_Combustible_Imprimible(final Window parent) {
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
			img = ImageIO.read(new File(Venta_Combustible_Imprimible.class.getResource("/resources/logo.png").getPath()));
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

		Producto = new JLabel("New label");
		GridBagConstraints gbc_Producto = new GridBagConstraints();
		gbc_Producto.anchor = GridBagConstraints.WEST;
		gbc_Producto.insets = new Insets(0, 0, 0, 5);
		gbc_Producto.gridx = 5;
		gbc_Producto.gridy = 1;
		panel_1.add(Producto, gbc_Producto);
		Producto.setFont(new Font("Calibri", Font.BOLD, 18));

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

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.WEST;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 1;
		panel_2.add(panel_4, gbc_panel_4);

		JLabel lblPiloto_1 = new JLabel("Destinatario:");
		lblPiloto_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblPiloto_1);
		lblPiloto_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblPiloto_1.setBackground(Color.WHITE);

		destinatario = new JLabel("New label");
		destinatario.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(destinatario);
		destinatario.setBackground(Color.WHITE);
		destinatario.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.WEST;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.VERTICAL;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 2;
		panel_2.add(panel_5, gbc_panel_5);

		JLabel lblVencPsicofisico = new JLabel("Cantidad (L):");
		panel_5.add(lblVencPsicofisico);
		lblVencPsicofisico.setBackground(Color.WHITE);
		lblVencPsicofisico.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		cantidad = new JLabel("New label");
		panel_5.add(cantidad);
		cantidad.setBackground(Color.WHITE);
		cantidad.setFont(new Font("Calibri", Font.ITALIC, 25));

		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.WEST;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.VERTICAL;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 3;
		panel_2.add(panel_6, gbc_panel_6);

		JLabel lblPiloto = new JLabel("Detalle:");
		panel_6.add(lblPiloto);
		lblPiloto.setBackground(Color.WHITE);
		lblPiloto.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		detalle = new JLabel("New label");
		panel_6.add(detalle);
		detalle.setFont(new Font("Calibri", Font.ITALIC, 25));
		detalle.setBackground(Color.WHITE);

		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.anchor = GridBagConstraints.WEST;
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.fill = GridBagConstraints.VERTICAL;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 4;
		panel_2.add(panel_7, gbc_panel_7);

		JLabel lblPartida = new JLabel("Forma de pago:");
		panel_7.add(lblPartida);
		lblPartida.setBackground(Color.WHITE);
		lblPartida.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		formaDePago = new JLabel("New label");
		panel_7.add(formaDePago);
		formaDePago.setBackground(Color.WHITE);
		formaDePago.setFont(new Font("Calibri", Font.ITALIC, 19));

		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.anchor = GridBagConstraints.WEST;
		gbc_panel_8.insets = new Insets(0, 0, 5, 5);
		gbc_panel_8.fill = GridBagConstraints.VERTICAL;
		gbc_panel_8.gridx = 1;
		gbc_panel_8.gridy = 5;
		panel_2.add(panel_8, gbc_panel_8);

		JLabel lblAterrizaje = new JLabel("Total: $");
		panel_8.add(lblAterrizaje);
		lblAterrizaje.setBackground(Color.WHITE);
		lblAterrizaje.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));

		total = new JLabel("New label");
		panel_8.add(total);
		total.setBackground(Color.WHITE);
		total.setFont(new Font("Calibri", Font.ITALIC, 25));
	}

	public void inic(Gasto gasto) {

		if ((gasto.getIdPersona()+"").equals("-1"))
			this.destinatario.setText(gasto.getDetalle());
		else{
			String palabra = Persona.getPersonaById(gasto.getIdPersona()+"").toStringSimple();
			this.destinatario.setText(palabra.substring(0,Math.min(18, palabra.length())));
		}

		this.fechaVuelo.setText(DateUtils.toTraditionalFormat(new Date((long) gasto.getFecha())));
		
		
		String gastoTipo = gasto.getTipo();
		
		this.Producto.setText(gastoTipo.substring(0,Math.min(18, gastoTipo.length())));
		this.detalle.setText("");
		this.formaDePago.setText(gasto.getFormaPago().name());
		
		

	
		if (gasto.getUnidades() >0)
			this.cantidad.setText(gasto.getUnidades()+"");
		else
			this.cantidad.setText("");
		this.total.setText(gasto.getTotal()+"");
	}

	@Override
	public void updateUi() {

	}

	public Container getImprimible() {
		return getContentPane();
	}
}
