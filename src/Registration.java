/*
 * This program firstly reads from the textfile (WarmUpData.txt) and save the students info into a HashTable (using hashmap interface)
 * 	Then it allows the user to choose from a menu to perform desired actions 
 *	The program output is displayed both in console and output textFile
 *	Each time user choose any action, the output will be saved in a separate output text file
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Registration {

	public static int ouputPageNumber = 1;

	public static void main(String[] args) {

		HashMap<Integer, Student> hTable = new HashMap<Integer, Student>();

		File file = new File("WarmUpdata.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
		
			String line = br.readLine();
			StringTokenizer tokenizer;

			while (line != null) {
				Student s = new Student();
				tokenizer = new StringTokenizer(line, ",");

				String token = tokenizer.nextToken();
				token = token.toUpperCase();
				s.setLastName(token);

				token = tokenizer.nextToken();
				token = token.toUpperCase();
				s.setFirstName(token);

				token = tokenizer.nextToken();
				s.setId(token);

				line = br.readLine();
				tokenizer = new StringTokenizer(line, ",");
				token = tokenizer.nextToken();

				double currentCredits = 0;
				double tempCredit = 0;
				double currentGpa = 0;

				while (token.compareTo("-999") != 0) {

					Course c = new Course();

					c.setCourseNumber(token);

					token = tokenizer.nextToken();

					tempCredit = Double.parseDouble(token);
					c.setNumberOfCredit(tempCredit);

					currentCredits += Double.parseDouble(token);

					token = tokenizer.nextToken();
					c.setGradeRecieved(token);
					c.setGradePoint(c.gradeCount(token));
					currentGpa += tempCredit * c.gradeCount(token);

					line = br.readLine();
					tokenizer = new StringTokenizer(line, ",");
					token = tokenizer.nextToken();

					tempCredit = 0;
					s.courseList.add(c);
				}
				currentGpa = Math.round((currentGpa / currentCredits) * 100.0) / 100.0; // converting
																						// it
																						// to
																						// 2
																						// decimal
																						// place

				s.setTotalCredits(currentCredits);
				s.setGPA(currentGpa);

				int last_Three_Digit_of_ID = Integer.parseInt(s.getId()) % 1000;

				//System.out.println(last_Three_Digit_of_ID);

				hTable.put(last_Three_Digit_of_ID, s);
				//System.out.println(hTable.size());

				line = br.readLine();
				line = br.readLine();

			} // done with reading from text file

			Scanner input = new Scanner(System.in);

			System.out.println("\nSelect from the following menu");
			System.out.println("\tPress 1 : To add a student. ");
			System.out.println("\tPress 2 : To delete a student.");
			System.out.println("\tPress 3 : To search for a specific students info.");
			System.out.println("\tPress 4 : Show all the students info.");
			System.out.println("\tPress 5 : To add a course from a specific student's courselist.");
			System.out.println("\tPress 6 : To delete a course from a specific student's courselist.");

			System.out.println("\tPress 9 : To exit the program.");
			System.out.println();
			System.out.print("Enter your response: ");

			int answer = input.nextInt();
			Scanner input2 = new Scanner(System.in);
			String str;

			while (answer != 9) {
				Student s1 = new Student();
				HashOperation hOperation = new HashOperation();

				String newFile = "output" + ouputPageNumber + ".txt";
				File file2 = new File(newFile);
				PrintWriter p = new PrintWriter(new FileWriter(file2));

				if (answer == 1) {

					int count1;

					System.out.print("What is the last name  :");
					str = input2.next();
					str = str.toUpperCase();
					s1.setLastName(str);

					System.out.print("What is the first name :");
					str = input2.next();
					str = str.toUpperCase();
					s1.setFirstName(str);

					// assigning random id ( 6 digit)
					int min = 100000;
					int max = 999999;
					Random rand = new Random();
					int num = rand.nextInt((max - min) + 1) + min;

					String generatedID = Integer.toString(num);

					s1.setId(generatedID);
					System.out.print("How many course did this student take?");
					count1 = Integer.parseInt(input2.next());

					// adding coursess for this student
					for (int k = 0; k < count1; k++) {
						Course c1 = new Course();

						System.out.println("Enter the course id :");
						str = input2.next();
						c1.setCourseNumber(str);

						System.out.println("What is the number of credits of this course? :");
						str = input2.next();

						c1.setNumberOfCredit(Double.parseDouble(str));

						System.out.println("What is the letter grade student recieved? :");
						str = input2.next();
						c1.setGradeRecieved(str);

						c1.setGradePoint(c1.gradeCount(str));

						s1.courseList.add(c1);

					}

					double tempCredits = 0;
					double tempGpa = 0;

					for (int i = 0; i < s1.courseList.size(); i++) {
						tempCredits += s1.courseList.get(i).getNumberOfCredits();
						tempGpa += s1.courseList.get(i).getGradePoint() * s1.courseList.get(i).getNumberOfCredits();

					}
					// currentGpa=Math.round((currentGpa/currentCredits)*100.0)/100.0;
					tempGpa = Math.round((tempGpa / tempCredits) * 100.0) / 100.0;
					s1.setGPA(tempGpa);
					s1.setTotalCredits(tempCredits);

					int key = Integer.parseInt(s1.getId()) % 1000;
					hTable.put(key, s1);
					hOperation.printAll(hTable, "The student has been added. Here's the updated registration database",p);

					ouputPageNumber++;
					System.out.println();
					p.close();
				}

				// deleting a student from the database
				else if (answer == 2) {
					System.out.print("Please enter the last 3 didgit of the student's id :");
					str = input2.next();
					int key = Integer.parseInt(str);
					
					boolean flag = false;

					for (Integer key2 : hTable.keySet()) {

						if (key2 == key)
							flag = true;
					}

					if (flag == false) {
						System.out.println("Sorry, student isn't in the database.");
					} 
					else {

						hTable.remove(key);
						hOperation.printAll(hTable, "The student has been deleted. Here's the updated registration database",p);
						ouputPageNumber++;

						p.close();
					}
				}

				else if (answer == 3) {
					System.out.print("Please enter the last 3 digit of the student :");
					str = input2.next();
					int key = Integer.parseInt(str);
						
					hOperation.printSpecific(hTable, key,p);
					ouputPageNumber++;

					p.close();
				}

				// printing the whole database
				else if (answer == 4) {

					// tOperation.printAll(tMap, " Here's the registration
					// database");
					hOperation.printAll(hTable, " Here's the registration database",p);
					ouputPageNumber++;
					p.close();
				}

				else if (answer == 5) {
					System.out.print("Please enter the last 3 digit of the student's id :");
					str = input2.next();
					int key = Integer.parseInt(str);
					//str = str.toUpperCase();
					boolean flag = false;

					for (Integer key2 : hTable.keySet()) {

						if (key2 == key)
							flag = true;
					}

					if (flag == false) {
						System.out.println("Sorry, student isn't in the database.");
					} 

					else {
						//Student tempStudent = tMap.get(str);
						Student tempStudent = hTable.get(key);
						// System.out.println(tempStudent.getFirstName());

						Course c1 = new Course();

						System.out.println("Enter the course id :");
						str = input2.next();
						c1.setCourseNumber(str);

						// System.out.println(tempStudent.getFirstName());

						System.out.println("What is the number of credits of this course? :");
						str = input2.next();

						c1.setNumberOfCredit(Double.parseDouble(str));

						System.out.println("What is the letter grade student recieved? :");
						str = input2.next();
						c1.setGradeRecieved(str);
						c1.setGradePoint(c1.gradeCount(str));

						tempStudent.courseList.add(c1);

						double tempCredits = 0;
						double tempGpa = 0;

						for (int j = 0; j < tempStudent.courseList.size(); j++) {
							tempCredits += tempStudent.courseList.get(j).getNumberOfCredits();
							tempGpa += (tempStudent.courseList.get(j).getGradePoint()
									* tempStudent.courseList.get(j).getNumberOfCredits());
						}

						tempGpa = Math.round((tempGpa / tempCredits) * 100.0) / 100.0;
						tempStudent.setGPA(tempGpa);
						tempStudent.setTotalCredits(tempGpa);

						//tOperation.printAll(tMap,"The course has been added. Here's the updated registration database");
						
						hOperation.printAll(hTable, "The course has been added. Here's the updated registration database",p);
						ouputPageNumber++;
						System.out.println();
						 p.close();
					}
				}
				else if (answer == 6) {
					System.out.print("Please enter the last 3 digit of the student's id :");
					str = input2.next();
					int key = Integer.parseInt(str);
					//str = str.toUpperCase();
				
					boolean flag = false;

					for (Integer key2 : hTable.keySet()) {

						if (key2 == key)
							flag = true;
					}

					if (flag == false) {
						System.out.println("Sorry, student isn't in the database.");
					} 
					
					else {
						Student tempStudent = hTable.get(key);
						Course c1 = new Course();
						
						int check = 0;

						System.out.println("Enter the course id that you want to delete :");
						str = input2.next();

						for (int i = 0; i < tempStudent.courseList.size(); i++) {
							if (tempStudent.courseList.get(i).getCourseNumber().compareTo(str) == 0){
								tempStudent.courseList.remove(i);
								check =1;
							}
						}
						
						if ( check == 0){
							System.out.println("Student didn't take this course !!");
						}
						else{
							double tempCredits = 0;
							double tempGpa = 0;

							for (int j = 0; j < tempStudent.courseList.size(); j++) {
								tempCredits += tempStudent.courseList.get(j).getNumberOfCredits();
								tempGpa += (tempStudent.courseList.get(j).getGradePoint()
										* tempStudent.courseList.get(j).getNumberOfCredits());
							}

							tempGpa = Math.round((tempGpa / tempCredits) * 100.0) / 100.0;
							tempStudent.setGPA(tempGpa);
							tempStudent.setTotalCredits(tempGpa);
							//System.out.println("The course has been deleted. Here's the updated registration database");
							
							//tOperation.printAll(tMap, p , "The course has been deleted. Here's the updated registration database");
							hOperation.printAll(hTable, "The course has been deleted. Here's the updated registration database",p);

							ouputPageNumber++;
							System.out.println();
							p.close();
						}
					}
				}

				else {
					System.out.println("Wrong input !");
				}
		
				System.out.println("\nSelect from the following menu");
				System.out.println("\tPress 1 : To add a student. ");
				System.out.println("\tPress 2 : To delete a student.");
				System.out.println("\tPress 3 : To search for a specific students info.");
				System.out.println("\tPress 4 : Show all the students info.");
				System.out.println("\tPress 5 : To add a course from a specific student's courselist.");
				System.out.println("\tPress 6 : To delete a course from a specific student's courselist.");

				System.out.println("\tPress 9 : To exit the program.");
				System.out.println();
				System.out.print("Enter your response: ");

				answer = input.nextInt();
			}
		}

		catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	} // end of main

}
