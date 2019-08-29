package Classes;

public class IssueBooksTM {
    private String issuId;
    private String bookId;
    private String memberId;
    private String date;

    public IssueBooksTM(String issuId, String bookId, String memberId, String date) {
        this.setIssuId(issuId);
        this.setBookId(bookId);
        this.setMemberId(memberId);
        this.setDate(date);
    }

    public IssueBooksTM() {
    }

    public String getIssuId() {
        return issuId;
    }

    public void setIssuId(String issuId) {
        this.issuId = issuId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "IssueBooksTM{" +
                "issuId='" + getIssuId() + '\'' +
                ", bookId='" + getBookId() + '\'' +
                ", memberId='" + getMemberId() + '\'' +
                ", date='" + getDate() + '\'' +
                '}';
    }
}
