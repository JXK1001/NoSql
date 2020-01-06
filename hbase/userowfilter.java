package myhbase;

import java.io.IOException;
import java.util.Scanner;

import javax.ws.rs.core.NewCookie;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

public class userowfilter {
	
	public static void main(String[] args) throws IOException {
		String tableName = "music";;
		String name;
		System.out.println("请输入歌手名：");
		Scanner scanner= new Scanner(System.in);
		name= scanner.nextLine();
		scanName(tableName, name);	
	}
	public static void scanName(String tableName, String name) throws IOException{
		connect.getconnect();
		Table table= connect.connection.getTable(TableName.valueOf(tableName));
		Scan scan= new Scan();
		Filter filter = new SingleColumnValueFilter(Bytes.toBytes("singers"), Bytes.toBytes("sname"), CompareOp.EQUAL, new SubstringComparator(name));
		scan.setFilter(filter);
		ResultScanner resultScanner= table.getScanner(scan);
		for (Result result : resultScanner) {
			for (Cell cell : result.rawCells()) {
				new String(CellUtil.getCellKeyAsString(cell));
				System.out.print(
						new String(CellUtil.cloneValue(cell))+" ");
			}
		}
		
		
	}
	
}