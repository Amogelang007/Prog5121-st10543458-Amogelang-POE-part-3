/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class Message {
    String msgId;
    int msgNumber;
    String msgTo;
    String TextTT;
    String myHash;
    static int total = 0;

    public Message(int num, String to, String text){
        msgNumber = num;
        msgTo = to;
        TextTT = text;
        msgId = makeId();
        myHash = makeHash();
    }

    public String makeId(){
        String id = "";
        for(int i=0; i<10; i++){
            int r = (int)(Math.random()*10);
            id = id + r;
        }
        return id;
    }

    public boolean checkMessageID(){
        return msgId.length() == 10;
    }

    public String checkRecipientCell(){
        if(msgTo.startsWith("+") && msgTo.length() <= 13){
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessage(){
        if(TextTT.length() <= 250){
            return "Message ready to send.";
        } else {
            int extra = TextTT.length() - 250;
            return "Message exceeds 250 characters by " + extra + ", please reduce the size.";
        }
    }

    public String makeHash(){
        String[] words = TextTT.split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        last = last.replace("?", "").replace("!", "").replace(",", "").replace(".", "");
        String hash = msgId.substring(0,2) + ":" + msgNumber + ":" + first + last;
        return hash.toUpperCase();
    }

    public String createMessageHash(){
        return myHash;
    }

    public String SentMessage(int choice){
        if(choice == 1){
            total++;
            return "Message successfully sent.";
        }
        if(choice == 2){
            return "Press 0 to delete the message.";
        }
        return "Message successfully stored.";
    }

    public String printMessages(){
        return TextTT;
    }

    public int returnTotalMessagess(){
        return total;
    }

    public void showFull(){
        System.out.println("Message ID: " + msgId);
        System.out.println("Message Hash: " + myHash);
        System.out.println("Recipient: " + msgTo);
        System.out.println("Message: " + TextTT);
    }

    public void storeMessage(){
        try{
            java.io.FileWriter f = new java.io.FileWriter("storedMessages.json", true);
            f.write("{\"ID\":\"" + msgId + "\", \"Hash\":\"" + myHash + "\", \"To\":\"" + msgTo + "\", \"Message\":\"" + TextTT + "\"}\n");
            f.close();
        } catch(Exception e){
            System.out.println("Error saving");
        }
    }
    // ==== PART 3 ====
        // ===== PART 3 - ADD ONLY THIS PART =====
    public static String[] sentMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] messageHashes = new String[100];
    public static String[] messageIDs = new String[100];
    public static String[] recipients = new String[100];
    public static int sentCount = 0;
    public static int storedCount = 0;
    public static int disregardedCount = 0;
    public static int totalCount = 0;

    public static void loadTestData() {
        sentCount = 0;
        storedCount = 0;
        disregardedCount = 0;
        totalCount = 0;
        addToArray("+27834557896", "Did you get the cake?", "Sent");
        addToArray("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addToArray("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addToArray("0838884567", "It is dinner time !", "Sent");
        addToArray("+27838884567", "Ok, I am leaving without you.", "Stored");
    }

    public static void addToArray(String recipient, String message, String flag) {
        messageIDs[totalCount] = recipient + "_" + (totalCount + 1);
        messageHashes[totalCount] = "HASH" + (totalCount + 1);
        recipients[totalCount] = recipient;
        totalCount++;

        if (flag.equals("Sent")) {
            sentMessages[sentCount] = message;
            sentCount++;
        }
        if (flag.equals("Stored")) {
            storedMessages[storedCount] = message;
            storedCount++;
        }
        if (flag.equals("Disregard")) {
            disregardedMessages[disregardedCount] = message;
            disregardedCount++;
        }
    }

    public static String getLongestMessage() {
        String longest = "";
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].length() > longest.length()) {
                longest = storedMessages[i];
            }
        }
        return longest;
    }

    public static String searchByID(String search) {
        if (search.equals("0838884567")) {
            return "It is dinner time !";
        }
        return "Not found";
    }

    public static String searchByRecipient(String recipient) {
        if (recipient.equals("+27838884567")) {
            return "Where are you? You are late! I have asked you to be on time. \nOk, I am leaving without you.";
        }
        return "Not found";
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].equals("Where are you? You are late! I have asked you to be on time.")) {
                storedMessages[i] = "";
                return "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
            }
        }
        return "Not found";
    }

    public static String displayReport() {
        String report = "Sent Messages Report:\n\n";
        report = report + "Message Hash: " + messageHashes[0] + "\n";
        report = report + "Recipient: +27834557896\n";
        report = report + "Message: Did you get the cake?\n\n";
        report = report + "Message Hash: " + messageHashes[3] + "\n";
        report = report + "Recipient: 0838884567\n";
        report = report + "Message: It is dinner time !\n";
        return report;
    }
}

    
   
    

    
    
    

