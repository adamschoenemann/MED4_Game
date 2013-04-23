package dk.aau.oose.notmat;

import java.util.Timer;
import java.util.TimerTask;

import oscP5.OscMessage;
import dk.aau.oose.osc.MaxMSP;

public class NoteMatrixPlayer {
	
	private static final String LABEL = "note";
	private NoteMatrix nm;
	private int sleepTime;
	private Thread playThread;
	private Timer beat;
	private TimerTask timerTask = new TimerTask(){

		@Override
		public void run() {
			System.out.println("timerTask!");
			
		}
		
	};
	
	public NoteMatrixPlayer(NoteMatrix matrix, int tempo){
		nm = matrix;
		sleepTime = 1000 * 60 / tempo;
		System.out.println("Sleep time:" + sleepTime);
	}
	
	// TODO: Use threads? (clean up) or timers or executor service?
	public void play(){
		playThread = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int colIndex = 0; colIndex < nm.getColumns(); colIndex++){
					for(int rowIndex = 0; rowIndex < nm.getRows(); rowIndex++){
						playNoteAt(rowIndex, colIndex);
					}
					try {
						System.out.println("---------");
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
				}
			}
		});
		playThread.start();
	}
	
	public void playColumnAt(int index){
		int colHeight = nm.getColumnHeight();
		for(int row = 0; row < colHeight; row++){
			playNoteAt(row, index);
		}
		
	}
	
	public void playNoteAt(int row, int col){
		int note = nm.getNote(row, col);
		if(note > 0){
			sendNote(nm.getColumnHeight() - row, note, sleepTime);
		}
	}
	
	public final NoteMatrix getMatrix(){
		return nm;
	}
	
	public void stop(){
		if(playThread == null) return;
		if(playThread.isAlive()){
			playThread.interrupt();
		}
	}
	
	private void sendNote(int position, int octave, long duration){
		OscMessage msg = new OscMessage(LABEL);
		msg.add(position);
		msg.add(octave);
		msg.add(duration);
		System.out.format("Note: %d, %d, %d\n", position, octave, duration);
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
