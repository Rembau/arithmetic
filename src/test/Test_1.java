package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import quick.QuickRank;

import conn.Conn;

public class Test_1 {
	public void f1()
	{
		Conn conn = new Conn();
		String sql="select * from test1 order by t asc";

		try 
		{
//			ResultSet rs=null;
//			rs = 
					conn.getOldStmt().executeQuery(sql);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally
		{
			conn.close();
		}
		
	}
	public void f2()
	{
		Conn conn = new Conn();
		String sql="select * from test1";
		ResultSet rs=null;
		int data[]=null;
		try 
		{
			rs = conn.getOldStmt().executeQuery(sql);
			//rs.last();
			data=new int[100000];
			int i=0;
			//rs.beforeFirst();
			while(rs.next())
			{
				data[i] = rs.getInt(1);
				i++;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally
		{
			conn.close();
		}
		QuickRank quick=new QuickRank();
		quick.quickSort(data,0,data.length-1);
	}
	public static void main(String[] args) {
		Test_1 t=new Test_1();
		Conn conn = new Conn();
		conn.close();
		long i=System.currentTimeMillis();
		t.f1();
		long j=System.currentTimeMillis();
		t.f2();
		long k=System.currentTimeMillis();
		System.out.println("���ݿ�order by t asc����"+(j-i)+"\n�����ݿ��ж�������������:"+(k-j));
	}
}
