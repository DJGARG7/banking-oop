import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class User
{
    private String name, password;
    private int userid;
    private int ph_no;
    public final  BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

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
    int getPh_no()
    {
        return ph_no;
    }
    void setPh_no(int ph_no)
    {
        this.ph_no = ph_no;
    }
    
    public boolean auth(int userid, String password) throws IOException
    {
        File f = new File("C:/Users/Rutvay/Desktop/codes/Accounts.txt");
        Scanner in= new Scanner(f);
        String[] data;
        do
        {
            if(!in.hasNextLine())//check for endoffile
                break;
            data = in.nextLine().trim().split("[#]");
            setUserid(Integer.parseInt(data[0]));
            setPassword(data[1]);
            setName(data[2]);
            setPh_no(Integer.parseInt(data[3]));
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
        int userid = Integer.parseInt(scan.readLine());
        Scanner alag = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scan.readLine();
        if(auth(userid, password))
        {
            System.out.println("Login Successfull");
            return true;
        }
        else
        {
            System.out.println("Login Failed!");
            System.out.print("Login Again?[y/n]");
            char choice = alag.nextLine().charAt(0);
            if(choice == 'y')
                return login();
            else
            {
                System.out.print("SignUp Instead?[y/n]");
                choice = alag.nextLine().charAt(0);
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
        File f = new File("C:/Users/Rutvay/Desktop/codes/Accounts.txt");  
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
                setPh_no(Integer.parseInt(scan.readLine()));
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
        return false;
    }
    boolean change()throws IOException
    {
        File f = new File("C:/Users/Rutvay/Desktop/codes/Accounts.txt");
        String oldpas="";
        System.out.println("Enter your old password :");
        oldpas=scan.readLine();
        if(!oldpas.equals(getPassword()))
        {
            System.out.println("You have entered a wrong password");
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
        return true;
    }
}
