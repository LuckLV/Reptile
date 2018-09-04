package main;

import java.util.ArrayList;
import java.util.List;

import model.Address;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import db.MySqlControl;
import util.AddressFecter;

public class AddressMain {
	
	public static void main(String[] args) throws Exception {
		HttpClient client = new DefaultHttpClient();
		String _url = "http://esf.hf.fang.com/";
		List<Address> addresses = new ArrayList<Address>();
		
		addresses = AddressFecter.htmlGet(client, _url);
		
		MySqlControl.executeAddressUpdate(addresses);
	}
}
