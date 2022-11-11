package lapr.project.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


/**
 * Creates an instance of the Test class using information from a CSV file
 * @author renan
 */
public class CSVReader {

    public String[] createShipFromFile(String fileName, int i){
        try(FileInputStream fileIn = new FileInputStream(fileName)) {
            Scanner sc = new Scanner(fileIn);

            for(int k=0; k<i+1; k++)
                sc.nextLine();

            if(sc.hasNext()) {
                String testLine = sc.nextLine();
                return testLine.split(",");
            }
            else
                return null;

        } catch(IOException e) {
            Utils.print("File not found");
            return null;
        }
    }

}
