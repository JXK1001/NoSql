package myhbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;


import myhbase.connect;
public class addfamily {
	public static void main(String[] args) throws IOException {
		connect.getconnect();
		addFamily("music", "aaa");
	}
//�޸ı�ṹ����------���������
public static void addFamily(String tableName,String colFamily) throws IOException {
	Admin admin= connect.connection.getAdmin();
	TableName arg0= TableName.valueOf(tableName);
//��������ʵ��,������ʾ�����汾��
HColumnDescriptor col=new HColumnDescriptor(colFamily);
col.setMaxVersions(3);
//�������ʵ��
admin.addColumn(arg0, col);
//ͨ��HTableDescriptor��ʵ���ȡ���������Ϣ��������Ϣ��
HTableDescriptor htd =admin.getTableDescriptor(arg0);
admin.disableTable(arg0);
//�޸ı���Ϣ
admin.modifyTable(arg0, htd);
admin.enableTable(arg0);
}
private static Object getConfig() {
	// TODO Auto-generated method stub
	return null;
}
}