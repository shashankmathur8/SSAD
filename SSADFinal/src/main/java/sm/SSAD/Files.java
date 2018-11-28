package sm.SSAD;

public class Files {
    private String Uname;
    Files(String uname,String hash,String time){
        this.Uname=uname;
        this.Time=time;
        this.Hash=hash;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        this.Hash = hash;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    private String Hash;
    private String Time;
}
