import java.sql.*;
import java.sql.Date;
import java.util.*;

public class bankAccount extends Mail{

	String name,address,email,password,query;
	int deposit=0,withdraw=0,balance=0;
	ATM owner;
	CreatePDF pdf;
	Mail m;
    int acNO= 0;int k=0;
    boolean transfer=false,dra=false,dep=false;
	
	
	Scanner scan=new Scanner(System.in);
	
	void createAC() throws SQLException {
		acNO=(int) ((Math.random()*((9999-1000)+1))+1000);
		

		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select *from bankac");
		rs.next();
		
		System.out.println("Name:");
		name=scan.nextLine();
		
		System.out.println("address:");
		address=scan.nextLine();
		
		System.out.println("Email:");
		email=scan.nextLine();
		
		//System.out.println("AC NO:");
		//acNO=scan.nextInt();
		//scan.nextLine();
		System.out.println("Enter new Password:");
		password=scan.nextLine();
		
		String insertac="insert into bankac values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(insertac);
		pst.setInt(1,acNO);
		pst.setString(2,name);
		pst.setString(3,address);
		pst.setString(4,email);
		pst.setInt(5,deposit);
		pst.setInt(6,withdraw);
		pst.setString(7,password);
		pst.setInt(8,balance);
		pst.execute();
		
		
	}
	
	void deposit()  {
		withdraw=0;
		Connection con=new dbConnection().getConnection();
		Statement st;
		try {
			st = con.createStatement();
		
		
		
		System.out.println("Enter AC no");
		acNO=scan.nextInt();
		System.out.println("Enter amount to deposit:");
		deposit=scan.nextInt();
		
		ResultSet rs=st.executeQuery("select *from bankac where acNO='"+acNO+"'");
		rs.next();
		String name=rs.getString("name");
		int oldbalance=rs.getInt("balance");
		balance=oldbalance+deposit;  
		query="update bankac set deposit='"+deposit+"'  where acNO='"+acNO+"'";
		st.executeUpdate(query);
		query="update bankac set balance='"+balance+"'  where acNO='"+acNO+"'";
		st.execute(query);
		
	
		
        System.out.println("------------------Deposit details----------------");       
		System.out.println("AC NAME:"+name);
		System.out.println("AC NO:"+acNO);
		System.out.println("Deposited amount:"+deposit);
        System.out.println("--------------------------------------------------");
        statement(deposit, withdraw, acNO,balance);
       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			///System.out.println("Account Number not found!!!!!!");
		}
	}
	
	void withdraw()  {
		
		deposit=0;
		Connection con=new dbConnection().getConnection();
		Statement st;
		try {
			st = con.createStatement();
		
		System.out.println("Enter AC no");
		acNO=scan.nextInt();
		scan.nextLine();
	    do
	    {
		System.out.println("Enter Your PIN:");
		password=scan.nextLine();
		ResultSet rs=st.executeQuery("select  *from bankac where acNO='"+acNO+"'");
		rs.next();
		String name=rs.getString("name");
		
		String pass=rs.getString("password");
		if(password.equals(pass)) {

		int oldbalance=rs.getInt("balance");
		System.out.println("Current Balance:"+oldbalance);	
		System.out.println("Enter amount to withdraw");
		withdraw=scan.nextInt();
	     if(oldbalance>withdraw) 
	     {
	    balance=oldbalance-withdraw;
		
		query="update bankac set withdraw='"+withdraw+"'  where acNO='"+acNO+"'";
		st.executeUpdate(query);
		query="update bankac set balance='"+balance+"'  where acNO='"+acNO+"'";
		st.executeUpdate(query);
	     
	     
		System.out.println("------------------Withdraw details----------------");       
		System.out.println("AC NAME:"+name);
		System.out.println("AC NO:"+acNO);
		System.out.println("Withdraw amount:"+withdraw);
        System.out.println("--------------------------------------------------");
        statement(deposit, withdraw, acNO,balance);
        dra=true;
	     
	     }
	     else
	    	 System.out.println("Not enough balance!!!!");
	    

		}else
			{System.out.println("Invalid PIN");
			k++;
			if(k==3) {System.out.println("You entered wrong pin for 3 times. so-Try again later!!!");}
			}
	    }while(k<=2);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Either your a/c number or password is incorrect, confirm and try again!!!!!");

		}
		
		
	}
	void displayACcreate() throws SQLException {
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		
		
		System.out.println("NAME:"+name);
		System.out.println("Address:"+address);
		System.out.println("Email:"+email);
		System.out.println("AC NO:"+acNO);
		System.out.println("PIN:"+password);
		
		
		
	}
	
