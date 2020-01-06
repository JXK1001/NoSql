package myhbase;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import myhbase.connect;

public class gettabledata {
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		getData();
	}
	public static void getData() throws IOException{
		Table table = connect.connection.getTable(TableName.valueOf("music"));
		Get get = new Get(Bytes.toBytes("100"));
		Result result = table.get(get);
		for(Cell cell:result.rawCells()){
			System.out.println(new String(CellUtil.getCellKeyAsString(cell))+":"
					+new String(CellUtil.cloneFamily(cell))+":"
					+new String(CellUtil.cloneQualifier(cell))+":"
					+new String(CellUtil.cloneRow(cell))+":"
					+new String(CellUtil.cloneValue(cell)));
		}
		table.close();
	}
	
	
}
