package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import base_datos.Utils;
import base_datos.managerDB;
import data.Avion;
import data.Persona;
import data.Vuelo;
import extended.JDialogExtended;
import extended.MainController;

public class Informes extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> inicioAnio, finAnio, inicioMes, finMes;
	private JComboBox<String> inicioMesComboBox, inicioAnioComboBox, finMesComboBox, finAnioComboBox;
	private JComboBox<Persona> personasCuentaCorriente;

	/**
	 * Create the dialog.
	 */
	public Informes(final JFrame parent) {
		super(parent);
		setTitle("Mostrar informes");
		setBounds(100, 100, 645, 412);
		getContentPane().setLayout(new BorderLayout());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				parent.setEnabled(true);
				parent.setVisible(true);
			}
		});
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Vencimientos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 4, 4, 4)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 11;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 200, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnVencimientoDeLicencia = new JButton("Vencimiento de Licencia Psicofisico");
				GridBagConstraints gbc_btnVencimientoDeLicencia = new GridBagConstraints();
				gbc_btnVencimientoDeLicencia.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnVencimientoDeLicencia.gridx = 1;
				gbc_btnVencimientoDeLicencia.gridy = 0;
				panel.add(btnVencimientoDeLicencia, gbc_btnVencimientoDeLicencia);
				btnVencimientoDeLicencia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						// Create columns names
						String columnNames[] = {"fecha", "detalle"};

						String script = "(	" + "select `pi`.`fechaVencimientoLicencia` AS `fecha`, " + "concat(`pe`.`apellido`,' , ',`pe`.`nombre`) AS `detalle` " + "from (`" + MainController.getEsquema() + "`.`piloto` `pi` join `" + MainController.getEsquema() + "`.`persona` `pe` on((`pe`.`id` = `pi`.`id`))) " + "where (`pi`.`fechaVencimientoLicencia` is not null)" + ")" +

						"union" +

						"(" + "	select 	`pi`.`fecha_psicofisico` AS `fecha`," + "	concat(`pe`.`apellido`,' , ',`pe`.`nombre`) AS `detalle` " + "   from (`" + MainController.getEsquema() + "`.`instructor` `pi` join `" + MainController.getEsquema() + "`.`persona` `pe` on((`pe`.`id` = `pi`.`id`))) " + "   where (`pi`.`fecha_psicofisico` is not null)" + ") ";

						List<String> campos = Arrays.asList(columnNames);

						List<List<String>> datos = managerDB.executeScript_Query(script, campos);

						SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd ");

						for (List<String> list : datos) {
							list.set(0, format.format(new Date(Long.parseLong(list.get(0)))));
						}

						MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, datos, "Vencimiento del psicofísico", ""));
					}
				});
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Horas de vuelo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 4, 4, 4)));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridwidth = 11;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblDesde = new JLabel("desde");
				GridBagConstraints gbc_lblDesde = new GridBagConstraints();
				gbc_lblDesde.gridheight = 2;
				gbc_lblDesde.insets = new Insets(0, 0, 0, 5);
				gbc_lblDesde.gridx = 0;
				gbc_lblDesde.gridy = 0;
				panel.add(lblDesde, gbc_lblDesde);
			}
			{
				inicioMes = new JComboBox<String>();
				GridBagConstraints gbc_inicioMes = new GridBagConstraints();
				gbc_inicioMes.gridheight = 2;
				gbc_inicioMes.insets = new Insets(0, 0, 0, 5);
				gbc_inicioMes.gridx = 1;
				gbc_inicioMes.gridy = 0;
				panel.add(inicioMes, gbc_inicioMes);
				inicioMes.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			}
			{
				JLabel label = new JLabel("/");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.gridheight = 2;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 2;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				inicioAnio = new JComboBox<String>();
				GridBagConstraints gbc_inicioAnio = new GridBagConstraints();
				gbc_inicioAnio.gridheight = 2;
				gbc_inicioAnio.insets = new Insets(0, 0, 0, 5);
				gbc_inicioAnio.gridx = 3;
				gbc_inicioAnio.gridy = 0;
				panel.add(inicioAnio, gbc_inicioAnio);
				inicioAnio.setModel(new DefaultComboBoxModel<String>(new String[]{"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
			}
			{
				JLabel lblHasta = new JLabel("hasta");
				GridBagConstraints gbc_lblHasta = new GridBagConstraints();
				gbc_lblHasta.gridheight = 2;
				gbc_lblHasta.insets = new Insets(0, 0, 0, 5);
				gbc_lblHasta.gridx = 4;
				gbc_lblHasta.gridy = 0;
				panel.add(lblHasta, gbc_lblHasta);
			}
			{
				finMes = new JComboBox<String>();
				GridBagConstraints gbc_finMes = new GridBagConstraints();
				gbc_finMes.gridheight = 2;
				gbc_finMes.insets = new Insets(0, 0, 0, 5);
				gbc_finMes.gridx = 5;
				gbc_finMes.gridy = 0;
				panel.add(finMes, gbc_finMes);
				finMes.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			}
			{
				JLabel label = new JLabel("/");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.gridheight = 2;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 6;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				finAnio = new JComboBox<String>();
				GridBagConstraints gbc_finAnio = new GridBagConstraints();
				gbc_finAnio.gridheight = 2;
				gbc_finAnio.insets = new Insets(0, 0, 0, 5);
				gbc_finAnio.gridx = 7;
				gbc_finAnio.gridy = 0;
				panel.add(finAnio, gbc_finAnio);
				finAnio.setModel(new DefaultComboBoxModel<String>(new String[]{"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
			}
			{
				JButton btnHorasDeVuelo = new JButton("Horas de vuelo simples");
				GridBagConstraints gbc_btnHorasDeVuelo = new GridBagConstraints();
				gbc_btnHorasDeVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnHorasDeVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_btnHorasDeVuelo.gridx = 9;
				gbc_btnHorasDeVuelo.gridy = 0;
				panel.add(btnHorasDeVuelo, gbc_btnHorasDeVuelo);
				{
					JButton btnNewButton = new JButton("Horas de vuelo con detalle");
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnNewButton.gridx = 9;
					gbc_btnNewButton.gridy = 1;
					panel.add(btnNewButton, gbc_btnNewButton);
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Revisiones a los aviones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 4, 4, 4)));
						GridBagConstraints gbc_panel_1 = new GridBagConstraints();
						gbc_panel_1.gridwidth = 11;
						gbc_panel_1.insets = new Insets(0, 0, 5, 5);
						gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
						gbc_panel_1.gridx = 2;
						gbc_panel_1.gridy = 3;
						contentPanel.add(panel_1, gbc_panel_1);
						GridBagLayout gbl_panel_1 = new GridBagLayout();
						gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0};
						gbl_panel_1.rowHeights = new int[]{0, 0, 0};
						gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
						gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
						panel_1.setLayout(gbl_panel_1);
						{
							JButton btnVerUltimasRevisiones = new JButton("Ver ultimas revisiones");
							btnVerUltimasRevisiones.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									List<String> columnas = new ArrayList<String>();

									String columnNames[] = {"idAvion", "tipoInspeccion", "fecha", "Horas_voladas_desde_la_ultima_revision"};

									SimpleDateFormat formatDay = new SimpleDateFormat("dd/MM/YYYY");
									
									List<String> campos = Arrays.asList(columnNames);
									List<List<String>> filtrado = managerDB.executeScript_Query(
											
											"select idAvion, tipoInspeccion, fecha, max(horasDesdeRevision) as Horas_voladas_desde_la_ultima_revision " +
											"from " +
											"(" +
											"(" +
											"SELECT ins.idAvion, tipoInspeccion, max(fechaInspeccion) as fecha, round(sum((vue.horaFinal-vue.horaInicio)/3600000)) as horasDesdeRevision, count(*) " +
											"FROM aviones.inspeccion as ins left join aviones.vuelo as vue on (vue.idAvion=ins.idAvion) " +
											"where fechaInspeccion < vue.horaInicio " +
											"group by tipoInspeccion, idAvion " +
											"order by idAvion" +
											")" +
											"union" +
											"(" +
											"SELECT ins.idAvion, tipoInspeccion, max(fechaInspeccion) as fecha, '0', count(*) " +
											"FROM aviones.inspeccion as ins left join aviones.vuelo as vue on (vue.idAvion=ins.idAvion) " +
											"group by tipoInspeccion, idAvion " +
											"order by idAvion)" +
											") as pino " +
											"group by idAvion, tipoInspeccion", campos);

	
									if (filtrado.isEmpty()) {
										JOptionPane.showMessageDialog(null, "No se encontraron vuelos en el intervalo del " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem());
										return;
									}
									for (List<String> list : filtrado) {
										list.set(2, formatDay.format(new Date(Long.parseLong(list.get(2)))));
									}

									// Create columns names String
									String[] columnNmes = {"Avion", "Tiempo de vuelo"};
									List<String> camos = Arrays.asList(columnNames);

									Informes_Table dialog = new Informes_Table(parent, campos, filtrado, "Horas de vuelo desde " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem(), "");
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.setVisible(true);
									setAction(MainController.ACTION_CONTINUE);
									Informes.this.dispose();

								}
							});
							btnVerUltimasRevisiones.setToolTipText("");
							GridBagConstraints gbc_btnVerUltimasRevisiones = new GridBagConstraints();
							gbc_btnVerUltimasRevisiones.insets = new Insets(0, 0, 5, 0);
							gbc_btnVerUltimasRevisiones.fill = GridBagConstraints.HORIZONTAL;
							gbc_btnVerUltimasRevisiones.gridx = 9;
							gbc_btnVerUltimasRevisiones.gridy = 0;
							panel_1.add(btnVerUltimasRevisiones, gbc_btnVerUltimasRevisiones);
						}
						{
							JButton btnNewButton_1 = new JButton("Gasto de Aceite p/ avion p/ hora");
							{
								JLabel label = new JLabel("desde");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.EAST;
								gbc_label.insets = new Insets(0, 0, 0, 5);
								gbc_label.gridx = 0;
								gbc_label.gridy = 1;
								panel_1.add(label, gbc_label);
							}
							{
								inicioMesComboBox = new JComboBox<String>();
								inicioMesComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
								GridBagConstraints gbc_inicioMesComboBox = new GridBagConstraints();
								gbc_inicioMesComboBox.insets = new Insets(0, 0, 0, 5);
								gbc_inicioMesComboBox.fill = GridBagConstraints.HORIZONTAL;
								gbc_inicioMesComboBox.gridx = 1;
								gbc_inicioMesComboBox.gridy = 1;
								panel_1.add(inicioMesComboBox, gbc_inicioMesComboBox);
							}
							{
								JLabel label = new JLabel("/");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.EAST;
								gbc_label.insets = new Insets(0, 0, 0, 5);
								gbc_label.gridx = 2;
								gbc_label.gridy = 1;
								panel_1.add(label, gbc_label);
							}
							{
								inicioAnioComboBox = new JComboBox<String>();
								inicioAnioComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
								GridBagConstraints gbc_inicioAnioComboBox = new GridBagConstraints();
								gbc_inicioAnioComboBox.insets = new Insets(0, 0, 0, 5);
								gbc_inicioAnioComboBox.fill = GridBagConstraints.HORIZONTAL;
								gbc_inicioAnioComboBox.gridx = 3;
								gbc_inicioAnioComboBox.gridy = 1;
								panel_1.add(inicioAnioComboBox, gbc_inicioAnioComboBox);
							}
							{
								JLabel label = new JLabel("hasta");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.EAST;
								gbc_label.insets = new Insets(0, 0, 0, 5);
								gbc_label.gridx = 4;
								gbc_label.gridy = 1;
								panel_1.add(label, gbc_label);
							}
							{
								finMesComboBox = new JComboBox<String>();
								finMesComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
								GridBagConstraints gbc_finMesComboBox = new GridBagConstraints();
								gbc_finMesComboBox.insets = new Insets(0, 0, 0, 5);
								gbc_finMesComboBox.fill = GridBagConstraints.HORIZONTAL;
								gbc_finMesComboBox.gridx = 5;
								gbc_finMesComboBox.gridy = 1;
								panel_1.add(finMesComboBox, gbc_finMesComboBox);
							}
							{
								JLabel label = new JLabel("/");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.EAST;
								gbc_label.insets = new Insets(0, 0, 0, 5);
								gbc_label.gridx = 6;
								gbc_label.gridy = 1;
								panel_1.add(label, gbc_label);
							}
							{
								finAnioComboBox = new JComboBox<String>();
								finAnioComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"}));
								GridBagConstraints gbc_finMesComboBox = new GridBagConstraints();
								gbc_finMesComboBox.insets = new Insets(0, 0, 0, 5);
								gbc_finMesComboBox.fill = GridBagConstraints.HORIZONTAL;
								gbc_finMesComboBox.gridx = 7;
								gbc_finMesComboBox.gridy = 1;
								panel_1.add(finAnioComboBox, gbc_finMesComboBox);
							}
							GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
							gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
							gbc_btnNewButton_1.gridx = 9;
							gbc_btnNewButton_1.gridy = 1;
							panel_1.add(btnNewButton_1, gbc_btnNewButton_1);

							btnNewButton_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									// Create columns names
									String columnNames[] = {"Avion", "CantAceite"};

									SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
									Date fechaInicioSimple = null, fechaFinalSimple = null;
									try {
										fechaInicioSimple = format.parse(inicioAnioComboBox.getSelectedItem() + "-" + inicioMesComboBox.getSelectedItem() + "-1");
										fechaFinalSimple = format.parse(finAnioComboBox.getSelectedItem() + "-" + finMesComboBox.getSelectedItem() + "-1");
									} catch (ParseException e) {
										e.printStackTrace();
									}

									String script = "SELECT name as Avion, avg(cantAceite) as CantAceite FROM `" + MainController.getEsquema() + "`.vuelo INNER JOIN `" + MainController.getEsquema() + "`.avion on idAvion like avion.id where horaInicio >'" + fechaInicioSimple.getTime() + "' and horaInicio < '" + fechaFinalSimple.getTime() + "' group by idAvion;";

									List<String> campos = Arrays.asList(columnNames);

									List<List<String>> datos = managerDB.executeScript_Query(script, campos);

									if (!datos.isEmpty())
										MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, datos, "Ver stock de combustible", ""));
									else
										JOptionPane.showMessageDialog(null, "No se encontraron vuelos entre las fechas consultadas.");

								}
							});
						}
					}
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Stock", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 4, 4, 4)));
						GridBagConstraints gbc_panel_1 = new GridBagConstraints();
						gbc_panel_1.gridwidth = 11;
						gbc_panel_1.insets = new Insets(0, 0, 5, 5);
						gbc_panel_1.fill = GridBagConstraints.BOTH;
						gbc_panel_1.gridx = 2;
						gbc_panel_1.gridy = 4;
						contentPanel.add(panel_1, gbc_panel_1);
						GridBagLayout gbl_panel_1 = new GridBagLayout();
						gbl_panel_1.columnWidths = new int[]{0, 200, 0};
						gbl_panel_1.rowHeights = new int[]{0, 0};
						gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
						gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
						panel_1.setLayout(gbl_panel_1);
						{
							JButton btnVerStockDe = new JButton("Ver stock de combustible");
							btnVerStockDe.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									// Create columns names
									String columnNames[] = {"Recurso", "Stock_disponible"};

									String script = "SELECT * FROM `" + MainController.getEsquema() + "`.stock where not(Recurso like '');";

									List<String> campos = Arrays.asList(columnNames);

									List<List<String>> datos = managerDB.executeScript_Query(script, campos);

									MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, datos, "Ver stock de combustible", ""));
								}
							});
							GridBagConstraints gbc_btnVerStockDe = new GridBagConstraints();
							gbc_btnVerStockDe.fill = GridBagConstraints.HORIZONTAL;
							gbc_btnVerStockDe.gridx = 1;
							gbc_btnVerStockDe.gridy = 0;
							panel_1.add(btnVerStockDe, gbc_btnVerStockDe);
						}
					}
					{
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Cuentas Corriente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(0, 4, 4, 4)));
						GridBagConstraints gbc_panel_1 = new GridBagConstraints();
						gbc_panel_1.gridwidth = 11;
						gbc_panel_1.insets = new Insets(0, 0, 0, 5);
						gbc_panel_1.fill = GridBagConstraints.BOTH;
						gbc_panel_1.gridx = 2;
						gbc_panel_1.gridy = 5;
						contentPanel.add(panel_1, gbc_panel_1);
						GridBagLayout gbl_panel_1 = new GridBagLayout();
						gbl_panel_1.columnWidths = new int[]{0, 200, 0};
						gbl_panel_1.rowHeights = new int[]{0, 0};
						gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
						gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
						panel_1.setLayout(gbl_panel_1);
						{
							personasCuentaCorriente = new JComboBox<Persona>();
							GridBagConstraints gbc_comboBox = new GridBagConstraints();
							gbc_comboBox.insets = new Insets(0, 0, 0, 5);
							gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
							gbc_comboBox.gridx = 0;
							gbc_comboBox.gridy = 0;
							panel_1.add(personasCuentaCorriente, gbc_comboBox);
						}
						{
							JButton btnVerCuentasCorrientes = new JButton("Ver detalle de cuenta corriente");
							btnVerCuentasCorrientes.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {

									// Create columns names
									String columnNames[] = {"fecha","detalle", "A_pagar", "A_favor"};

									String script = "SELECT fecha,detalle, A_pagar, A_favor FROM aviones.cuenta_corriente WHERE idPersona like'" + ((Persona) personasCuentaCorriente.getSelectedItem()).getId() + "';";

									List<String> campos = Arrays.asList(columnNames);

									List<List<String>> datos = managerDB.executeScript_Query(script, campos);

									if (datos.isEmpty()) {
										JOptionPane.showMessageDialog(null, "No se registran movimientos.");
										return;
									}
									SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd ");

									float total = 0;

									for (List<String> list : datos) {
										list.set(0, format.format(new Date(Long.parseLong(list.get(0)))));
										total -= Float.parseFloat(list.get(2));
										total += Float.parseFloat(list.get(3));
										list.set(2, Math.round(Float.parseFloat(list.get(2)))+"");
										list.set(3, Math.round(Float.parseFloat(list.get(3)))+"");
									}

									MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, datos, "Vencimiento del psicofísico", "Monto actual: $" + total));
								}
							});
							GridBagConstraints gbc_btnVerCuentasCorrientes = new GridBagConstraints();
							gbc_btnVerCuentasCorrientes.fill = GridBagConstraints.HORIZONTAL;
							gbc_btnVerCuentasCorrientes.gridx = 1;
							gbc_btnVerCuentasCorrientes.gridy = 0;
							panel_1.add(btnVerCuentasCorrientes, gbc_btnVerCuentasCorrientes);
						}
					}
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							Date fechaInicioSimple = null;
							try {
								fechaInicioSimple = format.parse(inicioAnio.getSelectedItem() + "-" + inicioMes.getSelectedItem() + "-1");
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Date fechaFinalSimple = null;
							try {
								fechaFinalSimple = format.parse(finAnio.getSelectedItem() + "-" + finMes.getSelectedItem() + "-1");
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							List<Vuelo> vuelos = Vuelo.loadFromDB();
							List<List<String>> filtrado = new ArrayList<List<String>>();

							for (Vuelo vuelo : vuelos) {
								if (vuelo.getHoraInicio() > fechaInicioSimple.getTime() && vuelo.getHoraFinal() < fechaFinalSimple.getTime()) {
									List<String> aa = new ArrayList<String>();
									aa.add(format.format(new Date(vuelo.getHoraInicio())) + "");
									aa.add(Utils.minutesBetweenDates(new Date(vuelo.getHoraInicio()), new Date(vuelo.getHoraFinal())) + " minutos ");
									aa.add(Avion.getAvionById(vuelo.getIdAvion() + "").getName());
									filtrado.add(aa);
								}
							}

							if (filtrado.isEmpty()) {
								JOptionPane.showMessageDialog(null, "No se encontraron vuelos en el intervalo del " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem());
								return;
							}

							// Create columns names
							String columnNames[] = {"fecha", "detalle", "avion"};
							List<String> campos = Arrays.asList(columnNames);

							MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, filtrado, "Horas de vuelo desde " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem(), ""));

						}
					});
				}
				btnHorasDeVuelo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date fechaInicioSimple = null, fechaFinalSimple = null;
						try {
							fechaInicioSimple = format.parse(inicioAnio.getSelectedItem() + "-" + inicioMes.getSelectedItem() + "-1");
							fechaFinalSimple = format.parse(finAnio.getSelectedItem() + "-" + finMes.getSelectedItem() + "-1");
						} catch (ParseException e) {
							e.printStackTrace();
						}

						List<List<String>> filtrado = new ArrayList<List<String>>();

						Hashtable<String, Integer> horasVoladas = getHorasVoladasSince(fechaInicioSimple, fechaFinalSimple);

						for (String list : horasVoladas.keySet()) {
							List<String> aa = new ArrayList<String>();
							aa.add(Avion.getAvionById(list).getName());
							float valor = horasVoladas.get(list);
							valor = valor / 60;
							aa.add(horasVoladas.get(list) + " minutos (" + valor + " horas)");
							filtrado.add(aa);

						}

						if (filtrado.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se encontraron vuelos en el intervalo del " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem());
							return;
						}

						// Create columns names
						String columnNames[] = {"Avion", "Tiempo de vuelo"};
						List<String> campos = Arrays.asList(columnNames);

						MainController.sleepActualAndCreateNew(Informes.this, new Informes_Table(Informes.this, campos, filtrado, "Horas de vuelo desde " + "01/" + inicioMes.getSelectedItem() + "/" + inicioAnio.getSelectedItem() + " a 01/" + finMes.getSelectedItem() + "/" + finAnio.getSelectedItem(), ""));

					}
				});
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
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

	public void inic() {
		List<Persona> personas = Persona.loadFromDB();
		for (Persona persona : personas) {
			personasCuentaCorriente.addItem(persona);
		}
	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub

	}

	private Hashtable<String, Integer> getHorasVoladasSince(Date fechaInicioSimple, Date fechaFinalSimple) {
		return getHorasVoladasSince(fechaInicioSimple, fechaFinalSimple, -1 + "");
	}
	private Hashtable<String, Integer> getHorasVoladasSince(Date fechaInicioSimple, Date fechaFinalSimple, String id) {
		List<Vuelo> vuelos = Vuelo.loadFromDB();
		Hashtable<String, Integer> horasVoladas = new Hashtable<String, Integer>();

		for (Vuelo vuelo : vuelos) {
			if (vuelo.getHoraInicio() > fechaInicioSimple.getTime() && vuelo.getHoraFinal() < fechaFinalSimple.getTime() && (id.equals("-1") || id.equals(vuelo.getIdAvion() + ""))) {
				if (!horasVoladas.containsKey(vuelo.getIdAvion() + ""))
					horasVoladas.put(vuelo.getIdAvion() + "", 0);

				horasVoladas.put(vuelo.getIdAvion() + "", horasVoladas.get(vuelo.getIdAvion() + "") + Utils.minutesBetweenDates(new Date(vuelo.getHoraInicio()), new Date(vuelo.getHoraFinal())));
			}
		}
		return horasVoladas;
	}

}
