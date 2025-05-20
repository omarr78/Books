package packageName;

import java.util.ArrayList;
import java.util.Scanner;


public class Searching {
    public static Book searchById(ArrayList<Book> books, int id) {
        for(Book book : books) {
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }
    public static int searchForId(ArrayList<Book> books,int id) {
        for(int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    public static void handleSearchingById(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        String id;
        while (true) {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            if (Main.isNumeric(id)) break;
            else {
                System.out.println("Invalid ID");
            }
        }
        Book book = Searching.searchById(books, Integer.parseInt(id));
        if (book == null) {
            System.out.println("Book not found");
        } else {
            Main.printBook(book);
        }
    }
    public static ArrayList<Book> searchByTitle(ArrayList<Book> books, String title) {
        ArrayList<Book> ret = new ArrayList<>();
        for(Book book : books) {
            String b = book.getTitle().toLowerCase();
            title = title.toLowerCase();
            if(b.contains(title)){
                ret.add(book);
            }
        }
        return ret;
    }
    public static void handleSearchingByTitle(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        ArrayList<Book> searchedBooks = Searching.searchByTitle(books, title);
        if (searchedBooks.isEmpty()) {
            System.out.println("Book not found");
        } else {
            for (Book book : searchedBooks) {
                Main.printBook(book);
            }
        }
    }
    public static ArrayList<Book> searchByAuthor(ArrayList<Book> books, String author) {
        ArrayList<Book> ret = new ArrayList<>();
        for(Book book : books) {
            String b = book.getAuthor().toLowerCase();
            author = author.toLowerCase();
            if(b.contains(author)){
                ret.add(book);
            }
        }
        return ret;
    }
    public static void handleSearchingByAuthor(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        ArrayList<Book> searchedBooks = Searching.searchByAuthor(books, author);
        if (searchedBooks.isEmpty()) {
            System.out.println("Books not found");
        } else {
            for (Book book : searchedBooks) {
                Main.printBook(book);
            }
        }
    }
    public static ArrayList<Book> searchByTopic(ArrayList<Book> books, String topic) {
        ArrayList<Book> ret = new ArrayList<>();
        for(Book book : books) {
            String b = book.getTopic().toLowerCase();
            topic = topic.toLowerCase();
            if(b.contains(topic)){
                ret.add(book);
            }
        }
        return ret;
    }
    public static void handleSearchingByTopic(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter topic: ");
        String topic = sc.nextLine();
        ArrayList<Book> searchedBooks = Searching.searchByTopic(books, topic);
        if (searchedBooks.isEmpty()) {
            System.out.println("Books not found");
        } else {
            for (Book book : searchedBooks) {
                Main.printBook(book);
            }
        }
    }
    public static ArrayList<Book> searchByYear(ArrayList<Book> books, int year) {
        ArrayList<Book> ret = new ArrayList<>();
        for(Book book : books) {
            if(book.getYear() == year){
                ret.add(book);
            }
        }
        return ret;
    }
    public static void handleSearchingByYear(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        String year;
        while (true) {
            System.out.print("Enter year: ");
            year = sc.nextLine();
            if (Main.isNumeric(year) && Integer.parseInt(year) > 1900 && Integer.parseInt(year) <= 2025)
                break;
            else
                System.out.println("Invalid year");
        }
        ArrayList<Book> searchedBooks = Searching.searchByYear(books, Integer.parseInt(year));
        if (searchedBooks.isEmpty()) {
            System.out.println("Books not found");
        } else {
            for (Book book : searchedBooks) {
                Main.printBook(book);
            }
        }
    }
}
