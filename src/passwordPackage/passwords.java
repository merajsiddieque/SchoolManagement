package passwordPackage;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

// import User.user;
public class passwords {
    // Declare the HashMap at the class level
    private static HashMap<String, String> passes = ReadStringHashPairs();

//     public static void main(String[] args) {
//         // Example usage of signUp and signin methods
//        signUp("user1", "password");
//     //    signin("user1", "password");
// //        signin("user1", "passwor");
// //        printFileContent();
//     }

    public static boolean signUp(String username, String pass) {
        String filePath = "C:\\Users\\kotha\\OneDrive\\Desktop\\SchoolManagement\\src\\passwordPackage\\passwords_Store.txt";
         if(passes.containsKey(username)) {
         	System.out.println("The username is already taken");
         	return false;
         }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // Append mode
            String hashValue = sha256(pass);
            writer.write(String.format("\"%s\" : \"%s\"", username, hashValue));
//            writer.write("nigaa");
            writer.newLine();
            writer.flush();
            System.out.println("Successfully updated to the system");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
//    public static void printFileContent() {
//        String filePath = "passwords.txt";
//        System.out.println("File path: " + new java.io.File(filePath).getAbsolutePath());
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static boolean signin(String username, String pass) {
        String hash = sha256(pass);
        if (passes.containsKey(username) && passes.get(username).equals(hash)) {
            System.out.println("The login is successful");
            return true; 
        } else {
            System.out.println("The login fails");
            return false; // 
        }
    }
    //hashing
    private static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, String> ReadStringHashPairs() {
        String filePath = "C:\\Users\\kotha\\OneDrive\\Desktop\\SchoolManagement\\src\\passwordPackage\\passwords_Store.txt";
        HashMap<String, String> stringHashPairs = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "");
                    stringHashPairs.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringHashPairs;
    }
    public static void Rewrite() {
        String filePath = "C:\\Users\\kotha\\OneDrive\\Desktop\\SchoolManagement\\src\\passwordPackage\\passwords_Store.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Entry<String, String> entry : passes.entrySet()) {
                writer.write(String.format("\"%s\" : \"%s\"", entry.getKey(), entry.getValue()));
                writer.newLine(); // New line after each key-value pair
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Resets the password for a given user
    public static boolean Reset_password(Scanner sc) {
    	String username,oldPassword;
        System.out.println("Enter the username:");
        sc.nextLine();
         username = sc.nextLine();
        System.out.println("Enter your old password:");
         oldPassword = sc.nextLine();
        
        if (passes.containsKey(username) && passes.get(username).equals(sha256(oldPassword))) {
            passes.remove(username);
            System.out.println("Enter your new password:");
            String newPassword = sc.nextLine();
            passes.put(username, sha256(newPassword));
            Rewrite();  
            return true;
        } else {
            System.out.println("Invalid username or password");
            return false;
        }
    }
}
