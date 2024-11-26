import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTextField bookTitleField;
    private JTextField searchField;
    private JTextArea bookListArea;
    private ArrayList<String> bookList;

    public LibraryManagementSystem() {
        bookList = new ArrayList<>();
        
        bookList.add("The Lord of the Rings");
        bookList.add("Harry Potter");
        bookList.add("To Kill a Mockingbird");
        bookList.add("The Little Red Book");
        bookList.add("Jungle Book");
        bookList.add("A fine Balance");

        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        JLabel bookTitleLabel = new JLabel("Book Title:");
        northPanel.add(bookTitleLabel);

        bookTitleField = new JTextField(20);
        northPanel.add(bookTitleField);

        JButton addButton = new JButton("Add Book");
        northPanel.add(addButton);

        JLabel searchLabel = new JLabel("Search:");
        northPanel.add(searchLabel);

        searchField = new JTextField(20);
        northPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        northPanel.add(searchButton);

        frame.add(northPanel, BorderLayout.NORTH);

        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookListArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookTitle = bookTitleField.getText();
                if (!bookTitle.isEmpty()) {
                    bookList.add(bookTitle);
                    updateBookList();
                    bookTitleField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a book title.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action Listener for Search Button
        searchButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText().toLowerCase();
                if (!searchQuery.isEmpty()) {
                    searchBook(searchQuery);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a search term.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBookList();  // Initialize the book list display
        frame.setVisible(true);
    }

    private void updateBookList() {
        bookListArea.setText("");
        for (String book : bookList) {
            bookListArea.append(book + "\n");
        }
    }

    private void searchBook(String query) {
        bookListArea.setText("");
        for (String book : bookList) {
            if (book.toLowerCase().contains(query)) {
                bookListArea.append(book + "\n");
            }
        }
        if (bookListArea.getText().isEmpty()) {
            bookListArea.setText("No books found matching the search criteria.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}
