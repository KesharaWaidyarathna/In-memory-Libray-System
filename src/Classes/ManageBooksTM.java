package Classes;

public class ManageBooksTM {
    private String bookId;
    private String name;
    private String authorName;
    private String statues;

    public ManageBooksTM(String bookId, String name, String authorName, String statues) {
        this.setBookId(bookId);
        this.setName(name);
        this.setAuthorName(authorName);
        this.setStatues(statues);
    }

    public ManageBooksTM() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    @Override
    public String toString() {
        return "ManageBooksTM{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                ", statues='" + statues + '\'' +
                '}';
    }
}
