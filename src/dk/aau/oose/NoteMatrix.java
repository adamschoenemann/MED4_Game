package dk.aau.oose;

public class NoteMatrix {
	
	
	private int[][] notes;
	
	public NoteMatrix(int rows, int cols){
		notes = new int[rows][cols];
	}
	
	public void setNote(int row, int col, int val){
		notes[row][col] = val;
	}
	
	public int getNote(int row, int col){
		return notes[row][col];
	}
	
	public int getRows(){
		return notes.length;
	}
	
	public int getColumns(){
		return notes[0].length;
	}
	
	public void getColumn(int colIndex, int[] output){
		if(output.length != notes.length){
			throw new IndexOutOfBoundsException();
		}
		for(int r = 0; r < notes.length; r++){
			output[r] = notes[r][colIndex];
		}
	}
	
	public void getRow(int rowIndex, int[] output){
		if(output.length != notes[0].length){
			throw new IndexOutOfBoundsException();
		}
		for(int c = 0; c < notes[0].length; c++){
			output[c] = notes[rowIndex][c];
		}
	}
	
	public void setColumn(int colIndex, int[] input){
		if(input.length != notes.length){
			throw new IndexOutOfBoundsException();
		}
		for(int r = 0; r < notes.length; r++){
			notes[r][colIndex] = input[r];
		}
	}
	
	public void setRow(int rowIndex, int[] input){
		if(input.length != notes[0].length){
			throw new IndexOutOfBoundsException();
		}
		for(int c = 0; c < notes[0].length; c++){
			notes[rowIndex][c] = input[c];
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < notes.length; r++){
			sb.append("|");
			for(int c = 0; c < notes[0].length; c++){
				sb.append(notes[r][c]).append("|");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static NoteMatrix testInstance(int rows, int cols){
		NoteMatrix nm = new NoteMatrix(rows, cols);
		for(int r = 0; r < nm.getRows(); r++){
			for(int c = 0; c < nm.getColumns(); c++){
				if(Math.random() > 0.7)
					nm.setNote(r, c, (int) Math.round(Math.random() * 3f));
			}
		}
		return nm;
	}
	
	public static void main(String[] args){
		NoteMatrix nm = new NoteMatrix(5, 8);
		int[] row1 = new int[nm.getColumns()];
		for(int i = 0; i < row1.length; i++){
			row1[i] = (int) Math.round(Math.random() * 3f);
		}
		nm.setRow(0, row1);
		System.out.println(nm);
		nm.getRow(0, row1);
		
	}
	
}
