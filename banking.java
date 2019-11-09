import java.io.*;
class Transaction
{
	int userid,bal,tr1;
	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	Transaction(int userid)
	{
		this.userid = userid;
		bal = searchId(userid);
	}
	int getBal()
	{
		return bal;
	}
	int searchId(int userid)
	{
		int bal = 0;
		//if account found
		//scanner file
		return bal;
		//else
		//return -1;
	}
	void updatefile(int userid,int bal)
	{
		//append at last userid and bal
		//file writer
	}
	void deposit()
	{
		System.out.println("enter amount to be deposited(max 50000 ruppees at a time)");
		tr1 = Integer.parseInt(scan.readLine());
		if(tr1>50000)
			System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED");
		else
		{
			bal +=tr1; 	
			System.out.println("TRANSACTION SUCCESSFUL");
		}
	}
	void withdraw()
	{
		System.out.println("enter amount to be withdrawn(max 20000 ruppees at a time)");
		tr1 = Integer.parseInt(scan.readLine());
		if(tr1>20000)
			System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED\nCURRENT BALANCE:"+bal);
		else if(bal<tr1)
			System.out.println("NOT ENOUGH FUNDS....TRANSACTION FAILED\nCURRENT BALANCE:"+bal);
		else
		{
			bal -=tr1;
			System.out.println("TRANSACTION SUCCESSFUL");
		}
	}
	void transfer()
	{
		int bal=0;
		System.out.println("ENTER USERID OF BENEFICIARY");
		int userid = Integer.parseInt(scan.readLine());
		if((bal=searchId(userid))==-1)
			System.out.println("USERID NOT FOUND....TRANSACTION FAILED");
		else
		{
			System.out.println("enter the amount to be transfered(max 10000 ruppees allowed)");
			tr1 = Integer.parseInt(scan.readLine());
			if(tr1>10000)
				System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED\nCURRENT BALANCE:"+this.bal);
			else if(this.bal<tr1)
				System.out.println("NOT ENOUGH FUNDS....TRANSACTION FAILED\nCURRENT BALANCE:"+this.bal);
			else
			{
				this.bal -=tr1;
				bal += tr1;
				System.out.println("TRANSACTION SUCCESSFUL");
				updatefile(userid,bal);
			}
		}
	}
}
public class Banking extends User
{
	void display(int bal)
	{
		System.out.println("USER ID: "+getUserid());
		System.out.println("ACCOUNT HOLDER NAME: "+getName());
		System.out.println("PHONE NUMBER: "+ getPh_no());
		System.out.println("AVAILABLE BALANCE: "+ bal);
	}
	void mainmenu1()
	{
		Transaction tr = new Transaction(getUserid());
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
			tr.deposit();
			break;
			case 2:
			tr.withdraw();
			break;
			case 3:
			tr.transfer();
			break;
			case 4:
			tr.change();
			break;
			case 5:
			display(tr.getBal());
			break;
			case 6:
			System.out.println("LOGGING OUT.......");
			break;
			default:
			System.out.println("wrong choice ...enter again");

		}
		}while(ch!=6);
		tr.updatefile(getUserid(),tr.getBal());
	}
}
