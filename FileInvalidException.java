/**
 * 
 * This is the FileInvalidException class which is an Exception when a file is invalid.
 *
 */

/**
 * This class is a class which Extends from the Exception class and is thrown whenever
 * a file is invalid. It contains 2 constructors which are both parametrized.
 *
 */
public class FileInvalidException extends Exception 
{
//default constructor:
	/**
	 * Makes new Invalid Exception to be thrown when a file is invalid
	 * @param i: define which latex file number is invalid
	 * @param s: define which field from the latex file is invalid
	 */
	public FileInvalidException(int i, String s)
	{
		super("Error: Detected empty field" + "\n======================\n\n" + "problem detected with input file Latex " + i + ".bib"
				+ "\nFile is invalid: Field \"" + s +"\""  + " is empty. Processing stopped at this point. Other empty fields may be "
						+ "present as well.\n");
	}
	/**
	 * Makes a new FileInvalidException 
	 * @param s: String parameter that displays the exception in words
	 */
//parametrized constrcutor:
	public FileInvalidException(String s)
	{
		super(s);
	}
	
}
