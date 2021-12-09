package com.example.demo;
 
import java.util.Calendar;
import java.util.Date;

 
public class NewDate  {
	
	int [] checkInTime = new int[2];
	int [] checkOutTime = new int[2];
	public int [] date= new int[3];
	
 
	public NewDate()
	{
		
	}

	

	public NewDate(String date ,String checkInTime, String checkOutTime)
	{
		
		this.date =getDate(date);
		this.checkInTime = getTime(checkInTime);
		this.checkOutTime =getTime(checkOutTime);
		
		
	}
	public static boolean team(String checkInTime, String checkOutTime)
	{
		
		int[] arrIn =getTime(checkInTime);
		int[] arrOut =getTime(checkOutTime);
		if(arrOut[0]<=arrIn[0])
			return true;
			else
			return false;
	}
	
		
	public static NewDate getDateAndTime() {
		
		NewDate temp = new NewDate();
		Date obj = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(obj);
		temp.date[2] = cal.get(Calendar.DAY_OF_MONTH);
		temp.date[1]= cal.get(Calendar.MONTH) +1;
		temp.date[0]= cal.get(Calendar.YEAR);
		temp.checkOutTime[0]=temp.checkInTime[0] = cal.get(Calendar.HOUR_OF_DAY);
		temp.checkOutTime[1]=temp.checkInTime[1]=  cal.get(Calendar.MINUTE);
		return temp;
	}

	
	public static int[] getDate(String s) {
		
		int temp[];
		temp = new int[3];
		String str="";
		int count=0;
		for(int i=0; i<s.length();i++)
		{
			
			if(s.charAt(i)=='-')
			{
				count++;
				str="";
				
			}
			else
			{
				str +=s.charAt(i);
				temp[count]=Integer.parseInt(str);
			}
				
				
		}
		return temp;
	}
	public static int[] getTime(String s)
	{
		int temp[];
		temp = new int[3];
		int count=0;
		String str="";
		for(int i=0; i<s.length();i++)
		{
			
			
			if(s.charAt(i)==':')
			{
				count++;
				str="";
				
			}
			else
			{
				str +=s.charAt(i);
				temp[count]=Integer.parseInt(str);
			}
				
				
		}
				
		return temp;
	}
	
	
	public static String getDate(int [] a)
	{
		return a[0] +"-" + a[1] + "-" + a[2];
	}
 
	public static String getTime(int [] a)
	{
		return a[0] +":" + a[1] ;
	}
 
	public static String getTime(int hrs , int min)
	{
		return hrs +":" + min ;
	}
	
	public static String getDate(int day, int month ,int year)
	{
		return day +"-" +month +"-" + year;
	}



	public static boolean comparison(NewDate book, NewDate passed) {
		
	for(int i=0;i<3;i++)
	{
		if(book.date[i]==passed.date[i])
		{
			continue;
		}
		else 
			return true;
			
	}
		if(book.checkInTime[0]<passed.checkInTime[0])
		{
			if(book.checkOutTime[0]>passed.checkInTime[0])
				return false;
			else if(book.checkOutTime[0]==passed.checkInTime[0])
			{
				if(book.checkOutTime[1]>passed.checkInTime[1])
					return false;
			}
			else
		   return true;
						
		}
		else if(book.checkInTime[0]>passed.checkInTime[0])
		{
			if(book.checkInTime[0]<passed.checkOutTime[0])
				return false;
			else if(book.checkInTime[0]==passed.checkOutTime[0])
			{
				if(book.checkInTime[1]<passed.checkOutTime[1])
						return false;
			}
			else
				return true;
		}
		else 
		return false;
			
		return true;
			
	}
	

	public static boolean update(NewDate book) {
		 NewDate passed= getDateAndTime();
		
	for(int i=0;i<3;i++)
	{
		if(book.date[i]==passed.date[i])
		{
			continue;
		}
		else if(book.date[i]<passed.date[i])
		{
			return true;
		}
		else 
			return false;
			
	}
		if(book.checkOutTime[0]<passed.checkOutTime[0])
		{
			
		   return true;
						
		}
		else if(book.checkOutTime[0]==passed.checkOutTime[0])
		{
			if(book.checkOutTime[1]<=passed.checkOutTime[1])
			{
				
			   return true;
							
			}
			else
				return false;
				
		}
		else
			return false;
			
			
	}
	public static boolean error(NewDate book) {
		 NewDate passed= getDateAndTime();
		
	for(int i=0;i<3;i++)
	{
		if(book.date[i]==passed.date[i])
		{
			continue;
		}
		else if(book.date[i]<passed.date[i])
		{
			return true;
		}
		else 
		{
			if(book.checkOutTime[0]<=book.checkInTime[0])
				return true;
				else
				return false;
		}
			
	}
		if(book.checkInTime[0]<passed.checkInTime[0])
		{
			
		   return true;
						
		}
		else if(book.checkInTime[0]==passed.checkInTime[0])
		{
			if(book.checkInTime[1]<=passed.checkInTime[1])
			{
				
			   return true;
							
			}
			else
			{
				if(book.checkOutTime[0]<=book.checkInTime[0])
					return true;
					else
					return false;
			}
				
		}
		else
		{
			if(book.checkOutTime[0]<=book.checkInTime[0])
				return true;
				else
				return false;
		}
			
			
	}
	
	public static boolean cancelled(NewDate book) {
		 NewDate passed= getDateAndTime();
		
	for(int i=0;i<3;i++)
	{
		if(book.date[i]==passed.date[i])
		{
			continue;
		}
		else 
		return true;
			
	}
		if((book.checkInTime[0]-1)>(passed.checkInTime[0]))
		{
			
		   return true;
						
		}
		else 
		return false;
			
			
	}
	public static boolean modified(NewDate book) {
		 NewDate passed= getDateAndTime();
		
	for(int i=0;i<3;i++)
	{
		if(book.date[i]==passed.date[i])
		{
			continue;
		}
		else 
		return true;
			
	}
		if((book.checkInTime[0]-2)>(passed.checkInTime[0]))
		{
			
		   return true;
						
		}
		else 
		return false;
			
			
	}

	 
	
	
}