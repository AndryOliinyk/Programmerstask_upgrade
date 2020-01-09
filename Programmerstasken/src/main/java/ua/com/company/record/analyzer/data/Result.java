package ua.com.company.record.analyzer.data;

public class Result {
    
    private static final String DEFAULT_RESULT = "-";

    private String resultID;
    private int[] validRepresent;
    private String averageWaitingTime = DEFAULT_RESULT;

    public String getResultID() {
        return resultID;
    }
    public void setResultID(String resultID) {
        this.resultID = resultID;
    }
    public int[] getValidRepresent() {
        return validRepresent;
    }
    public void setValidRepresent(int[] validRepresent) {
        this.validRepresent = validRepresent;
    }
    public String getAverageWaitingTime() {
        return averageWaitingTime;
    }
    public void setAverageWaitingTime(String averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public void generateAvarage() {
        if (validRepresent.length != 0) {
            int sumRepresent = 0;
            for (int represent : validRepresent) {
                sumRepresent += represent;
            }
            setAverageWaitingTime(String.valueOf(sumRepresent / validRepresent.length));
        }
    }
}