package id3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import conn.Conn;

public class ID3_1 {
	int p=0,n=0;
	ArrayList<Object[]> values;
	Justice j;
	int main;
	public ID3_1(ArrayList<Object[]> values,int m,Justice j)
	{
		this.values=values;
		this.j=j;
		this.main=m;
		for(Object[] o:values)
		{
			if(j.compare(o[main]))
			{
				p++;
			} else
			{
				n++;
			}
		}
	}
	/**
	 * 获取信息量，供决策树做出正确判断。
	 * @return
	 */
	public double getInfos(int p,int n)
	{
		double a=p/(double)(p+n);
		double b=n/(double)(p+n);
		return (a==0 || b==0)?0:-a*Math.log(a)/Math.log(2)-b*Math.log(b)/Math.log(2);
	}
	/**
	 * 获取信息熵
	 * @param p
	 * @param n
	 * @return
	 */
	public double getE(int pn[][])
	{
		double r=0;
		for(int i=0;i<pn.length;i++)
		{
			double in=getInfos(pn[i][0],pn[i][1]);
			double x=(pn[i][0]+pn[i][1])/(double)(p+n);
			r+=in*x;
		}
		return r;
	}
	/**
	 * 获取信息增益
	 */
	public  double getGain(int select)
	{
		int [][]pn=count(select);
		return getInfos(p,n)-getE(pn);
	}
	/**
	 * 统计
	 */
	public int[][] count(int select)
	{
		int[][] c;
		HashMap<Object,int[]> lists=new HashMap<Object,int[]>();
		for(Object o[]:values)
		{
			if(!lists.containsKey(o[select]))
			{
				int i[]={0,0};
				lists.put(o[select], i);
			}
			if(j.compare(o[main]))
			{
				lists.get(o[select])[0]++;
			} else
			{
				lists.get(o[select])[1]++;
			}
		}
		c=new int[lists.size()][];
		int i=0;
		for(int v[]:lists.values())
		{
			c[i]=v;
			i++;
		}
		return c;
	}
	public static void main(String[] args) {
		//System.out.println(new ID3().getInfos(4, 0));
		//String names[]={"年龄","收入","是否学生","信用","购买PC"};
		ArrayList<Object[]> values= new ArrayList<Object[]>();
		try {
			ResultSet rs = new Conn("test").getStmt().executeQuery("select * from id3 where age='<=30'");
			while(rs.next())
			{
				String vs[]=new String[5];
				for(int j=0;j<5;j++)
				{
					vs[j]=rs.getString(j+1).trim();
					System.out.print(vs[j]+" ");
				}
				System.out.println();
				values.add(vs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ID3_1 id3 = new ID3_1(values,4,new Justice(){
			public boolean compare(Object o) {			
				if("是".equals(o))
				{
					return true;
				}
				return false;
			}});
		System.out.println(id3.getGain(1));
	}
}
