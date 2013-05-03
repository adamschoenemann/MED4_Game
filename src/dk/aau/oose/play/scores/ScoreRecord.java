package dk.aau.oose.play.scores;

public class ScoreRecord implements Comparable<ScoreRecord>{
	
	String name;
	int score;
	
	public ScoreRecord(String name, int score){
		initiate(name, score);
	}
	
	public ScoreRecord(String lineFromTextFile){
		String name;
		int score;
		int positionOfColon = lineFromTextFile.indexOf(':');
		if(positionOfColon < 0){
			name = "ERR";
			score = 0;
			System.out.println("Error inferring highscore from " + lineFromTextFile);
		} else {
			name = lineFromTextFile.substring(0, positionOfColon);
			score = Integer.parseInt(lineFromTextFile.substring(positionOfColon + 1));
		}
		initiate(name, score);
	}
	
	private void initiate(String name, int score){
		this.name = name;
		this.score = score;
	}
	
	public String toString(){
		return (name + ":" + Integer.toString(score));
	}
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public int compareTo(ScoreRecord o) {		
		return (new Integer(score)).compareTo(new Integer(o.getScore()));
	}

}
