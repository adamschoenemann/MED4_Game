package dk.aau.oose.play.scores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class HighScoreManager {
	
	private static HighScoreManager instance;
	
	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	private TreeSet<ScoreRecord> scoreRecords;
	

	public static void main(String[] args) {
		HighScoreManager hsm = new HighScoreManager();
		
		hsm.deleteRecords();
		
		hsm.add("A", 1000);
		hsm.add("B", 10);

		System.out.println(hsm.toString());
		
		hsm.add("C", 22);

		System.out.println(hsm.toString());
		
		hsm.add("D", -1);
		
		hsm.commit();
	}
	
	public HighScoreManager(){
		file = new File("highscores.txt");
		
		if(!file.exists()) {
			try {
				System.out.println("Highscores file doesn't exist - creating a new one...");
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		scoreRecords = readAll();
	}
	
	public static HighScoreManager getInstance(){
		if(instance == null){
			instance = new HighScoreManager();
		}
		return instance;
	}
	
	public void add(ScoreRecord s){
		scoreRecords.add(s);
	}
	
	public void add(String name, int score){
		add(new ScoreRecord(name, score));
	}
	
	public void removeAllOf(String name){
		for(ScoreRecord s: scoreRecords){
			if(s.getName().contains(name)){
				scoreRecords.remove(s);
			}
		}
	}
	
	
	/*
	private void write(ScoreRecord sr){
		try {
			writer = new BufferedWriter( new FileWriter(file, true));
			writer.write(sr.toString());
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}*/
	
	private TreeSet<ScoreRecord> readAll(){
		TreeSet<ScoreRecord> srs = new TreeSet<ScoreRecord>(new ScoreRecordComparator());
		try {
			reader = new BufferedReader(  new FileReader(file));
			String line = reader.readLine();
			while(line != null){
				srs.add(new ScoreRecord(line));
				line = reader.readLine();
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return srs;
	}
	
	private void commit(){
		try {
			writer = new BufferedWriter( new FileWriter(file, false));
			for(ScoreRecord s: scoreRecords){
				writer.write(s.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteRecords(){
		scoreRecords.clear();
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		for(ScoreRecord s : scoreRecords){
			out.append(s.toString());
			out.append( System.getProperty("line.separator"));
		}
		return out.toString();
	}
	
	public int size(){
		return scoreRecords.size();
	}
	
	public String getNameAt(int index){
		ScoreRecord s = getScoreRecordAt(index);
		if(s != null){
			return s.getName();
		} else {
			return null;
		}
	}
	
	public String getScoreAt(int index){
		ScoreRecord s = getScoreRecordAt(index);
		if(s != null){
			return Integer.toString(s.getScore());
		} else {
			return null;
		}
	}
	
	public ScoreRecord getScoreRecordAt(int index){
		if(index >= 0 && index < size()){
			int currentIndex = 0;
			for(ScoreRecord s : scoreRecords){
				if(currentIndex++ == index){
					return s;
				}
			}
		}
		return null;
	}
}
