package com.qunar.ops.oaengine.util;

public enum TableToParam {
	OvertimeMealsInfo("formson_0115", "Formson0115"),
	HospitalityInfo("formson_0116", "Formson0116"),
	OtherCostsInfo("formson_0117", "Formson0117"),
	EmployeeRelationsFeesInfo("formson_0118", "Formson0118"),
	TaxiFaresInfo("formson_0119", "Formson0119");
	
	private String tableName;
	private String modelName;
	
	private TableToParam(String tableName, String modelName){
		this.tableName = tableName;
		this.modelName = modelName;
	}
	
	@Override
	public String toString() {
		return "tableName=" + this.tableName + ",modelName=" + this.modelName;
	}
	
}
