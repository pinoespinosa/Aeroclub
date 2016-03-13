package visual;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import extended.JDialogExtended;

public class Informes_Table extends JDialogExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel topPanel;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel additionaInformation;

	/**
	 * Create the dialog.
	 */
	public Informes_Table(final Window parent, List<String> columnNames, List<List<String>> dataValues, String titulo, String extraInformation) {
		super(parent);

		// Set the frame characteristics
		setTitle(titulo);
		setSize(840, 606);
		setBackground(Color.gray);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 686, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 472, 0, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setAutoscrolls(true);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 1;
		gbc_topPanel.gridy = 1;
		getContentPane().add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{686, 0};
		gbl_topPanel.rowHeights = new int[]{458, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);

		// Create a new table instance
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setAutoCreateRowSorter(true);

		// instance table model
		DefaultTableModel tableModel = new DefaultTableModel(listOfListToArray(dataValues), columnNames.toArray()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		topPanel.add(scrollPane, gbc_scrollPane);
		{
			additionaInformation = new JLabel(extraInformation);
			GridBagConstraints gbc_additionaInformation = new GridBagConstraints();
			gbc_additionaInformation.anchor = GridBagConstraints.EAST;
			gbc_additionaInformation.insets = new Insets(0, 0, 5, 5);
			gbc_additionaInformation.gridx = 1;
			gbc_additionaInformation.gridy = 2;
			getContentPane().add(additionaInformation, gbc_additionaInformation);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.insets = new Insets(0, 0, 0, 5);
			gbc_buttonPane.anchor = GridBagConstraints.SOUTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 1;
			gbc_buttonPane.gridy = 3;
			getContentPane().add(buttonPane, gbc_buttonPane);
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

	}

	@Override
	public void updateUi() {
		// TODO Auto-generated method stub

	}

	private String[][] listOfListToArray(List<List<String>> dataValues) {

		String[][] valores = new String[dataValues.size()][dataValues.get(0).size()];

		for (int i = 0; i < dataValues.size(); i++) {
			for (int j = 0; j < dataValues.get(i).size(); j++) {
				valores[i][j] = dataValues.get(i).get(j);
			}
		}
		return valores;
	}

}
