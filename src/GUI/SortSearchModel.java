package GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortSearchModel {
    private double[] arr;
    private Connection connection;
    private List<AlgorithmExecution> executions = new ArrayList<>();
    private int selectionSortRunCount = 0;
    private int bubbleSortRunCount = 0;
    private int insertionSortRunCount = 0;
    private int mergeSortRunCount = 0;
    private int quickSortRunCount = 0;
    public SortSearchModel() {
        connection = Data.DatabaseConnection.getConnection();
    }

    public void setArr(double[] arr) {
        this.arr = arr;
    }

    // Phương thức để lấy giá trị mảng
    public double[] getArr() {
        return arr;
    }
 // Các phương thức tăng bộ đếm số lần chạy cho từng thuật toán
    public void incrementSelectionSortRunCount() {
        selectionSortRunCount++;
    }

    public int getSelectionSortRunCount() {
        return selectionSortRunCount;
    }

    public void incrementBubbleSortRunCount() {
        bubbleSortRunCount++;
    }

    public int getBubbleSortRunCount() {
        return bubbleSortRunCount;
    }

    public void incrementInsertionSortRunCount() {
        insertionSortRunCount++;
    }

    public int getInsertionSortRunCount() {
        return insertionSortRunCount;
    }

    public void incrementMergeSortRunCount() {
        mergeSortRunCount++;
    }

    public int getMergeSortRunCount() {
        return mergeSortRunCount;
    }

    public void incrementQuickSortRunCount() {
        quickSortRunCount++;
    }

    public int getQuickSortRunCount() {
        return quickSortRunCount;
    }


    // Thêm một kết quả thực thi và lưu vào cơ sở dữ liệu
 // Thêm một kết quả thực thi và lưu vào cơ sở dữ liệu
    public void addExecution(String algorithmName, int numberOfElements, int runCount, double executionTime) {
        // Tạo đối tượng AlgorithmExecution với số lần chạy và thời gian thực thi
        AlgorithmExecution execution = new AlgorithmExecution(algorithmName, numberOfElements, runCount, executionTime);

        // Thêm kết quả vào danh sách
        executions.add(execution);

        // Lưu kết quả vào cơ sở dữ liệu (hoặc thực hiện hành động khác)
        saveExecutionToDatabase(execution);
    }



    // Lấy danh sách kết quả thực thi
    public List<AlgorithmExecution> getExecutions() {
        return executions;
    }

    // Chuyển List<AlgorithmExecution> thành mảng AlgorithmExecution[]
    public AlgorithmExecution[] getExecutionsArray() {
        return executions.toArray(new AlgorithmExecution[0]);
    }

    // Lưu kết quả thực thi vào cơ sở dữ liệu
    private void saveExecutionToDatabase(AlgorithmExecution execution) {
        String query = "INSERT INTO algorithm_executions (algorithm_name, number_of_elements, run_count, execution_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, execution.getAlgorithmName());
            stmt.setInt(2, execution.getNumberOfElements());
            stmt.setInt(3, execution.getRunCount());
            stmt.setDouble(4, execution.getExecutionTime());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving execution data to database: " + e.getMessage());
        }
    }

    public String findCharacterWithNeighbors(double x) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) { // So sánh trực tiếp
                found = true;
                double prev = (i > 0) ? arr[i - 1] : -1; 
                double next = (i < arr.length - 1) ? arr[i + 1] : -1; 

                result.append("Vị trí: ").append(i+1).append(" \n ")
                      .append("Phần tử trước: ").append(prev == -1 ? "Không có" : prev)
                      .append(", Phần tử sau: ").append(next == -1 ? "Không có" : next)
                      .append("\n"); 
            }
        }

        if (!found) {
            return "Không tìm thấy ký tự.";
        }

        return result.toString();
    }

 // Phương thức sắp xếp chọn
    public void selectionSort() {
    	resetRunCount();
        for (int i = 0; i < arr.length - 1; i++) {
            int p = i;
            for (int j = i + 1; j < arr.length; j++) {
                selectionSortRunCount++; // Đếm số lần so sánh
                if (arr[j] < arr[p]) {
                    p = j;
                }
            }
            double temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
        }
       // System.out.println("Selection Sort Run Count: " + selectionSortRunCount);
    }

    // Phương thức sắp xếp nổi bọt
    public void bubbleSort() {
    	resetRunCount();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                bubbleSortRunCount++; // Đếm số lần so sánh
                if (arr[j] > arr[j + 1]) {
                	double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
       // System.out.println("Bubble Sort Run Count: " + bubbleSortRunCount);
    }

    // Phương thức sắp xếp chèn
    public void insertionSort() {
    	resetRunCount();
        for (int i = 1; i < arr.length; i++) {
        	double key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                insertionSortRunCount++; // Đếm số lần so sánh
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            insertionSortRunCount++; // Đếm lần so sánh cuối cùng khi điều kiện `arr[j] <= key`
            arr[j + 1] = key;
        }
       // System.out.println("Insertion Sort Run Count: " + insertionSortRunCount);
    }

    // Phương thức sắp xếp trộn
    public void mergeSort(double[] arr2, int L, int R) {
        if (L < R) { // Điều kiện dừng
            int M = (L + R) / 2;

            // Đệ quy chia đôi
            mergeSort(arr2, L, M);
            mergeSort(arr2, M + 1, R); // Sửa lại chỗ này để phần tử M không bị lặp lại

            // Mảng tạm để hợp nhất
            double[] b = new double[R - L + 1]; // Kích thước mảng b phải đủ để chứa cả hai nửa
            int i = L, j = M + 1, k = 0;

            // Hợp nhất hai nửa mảng
            while (i <= M && j <= R) {
                mergeSortRunCount++; // Đếm số lần so sánh trong vòng lặp này
                if (arr2[i] < arr2[j]) {
                    b[k++] = arr2[i++];
                } else {
                    b[k++] = arr2[j++];
                }
            }

            // Xử lý các phần tử còn lại ở mảng trái
            while (i <= M) {
                mergeSortRunCount++; // Đếm số lần xử lý (không có so sánh)
                b[k++] = arr2[i++];
            }

            // Xử lý các phần tử còn lại ở mảng phải
            while (j <= R) {
                mergeSortRunCount++; // Đếm số lần xử lý (không có so sánh)
                b[k++] = arr2[j++];
            }

            // Sao chép lại mảng đã sắp xếp
            for (int m = 0; m < R - L + 1; m++) {
                arr2[L + m] = b[m];
            }
        }
    }



    public void quickSort(double[] a, int L, int R) {
        if (L >= R) return;

        // Chọn pivot là phần tử giữa
        double pivot = a[(L + R) / 2];
        int i = L, j = R;

        while (i <= j) {
            // Tìm phần tử lớn hơn hoặc bằng pivot từ trái
            while (a[i] < pivot) {
                i++;
                quickSortRunCount++; // Đếm số lần so sánh
            }

            // Tìm phần tử nhỏ hơn hoặc bằng pivot từ phải
            while (a[j] > pivot) {
                j--;
                quickSortRunCount++; // Đếm số lần so sánh
            }

            if (i <= j) {
                // Hoán đổi các phần tử nếu cần
            	double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        // Gọi đệ quy cho mảng con bên trái
        if (L < j) quickSort(a, L, j);

        // Gọi đệ quy cho mảng con bên phải
        if (i < R) quickSort(a, i, R);
    }





    // Hàm để reset bộ đếm
    public void resetRunCount() {
        selectionSortRunCount = 0;
        bubbleSortRunCount = 0;
        insertionSortRunCount = 0;
        mergeSortRunCount = 0;
        quickSortRunCount = 0;
    }
   

    

}
