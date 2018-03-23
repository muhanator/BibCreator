import java.util.Scanner;  

import java.io.*;

/**
 * @author This class is the main part of the program. It contains all methods that will be used to convert 
 * the latex files to IEEE, ACM and NJ format. It also gets rid of any invalid files.
 * This program in general will loop through 10 different latex files and will first check whether or not
 * these files are valid. For the ones that are not valid, they will be deleted and those that are will have their
 * contents converted into 3 different files into ACM, IEEE and NJ format.
 */

public class BibCreatorDriver 
{
	/**
	 * This method processes the files to see if they are valid or not.
	 * Meaning that it checks for any sections being empty whether it be author, title, number etc.
	 * It will be used to make all the output files by using the methods in the ConversionMethods.java
	 * class
	 * 
	 * @param sc: is an array of scanner objects
	 * @param pw: is an array or PrintWriter Objects which will be used for ACM conversion
	 * @param acm: an array of files in ACM format
	 * @param pw2: an array of PrintWriter objects that will be used for IEEE conversion
	 * @param ieee: an array of files in IEEE format
	 * @param pw3: an array of PrintWriter Objects that will be used for NJ conversion
	 * @param nj: an array of files in NJ format
	 */
	public static void processFilesForValidation(Scanner[] sc,PrintWriter[] pw, File[] acm,PrintWriter[] pw2,
		File[] ieee, PrintWriter[] pw3, File[] nj)
	{
		String line;
		String line2;
		
	/**The number of times you throw the exception meaning the number of invalid files */
		int exceptionCalls=0;
	/**
	 * for loop that goes through every file to determine whether or not the file is valid
	 */
	for (int i=0; i<10; i++)
	{
	//number of articles initially set:
		int numArticles=0;
		int times=0;
	//while there are lines in the article:
		while (sc[i].hasNextLine())
		{
				try
				{
					line=sc[i].nextLine();			
					if (line.contains("@ARTICLE{"))
					{
					//increment the number of articles: 
						numArticles++;
					//while the line2 does not equal "}" keep looking through it:
						line2=sc[i].nextLine();
						pw[i].print("[" + numArticles + "]\t");
					//while line2 does not equal "}':
						while (!(line2.equals("}")))
						{
							//if the line contains an "=" then check for exception and what it contains 
							if (line2.contains("="))
							{	
							//split the line2 separated by an "="
								String splitLine2[]=line2.split("=");
				
							//if the right span after the "=" is "{}" then throw the exception:
								if (splitLine2[1].contains("{}") && times==0)
								{
									
								//getting rid of the brackets:
									String s=splitLine2[0].replace("{", "");
									s=splitLine2[0].replace("}", "");
								//delete the file, but first close the printwriter:
								//MUST DELETE THE FILE:
									pw[i].close();
									acm[i].delete();
									pw2[i].close();
									ieee[i].delete();
									pw3[i].close();
									nj[i].delete();
									times++;
									exceptionCalls++;
									
									throw new FileInvalidException(i+1,s);
								}
							//if it is not then convert using the acm conversion:
								ConversionMethods.acmConversion(splitLine2, pw[i]);
							//convert to ieeeConversion
								ConversionMethods.ieeeConversion(splitLine2, pw2[i]);
							//convert to nj:
								ConversionMethods.njConversion(splitLine2,pw3[i]);

							//if all goes well then line2 reads the next line:
								line2=sc[i].nextLine();
							}
							//if the line does not contain a "=" then skip to next iteration:
							else 
								line2=sc[i].nextLine();
						}
					}
				}
			/**
			 * Catches the exception if the file is invalid:
			 */
				catch(FileInvalidException e)
				{
					System.out.println(e.getMessage());
				}
		}
	/**
	 * Must close all PrintWriter and Scanner objects:
	 */
		pw[i].close();
		pw2[i].close();
		pw3[i].close();
		sc[i].close();
	}
	
/**Displaying how many files were valid and invalid*/
	if (exceptionCalls>0)
		System.out.println("A total of " + exceptionCalls + " files were invalid, and could not be processed"
				+ ". All other " + (10-exceptionCalls) + " \"valid\" files have been created.");
		System.out.println();
	}
	
