package com.andrzejborowczyk.bdihospitalization;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


/**
 * GUI for the heart beat measuring agent
 * @author andrzej
 * 
 * 
 *
 */
public class GUIForHBMAgent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890670766862950027L;

	protected DefaultTableModel tadata;

	public GUIForHBMAgent() {
		
		//this.setName("Airconditioning agent GUI");
		this.setTitle("Heart Beat Measure GUI");
		
		String[] columns = new String[] {"Time",  "Current heart beat", "Action" };
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
