import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.*;
import java.io.*;
public class ClacHash {
static MongoClient client,client2;
static DB db,db2;
static DBCollection collection,collection2;
static DBObject cur;
static DBObject putObj;
static File file;
static Data DataAccess=new Data();
static List<File> filelist=new ArrayList<File>();
static List<DBObject> finalInsert= new ArrayList<DBObject>();
static int count=0;
 ClacHash(List<String> add){
     for(String s: add){     
     filelist.add(new File(s));
     }
 
 }
public static void Generate() throws IOException {
    try{
                client=new MongoClient("localhost");
                db= client.getDB("Files_SD");
                collection = db.getCollection("Files");
                for(File file1: filelist){
                calcHash(file1);
                
                }
                System.out.println(finalInsert);
                collection.insert(finalInsert);
                
                int responce=MakeRequest.makePostRequest("putintoDb",DataAccess.getUname(),finalInsert);
                
    }
    catch(Exception e){
    e.printStackTrace();
    }
    
}
 static void calcHash(File file){
        for (File f : file.listFiles()) {
        if(f.isFile()){
            
            try {
                String fname=f.toString();
                String hash=Hash.generateSHA256(f);
                if(hash.isEmpty()){
                }else{
                
                putObj=new BasicDBObject("hash", hash).append("file", fname).append("id", ++count);
                }
                System.out.println(hash+"v"+f);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

            }

        }else if (f.isDirectory()){
            
            calcHash(f);
            

        }
        if(!finalInsert.contains(putObj)){
        
        finalInsert.add(putObj);
        }
    }

 }
}