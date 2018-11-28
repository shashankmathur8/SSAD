package sm.SSAD;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.boot.json.BasicJsonParser;
import java.util.List;
import java.util.Map;

public class DataBaseInt {
    RestTemplate restTemplate = new RestTemplate();

    private static DataBaseInt instance = null;
    public static DataBaseInt getInstance(){
        if(instance == null){
            instance = new DataBaseInt();

        }
        return instance;
    }

    public DataBaseInt(){

    }

    List getDevices(String Uname){

        final String uri = "https://api.mlab.com/api/1/databases/"+Uname+"/collections?apiKey="+StaticData.getAPIkey();


        List  result = restTemplate.getForObject(uri, List.class);


        return result;
    }
    String find(String Url,String sort){
        String newHm=restTemplate.getForObject(Url, String.class,sort);
        return newHm;
    }



}
