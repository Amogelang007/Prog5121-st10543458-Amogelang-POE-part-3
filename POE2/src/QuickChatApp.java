/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 * 
 */
import java.util.Scanner;

public class QuickChatApp {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to QuickChat.");
        System.out.print("How many messages you want to send? ");
        int limit = sc.nextInt();
        sc.nextLine();
        int count = 0;
        while(true){
            System.out.println("\n--- My Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages Report");
            System.out.println("4) Quit");
            int menu = sc.nextInt();
            sc.nextLine();
            if(menu==4){
                System.out.println("You sent "+count+" messages. Bye!");
                break;
            }
            if(menu==3) {
                Message.loadTestData();
                System.out.println("Stored Messages:");
                for(int i = 0; i < Message.storedCount; i++) {
                System.out.println(Message.storedMessages[i]);
            }
               continue;
}
            if(menu==2){
                Message.loadTestData();
                System.out.println(Message.displayReport());
                System.out.println("Longest message: " + Message.getLongestMessage());
                System.out.println("Search by recipient: " + Message.searchByRecipient("+27838884567"));
                continue;
            }
            if(menu==1){
                if(count>=limit){
                    System.out.println("You reached limit of "+limit);
                    continue;
                }
                System.out.print("Enter recipient (+27...): ");
                String to = sc.nextLine();
                System.out.print("Enter message: ");
                String text = sc.nextLine();
                Message m = new Message(count, to, text);
                String lenCheck = m.checkMessage();
                System.out.println(lenCheck);
                if(lenCheck.contains("exceeds")) continue;
                System.out.println(m.checkRecipientCell());
                if(m.checkRecipientCell().contains("incorrectly")) continue;
                System.out.println("Message ID: "+m.msgId);
                System.out.println("Message Hash: "+m.createMessageHash());
                System.out.println("1-Send 2-Disregard 3-Store");
                System.out.print("Choose: ");
                int what = sc.nextInt();
                sc.nextLine();
                System.out.println(m.SentMessage(what));
                if(what==1){
                    m.showFull();
                    count++;
                }
                if(what==3){
                    m.storeMessage();
                    System.out.println("Saved to storedMessages.json");
                }
            }
        }
        sc.close();
    }
} 

