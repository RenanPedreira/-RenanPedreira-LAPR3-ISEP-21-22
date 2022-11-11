package lapr.project.ui;


/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    public static void main(String[] args) {

        /*DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance().getDatabaseConnection();
        } catch (IOException exception) {
            System.out.println("Connection failed");
        }

        Connection connection = databaseConnection.getConnection();
        System.out.println("Connected to the database!");*/

        try {
            lapr.project.ui.MainMenuUI menu = new MainMenuUI();

            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

