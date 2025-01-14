package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortSearchController {
    private SortSearchModel model;
    private SortSearchView view;

    public SortSearchController(SortSearchModel model, SortSearchView view) {
        this.model = model;
        this.view = view;

        this.view.btnSortSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortSelection();
            }
        });

        this.view.btnSortBubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortBubble();
            }
        });

        this.view.btnSortInsertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortInsertion();
            }
        });

        this.view.btnSortMerge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortMerge();
            }
        });

        this.view.btnSortQuick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSortQuick();
            }
        });

        this.view.btnSearchChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
    }

    private double[] processInput() {
        try {
            int n = Integer.parseInt(view.txtN.getText().trim());
            if (n <= 0) {
                view.lstResult.setText("Vui lòng nhập n là số nguyên dương lớn hơn 0.");
                return null;
            }

            String inputText = view.txtInput.getText().trim();
            // Sửa lại regex để kiểm tra chính xác số thực và dấu phân cách
            if (!inputText.matches("(-?\\d+\\.?\\d*)([ ,]+(-?\\d+\\.?\\d*))*")) {
                view.lstResult.setText("Vui lòng nhập các phần tử hợp lệ: số thực, cách nhau bởi dấu phẩy hoặc khoảng trắng.");
                return null;
            }

            String[] input = inputText.split("[ ,]+");
            if (input.length != n) {
                view.lstResult.setText("Vui lòng nhập đúng " + n + " phần tử.");
                return null;
            }

            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                try {
                    arr[i] = Double.parseDouble(input[i]); // Chuyển đổi thành số thực
                } catch (NumberFormatException e) {
                    view.lstResult.setText("Vui lòng nhập các phần tử là số thực.");
                    return null;
                }
            }
            return arr;
        } catch (NumberFormatException e) {
            view.lstResult.setText("Vui lòng nhập n là số nguyên dương lớn hơn 0.");
            return null;
        }
    }





    public void handleSortSelection() {
    	double[] arr = processInput();
        if (arr == null) return;

        model.setArr(arr);
        long start = System.nanoTime();
        model.selectionSort();
        long end = System.nanoTime();

        double executionTime = (end - start) / 1_000_000.0; // Thời gian tính bằng mili giây

        // Tăng bộ đếm run count cho Selection Sort
        model.incrementSelectionSortRunCount();  // Cần gọi đúng nơi này

        // Lưu kết quả vào database
        model.addExecution("Selection Sort", arr.length, model.getSelectionSortRunCount(), executionTime);

        view.lstResult.setText("Mảng sau sắp xếp: " + java.util.Arrays.toString(model.getArr()));
        view.lblTime.setText("Thời gian chạy: " + executionTime + " ms");
    }


    public void handleSortBubble() {
    	double[] arr = processInput();
        if (arr == null) return;

        model.setArr(arr);
        long start = System.nanoTime();
        model.bubbleSort();
        long end = System.nanoTime();

        double executionTime = (end - start) / 1_000_000.0; // Thời gian tính bằng mili giây

        // Tăng bộ đếm run count cho Bubble Sort
        model.incrementBubbleSortRunCount();

        // Lưu kết quả vào database
        model.addExecution("Bubble Sort", arr.length, model.getBubbleSortRunCount(), executionTime);

        view.lstResult.setText("Mảng sau sắp xếp: " + java.util.Arrays.toString(model.getArr()));
        view.lblTime.setText("Thời gian chạy: " + executionTime + " ms");
    }

    public void handleSortInsertion() {
    	double[] arr = processInput();
        if (arr == null) return;

        model.setArr(arr);
        long start = System.nanoTime();
        model.insertionSort();
        long end = System.nanoTime();

        double executionTime = (end - start) / 1_000_000.0; // Thời gian tính bằng mili giây

        // Tăng bộ đếm run count cho Insertion Sort
        model.incrementInsertionSortRunCount();

        // Lưu kết quả vào database
        model.addExecution("Insertion Sort", arr.length, model.getInsertionSortRunCount(), executionTime);

        view.lstResult.setText("Mảng sau sắp xếp: " + java.util.Arrays.toString(model.getArr()));
        view.lblTime.setText("Thời gian chạy: " + executionTime + " ms");
    }

    public void handleSortMerge() {
    	double[] arr = processInput();
        if (arr == null) return;

        model.setArr(arr);
        long start = System.nanoTime();
        model.mergeSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();

        double executionTime = (end - start) / 1_000_000.0; 

        model.incrementMergeSortRunCount();

        model.addExecution("Merge Sort", arr.length, model.getMergeSortRunCount(), executionTime);

        view.lstResult.setText("Mảng sau sắp xếp: " + java.util.Arrays.toString(model.getArr()));
        view.lblTime.setText("Thời gian chạy: " + executionTime + " ms");
    }

    public void handleSortQuick() {
    	double[] arr = processInput();
        if (arr == null) return;

        model.setArr(arr);
        long start = System.nanoTime();
        model.quickSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();

        double executionTime = (end - start) / 1_000_000.0; 

        model.incrementQuickSortRunCount();

        model.addExecution("Quick Sort", arr.length, model.getQuickSortRunCount(), executionTime);

        view.lstResult.setText("Mảng sau sắp xếp: " + java.util.Arrays.toString(model.getArr()));
        view.lblTime.setText("Thời gian chạy: " + executionTime + " ms");
    }

    private void handleSearch() {
        try {
            double x = Double.parseDouble(view.txtSearchChar.getText()); // Chuyển đổi sang double
            String result = model.findCharacterWithNeighbors(x); // Gọi hàm tìm kiếm với kiểu double
            view.lstSearchResult.setText(result);
        } catch (NumberFormatException ex) {
            view.lstSearchResult.setText("Vui lòng nhập đúng dữ liệu (số thực hoặc số nguyên).");
        }
    }

}
