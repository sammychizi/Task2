package ConsumeAPI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Fact {

    private static HttpURLConnection connection;

    public JSONObject object() throws Exception{

        //Method1 java.net.hhtp url connection
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            //open the connection
            URL url = new URL("https://catfact.ninja/fact");
            connection= (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status >299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
             }
            else{
                reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            connection.disconnect();
        }

        Object obj = new JSONParser().parse(String.valueOf(responseContent));
        JSONObject jo = (JSONObject) obj;

        return jo;

    }

}