package sm.SSAD;
import java.util.ArrayList;
public class UserMockData {
    private ArrayList <User>UserAL= new ArrayList<User>();
    private static UserMockData instance = null;
    public static UserMockData getInstance(){
        if(instance == null){
            instance = new UserMockData();
        }
        return instance;
    }

    public UserMockData(){
        UserAL.add(new User("Admin","Admin123"));
        UserAL.add(new User("shashank","Shashank123"));

    }
    public ArrayList<User> fetchUSers(){
        return UserAL;
    }

    public User getUserByUname(String Uname,String Password) {
        for(User b: UserAL) {
            if(b.getUname().equals(Uname) && b.getPassword().equals(Password)) {
                return b;
            }else{
                continue;
            }
        }
        return new User("Incorrect Credentials","Incorrect Credentials");
    }


    public User createUser(String UserName,String Password) {
        User newUser = new User(UserName,Password);
        UserAL.add(newUser);
        return newUser;
    }

    public User updateUser(String Uname,String Password,String NewPassword) {
        for(User b: UserAL) {
            if(b.getUname().equals(Uname)) {
                if(b.getPassword().equals(Password)){
                    int UserIndex = UserAL.indexOf(b);
                    b.setUname(Uname);
                    b.setPassword(NewPassword);
                    UserAL.set(UserIndex, b);
                    return b;
                }else{
                    return new User("Incorrect Password","Incorrect Password");
                }
            }

        }

        return null;
    }
}
