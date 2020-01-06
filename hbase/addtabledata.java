package myhbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import myhbase.connect;

public class addtabledata {
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		addData();
	}
	public static void addData() throws IOException{
		//描述表名
		HTable table = (HTable)connect.connection.getTable(TableName.valueOf("music"));
		//设置缓存大小
		table.setWriteBufferSize(6*1024);
		//关闭自动写入，设置为手动写入
		table.setAutoFlush(false);
		//rowkey
		Put put = new Put(Bytes.toBytes("001"));
		//写请求
		put.setDurability(Durability.SKIP_WAL);
		put.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("name"), Bytes.toBytes("animals"));
		put.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("CD"), Bytes.toBytes("V"));
		put.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("lyr"), Bytes.toBytes("adam"));
		put.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("wr"), Bytes.toBytes("bn.blk"));
		put.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("stly"), Bytes.toBytes("pop"));
		put.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("sname"), Bytes.toBytes("Maroon5"));
		put.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("place"), Bytes.toBytes("US"));
		put.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("company"), Bytes.toBytes("free"));
		
		//写入
		table.put(put);
		//持久化
		table.flushCommits();
		System.out.println("信息录入完成");
		
		Put put2 = new Put("002".getBytes());
		put2.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("name"), Bytes.toBytes("初雪"));
		put2.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("CD"), Bytes.toBytes("十二月的奇迹"));
		put2.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("lyr"), Bytes.toBytes("Kenzie"));
		put2.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("wr"), Bytes.toBytes("Kenzie"));
		put2.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("stly"), Bytes.toBytes("kpop"));
		put2.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("sname"), Bytes.toBytes("EXO"));
		put2.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("place"), Bytes.toBytes("Korea"));
		put2.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("company"), Bytes.toBytes("SM"));
		table.put(put2);
		table.flushCommits();
		
		Put put3 = new Put("003".getBytes());
		put3.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("name"), Bytes.toBytes("给你们"));
		put3.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("CD"), Bytes.toBytes("雨一直下"));
		put3.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("lyr"), Bytes.toBytes("张宇"));
		put3.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("wr"), Bytes.toBytes("十一郎"));
		put3.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("stly"), Bytes.toBytes("pop"));
		put3.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("sname"), Bytes.toBytes("张宇"));
		put3.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("place"), Bytes.toBytes("China"));
		put3.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("company"), Bytes.toBytes("EMI"));

		Put put4 = new Put("004".getBytes());
		put4.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("name"), Bytes.toBytes("lemon"));
		put4.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("CD"), Bytes.toBytes("lemon"));
		put4.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("lyr"), Bytes.toBytes("米津玄师"));
		put4.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("wr"), Bytes.toBytes("米津玄师"));
		put4.addColumn(Bytes.toBytes("songs"), Bytes.toBytes("stly"), Bytes.toBytes("jpop"));
		put4.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("sname"), Bytes.toBytes("米津玄师"));
		put4.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("place"), Bytes.toBytes("Japan"));
		put4.addColumn(Bytes.toBytes("singers"), Bytes.toBytes("company"), Bytes.toBytes("Sony Music Records"));
		//创建链表
		List<Put> putList = new ArrayList<Put>();
		//将put3、put4加入链表
		putList.add(put3);
		putList.add(put4);
		//将链表内容写入
		table.put(putList);
		//持久化
		table.flushCommits();
		
		table.close();
	}
}