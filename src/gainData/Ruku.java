package gainData;
import java.sql.SQLException;

import conn.Conn;


public class Ruku {

	public static void main(String[] args) {
		int count=100000;   //数据量
		GainData.setSize(count);
		int[] num=GainData.randomNum();
		int[] num1=GainData.randomNum();
		int[] num2=GainData.randomNum();
		int i=1;
		int sum=50;     //线程数
		while(i<=sum){
			new Th(num,num1,num2,i,sum,count).start();
			i++;
		}
	}
}
class Th extends Thread{
	int[] num;
	int[] num1;
	int[] num2;
	int mark;
	int sum;
	int count;
	Th(int num[],int num1[],int num2[],int mark,int sum,int count){
		this.num=num;
		this.num1=num1;
		this.num2=num2;
		this.mark=mark;
		this.sum=sum;
		this.count=count;
	}
	public void run(){
		Conn c=new Conn();
		c.getConn();
		int i=(count/sum)*(mark-1);
		int j=(count/sum)*mark;
		try {
			while(i<j){
				c.getOldStmt().executeUpdate("insert into test value("+num[i]+","+num1[i]+","+num2[i]+")");
				i++;
			}
			System.out.println("录入完成"+i);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			c.close();
		}
	}
}
