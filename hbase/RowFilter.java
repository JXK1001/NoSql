package myhbase;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

public class RowFilter {
	public static void main(String[] args)throws IOException {
		connect.getconnect();
		String tableName="music";
		String[] colFamily1={"songs","singers"};
		usefamilyfilter(tableName,"002",colFamily1[1],"sname"); 
}
	public static Configuration getconnect() throws IOException{
		//初始化conf
		Configuration conf = HBaseConfiguration.create();
		//指定hbase持久化目录
		conf.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
		//指定启动zookeeper的服务器列表
		conf.set("hbase.zookeeper.quorum", "localhost");
		//尝试连接并捕捉异常
		return conf;
	} 
	public static void usefamilyfilter(String tableName,String row,String family,String col)throws IOException{
		HTable hTable= new HTable(getconnect(), tableName);
		/*Scan scan=new Scan();
		scan.addColumn(family.getBytes(),col.getBytes());
		FamilyFilter familyFilter= new FamilyFilter(CompareOp.EQUAL,new SubstringComparator(family));
		scan.setFilter(familyFilter);
		ResultScanner resultScanner= hTable.getScanner(family.getBytes());
		for(Result result:resultScanner){
			System.out.println("scan:"+result);
			}*/
		
		
	Get get =new Get(Bytes.toBytes(row));
		get.addColumn(family.getBytes(),col.getBytes());
		FamilyFilter familyFilter2=new FamilyFilter(CompareOp.EQUAL,new SubstringComparator(family));
		get.setFilter(familyFilter2);
		Result result =hTable.get(get);
		System.out.println("get:"+result);
	}
	private static Configuration getConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
