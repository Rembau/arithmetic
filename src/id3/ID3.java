package id3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import conn.Conn;

public class ID3 {
	ArrayList<Object[]> values = new ArrayList<Object[]>();
	Justice j;
	int main;
	ArrayList<Integer> allUsable;
	String names[]={"天气","温度","分类1","风速","玩耍"};
	public ID3(ArrayList<Object[]> values,int m,ArrayList<Integer> allUsable,Justice j)
	{
		this.values=values;
		this.j=j;
		this.main=m;
		this.allUsable=allUsable;
		execute(allUsable,values);
		
	}
	public void execute(ArrayList<Integer> usable,ArrayList<Object[]> values)
	{
		int mark=-1;
		double max=0;
		for(int u:usable)
		{
			ID3_1 id31=new ID3_1(values,main,j);
			double r=id31.getGain(u);
			//System.out.println(r+" "+max);
			if(r>max)
			{
				mark=u;
				max=r;
			}
		}
		if(mark==-1)
		{
			return;
		}
		System.out.println(getSpace(allUsable.size()-usable.size())+"选择节点: "+names[mark]);
		HashMap<Object,ArrayList<Object[]>> seprateValues=new HashMap<Object,ArrayList<Object[]>>();
		for(Object[] o:values)
		{
			if(!seprateValues.containsKey(o[mark]))
			{
				ArrayList<Object[]> value= new ArrayList<Object[]>();
				seprateValues.put(o[mark], value);
			}
			seprateValues.get(o[mark]).add(o);
		}
		ArrayList<Integer> newUsable = new ArrayList<Integer>();
		for(int u1:usable)
		{
			if(u1!=mark)
			{
				newUsable.add(u1);
				System.out.print(" "+u1);
			}
		}
		System.out.println();
		for(Object o:seprateValues.keySet())
		{
			ArrayList<Object[]> vs=seprateValues.get(o);
			Object o1=vs.get(0)[main];
			boolean flag=true;
			for(Object[] o2:vs)
			{
				if(!o2[main].equals(o1))
				{
					System.out.println(getSpace(allUsable.size()-usable.size())+o+" 不是叶子节点");
					execute(newUsable,vs);
					flag=false;
					break;
				}
			}
			if(flag)
			{
				System.out.println(getSpace(allUsable.size()-usable.size())+o+" 为叶子节点 "+o1);
			}
		}
	}
	public String getSpace(int s)
	{
		String str="";
		for(int i=0;i<s;i++)
		{
			str+="   ";
		}
		return str;
	}
	public static void main(String[] args) {
		ArrayList<Object[]> values= new ArrayList<Object[]>();
		try {
			ResultSet rs = new Conn("test").getStmt().executeQuery("select * from id3");
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
		ArrayList<Integer> allUsable = new ArrayList<Integer>();
		int main=4;
		for(int i=0;i<values.get(0).length;i++){
			if(i!=main)
			{
				allUsable.add(i);
			}
		}
		new ID3(values,main,allUsable,new Justice(){
			public boolean compare(Object o) {			
				if("Yes".equals(o))
				{
					return true;
				}
				return false;
			}});
		System.out.println();
	}

}
