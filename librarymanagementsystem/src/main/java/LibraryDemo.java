public class LibraryDemo {
    public static void main(String[] args) {
        // Create library
        Library library = new Library();

        System.out.println("=== LIBRARY MANAGEMENT SYSTEM DEMO ===\n");

        // Create and add books (demonstrating overloaded constructors)
        Book book1 = new Book("978-0-13-468599-1", "Effective Java", "Joshua Bloch");
        Book book2 = new Book("978-0-596-00976-6", "Head First Design Patterns", "Eric Freeman", true);
        Book book3 = new Book("978-0-134-68599-2", "Clean Code", "Robert Martin");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println();

        // Register members
        Member member1 = new Member("M001", "Alice Johnson");
        Member member2 = new Member("M002", "Bob Smith");

        library.registerMember(member1);
        library.registerMember(member2);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("INITIAL LIBRARY STATE");
        System.out.println("=".repeat(50));
        System.out.println(library);

        // Perform lending operations
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LENDING OPERATIONS");
        System.out.println("=".repeat(50) + "\n");

        // Successful loan 1
        library.lendBook("M001", "978-0-13-468599-1");
        System.out.println();

        // Successful loan 2
        library.lendBook("M002", "978-0-596-00976-6");
        System.out.println();

        // Successful loan 3 (same member, different book)
        library.lendBook("M001", "978-0-134-68599-2");
        System.out.println();

        // REJECTED loan - book already on loan
        System.out.println("--- Attempting to lend already borrowed book ---");
        library.lendBook("M002", "978-0-13-468599-1");
        System.out.println();

        System.out.println("=".repeat(50));
        System.out.println("LIBRARY STATE AFTER LENDING");
        System.out.println("=".repeat(50));
        System.out.println(library);

        // Perform return operations
        System.out.println("\n" + "=".repeat(50));
        System.out.println("RETURN OPERATIONS");
        System.out.println("=".repeat(50) + "\n");

        // Return a book
        library.returnBook("978-0-13-468599-1");
        System.out.println();

        // Now the previously rejected loan should succeed
        System.out.println("--- Attempting to lend previously unavailable book ---");
        library.lendBook("M002", "978-0-13-468599-1");
        System.out.println();

        System.out.println("=".repeat(50));
        System.out.println("FINAL LIBRARY STATE");
        System.out.println("=".repeat(50));
        System.out.println(library);

        // Demonstrate search functionality
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SEARCH DEMONSTRATION");
        System.out.println("=".repeat(50) + "\n");
        System.out.println("Searching for books with 'Java' in title:");
        for (Book book : library.searchBookByTitle("Java")) {
            System.out.println("  - " + book.getTitle() + " by " + book.getAuthor());
        }
    }
}


