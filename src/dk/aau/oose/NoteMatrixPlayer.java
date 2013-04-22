package dk.aau.oose;

import dk.aau.oose.osc.MaxMSP;
import oscP5.OscMessage;

public class NoteMatrixPlayer {
	
	private static final String LABEL = "note";
	private NoteMatrix nm;
	private int sleepTime;
	
	public NoteMatrixPlayer(NoteMatrix matrix, int tempo){
		nm = matrix;
		sleepTime = 1000 * 60 / tempo;
		System.out.println("Sleep time:" + sleepTime);
	}
	
	public void play(){
		for(int colIndex = 0; colIndex < nm.getColumns(); colIndex++){
			for(int rowIndex = 0; rowIndex < nm.getRows(); rowIndex++){
				int note = nm.getNote(rowIndex, colIndex);
				if(note > 0){
					int position = nm.getRows() - rowIndex;
					sendNote(position, note, sleepTime);
					System.out.println("Note: " + position);
				}
			}
			try {
				System.out.println("---------");
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendNote(int position, int octave, long duration){
		OscMessage msg = new OscMessage(LABEL);
		msg.add(position);
		msg.add(octave);
		msg.add(duration);
		MaxMSP.send(msg);
	}
	
	public static void main(String[] args){
		MaxMSP.Connect("127.0.0.1", 7400);
		NoteMatrix nm = new NoteMatrix(5, 32);
		int[] row1 = new int[nm.getColumns()];
		for(int i = 0; i < row1.length; i++){
			row1[i] = (int) Math.round(Math.random() * 3f);
		}
		nm.setRow(0, row1);
		for(int r = 0; r < nm.getRows(); r++){
			for(int c = 0; c < nm.getColumns(); c++){
				nm.setNote(r, c, (int) Math.round(Math.random() * 3f));
			}
		}
		System.out.println(nm);
		NoteMatrixPlayer nmp = new NoteMatrixPlayer(nm, 200);
		nmp.play();
	}
	
}
