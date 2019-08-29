package Classes;

public class SearchTM {
    private String bookId;
    private String name;
    private String author;
    private String staues;

    public SearchTM(String bookId, String name, String author, String staues) {
        this.setBookId(bookId);
        this.setName(name);
        this.setAuthor(author);
        this.setStaues(staues);
    }

    public SearchTM() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStaues() {
        return staues;
    }

    public void setStaues(String staues) {
        this.staues = staues;
    }

    @Override
    public String toString() {
        return "SearchTM{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", staues='" + staues + '\'' +
                '}';
    }
}
