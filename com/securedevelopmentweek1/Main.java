package com.securedevelopmentweek1;



import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;            // Import KeyGenerator class to generate encryption keys
import javax.crypto.SecretKey;               // Import SecretKey class to represent the secret key

import java.util.Base64;
import java.util.Scanner;                    // Import Scanner for taking user input from the console




class Encryption {

    // Method to encrypt data using DES
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  // Create a Cipher instance that will use DES encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);  // Initialize the Cipher in encryption mode with the secret key
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF8"));  // Convert the plaintext to bytes and encrypt it
        return Base64.getEncoder().encodeToString(encryptedBytes);  // Encode the encrypted bytes into a Base64 string and return
    }
}

class Decryption {

    // Method to decrypt data using DES
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");  // Create a Cipher instance that will use DES decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);  // Initialize the Cipher in decryption mode with the secret key
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));  // Decode the Base64 string and decrypt it
        return new String(decryptedBytes, "UTF8");  // Convert the decrypted bytes back into a UTF-8 string and return it
    }
}

class Main {

    // Method to generate a DES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");  // Create a KeyGenerator instance for the DES algorithm
        keyGenerator.init(56);  // Initialize the KeyGenerator with a key size of 56 bits, which is the standard for DES
        return keyGenerator.generateKey();  // Generate and return the secret key for DES encryption
    }

    // Method to display a menu and process user input
    public static void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object to take user input
        SecretKey secretKey = generateKey();  // Generate a DES secret key for both encryption and decryption

        while (true) {
            // Display the menu options
            System.out.println("\n====== MENU ======");
            System.out.println("1. Encrypt a message");
            System.out.println("2. Decrypt a message");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();  // Read the user's menu choice
            scanner.nextLine();  // Consume newline left after reading integer input


            switch (choice) {
                case 1:  // Encryption option
                    System.out.print("Enter the plaintext message to encrypt: ");
                    String plainText = scanner.nextLine();  // Get the plaintext message from the user

                    // Start measuring the time for encryption
                    long startEncryptTime = System.nanoTime();
                    String encryptedText = Encryption.encrypt(plainText, secretKey);  // Encrypt the plaintext
                    long endEncryptTime = System.nanoTime();
                    long encryptDuration = (endEncryptTime - startEncryptTime) / 1_000_000;  // Time in milliseconds

                    // Display the encrypted text and the time it took to encrypt
                    System.out.println("Encrypted Text: " + encryptedText);
                    System.out.println("Time taken to encrypt: " + encryptDuration + " ms");
                    break;

                case 2:  // Decryption option
                    System.out.print("Enter the encrypted message to decrypt: ");
                    String encryptedMessage = scanner.nextLine();  // Get the encrypted message from the user

                    // Start measuring the time for decryption
                    long startDecryptTime = System.nanoTime();
                    String decryptedText = Decryption.decrypt(encryptedMessage, secretKey);  // Decrypt the message
                    long endDecryptTime = System.nanoTime();
                    long decryptDuration = (endDecryptTime - startDecryptTime) / 1_000_000;  // Time in milliseconds

                    // Display the decrypted text and the time it took to decrypt
                    System.out.println("Decrypted Text: " + decryptedText);
                    System.out.println("Time taken to decrypt: " + decryptDuration + " ms");
                    break;

                case 3:  // Exit option
                    System.out.println("Exiting the application. Goodbye!");
                    return;  // Exit the menu loop and terminate the application

                default:  // Invalid input
                    System.out.println("Invalid option! Please try again.");
                    break;
            
   
            }
        }
    }



    // Main method to run the program
    public static void main(String[] args) {
        try {
            // Call the menu method to start the application
            menu();
        } catch (Exception e) {
            e.printStackTrace();  // Print any exceptions that occur
        }
    }
}