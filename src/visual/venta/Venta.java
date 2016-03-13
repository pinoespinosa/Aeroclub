package visual.venta;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import extended.JDialogExtended;
import extended.MainController;

public class Venta extends JDialogExtended {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Venta(final Window frame) {
		super(frame);
		setTitle("Tipos de venta");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[]{32, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JButton btnVuelo = new JButton("Nuevo Vuelo");
			btnVuelo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					MainController.closeActualAndCreateNew(Venta.this, new Venta_Vuelo(frame));
				}
			});
			btnVuelo.setFont(MainController.getDefaultFont(MainController.BUTTON));
			GridBagConstraints gbc_btnVuelo = new GridBagConstraints();
			gbc_btnVuelo.insets = new Insets(0, 0, 5, 0);
			gbc_btnVuelo.fill = GridBagConstraints.BOTH;
			gbc_btnVuelo.gridx = 0;
			gbc_btnVuelo.gridy = 0;
			contentPanel.add(btnVuelo, gbc_btnVuelo);
		}
		{
			JButton btnCombustible = new JButton("Nuevo Venta de Combustible/Aceite");
			btnCombustible.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					MainController.closeActualAndCreateNew(Venta.this, new Venta_Combustible(frame));
				}
			});
		{
			JButton btnNuevoVueloPor = new JButton("Nuevo Vuelo por Adelantado");
			btnNuevoVueloPor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainController.closeActualAndCreateNew(Venta.this, new Venta_Vuelo_Adelantado(frame));
				}
			});
			btnNuevoVueloPor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_btnNuevoVueloPor = new GridBagConstraints();
			gbc_btnNuevoVueloPor.fill = GridBagConstraints.BOTH;
			gbc_btnNuevoVueloPor.insets = new Insets(0, 0, 5, 0);
			gbc_btnNuevoVueloPor.gridx = 0;
			gbc_btnNuevoVueloPor.gridy = 1;
			contentPanel.add(btnNuevoVueloPor, gbc_btnNuevoVueloPor);
		}
			btnCombustible.setFont(MainController.getDefaultFont(MainController.BUTTON));
			GridBagConstraints gbc_btnCombustibleAceite = new GridBagConstraints();
			gbc_btnCombustibleAceite.insets = new Insets(0, 0, 5, 0);
			gbc_btnCombustibleAceite.fill = GridBagConstraints.BOTH;
			gbc_btnCombustibleAceite.gridx = 0;
			gbc_btnCombustibleAceite.gridy = 2;
			contentPanel.add(btnCombustible, gbc_btnCombustibleAceite);
		}
		{
			JButton btnNuevoDepositoDe = new JButton("Nuevo Deposito de Dinero");
			btnNuevoDepositoDe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					MainController.closeActualAndCreateNew(Venta.this, new Venta_Deposito_Dinero(frame));
				}
			});
			btnNuevoDepositoDe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_btnNuevoDepositoDe = new GridBagConstraints();
			gbc_btnNuevoDepositoDe.insets = new Insets(0, 0, 5, 0);
			gbc_btnNuevoDepositoDe.fill = GridBagConstraints.BOTH;
			gbc_btnNuevoDepositoDe.gridx = 0;
			gbc_btnNuevoDepositoDe.gridy = 3;
			contentPanel.add(btnNuevoDepositoDe, gbc_btnNuevoDepositoDe);
		}
		{
			JButton btnExtraccinDeCombustibleaceite = new JButton("Administrar Combustible/Aceite ajeno");
			btnExtraccinDeCombustibleaceite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainController.closeActualAndCreateNew(Venta.this, new Venta_Combustible_Propio(frame));
				}
			});
			btnExtraccinDeCombustibleaceite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_btnExtraccinDeCombustibleaceite = new GridBagConstraints();
			gbc_btnExtraccinDeCombustibleaceite.fill = GridBagConstraints.BOTH;
			gbc_btnExtraccinDeCombustibleaceite.gridx = 0;
			gbc_btnExtraccinDeCombustibleaceite.gridy = 4;
			contentPanel.add(btnExtraccinDeCombustibleaceite, gbc_btnExtraccinDeCombustibleaceite);
		}
		inic();
	}
	@PostConstruct
	private void inic() {

	}
	@Override
	public void updateUi() {
		// TODO Auto-generated method stub

	}

}
