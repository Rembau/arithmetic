package quick;

import gainData.GainData;

public class QuickRank {
	static long startTime;
	static long endTime;
	public QuickRank(){
	}
	public void quickSort(int[] data,int low,int high){
		if(low<high){
			int pivot=parttion(data,low,high);
			
			quickSort(data,low,pivot-1);
			quickSort(data,pivot+1,high);
		}
		
	}
	public int parttion(int[] data,int low,int high){
		int border=low;
		int pivot=data[low];
		int temp=0;
		for(int i=low+1;i<=high;i++){
			if(data[i]<pivot){
				temp=data[border+1];
				data[border+1]=data[i];
				data[i]=temp;
				border++;
				/*System.out.print("����");
				for(int n: data){
					System.out.print("  "+n);
				}*/
				//System.out.println(border);
			}
		}
		temp=pivot;
		data[low]=data[border];
		data[border]=temp;
		return border;
	}
   


/** 

* ��������ľ���ʵ�֣������� 

* @param data 

* @param low 

* @param high 

*/  

public void qsort_asc(int data[], int low, int high) {  
	int i, j, x;  
	if (low < high) { //����������������ݹ�   
	       i = low;  
	       j = high;  
	       x = data[i];  
	       while (i < j) {  
	              while (i < j && data[j] > x) {  
	                     j--; //���������ҵ�һ��С��x����   
	              }  
	              if (i < j) {  
	                     data[i] = data[j];  
	                     i++;  
	              }  
	              while (i < j && data[i] < x) {  
	                     i++; //���������ҵ�һ������x����   
	              }  
	              if (i < j) {  
	                     data[j] = data[i];  
	                     j--;  
	              }  
	       }  
	       data[i] = x;  
	       qsort_asc(data, low, i - 1);  
	       qsort_asc(data, i + 1, high);  
	}

}  
	public static void main(String args[]){
		QuickRank quick=new QuickRank();
		GainData.setSize(100000);
		int[] num=GainData.randomNum();
		System.out.println(num[0]);
		System.out.print("����ǰ");
		/*for(int n: num){
			System.out.print("  "+n);
		}*/
		startTime=System.currentTimeMillis();
		quick.quickSort(num,0,num.length-1);
		//quick.qsort_asc(num,0,num.length-1);
		endTime=System.currentTimeMillis();
		System.out.println("�����");
		/*for(int n: num){
			System.out.println(" "+n);
		}*/
		System.out.println("====�����������====");
		System.out.println("����������ʱ:"+(endTime-startTime));
	}
}
