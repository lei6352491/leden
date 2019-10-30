package com.zhouyi.business.core.database;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;

public class MapperMethodGenerator {

	public static void process(Writer writer, CommonTable table, String idProertyClassType, HashSet<String> existMethodSet, KeyGenMode keyGenMode) throws IOException{
		if(!existMethodSet.contains("selectAll")){
			writer.write(addSelectAll(table, idProertyClassType));
		}
		if(!existMethodSet.contains("selectByPrimaryKey")){
			writer.write(addSelectByPrimaryKey(table, idProertyClassType));
		}
		if(!existMethodSet.contains("selectByModel")){
			writer.write(addSelectByModel(table, idProertyClassType));
		}
		if(!existMethodSet.contains("deleteByPrimaryKey")){
			writer.write(addDeleteByPrimaryKey(table, idProertyClassType));
		}
		if(!existMethodSet.contains("insert")){
			writer.write(addInsert(table, idProertyClassType));
		}
		if(!existMethodSet.contains("insertBatch")){
			writer.write(addInsertBatch(table, idProertyClassType));
		}
		if(!existMethodSet.contains("replaceBatch")){
			writer.write(addReplaceBatch(table, idProertyClassType));
		}
		if(!existMethodSet.contains("insertAndReturnId")){
			writer.write(addInsertAndReturnId(table, idProertyClassType, keyGenMode));
		}
		if(!existMethodSet.contains("insertSelective")){
			writer.write(addInsertSelective(table, idProertyClassType));
		}
		if(!existMethodSet.contains("insertSelectiveAndReturnId")){
			writer.write(addInsertSelectiveAndReturnId(table, idProertyClassType, keyGenMode));
		}
		if(!existMethodSet.contains("updateByPrimaryKeySelective")){
			writer.write(addUpdateByPrimaryKeySelective(table, idProertyClassType));
		}
		if(!existMethodSet.contains("updateByPrimaryKey")){
			writer.write(addUpdateByPrimaryKey(table, idProertyClassType));
		}
		if(!existMethodSet.contains("updateByModel")){
			writer.write(addUpdateByModel(table, idProertyClassType));
		}
	}

	
	private static String getColumns(CommonTable table){
		StringBuilder sb=new StringBuilder();
		for (CommonTableColumn tableColumn : table.getAllColumnList()) {
			sb.append(tableColumn.getColumn());
			sb.append(",");
		}
		if(sb.indexOf(",")!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	private static String getColumnsExcludeId(CommonTable table){
		StringBuilder sb=new StringBuilder();
		for (CommonTableColumn tableColumn : table.getAllColumnList()) {
			if(!tableColumn.getColumn().equals(table.getIdColumn().getColumn())){
				sb.append(tableColumn.getColumn());
				sb.append(",");
			}
		}
		if(sb.indexOf(",")!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	private static String getIfColumns(CommonTable table){
		StringBuilder sb=new StringBuilder();
		sb.append("\n   <trim prefix=\"(\" suffix=\")\"   suffixOverrides=\",\" >");
		
		for (CommonTableColumn tableColumn : table.getAllColumnList()) {
			sb.append("\n    <if test=\""+tableColumn.getProperty()+" != null\" >");
			sb.append(tableColumn.getColumn());
			sb.append(",");
			sb.append("\n    </if>");
		}
		sb.append("\n   </trim>");
		return sb.toString();
	}
	
	private static String getKeyCondition(CommonTableColumn idColumn){
		return idColumn.getColumn()+" = #{"+idColumn.getProperty()+",jdbcType="+idColumn.getJdbcType()+"}";
	}
	
	private static String getConditions(List<CommonTableColumn> columnList,boolean needif,String stuffix){
		StringBuilder sb = new StringBuilder();
		if(needif)sb.append("\n   <trim prefix=\"\"  suffixOverrides=\""+stuffix+"\" >");
		for (CommonTableColumn column : columnList) {
			if(needif)sb.append("\n    <if test=\""+column.getProperty()+" != null\" >");
			sb.append("\n   	  "+column.getColumn()+" = #{"+column.getProperty()+",jdbcType="+column.getJdbcType()+"}"+stuffix);
			if(needif)sb.append("\n    </if>");
		}
		if(!needif&&sb.indexOf(stuffix)!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		if(needif)sb.append("\n   </trim>");
		return sb.toString();
	}
	
	private static String getMConditions(String var ,List<CommonTableColumn> columnList,boolean needif,String stuffix){
		StringBuilder sb = new StringBuilder();
		if(needif)sb.append("\n   <trim prefix=\"\"  suffixOverrides=\""+stuffix+"\" >");
		for (CommonTableColumn column : columnList) {
			if(needif)sb.append("\n    <if test=\""+var+column.getProperty()+" != null\" >");
			sb.append("\n   	  "+column.getColumn()+" = "+"#{"+var+column.getProperty()+",jdbcType="+column.getJdbcType()+"}"+stuffix);
			if(needif)sb.append("\n    </if>");
		}
		if(!needif&&sb.indexOf(stuffix)!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		if(needif)sb.append("\n   </trim>");
		return sb.toString();
	}
	
	private static String getValues(List<CommonTableColumn> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (CommonTableColumn column : columnList) {
			sb.append("#{"+column.getProperty()+",jdbcType="+column.getJdbcType()+"},");
		}
		if(sb.indexOf(",")!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(")");
		return sb.toString();
	}
	
	private static String getIfValues(List<CommonTableColumn> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("\n   <trim prefix=\"(\" suffix=\")\"  suffixOverrides=\",\" >");
		for (CommonTableColumn column : columnList) {
			sb.append("\n    <if test=\""+column.getProperty()+" != null\" >");
			sb.append("#{"+column.getProperty()+",jdbcType="+column.getJdbcType()+"},");
			sb.append("\n    </if>");
		}
		sb.append("\n   </trim>");
		return sb.toString();
	}
	
	
	private static String getBatchValues(List<CommonTableColumn> columnList){
		StringBuilder sb = new StringBuilder();
		sb.append("\n   <trim prefix=\"\"  suffixOverrides=\",\" >");
		sb.append("\n <foreach item=\"model\" index=\"index\" collection=\"list\"> ");
		sb.append("\n (");
		for (CommonTableColumn column : columnList) {
			sb.append("#{model."+column.getProperty()+",jdbcType="+column.getJdbcType()+"},");
		}
		if(sb.indexOf(",")!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(" ),");
		sb.append("\n </foreach>");
		sb.append("\n   </trim>");
		return sb.toString();
	}
	
	private static String getBatchValuesExcludeId(List<CommonTableColumn> columnList,CommonTable table){
		StringBuilder sb = new StringBuilder();
		sb.append("\n   <trim prefix=\"\"  suffixOverrides=\",\" >");
		sb.append("\n <foreach item=\"model\" index=\"index\" collection=\"list\"> ");
		sb.append("\n (");
		for (CommonTableColumn column : columnList) {
			if(!column.getColumn().equals(table.getIdColumn().getColumn())){
			sb.append("#{model."+column.getProperty()+",jdbcType="+column.getJdbcType()+"},");
			}
		}
		if(sb.indexOf(",")!=-1){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(" ),");
		sb.append("\n </foreach>");
		sb.append("\n   </trim>");
		return sb.toString();
	}
	
	private static String addSelectAll(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n<select id=\"selectAll\" resultMap=\""+table.getBaseResultMap()+"\" parameterType=\""+idProertyClassType+"\" >");
		sb.append("\n select ");  
		sb.append(getColumns(table));
		sb.append("\n    from "+table.getTable());
		sb.append("\n</select>\n");
		
		return sb.toString();
	}
	
	private static String addSelectByPrimaryKey(CommonTable table,String idProertyClassType) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<select id=\"selectByPrimaryKey\" resultMap=\"" +table.getBaseResultMap()+"\" parameterType=\""+idProertyClassType+"\" >");
		sb.append("\n select ");  
		sb.append(getColumns(table));
		sb.append("\n    from "+table.getTable());
		sb.append("\n    where "+getKeyCondition(table.getIdColumn()));
		sb.append("\n</select>\n");
		return sb.toString();
	}
	
	private static String addSelectByModel(CommonTable table,String idProertyClassType) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<select id=\"selectByModel\" resultMap=\"" +table.getBaseResultMap()+"\" parameterType=\""+table.getClassType()+"\" >");
		sb.append("\n select ");  
		sb.append(getColumns(table));
		sb.append("\n    from "+table.getTable());
		sb.append("\n    where "+getConditions(table.getAllColumnList(),true," and "));
		sb.append("\n</select>\n");
		return sb.toString();
	}
	
	private static String addDeleteByPrimaryKey(CommonTable table,String idProertyClassType) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<delete id=\"deleteByPrimaryKey\"  parameterType=\""+idProertyClassType+"\" >");
		sb.append("\n delete  from "+table.getTable());
		sb.append("\n    where "+getKeyCondition(table.getIdColumn()));
		sb.append("\n</delete>\n");
		return sb.toString();
	}

	private static String addInsert(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n<insert id=\"insert\"  parameterType=\""+table.getClassType()+"\" >");
		sb.append("\n insert into "+table.getTable());  
		sb.append("("+getColumns(table)+")");
		sb.append("\n    values "+getValues(table.getAllColumnList()));
		sb.append("\n <selectKey keyProperty=\""+table.getIdColumn().getColumn()+"\" resultType=\""+idProertyClassType+"\">"+
      "select LAST_INSERT_ID() as id </selectKey>");
		sb.append("\n</insert>\n");
		
		return sb.toString();
	}
	
	private static String addInsertBatch(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n<insert id=\"insertBatch\"  parameterType=\"java.util.List\" keyProperty=\""+table.getIdColumn().getColumn()+"\" useGeneratedKeys=\"true\">");
		sb.append("\n insert into "+table.getTable());  
		sb.append("("+getColumnsExcludeId(table)+")");
		sb.append("\n    values "+getBatchValuesExcludeId(table.getAllColumnList(),table));
		sb.append("\n</insert>\n");
		return sb.toString();
	}
	
