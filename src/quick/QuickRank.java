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
		int border = low;   //border��¼��ǰ��pivotС�����ֵı߽磬�ҵ�һ����pivotС�ľ͸�border����������border��һ
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
	 * ��������ľ���ʵ�֣�������
	 * 
	 * @param data
	 * 
	 * @param low
	 * 
	 * @param high
	 */

	public void qsort_asc(int data[], int low, int high) {
		int i, j, x;
		if (low < high) { // ����������������ݹ�
			i = low;
			j = high;
			x = data[i];
			c2++;
			c2_+=3;
			while (i < j) {
				while (i < j && data[j] > x) {
					j--; // ���������ҵ�һ��С��x����
					c2_+=1;
				}
				if (i < j) {
					data[i] = data[j];
					i++;
					c2_+=2;
				}
				while (i < j && data[i] < x) {
					i++; // ���������ҵ�һ������x����
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
		//System.out.println("����ǰ");
		//for(int n: num){ System.out.println("  "+n); }

		startTime = System.currentTimeMillis();
		quick.quickSort(num, 0, num.length - 1);
		quick.qsort_asc(num,0,num.length-1);
		endTime = System.currentTimeMillis();
		System.out.println("c1="+c1+" c1_="+c1_+" c2="+c2+" c2_="+c2_);
		//System.out.println("�����");
		//for(int n: num){ System.out.println(" "+n); }
		
		System.out.println("====�����������====");
		System.out.println("����������ʱ:" + (endTime - startTime));
	}
}
