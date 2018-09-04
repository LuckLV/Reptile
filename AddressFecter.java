package util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.util.EntityUtils;

import parse.AddressParser;
import model.Address;

public class AddressFecter {
	
	public static List<Address> htmlGet(HttpClient client, String url) throws Exception {
		
		List<Address> AddressInfo = new ArrayList<Address>();
		HttpResponse response = HTTPUtils.getRawHtml(client, url);
		int StatusCode = response.getStatusLine().getStatusCode();//页面请求状态值
		if(StatusCode == 200){
			String entity = EntityUtils.toString(response.getEntity(), "UTF-8");
			GzipDecompressingEntity zipRes = new GzipDecompressingEntity(response.getEntity());
			String s = EntityUtils.toString(zipRes, "gb2312");
			
			AddressInfo = AddressParser.getdata(s);
			EntityUtils.consume(response.getEntity());
		}else{
			EntityUtils.consume(response.getEntity());
		}
		
		return AddressInfo;
		
	}
}
