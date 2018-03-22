import java.io.*;

/**
 * 
 * @author This class contains all the methods that are used to convert the files into ACM,
 * IEEE and NJ format.
 *
 */
public class ConversionMethods 
{

	/**
	 * Static Variables used in the conversion methods:
	 */
	public static String author="", author2="", author3="";
	public static String year="", year2="", year3="";
	public static String title="", title2="", title3="";
	public static String journal="", journal2="", journal3="";
	public static String volume="", volume2="", volume3="";
	public static String number="", number2="", number3="";
	public static String pages="", pages2="", pages3="";
	public static String doi="", doi2="", doi3="";
	
	public static String month2="", month3="";
	
	/**
	 * This method will be used to convert the contents within a file to ACM format.
	 * @param splitLine2: array of Strings which at index 0 contains general information, and at index 1 defines the information explicitly
	 * @param pw: PrintWriter object that will be used to write a desired file 
	 */
	
	public static void acmConversion(String splitLine2[], PrintWriter pw)
	{
		
/**
 * Now program will into many if and else statements to match the info to the current line we are at
 * This first one checks to see if the information in splitLine2 is about the author
 */
		int numAuthors=1;
	//IF THE LEFT SIDE OF LINE IS EQUAL TO AUTHOR:
		if (splitLine2[0].contains("author") && (!splitLine2[1].contains("{}")))
		{
		//splitting the authors from the left and right side:
			String splitAuthor[]= splitLine2[1].split(" ");
			for (int i=0; i<splitAuthor.length; i++)
			{
			//if the current index is not equal to "and", write the word to the file:
				if (splitAuthor[i].contains("and"))
					numAuthors++;
				
				else if (!(splitAuthor[i].equals("and")) && numAuthors<2)
				{
					if (splitAuthor[i].contains("{") && splitAuthor[i].contains("."))
					{
						author+=splitAuthor[i].replace("{", "");
						author+=" ";
					}
					else if (splitAuthor[i].contains("{"))
					{
//						pw.print(splitAuthor[i].replace("{", "") + " ");
						author+=splitAuthor[i].replace("{", "");
					}
					
					else if ( splitAuthor[i].contains("}"))
					{
//						pw.print(splitAuthor[i].replace('}', ' ') + " ");
						author+=splitAuthor[i].replace("} ", "");
					}
					else if (splitAuthor[i].contains("."))
					{
						author+=splitAuthor[i] + " ";
					}
					else
						author+=splitAuthor[i];
				}
				
				if (numAuthors>1)
				{
					author+= " et al.";
					break;
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the year
		 */
		else if (splitLine2[0].contains("year"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
			//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
//					pw.print(splitLine2[1].charAt(i));
					year+=splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(".");
					year+="";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the title
		 */
		else if (splitLine2[0].contains("title"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==','))  
				{
//					pw.print(splitLine2[1].charAt(i));
					title+=splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(".");
					title+= ".";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the journal
		 */
		else if (splitLine2[0].contains("journal"))
		{
			pw.print(" ");
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==','))  
				{
//					pw.print(splitLine2[1].charAt(i));
					journal+= splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(".");
					journal+= ".";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the volume
		 */
		else if (splitLine2[0].contains("volume"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==','))  
				{
//					pw.print(splitLine2[1].charAt(i));
					volume+= splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(",");
					volume+= ",";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the number
		 */
		else if (splitLine2[0].contains("number"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==','))  
				{
//					pw.print(splitLine2[1].charAt(i));
					number+= splitLine2[1].charAt(i);
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the pages
		 */
		else if (splitLine2[0].contains("pages"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',')) 
				{
//					pw.print(splitLine2[1].charAt(i));
					pages+= splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(".");
					pages+= ".";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the doi
		 */
		else if (splitLine2[0].contains("doi"))
		{
//			pw.print("DOI:https://doi.org/");
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==','))  
				{
//					pw.print(splitLine2[1].charAt(i));
					doi+=splitLine2[1].charAt(i);
				}
			//if the character is a comma then simply replace it with a period:
				else if (splitLine2[1].charAt(i)==',')
				{
//					pw.print(".");
					doi+=".";
				}
			}
		}	
		
	/**once all the fields are completed, Then you can add them together in the proper order*/
		if ((!year.equals("")) && (!author.equals("")) && (!title.equals(""))&& (!volume.equals(""))&&(!journal.equals(""))
				&&(!number.equals("")) && (!pages.equals("")) && (!doi.equals("")))
		{
			pw.println(ConversionMethods.author + " " + ConversionMethods.year+ ". " + ConversionMethods.title
					+ ConversionMethods.journal + ConversionMethods.volume + ConversionMethods.number 
					+ "(" +ConversionMethods.year + "), "
					+ ConversionMethods.pages + "DOI:https://doi.org/"+ ConversionMethods.doi);
			
		/**afterwards you have to wipe them clean again for the next time they are used:*/
			ConversionMethods.author=""; ConversionMethods.year=""; ConversionMethods.title="";
			ConversionMethods.journal=""; ConversionMethods.volume=""; ConversionMethods.number= "";
			ConversionMethods.pages=""; ConversionMethods.doi="";
			pw.println();
		}
	}
	
	/**
	 * This method is used to convert the contents in a file into IEEE format.
	 * @param splitLine2: array of Strings which at index 0 contains general information, and at index 1 defines the information explicitly
	 * @param pw2: PrintWriter objects that will be used to write to a specific file
	 */
	public static void ieeeConversion(String splitLine2[], PrintWriter pw2)
	{
		/**Now we go into many if else statements to match what the String in splittLIne2 is talking about
		 * This first one checks to see if the information in splitLine2 is about the author
		 */
		if (splitLine2[0].contains("author"))
		{
			String splitAuthor2[]= splitLine2[1].split(" ");
			for (int i=0; i<splitAuthor2.length; i++)
			{
			//when you find the "}" it means you are at the end of author list and add a period:
				if (splitAuthor2[i].contains("},"))
				{
					author2+=splitAuthor2[i].replace("},", ".");
				}
				else if (splitAuthor2[i].contains("{")) 
				{
					author2+=splitAuthor2[i].replace("{", "") + " ";
				}
				else if (splitAuthor2[i].contains("and"))
				{
					author2+=splitAuthor2[i].replace("and", ",") + " ";
				}
				else if (splitAuthor2[i].contains(".") && splitAuthor2[i+1].contains("and"))
				{
					author2+=splitAuthor2[i];
				}
				else if (splitAuthor2[i+1].contains("."))
				{
					author2+=splitAuthor2[i] + " ";
				}
				else if (splitAuthor2[i].contains(".") && splitAuthor2[i+1].contains("and"))
				{
					author2+=splitAuthor2[i];
				}
				else if (!splitAuthor2[i].contains(".") && splitAuthor2[i-1].length()>3)
				{
					author2+= " " + splitAuthor2[i];
				}
				else if (!splitAuthor2[i].contains("."))
				{
					author2+= splitAuthor2[i];
				}
				else
					author2+= splitAuthor2[i] + " ";
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the title
		 */
		else if (splitLine2[0].contains("title"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
			//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					title2+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
				{
					if (splitLine2[1].charAt(i-1)==',')
						title2+= "";
					else
						title2+= splitLine2[1].charAt(i);
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the journal
		 */
		else if (splitLine2[0].contains("journal"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)=='{') 
					journal2+="";
				else if (splitLine2[1].charAt(i)=='}')
					journal2+="";
				else
					journal2+= splitLine2[1].charAt(i);
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the volume
		 */
		else if (splitLine2[0].contains("volume"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)=='{') 
					volume2+="";
				else if (splitLine2[1].charAt(i)=='}')
					volume2+="";
				else
					volume2+= splitLine2[1].charAt(i);
			}
		}

		/**
		 * This first one checks to see if the information in splitLine2 is about the number
		 */
		else if (splitLine2[0].contains("number"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)=='{') 
					number2+="";
				else if (splitLine2[1].charAt(i)=='}')
					number2+="";
				else
					number2+= splitLine2[1].charAt(i);
			}
		} 
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the pages
		 */
		else if (splitLine2[0].contains("pages"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)=='{') 
					pages2+="";
				else if (splitLine2[1].charAt(i)=='}')
					pages2+="";
				else
					pages2+= splitLine2[1].charAt(i);
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the month
		 */
		else if (splitLine2[0].contains("month"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)=='{') 
					month2+="";
				else if (splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',')
					month2+="";
				else
					month2+= splitLine2[1].charAt(i);
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the author
		 */
		else if (splitLine2[0].contains("year"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
				if (splitLine2[1].charAt(i)==',') 
					year2+=".";
				else if (splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)==' ') 
					year2+="";
				else
					year2+= splitLine2[1].charAt(i);
			}
		}
		
		/**Once all the static variables used to convert to IEEE format have been used, we format it in the proper way here*/
		if ((!author2.equals("")) && (!title2.equals("")) && (!journal2.equals("")) && (!volume2.equals(""))
			&& (!number2.equals("")) && (!pages2.equals("")) && (!month2.equals("")) && (!year2.equals("")))
		{
			pw2.println(author2 + " \"" + title2 + "\", " + journal2 + "vol. " + volume2 + "no. " + number2
					+ "p. " + pages2 + month2 + " " + year2);
		//then we reset the static variables:
			author2=""; title2=""; journal2=""; volume2=""; number2=""; pages2=""; month2=""; year2="";
			pw2.println();
		}
	}
	
	/**
	 * This method is used to convert the contents in a file to NJ format 
	 * @param splitLine2: array of Strings which at index 0 contains general information, and at index 1 defines the information explicitly
	 * @param pw3: PrintWriter object that is used to write to a specific file 
	 */
	public static void njConversion(String splitLine2[], PrintWriter pw3)
	{
		/**
		 * This first one checks to see if the information in splitLine2 is about the author
		 */
		if (splitLine2[0].contains("author"))
		{
		//splitting the authors from the left and right side:
			String splitAuthor[]= splitLine2[1].split(" ");
			for (int i=0; i<splitAuthor.length; i++)
			{	
				if (!(splitAuthor[i].equals("and")))
				{
					if (splitAuthor[i].contains("{"))
					{
						author3+=splitAuthor[i].replace("{", "") + " ";
					}
					else if (splitAuthor[i].contains("}"))
					{
//						pw.print(splitAuthor[i].replace('}', ' ') + " ");
						author3+=splitAuthor[i].replace("}", "") + " ";
					}
					else if (splitAuthor[i].contains(".") && splitAuthor[i+1].contains("and"))
					{
						author3+=splitAuthor[i];
					}
					else if (splitAuthor[i].contains("."))
					{
						author3+=splitAuthor[i].replace(".", ". ");
					}
					else if (splitAuthor[i].contains(", "))
						author3+=splitAuthor[i].replace(',', '.');
					else if (splitAuthor[i+1].contains("."))
					{
						author3+=splitAuthor[i] + " ";
					}
					else
						author3+=splitAuthor[i];
				}
				else if (splitAuthor[i].equals("and"))
				{
					author3+=" & ";
				}
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the title
		 */
		else if (splitLine2[0].contains("title"))
		{
			
			for (int i=0; i<splitLine2[1].length(); i++)
			{
		//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					title3+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
					title3+=" ";
				else if (splitLine2[1].charAt(i)=='}')
					title3+=".";
			}	
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the journal
		 */
		else if (splitLine2[0].contains("journal"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
		//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					journal3+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
					journal3+=" ";
				else if (splitLine2[1].charAt(i)=='}')
					journal3+=".";
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the volume
		 */
		else if (splitLine2[0].contains("volume"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
		//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					volume3+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
					volume3+=" ";
				else if (splitLine2[1].charAt(i)=='}')
					volume3+=",";
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the pages
		 */
		else if (splitLine2[0].contains("pages"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
		//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					pages3+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
					pages3+="";
				else if (splitLine2[1].charAt(i)=='}')
					pages3+="";
			}
		}
		
		/**
		 * This first one checks to see if the information in splitLine2 is about the year
		 */
		else if (splitLine2[0].contains("year"))
		{
			for (int i=0; i<splitLine2[1].length(); i++)
			{
		//if the character is not a { or a } or a , then write it to the file:
				if (!(splitLine2[1].charAt(i)=='{' || splitLine2[1].charAt(i)=='}' || splitLine2[1].charAt(i)==',' || splitLine2[1].charAt(i)==' '))  
				{
					year3+=splitLine2[1].charAt(i);
				}
				else if (splitLine2[1].charAt(i)==' ')
					year3+="";
				else if (splitLine2[1].charAt(i)=='}')
					year3+=")";
				else if (splitLine2[1].charAt(i)=='{') 
					year3+="(";
			}
		}
		
		/**Once all the variables are used, we format it into the file in the proper order*/
		if ((!author3.equals("")) && (!title3.equals("")) && (!journal3.equals("")) && (!volume3.equals(""))
				&& (!pages3.equals("")) && (!year3.equals("")))
			{
				author3= author3.replace(',', '.');
			//printing it all to the files:
				pw3.println(author3 + title3 + journal3 + volume3 + pages3 + year3 +".");
				
				author3=""; title3=""; journal3=""; volume3=""; number3=""; pages3=""; month3=""; year3="";
				pw3.println();
			}
		
	}
}
