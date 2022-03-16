import java.util.LinkedList;

public class Compatibility implements Comparable<Compatibility>{
 private Member their;
 private int score;
 public Compatibility (Member my, Member their) {
	 for (Interest i1 : my)
		 for (Interest i2 : their) {
			 if (i1.getTopic().compareToIgnoreCase(i2.getTopic()) == 0)
				 score += i1.getLevel()*i2.getLevel();
		 }
			for (Interest i2 : their) {
				if(my.findInterest(i2.getTopic()) == null)
					score+= i2.getLevel()/2;
			}	 
 }
 public int getScore() {
	 return score;
 }
 public Member getTheir() {
	 return their;
 }
 public String toString() {
	 return their.getName() + "-"+ score;
 }
 public int compareTo(Compatibility o) {
	 if (score == o.getScore()) {
			if (their.getName().compareTo(o.their.getName()) > 0)
				return 1;
			else
				return -1;
		}
		else if (score < o.getScore())
			return -1;
		else 
			return 1;
	
 }
}
