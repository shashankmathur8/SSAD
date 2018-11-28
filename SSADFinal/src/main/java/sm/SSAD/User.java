package sm.SSAD;

public class User {
    private String Uname;
    private String Password;
    User(){}
    User(String Uname,String Password){

        this.Uname=Uname;
        this.Password=Password;

    }
    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {

        return Password;
    }
    @Override
    public String toString() {
        return "Blog{" +
                "Uname=" + Uname +
                ", Password='" + Password + '\'' ;
    }



}
