import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
public class Membership implements Iterable<Member>, Serializable{
	private LinkedList<Member> members;
	private LinkedList<Compatibility> compat;
	public Membership() {
		members = new LinkedList<Member>();
		compat = new LinkedList<Compatibility>();
	}
	public void addMember(Member member) {
		members.add(member);
	}
	public Member findMember(String name) {
		for (Member member: this) {
			if (member.getName().equals(name)) { 
				return member;
			}
		}
		return null;	
	}
	public String toString() {
		String result = "";
        for (Member member: this) {
        	result += member.getName() + "\n"; }
        return result;
	}
	public Iterator<Member> iterator(){
		return members.iterator();	
	}
	public void save(String filename) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(this); 
		oos.flush();
		oos.close(); 
		
	}
	public Membership load(String filename) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		Membership m = (Membership) ois.readObject();
		ois.close();
		return m;		
	}
	public Iterator<Compatibility> compat(Member m) throws Exception {
		for (Member mem : members) {
			if (m.getName().equalsIgnoreCase(mem.getName()))
				continue;
			else
				compat.add(new Compatibility(m, mem));
		}
		compat.sort(null);
		return compat.iterator();		
	}
}
