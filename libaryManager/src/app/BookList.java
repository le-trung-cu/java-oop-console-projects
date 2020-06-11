package app;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private static List<Book> _books;

    public BookList() {

        // khởi tạo dữ liệu cho ứng dụng
        if(_books == null){
            _books = initialBookData();
        }
    }

    public static List<Book> initialBookData() {
        List<Book> booksList = new ArrayList<Book>();
        booksList.add(new Book("HW1", "The cooker", "Tim", false));
        booksList.add(new Book("HW2", "The Timer", "Steven", true));
        booksList.add(new Book("HW3", "Travel to world", "Job", false));
        booksList.add(new Book("HW4", "Food cooker", "Shen", false));
        booksList.add(new Book("HW5", "See the world", "Timony", false));
        return booksList;
    }

    public boolean add(Book book) throws IllegalArgumentException {
        // kiểm tra id của book có tồn tại chưa
        for (Book b : _books) {
            if (b.getId().equals(book.getId())) {
                throw new IllegalArgumentException("ERRO:  id of the book already exists!");
            }
        }

        return _books.add(book);
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : _books) {
            if (book.getTitle().toUpperCase().contains(title.toUpperCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // public Book searchBookById(String id) {
    //     for (Book book : _books) {
    //         if (book.getId().equals(id.toUpperCase())) {
    //             return book;
    //         }
    //     }
    //     return null;
    // }

    public String Borrowe(String id) {
        Book bookSearh = null;
        String message = "";
        for (Book book : _books) {
            if (book.getId().equals(id.toUpperCase())) {
                bookSearh = book;
                break;
            }
        }
        if (bookSearh == null) {
            message = "There is no this book!";
        }else {
            if (bookSearh.isBorrowed()) {
                message = "You can not brrow this book. The book has been brrowed";
            } else {
                bookSearh.setBorrowed(true);
                message = "You have successfully borrow the book: " + bookSearh.getTitle();
            }
        }
        return message;
    }

    public void displayTable() {
        displayTable(_books);
    }

    public void displayTable(List<Book> books) {

        System.out.println(String.format(Book.formatTable, "ID", "Title", "Author", "Is borrowes"));

        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
}