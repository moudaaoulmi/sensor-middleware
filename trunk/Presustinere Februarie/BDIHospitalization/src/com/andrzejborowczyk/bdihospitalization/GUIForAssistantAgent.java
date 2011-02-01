package com.andrzejborowczyk.bdihospitalization;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *  Assistant agent gui.
 * @author andrzej
 * 
 *
 *
 */
public class GUIForAssistantAgent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890670766862950027L;

	protected DefaultTableModel tadata;

	public GUIForAssistantAgent() {
		
		this.setTitle("Assistant agent GUI");
		
		String[] columns = new String[] {"Time", "Patient desires tempearature", "Action" };
		this.tadata = new DefaultTableModel(columns, 0);
		JTable tatable = new JTable(tadata);
		JScrollPane sp = new JScrollPane(tatable);
		this.getContentPane().add("Center", sp);
		this.pack();
		this.setVisible(true);
	}

	public void addRow(final String[] content) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tadata.addRow(content);
				tadata.moveRow(tadata.getRowCount()-1, tadata.getRowCount()-1, 0);
			}
		});
	}

}
