// Nivedha Balasubramanian
// IT 2045C
// Practicum 01 Java File and IO review


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class PersonReader {
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        String Title1 = "ID#";
        String Title2 = "Firstname";
        String Title3 = "Lastname";
        String Title4 = "Title";
        String Title5 = "YOB";
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try
        {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();

                inFile = new Scanner(target);

                int idWidth = 10;
                int firstNameWidth = 15;
                int lastNameWidth = 15;
                int titleWidth = 10;
                int yobWidth = 10;

                String header = String.format("%-" + idWidth + "s %-" + firstNameWidth + "s %-" + lastNameWidth + "s %-" + titleWidth + "s %-" + yobWidth + "s",
                        "ID#", "Firstname", "Lastname", "Title", "YOB");

                String lineSeparator = "=".repeat(header.length());

                // Print the table header with horizontal lines
                System.out.println(header);
                System.out.println(lineSeparator);

                while(inFile.hasNextLine())
                {
                    line = inFile.nextLine();
                    String[] columns = line.split(",\\s*");

                    System.out.println(String.format("%-" + idWidth + "s %-" + firstNameWidth + "s %-" + lastNameWidth + "s %-" + titleWidth + "s %-" + yobWidth + "s",
                            columns[0], columns[1], columns[2], columns[3], columns[4]));

                }

                inFile.close();
            }
            else
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}
