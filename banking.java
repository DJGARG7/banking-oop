import java.io.*;
import java.util.*;
class Transaction
{
	int userid,bal,tr1;
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    File f = new File("D:/java codes/1project/Balance.txt");
	Transaction(int userid)throws IOException
	{
		this.userid = userid;
		bal = searchId(userid);
	}
	//rutvay chutiyas
	int getBal()
	{
		return bal;
	}
	int searchId(int userid)throws IOException
	{
        String s="";
        Scanner scan= new Scanner(f);
        while(scan.hasNextLine())
        {
            s=scan.nextLine();  
            if(s.startsWith(userid+"#"))
            {
                    return Integer.parseInt(s.split("[#]")[1]);
            }
        }
        scan.close();
        return -1; 
	}
	void updatefile(int userid,int bal)throws IOException
	{
        Scanner scan = new Scanner(f);
        String s="";
        String temp="";
        while(scan.hasNextLine())
        {
            s= scan.nextLine();
            if(s.startsWith(userid+"#"))
            continue;
            temp= temp+s+"\n";
        }
        scan.close();
        FileWriter  f1= new FileWriter(f);
        f1.write(temp);
        f1.append(userid+"#"+bal+"\n");
        f1.close(); 
	}
	void deposit()throws IOException
	{
		Show.clear();
		System.out.println("enter amount to be deposited(max 50000 ruppees at a time)");
		tr1 = Integer.parseInt(scan.readLine());
		if(tr1>50000)
		{
			Show.clear();
			System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED");
		}
		else if(tr1<1)
		{
			Show.clear();
			System.out.println("invalid amount....transaction failed");
		}
		else
		{
			bal +=tr1; 	
			Show.clear();
			System.out.println("TRANSACTION SUCCESSFUL");
		}
	}
	void withdraw()throws IOException
	{
		Show.clear();
		System.out.println("enter amount to be withdrawn(max 20000 ruppees at a time)");
		tr1 = Integer.parseInt(scan.readLine());
		if(tr1>20000)
		{
			Show.clear();
			System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED\nCURRENT BALANCE:"+bal);
		}
		else if(tr1<1)
		{
			Show.clear();
			System.out.println("invalid amount....transaction failed");
		}
		else if(bal<tr1)
		{
			Show.clear();
			System.out.println("NOT ENOUGH FUNDS....TRANSACTION FAILED\nCURRENT BALANCE:"+bal);
		}
		else
		{
			bal -=tr1;
			Show.clear();
			System.out.println("TRANSACTION SUCCESSFUL");
		}
	}
	void transfer()throws IOException
	{
		int bal=0;
		Show.clear();
		System.out.println("ENTER USERID OF BENEFICIARY");
		int userid = Integer.parseInt(scan.readLine());
		Show.clear();
		if(userid== this.userid)
			System.out.println("CANNOT TRANSFER TO SELF");
		else if((bal=searchId(userid))==-1)
			System.out.println("USERID NOT FOUND....TRANSACTION FAILED");
		else
		{
			System.out.println("enter the amount to be transfered(max 10000 ruppees allowed)");
			tr1 = Integer.parseInt(scan.readLine());
			Show.clear();
			if(tr1>10000)
				System.out.println("MAXIMUN LIMIT EXCEEDED......TRANSACTION FAILED\nCURRENT BALANCE:"+this.bal);
			else if(tr1<1)
				System.out.println("invalid amount....transaction failed");
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
public class banking extends User
{
	public final BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

	void display(int bal) throws IOException
	{
		Show.clear();
		System.out.println("USER ID: "+getUserid());
		System.out.println("ACCOUNT HOLDER NAME: "+getName());
		System.out.println("PHONE NUMBER: "+ getPh_no());
		System.out.println("AVAILABLE BALANCE: "+ bal);
		System.out.println("press enter to continue");
		scan.readLine();
		Show.clear();
	}
	void mainmenu1()throws IOException
	{
        int ch;
		Transaction tr = new Transaction(getUserid());
		do
		{
		System.out.println("\n___________\n");
		System.out.println("1.DEPOSIT");
		System.out.println("2.WITHDRAW");
		System.out.println("3.TRANSFER");
		System.out.println("4.CHANGE YOUR PASSWORD");
		System.out.println("5. DISPLAY DETAILS AND BALANCE");
		System.out.println("6.LOGOUT");
		System.out.println("ENTER YOUR CHOICE");
		ch = Integer.parseInt(scan.readLine());
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
			change();
			break;
			case 5:
			display(tr.getBal());
			break;
			case 6:
			Show.clear();
			System.out.println("LOGGING OUT.......");
			break;
			default:
			Show.clear();
			System.out.println("wrong choice ...enter again");

		}
		}while(ch!=6);
		tr.updatefile(getUserid(),tr.getBal());
	}
}
