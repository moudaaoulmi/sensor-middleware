package com.andrzejborowczyk.bdihospitalization;

import javax.swing.SwingUtilities;

import jadex.runtime.IInternalEvent;
import jadex.runtime.Plan;


/**
 * GUI for the assistant agent showing temperature desire
 * @author andrzej
 *
 * 
 */
@SuppressWarnings("serial")
public class TemperatureDesireGUIPlan extends Plan {
	
	GUIForAssistantAgent gui;

	public TemperatureDesireGUIPlan() {
		gui = new GUIForAssistantAgent();
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
