/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shashank
 */
public class Data {
    static String accesstoken="";
    static String Uname="";
    String AccessKey="tLK365zH7JyzkOGFGHwRU4imUVpcmRCg";
    String baseURL="https://api.mlab.com/api/1";
    String getAccessToken(){
    return this.accesstoken;
    
    }
    
    void setAccessToken(String ac){
    accesstoken=ac;
    
    }
    
    String getUname(){
    return this.Uname;
    
    }
    
    void setUname(String un){
    Uname=un;
    
    }
    
    String getAccessKey(){
    return this.AccessKey;
    
    }
    String getbaseURL(){
    return this.baseURL;
    
    }
    
    
    
}
