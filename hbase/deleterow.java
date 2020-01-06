package myhbase;
import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import myhbase.connect;
public class deleterow {
	private static Delete delete2;
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		deleteRow();
	}
	public static void deleteRow(){
		HTable table= null;
		try {
			table = (HTable) connect.connection.getTable(TableName.valueOf("music"));
			Delete delete1= new Delete(Bytes.toBytes("001"));
			table.delete(delete1);
			table.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
