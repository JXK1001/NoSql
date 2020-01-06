package myhbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;


public class connect {
	//建立conf变量用来描述集群访问路径
	public static Configuration conf;
	//建立connection变量用来建立连接
	public static Connection connection;
	
	public static void main(String[] args)throws IOException{
		getconnect();
	}
	//定义函数
	public static void getconnect() throws IOException{
		//https://www.cnblogs.com/qinersky902/p/6217741.html
		//初始化conf
		conf = HBaseConfiguration.create();
		//指定hbase持久化目录
		conf.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
		//指定启动zookeeper的服务器列表
		conf.set("hbase.zookeeper.quorum", "localhost");
//		conf.set("hbase.zookeeper.property.clientPort", "2181");
//		conf.set("zookeeper.znode.parent", "/hbase");
		//尝试连接并捕捉异常
		try {
			connection = ConnectionFactory.createConnection(conf);
		System.err.println("连接成功……");
		} catch (IOException e) {
			// TODO: handle exception
		}
	} 
}