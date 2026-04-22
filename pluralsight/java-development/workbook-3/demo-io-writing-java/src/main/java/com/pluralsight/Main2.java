package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main2 {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("potato.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String numText;
            for (int i = 1; i <= 10; i++) {
                numText = String.format("Counting %d\n", i);
                bufferedWriter.write(numText);
            }

            bufferedWriter.close();

        } catch (Exception ex) {
            System.err.println("An error has occurred!");
        }
    }
}
