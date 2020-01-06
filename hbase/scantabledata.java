package myhbase;
import java.io.IOException;


import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

import com.google.common.primitives.Bytes;

import myhbase.connect;
public class scantabledata {
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		scanData();
	}
	public static void scanData() throws IOException{
		Table table= connect.connection.getTable(TableName.valueOf("music"));
		Scan scan= new Scan();
		ResultScanner results= table.getScanner(scan);
		for(Result result:results){
			for(Cell cell:result.rawCells()){
				System.out.println(new String(CellUtil.cloneRow(cell))+":"
						+new String(CellUtil.cloneFamily(cell))+":"
						+new String(CellUtil.cloneQualifier(cell))+":"
						+new String(CellUtil.cloneValue(cell))+":"
						+cell.getTimestamp());
			}
		}
	/*
		//查询指定的列

		//构建一个空的Scan实例

		Scan scan2 = new Scan();

		//添加要查询的列

		Scanfamily.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(“name”),);

		//获取一个扫描器迭代访问所有的行

		ResultScanner scanner1 = table.getScanner(scan2);

		//循环打印查询结构中的所有行数据

		for (Result result : scan2) {

		System.err.println("Scan:" + result); }

		scan.close();

		System.err.println("SUCCESS");

		}

		*/
		table.close();
	}
	
}