	void displayStatement() throws SQLException {
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		
		int i=0;
		System.out.println("Enter ac No:");
		acNO=scan.nextInt();
		scan.nextLine();
	    System.out.println("Enter password:");
	    password=scan.nextLine();
		ResultSet rs=st.executeQuery("select *from bankac where acNO='"+acNO+"' ");
		rs.next();
		String pass=rs.getString("password");
        if(password.equals(pass)) {
        	ResultSet rs2=st.executeQuery("select *from statement where ac='"+acNO+"' order by DateTime desc ");
    		System.out.println("Your Bank statement is:");
    		while(rs2.next() && i<=100)
    		{
    		int dep=rs2.getInt("deposit");
    		int draw=rs2.getInt("withdraw");
    		int bal=rs2.getInt("balance");
    		Date dt=rs2.getDate("DateTime");
    		Time tm=rs2.getTime("DateTime");
    		System.out.println("__________________________________________________________");
    		System.out.println("Deposit          :"+dep);
    		System.out.println("Withdraw         :"+draw);
    		System.out.println("Balance          :"+bal);
    		System.out.println("Date-Time        :"+dt+" "+tm);
    		i++;
    		System.out.println("_________________________  _________________________________");
    		}
        }
		
		
		
		
	}
	
	
	
	
void statement(int depo,int draw,int acc,int bal) throws SQLException {
		
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select *from statement");
		rs.next();
		
			query="insert into statement values('"+acc+"','"+depo+"','"+draw+"',curtime(),'"+balance+"')";
			st.execute(query);
			if(dra=true)
				System.out.println("Transaction type: deposit");
			
			else if(dep=true)
				System.out.println("Transaction type: deposit");
			else
				System.out.println("Transaction type: deposit");
		
	}
	
	
	void transfer() throws SQLException {
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();

		System.out.println("----------Transfer Window------------");
		System.out.println("Enter Your AC NO:");
		acNO=scan.nextInt();
		ResultSet rs=st.executeQuery("select *from bankac where acNO='"+acNO+"'");
		rs.next();
		scan.nextLine();
		String pass=rs.getString("password");
		System.out.println("Enter PIN");
		password=scan.nextLine();
		if(password.equals(pass)) {
			
			System.out.println("Enter AC NO of receiver:");
			acNO=scan.nextInt();
			ResultSet s=st.executeQuery("select *from bankac where acNO='"+acNO+"'");
			s.next();
			
			System.out.println("Enter amount to transfer:");
			deposit=scan.nextInt();
			
			int oldbalance=s.getInt("balance");
			
			balance=oldbalance+deposit;
			query="update bankac set deposit='"+deposit+"'  where acNO='"+acNO+"'";
			st.executeUpdate(query);
			query="update bankac set balance='"+balance+"'  where acNO='"+acNO+"'";
			st.execute(query);
			
			statement(deposit, withdraw, acNO,balance);
			transfer=true;
			
			
			
		}
		
	}
	
	void mailing() throws SQLException {
		Connection con=new dbConnection().getConnection();
		Statement st=con.createStatement();
		System.out.println("Enter Your AC NO:");
		acNO=scan.nextInt();
		ResultSet rs=st.executeQuery("select *from bankac where acNO='"+acNO+"'");
		rs.next();
		scan.nextLine();
		String pass=rs.getString("password");
		String id=rs.getString("email");
		System.out.println("Enter PIN");
		password=scan.nextLine();
		if(password.equals(pass)) {
			
			pdf();
			send(id);
		}
		else
		{
			System.out.println("INVALID PIN!!!");
			
		}
		
		
		
	}

}
