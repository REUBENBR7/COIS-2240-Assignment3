import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private static List<String> transactionLog = new ArrayList<>();

    // Perform the borrowing of a book
    public static boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book);
            logTransaction("Borrowing: " + member.getName() + " borrowed " + book.getTitle());
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public static void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            logTransaction("Returning: " + member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Log a transaction
    private static void logTransaction(String details) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        transactionLog.add(timestamp + " - " + details);
        System.out.println(details);
    }

    // Display transaction history
    public static void displayTransactionHistory() {  
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("--- Transaction History ---");
            for (String transaction : transactionLog) {
                System.out.println(transaction);
            }
        }
    }
}
