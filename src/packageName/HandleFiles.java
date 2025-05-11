package packageName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleFiles {
    static ArrayList<Book> readFile(String filename) {
        ArrayList<Book> books = new ArrayList<>();
        try{
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                String topic = parts[3];
                int year = Integer.parseInt(parts[4]);
                books.add(new Book(id, title, author, topic, year));
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return books;
    }
    static void writeFile(String filename, ArrayList<Book> books) throws IOException {
        if(books.isEmpty()) {
            return;
        }
        try{
            File file = new File(filename);
            FileWriter myWriter = new FileWriter(file);
            for(Book book : books) {
                myWriter.write(book.getId()+","+book.getTitle()+","+book.getAuthor()+","
                        +book.getTopic()+","+book.getYear()+"\n");
            }
            myWriter.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
