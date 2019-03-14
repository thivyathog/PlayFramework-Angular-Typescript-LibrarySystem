package controllers;

import Module.*;
import Module.DateModule;
import connectivity.ConnectionClass;


import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;


import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class WestminsterLibraryManager implements LibraryManager {

    public double fee = 0;
    private LibraryItem[] librarySpace;
    private int bookCounter;
    private int dvdCounter;
    private int totalCounter;
    public String available;
    protected List<Reader> readerList = new ArrayList<>();
    private List<LibraryItem> libraryItems = new ArrayList<>();
    private List<LibraryItem> shelf = new ArrayList<>();
    protected List<LibraryItem> dvdShelf = new ArrayList<>();

    public WestminsterLibraryManager(int librarySpaceSize) {

        this.librarySpace = new LibraryItem[librarySpaceSize]; //initializing the array


        this.bookCounter = 1; //initializing the counters
        this.dvdCounter = 1;
        this.totalCounter = 0;

        try {
            initialize(); //retrieving data from database
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*
     *
     * Initializing the library items array and reader list by fetching it from
     * database
     *
     * */

    public void initialize() throws SQLException {
        Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql3 = "SELECT * From libraryitem";
        ResultSet resultSet = statement.executeQuery(sql3);

        while (resultSet.next()) {

            if (resultSet.getString(7).equalsIgnoreCase("dvd")) {
                String[] subs = resultSet.getString(11).replaceAll("\\[", "")
                        .replaceAll("\\]", "").split(",");
                String[] list1 = resultSet.getString(12).replaceAll("\\[", "")
                        .replaceAll("\\]", "").split(",");
                List<Language> list = new ArrayList<>();
                List<Language> list2 = new ArrayList<>();
                for (String aList1 : list1) lang(list2, aList1.trim());
                for (String aList1 : subs) lang(list, aList1.trim());

                System.out.println("add!!" + resultSet.getInt(1));

                String value = resultSet.getString(5);
                if (resultSet.wasNull()) {
                    value = "";
                }
                System.out.println(value);
                if (value.equals("")) {
                    LibraryItem item = new DVD(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), new DateModule(resultSet.getString(4)),
                            null, null, resultSet.getString(7), list, list2,
                            resultSet.getString(14), resultSet.getString(13));
                    item.setBorrowed(null);

                    addItem(item);
                } else {

                    addItem(new DVD(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), new DateModule(resultSet.getString(4)),
                            new DateModule(resultSet.getString("borrowed_date")),
                            new Reader(resultSet.getString(6)), resultSet.getString(7), list,
                            list2, resultSet.getString(14), resultSet.getString(13)));

                }


            } else {

                String[] author = resultSet.getString(8).replaceAll("\\[", "").replaceAll("\\]",
                        "").split(",");
                List<Author> authorList = new ArrayList<>();
                for (int k = 0; k < author.length; k++) {
                    authorList.add(new Author(author[k].trim()));
                }

                String value = resultSet.getString(5);
                if (resultSet.wasNull()) {
                    value = "";
                }
                System.out.println(value);
                if (value.equals("")) {
                    LibraryItem item = new Book(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), new DateModule(resultSet.getString(4)),
                            new DateModule("12-12-2018"), null, resultSet.getString(7),
                            authorList, resultSet.getString(9), resultSet.getInt(10));
                    item.setBorrowed(null);

                    addItem(item);
                } else {

                    addItem(new Book(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3), new DateModule(resultSet.getString(4)),
                            new DateModule(resultSet.getString("borrowed_date")),
                            new Reader(resultSet.getString(6)), resultSet.getString(7), authorList,
                            resultSet.getString(9), resultSet.getInt(10)));

                }
                //addItem(new Book(isbn,a,b,new DateModule(resultSet.getString(4)),new DateModule("2018-09-02"), new Reader
                // (resultSet.getString(6)),c,authorList,d,bo));


            }

            System.out.println(freeSlots() + "free");
        }


        String query = "SELECT * From reader;"; //populating reader List
        ResultSet resultSet2 = statement.executeQuery(query);
        while (resultSet2.next()) {

            readerList.add(new Reader(resultSet2.getString(1), resultSet2.getString(2),
                    resultSet2.getString(3), resultSet2.getString(4)));
        }
        connection.close();
    }

    public int freeSlots() {
        int counter = 0;
        for (LibraryItem itemv : librarySpace) {
            if (itemv == null) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void addItem(LibraryItem libraryItem) {

        if (freeSlots() == 0) { //checking if there is space available
            available = "No space available";

        } else {

            if (libraryItem.getType().equalsIgnoreCase("Book")) {
                if (bookCounter == 100) { //only 100 books can be added
                    available = "You have reached maximum of 100books that can be added";
                } else {
                    librarySpace[totalCounter] = libraryItem;
                    System.out.println("total" + totalCounter);

                    totalCounter++;

                    available = "SUCESSFULLY ADDED!";
                }
            } else {
                if (dvdCounter == 50) {
                    available = "You have reached maximum of 50 dvd that can be added";
                } else {
                    ;
                    librarySpace[totalCounter] = libraryItem;
                    System.out.println("total" + totalCounter);

                    totalCounter++;
                    available = "SUCESSFULLY ADDED!";

                }
            }


        }

    }

    @Override
    public List<LibraryItem> displayItems() { //displaying items through list


        for (LibraryItem item : librarySpace) {

            if ((item != null)) {
                if (!libraryItems.contains(item)) {
                    libraryItems.add(item);
                }

            }
        }

        return libraryItems;
    }

    public List<LibraryItem> bookList() { //sorting the list into their type based.
        for (int i = 0; i < displayItems().size(); i++) {
            if (displayItems().get(i).getType().equalsIgnoreCase("Book")) {
                if (!shelf.contains(displayItems().get(i))) {

                    shelf.add(displayItems().get(i));
                }
            } else {
                if (!dvdShelf.contains(displayItems().get(i))) {

                    dvdShelf.add(displayItems().get(i));
                }
            }
        }
        return shelf;
    }

    public List<LibraryItem> dvdList() {
        return dvdShelf;
    }

    @Override
    public void deleteItem(LibraryItem libraryItem) {

            int i = 0;
            int count = 0;

            for (i = 0; i < displayItems().size(); i++) {
                System.out.println(librarySpace.length);
                System.out.println(Arrays.toString(librarySpace));
                System.out.println(librarySpace[i]);
                System.out.println(librarySpace[i + 1]);
                if (libraryItems.get(i).equals(libraryItem)) {
                    if(libraryItem.getCurrentReader()==null) {
                        /*if (librarySpace[i].getISBN() == libraryItem.getISBN()) {*/
                        System.out.println(i);

                        for (int j = i; j < displayItems().size(); j++) {
                            if (j == 0) {
                                librarySpace[i] = null;
                            }
                            librarySpace[j] = librarySpace[j + 1];
                        }

                        libraryItems.remove(i);
                       // dvdShelf.remove(libraryItem);

                        if (libraryItem.getType().equalsIgnoreCase("DVD")) {
                            dvdShelf.remove(libraryItem);
                        } else {
                            shelf.remove(libraryItem);
                         //   dvdShelf.remove(libraryItem);
                        }

                    }
                }
            }
            System.out.println("after removal" + displayItems());
            System.out.println("after removal" + dvdShelf);
            System.out.println("after removal" + shelf);

            System.out.println("Available free space" + (librarySpace.length - count));
        }



    @Override
    public void borrowItem(LibraryItem item, Reader borrower, DateModule borrowedDateModule) throws SQLException {
        Connection connection = ConnectionClass.getConnection();

        System.out.println(item.getISBN());
        String query = "UPDATE libraryitem SET borrowed_date='" + LocalDate.parse(borrowedDateModule.getDateC())
                + "',readerId='" + borrower.getrId() + "'where isbn=" + item.getISBN() + ";";
        String query2 = "INSERT INTO borrowitem(isbn, readerId, borrowed_date, returned_date) VALUES(" + item.getISBN()
                + ",'" + borrower.getrId() + "','" + LocalDate.parse(borrowedDateModule.getDateC()) + "'," + null + ");";
        Statement statement = connection.createStatement();


        for (LibraryItem list : librarySpace) {
            System.out.println("list" + list);
            if (list != null) {
                if (list.getISBN() == item.getISBN()) { //finding the library item
                    if (item.getCurrentReader() == null) { //checking if the book is not borrowed.
                        available = "You have successfully borrowed the library item!";
                        System.out.println("enterReader " + item.getCurrentReader());
                        statement.execute(query);
                        statement.execute(query2);
                        item.setCurrentReader(borrower);
                        item.setBorrowed(borrowedDateModule);

                        System.out.println(item);
                    } else {
                        available = "The library item you entered is already borrowed!";
                    }
                }
            }
        }


    }

    @Override
    public void returnItem(LibraryItem item, DateModule borrowedDateModule, DateModule returnedDateModule) throws
            SQLException {

        int returnedperiod = returnedDateModule.calcDate() - borrowedDateModule.calcDate();
        //calculating the days difference between the borrowed and returned

        double cost = cost(item, returnedperiod);
        System.out.println("Cost to pay due to delay:" + cost(item, returnedperiod));
        String query = "UPDATE borrowitem SET cost=" + cost + ",returned_date='" + returnedDateModule.getDateC() +
                "' where borrowed_date='" + LocalDate.parse(borrowedDateModule.getDateC()) + "' AND isbn=" + item.getISBN()
                + ";";
        Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String query2 = "UPDATE libraryitem SET borrowed_date=" + null + " where isbn=" + item.getISBN() + ";";
        if (cost(item, returnedperiod) != 0) {
            fee = cost(item, returnedperiod);  //if its greater than zero
            statement.execute(query);
        } else {
            fee = 0;
        }

        item.setBorrowed(null); //setting the item borrowed date and reader to nul
        item.setCurrentReader(null);
        statement.execute(query2);
        System.out.println("return" + item);
    }

    public int checkIsbn(LibraryItem libraryItem) { //checking if the isbn is already there or not.
        int counter = 0;
        for (LibraryItem v : librarySpace) {
            if (v != null) {
                System.out.println(v.getISBN());
                if (v.getISBN() == libraryItem.getISBN()) {
                    System.out.println(libraryItem.getISBN());
                    counter = 1;

                }
            }
        }

        return counter;
    }

    public double cost(LibraryItem item, int returnedperiod) { //calculating due cost
        double cost = 0;
        int first3day;

        if (item.getClass().getSimpleName().equals("Book")) {
            System.out.println("enter");
            if (returnedperiod > 7) { //if return period is greater than 7
                first3day = returnedperiod - 7;  //first 7 days will be free.
                if (first3day < 4) {
                    cost = first3day * 24 * 0.2;  //first 3 days will be time*0.2 dollars
                    System.out.println(">7" + cost);
                } else {
                    first3day = returnedperiod - 3;
                    cost = 3 * 24 * 0.2 + first3day * 24 * 0.5; //after 3 days it will be 0.5 dollars
                    System.out.println("less" + cost);

                }

            }
        } else {//for DVD
            if (returnedperiod > 3) {
                first3day = returnedperiod - 3; //first 3 days free
                if (first3day < 3) {
                    cost = first3day * 24 * 0.2;

                } else {
                    first3day = first3day - 3;
                    cost = 3 * 24 * 0.2 + first3day * 24 * 0.5;

                }
            }


        }
        DecimalFormat df = new DecimalFormat("####0.00");
        String costr = df.format(cost);
        cost = Double.parseDouble(costr);

        return cost;
    }

    public void deleteItems(LibraryItem libraryItem) {
        int i = 0;
        int count = 0;

        for (i = 0; i < displayItems().size(); i++) {
            System.out.println(librarySpace.length);

            System.out.println(librarySpace[i]);

            if (librarySpace[i].equals(libraryItem)) {
                /*if (librarySpace[i].getISBN() == libraryItem.getISBN()) {*/
                System.out.println(i);

                for (int j = i; j < displayItems().size(); j++) {
                    if (j == 0) {
                        librarySpace[i] = null;
                    }
                    librarySpace[j] = librarySpace[j + 1];
                }

                libraryItems.remove(i);


            }


            }

        System.out.println("after removal" + displayItems());
        System.out.println("after removal" + dvdShelf);
        System.out.println("after removal" + shelf);

        System.out.println("Available free space" + (librarySpace.length - count));
    }

    public LibraryItem findLibraryItem(int isbn) { //finding the library item based on the isbn
        LibraryItem item = null;

        for (int i = 0; i < displayItems().size(); i++) {
            System.out.println("i:" + i + ":::" + displayItems().get(i).getISBN());
            if (displayItems().get(i).getISBN() == isbn) {

                item = displayItems().get(i);
                System.out.println("borrow" + item);
            }

        }

        return item;
    }

    /**
     * Generating a report of over due items and displaying it on descending order
     *
     * @return report
     * @throws SQLException
     */
    @Override
    public List<LibraryItem> generateReport() throws SQLException {
        List<LibraryItem> reportC = new ArrayList<>();
        System.out.println("display items" + displayItems());
        Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql1 = "SELECT  isbn,readerId,returned_date FROM `borrowitem` ";

        ResultSet rs = statement.executeQuery(sql1);
        while (rs.next()) {
            int isbn = rs.getInt("isbn");
            String rId = rs.getString("readerId");
            System.out.println(isbn);
            String date = rs.getString("returned_date");
            if (rs.wasNull()) {
                date = "";
            }
            LibraryItem item = findLibraryItem(isbn);

            if (date.equalsIgnoreCase("")) { //calculates only if return period is null

                int returnperiod = new DateModule(LocalDate.now().toString()).calcDate() - item.getBorrowed().calcDate();
                //calculating return period
                System.out.println(returnperiod);
                double cost = cost(item, returnperiod);
                item.setDueCost(cost); //setting the due cost to that item.
                item.setCurrentReader(findReader(rId)); //finfing the reader
                reportC.add(item);


            }
        }
        reportC.sort(new MySort()); //sorting it

        System.out.println("reportC" + reportC);
        return reportC;
    }


    @Override
    public LibraryItem searchItemByTitle(String title) throws LibraryManagerException {


        System.out.println("title" + title);
        for (LibraryItem item : displayItems()) {
            if (!(item == null)) {
                if (item.getTheTitle().equalsIgnoreCase(title)) {
                    return item;
                }

            }
        }

        throw new LibraryManagerException(title); //throwing a exception error if title isnt found!


    }

    /**
     * @param item
     * @param reader
     * @throws SQLException Getting all the records of a specific item id and calcualting the average of the difference between
     *                      the date they borrowed and the date they returned and taking it as a average days the book has been kept
     *                      using that giving a date it will be available.
     *                      <p>
     *                      if its been reserved by a person ,
     *                      dateAvg+borrowed date+the date the reserved person assumed
     */
    @Override
    public void reserve(LibraryItem item, Reader reader, int noOfDay) throws SQLException {
        int availableDate = 0;
        int days = noOfDay;
        Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql3 = "SELECT AVG(DATEDIFF(`returned_date`, `borrowed_date`)) AS DateAVg  from borrowitem where isbn=" +
                item.getISBN() + ";";
        ResultSet resultSet = statement.executeQuery(sql3);
        System.out.println("got result");
        if (resultSet.next()) {
            availableDate = resultSet.getInt("DateAVg");
            //days=resultSet.getInt(1);
        }
        if (availableDate == 0) {
            availableDate = 7; //if the book has been never borrowed
        }

        String sql = "SELECT * FROM reserve where isbn='" + item.getISBN() + "';";

        String sq = "INSERT INTO RESERVE( `ReaderId`, `isbn`, `Days`) VALUES('" + reader.getrId() + "'," + item.getISBN()
                + "," + days + ")";
        int count = 0;

        ResultSet res = statement.executeQuery("SELECT COUNT(*) FROM RESERVE where isbn='" + item.getISBN() + "';");
        while (res.next()) {
            count = res.getInt(1);
        }
        System.out.println(count + "count");

        if (count == 0) {
            System.out.println("enter");
            System.out.println("Available from" + item.getBorrowed().getCurDate());
            System.out.println("Available date" + availableDate);
            LocalDate date = LocalDate.parse(item.getBorrowed().getDateC()).plusDays(availableDate);
            System.out.println("date");
            date.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String avDate = date.toString();
            connection.createStatement().execute(sq);
            available = "The book will be available on " + date.toString();
            System.out.println(avDate);
        } else {

            ResultSet rs = statement.executeQuery(sql);
            System.out.println("enter else");
            int day = 0;
            while (rs.next()) {
                day = +rs.getInt(4);
            }
            System.out.println(day);
            availableDate = availableDate + day;
            System.out.println(item.getBorrowed().getDateC());
            LocalDate date = LocalDate.parse(item.getBorrowed().getDateC()).plusDays(availableDate);
            date.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String avDate = date.toString();
            System.out.println(avDate);


            available = "The book will be available on " + avDate;

        }

        connection.close();
    }

    public Reader findReader(String id) throws SQLException {
        Reader reader1 = null;
      /*  Connection connection = ConnectionClass.getConnection();
        Statement statement = connection.createStatement();
        String query="SELECT * From reader where id='"+id+"';";
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next())
        {
            readerList.add(new Reader(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
            resultSet.getString(4)));
        }*/
        for (int x = 0; x < readerList.size(); x++) {
            if (id.equals(readerList.get(x).getrId())) {
                reader1 = readerList.get(x);
                System.out.println(reader1);
            }
        }
        return reader1;
    }

    protected void lang(List<Language> languages, String subs) {
        switch (subs) { //getting the enum value of the string obtained from database.

            case "ENGLISH":
                languages.add(Language.ENGLISH);
                break;
            case "DUTCH":
                languages.add(Language.DUTCH);
                break;
            case "SINHALA":
                languages.add(Language.SINHALA);
                break;
            case "TAMIL":
                System.out.println("tamil");
                languages.add(Language.TAMIL);
                break;
            case "FRENCH":
                System.out.println("fr");
                languages.add(Language.FRENCH);
                break;

        }
    }
}

class MySort implements Comparator<LibraryItem> { //implementing comparator class

    @Override
    public int compare(LibraryItem o1, LibraryItem o2) {
        if (o1.getDueCost() - o2.getDueCost() > 0) { //comparing value with due cost of objects.
            return -1;
        } else {
            return +1;
        }
    }
}
