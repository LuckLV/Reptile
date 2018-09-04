package util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;



public class HTTPUtils {
	
	public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
		
		HttpGet getMethod = new HttpGet(personalUrl);
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "ok");
		
		try {
			response = client.execute(getMethod);
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			//e.printStackTrace();
		}
				
		return response;
		
	}	
}
