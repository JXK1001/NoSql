package myhbase;

import java.io.IOException;
import java.util.Collection;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;

import myhbase.connect;

public class describemytable {
	public static void main(String[] args)throws IOException {
		connect.getconnect();
		describetable();
	}
	public static void describetable() throws IOException{
		Admin admin = connect.connection.getAdmin();
		TableName tableName = TableName.valueOf("music");
		HTableDescriptor desc = admin.getTableDescriptor(tableName);
		System.out.println("表信息(infomation)加载中……");
		System.out.println("表名：" + desc.getNameAsString());
		System.out.println("resign size最大值：" + desc.getMaxFileSize());
		System.out.println("缓存冲洗大小：" + desc.getMemStoreFlushSize());
		System.out.println("副本数：" + desc.getRegionReplication());
		System.out.println("拆分策略：" + desc.getRegionSplitPolicyClassName());
		System.out.println("刷新策略：" + desc.getFlushPolicyClassName());
		Collection<HColumnDescriptor>families = desc.getFamilies();
		System.out.println("");
		for (HColumnDescriptor result : families) {
			System.out.println("列族名：" + result.getNameAsString());
			System.out.println("boolm过滤器：" + result.getBloomFilterType());
			System.out.println("块大小：" + result.getBlocksize());
			System.out.println("最多版本数" + result.getMaxVersions());
			System.out.println("最少版本数" + result.getMinVersions());
			
		}
		admin.close();
		
	}
	
	
}
