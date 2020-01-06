package myhbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.regionserver.BloomType;


import myhbase.connect;

public class creatmytable {
	public static void main(String[] args)throws IOException{
		connect.getconnect();
		createtable();
		
	}
	public static void createtable() throws IOException{
		//设定tableName
		TableName tableName = TableName.valueOf("music");
		//创建admin对象
		Admin admin = connect.connection.getAdmin();
		//判断表是否存在；如果存在，禁用、删除
		if (admin.tableExists(tableName)) {
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			System.err.println(tableName.toString()+"表已存在，正在删除……");
		} 
		//创建desc对象，用来描述表信息
		HTableDescriptor desc = new HTableDescriptor(tableName);
		//创建colDesc对象，用来描述列族信息
		HColumnDescriptor colDesc = new HColumnDescriptor("songs");
		colDesc.setBloomFilterType(BloomType.ROWCOL);
		desc.addFamily(colDesc);
		desc.addFamily(new HColumnDescriptor("singers"));
		admin.createTable(desc);
		System.err.println(tableName.toString()+"表创建成功");
		admin.close();
		System.err.println("关闭连接！");
	}
	
}