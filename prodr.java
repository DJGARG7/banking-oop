import java.io.*;
import java.util.Scanner;
class prodr
{
    public static void main(String args[])throws IOException
    {
        //Scanner scan1 = new Scanner(System.in);
        BufferedReader scan1 = new BufferedReader(new InputStreamReader(System.in));
		int chza;
		banking ob1 = new banking();
		Show.clear();
		do
		{   
		   	System.out.println("		1: NEW USER REGISTRATION");
     		System.out.println("		2: LOGIN");
	       	System.out.println("		3: CHANGE PASSWORD");
    	   	System.out.println("		4: EXIT");
       		System.out.println("		enter your choice");
			   chza = Integer.parseInt(scan1.readLine()); 
			   
       		switch(chza)
        	{
        		case 1:
        		ob1.signUp();
        		break;
        		case 2:
        		if(ob1.login())
					ob1.mainmenu1();
        		break;
        		case 3:
        		if(ob1.login())
        			ob1.change();
        		break;
        		case 4:
        		System.out.println("EXITING...");
        		break;
        		default :
        		System.out.println("wrong choice ...enter again");
        	}
        }while(chza != 4);      
    }
}
		 
