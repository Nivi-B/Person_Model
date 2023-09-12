import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

// Nivedha Balasubramanian
// IT 2045C
// Practicum 01 Java File and IO review

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personTestData.txt");

        boolean done = false;

        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;

        do {
                ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits] ");
                firstName = SafeInput.getNonZeroLenString(in, "Enter the First Name ");
                lastName = SafeInput.getNonZeroLenString(in, "Enter the Last Name ");
                title = SafeInput.getNonZeroLenString(in, "Enter the Title ");
                YOB = SafeInput.getRangedInt(in,"Enter the year of birth ",1000,9999);

                personRec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
                folks.add(personRec);

                done = SafeInput.getYNConfirm(in, "Are you done? ");
        } while (!done);

        for(String p: folks)
            System.out.println(p);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}