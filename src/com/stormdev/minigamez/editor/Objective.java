package com.stormdev.minigamez.editor;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Objective extends JPanel implements ActionListener{
	private static final long serialVersionUID = -8722850909010143667L;
	WindowArea window = null;
	GameEventList event = null;
	ObjectiveActions actions = null;
	JPanel values = new JPanel();
	JButton save = new JButton("Save!");
	JLabel when = new JLabel("When:");
	JLabel DO = new JLabel("Do:");
    public Objective(WindowArea area, ObjectiveManager manager){
    	this.window = area;
    	event = new GameEventList(area);
    	actions = new ObjectiveActions(area);
    	event.addActionListener(this);
    	actions.addActionListener(this);
    	event.draw();
    	actions.draw();
    	save.addActionListener(manager);
    }
    public String getEvent(){
    	return (String)this.event.getSelectedItem();
    }
    public String getAction(){
    	return (String)this.actions.getSelectedItem();
    }
    public HashMap<String, Object> getActionValues(){
		HashMap<String, Object> vals = new HashMap<String, Object>();
		Component[] comps = values.getComponents();
		for(Component comp:comps){
			if(comp.getName().toLowerCase().contains("actionarg")){
				if(comp instanceof JTextField){
					vals.put(comp.getName(), ((JTextField)comp).getText());
					((JTextField) comp).setText("");
				}
				if(comp instanceof OptionList){
					vals.put(comp.getName(), ((OptionList)comp).getSelectedItem());
				}
			}
		}
		return vals;
    }
    public HashMap<String, Object> getEventValues(){
		HashMap<String, Object> vals = new HashMap<String, Object>();
		Component[] comps = values.getComponents();
		for(Component comp:comps){
			if(comp.getName().toLowerCase().contains("eventarg")){
				if(comp instanceof JTextField){
					if(comp.getName().toLowerCase().contains("t:int")){
						int val = 1;
						try {
							val = Integer.parseInt(((JTextField) comp).getText());
						} catch (NumberFormatException e) {
							window.popUpMsg("Field should contain an integer!", "ERROR");
							return null;
						}
						vals.put(comp.getName(), val);
						((JTextField) comp).setText("");
					}
					else{
					vals.put(comp.getName(), ((JTextField)comp).getText());
					((JTextField) comp).setText("");
					}
				}
				if(comp instanceof OptionList){
					vals.put(comp.getName(), ((OptionList)comp).getSelectedItem());
				}
			}
		}
		return vals;
    }
    public void draw(){
    	clearPanel(values);
    	this.add(when);
    	this.add(event);
    	this.add(DO);
    	this.add(actions);
    	//adjust values tab
    	if(getEvent().equalsIgnoreCase("numPlayersLeft")){
    		JTextField pLeft = new JTextField(2);
    		pLeft.setText("2");
    		pLeft.setName("eventarg0t:int");
    		values.add(new JLabel("Left:"));
    		values.add(pLeft);
    	}
    	if(getAction().equalsIgnoreCase("sendMessage")){
    		JTextField toSend = new JTextField(16);
    		toSend.setName("actionarg0");
    		OptionList to = new OptionList(window);
    		ArrayList<String> recipients = new ArrayList<String>();
    		recipients.add("player");
    		recipients.add("team");
    		to.setVals(recipients);
    		to.setName("actionarg1");
    		to.draw();
    		values.add(new JLabel("Msg:"));
    		values.add(toSend);
    		values.add(new JLabel("To:"));
    		values.add(to);
    	}
    	this.add(values);
    	this.add(save);
    	window.refresh();
    }
    public void clearPanel(JPanel panel){
    	panel.removeAll();
    	return;
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
	    draw();
	}
    
	

}
