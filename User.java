import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class User
{
    private String name, password;
    private int userid;
    private long ph_no;
    public final Scanner scan = new Scanner(System.in);

    String getName()
    {
        return name;
    }
    void setName(String name)
    {
        this.name = name;
    }
    String getPassword()
    {
        return password;
    }
    void setPassword(String password)
    {
        this.password = password;
    }
    int getUserid()
    {
        return userid;
    }
    void setUserid(int userid)
    {
        this.userid = userid;
    }
    long getPh_no()
    {
        return ph_no;
    }
    void setPh_no(long ph_no)
    {
        this.ph_no = ph_no;
    }
    
    public boolean auth(int userid, String password) throws IOException
    {
        File f = new File("./Accounts.txt");
        FileInput in = new FileInput(f);//??
        String[] data;
        do
        {
            if(!in.ready())//check for endoffile
                break;
            data = in.readline().trim().split("[#]");
            setUserid(Integer.parseInt(data[0]));
            setPassword(data[1]);
            setName(data[2]);
            setPh_no(Long.parseLong(data[3]));
        }while(getUserid()!=userid || !getPassword().equals(password));
        in.close();
        if(getUserid()==userid && getPassword().equals(password))
            return true;
        return false;
    }

    public boolean login() throws IOException
    {
        //Layout.clearScreen();
        //Layout.displayEstelle();
        System.out.println("\t\tLogin\t\t");
        System.out.print("Enter your username: ");
        int userid = scan.nextInt();
        System.out.print("Enter your password: ");
        String password = scan.nextLine();
        if(auth(userid, password))
        {
            System.out.println("Login Successfull");
            return true;
        }
        else
        {
            System.out.println("Login Failed!");
            System.out.print("Login Again?[y/n]");
            char choice = scan.nextLine().charAt(0);
            if(choice == 'y')
                return login();
            else
            {
                System.out.print("SignUp Instead?[y/n]");
                choice = scan.nextLine().charAt(0);
                if(choice == 'y')
                    return signUp();
                else
                    return false;
            }
        }
    }

    public boolean signUp() throws IOException
    {
		//Layout.clearScreen();
        //Layout.displayEstelle();
        Random r = new Random();
		File f = new File("./Accounts.txt");
		FileInput in = new FileInput(f);
		String s = "";
		char c;
        while(in.ready())
        {
            c = (char)in.read();
            s = s.concat(String.valueOf(c));
        }
		in.close();		
        String[] str = s.trim().split("\n");

        int userid,error;
        System.out.println("\t\tSignUp\t\t");
        System.out.print("Enter your NAME: ");
        setName(scan.nextLine());
        do
        {
            System.out.print("Enter your PhoneNo: ");
            error = 0;
            try
            {
                setPh_no(Long.parseLong(scan.nextLine()));
            }
            catch(NumberFormatException e)
            {
                error = 1;
            }
            //check for 10 digits
        }while(error == 1);
        do
        {
            userid = 100001 + r.nextInt(9999);
            for(int i=0; i<str.length; i++)
            {
                if(Integer.parseInt(str[i].split("[#]")[0])==userid)
                {
                    userid = 0;
                    break;
                }
            }
            setUserid(userid);
        }while(userid == 0);
        System.out.println("the generated userid is :"+userid);       
        System.out.print("Enter your Password: ");
        setPassword(scan.nextLine());
        FileWriter writer = new FileWriter(f);
		writer.write(s + "\n");
        writer.append(userid+"#"+getPassword()+"#"+getName()+"#"+getPh_no());
        writer.close();
        return true;
    }
    void change(){}
}