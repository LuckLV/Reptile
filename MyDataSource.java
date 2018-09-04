package db;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class MyDataSource {
	
	public static DataSource getDataSource(String connectURI) {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("llh=1222.0");
		ds.setUrl(connectURI);
		
		return ds;		
	}
	
}
