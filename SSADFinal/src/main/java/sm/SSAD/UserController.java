package sm.SSAD;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.bson.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    UserMockData UMD= UserMockData.getInstance();
    DataBaseInt DBI= DataBaseInt.getInstance();
    @RequestMapping("/")
    public String index() {
        return "Congratulations from UserController.java";
    }
    @PostMapping("/user/{uname}")
    public HashMap<String, String> ToDefine(@PathVariable String uname, @RequestParam String password){
        User CurrentUser=UMD.getUserByUname(uname,password);
        HashMap<String,String> map1= new HashMap<String,String>();
        map1.put("uname",CurrentUser.getUname());
        map1.put("password",CurrentUser.getPassword());
        map1.put("",StaticData.getAPIkey());
        return map1;
    }

    @PostMapping("/userReg/{uname}/{pass}")
    public HashMap<String,String> Create(@PathVariable String uname, @PathVariable String pass){
        User NewUser=UMD.createUser(uname,pass);
        HashMap<String,String> map1= new HashMap<String,String>();
        map1.put("uname",NewUser.getUname());
        map1.put("password",NewUser.getPassword());
        return map1;
    }
    @PutMapping("/user/update/{uname}")
    public HashMap<String,String> Update(@PathVariable String uname, @RequestParam String password,@RequestParam String newpassword){
        User UpdatedUser=UMD.updateUser(uname,password,newpassword);
        HashMap<String,String> map1= new HashMap<String,String>();
        map1.put("uname",UpdatedUser.getUname());
        map1.put("password",UpdatedUser.getPassword());
        return map1;
    }

    @PostMapping("/devices/{uname}")
    public List getDevices(@PathVariable String uname){
        List response=DBI.getDevices(uname);
        return  response;
    }
    @GetMapping("/{uname}/find/{Device}/{hash}")
    public String find(@PathVariable String uname, @PathVariable String hash,@PathVariable String Device){
        List<String> Devices= DBI.getDevices(uname);
        String response;

        String sort = "{\"hash\":\""+hash+"\"}";

            String url="https://api.mlab.com/api/1/databases/"+uname+"/collections/"+Device+"?q={sort}&fo=true&apiKey="+StaticData.getAPIkey();



            response =DBI.find(url,sort);



        return ""+response;
    }




}