package dk.aau.oose.play.scores;

import java.util.Comparator;

public class ScoreRecordComparator implements Comparator<ScoreRecord> {

	@Override
	public int compare(ScoreRecord a, ScoreRecord b) {
		int values = a.compareTo(b);
		
		if(values != 0)
			return -values;
		else {
			return a.getName().compareTo(b.getName());
		}
	}
}
