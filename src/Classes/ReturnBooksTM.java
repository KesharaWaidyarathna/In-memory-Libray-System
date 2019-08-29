package Classes;

public class ReturnBooksTM {
    private String issueId;
    private double totalFines;
    private String returnDate;

    public ReturnBooksTM(String issueId, double totalFines, String returnDate) {
        this.setIssueId(issueId);
        this.setTotalFines(totalFines);
        this.setReturnDate(returnDate);
    }

    public ReturnBooksTM() {
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public double getTotalFines() {
        return totalFines;
    }

    public void setTotalFines(double totalFines) {
        this.totalFines = totalFines;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "ReturnBooksTM{" +
                "issueId='" + issueId + '\'' +
                ", totalFines=" + totalFines +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
