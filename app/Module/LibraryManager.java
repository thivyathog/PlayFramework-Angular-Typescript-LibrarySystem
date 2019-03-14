package Module;

import java.sql.SQLException;
import java.util.List;

public interface LibraryManager {
    void addItem(LibraryItem libraryItem);

    List<LibraryItem> displayItems();

    void deleteItem(LibraryItem libraryItem);

    void borrowItem(LibraryItem item, Reader borrower, DateModule borrowedDateModule) throws SQLException;

    void returnItem(LibraryItem item, DateModule borrowedDateModule, DateModule returnedDateModule) throws SQLException;

    List<LibraryItem> generateReport() throws SQLException;

    LibraryItem searchItemByTitle(String title) throws LibraryManagerException;

    void reserve(LibraryItem item, Reader reader, int noOfDay) throws SQLException;

}