import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Carlitto on 31.07.2017.
 */
public class TestJDBC {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
                //"useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER = "hbstudent";
        String PASSWORD = "hbstudent";

        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!!!!!");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

}
