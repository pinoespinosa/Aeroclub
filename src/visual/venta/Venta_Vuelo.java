package visual.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Vuelo;
import extended.JDialogExtended;
import extended.MainController;
import extended.ObjetoDeImpresion;

public class Venta_Vuelo extends JDialogExtended {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<Vuelo> vuelosList;
	private JList<Vuelo> list;
	private JButton crearVueloBtn, imprimirComprobanteBtn;
	private JButton btnCerrarVuelo;
	private JButton btnEditarVuelo;
	private JButton btnVerVuelo;

	/**
	 * Create the dialog.
	 * 
	 * @param frmSistemaDeGestin
	 */
	public Venta_Vuelo(final JFrame parent) {
		super(parent);
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setTitle("Sistema de Gesti\u00F3n Aeroclub Tandil");
		setBounds(100, 100, 539, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 256, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 241, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{

			{
				GridBagConstraints gbc_inicioSpinner = new GridBagConstraints();
				gbc_inicioSpinner.fill = GridBagConstraints.BOTH;
				gbc_inicioSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_inicioSpinner.gridx = 3;
				gbc_inicioSpinner.gridy = 8;

			}
			{

				GridBagConstraints gbc_finalizacionSpinner = new GridBagConstraints();
				gbc_finalizacionSpinner.fill = GridBagConstraints.BOTH;
				gbc_finalizacionSpinner.insets = new Insets(0, 0, 5, 5);
				gbc_finalizacionSpinner.gridx = 3;
				gbc_finalizacionSpinner.gridy = 9;

			}
		}
		{
			final JPanel panelDerecho = new JPanel();
			panelDerecho.setBorder(new TitledBorder(new LineBorder(new Color(64, 64, 64), 1, true), "�ltimos vuelos realizados", TitledBorder.LEADING, TitledBorder.TOP, MainController.getDefaultFont(MainController.GROUP_LAYOUT), null));
			GridBagConstraints gbc_panelDerecho = new GridBagConstraints();
			gbc_panelDerecho.insets = new Insets(0, 0, 5, 5);
			gbc_panelDerecho.fill = GridBagConstraints.BOTH;
			gbc_panelDerecho.gridx = 1;
			gbc_panelDerecho.gridy = 1;
			getContentPane().add(panelDerecho, gbc_panelDerecho);
			GridBagLayout gbl_panelDerecho = new GridBagLayout();
			gbl_panelDerecho.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panelDerecho.rowHeights = new int[]{0, 35, 35, 35, 0};
			gbl_panelDerecho.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelDerecho.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelDerecho.setLayout(gbl_panelDerecho);
			{

				vuelosList = new DefaultListModel<Vuelo>();
				updateListVuelos();
			}
			{
				{
					JScrollPane scrollPane = new JScrollPane();
					GridBagConstraints gbc_scrollPane = new GridBagConstraints();
					gbc_scrollPane.gridwidth = 3;
					gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
					gbc_scrollPane.fill = GridBagConstraints.BOTH;
					gbc_scrollPane.gridx = 0;
					gbc_scrollPane.gridy = 0;
					panelDerecho.add(scrollPane, gbc_scrollPane);
					list = new JList<Vuelo>();
					scrollPane.setViewportView(list);
					list.setFont(new Font("Consolas", Font.ITALIC, 14));
					list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					list.setModel(vuelosList);
					FileListCellRenderer renderer = new FileListCellRenderer();
					list.setCellRenderer(renderer);
				}
			}
			{
			}
			{
				imprimirComprobanteBtn = new JButton("Imprimir comprobante");
				imprimirComprobanteBtn.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_imprimir.png")));
				imprimirComprobanteBtn.setEnabled(false);
				imprimirComprobanteBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						PrinterJob job = PrinterJob.getPrinterJob();
						PageFormat pf = job.defaultPage();
						Paper paper = new Paper();
						paper.setSize(612.0, 832.0);
						double margin = 10;
						paper.setImageableArea(margin, margin, paper.getWidth() - margin, paper.getHeight() - margin);
						pf.setPaper(paper);
						pf.setOrientation(PageFormat.REVERSE_LANDSCAPE);
						ObjetoDeImpresion pin = new ObjetoDeImpresion();

						SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm");

						Venta_Vuelo_Imprimible dialog = new Venta_Vuelo_Imprimible(parent);
						dialog.inic(list.getSelectedValue());
						dialog.setVisible(true);

						pin.setP(dialog.getImprimible());
						job.setPrintable(pin, pf);
						job.setJobName("funciona?");

						try {
							if (job.printDialog())
								job.print();
							dialog.dispose();
						} catch (Exception e) {
							System.out.println(e);
						}

					}
				});
				{
					crearVueloBtn = new JButton("Nuevo Vuelo    ");
					crearVueloBtn.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_aterrizaje.png")));
					crearVueloBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							Venta_Vuelo_Nuevo_Editar_Cerrar dialog = new Venta_Vuelo_Nuevo_Editar_Cerrar(Venta_Vuelo.this);
							MainController.sleepActualAndCreateNew(Venta_Vuelo.this, dialog);
							dialog.inic(list.getSelectedValue(), Venta_Vuelo_Nuevo_Editar_Cerrar.TYPE.MODE_CREAR);
						}
					});
					GridBagConstraints gbc_crearVueloBtn = new GridBagConstraints();
					gbc_crearVueloBtn.fill = GridBagConstraints.BOTH;
					gbc_crearVueloBtn.insets = new Insets(0, 0, 5, 5);
					gbc_crearVueloBtn.gridx = 0;
					gbc_crearVueloBtn.gridy = 1;
					panelDerecho.add(crearVueloBtn, gbc_crearVueloBtn);
				}
				{
					{
						btnEditarVuelo = new JButton("Editar Vuelo");
						btnEditarVuelo.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_editar.png")));
						btnEditarVuelo.setEnabled(false);
						btnEditarVuelo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
			
								if (list.getSelectedValue()!=null){
									Venta_Vuelo_Nuevo_Editar_Cerrar dialog = new Venta_Vuelo_Nuevo_Editar_Cerrar(Venta_Vuelo.this);
									MainController.sleepActualAndCreateNew(Venta_Vuelo.this, dialog);
									dialog.inic(list.getSelectedValue(), Venta_Vuelo_Nuevo_Editar_Cerrar.TYPE.MODE_EDICION);
								}
							}
						});
						GridBagConstraints gbc_btnEditarVuelo = new GridBagConstraints();
						gbc_btnEditarVuelo.fill = GridBagConstraints.BOTH;
						gbc_btnEditarVuelo.insets = new Insets(0, 0, 5, 5);
						gbc_btnEditarVuelo.gridx = 1;
						gbc_btnEditarVuelo.gridy = 1;
						panelDerecho.add(btnEditarVuelo, gbc_btnEditarVuelo);
					}
				}
				btnCerrarVuelo = new JButton("Cerrar Vuelo    ");
				btnCerrarVuelo.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_despegue.png")));
				btnCerrarVuelo.setEnabled(false);
				btnCerrarVuelo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (list.getSelectedValue()!=null){
							Venta_Vuelo_Nuevo_Editar_Cerrar dialog = new Venta_Vuelo_Nuevo_Editar_Cerrar(Venta_Vuelo.this);
							MainController.sleepActualAndCreateNew(Venta_Vuelo.this, dialog);
							dialog.inic(list.getSelectedValue(), Venta_Vuelo_Nuevo_Editar_Cerrar.TYPE.MODE_CERRAR);
						}
					}
				});
				GridBagConstraints gbc_btnCerrarVuelo = new GridBagConstraints();
				gbc_btnCerrarVuelo.fill = GridBagConstraints.BOTH;
				gbc_btnCerrarVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_btnCerrarVuelo.gridx = 2;
				gbc_btnCerrarVuelo.gridy = 1;
				panelDerecho.add(btnCerrarVuelo, gbc_btnCerrarVuelo);
				{
					btnVerVuelo = new JButton("Ver informaci\u00F3n del vuelo");
					btnVerVuelo.setIcon(new ImageIcon(Venta_Vuelo.class.getResource("/resources/icon_ver.png")));
					btnVerVuelo.setEnabled(false);
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.fill = GridBagConstraints.BOTH;
					gbc_btnNewButton.gridwidth = 3;
					gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
					gbc_btnNewButton.gridx = 0;
					gbc_btnNewButton.gridy = 2;
					panelDerecho.add(btnVerVuelo, gbc_btnNewButton);
					btnVerVuelo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							
							if (list.getSelectedValue() != null ) {
								Venta_Vuelo_Imprimible dialog = new Venta_Vuelo_Imprimible(parent);
								dialog.inic(list.getSelectedValue());
								dialog.setVisible(true);
							}
										}
					});
				}
				GridBagConstraints gbc_imprimirComprobanteBtn = new GridBagConstraints();
				gbc_imprimirComprobanteBtn.gridwidth = 3;
				gbc_imprimirComprobanteBtn.fill = GridBagConstraints.BOTH;
				gbc_imprimirComprobanteBtn.gridx = 0;
				gbc_imprimirComprobanteBtn.gridy = 3;
				panelDerecho.add(imprimirComprobanteBtn, gbc_imprimirComprobanteBtn);
			}
		}

		list.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				adjustButtons();
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {	}
			
			@Override
			public void keyPressed(KeyEvent arg0) {		}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				adjustButtons();
			}
		});
	}

	public void updateListVuelos() {
		vuelosList.removeAllElements();
		for (Vuelo vuelo : Vuelo.loadFromDB())
			vuelosList.addElement(vuelo);

	}

	public void adjustButtons(){
		Vuelo selected = list.getSelectedValue();
		
		if (selected == null){
			btnEditarVuelo.setEnabled(false);
			btnCerrarVuelo.setEnabled(false);
			btnVerVuelo.setEnabled(false);
			imprimirComprobanteBtn.setEnabled(false);
		}
		else
		{
			
			btnEditarVuelo.setEnabled(selected.isOpen());
			btnCerrarVuelo.setEnabled(selected.isOpen());
			imprimirComprobanteBtn.setEnabled(!selected.isOpen());
			btnVerVuelo.setEnabled(true);			
		}
		
	}
	
	@Override
	public void updateUi() {
		updateListVuelos();

	}
}
