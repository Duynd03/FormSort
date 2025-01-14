package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UI {
    private static int n;
    private static int[] arr;
    private static JFrame frame;
    private static JTextField txtInput, txtN, txtSearchChar;
    private static JButton btnSortSelection, btnSortBubble, btnSortInsertion, btnSortMerge, btnSortQuick, btnSearchChar;
    private static JTextArea lstResult;
    private static JLabel lblResult, lblTime, lblSearchResult;

    public static void main(String[] args) {
        // Khởi tạo giao diện
        frame = new JFrame("Sắp Xếp và Tìm Kiếm Ký Tự");
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);

        // Các thành phần giao diện
        JLabel lblN = new JLabel("Nhập số lượng phần tử (n):");
        txtN = new JTextField(5);
        JLabel lblInput = new JLabel("Nhập chuỗi ký tự (mỗi ký tự cách nhau 1 dấu cách):");
        txtInput = new JTextField(30);
        JLabel lblSearchChar = new JLabel("Nhập ký tự cần tìm:");
        txtSearchChar = new JTextField(5);
        btnSearchChar = new JButton("Tìm Ký Tự");
        
        // Tạo nhóm nút sắp xếp
        JPanel sortPanel = new JPanel();
        sortPanel.setLayout(new GridLayout(1, 5, 5, 5)); // Căn chỉnh các nút sắp xếp trong một hàng

        btnSortSelection = new JButton("Sắp Xếp Chọn Lọc");
        btnSortBubble = new JButton("Sắp Xếp Nổi Bọt");
        btnSortInsertion = new JButton("Sắp Xếp Chèn");
        btnSortMerge = new JButton("Sắp Xếp Gộp");
        btnSortQuick = new JButton("Sắp Xếp Nhanh");

        sortPanel.add(btnSortSelection);
        sortPanel.add(btnSortBubble);
        sortPanel.add(btnSortInsertion);
        sortPanel.add(btnSortMerge);
        sortPanel.add(btnSortQuick);

        lstResult = new JTextArea(5, 40);
        lstResult.setEditable(false);
        lblResult = new JLabel("Kết quả tìm kiếm:");
        lblTime = new JLabel("Thời gian chạy: ");
        lblSearchResult = new JLabel("Vị trí của ký tự:");

        // Thêm các thành phần vào JFrame
        frame.add(lblN);
        frame.add(txtN);
        frame.add(lblInput);
        frame.add(txtInput);
        frame.add(lblSearchChar);
        frame.add(txtSearchChar);
        frame.add(btnSearchChar);
        frame.add(sortPanel); // Thêm panel chứa các nút sắp xếp
        frame.add(new JScrollPane(lstResult));
        frame.add(lblResult);
        frame.add(lblTime);
        frame.add(lblSearchResult);

        // Sự kiện khi nhấn nút "Tìm Ký Tự"
        btnSearchChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchCharacter();
            }
        });

        // Sự kiện khi nhấn nút "Sắp Xếp Chọn Lọc"
        btnSortSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortingAndDisplay(UI::selectionSort);
            }
        });

        // Sự kiện khi nhấn nút "Sắp Xếp Nổi Bọt"
        btnSortBubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortingAndDisplay(UI::bubbleSort);
            }
        });

        // Sự kiện khi nhấn nút "Sắp Xếp Chèn"
        btnSortInsertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortingAndDisplay(UI::insertionSort);
            }
        });

        // Sự kiện khi nhấn nút "Sắp Xếp Gộp"
        btnSortMerge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortingAndDisplay(arr -> mergeSort(arr, 0, arr.length));
            }
        });

        // Sự kiện khi nhấn nút "Sắp Xếp Nhanh"
        btnSortQuick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortingAndDisplay(arr -> quickSort(arr, 0, arr.length - 1));
            }
        });

        // Thiết lập frame và hiển thị
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Phương thức tìm kiếm ký tự
    private static void handleSearchCharacter() {
        try {
            n = Integer.parseInt(txtN.getText());
            String input = txtInput.getText();
            String[] strArr = input.split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(strArr[i]);
            }

            String searchChar = txtSearchChar.getText();
            if (searchChar.isEmpty()) {
                lblSearchResult.setText("Vui lòng nhập ký tự cần tìm.");
                return;
            }
            char ch = searchChar.charAt(0);

            // Tìm kiếm ký tự trước khi sắp xếp
            int beforeIndex = findCharacter(arr, ch);
            lblSearchResult.setText("Vị trí ký tự trước khi sắp xếp: " + beforeIndex);
        } catch (Exception ex) {
            lblSearchResult.setText("Vui lòng nhập đúng dữ liệu.");
        }
    }

    // Phương thức xử lý sắp xếp và hiển thị kết quả
    private static void handleSortingAndDisplay(SortMethod sortMethod) {
        try {
            n = Integer.parseInt(txtN.getText());
            String input = txtInput.getText();
            String[] strArr = input.split(" ");
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(strArr[i]);
            }

            double time = measureTime(() -> sortMethod.sort(arr));

            lstResult.setText(Arrays.toString(arr));
            lblTime.setText("Thời gian chạy: " + time + " ms");
        } catch (Exception ex) {
            lstResult.setText("Vui lòng nhập đúng dữ liệu.");
        }
    }

    // Tìm kiếm ký tự trong mảng
    private static int findCharacter(int[] arr, char ch) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                return i;
            }
        }
        return -1; // Không tìm thấy ký tự
    }

    // Phương thức đo thời gian thực thi
    public static double measureTime(Runnable task) {
        long totalTime = 0;
        int repetitions = 10;
        for (int i = 0; i < repetitions; i++) {
            long startTime = System.nanoTime();
            task.run();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / (1000000.0 * repetitions); // Chuyển đổi thành mili giây
    }

    // Các thuật toán sắp xếp
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int p = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[p]) {
                    p = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > x) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = x;
        }
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L >= R - 1) return;
        int M = (L + R) / 2;
        mergeSort(arr, L, M);
        mergeSort(arr, M, R);

        int[] b = new int[arr.length];
        int i = L, j = M, k = L;
        while (k < R) {
            if (j >= R || (i < M && arr[i] < arr[j])) {
                b[k++] = arr[i++];
            } else {
                b[k++] = arr[j++];
            }
        }

        for (int m = L; m < R; m++) {
            arr[m] = b[m];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        quickSort(arr, low, i);
        quickSort(arr, i + 2, high);
    }

    // Định nghĩa interface cho các thuật toán sắp xếp
    interface SortMethod {
        void sort(int[] arr);
    }
}
