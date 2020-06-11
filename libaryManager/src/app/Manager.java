package app;

import java.util.List;
import java.util.Scanner;

public class Manager {
    private Scanner scan;
    private boolean isExit = false;
    private BookList bookList;

    public Manager(Scanner scan) {
        this.scan = scan;
        bookList = new BookList();
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Manager manager = new Manager(scan);
        System.out.println("Welcome to the online book library");
        printLine();

        while (!manager.isExit) {
            manager.showMenu();
        }

        scan.close();
    }

    public static void printLine() {
        System.out.println("______________________________");
    }

    public void showMenu() {
        System.out.println("1. Enter a new book");
        System.out.println("2. Search a book by book title");
        System.out.println("3. Display books");
        System.out.println("4. Borrow a book by book id");
        System.out.println("5. Exit");
        System.out.print("Your choice: ");
        int menuItemSelected = scan.nextInt();
        scan.nextLine();
        
        switch (menuItemSelected) {
            case 1:
                enterNewBook();
                break;
            case 2:
                searchBook();
                break;
            case 3:
                bookList.displayTable();
                break;
            case 4:
                borrowBookById();
                break;
            case 5:
                isExit = true;
                break;
            default:
                break;
        }

        printLine();
    }

    // mượn 1 quyển sách bằng cách nhập Id của sách
    private void borrowBookById() {
        System.out.print("Enter Id of book: ");
        String id = scan.nextLine().trim();
        String message = bookList.Borrowe(id);
        System.out.println(message);
    }
        
    // tìm kiếm book bởi title của book
    private void searchBook() {
        System.out.print("Enter title of book: ");
        String title = scan.nextLine();
        List<Book> books = bookList.searchBookByTitle(title);
        if(books == null || books.size() == 0){
            System.out.println("No book is found");
        }else{
            bookList.displayTable(books);
        }
    }
    
    public void enterNewBook() {
        Book book = createBook();
        try{
            if (book != null && bookList.add(book)) {
                System.out.println("A new book has been added");
                return;
            }
        }catch(IllegalArgumentException e){
            System.out.println("======================");
            System.out.println(e.getMessage());
        }
        System.out.println("Book not added!");
    }

    private Book createBook() {
        try {
            System.out.println("Enter information for the new book:");
            System.out.print("ID: ");
            String id = scan.nextLine().trim().toUpperCase();

            System.out.print("Title: ");
            String title = scan.nextLine().trim();

            System.out.print("Author: ");
            String author = scan.nextLine().trim();

            System.out.print("Is borrowed (1 = yes, 0 = no): ");
            int choice = scan.nextInt();
            scan.nextLine();
            boolean isBorrowed = choice == 1 ? true : false;
            Book book = new Book(id, title, author, isBorrowed);
            return book;
        } catch (Exception e) {
            System.out.println("has a Erro!");
        }
        return null;
    }
}