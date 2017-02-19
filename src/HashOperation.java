import java.io.PrintWriter;
import java.util.HashMap;


public class HashOperation {
	
	public void printAll(HashMap <Integer, Student> hashTable, String massage, PrintWriter p){
		System.out.println(massage);
		p.println(massage);
		
		for(Integer key: hashTable.keySet()){
			
			Student temp = hashTable.get(key);
			
			System.out.println();
			System.out.print("Student's name & id : ");
			System.out.print(temp.getLastName() + ", " + temp.getFirstName());
			System.out.println(", " + temp.getId());
			
			p.println();
			p.print("Student's name & id : ");
			p.print(temp.getLastName() + ", " + temp.getFirstName());
			p.println(", " + temp.getId());
			
			
			System.out.println("Total credits       : " + temp.getTotalCredits());
			p.println("Total credits       : " + temp.getTotalCredits());
			
			System.out.println("Gpa (calculated)    : " + temp.getGPA());
			p.println("Gpa (calculated)    : " + temp.getGPA());
					
			System.out.println("Course info :");
			p.println("Course info :");
			
			for (int j = 0; j < temp.courseList.size(); j++) {

				System.out.println(temp.courseList.get(j).getCourseNumber() + "	 "
						+ temp.courseList.get(j).getGradeRecieved());
				

				p.println(temp.courseList.get(j).getCourseNumber() + "	 "
						+ temp.courseList.get(j).getGradeRecieved());
			}
			System.out.println();
			p.println();	
		}
	}
	
	
	public void printSpecific (HashMap <Integer, Student> hashTable, int digit, PrintWriter p){
		boolean flag = false;
		
		for (int key: hashTable.keySet()){
			if (key == digit)
				flag =true;
		}
		
		if (flag == false){
			System.out.println("Sorry, student isn't in the database.");
			return;
		}

		Student temp = hashTable.get(digit);
		
		System.out.println("Here's that student's info:");
		System.out.println();
		System.out.print("Student's name & id : ");
		System.out.print(temp.getLastName() + ", " + temp.getFirstName());
		System.out.println(", " + temp.getId());
		
		System.out.println("Total credits       : " + temp.getTotalCredits());
		System.out.println("Gpa (calculated)    : " + temp.getGPA());
		System.out.println("Course info :");
		
		
		p.println("Here's that studen's info:");
		p.println();
		p.print("Student's name & id : ");
		p.print(temp.getLastName() + ", " + temp.getFirstName());
		p.println(", " + temp.getId());
		
		p.println("Total credits       : " + temp.getTotalCredits());
		p.println("Gpa (calculated)    : " + temp.getGPA());
		p.println("Course info :");
		
		
		for (int j = 0; j < temp.courseList.size(); j++) {

			System.out.println(temp.courseList.get(j).getCourseNumber() + "	 "
					+ temp.courseList.get(j).getGradeRecieved());
			
			p.println(temp.courseList.get(j).getCourseNumber() + "	 "
					+ temp.courseList.get(j).getGradeRecieved());
		}
		System.out.println();
		p.println();
		
		
	}

}
