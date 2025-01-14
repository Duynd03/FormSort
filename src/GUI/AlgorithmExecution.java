package GUI;

public class AlgorithmExecution {
    private String algorithmName;
    private int numberOfElements;
    private int runCount;
    private double executionTime;
    private String timestamp;

    public AlgorithmExecution(String algorithmName, int numberOfElements, int runCount, double executionTime) {
        this.algorithmName = algorithmName;
        this.numberOfElements = numberOfElements;
        this.runCount = runCount;
        this.executionTime = executionTime;
        this.timestamp = java.time.LocalDateTime.now().toString(); 
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getRunCount() {
        return runCount;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Algorithm: " + algorithmName +
               ", Elements: " + numberOfElements +
               ", Runs: " + runCount +
               ", Time: " + executionTime + " ms" +
               ", Timestamp: " + timestamp;
    }
}
