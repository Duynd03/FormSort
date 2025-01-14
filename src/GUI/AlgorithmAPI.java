package GUI;

public class AlgorithmAPI {
    private SortSearchModel model;

    public AlgorithmAPI(SortSearchModel model) {
        this.model = model;
    }

    public void printExecutionData() {
        System.out.println("Danh sách kết quả thực thi:");
        AlgorithmExecution[] executions = model.getExecutionsArray(); 
        for (var execution : executions) {
            System.out.println(execution);
        }
    }
}
