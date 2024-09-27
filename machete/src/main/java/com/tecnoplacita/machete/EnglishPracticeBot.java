package com.tecnoplacita.machete;

import java.util.Scanner;

public class EnglishPracticeBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the English Practice Bot!");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Goodbye! Keep practicing!");
                break;
            }

            String response = getResponse(input);
            System.out.println("Bot: " + response);
        }

        scanner.close();
    }

    private static String getResponse(String input) {
        // Simple NLP-based processing (placeholder)
        if (input.toLowerCase().contains("hello")) {
            return "Hello! How can I assist you in your English practice?";
        } else if (input.toLowerCase().contains("what is your name")) {
            return "I'm an English practice bot. What's your name?";
        } else if (input.toLowerCase().contains("tell me about technology")) {
            return "Technology is the application of scientific knowledge for practical purposes. Do you want to learn vocabulary related to technology?";
        } else {
            return "I'm not sure how to respond to that. Try asking something else!";
        }
    }
}
