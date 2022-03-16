
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
public class Menu {
 private Membership membership;
 private Scanner keyboard;
 
 public Menu() {
	 membership = new Membership();
	 keyboard = new Scanner(System.in);
 }
 public void run() { 
	 String input = "";
	 int menuOptions;
	 
	 while (!input.equalsIgnoreCase("y")) {
		 try {
		 System.out.println("Menu will appear here");
		 System.out.println("1. Load Members\n2. Save Members\n3. List All Members\n4. Add Member\n5. Remove Member\n6. List Member\n7. Add Interest");
		 menuOptions = keyboard.nextInt();
		 keyboard.nextLine();
		 
		 switch (menuOptions) {
		 case 1:
			 loadMembers();
			 break;
		 case 2:
			 saveMembers();
			 break;
		 case 3:
			 listAllMembers();
			 break;
		 case 4:
			 addMember();
			 break;
		 case 5:
			 removeMember();
			 break;
		 case 6:
			 listMember();
			 break;
		 case 7: 
			 addInterest();
			 break;
		 default:
			 System.out.println("There is no such "+ menuOptions+" option.");
			 break;
		 }	
	 } catch (InputMismatchException e) {
			 System.out.println("Input Mismatch");
			 keyboard.nextLine();
		 }	 
		 System.out.println("Do you want to quit?");
		 input = keyboard.nextLine();
	 }
	 }
 private void loadMembers()  {
	 System.out.println("Load Members");
	 System.out.print("File name: ");
	 String filename = keyboard.nextLine();
	 try {
		 membership = membership.load(filename);
	 } catch (ClassNotFoundException e) {
		 System.out.println("Class is not found");
		 System.out.print("Enter a new file name: ");
		 filename = keyboard.nextLine();
	 } catch (IOException e) {
		 System.out.println("File error");
		 System.out.print("Enter a new file name: ");
		 filename = keyboard.nextLine();
	 }	 
 }
 private void saveMembers() {
	 System.out.println("Save Members");
	 System.out.print("File name: ");
	 String filename = keyboard.nextLine();
	 try {
		  membership.save(filename);
		 } catch (IOException e) {
			 System.out.println("File error");
			 System.out.print("Enter a new file name: ");
			 filename = keyboard.nextLine();
		 }
 }
 private void listAllMembers() {
	 System.out.println("List All Members");
	 System.out.println(membership);
 }
 private void addMember() {
	 System.out.println("Add Member");
	 System.out.print("Name: ");
	 String name = keyboard.nextLine();
	 
	 if (membership.findMember(name) != null) {
		 System.out.println("That member already exists");
		 return;
	 }
	 try {
	 System.out.print("Year (1-5): ");
	 int year = keyboard.nextInt();
	 
		 Member member = new Member(name, year);
	 	 membership.addMember(member);
	 } catch (InvalidMemberException e) {
		 System.out.println(e.getMessage());
	 } catch (InputMismatchException e) {
		 System.out.println("Input Mismatch");
	 }
	 keyboard.nextLine();
 }
 private void removeMember() {
	 System.out.println("Remove Member");
	 Iterator<Member> iter = membership.iterator();
	 System.out.println("Name: ");
	 String name = keyboard.nextLine();
	 while (iter.hasNext()) {
		 if (iter.next().equals(membership.findMember(name))) {
			iter.remove();
			System.out.println("Found "+ name);
			return;
		 }
	 }
	 System.out.println(name+ " is not found");
 }
 private void listMember() {
	 System.out.println("List Member");
	 System.out.print("Name: ");
	 String name = keyboard.nextLine();
	 
	 if (membership.findMember(name) == null) {
		 System.out.println("That member does not exist");
	 }
	 else {	
		 Member member = membership.findMember(name);  
		 System.out.println(member);
		 try {
		 membership.compat(member);
		 } catch (Exception e) {
		 System.out.println("Compatible score is not found");
		 }
	 }
	 
		 
 }
 private void addInterest() {
	 System.out.println("Add Interest");
	 System.out.print("Name: ");
	 String name = keyboard.nextLine();
	 if (membership.findMember(name) == null) {
		 System.out.println("That member does not exists");
		 return;
	 }
	 System.out.print("Topic: ");
	 String topic = keyboard.nextLine();
	 
	 Member member = membership.findMember(name);
	
	
	 try {
	 System.out.print("Level (0-10): ");
	 int level = keyboard.nextInt();
	 
		 Interest interest = new Interest(topic, level);
	 	 member.addInterest(interest);
	 } catch (InvalidInterestException e) {
		 System.out.println(e.getMessage());
	 } catch (InputMismatchException e) {
		 System.out.println("Input Mismatch");
	 }
	 keyboard.nextLine();
 }
}
