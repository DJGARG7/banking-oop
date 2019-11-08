import java.io.*;
public class banking extends User
{
	void mainmenu1()
	{
		do
		{
		System.out.println("WELCOME TO BANK");
		System.out.println("1.DEPOSIT");
		System.out.println("2.WITHDRAW");
		System.out.println("3.TRANSFER");
		System.out.println("4.CHANGE YOUR PASSWORD");
		System.out.println("5. DISPLAY DETAILS AND BALANCE");
		System.out.println("6.Logout");
		int ch = Integer.parseInt(scan.readLine());
		switch(ch)
		{
			case 1:
			deposit();
			break;
			case 2:
			withdraw();
			break;
			case 3:
			transfer();
			break;
			case 4:
			change();
			break;
			case 5:
			display();
			break;
			case 6:
			System.out.println("LOGGING OUT.......");
			break;
			default:
			System.out.println("wrong choice ...enter again");

		}
		}while(ch!=6);
	}
	void deposit(){}
	void withdraw(){}
	void transfer(){}
	void display(){}
}
