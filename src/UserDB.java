import java.util.HashMap;

public class UserDB {
    public static HashMap<String, String> userDB = new HashMap<>();
    public static  void addUser(String username, String password){userDB.put(username, password);}
}
