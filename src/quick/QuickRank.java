package quick;

import gainData.GainData;

public class QuickRank {
	static long startTime;
	static long endTime;;
	static int c1=0;
	static int c1_=0;
	
	static int c2=0;
	static int c2_=0;
	
	public QuickRank() {
	}

	public void quickSort(int[] data, int low, int high) {
		if (low < high) {
			int pivot = partition(data, low, high);

			quickSort(data, low, pivot - 1);
			quickSort(data, pivot + 1, high);
		}

	}

	public int partition(int[] data, int low, int high) {
		int border = low;   //border记录当前比pivot小的数字的边界，找到一个比pivot小的就跟border交换，并且border加一
		int pivot = data[low];
		int temp = 0;
		for (int i = low + 1; i <= high; i++) {
			c1++;
			c1_+=2;
			if (data[i] < pivot) {
				c1++;
				temp = data[border];
				data[border] = data[i];
				data[i] = temp;
				c1_+=4;
				border++;
			}
		}
		return border--;
	}

	/**
	 * 
	 * 快速排序的具体实现，排正序
	 * 
	 * @param data
	 * 
	 * @param low
	 * 
	 * @param high
	 */

	public void qsort_asc(int data[], int low, int high) {
		int i, j, x;
		if (low < high) { // 这个条件用来结束递归
			i = low;
			j = high;
			x = data[i];
			c2++;
			c2_+=3;
			while (i < j) {
				while (i < j && data[j] > x) {
					j--; // 从右向左找第一个小于x的数
					c2_+=1;
				}
				if (i < j) {
					data[i] = data[j];
					i++;
					c2_+=2;
				}
				while (i < j && data[i] < x) {
					i++; // 从左向右找第一个大于x的数
					c2_+=1;
				}
				if (i < j) {
					data[j] = data[i];
					j--;
					c2_+=2;
				}
				c2+=7;
				
			}
			data[i] = x;
			qsort_asc(data, low, i - 1);
			qsort_asc(data, i + 1, high);
			c2_+=3;
		}

	}

	public static void main(String args[]) {
		QuickRank quick = new QuickRank();
		GainData.setSize(10000);
		int[] num = GainData.randomNum();

		System.out.println(num[0]);
		//System.out.println("排序前");
		//for(int n: num){ System.out.println("  "+n); }

		startTime = System.currentTimeMillis();
		quick.quickSort(num, 0, num.length - 1);
		quick.qsort_asc(num,0,num.length-1);
		endTime = System.currentTimeMillis();
		System.out.println("c1="+c1+" c1_="+c1_+" c2="+c2+" c2_="+c2_);
		//System.out.println("排序后");
		//for(int n: num){ System.out.println(" "+n); }
		
		System.out.println("====快速排序结束====");
		System.out.println("快速排序用时:" + (endTime - startTime));
	}
}
