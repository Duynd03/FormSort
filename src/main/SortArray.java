package main;

import java.util.Scanner;

public class SortArray {
	private int n;
	private int []a;
	Scanner sc= new Scanner(System.in);
	public void input() {
		 n = sc.nextInt();
		 a = new int[n];
		for(int i=0;i<n;i++) {
			a[i]= sc.nextInt();
		}
		
	}
	public void output() {
		for(int i=0; i<n;i++) {
			System.out.print(a[i]+ " ");
		}
		System.out.println();
	}
	public void sort() {
		for(int i=0;i<n;i++) {
			for(int j=i+1; j<n;j++) {
				if(a[i]> a[j]) {
					int index= a[i];
					a[i]=a[j];
					a[j]= index;
				}
			}
		}
	}
	public void bubbleSort() {
	    for (int i = 0; i < n; i++) {
	        boolean swapped = false;
	        for (int j = n - 1; j > i; j--) {
	            if (a[j - 1] > a[j]) {
	                int temp = a[j - 1];
	                a[j - 1] = a[j];
	                a[j] = temp;
	                swapped = true;
	            }
	        }
	        if (!swapped) {
	            break;
	        }
	    }
	}
	public void selectionSort() {
	    for (int i = 0; i < n; i++) {
	        int p = i;
	        for (int j = i + 1; j < n; j++) {
	            if (a[j] < a[p]) {
	                p = j;
	            }
	        }
	        int temp = a[i];
	        a[i] = a[p];
	        a[p] = temp;
	    }
	}
	public void mergeSort(int[] a, int L, int R) {
	    if (L >= R - 1) return;
	    int M = (L + R) / 2;
	    mergeSort(a, L, M);
	    mergeSort(a, M, R);

	    int[] b = new int[n];
	    int i = L, j = M, k = L;
	    while (k < R) {
	        if (j >= R || (i < M && a[i] < a[j])) {
	            b[k++] = a[i++];
	        } else {
	            b[k++] = a[j++];
	        }
	    }

	    for (int m = L; m < R; m++) {
	        a[m] = b[m];
	    }
	}
	public void quickSort(int[] a, int low, int high) {
	    if (low >= high) return;
	    int pivot = a[high];
	    int i = low - 1;
	    for (int j = low; j < high; j++) {
	        if (a[j] < pivot) {
	            i++;
	            int temp = a[i];
	            a[i] = a[j];
	            a[j] = temp;
	        }
	    }
	    int temp = a[i + 1];
	    a[i + 1] = a[high];
	    a[high] = temp;

	    quickSort(a, low, i);
	    quickSort(a, i + 2, high);
	}

	public void insertionSort() {
	    for (int i = 1; i < n; i++) {
	        int x = a[i];
	        int j = i - 1;
	        while (j >= 0 && a[j] > x) {
	            a[j + 1] = a[j];
	            j--;
	        }
	        a[j + 1] = x;
	    }
	}

	public void printBeforeAfterElement() {
		int x= sc.nextInt();
		String tem=" ";
		for(int i=0;i<n;i++) {
			if(x==a[i]) {
				System.out.println(i+1);
				if(i == n){
					 System.out.println(a[i-1]+ " "+ tem);
				}	
				if (i == 0){
					System.out.println(tem+ " "+ a[i+1] );
				}
				else if( i !=n && i!=0) {
					System.out.println(a[i-1] + " "+ a[i+1]);
				}
			else {
				System.out.println(" ");
			}
			}
		}
	}
	public static void measureTime(Runnable task) {
	    int repetitions = 10;  
	    long totalTime = 0;    
	    for (int i = 0; i < repetitions; i++) {
	        long startTime = System.currentTimeMillis();  
	        task.run();  
	        long endTime = System.currentTimeMillis();    
	        totalTime += (endTime - startTime);  	    }
	    double averageTime = totalTime / (double) repetitions;  
	    System.out.println("Average time taken: " + averageTime + " ms");
	}


	public static void main(String[] args) {
	    SortArray arr = new SortArray();
	    arr.input();
	    
	    // Đo thời gian của từng thuật toán sắp xếp
	    System.out.println("Time for Bubble Sort:");
	    measureTime(() -> arr.bubbleSort());
	    arr.output();
	    
	    arr.input();  // Nhập lại mảng cho lần thử kế tiếp
	    System.out.println("Time for Insertion Sort:");
	    measureTime(() -> arr.insertionSort());
	    arr.output();
	    
	    arr.input();  // Nhập lại mảng cho lần thử kế tiếp
	    System.out.println("Time for Selection Sort:");
	    measureTime(() -> arr.selectionSort());
	    arr.output();
	    
	    arr.input();  // Nhập lại mảng cho lần thử kế tiếp
	    System.out.println("Time for Merge Sort:");
	    measureTime(() -> arr.mergeSort(arr.a, 0, arr.n));
	    arr.output();
	    
	    arr.input();  // Nhập lại mảng cho lần thử kế tiếp
	    System.out.println("Time for Quick Sort:");
	    measureTime(() -> arr.quickSort(arr.a, 0, arr.n - 1));
	    arr.output();
	}
	
	
}
