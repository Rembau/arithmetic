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
				/*System.out.print("排序");
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

* 快速排序的具体实现，排正序 

* @param data 

* @param low 

* @param high 

*/  

public void qsort_asc(int data[], int low, int high) {  
	int i, j, x;  
	if (low < high) { //这个条件用来结束递归   
	       i = low;  
	       j = high;  
	       x = data[i];  
	       while (i < j) {  
	              while (i < j && data[j] > x) {  
	                     j--; //从右向左找第一个小于x的数   
	              }  
	              if (i < j) {  
	                     data[i] = data[j];  
	                     i++;  
	              }  
	              while (i < j && data[i] < x) {  
	                     i++; //从左向右找第一个大于x的数   
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
		System.out.print("排序前");
		/*for(int n: num){
			System.out.print("  "+n);
		}*/
		startTime=System.currentTimeMillis();
		quick.quickSort(num,0,num.length-1);
		//quick.qsort_asc(num,0,num.length-1);
		endTime=System.currentTimeMillis();
		System.out.println("排序后");
		/*for(int n: num){
			System.out.println(" "+n);
		}*/
		System.out.println("====快速排序结束====");
		System.out.println("快速排序用时:"+(endTime-startTime));
	}
}
