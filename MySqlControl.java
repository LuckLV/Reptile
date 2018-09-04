package db;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Address;
import model.Contents;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MySqlControl {
	
	static final Log logger = LogFactory.getLog(MySqlControl.class);
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://localhost:3306/soufang");
	static QueryRunner qr = new QueryRunner(ds);
	
	public static <T> List<T> getListInfoBySQL(String sql, Class<T> type) {
		
		List<T> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<T>(type));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;		
	}
	
	public static void executeAddressUpdate(List<Address> addresses) {
		
		Object[][] params = new Object[addresses.size()][4];
		for(int i = 0; i < params.length; i++){
			params[i][0] = addresses.get(i).getAddr_id();
			params[i][1] = addresses.get(i).getTitle();
			params[i][2] = addresses.get(i).getAddr_url();
			params[i][3] = addresses.get(i).getCraw_time();
		}
		
		try {
			qr.batch("insert into soufang_address (id, title,url,craw_time)"
			        + "values (?,?,?,?)", params);
		} catch (Exception e) {
			logger.error(e);
		}	
	}
	
	public static void executeContentInsert(List<Contents> contentinfo) {
		Object[][] params = new Object[contentinfo.size()][18];
		for ( int i=0; i<params.length; i++ ){
            params[i][0] = contentinfo.get(i).getId();
            params[i][1] = contentinfo.get(i).getTitle();
            params[i][2] = contentinfo.get(i).getPublishtime() ;
            params[i][3] = contentinfo.get(i).getPrice();
            params[i][4] = contentinfo.get(i).getHousetype();
            params[i][5] = contentinfo.get(i).getAcreage() ;
            params[i][6] = contentinfo.get(i).getUseacreage();
            params[i][7] = contentinfo.get(i).getYears();
            params[i][8] = contentinfo.get(i).getOrientation() ;
            params[i][9] = contentinfo.get(i).getFloor();
            params[i][10] = contentinfo.get(i).getStructure();
            params[i][11] = contentinfo.get(i).getDecoration() ;
            params[i][12] = contentinfo.get(i).getType();
            params[i][13] = contentinfo.get(i).getBuildingtype();
            params[i][14] = contentinfo.get(i).getPropertyright() ;
            params[i][15] = contentinfo.get(i).getEstate() ;
            params[i][16] = contentinfo.get(i).getSchool();
            params[i][17] = contentinfo.get(i).getFacilities() ;
        }
		
		try {
			qr.batch("insert into soufang_content (id, title,publishtime, price,housetype,acreage,useacreage,years,orientation,floor,structure,decoration,type,buildingtype,propertyright,estate,school,facilities)"
			        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
