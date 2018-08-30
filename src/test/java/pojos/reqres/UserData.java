package pojos.reqres;

public class UserData {
    public User data;

    public UserData(){}

    public UserData(User data) {
        this.data = data;
    }

    public User getUserData() {
        return data;
    }

    public void setUserData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "data=" + data +
                '}';
    }
}
