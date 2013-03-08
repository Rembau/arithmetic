package sort.HeapSort;
public class HeapSortTest {
/*
����* ���������ΪС���ѣ�����С��������
����*/
	public static int[] heap = new int[] { 1, 3, 7, 5, 2, 8, 4, 6 ,10, 9};
	public static void main(String[] args) {
		int temp;
	/*
����* ������(�Ըöѽ��м򵥵�����)
����*/
		CreateHeap();
		for (int i = heap.length - 1; 0 < i; i--) {
			temp = heap[0];
			heap[0] = heap[i];
			heap[i] = temp;
   /*
����* չʾÿ�������Ľ��
����*/
			for (int j = 0; j < heap.length; j++) {
				System.out.print(heap[j] + " ");
			}
			System.out.println();//����
/*
����* �ӶѶ����е�����ʹδ����������ؼ��ֵ��Ѷ�
����*/
			AdjustHeap(0, i);
		}
	}
/*
����* ������ʹ��Ѷ�Ϊδ����������ؼ���
����*/
	public static void AdjustHeap(int location, int unSortlength) {
		int temp;
		int tempLoc;
/*
����* ȷ�����ҽڵ����
����*/
		if ((tempLoc = (location + 1) * 2) < unSortlength) {
/*
����* �ж����ҽڵ��С
����*/
			if (heap[tempLoc] >= heap[tempLoc - 1]) {
/*
����* �жϸ��ڵ����ӽڵ�Ĵ�С�������ڵ�С���������ӽڵ㻻λ
����*/
				if (heap[location] < heap[tempLoc]) {
					temp = heap[location];
					heap[location] = heap[tempLoc];
					heap[tempLoc] = temp;
/*
����* �ݹ鷨�Ի�λ����ӽڵ㼰���ӽڵ���е���
����*/
					AdjustHeap(tempLoc, unSortlength);
				}
			} else {
/*
����* ��ڵ�����ҽڵ�
����*/
				if (heap[location] < heap[tempLoc - 1]) {
					temp = heap[location];
					heap[location] = heap[tempLoc - 1];
					heap[tempLoc - 1] = temp;
/*
����* �ݹ鷨�Ի�λ����ӽڵ㼰���ӽڵ���е���
����*/
					AdjustHeap(tempLoc - 1, unSortlength);
				}
			}
		}
/*
����* ȷ����ڵ����
����*/
		else if ((tempLoc = (location + 1) * 2 - 1) < unSortlength) {
/*
����* ����ڵ���бȽ�
����*/
			if (heap[location] < heap[tempLoc]) {
/*
����* ���ӽڵ���ڸ��ڵ㣬�����߽��л�λ
����*/
				temp = heap[location];
				heap[location] = heap[tempLoc];
				heap[tempLoc] = temp;
				AdjustHeap(tempLoc, unSortlength);
			}
		}
	}
/*
����* ������(�Ըöѽ��м򵥵�����)
����*/
	public static void CreateHeap() {
		for (int i = heap.length - 1; i >= 0; i--) {
			AdjustHeap(i, heap.length);
		}
	}
}
