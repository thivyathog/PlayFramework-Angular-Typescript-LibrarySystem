package Module;


import java.util.List;

public class Book extends LibraryItem {
    private int noOfpages;

    private List<Author> author;

    private String publisher;


    public Book(int iSBN, String theTitle, String sector, DateModule publicationDateModule, String type, List<Author> author,
                String publisher, int noOfpages) {
        super(iSBN, theTitle, sector, publicationDateModule, type);
        this.author = author;
        this.publisher = publisher;
        this.noOfpages = noOfpages;
    }

    public Book(int iSBN, String theTitle, String sector, DateModule publicationDateModule, DateModule borrowed,
                Reader currentReader, String type, List<Author> author, String publisher, int noOfpages) {
        super(iSBN, theTitle, sector, publicationDateModule, borrowed, currentReader, type);
        this.author = author;
        this.publisher = publisher;
        this.noOfpages = noOfpages;
    }


    public List<Author> getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }


    public int getNoOfpages() {
        return noOfpages;
    }


    @Override
    public String toString() {

        return super.toString() +
                "author=" + author +
                ", publisher='" + publisher + '\'' +
                ", noOfpages=" + noOfpages +
                '}';
    }
}