	/**
	 * This is the main method in the class for BibCreator
	 */
	public static void main(String [] args)
	{
		System.out.println("Welcome to BibCreator!\n");
		
		
			PrintWriter pw[]=new PrintWriter[10];	//USED FOR ACM
			PrintWriter pw2[]=new PrintWriter[10];	//USED FOR IEEE
			PrintWriter pw3[]=new PrintWriter[10];	//USED FOR NJ
			
			File acm[]= new File[10];	//delete method is part of the File class
			File ieee[]= new File[10];
			File nj[]= new File[10];
		
	/**making an array of scanner objects */
			Scanner sc[]=new Scanner[10];
			String s;
			Scanner key= new Scanner(System.in);
			
		/**Trying to open the Latexfiles using scanner:*/
		for (int i=0; i<10; i++)
		{
			try
			{
			/**try to open the file:*/
				sc[i]= new Scanner(new FileInputStream("Latex" + (i+1)+ ".bib"));
				
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Could not open input file" + (i+1) + "for reading, Please check if file exists! ");
				System.out.println("Program will terminate after closing any opened files");
				for (int j=0; j<i; j++)
				{
					sc[j].close();
				}
				System.exit(0);
			}
		}	
	/**trying to copy to Output files using PrintWriter for ACM:*/
		for (int i=0; i<10; i++)
		{
			try
			{
				pw[i]= new PrintWriter(new FileOutputStream("ACM" + (i+1) +".json"));
				acm[i]= new File("ACM" + (i+1) +".json");
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File could not be opened/created");
				System.out.println("Program will terminate after deleting all created output files");
				pw[i].close();
				System.exit(0);
			}
		}
		
	/**trying to copy to Output files using PrintWriter for IEEE:*/
		for (int i=0; i<10; i++)
		{
			try
			{
			/**making the printWriter2 to write to the file for IEEE:*/
				pw2[i]= new PrintWriter(new FileOutputStream("IEEE" + (i+1) +".json"));
			/**making the file for the json for IEEE:*/
				ieee[i]= new File("IEEE" + (i+1) +".json");
			}
			/** Catching the exception in case the file is not found*/
			catch (FileNotFoundException e)
			{
				System.out.println("File could not be opened/created");
				System.out.println("Program will terminate after deleting all created output files");
				pw2[i].close();
				System.exit(0);
			}
		}
		
	/**trying to copy to Output files using PrintWriter for NJ: */
		for (int i=0; i<10; i++)
		{
			try
			{
			/**making the printWriter2 to write to the file for NJ:*/
				pw3[i]= new PrintWriter(new FileOutputStream("NJ" + (i+1) +".json"));
			/**making the file for the json for NJ:*/
				nj[i]= new File("NJ" + (i+1) +".json");
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File could not be opened/created");
				System.out.println("Program will terminate after deleting all created output files");
				pw3[i].close();
				System.exit(0);
			}
		}
		/**processing the files for validation:*/
			processFilesForValidation(sc, pw, acm, pw2, ieee, pw3, nj);
			
/**
 * This is the final part where, the program asks the user to enter the name of one of the created output files.
 * 
 */			BufferedReader bw=null;
 			PrintWriter pw4= null;
 			int tries=2;
 		/**Asking the user for the name of the file he wants to review*/
			try
			{
				System.out.println("Please enter the name of one of the files you want to review: ");
				String name= key.nextLine();
				
			/** making a BufferedReader object which the same name as the file that the user wants to review*/
				bw= new BufferedReader(new FileReader(name)); //if this file is not found then Exception will be thrown
				System.out.println();
			/**Calling the method that displays the contents the BufferedReader object*/
				displayFileContents(bw);
				
			}
			
			catch(FileNotFoundException e)
			{
			/**Displaying an error message if the file could not be found */
				System.out.println("Could not open input file, file does not exist; possibly it could not be created");
			/**Giving the user one more try if tey only attempted once*/
				if (tries>1)
				{
					System.out.println("However you will be allowed another chance to enter your file name");
					System.out.println("Please enter the name of one of the files you want to review: ");
					String name= key.nextLine();
					try
					{
					bw= new BufferedReader(new FileReader(name)); //if this file is not found then Exception will be thrown
					System.out.println();
					displayFileContents(bw);
					}
					catch (FileNotFoundException f)
					{
						System.out.println("Could not open input file again! Either file does not exist or could not be created");
						System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
						System.exit(0);
					}
					catch(IOException f)
					{
						System.out.println("Error: An error has occurred while reading from this file. ");
						System.out.println("The Program will terminate.");
						System.exit(0);	
					}
				}	
			}
			catch(IOException e)
			{
				System.out.println("Error: An error has occurred while reading from this file. ");
				System.out.println("The Program will terminate.");
				System.exit(0);		
			}
	}
	
/**
 * This method displays the content of a file using BufferedReader as a parameter
 * 
 * @param bw: Buffered reader object as parameter
 * @throws IOException: utilized when something goes wrong when trying to read.
 */
	public static void displayFileContents(BufferedReader bw) throws IOException
	{
		int x;
		
		x = bw.read();
		while(x != -1) 
		{
		/**You cast x so integers are not all that is shown*/
			System.out.print((char)x);		
			x = bw.read();		
		}
	/**must close the BufferedReader object*/
		bw.close();
	}

}
