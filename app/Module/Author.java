package Module;


public class Author extends Person {

    private int authorid;

    public Author(String name) {
        super(name);
    }

    public Author(String name, int authorid) {
        super(name);
        this.authorid = authorid;
    }

    @Override
    public String toString() {
        return getName();
    }
}
