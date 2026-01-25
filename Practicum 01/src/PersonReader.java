import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        String id = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yob = 0;
        final int FIELDS_LENGTH = 5;

        ArrayList<String> lines = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDirectory);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try {
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while(reader.ready())
                {
                    String rec;
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;
                    System.out.printf("\nLine %4d %-60s", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file read!");

                String[] fields;
                System.out.printf("%-8s%-15s%-15s%-6s%s","ID#","Firstname","Lastname","Title","YOB");
                System.out.println("\n==============================================");
                for(String l:lines){
                    fields = l.split(",");

                    if(fields.length == FIELDS_LENGTH){
                        id = fields[0].trim();
                        firstName = fields[1].trim();
                        lastName = fields[2].trim();
                        title = fields[3].trim();
                        yob = Integer.parseInt(fields[4].trim());
                        System.out.printf("\n%-8s%-15s%-15s%-6s%d", id, firstName, lastName, title, yob);
                    }
                    else {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{

        }

    }
}