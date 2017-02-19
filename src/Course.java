 import java.util.ArrayList;

public class Course {
    
    private String CourseNumber;
    
    private double NumberOfCredit; // NumberOfCredit for a specific course 
    private String GradeRecieved;
    private double GradePoint;
    
    
    public String getCourseNumber() {
        return CourseNumber;
    }
    public void setCourseNumber(String courseNumber) {
        CourseNumber = courseNumber;
    }
    
    
    public double getNumberOfCredits() {
        return NumberOfCredit;
    }
    public void setNumberOfCredit(double numberOfCredits) {
        NumberOfCredit = numberOfCredits;
    }
    
    
    public String getGradeRecieved() {
        return GradeRecieved;
    }
    public void setGradeRecieved(String gradeRecieved) {
        GradeRecieved = gradeRecieved;
    } 
    
    
    public double getGradePoint() {
		return GradePoint;
	}
	public void setGradePoint(double gradePoint) {
		GradePoint = gradePoint;
	}
    
    public boolean equalCourseId(ArrayList<String> a, String cId){
    	for (int i =0; i<a.size();i++){
    		if (a.get(i).compareTo(cId)==0)
    			return true;
    	}
		return false;
    	
    }
    
    public String toString(){
    	return CourseNumber+", "+ NumberOfCredit+", "+GradeRecieved;
    }
	
    
    public double gradeCount(String x){
        double grade=0.0;
        
        if(x.compareToIgnoreCase("A")==0 || x.compareToIgnoreCase("A+")==0 ){
           grade=4;
           return grade;
        }
           
        else if (x.compareToIgnoreCase("A-")==0 ){
           grade=3.7;
           return grade;
        }
        else if(x.compareToIgnoreCase("B+")==0){
           grade=3.3;
           return grade;
        }
        else if(x.compareToIgnoreCase("B")==0){
           grade=3.0;
           return grade;
        }
        else if (x.compareToIgnoreCase("B-")==0){
           grade=2.7;
           return grade;
        }
        else if (x.compareToIgnoreCase("C+")==0){
            grade=2.3;
            return grade;
         }
        else if (x.compareToIgnoreCase("C")==0){
           grade=2.0;
           return grade;
        }
        else if (x.compareToIgnoreCase("C-")==0){
           grade=1.7;
           return grade;
        }
        else if(x.compareToIgnoreCase("D")==0){
        	 grade=1.0;
        	 return grade;
        }
        else
           return grade;
     }
    

 }


