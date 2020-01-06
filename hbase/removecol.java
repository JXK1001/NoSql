package myhbase;
import java.io.IOException;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.util.Bytes;

import myhbase.connect;
import myhbase.scantabledata;
public class removecol {
	
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		removeCol();
		scantabledata.scanData();
	}
	
	public static void removeCol() throws IOException{
		Admin admin= connect.connection.getAdmin();
		TableName tableName = TableName.valueOf("music");
		/*HTableDescriptor des= admin.getTableDescriptor(TableName.valueOf("music"));
		try {
			des.removeFamily(Bytes.toBytes("name"));
			System.out.println("列已删除……");
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		admin.disableTable(tableName);
		System.out.println("表已禁用……");
		admin.deleteColumn(tableName, Bytes.toBytes("aaa"));
		System.out.println("列族已删除……");
		admin.enableTable(tableName);
		System.out.println("表已恢复使用……");
		admin.close();
	}
}
