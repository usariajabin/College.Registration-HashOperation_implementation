import java.util.ArrayList;
import java.util.Random;

public class Student{
    
    private String FirstName;
    private String LastName;
    private String ID=null;
    private double TotalCredits=0;
    private double GPA=0;
    
    Random rand= new Random();
    int num = rand.nextInt(999999)+ 100000;
	String generatedID= Integer.toString(num);
    
    //list of courses taken
    ArrayList <Course> courseList= new ArrayList<Course>();
    
   Student(){

   }

    public String getFirstName() {
        return FirstName;
    }
    
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName= lastName;
    }

   

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double gpa) {
        GPA = gpa;
    }
    
    public void setId(String id ){
       ID =id;
    }
    
    public String getId(){
       return ID;
    }
    
    public double getTotalCredits() {
		return TotalCredits;
	}

	public void setTotalCredits(double totalCredits) {
		TotalCredits = totalCredits;
	}
    
    public void setGeneratedId(String id ){
        generatedID =id;
     }
     
     public String getgeneratedId(){
        return generatedID;
     }
   

	public boolean ComparelastName(ArrayList <Student> a,String str) {
        for(int i =0;i<a.size(); i++){
        	if ( ((a.get(i)).getLastName()).compareTo(str) ==0)
        		return true;
        }
        	
    	return false;
    }
        
        
    public boolean equalsID(ArrayList <Student> a, String str) {
    	for(int i =0;i<a.size(); i++){
        	if ( ((a.get(i)).getId()).compareTo(str) ==0  || (a.get(i)).getgeneratedId().compareTo(str)==0)
        		return true;
        }
    	return false;
    }
    
    public String ToString(){
        return LastName+ "," + FirstName+ ","+ ID + ","+ TotalCredits+ "," + GPA; 
    }
}
        

