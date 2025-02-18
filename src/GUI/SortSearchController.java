package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
        this.view.btnShowStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	handStatistics();
            }
        });
        this.view.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();  
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

            if (inputText.isEmpty()) {
                view.lstResult.setText("Vui lòng nhập các phần tử.");
                return null;
            }
            if (!inputText.matches("(-?\\d+(\\.\\d+)?)([ ,]+(-?\\d+(\\.\\d+)?))*")) {
                view.lstResult.setText("Vui lòng nhập các phần tử hợp lệ: số thực, cách nhau bởi dấu phẩy hoặc khoảng trắng.");
                return null;
            }
            String[] input = inputText.split("[ ]+");

            if (input.length != n) {
                view.lstResult.setText("Vui lòng nhập đúng " + n + " phần tử.");
                return null;
            }
            double[] arr = new double[n];

            for (int i = 0; i < n; i++) {
                try {
                    arr[i] = Double.parseDouble(input[i].trim()); 
                } catch (NumberFormatException e) {
                    view.lstResult.setText("Vui lòng nhập các phần tử là số thực hợp lệ.");
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
        double executionTime = (end - start) / 1_000_000.0; 
        model.incrementSelectionSortRunCount();  
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

        double executionTime = (end - start) / 1_000_000.0; 

        model.incrementBubbleSortRunCount();

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
        double executionTime = (end - start) / 1_000_000.0; 
        model.incrementInsertionSortRunCount();
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
            double x = Double.parseDouble(view.txtSearchChar.getText()); 
            String result = model.findCharacterWithNeighbors(x); 
            view.lstSearchResult.setText(result);
        } catch (NumberFormatException ex) {
            view.lstSearchResult.setText("Vui lòng nhập đúng dữ liệu (số thực hoặc số nguyên).");
        }
    }
    private void handStatistics() {
        // Lấy danh sách các lần thực thi
        List<AlgorithmExecution> executions = model.getExecutions();
        if (executions.isEmpty()) {
            // Nếu không có dữ liệu, hiển thị thông báo
            view.lstResult.setText("Không có dữ liệu thống kê.");
            return;
        }
        // Hiển thị dữ liệu lên JTable
        String[] columnNames = {"Thuật toán", "Số phần tử", "Số lần thực thi", "Thời gian (ms)"};
        Object[][] data = new Object[executions.size()][4];
        // Tìm thuật toán tối ưu (có thời gian thực thi nhỏ nhất)
        AlgorithmExecution optimalAlgorithm = executions.get(0); // Giả định thuật toán đầu tiên là tối ưu
        for (int i = 0; i < executions.size(); i++) {
            AlgorithmExecution execution = executions.get(i);
            data[i][0] = execution.getAlgorithmName();
            data[i][1] = execution.getNumberOfElements();
            data[i][2] = execution.getRunCount();
            data[i][3] = execution.getExecutionTime();
            // Cập nhật thuật toán tối ưu
            if (execution.getExecutionTime() < optimalAlgorithm.getExecutionTime()) {
                optimalAlgorithm = execution;
            }
        }
        // Cập nhật JTable với dữ liệu mới
        view.tblStatistics.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        // Hiển thị thông tin thuật toán tối ưu lên lstResult
        String result = String.format(
            "Thuật toán tối ưu nhất:\n" +
            "- Tên thuật toán: %s\n" +
            "- Số phần tử: %d\n" +
            "- Số lần thực thi: %d\n" +
            "- Thời gian thực thi: %.4f ms",
            optimalAlgorithm.getAlgorithmName(),
            optimalAlgorithm.getNumberOfElements(),
            optimalAlgorithm.getRunCount(),
            optimalAlgorithm.getExecutionTime()
        );
        view.lstResult.setText(result);
    }
    private void resetData() {
        DefaultTableModel tableModel = (DefaultTableModel) view.tblStatistics.getModel();
        tableModel.setRowCount(0); 
        view.lstResult.setText(""); 
        view.txtInput.setText(""); 
        view.txtN.setText(""); 
        view.txtSearchChar.setText(""); 
        view.lblTime.setText(""); 
        view.lstSearchResult.setText("");
        // Xóa danh sách thực thi trong model
        model.getExecutions().clear();
    }




}