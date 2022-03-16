import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
public class Member implements Iterable<Interest>, Serializable {
	private String name;
    private int year;
    private LinkedList<Interest> interests;
    
    public Member(String name, int year) throws InvalidMemberException { 
    	setName(name);
    	setYear(year);
    	interests = new LinkedList<Interest>();
    }
    public void setName(String name) throws InvalidMemberException {
    	if (name.equals("")) {
    		throw new InvalidMemberException("Name may not be blank");
    	}
    	this.name = name;
    }
    public void setYear(int year) throws InvalidMemberException {
       if (year < 1 || year > 5) {
    	   throw new InvalidMemberException("Year " + year + " is invalid; please specify between 1-5");
       }
       this.year = year;
    }
    public String getName() {
       return name;
    }
    public int getYear() {
       return year;
    }
    public String toString() {
    	String result = "";
        for (Interest interest: interests) {
        	result += "\n"+interest.getTopic() + ": "+ interest.getLevel(); 
        	}
        return name+ " - "+ year + result;
    }
    public void addInterest(Interest interest) {
    	Interest duplicate = findInterest(interest.getTopic()) ;
    	if ( duplicate != null) { 
   		 	interests.remove(duplicate);
   		}
   		interests.add(interest);
   		interests.sort(null);
    }
    public Interest findInterest(String topic) {
    	for (Interest interest: interests) {
			if (interest.getTopic().equals(topic)) { 
				return interest;
			}
		}
		return null;	
    }
    public Iterator<Interest> iterator(){
		return interests.iterator();	
	}
    
}
