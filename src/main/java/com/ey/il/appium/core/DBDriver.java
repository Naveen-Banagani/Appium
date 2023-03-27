package com.ey.il.appium.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDriver {
	
	private static DBDriver sqlDriver = new DBDriver();

	private String strQuery;

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public Object[][] getTestData() throws SQLException{

		Connection conn = null;
		int columnCnt = 0;
		int resultRowCnt =0;
		int rowCnt =0;
		String [][] dataArray = null;

		try {
			String url = "jdbc:sqlite:"+Driver.getAllSettings();
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(getStrQuery());
			columnCnt = rs.getMetaData().getColumnCount();
			resultRowCnt = getRowCount(getStrQuery());
			dataArray = new String[resultRowCnt][columnCnt];
			while(rs.next())
			{
				for(int col=0; col<columnCnt; col++) {
					dataArray[rowCnt][col] = rs.getString(col+1); 
				}
				rowCnt++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return dataArray;
	}

	private int getRowCount(String sqlQuery) throws SQLException{

		int rowCount = 0;
		Connection conn = null;
		String splitQuery[] = sqlQuery.split("FROM");	
		String addQuery = "";
		for(int i=1;i<splitQuery.length;i++) {
			addQuery = addQuery+"FROM"+splitQuery[i];
		}
		String queryBuilder = "SELECT COUNT(*) "+ addQuery.trim();
		try {

			String url = "jdbc:sqlite:"+Driver.getAllSettings();
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryBuilder);
			rs.next();
			rowCount = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return rowCount;
	}

	public void setData(){

		Connection conn = null;
		Statement statement;
		String url = "jdbc:sqlite:"+Driver.getAllSettings();
		try {
			conn = DriverManager.getConnection(url);
			statement = conn.createStatement();
			statement.executeUpdate(getStrQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private static String getDataIndex(String methodName) throws SQLException {

		sqlDriver.setStrQuery("SELECT DATA_INDEX FROM TESTCASES WHERE METHOD='"+methodName+"'");
		String data_index = (String) sqlDriver.getTestData()[0][0];
		return data_index;

	}

	public static String[][] getTestData(String keyword) throws SQLException{

		return(getTestData(keyword,"*"));

	}

	public static String[][] getTestData(String keyword,String...arg) throws SQLException{
		
		DBDriver getData = new DBDriver();
		String [][] testDataArray = null;

		sqlDriver.setStrQuery("SELECT * FROM TESTMAPPING WHERE KEYWORD='"+keyword+"'");
		String[][] propArray = (String[][]) sqlDriver.getTestData();
		String methodName = propArray[0][1];
		String dbTableName = propArray[0][2];
		getData.setStrQuery(getFormatttedTestDataSQL(methodName, dbTableName,arg));
		testDataArray = (String[][]) getData.getTestData();
		return(testDataArray);

	}
	
	private static String getFormatttedTestDataSQL(String methodName, String dbTableName,String... arg) throws SQLException {

		String[] datas = null;
		String formatedSQL =null;
		String dataIndex = getDataIndex(methodName);
		String tableCol = "";
		
		for(int i=0;i<arg.length;i++) {
			tableCol = tableCol+arg[i]+", ";
		}
		tableCol = tableCol.substring(0, tableCol.lastIndexOf(","));

		if(dataIndex.contains(",")) {
			formatedSQL = "SELECT "+tableCol+" FROM "+dbTableName+" WHERE DATA_INDEX IN ("+dataIndex+")";
		}else if(dataIndex.contains("-")) {
			datas = dataIndex.split("\\-");
			formatedSQL = "SELECT "+tableCol+" FROM "+dbTableName+" WHERE DATA_INDEX BETWEEN "+datas[0]+" AND "+datas[1];
		}else if(!dataIndex.contains("-") || !dataIndex.contains(",")) {
			formatedSQL = "SELECT "+tableCol+" FROM "+dbTableName+" WHERE DATA_INDEX = ("+dataIndex+")";
		}else {
			System.out.println("Invalid test data configuration on TESTCASES table");
		}
		return formatedSQL;

	}


}
