package com.stormdev.minigamez.editor;

import java.util.ArrayList;

public class GameEventList extends OptionList {
	private static final long serialVersionUID = 1090516548573439068L;
	public ArrayList<String> vals = new ArrayList<String>();
	private WindowArea window = null;
	public GameEventList(WindowArea area){
		super(area);
		this.window = area;
		calculate();
	}
	public void calculate(){
		vals.add("gameStart");
		vals.add("gameEnd");
		vals.add("gameExit");
		vals.add("playerDie");
		vals.add("onePlayerLeft");
		vals.add("NumPlayersLeft");
		if(window.useTeams.isSelected()){
		vals.add("oneTeamLeft");
		}
		vals.add("playerArriveAtLocation");
		vals.add("playerOutsideArenaBounds");
		vals.add("playerOutOfLives");
		vals.add("playerQuitGame");
		vals.add("playerRespawn");
		this.setVals(vals);
	}

}
