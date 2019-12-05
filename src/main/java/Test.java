import DatabaseManager.DatabaseManager;
import Entities.Student;
import Entities.UndergraduateStudent;

public class Test {

    public static void main(String[] args) {
        Student test = new UndergraduateStudent("Yernur","Alimkhanov","qwerty","email");
        DatabaseManager db = new DatabaseManager();
        db.add(test);
    }
}
