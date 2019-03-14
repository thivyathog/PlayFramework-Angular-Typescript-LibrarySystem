package Module;

public class LibraryManagerException extends Exception {
    private String title;

    public String getTitle() {
        return title;
    }

    public LibraryManagerException(String title) {
        this.title = title;
    }
}
