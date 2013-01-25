package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test_2 {
	public static void array()
	{
		ArrayList<Short> list= new ArrayList<Short>();
		int i=0;
		Runtime r=Runtime.getRuntime();;
		try
		{
			for(i=0;i<100000000;i++)
			{
				list.add((short)1);
				if(r.freeMemory()<10000)
				{
					break;
				}
			}
			list=null;
		} catch(Exception e)
		{
			System.out.println(i);
		} finally
		{
			System.out.println(i+" "+r.freeMemory()+" "+r.totalMemory());
		}
	}
	public static void linked()
	{
		LinkedList<Long> list= new LinkedList<Long>();
		int i=0;
		Runtime r=Runtime.getRuntime();;
		try
		{
//			Test_2 t = new Test_2();
			for(i=0;i<100000000;i++)
			{
				list.add(new Long(0xffffffff));
				if(r.freeMemory()<10000)
				{
					break;
				}
			}
			list=null;
		} catch(Exception e)
		{
			System.out.println(i);
		} finally
		{
			System.out.println(i+" "+r.freeMemory()+" "+r.totalMemory());
		}
	}
	public static void main(String[] args) {
		linked();  //259513072
	}
	static void f(Object s[])
	{
		for(Object o:s)
		{
			System.out.println(o.equals("1"));
		}
	}
}
