import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookstoreManagementSystem {

    // Book class
    public static class Book {
        private String isbn;
        private String title;
        private String author;
        private double price;
        private int stockQuantity;

        public Book(String isbn, String title, String author, double price, int stockQuantity) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        // Getters and setters
        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public int getStockQuantity() { return stockQuantity; }
        public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

        @Override
        public String toString() {
            return String.format("ISBN: %s\tTitle: %s\tAuthor: %s\tPrice: $%.2f\tStock Quantity: %d",
                    isbn, title, author, price, stockQuantity);
        }
    }

    // BookstoreManager class
    public static class BookstoreManager {
        private List<Book> books;

        public BookstoreManager() {
            books = new ArrayList<>();
            addSampleBooks();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public List<Book> getAllBooks() {
            return books;
        }

        public Book getBookByIsbn(String isbn) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
            return null;
        }

        public void updateBook(String isbn, Book updatedBook) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getIsbn().equals(isbn)) {
                    books.set(i, updatedBook);
                    return;
                }
            }
        }

        public void deleteBook(String isbn) {
            books.removeIf(book -> book.getIsbn().equals(isbn));
        }

        // Method to add sample books
        private void addSampleBooks() {
            books.add(new Book("978-0132350884", "Clean Code", "Robert C. Martin", 29.99, 50));
            books.add(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 45.00, 30));
            books.add(new Book("978-0321356680", "Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 42.99, 20));
            books.add(new Book("978-1119039738", "Sales Management. Simplified.", "Mike Weinberg", 18.95, 40));
            books.add(new Book("978-0134494166", "The Challenger Sale", "Matthew Dixon, Brent Adamson", 16.99, 35));
            books.add(new Book("978-1591847984", "To Sell Is Human", "Daniel H. Pink", 14.99, 25));
            books.add(new Book("978-0066620992", "SPIN Selling", "Neil Rackham", 15.95, 45));
            books.add(new Book("978-1524763169", "Principles", "Ray Dalio", 23.00, 50));
            books.add(new Book("978-0590353427", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 10.99, 100));
            books.add(new Book("978-1501173219", "The Silent Patient", "Alex Michaelides", 17.99, 40));
        }
    }

    // BookstoreUI class
    public static class BookstoreUI {
        private Scanner scanner;
        private BookstoreManager manager;

        public BookstoreUI() {
            scanner = new Scanner(System.in);
            manager = new BookstoreManager();
        }

        public void start() {
            while (true) {
                System.out.println("\nBookstore Management System");
                System.out.println("1. Add Book");
                System.out.println("2. View All Books");
                System.out.println("3. Search Book by ISBN");
                System.out.println("4. Update Book");
                System.out.println("5. Delete Book");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        viewAllBooks();
                        break;
                    case 3:
                        searchBookByIsbn();
                        break;
                    case 4:
                        updateBook();
                        break;
                    case 5:
                        deleteBook();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void addBook() {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter Stock Quantity: ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Book book = new Book(isbn, title, author, price, stockQuantity);
            manager.addBook(book);
            System.out.println("Book added successfully.");
        }

        private void viewAllBooks() {
            System.out.println("All Books:");
            for (Book book : manager.getAllBooks()) {
                System.out.println(book);
                System.out.println();
            }
        }

        private void searchBookByIsbn() {
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            Book book = manager.getBookByIsbn(isbn);
            if (book != null) {
                System.out.println(book);
            } else {
                System.out.println("Book not found.");
            }
        }

        private void updateBook() {
            System.out.print("Enter ISBN of the book to update: ");
            String isbn = scanner.nextLine();
            Book existingBook = manager.getBookByIsbn(isbn);
            if (existingBook == null) {
                System.out.println("Book not found.");
                return;
            }

            System.out.print("Enter new Title (leave blank to keep current): ");
            String title = scanner.nextLine();
            System.out.print("Enter new Author (leave blank to keep current): ");
            String author = scanner.nextLine();
            System.out.print("Enter new Price (enter -1 to keep current): ");
            double price = scanner.nextDouble();
            System.out.print("Enter new Stock Quantity (enter -1 to keep current): ");
            int stockQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (!title.isEmpty()) existingBook.setTitle(title);
            if (!author.isEmpty()) existingBook.setAuthor(author);
            if (price != -1) existingBook.setPrice(price);
            if (stockQuantity != -1) existingBook.setStockQuantity(stockQuantity);

            manager.updateBook(isbn, existingBook);
            System.out.println("Book updated successfully.");
        }

        private void deleteBook() {
            System.out.print("Enter ISBN of the book to delete: ");
            String isbn = scanner.nextLine();
            manager.deleteBook(isbn);
            System.out.println("Book deleted successfully.");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new BookstoreUI().start();
    }
}
