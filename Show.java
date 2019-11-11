import java.io.*;
public class Show{
public static void clear()
{
    try
	{	
		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }
    catch(Exception E)
		{
			System.out.println(E);
		}
    System.out.println("");
		   System.out.println(" 	    /$$$$$$        /$$   /$$       /$$$$$$$            ");
		   System.out.println("	   /$$__  $$      | $$$ | $$      | $$__  $$  /$$/$$ ");
		   System.out.println("	  | $$  \\__/      | $$$$| $$      | $$  \\ $$ |  $$$/ ");
		   System.out.println("	  | $$ /$$$$      | $$ $$ $$      | $$$$$$$  /$$$$$$$");
		   System.out.println("	  | $$|_  $$      | $$  $$$$      | $$__  $$|__ $$$_/");
		   System.out.println("	  | $$  \\ $$      | $$\\  $$$      | $$  \\ $$  /$$ $$ ");
		   System.out.println("	  |  $$$$$$/      | $$ \\  $$      | $$$$$$$/ |__/__/ ");
		   System.out.println("	   \\______/       |__/  \\__/      |_______/          ");
			System.out.println("___________GOLIATH_________NATIONAL________BANK_____________");
	
    }
}