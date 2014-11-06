package com.qunar.ops.oaengine.result;

import java.io.Serializable;

public class FormInfo implements Serializable{
	/**
	 * 表单信息
	 */
	private static final long serialVersionUID = 8933545444370281313L;
	private Long id;
	private String oid;
	private String procInstId;
	private OvertimeMealsInfo[] overtimeMealsInfo;
	private HospitalityInfo[] hospitalityInfo;
	private OtherCostsInfo[] otherCostsInfo;
	private EmployeeRelationsFeesInfo[] employeeRelationsFeesInfo;
	private TaxiFaresInfo[] taxiFaresInfo;
}
