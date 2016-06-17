package visual.venta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Gasto;
import extended.JDialogExtended;
import extended.ObjetoDeImpresion;

public class Menu_Comprobantes extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Gasto> gastosComboBox;
	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 */
	public Menu_Comprobantes(final Window parent) {
		super(parent);
		setTitle("Pago a instructores");
		setBounds(100, 100, 469, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{

			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinner.gridwidth = 2;
			gbc_spinner.insets = new Insets(0, 0, 5, 5);
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 1;

		}
		{
			JLabel lblDestinatario = new JLabel("Comprabantes generados");
			GridBagConstraints gbc_lblDestinatario = new GridBagConstraints();
			gbc_lblDestinatario.anchor = GridBagConstraints.EAST;
			gbc_lblDestinatario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDestinatario.gridx = 1;
			gbc_lblDestinatario.gridy = 2;
			contentPanel.add(lblDestinatario, gbc_lblDestinatario);
		}
		{
			gastosComboBox = new JComboBox<Gasto>();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 2;
			contentPanel.add(gastosComboBox, gbc_comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Imprimir");
				okButton.addActionListener(new ActionListener() {
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

						Venta_Combustible_Imprimible dialog = new Venta_Combustible_Imprimible(parent);
						dialog.inic((Gasto) gastosComboBox.getSelectedItem());
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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

	private void inic() {

		List<Gasto> listGasto = Gasto.loadFromDBSinDuplicados();
		Collections.sort(listGasto);
		for (Gasto instructor : listGasto) {
			gastosComboBox.addItem(instructor);
		}

	}

	@Override
	public void updateUi() {

	}

}
