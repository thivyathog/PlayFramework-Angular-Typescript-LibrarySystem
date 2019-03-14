package controllers;

import Module.*;
import com.fasterxml.jackson.databind.JsonNode;
import connectivity.ConnectionClass;

import play.libs.Json;
import play.mvc.Result;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class LibraryController {
    private WestminsterLibraryManagerSingleton library = WestminsterLibraryManagerSingleton.getInstance();
    //singleton object ,westminister can be instantiated once.

    public Result readerList() {

        return ok(Json.toJson(library.westminsterLibraryManager.readerList));
    }


    public Result reserve() throws SQLException {
        JsonNode borrower = Json.parse(request().body().asText());
        int isbn = Integer.parseInt(borrower.get("isbn").textValue());
        String readerId = borrower.get("readerId").textValue();

        int days = Integer.parseInt(borrower.get("pDate").textValue());
        LibraryItem item = library.westminsterLibraryManager.findLibraryItem(isbn);
        System.out.println("entered");

        Reader reader1 = library.westminsterLibraryManager.findReader(readerId);
        library.westminsterLibraryManager.reserve(item, reader1, days);
        return created(library.westminsterLibraryManager.available);
    }

    public Result availability() {
        return ok(Json.toJson(library.westminsterLibraryManager.freeSlots()));
    }

    public Result reportGenerate() throws SQLException {
        System.out.println("working");

        return ok(Json.toJson(library.westminsterLibraryManager.generateReport()));
    }

    public Result deleteItem(Integer id) {
        return ok();
    }

    public Result del() {

        return created("hiisbn");
    }


    public Result searchTitle() throws LibraryManagerException {

        String title = request().body().asText();
        LibraryItem libraryItem = null;

        try {

            libraryItem = library.westminsterLibraryManager.searchItemByTitle(title);
        } catch (LibraryManagerException e) {

            System.out.println("The book with the title " + //catching the error
                    e.getTitle() + " isnt available in our library.westminsterLibraryManager.");
            e.printStackTrace();
        }
        // LibraryItem libraryItem=library.westminsterLibraryManager.searchItemByTitle(title);
        List<LibraryItem> list = new ArrayList<>();
        list.add(libraryItem);
        return ok(Json.toJson(list));
    }

    public Result getItem() {

        return created("sucess!");
    }

    public Result returnItem() throws SQLException {
        JsonNode borrower = Json.parse(request().body().asText());
        int isbn = Integer.parseInt(borrower.get("isbn").textValue());

        String returnDate = borrower.get("pDate").textValue();

        LibraryItem item = library.westminsterLibraryManager.findLibraryItem(isbn);
        if (item != null) {
            String[] dateSplit = returnDate.split("T");
            System.out.println(dateSplit[0]);
            System.out.println(dateSplit[1]);
            String[] furtherSplit = dateSplit[1].split(":");
            library.westminsterLibraryManager.returnItem(item, item.getBorrowed(),
                    new DateModule(Integer.parseInt(furtherSplit[0]), Integer.parseInt(furtherSplit[1]), dateSplit[0]));
        }else{
            library.westminsterLibraryManager.available="LIBRARY ITEM WITH THIS ISBN IS NOT AVAILABLE! ";

        }

        return created(String.valueOf(library.westminsterLibraryManager.fee));
    }

    public Result lang() {

        JsonNode lang = Json.toJson(Language.values());

        return ok(lang);
    }

    public Result borrow() throws SQLException {

        JsonNode borrower = Json.parse(request().body().asText());
        int isbn = Integer.parseInt(borrower.get("isbn").textValue());
        String readerId = borrower.get("readerId").textValue();
        String publicationDate = borrower.get("pDate").textValue();
        System.out.println(isbn + "i");

        LibraryItem item = library.westminsterLibraryManager.findLibraryItem(isbn);

        if (item != null) {
            Reader reader1 = library.westminsterLibraryManager.findReader(readerId);
            System.out.println(reader1 + "reader1");
            String[] dateSplit = publicationDate.split("T");
            System.out.println(dateSplit[0]);
            System.out.println(dateSplit[1]);
            String[] furtherSplit = dateSplit[1].split(":");
            System.out.println("borrowitem" + item);
            library.westminsterLibraryManager.borrowItem(item, reader1, new DateModule(Integer.parseInt(furtherSplit[0])
                    , Integer.parseInt(furtherSplit[1]), dateSplit[0]));

        }else{
            library.westminsterLibraryManager.available="LIBRARY ITEM WITH THIS ISBN IS NOT AVAILABLE! ";
        }
        return created(library.westminsterLibraryManager.available);
    }


    public Result createReader() throws SQLException {
        JsonNode reader = Json.parse(request().body().asText());
        String name = reader.get("name").textValue();
        String readerId = reader.get("readerId").textValue();

        String email = reader.get("email").textValue();
        String contactNo = reader.get("contactNo").textValue();
        //library.westminsterLibraryManager.readerList.add(new Reader(readerId,name,contactNo,email));
        JsonNode json = Json.toJson(library.westminsterLibraryManager.readerList);

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();


        String sql = "INSERT INTO reader VALUES(" + readerId + ",'" + name + "','" + contactNo + "','" + email + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);


        library.westminsterLibraryManager.readerList.add(new Reader(readerId, name, contactNo, email));

        return created("Sucessfully Reader Added!");
    }


    public Result getDVD(){


        JsonNode work = Json.toJson(library.westminsterLibraryManager.dvdList());

        return ok(work);
    }


    public Result library()  {

        JsonNode work = Json.toJson(library.westminsterLibraryManager.bookList());
        return ok(work);
    }

    public Result book() {
        JsonNode book = Json.parse(request().body().asText());
        int isbn = Integer.parseInt(book.get("isbn").textValue());
        String title = book.get("title").textValue();

        String sector = book.get("sector").textValue();
        String publicationDate = book.get("pDate").textValue();
        String author1 = book.get("author1").textValue();
        String author2 = book.get("author2").textValue();
        String publisher = book.get("Publisher").textValue();
        int noOfpages = Integer.parseInt(book.get("noOfpages").textValue());
        String[] d = publicationDate.split("T");
        System.out.println(d[0]);
        System.out.println(d[1]);

        List<Author> authors = new ArrayList<>();
        if (!(author2.equalsIgnoreCase(""))) {
            authors.add(new Author(author1));
            authors.add(new Author(author2));
        } else {
            authors.add(new Author(author1));
        }
        int check = (library.westminsterLibraryManager.checkIsbn(new Book(isbn, title, sector, new DateModule(d[0]),
                "Book", authors, publisher, noOfpages)));
        System.out.println("return value" + check);
        if (check == 0) {
            library.westminsterLibraryManager.addItem(new Book(isbn, title, sector, new DateModule(d[0]), "Book",
                    authors, publisher, noOfpages));
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            LocalDate localDate = LocalDate.parse(d[0]);

            String sql = "INSERT INTO libraryitem VALUES(" + isbn + ",'" + title + "','" + sector + "','" + localDate +
                    "',null,null,'" + "Book" + "','" + authors + "','" + publisher + "','" + noOfpages + "',null,null," +
                    "null,null)";

            Statement statement = null;
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // LibraryItem.db().save(new Book(isbn,title,sector,new DateModule(publicationDate),"Book",authors,publisher,noOfpages));
            // item=library.westminsterLibraryManager.displayItems();
        } else {
            library.westminsterLibraryManager.available = "ISBN IS ALREADY TAKEN!";
        }

        return created(library.westminsterLibraryManager.available);
        // return created("sucess"+book.get("title").textValue());
        // return ok(Json.toJson(library.westminsterLibraryManager.displayItems()));

    }

    public Result dvd() throws SQLException {

        List<Language> subtitles = new ArrayList<>();
        List<Language> language = new ArrayList<>();
        JsonNode book = Json.parse(request().body().asText());
        int isbn = Integer.parseInt(book.get("isbn").textValue());
        String title = book.get("title").textValue();

        String sector = book.get("sector").textValue();
        String publicationDate = book.get("pDate").textValue();
        String actors = book.get("Actors").textValue();
        String subs = book.get("subz").textValue();
        String sub2 = book.get("sub2").textValue();
        String langs = book.get("Lang").textValue();
        String langs2 = book.get("aLang2").textValue();
        String producer = book.get("producer").textValue();
        String type = book.get("type").textValue();
        if (!(sub2.equalsIgnoreCase(""))) {
            library.westminsterLibraryManager.lang(subtitles, sub2);
            library.westminsterLibraryManager.lang(subtitles, subs);


        } else {
            library.westminsterLibraryManager.lang(subtitles, subs);
        }

        if (!(langs2.equalsIgnoreCase(""))) {
            library.westminsterLibraryManager.lang(language, langs2);
            library.westminsterLibraryManager.lang(language, langs);
        } else {
            library.westminsterLibraryManager.lang(language, langs);
        }
        //checking if the isbn is unique
        int check = (library.westminsterLibraryManager.checkIsbn(new DVD(isbn, title, sector,
                new DateModule(publicationDate), "DVD", language, subtitles, producer, actors)));

        if (check == 0) { //if its zero the isbn number is unique
            library.westminsterLibraryManager.addItem(new DVD(isbn, title, sector, new DateModule(publicationDate),
                    "DVD", language, subtitles, producer, actors));
            //  item=library.westminsterLibraryManager.displayItems();
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            LocalDate localDate = LocalDate.parse(publicationDate);

            String sql = "INSERT INTO libraryitem VALUES(" + isbn + ",'" + title + "','" + sector + "','" + localDate +
                    "',null,null,'" + "DVD" + "',null,null,null,'" + subtitles.toString() + "','" +
                    language.toString() + "','" + actors + "','" + producer + "')";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } else {
            library.westminsterLibraryManager.available = "ISBN IS ALREADY TAKEN!";
        }
        return (created(library.westminsterLibraryManager.available));

    }


    public Result work() throws SQLException { //deleting from the library.

        int isbn = Integer.parseInt(request().body().asText());
        LibraryItem item = library.westminsterLibraryManager.findLibraryItem(isbn);
        System.out.println(item);

            library.westminsterLibraryManager.deleteItem(item);


            Connection connectionClass = ConnectionClass.getConnection();
            Statement st = connectionClass.createStatement();
            String sql = "DELETE FROM libraryitem WHERE isbn=" + isbn + ";";
            st.execute(sql);
            // return created(ok);

            return created(item.getType());

    }
}
