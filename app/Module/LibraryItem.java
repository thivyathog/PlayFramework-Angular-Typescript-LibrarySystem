package Module;

import java.util.Objects;

public abstract class LibraryItem {

    private int iSBN;
    private String theTitle;
    private String lSector;
    private String type;
    private DateModule publicationDateModule;
    private double dueCost;
    private DateModule borrowed;
    private Reader currentReader;

    public LibraryItem(int iSBN, String theTitle, String lSector, DateModule publicationDateModule, String type) {
        this.iSBN = iSBN;
        this.theTitle = theTitle;
        this.lSector = lSector;
        this.publicationDateModule = publicationDateModule;
        this.type = type;
    }

    public LibraryItem(int iSBN, String theTitle, String lSector, DateModule publicationDateModule, DateModule borrowed,
                       Reader currentReader, String type) {
        this.iSBN = iSBN;
        this.theTitle = theTitle;
        this.lSector = lSector;
        this.publicationDateModule = publicationDateModule;
        this.borrowed = borrowed;
        this.currentReader = currentReader;
        this.type = type;
    }


    protected LibraryItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem)) return false;
        LibraryItem that = (LibraryItem) o;
        return iSBN == that.iSBN;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iSBN);
    }

    public double getDueCost() {
        return dueCost;
    }

    public void setDueCost(double dueCost) {
        this.dueCost = dueCost;
    }

    public String getType() {
        return type;
    }

    public int getISBN() {
        return iSBN;
    }


    public String getTheTitle() {
        return theTitle;
    }


    public DateModule getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(DateModule borrowed) {
        this.borrowed = borrowed;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }


    public String getlSector() {
        return lSector;
    }

    public DateModule getPublicationDateModule() {
        return publicationDateModule;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "iSBN=" + iSBN +
                ", theTitle='" + theTitle + '\'' +
                ", lSector='" + lSector + '\'' +
                ", publicationDateModule=" + publicationDateModule +
                ", borrowed=" + borrowed +
                ", currentReader=" + currentReader +
                ", type='" + type + '\'' +
                ", dueCost=" + dueCost +
                '}';
    }


}
