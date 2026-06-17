import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Register a member
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    // Lend a book to a member
    public boolean lendBook(String memberId, String isbn) {
        // Find the member
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Error: Member with ID " + memberId + " not found.");
            return false;
        }

        // Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return false;
        }

        // Check if book is available
        if (!book.isAvailable()) {
            System.out.println("Error: Book '" + book.getTitle() + "' is already on loan and not available.");
            return false;
        }

        // Create loan (14 days loan period)
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(14);
        Loan loan = new Loan(member, book, borrowDate, dueDate);

        // Update book availability
        book.setAvailable(false);

        // Add loan to library and member
        loans.add(loan);
        member.addLoan(loan);

        System.out.println("Success: Book '" + book.getTitle() + "' lent to " + member.getName() + 
                           ". Due date: " + dueDate);
        return true;
    }

    // Return a book
    public boolean returnBook(String isbn) {
        // Find the book
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Error: Book with ISBN " + isbn + " not found.");
            return false;
        }

        // Check if book is on loan
        if (book.isAvailable()) {
            System.out.println("Error: Book '" + book.getTitle() + "' is not currently on loan.");
            return false;
        }

        // Find the active loan for this book
        Loan activeLoan = null;
        for (Loan loan : loans) {
            if (loan.getBook().equals(book)) {
                activeLoan = loan;
                break;
            }
        }

        if (activeLoan == null) {
            System.out.println("Error: No active loan found for this book.");
            return false;
        }

        // Update book availability
        book.setAvailable(true);

        // Remove loan from member and library
        activeLoan.getMember().removeLoan(activeLoan);
        loans.remove(activeLoan);

        System.out.println("Success: Book '" + book.getTitle() + "' returned by " + 
                           activeLoan.getMember().getName());
        return true;
    }

    // Search for books by title
    public List<Book> searchBookByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    // Helper method to find member by ID
    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    // Helper method to find book by ISBN
    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Getters
    public List<Book> getAllBooks() {
        return books;
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public List<Loan> getAllLoans() {
        return loans;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== LIBRARY STATUS ==========\n");
        sb.append("Total Books: ").append(books.size()).append("\n");
        sb.append("Total Members: ").append(members.size()).append("\n");
        sb.append("Active Loans: ").append(loans.size()).append("\n\n");

        sb.append("--- BOOKS ---\n");
        for (Book book : books) {
            sb.append(book).append("\n");
        }

        sb.append("\n--- MEMBERS ---\n");
        for (Member member : members) {
            sb.append(member).append("\n");
        }

        sb.append("\n--- ACTIVE LOANS ---\n");
        if (loans.isEmpty()) {
            sb.append("No active loans.\n");
        } else {
            for (Loan loan : loans) {
                sb.append(loan).append("\n");
            }
        }
        sb.append("====================================\n");

        return sb.toString();
    }
}


