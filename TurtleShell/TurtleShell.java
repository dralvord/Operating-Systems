/*
 * Author: Dillon Alvord
 * Date: 2/8/16
 * File: TurtleShell.java
 */
import java.util.*;
import java.io.*;

public class TurtleShell {
	//Global variables
	Scanner keys = new Scanner(System.in);
	File folder = new File(".");
	String currentDirectory = ".";
	
	//main
	public static void main(String[] args) throws FileNotFoundException, Exception
	{
		TurtleShell turtleShell = new TurtleShell();
		turtleShell.go();
	}
	
	//Function: Breaks main out of the static context.
	//Parameters: None
	//Returns: None
	public void go() throws FileNotFoundException, Exception
	{
		folder = new File(folder.getCanonicalPath());
		currentDirectory = folder.getCanonicalPath();
		currentDirectory = folder.getAbsolutePath();
		String instruction = "";
		System.out.println("Welcome to Turtle Shell!");
		
		
		while(!instruction.equalsIgnoreCase("e") && !instruction.equalsIgnoreCase("exit"))
		{	//Loop where shell occurs.
			String directory = "";
			
			//Takes in user input and splits it into instruction and directory tokens.
			System.out.print("turtleshell>");
			String line = keys.nextLine();
			
			String[] tokens = line.split(" ");
			instruction = tokens[0];
			for(int i = 1; i < tokens.length;i++)
			{
				if(i > 1)
				{
					directory = directory + " ";
				}
				directory = directory + tokens[i];
			}
			
			
			if(instruction.equalsIgnoreCase("l") || instruction.equalsIgnoreCase("list"))
			{
				printDirectory();
			}
			else if(instruction.equalsIgnoreCase("d") || instruction.equalsIgnoreCase("down"))
			{
				changeDirectory(directory);
			}
			else if(instruction.equalsIgnoreCase("u") || instruction.equalsIgnoreCase("up"))
			{
				goUpDirectory();
			}
			else if(instruction.equalsIgnoreCase("w") || instruction.equalsIgnoreCase("wai"))
			{
				System.out.println(folder.getCanonicalPath());
			}
			else if(instruction.equalsIgnoreCase("e") || instruction.equalsIgnoreCase("exit"))
			{	//Here so pressing e or exit does not show help commands.
			}
			else
			{
				printHelpCommands();
			}
		}
		System.out.println("Bye");
	}
	
	//Function: Prints the files and directories found in the current directory.
	//Parameters: None
	//Returns: None
	public void printDirectory()
	{	
		
		File[] files = folder.listFiles();

		for (int i = 0; i < files.length; i++) 
		{
			if(files[i].isFile()) 
			{
		        System.out.println("(f) " + files[i].getName());
		    } 
			else if (files[i].isDirectory()) 
			{
		        System.out.println("(d) " + files[i].getName());
		    }
		}
	}
	
	//Purpose: Changes the directory to the one specified by the user.
	//Parameters: The directory to be moved to.
	//Returns: None
	public void changeDirectory(String directory) throws Exception
	{
		folder = new File(currentDirectory + "/" +  directory);
		if(folder.exists())
		{
			System.out.println(folder.getCanonicalPath());
			currentDirectory = folder.getCanonicalPath();
		}
		else
		{
			System.out.println(directory + " is not a directory.");
			folder = new File(currentDirectory);
		}
	}
	
	//Function: Moves to the parent directory from the current directory.
	//Parameters: None
	//Returns: None
	public void goUpDirectory() throws Exception
	{
		folder = folder.getParentFile();
		if(folder.exists())
		{
			System.out.println(folder.getCanonicalPath());
			currentDirectory = folder.getCanonicalPath();
		}
		else
		{
			System.out.println("Cannot go any further up.");
			folder = new File(currentDirectory);
		}
	}
	
	//Function: Prints the help commands
	//Parameters: None
	//Returns: None
	public void printHelpCommands()
	{
		 System.out.println("(l)ist: lists contents of current directory");
		 System.out.println("(d)own [dir]: moves into the specified child directory");
		 System.out.println("(u)p: moves to the parent directory");
		 System.out.println("(w)ai: prints the current directory");
		 System.out.println("(e)xit: exits the shell");
		 System.out.println("(h)elp: prints a list of the supported commands");
	}

}
