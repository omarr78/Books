package packageName;

import java.util.ArrayList;

public class Searching {
    static Book searchById(ArrayList<Book> books, int id) {
        for(Book book : books) {
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }
    static int searchForId(ArrayList<Book> books,int id) {
        for(int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    static ArrayList<Book> searchByTitle(ArrayList<Book> books, String title) {
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
    static ArrayList<Book> searchByAuthor(ArrayList<Book> books, String author) {
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
    static ArrayList<Book> searchByTopic(ArrayList<Book> books, String topic) {
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
    static ArrayList<Book> searchByYear(ArrayList<Book> books, int year) {
        ArrayList<Book> ret = new ArrayList<>();
        for(Book book : books) {
            if(book.getYear() == year){
                ret.add(book);
            }
        }
        return ret;
    }
}
