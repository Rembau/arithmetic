package countingSort;

public class CountingSort {
	static class Utils {
		public static void print(int a[]) {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] argv) {
		int[] A = CountingSort.countingSort(new int[] { 16, 4, 10, 10, 14, 7, 9, 3,
				2, 8, 1 });
		Utils.print(A);
	}

	public static int[] countingSort(int[] A) {
		int[] B = new int[A.length];
		// 假设A中的数据a'有，0<=a' && a' < k并且k=100
		int k = 100;
		countingSort(A, B, k);
		return B;
	}

	private static void countingSort(int[] A, int[] B, int k) {
		int[] C = new int[k];
		// 计数
		for (int j = 0; j < A.length; j++) {
			int a = A[j];
			C[a] += 1;
		}
		Utils.print(C);
		// 求计数和
		for (int i = 1; i < k; i++) {
			C[i] = C[i] + C[i - 1];
		}
		Utils.print(C);
		// 整理
		for (int j = A.length - 1; j >= 0; j--) {
			int a = A[j];
			B[C[a] - 1] = a;
			C[a] -= 1;
		}
	}
}
