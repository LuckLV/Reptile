package parse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Address;

public class AddressParser {
	
	public static List<Address> getdata(String entity) throws Exception {
		
		List<Address> addresses = new ArrayList<Address>();
		Document doc = Jsoup.parse(entity);
		
		int sumpages = Integer.parseInt(doc.select("div[class=fanye gray6]").
				select("span[class=txt]").text().replaceAll("\\D", ""));
		Map<String, Integer> keymap = new HashMap<String, Integer>();
		for(int i = 1; i < sumpages; i++){
			String everypageurl = "http://esf.hf.fang.com/house/i3" + i;
			Document document = Jsoup.connect(everypageurl).timeout(50000).userAgent("bbbb").get();
			Elements elements = document.select("dl[id~=D03_?]");
			
			for(Element ele : elements){
				String id = ele.select("dd[class=info rel floatr]")
						.select("p").select("a").attr("href").replaceAll("/chushou/", "").replaceAll(".htm", "");
				
				if(!keymap.containsKey(id)){
					keymap.put(id, 1);
					String url = "http://esf.hf.fang.com/" + ele.select("dd[class=info rel floatr]").select("p").select("a").attr("href");
					String title = ele.select("dd[class=info rel floatr]").select("p[class=title]").select("a").text();
					Date date = new Date();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String craw_time = format.format(date);
					Address address = new Address();
					address.setAddr_id(id);
					address.setAddr_url(url);
					address.setCraw_time(craw_time);
					address.setTitle(title);
					addresses.add(address);
				}
			}
		}
		
		return addresses;
		
	}
	
}
