package packageName;

import java.util.ArrayList;
import java.util.Scanner;

public class Adding {
    private static String getTitle(){
        Scanner sc = new Scanner(System.in);
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
        return title;
    }
    private static String getAuthor(){
        Scanner sc = new Scanner(System.in);
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
        return author;
    }
    private static String getTopic(){
        Scanner sc = new Scanner(System.in);
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
        return topic;
    }
    private static int getYear(){
        Scanner sc = new Scanner(System.in);
        String strYear;
        while (true) {
            System.out.print("Enter Year: ");
            strYear = sc.nextLine();
            if (Main.isNumeric(strYear) && Integer.parseInt(strYear) > 1900
                    && Integer.parseInt(strYear) <= 2025) {
                break;
            }
        }
        return Integer.parseInt(strYear);
    }
    public static Book createBook(int id){
        return new Book(id,getTitle(),getAuthor(),getTopic(),getYear());
    }
}
