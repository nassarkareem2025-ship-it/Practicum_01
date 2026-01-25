import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        String ID = "";
        String Name = "";
        String Desc = "";
        Double Cost = 0.0;

        final int FIELDS_LENGTH = 4;

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
                while (reader.ready()) {
                    String rec = reader.readLine();
                    lines.add(rec);
                    line++;
                    System.out.printf("\nLine %4d %-60s", line, rec);
                }
                reader.close();

                System.out.println("\n\nData file read!");

                String[] fields;

                System.out.printf("%-8s%-20s%-25s%s",
                        "ID", "Name", "Description", "Cost");
                System.out.println("\n==============================================================");

                for (String l : lines) {
                    fields = l.split(",");

                    if (fields.length == FIELDS_LENGTH) {
                        ID = fields[0].trim();
                        Name = fields[1].trim();
                        Desc = fields[2].trim();
                        Cost = Double.parseDouble(fields[3].trim());

                        System.out.printf("\n%-8s%-20s%-25s%.2f",
                                ID, Name, Desc, Cost);
                    } else {
                        System.out.println("\nFound a record that may be corrupt:");
                        System.out.println(l);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
