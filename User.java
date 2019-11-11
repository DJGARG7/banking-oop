import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class User
{
    private String name, password;
    private int userid;
    private long ph_no;
    public final BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    File f = new File("D:/java codes/1project/Accounts.txt");
    File f1 = new File("D:/java codes/1project/Balance.txt");

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
       
        Scanner in= new Scanner(f);
        String[] data;
        do
        {
            if(!in.hasNextLine())
                break;
            data = in.nextLine().trim().split("[#]");
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
        Show.clear();
        int error=0,userid=0;
        System.out.println("\t\tLogin\t\t");
        do
        {
            System.out.print("Enter your userid: ");
            error = 0;
            try
            {
                userid = Integer.parseInt(scan.readLine());
            }
            catch(NumberFormatException e)
            {
                error = 1;
                System.out.println("invalid userid");
            }
        }while(error == 1);
        
        Scanner alag = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scan.readLine();
        if(auth(userid, password))
        {
            Show.clear();
            System.out.println("Login Successfull");
            return true;
        }
        else
        {
            Show.clear();
            System.out.println("Login Failed!");
            System.out.print("Login Again?[y/n]");
            char choice = alag.nextLine().charAt(0);
            if(choice == 'y')
                return login();
            else
            {
                Show.clear();
                System.out.print("SignUp Instead?[y/n]");
                choice = alag.nextLine().charAt(0);
                if(choice == 'y')
                    return signUp();
                else
                {
                    Show.clear();
                    return false;
                }
            }
        }
    }

    public boolean signUp() throws IOException
    {
		
        Show.clear();
        Random r = new Random();
        Scanner in= new Scanner(f);
		String s = "";
        while(in.hasNextLine())
            s+=in.nextLine()+"\n";
        in.close();		
        String[] str = s.trim().split("\n");
        int userid,error;
        System.out.println("\t\tSignUp\t\t");
        System.out.print("Enter your NAME: ");
        setName(scan.readLine());
        do
        {
            System.out.print("Enter your PhoneNo: ");
            error = 0;
            try
            {
                setPh_no(Long.parseLong(scan.readLine()));
            }
            catch(NumberFormatException e)
            {
                System.out.println("invalid number");
                error = 1;
            }
            if(!(getPh_no()>=1000000000L && getPh_no()<=9999999999L))
            {
                System.out.println("invalid number");
                error = 1;
            }
        }while(error == 1);
        do
        {
            userid = 100001 + r.nextInt(9999);
            for(int i=0; i<str.length; i++)
                if(Integer.parseInt(str[i].split("[#]")[0])==userid)
                {
                    userid = 0;
                    break;
                }
            setUserid(userid);
        }while(userid == 0);
        System.out.println("the generated userid is :"+userid);       
        System.out.print("Enter your Password: ");
        setPassword(scan.readLine());
        FileWriter writer = new FileWriter(f);
		writer.write(s);
        writer.append(userid+"#"+getPassword()+"#"+getName()+"#"+getPh_no()+"\n");
        writer.close();
        Show.clear();
        System.out.println("SIGNUP SUCCESSFUL");
        Scanner ins= new Scanner(f1);
		s = "";
        while(ins.hasNextLine())
            s+=ins.nextLine()+"\n";
        ins.close();	
        FileWriter writer1 = new FileWriter(f1);
		writer1.write(s);
        writer1.append(userid+"#"+0+"\n");
        writer1.close();
        return false;
    }
    boolean change()throws IOException
    {
        String oldpas="";
        Show.clear();
        System.out.println("Enter your old password :");
        oldpas=scan.readLine();
        if(!oldpas.equals(getPassword()))
        {
            Show.clear();
            System.out.println("You have entered a wrong password.....task failed!!");
            return false;
        }
        System.out.println("Enter New password");
        setPassword(scan.readLine());
        Scanner s= new Scanner(f);
        String ch2="";
        String temp="";      
        while(s.hasNextLine())
        {
            temp = s.nextLine();
            if(temp.startsWith(getUserid()+"#"))
                continue;
            ch2 = ch2 + temp + "\n";
        }
        s.close();
        FileWriter name = new FileWriter(f);
        name.write(ch2);
        name.append(userid+"#"+getPassword()+"#"+getName()+"#"+getPh_no()+"\n");
        name.close();
        Show.clear();
        System.out.println("PASSWORD CHANGED SUCCESSFULLY");
        return true;
    }
}
