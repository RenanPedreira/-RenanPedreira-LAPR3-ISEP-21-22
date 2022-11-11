package lapr.project.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class Utils {

    /**
     * Method that reads a line from the console
     *
     * @param prompt
     * @return the String read
     */
    public static String readLineFromConsole(String prompt) {
        try {
            Utils.print();
            Utils.print(prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that reads and integer from the console
     *
     * @param prompt
     * @return the integer read
     */
    public static int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    /**
     * Method that verifies the response that the user gave
     *
     * @param message
     * @return true if the answer was "y" and false if it was "n"
     */
    public static boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    /**
     * Method that shows a list of things to be selected
     *
     * @param list
     * @param header
     * @return the object selected
     */
    public static Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    /**
     * Method that shows the index to select
     *
     * @param list
     * @param header
     * @return the index
     */
    public static int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    /**
     * Method that shows a list
     *
     * @param list
     * @param header
     */
    public static void showList(List list, String header) {
        Utils.print(header);

        int index = 0;
        for (Object o : list) {
            index = Math.incrementExact(index);

            Utils.print(String.format("%d. %s", index, o.toString()));
        }
        Utils.print();
        Utils.print("0 - Cancel");
    }

    /**
     * Method that returns an object selected
     *
     * @param list
     * @return an object selected
     */
    public static Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value.equals(0)) {
            return null;
        } else {
            return list.get(Math.decrementExact(value));
        }
    }

    /**
     * Method that returns a certain index selected
     *
     * @param list
     * @return a certain index selected
     */
    public static int selectsIndex(List list) {
        String input;
        Integer value = -2;
        do {

            input = Utils.readLineFromConsole("Type your option: ");

            try {
                if (input != null) {
                    if (!input.matches("^[0-9]+$"))
                        throw new IllegalArgumentException("Wrong input. Please insert option number.");
                    value = Integer.valueOf(input);
                }
            } catch (IllegalArgumentException e) {
                Utils.print("\nERROR: " + e.getMessage());
            }

            if(value < 0 || value > list.size())
                Utils.print("Invalid option. Try again.");


        } while (value < 0 || value > list.size());

        return Math.decrementExact(value);
    }

    /**
     * Method that returns a date without the time
     *
     * @param date
     * @return date without the time
     */
    public static Date getDateWithoutTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date dateWithoutTime = new Date();
        try {
            dateWithoutTime = df.parse(df.format(date));
        } catch (ParseException e) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, e);
        }
        return dateWithoutTime;
    }

    /**
     * Method that converst a list to an array
     *
     * @param list
     * @return array
     */
    public static double[] listToArray(List<Double> list) {
        double[] array = new double[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    /**
     * Method that set a certain time to zero
     *
     * @param cal
     * @return the calendar with the time set
     */
    public static Calendar setTimeToZero(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * Method used to print a blank line
     */
    public static void print() {
        Utils.print("");
    }

    /**
     * Method used to print a certain message
     *
     * @param message
     */
    public static void print(Object message) {
        System.out.println(message);
    }

    public static void printNL(Object message){
        System.out.print(message);
    }

}