	private static String addReplaceBatch(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n<insert id=\"replaceBatch\"  parameterType=\"java.util.List\" keyProperty=\""+table.getIdColumn().getColumn()+"\" useGeneratedKeys=\"true\">");
		sb.append("\n replace into "+table.getTable());  
		sb.append("("+getColumnsExcludeId(table)+")");
		sb.append("\n    values "+getBatchValuesExcludeId(table.getAllColumnList(),table));
		sb.append("\n</insert>\n");
		return sb.toString();
	}
	
	private static String addInsertAndReturnId(CommonTable table,String idProertyClassType, KeyGenMode keyGenMode){
		StringBuilder sb = new StringBuilder();
		sb.append(getInsertHeader(table, idProertyClassType, "insertAndReturnId", keyGenMode));
		sb.append("\n insert into "+table.getTable());  
		sb.append("("+getColumns(table)+")");
		sb.append("\n    values "+getValues(table.getAllColumnList()));
		sb.append("\n</insert>\n");
		return sb.toString();
	}
	
	private static String getInsertHeader(CommonTable table,String idProertyClassType, String method, KeyGenMode keyGenMode) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<insert id=\"" + method + "\"  parameterType=\""+table.getClassType()+"\" ");
		switch(keyGenMode) {
			case AUTO_INCREMENT:
				sb.append("useGeneratedKeys=\"true\" keyProperty=\""+table.getIdColumn().getProperty()+"\">");
				break;
			case MYCAT:
				sb.append(">");
				sb.append("<selectKey keyProperty=\"" + table.getIdColumn().getProperty() + "\" resultType=\"" + idProertyClassType +  "\" order=\"BEFORE\">");
				sb.append("select next value for MYCATSEQ_" + table.getTable().toUpperCase());
				sb.append("</selectKey>");				
				break;
		}

		return sb.toString();
	}
	
	private static String addInsertSelective(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n <insert id=\"insertSelective\" parameterType=\""+table.getClassType()+"\"  keyProperty=\""+table.getIdColumn().getColumn()+"\" useGeneratedKeys=\"true\">");
		sb.append("\n insert into "+table.getTable());  
		sb.append(getIfColumns(table));
		sb.append("\n    values "+getIfValues(table.getAllColumnList()));
		sb.append("\n</insert>\n");
		return sb.toString();
	}
	 
	
	private static String addInsertSelectiveAndReturnId(CommonTable table,String idProertyClassType, KeyGenMode keyGenMode){
		StringBuilder sb = new StringBuilder();
		sb.append(getInsertHeader(table, idProertyClassType, "insertSelectiveAndReturnId", keyGenMode));
		sb.append("\n insert into "+table.getTable());  
		sb.append(getIfColumns(table));
		sb.append("\n    values "+getIfValues(table.getAllColumnList()));
		sb.append("\n</insert>\n");
		return sb.toString();
	}
	
	private static String addUpdateByPrimaryKeySelective(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n <update id=\"updateByPrimaryKeySelective\" parameterType=\""+table.getClassType()+"\" >");
		sb.append("\n update "+table.getTable());  
		sb.append("\n <set> "+getConditions(table.getColumnList(), true,",")+"\n </set>");
		sb.append("\n where "+getKeyCondition(table.getIdColumn()));
		sb.append("\n</update>\n");
		return sb.toString();
	}
	
	private static String addUpdateByPrimaryKey(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n <update id=\"updateByPrimaryKey\" parameterType=\""+table.getClassType()+"\" >");
		sb.append("\n update "+table.getTable());  
		sb.append("\n <set> "+getConditions(table.getColumnList(), false,",")+"\n </set>");
		sb.append("\n where "+getKeyCondition(table.getIdColumn()));
		sb.append("\n</update>\n");
		return sb.toString();
	}
	
	
	private static String addUpdateByModel(CommonTable table,String idProertyClassType){
		StringBuilder sb = new StringBuilder();
		sb.append("\n <update id=\"updateByModel\" parameterType=\""+table.getClassType()+"\" >");
		sb.append("\n update "+table.getTable());  
		sb.append("\n <set> "+getMConditions("upateData.",table.getColumnList(), false,",")+"\n </set>");
		sb.append("\n where "+getMConditions("criteriaData.",table.getAllColumnList(), true," and "));
		sb.append("\n</update>\n");
		return sb.toString();
	}
	
}
