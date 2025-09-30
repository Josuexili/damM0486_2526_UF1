import java.io.Serializable;

public class UserLogin implements Serializable {
    public String username;
    public String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
