/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.JOptionPane;
import org.json.*;
/**
 *
 * @author Shashank
 */
public class MakeRequest {
     private static HttpURLConnection con;
     private static Data DataAccess = new Data();
    static int makePostRequest(String actionPerformed,String Uname,List<DBObject> finalInsert){
        String url = "https://127.0.0.1/Api/Users/Data";
        String urlParameters = "uname="+Uname+"&AccessToken="+DataAccess.getAccessToken();
        byte[] postData = finalInsert.toString().getBytes(StandardCharsets.UTF_8);
        int postDataLength=postData.length;
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            con.setUseCaches( false );
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }catch(Exception e){
                    }

            StringBuffer content = null;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuffer();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }catch(Exception e){
                    }
            if(content!=null){
            JSONObject jsonObj = new JSONObject(content.toString());
            int resp =Integer.parseInt(jsonObj.getString("n"));
            return resp;
            }
            

        }catch( Exception e){
        } finally {
            
            con.disconnect();
        }   
        
        
    return 0;
    
    
    }
    static int makeGetRequest(String URL){
    
        
        String url = URL;
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             
            con.setRequestProperty( "charset", "utf-8");
            con.setUseCaches( false );
            
            String HashToCompare = con.getHeaderField("Hash");
           JSONObject findOb= MakeRequest.Find(DataAccess.getUname(), HashToCompare);
           if(findOb.getString("Exist").equals("true")){
           JOptionPane.showMessageDialog(Dashboard.panel, "The File Already Exist at "+findOb.getString("location"));
           }else{
               
           }
            
            

        }catch( Exception e){
        } finally {
            
            con.disconnect();
        }   
        
        
    return 0;
    
    
    }
    static int makeGetRequest(String actionPerformed,String uname,String password){
    
        
        String url = "https://127.0.0.1/Api/Users/"+uname;
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
             
            con.setRequestProperty( "charset", "utf-8");
            con.setUseCaches( false );

            StringBuffer content = null;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuffer();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }catch(Exception e){
                    }

            JSONObject jsonObj = new JSONObject(content.toString());
            String PasswordForLogin =jsonObj.getString("password");
            if(PasswordForLogin.equals(password)){
             String AccessToken =jsonObj.getString("access_token");
            DataAccess.setAccessToken(AccessToken);
            DataAccess.setUname(uname.toUpperCase());
            return 1;

            }else{
                return 0;

            }
            

        }catch( Exception e){
        } finally {
            
            con.disconnect();
        }   
        
        
    return 0;
    
    
    }
    static JSONObject Find(String Uname,String Hash) throws Exception{
                    String url = "https://127.0.0.1/API/Users/Data/Find";
                    String urlParameters = "uname="+DataAccess.getUname()+"&access_token="+DataAccess.getAccessToken()+"&db=Files_SD&collection="+DataAccess.getUname()+"&hash="+Hash;
                   
                    
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                 JSONObject list= new JSONObject(response.toString());
                 
		//print result
		System.out.println(response.toString());
                
    
    return list;
    }
    
    
}
