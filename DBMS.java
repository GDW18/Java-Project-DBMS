import java.lang.*;
import java.util.*;

//where clause kara, aggegate,query la lowercase kara
class DBMS1
{
	public static void main(String arg[])
	{
		DBMS dobj = new DBMS();
		dobj.StartDBMS();
	}
}
//Database table / schema
class Student
{
	public int RID;
	public String Name;
	public int Salary;
	
	private static int Generator;
	static
	{
		Generator = 0;
	}
	
	public Student(String str,int value)
	{
		this.RID = ++Generator;
		this.Name = str;
		this.Salary = value;
	}
	
	public void DisplayData()
	{
		System.out.println(this.RID +"\t"+this.Name+"\t"+this.Salary);
	}
	
}

class DBMS
{
	public LinkedList<Student> lobj; //reference
	
	public DBMS()
	{
		lobj = new LinkedList<>();
	}
	
	public void StartDBMS()
	{
		Scanner scanobj = new Scanner(System.in);
		System.out.println("Marvellous customised DBMS Started successfully");
		
		String Query ="";
		while(true)
		{
			System.out.print("Marvellous DBMS console>");
			Query = scanobj.nextLine(); //tolowercase
			
			String Query1 = Query.toLowerCase();
			
			String tokens[] = Query1.split(" ");
			
			int QuerySize = tokens.length; 
			
			if(QuerySize == 1)
			{
				if("help".equals(tokens[0]))
				{
					System.out.println("This application is used to demonstrate the Customized DBMS");
					System.out.println("Exit:Terminate DBMS");
					System.out.println("Display all Data : select * from Student");
					System.out.println("Insert Data:Insert into student Name Salary");
					System.out.println("Delete by RID: remove RID RIDNo");
					System.out.println("Delete by Name:remove name RIDNAME");
					System.out.println("Display Minimumsalary:Display MinimumSalry");
					System.out.println("Display Maximumsalary:Display MaximumSalry");
					System.out.println("Display Averagesalary:Display AverageSalry");
					System.out.println("Display TotalSalary:Display Totalsalaries");
					System.out.println("Display Total countsof students:Display Totalcount");
					System.out.println("Display Student Information by Name:select * from Student where name is WRITE_STUDENT_NAME");
					System.out.println("Display Student Information by RID:select * from Student where name is WRITE_STUDENT_RID");
				}
				else if("exit".equals(tokens[0]))
				{
					System.out.println("Thank you for using Marvellous DBMS");
					break;
				}
			}
			else if(QuerySize == 2)
			{
				if("display".equals(tokens[0]))
				{
					if("minimumsalary".equals(tokens[1]))
					{
						AggregateMin();
					}
					else if("maximumsalary".equals(tokens[1]))
					{
						AggregateMax();
					}
					else if("averagesalary".equals(tokens[1]))
					{
						AggregateAvg();
					}
					else if("totalsalaries".equals(tokens[1]))
					{
						AggregateSum();
					}
					else if("totalcount".equals(tokens[1]))
					{
						AggregateCount();
					}
				}	
			}
			else if(QuerySize == 3)
			{
				if("remove".equals(tokens[0]))
				{
					if("rid".equals(tokens[1]))
					{
						DeleteSpecific(Integer.parseInt(tokens[2]));
					}
					else if("name".equals(tokens[1]))
					{
						DeleteSpecific(tokens[2]);
					}
				}
			}
			else if(QuerySize == 4)
			{
				if("select".equals(tokens[0]))
				{
					if("*".equals(tokens[1]))
					{
						if("from".equals(tokens[2]))
						{
							if("student".equals(tokens[3]))
							{
								DisplayAll();
							}
						}
					}
				}
			}
			else if(QuerySize == 5)
			{
				//Insert into student piyush 5000
				if("insert".equals(tokens[0]))
				{
					if("into".equals(tokens[1]))
					{
						if("student".equals(tokens[2]))
						{
							InsertData(tokens[3],Integer.parseInt(tokens[4]));//ekach class madhe ahe mhnun lobj.InsertData chi garaj nahi
						}
					}
				}
			}
			else if(QuerySize == 8)
			{
				if("select".equals(tokens[0]))
				{
					if("*".equals(tokens[1]))
					{
						if("from".equals(tokens[2]))
						{
							if("student".equals(tokens[3]))
							{
								if("where".equals(tokens[4]))
								{
									if("name".equals(tokens[5]))
									{
										if("is".equals(tokens[6]))
										{
											DisplaySpecific(tokens[7]);
										}
									}
								}
							}
						}
					}
				}
				if("rid".equals(tokens[5]))
				{
					if("is".equals(tokens[6]))
					{
						DisplaySpecific(Integer.parseInt(tokens[7]));
					}
				}
			}
		}
	}
	//Insert RID
	public void InsertData(String str,int value)
	{
		Student sobj = new Student(str,value);
		lobj.add(sobj);
	}
	//DIDPLAY Without condition
	public void DisplayAll()
	{
		for(Student sref: lobj)
		{
			sref.DisplayData();
		}
		
	}
	//Display by RID
	public void DisplaySpecific(int rid)
	{
		for(Student sref: lobj)
		{
			if(sref.RID == rid)
			{
				sref.DisplayData();
				break;
			}
		}	
	}
	//Display by Name
	public void DisplaySpecific(String str)
	{
		for(Student sref: lobj)
		{
			if(str.equals(sref.Name)) //(==) NAHI VAPRYCH Hashtable compare hoto
			{
				sref.DisplayData();
				//break; ajun piyush navache asu shaktat mhnum break nahi
			}
		}	
	}
	
	//Delete by RID
	public void DeleteSpecific(int rid)
	{
		int index = 0;
		for(Student sref: lobj)
		{
			if(sref.RID == rid)
			{
				lobj.remove(index);
				break;
			}
			index++;
		}	
	}
	
	public void DeleteSpecific(String Name)
	{
		int index = 0;
		for(Student sref: lobj)
		{
			if(Name.equals(sref.Name))
			{
				lobj.remove(index);
				break;
			}
			index++;
		}	
	}
	
	public void AggregateMax()
	{
		int iMax = 0;
		
		Student temp = null;
		
		for(Student sref : lobj)
		{
			if(sref.Salary > iMax)
			{
				iMax = sref.Salary;
				temp = sref; //object la refer kerto maximum salary cha
			}
		}
		System.out.println("Information of student having Maximum Salary:");
		temp.DisplayData();
	}
	public void AggregateMin()
	{
		int iMin =(lobj.getFirst()).Salary; // first mulachi salary bhetel
		
		Student temp = lobj.getFirst(); //temp la initialise kel karan pahile ch kami salary vala asel ter if madhe janar ch nahi
		
		for(Student sref : lobj)
		{
			if(sref.Salary < iMin)
			{
				iMin = sref.Salary;
				temp = sref;
			}
		}
		System.out.println("Information of student having Maximum Salary:");
		temp.DisplayData();
	}
	
	public void AggregateSum()
	{
		long iSum = 0;
		
		for(Student sref : lobj)
		{
			iSum = iSum + sref.Salary;
		}
		System.out.println("Summation of Salaries is:"+iSum);
	}
	
	public void AggregateAvg()
	{
		long iSum = 0;
		
		for(Student sref : lobj)
		{
			iSum = iSum + sref.Salary;
		}
		System.out.println("Average Salary is:"+iSum /(lobj.size())); //size hi method ahe
	}
	
	public void AggregateCount()
	{
		System.out.println("Count of students is :"+lobj.size());
	}
}
