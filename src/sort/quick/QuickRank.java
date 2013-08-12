package sort.quick;

import gainData.GainData;

import java.util.Arrays;

public class QuickRank {
	static long startTime;
	static long endTime;
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
		int pivot = data[low];                          //2 3 1 4 2 5
        int tem = 0;
        swap(data,high,low);
        //c1_+=2;
        //System.out.println("pivot:"+pivot);
		for (int i = low ; i < high; i++) {
			if (data[i] < pivot) {
                //System.out.println("border:"+border+" "+i+" "+data[i]);
                //c1+=2;
                swap(data,border,i);
                //c1_+=4;
				border++;
                /*for(int ii=low;ii<=high;ii++){
                    System.out.print(data[ii]+" ");
                }
                System.out.println();*/
			}
		}
        swap(data,high,border);
       /* System.out.println("1----------");
        for(int ii=low;ii<=high;ii++){
            System.out.print(data[ii]+" ");
        }
        System.out.println();
        System.out.println("border:"+border);
        System.out.println("----------");*/
		return border;
	}

    private void swap(int data[],int i,int j){
        int temp = data[i];
        data[i]=data[j];
        data[j]=temp;
    }
	/**
	 * 
	 * 快速排序的具体实现，排正序
	 */

	public void qsort_asc(int data[], int low, int high) {
		int i, j, x;
		if (low < high) { // 这个条件用来结束递归
			i = low;
			j = high;
			x = data[i];
			//c2++;
			//c2_+=3;
			while (i < j) {
				while (i < j && data[j] > x) {
					j--; // 从右向左找第一个小于x的数
					//c2_+=1;
                    //c2+=2;
				}
				if (i < j) {
					data[i] = data[j];
					i++;
                    //c2++;
					//c2_+=2;
				}
				while (i < j && data[i] < x) {
					i++; // 从左向右找第一个大于x的数
					//c2_+=1;
                    //c2+=2;
				}
				if (i < j) {
					data[j] = data[i];
					j--;
                    //c2++;
					//c2_+=2;
				}
			}
			data[i] = x;
			qsort_asc(data, low, i - 1);
			qsort_asc(data, i + 1, high);
			//c2_+=3;
		}

	}

	public static void main(String args[]) {
		QuickRank quick = new QuickRank();
		GainData.setSize(10000000);
		int[] num = GainData.randomNum();
       /* for(int d:num){
            System.out.print(d+" ");
        }*/
        System.out.println();
        //int[] num=new int[]{2, 3, 1, 4, 2, 5};
        int[] num_ = Arrays.copyOf(num,num.length);
        int[] num_1 = Arrays.copyOf(num,num.length);
        Arrays.sort(num_1);
		//System.out.println("排序前");
		//for(int n: num){ System.out.println("  "+n); }

		startTime = System.currentTimeMillis();
		quick.quickSort(num, 0, num.length - 1);
        long midTime = System.currentTimeMillis();
		quick.qsort_asc(num_,0,num_.length-1);
		endTime = System.currentTimeMillis();
		System.out.println("c1="+c1+" c1_="+c1_+" c2="+c2+" c2_="+c2_);
		//System.out.println("排序后");
		//int i=0; for(int n: num){ System.out.println(num_[i]+" "+n+" "+num_1[i++]); }

		System.out.println("====快速排序结束====");
		System.out.println("快速排序用时:" + (midTime - startTime)+" ;;; "+(endTime - midTime));
	}
}
