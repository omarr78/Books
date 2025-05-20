package packageName;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static final String MANAGER = "omar@gmail.com_123.txt";
    Scanner sc = new Scanner(System.in);


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
    public void signUp(){
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
                    boolean ch = false;
                    try{
                        ch = f.createNewFile();
                    }catch (IOException e){
                        System.out.println("An error occurred.");
                    }
                    if (ch) System.out.println("Account created");
                    break;
                }
            } else {
                System.out.println("invalid Email or Password");
            }
        }
    }
    public String[] login() {
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
    public void showBooks(ArrayList<Book> books){
        if(books.isEmpty()){
            System.out.println("No books found");
        }
        else{
            for (Book book : books) {
                printBook(book);
            }
        }
    }
    public void searchBooks(ArrayList<Book> books){
        String input;
        while (true) {
            PrintMessage.showSearchMessage();
            input = sc.nextLine();
            if (input.equals("1")) {
                Searching.handleSearchingById(books);
                break;
            }
            if (input.equals("2")) {
                Searching.handleSearchingByTitle(books);
                break;
            }
            if (input.equals("3")) {
                Searching.handleSearchingByAuthor(books);
                break;
            }
            if (input.equals("4")) {
                Searching.handleSearchingByTopic(books);
                break;
            }
            if (input.equals("5")) {
                Searching.handleSearchingByYear(books);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    public void deleteBook(ArrayList<Book> books) {
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
    public void addToMyBooks(ArrayList<Book> allBooks,ArrayList<Book> myBooks) {
        String id;
        while (true) {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            if (isNumeric(id)) break;
            else {
                System.out.println("Invalid ID");
            }
        }
        int idx = Searching.searchForId(myBooks, Integer.parseInt(id));
        if (idx == -1) {
            idx = Searching.searchForId(allBooks, Integer.parseInt(id));
            if(idx == -1){
                System.out.println("Book not found");
            }
            else{
                myBooks.add(allBooks.get(idx));
                System.out.println("Book added");
            }
        } else {
            System.out.println("Book already exists in your books");
        }
    }
    public void deleteFromMyBooks(ArrayList<Book> myBooks) {
        String id;
        while (true) {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            if (isNumeric(id)) break;
            else {
                System.out.println("Invalid ID");
            }
        }
        int idx = Searching.searchForId(myBooks, Integer.parseInt(id));
        if (idx == -1) {
            System.out.println("Book not found");
        } else {
            myBooks.remove(idx);
            System.out.println("Book removed");
        }
    }

    public Boolean validateEmailAndPassword(String email, String password) {
        boolean valid = email.contains("@") && email.contains(".");
        if (password.length() < 3 || password.length() > 16) {
            valid = false;
        }
        return valid;
    }
    public static void printBook(Book book) {
        System.out.println("ID: " + book.getId() + ", " + "Title: " + book.getTitle() + ", "
                + "Author: " + book.getAuthor() + ", " + "Topic: " + book.getTopic() + ", " + "Year: " + book.getYear());
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }




    public static void main(String[] args)  {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);


        String[] msg;

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
        if (fileName.equals(MANAGER)) { // Manager Account
            ArrayList<Book> books = HandleFiles.readFile(fileName);
            String input;
            while (true) {
                PrintMessage.showMainMessageManager(msg[0]);
                input = sc.nextLine();
                if (input.equals("1")) { // show all books
                    main.showBooks(books);
                }
                else if (input.equals("2")) { // search book
                    main.searchBooks(books);
                }
                else if (input.equals("3")) { // add new book
                    int id = books.getLast().getId() + 1;
                    books.add(Adding.createBook(id));
                    System.out.println("Book added");
                }
                else if (input.equals("4")) { // delete book
                    main.deleteBook(books);
                }
                else if(input.equals("-1")) { // Exit
                    break;
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            HandleFiles.writeFile(MANAGER, books);
        }
        else{ // user Account
            ArrayList<Book> managerBooks = HandleFiles.readFile(MANAGER);
            ArrayList<Book> userBooks = HandleFiles.readFile(fileName);
            String input;
            while (true) {
                PrintMessage.showMainMessageUser(msg[0]);
                input = sc.nextLine();
                if (input.equals("1")) { // show manager books(All books)
                    main.showBooks(managerBooks);
                }
                else if (input.equals("2")) { // show user books
                    main.showBooks(userBooks);
                }
                else if (input.equals("3")) { // search manager books(All books)
                    main.searchBooks(managerBooks);
                }
                else if (input.equals("4")) { // search user books
                    main.searchBooks(userBooks);
                }
                else if(input.equals("5")) { // Add to my books
                    main.addToMyBooks(managerBooks, userBooks);
                }
                else if (input.equals("6")) { // delete from my books
                    main.deleteFromMyBooks(userBooks);
                }
                else{
                    break;
                }
            }
            HandleFiles.writeFile(fileName, userBooks);
        }

    }

}