package packageName;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String MANAGER = "omar@gmail.com_123.txt";
    Scanner sc = new Scanner(System.in);

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String start() {
        String input;
        sc = new Scanner(System.in);
        while (true) {
            PrintMessage.startMessage();
            input = sc.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3")) {
                return input;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public void signUp() throws IOException {
        String[] msg;
        while (true) {
            msg = PrintMessage.signUp();
            if (validateEmailAndPassword(msg[0], msg[1])) {
                String fileName = (msg[0] + "_" + msg[1] + ".txt");
                File f = new File(fileName);
                if (f.exists()) {
                    System.out.println("Account already exists");
                    break;
                } else {
                    boolean ch = f.createNewFile();
                    if (ch) System.out.println("Account created");
                    break;
                }
            } else {
                System.out.println("invalid Email or Password");
            }
        }
    }

    public String[] login() throws IOException {
        String[] msg;
        int i = 0;
        while (true) {
            msg = PrintMessage.login();
            if (validateEmailAndPassword(msg[0], msg[1])) {
                String fileName = (msg[0] + "_" + msg[1] + ".txt");
                File f = new File(fileName);
                if (f.exists()) {
                    return msg;
                } else {
                    System.out.println("incorrect Email or Password");
                    i++;
                    if (i == 2) break;
                }
            } else {
                System.out.println("invalid Email or Password");
            }
        }
        return null;
    }

    public Boolean validateEmailAndPassword(String email, String password) {
        boolean valid = true;
        if (!email.contains("@") || !email.contains(".")) {
            valid = false;
        }
        if (password.length() < 3 || password.length() > 16) {
            valid = false;
        }
        return valid;
    }

    public void printBook(Book book) {
        System.out.println("ID: " + book.getId() + ", " + "Title: " + book.getTitle() + ", "
                + "Author: " + book.getAuthor() + ", " + "Topic: " + book.getTopic() + ", " + "Year: " + book.getYear());
    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);

        String[] msg = null;

        while (true) {
            String input = main.start();
            if (input.equals("1")) {
                msg = main.login();
            } else if (input.equals("2")) {
                main.signUp();
                msg = main.login();
            } else {
                sc.close();
                return;
            }
            if (msg != null) break;
        }
        String fileName = (msg[0] + "_" + msg[1] + ".txt");
        if (fileName.equals(MANAGER)) {
            ArrayList<Book> books = HandleFiles.readFile(fileName);
            String input;
            while (true) {
                PrintMessage.showMainMessageManager(msg[0]);
                input = sc.nextLine();
                if (input.equals("1")) {
                    if(books.isEmpty()){
                        System.out.println("No books found");
                    }
                    else{
                        for (Book book : books) {
                            main.printBook(book);
                        }
                    }
                }
                else if (input.equals("2")) {
                    while (true) {
                        PrintMessage.showSearchMessage();
                        input = sc.nextLine();
                        if (input.equals("1")) {
                            String id;
                            while (true) {
                                System.out.print("Enter ID: ");
                                id = sc.nextLine();
                                if (isNumeric(id)) break;
                                else {
                                    System.out.println("Invalid ID");
                                }
                            }
                            Book book = Searching.searchById(books, Integer.parseInt(id));
                            if (book == null) {
                                System.out.println("Book not found");
                            } else {
                                main.printBook(book);
                            }
                            break;
                        }
                        if (input.equals("2")) {
                            System.out.print("Enter Title: ");
                            String title = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTitle(books, title);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Book not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("3")) {
                            System.out.print("Enter Author: ");
                            String author = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByAuthor(books, author);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("4")) {
                            System.out.print("Enter topic: ");
                            String topic = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTopic(books, topic);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("5")) {
                            String year;
                            while (true) {
                                System.out.print("Enter year: ");
                                year = sc.nextLine();
                                if (isNumeric(year) && Integer.parseInt(year) > 1900 && Integer.parseInt(year) <= 2025)
                                    break;
                                else
                                    System.out.println("Invalid year");
                            }
                            ArrayList<Book> searchedBooks = Searching.searchByYear(books, Integer.parseInt(year));
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
                else if (input.equals("3")) {
                    int id = books.getLast().getId() + 1;
                    String title;
                    while(true){
                        System.out.print("Enter Title: ");
                        title = sc.nextLine();
                        if(title.isEmpty()){
                            System.out.println("Title is empty");
                        }
                        else{
                            break;
                        }
                    }
                    String author;
                    while(true){
                        System.out.print("Enter Author: ");
                        author = sc.nextLine();
                        if(author.isEmpty()){
                            System.out.println("Author is empty");
                        }
                        else{
                            break;
                        }
                    }
                    String topic;
                    while(true){
                        System.out.print("Enter Topic: ");
                        topic = sc.nextLine();
                        if(topic.isEmpty()){
                            System.out.println("Topic is empty");
                        }
                        else{
                            break;
                        }
                    }
                    String strYear;
                    while (true) {
                        System.out.print("Enter Year: ");
                        strYear = sc.nextLine();
                        if (isNumeric(strYear) && Integer.parseInt(strYear) > 1900 && Integer.parseInt(strYear) <= 2025) {
                            break;
                        }
                    }
                    int year = Integer.parseInt(strYear);
                    books.add(new Book(id, title, author, topic, year));
                    System.out.println("Book added");
                }
                else if (input.equals("4")) {
                    String id;
                    while (true) {
                        System.out.print("Enter ID: ");
                        id = sc.nextLine();
                        if (isNumeric(id)) break;
                        else {
                            System.out.println("Invalid ID");
                        }
                    }
                    int idx = Searching.searchForId(books, Integer.parseInt(id));
                    if (idx == -1) {
                        System.out.println("Book not found");
                    } else {
                        books.remove(idx);
                        System.out.println("Book removed");
                    }
                }
                else if(input.equals("-1")) {
                    break;
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            HandleFiles.writeFile(MANAGER, books);
        }
        else{
            ArrayList<Book> managerBooks = HandleFiles.readFile(MANAGER);
            ArrayList<Book> userBooks = HandleFiles.readFile(fileName);
            String input;
            while (true) {
                PrintMessage.showMainMessageUser(msg[0]);
                input = sc.nextLine();
                if (input.equals("1")) {
                    if(managerBooks.isEmpty()){
                        System.out.println("No books found");
                    }
                    else{
                        for (Book book : managerBooks) {
                            main.printBook(book);
                        }
                    }
                }
                else if (input.equals("2")) {
                    if(userBooks.isEmpty()){
                        System.out.println("No books found");
                    }
                    else{
                        for (Book book : userBooks) {
                            main.printBook(book);
                        }
                    }
                }
                else if (input.equals("3")) {
                    while (true) {
                        PrintMessage.showSearchMessage();
                        input = sc.nextLine();
                        if (input.equals("1")) {
                            String id;
                            while (true) {
                                System.out.print("Enter ID: ");
                                id = sc.nextLine();
                                if (isNumeric(id)) break;
                                else {
                                    System.out.println("Invalid ID");
                                }
                            }
                            Book book = Searching.searchById(managerBooks, Integer.parseInt(id));
                            if (book == null) {
                                System.out.println("Book not found");
                            } else {
                                main.printBook(book);
                            }
                            break;
                        }
                        if (input.equals("2")) {
                            System.out.print("Enter Title: ");
                            String title = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTitle(managerBooks, title);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Book not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("3")) {
                            System.out.print("Enter Author: ");
                            String author = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByAuthor(managerBooks, author);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("4")) {
                            System.out.print("Enter topic: ");
                            String topic = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTopic(managerBooks, topic);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("5")) {
                            String year;
                            while (true) {
                                System.out.print("Enter year: ");
                                year = sc.nextLine();
                                if (isNumeric(year) && Integer.parseInt(year) > 1900 && Integer.parseInt(year) <= 2025)
                                    break;
                                else
                                    System.out.println("Invalid year");
                            }
                            ArrayList<Book> searchedBooks = Searching.searchByYear(managerBooks, Integer.parseInt(year));
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
                else if (input.equals("4")) {
                    while (true) {
                        PrintMessage.showSearchMessage();
                        input = sc.nextLine();
                        if (input.equals("1")) {
                            String id;
                            while (true) {
                                System.out.print("Enter ID: ");
                                id = sc.nextLine();
                                if (isNumeric(id)) break;
                                else {
                                    System.out.println("Invalid ID");
                                }
                            }
                            Book book = Searching.searchById(userBooks, Integer.parseInt(id));
                            if (book == null) {
                                System.out.println("Book not found");
                            } else {
                                main.printBook(book);
                            }
                            break;
                        }
                        if (input.equals("2")) {
                            System.out.print("Enter Title: ");
                            String title = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTitle(userBooks, title);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Book not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("3")) {
                            System.out.print("Enter Author: ");
                            String author = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByAuthor(userBooks, author);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("4")) {
                            System.out.print("Enter topic: ");
                            String topic = sc.nextLine();
                            ArrayList<Book> searchedBooks = Searching.searchByTopic(userBooks, topic);
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        }
                        if (input.equals("5")) {
                            String year;
                            while (true) {
                                System.out.print("Enter year: ");
                                year = sc.nextLine();
                                if (isNumeric(year) && Integer.parseInt(year) > 1900 && Integer.parseInt(year) <= 2025)
                                    break;
                                else
                                    System.out.println("Invalid year");
                            }
                            ArrayList<Book> searchedBooks = Searching.searchByYear(userBooks, Integer.parseInt(year));
                            if (searchedBooks.isEmpty()) {
                                System.out.println("Books not found");
                            } else {
                                for (Book book : searchedBooks) {
                                    main.printBook(book);
                                }
                            }
                            break;
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
                else if(input.equals("5")) {
                    String id;
                    while (true) {
                        System.out.print("Enter ID: ");
                        id = sc.nextLine();
                        if (isNumeric(id)) break;
                        else {
                            System.out.println("Invalid ID");
                        }
                    }
                    int idx = Searching.searchForId(userBooks, Integer.parseInt(id));
                    if (idx == -1) {
                        idx = Searching.searchForId(managerBooks, Integer.parseInt(id));
                        if(idx == -1){
                            System.out.println("Book not found");
                        }
                        else{
                            userBooks.add(managerBooks.get(idx));
                            System.out.println("Book added");
                        }
                    } else {
                        System.out.println("Book already exists in your books");
                    }
                }
                else if (input.equals("6")) {
                    String id;
                    while (true) {
                        System.out.print("Enter ID: ");
                        id = sc.nextLine();
                        if (isNumeric(id)) break;
                        else {
                            System.out.println("Invalid ID");
                        }
                    }
                    int idx = Searching.searchForId(userBooks, Integer.parseInt(id));
                    if (idx == -1) {
                        System.out.println("Book not found");
                    } else {
                        userBooks.remove(idx);
                        System.out.println("Book removed");
                    }
                }
                else{
                    break;
                }

            }

        }
        sc.close();
    }

}