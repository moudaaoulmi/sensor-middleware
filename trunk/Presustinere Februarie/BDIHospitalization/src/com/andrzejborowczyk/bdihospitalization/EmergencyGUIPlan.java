package com.andrzejborowczyk.bdihospitalization;

import javax.swing.SwingUtilities;

import jadex.runtime.IInternalEvent;
import jadex.runtime.Plan;


/**
 * Plan for updating the gui
 * @author andrzej
 *
 *
 */
@SuppressWarnings("serial")
public class EmergencyGUIPlan extends Plan {
	
	GUIForEmergencyAgent gui;

	public EmergencyGUIPlan() {
		gui = new GUIForEmergencyAgent();
		log("Created: " + this);
	}
	@Override
	public void body() {
		while (true) {
			IInternalEvent event = waitForInternalEvent("gui_update");
			String[] content =  (String[]) event.getParameter("content").getValue();
			gui.addRow(content);
		}
	}
	
	public void aborted() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui.dispose();
			}
		});
	}

	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
