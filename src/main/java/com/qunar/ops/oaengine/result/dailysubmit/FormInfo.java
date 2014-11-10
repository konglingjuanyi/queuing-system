package com.qunar.ops.oaengine.result.dailysubmit;

import java.io.Serializable;
import java.util.Date;

import com.qunar.ops.oaengine.model.Formmain0114;

public class FormInfo extends Formmain0114 implements Serializable{
	/**
	 * 表单信息
	 */
	private static final long serialVersionUID = 8933545444370281313L;
//    private String firstDep;
//    private String secDep;
//    private String thridDep;
//    private String applyUser;
//    private Date applyDate;
//    private String BorrowSN;
//    private String isBorrow;
//    private String serialNumber;
//    private String fourthDep;
//    private Long sumOtherAmount;
//    private Long moneyAmount;
//    private String depLeaderOpinion;
//    private String depLeaderSign;
//    private Date depLeaderDate;
//    private String supDepLeaderOpinion;
//    private String supDepLeaderSign;
//    private Date supDepLeaderDate;
//    private String depDirectorOpinion;
//    private String depDirectorSign;
//    private Date depDirectorDate;
//    private String vpOption;
//    private String vpSign;
//    private Date vpDate;
//    private String ceoOption;
//    private String ceoSign;
//    private Date ceoDate;
//    private String cashierOpinion;
//    private String cashierSign;
//    private Date cashierDate;
//    private Long sumOvertimeMealsAmount;
//    private Long sumHospitalityAmount;
//    private Long sumEmployeeRelationsFees;
//    private Long sumTaxiFaresAmount;
//    private String depLevel;
//    private String userNumber;
//    private Long overtimeMealsNotifyAmount;
//    private Long hospitalityNotifyAmount;
//    private Long emRelationsFeesNotify;
//    private Long taxiFaresNotifyAmount;
//    private Long sumFinancialNotify;
//    private Long otherNotifyAmount;
//    private String financialOption;
//    private String financialSign;
//    private Date financialDate;
//    private String applyDep;
//    private String depNum;
//    private String bankNumber;
//    private String onlyFirstDep;
//    private String financialDirectorOption;
//    private String financialDirectorSign;
//    private Date financialDirectorDate;
//    private String ceoOption1;
//    private String ceoSign1;
//    private Date ceoDate1;
//    private Long borrowAmount;
//    private Long communicationCosts;
//    private String commuCostsComment;
//    private String rtxId;
//    private String reimbursementTeamName;
//    private Date reimbursementTeamDate;
//    private String address;
//    private String isDirectVp;
//    private String bankName;
	private OvertimeMealsInfo[] overtimeMealsInfo;
	private HospitalityInfo[] hospitalityInfo;
	private OtherCostsInfo[] otherCostsInfo;
	private EmployeeRelationsFeesInfo[] employeeRelationsFeesInfo;
	private TaxiFaresInfo[] taxiFaresInfo;
	
    public OvertimeMealsInfo[] getOvertimeMealsInfo() {
		return overtimeMealsInfo;
	}
	public void setOvertimeMealsInfo(OvertimeMealsInfo[] overtimeMealsInfo) {
		this.overtimeMealsInfo = overtimeMealsInfo;
	}
	public HospitalityInfo[] getHospitalityInfo() {
		return hospitalityInfo;
	}
	public void setHospitalityInfo(HospitalityInfo[] hospitalityInfo) {
		this.hospitalityInfo = hospitalityInfo;
	}
	public OtherCostsInfo[] getOtherCostsInfo() {
		return otherCostsInfo;
	}
	public void setOtherCostsInfo(OtherCostsInfo[] otherCostsInfo) {
		this.otherCostsInfo = otherCostsInfo;
	}
	public EmployeeRelationsFeesInfo[] getEmployeeRelationsFeesInfo() {
		return employeeRelationsFeesInfo;
	}
	public void setEmployeeRelationsFeesInfo(
			EmployeeRelationsFeesInfo[] employeeRelationsFeesInfo) {
		this.employeeRelationsFeesInfo = employeeRelationsFeesInfo;
	}
	public TaxiFaresInfo[] getTaxiFaresInfo() {
		return taxiFaresInfo;
	}
	public void setTaxiFaresInfo(TaxiFaresInfo[] taxiFaresInfo) {
		this.taxiFaresInfo = taxiFaresInfo;
	}
	
	
	
	public String getFirstDep() {
		return super.getField0001();
	}
	public void setFirstDep(String firstDep) {
		super.setField0001(firstDep);
	}
	public String getSecDep() {
		return super.getField0002();
	}
	public void setSecDep(String secDep) {
		super.setField0002(secDep);
	}
	public String getThridDep() {
		return super.getField0003();
	}
	public void setThridDep(String thridDep) {
		super.setField0003(thridDep);
	}
	public String getApplyUser() {
		return super.getField0004();
	}
	public void setApplyUser(String applyUser) {
		super.setField0004(applyUser);
	}
	public Date getApplyDate() {
		return super.getField0005();
	}
	public void setApplyDate(Date applyDate) {
		super.setField0005(applyDate);
	}
	public String getBorrowSN() {
		return super.getField0006();
	}
	public void setBorrowSN(String borrowSN) {
		super.setField0006(borrowSN);
	}
	public String getIsBorrow() {
		return super.getField0007();
	}
	public void setIsBorrow(String isBorrow) {
		super.setField0007(isBorrow);
	}
	public String getSerialNumber() {
		return super.getField0008();
	}
	public void setSerialNumber(String serialNumber) {
		super.setField0008(serialNumber);
	}
	public String getFourthDep() {
		return super.getField0009();
	}
	public void setFourthDep(String fourthDep) {
		super.setField0009(fourthDep);
	}
	public Long getSumOtherAmount() {
		return super.getField0010();
	}
	public void setSumOtherAmount(Long sumOtherAmount) {
		super.setField0010(sumOtherAmount);
	}
	public Long getMoneyAmount() {
		return super.getField0011();
	}
	public void setMoneyAmount(Long moneyAmount) {
		super.setField0011(moneyAmount);
	}
	public String getDepLeaderOpinion() {
		return super.getField0012();
	}
	public void setDepLeaderOpinion(String depLeaderOpinion) {
		super.setField0012(depLeaderOpinion);
	}
	public String getDepLeaderSign() {
		return super.getField0013();
	}
	public void setDepLeaderSign(String depLeaderSign) {
		super.setField0013(depLeaderSign);
	}
	public Date getDepLeaderDate() {
		return super.getField0014();
	}
	public void setDepLeaderDate(Date depLeaderDate) {
		super.setField0014(depLeaderDate);
	}
	public String getSupDepLeaderOpinion() {
		return super.getField0015();
	}
	public void setSupDepLeaderOpinion(String supDepLeaderOpinion) {
		super.setField0015(supDepLeaderOpinion);
	}
	public String getSupDepLeaderSign() {
		return super.getField0016();
	}
	public void setSupDepLeaderSign(String supDepLeaderSign) {
		super.setField0016(supDepLeaderSign);
	}
	public Date getSupDepLeaderDate() {
		return super.getField0017();
	}
	public void setSupDepLeaderDate(Date supDepLeaderDate) {
		super.setField0017(supDepLeaderDate);
	}
	public String getDepDirectorOpinion() {
		return super.getField0018();
	}
	public void setDepDirectorOpinion(String depDirectorOpinion) {
		super.setField0018(depDirectorOpinion);
	}
	public String getDepDirectorSign() {
		return super.getField0019();
	}
	public void setDepDirectorSign(String depDirectorSign) {
		super.setField0019(depDirectorSign);
	}
	public Date getDepDirectorDate() {
		return super.getField0020();
	}
	public void setDepDirectorDate(Date depDirectorDate) {
		super.setField0020(depDirectorDate);
	}
	public String getVpOption() {
		return super.getField0021();
	}
	public void setVpOption(String vpOption) {
		super.setField0021(vpOption);
	}
	public String getVpSign() {
		return super.getField0022();
	}
	public void setVpSign(String vpSign) {
		super.setField0022(vpSign);
	}
	public Date getVpDate() {
		return super.getField0023();
	}
	public void setVpDate(Date vpDate) {
		super.setField0023(vpDate);
	}
	public String getCeoOption() {
		return super.getField0024();
	}
	public void setCeoOption(String ceoOption) {
		super.setField0024(ceoOption);
	}
	public String getCeoSign() {
		return super.getField0025();
	}
	public void setCeoSign(String ceoSign) {
		super.setField0025(ceoSign);
	}
	public Date getCeoDate() {
		return super.getField0026();
	}
	public void setCeoDate(Date ceoDate) {
		super.setField0026(ceoDate);
	}
	public String getCashierOpinion() {
		return super.getField0027();
	}
	public void setCashierOpinion(String cashierOpinion) {
		super.setField0027(cashierOpinion);
	}
	public String getCashierSign() {
		return super.getField0028();
	}
	public void setCashierSign(String cashierSign) {
		super.setField0028(cashierSign);
	}
	public Date getCashierDate() {
		return super.getField0029();
	}
	public void setCashierDate(Date cashierDate) {
		super.setField0029(cashierDate);
	}
	public Long getSumOvertimeMealsAmount() {
		return super.getField0030();
	}
	public void setSumOvertimeMealsAmount(Long sumOvertimeMealsAmount) {
		super.setField0030(sumOvertimeMealsAmount);
	}
	public Long getSumHospitalityAmount() {
		return super.getField0031();
	}
	public void setSumHospitalityAmount(Long sumHospitalityAmount) {
		super.setField0031(sumHospitalityAmount);
	}
	public Long getSumEmployeeRelationsFees() {
		return super.getField0032();
	}
	public void setSumEmployeeRelationsFees(Long sumEmployeeRelationsFees) {
		super.setField0032(sumEmployeeRelationsFees);
	}
	public Long getSumTaxiFaresAmount() {
		return super.getField0033();
	}
	public void setSumTaxiFaresAmount(Long sumTaxiFaresAmount) {
		super.setField0033(sumTaxiFaresAmount);
	}
	public String getDepLevel() {
		return super.getField0063();
	}
	public void setDepLevel(String depLevel) {
		super.setField0063(depLevel);
	}
	public String getUserNumber() {
		return super.getField0064();
	}
	public void setUserNumber(String userNumber) {
		super.setField0064(userNumber);
	}
	public Long getOvertimeMealsNotifyAmount() {
		return super.getField0065();
	}
	public void setOvertimeMealsNotifyAmount(Long overtimeMealsNotifyAmount) {
		super.setField0065(overtimeMealsNotifyAmount);
	}
	public Long getHospitalityNotifyAmount() {
		return super.getField0066();
	}
	public void setHospitalityNotifyAmount(Long hospitalityNotifyAmount) {
		super.setField0066(hospitalityNotifyAmount);
	}
	public Long getEmRelationsFeesNotify() {
		return super.getField0067();
	}
	public void setEmRelationsFeesNotify(Long emRelationsFeesNotify) {
		super.setField0067(emRelationsFeesNotify);
	}
	public Long getTaxiFaresNotifyAmount() {
		return super.getField0068();
	}
	public void setTaxiFaresNotifyAmount(Long taxiFaresNotifyAmount) {
		super.setField0068(taxiFaresNotifyAmount);
	}
	public Long getSumFinancialNotify() {
		return super.getField0069();
	}
	public void setSumFinancialNotify(Long sumFinancialNotify) {
		super.setField0069(sumFinancialNotify);
	}
	public Long getOtherNotifyAmount() {
		return super.getField0070();
	}
	public void setOtherNotifyAmount(Long otherNotifyAmount) {
		super.setField0070(otherNotifyAmount);
	}
	public String getFinancialOption() {
		return super.getField0071();
	}
	public void setFinancialOption(String financialOption) {
		super.setField0071(financialOption);
	}
	public String getFinancialSign() {
		return super.getField0072();
	}
	public void setFinancialSign(String financialSign) {
		super.setField0072(financialSign);
	}
	public Date getFinancialDate() {
		return super.getField0073();
	}
	public void setFinancialDate(Date financialDate) {
		super.setField0073(financialDate);
	}
	public String getApplyDep() {
		return super.getField0074();
	}
	public void setApplyDep(String applyDep) {
		super.setField0074(applyDep);
	}
	public String getDepNum() {
		return super.getField0075();
	}
	public void setDepNum(String depNum) {
		super.setField0075(depNum);
	}
	public String getBankNumber() {
		return super.getField0076();
	}
	public void setBankNumber(String bankNumber) {
		super.setField0076(bankNumber);
	}
	public String getOnlyFirstDep() {
		return super.getField0078();
	}
	public void setOnlyFirstDep(String onlyFirstDep) {
		super.setField0078(onlyFirstDep);
	}
	public String getFinancialDirectorOption() {
		return super.getField0079();
	}
	public void setFinancialDirectorOption(String financialDirectorOption) {
		super.setField0079(financialDirectorOption);
	}
	public String getFinancialDirectorSign() {
		return super.getField0080();
	}
	public void setFinancialDirectorSign(String financialDirectorSign) {
		super.setField0080(financialDirectorSign);
	}
	public Date getFinancialDirectorDate() {
		return super.getField0081();
	}
	public void setFinancialDirectorDate(Date financialDirectorDate) {
		super.setField0081(financialDirectorDate);
	}
	public String getCeoOption1() {
		return super.getField0082();
	}
	public void setCeoOption1(String ceoOption1) {
		super.setField0082(ceoOption1);
	}
	public String getCeoSign1() {
		return super.getField0083();
	}
	public void setCeoSign1(String ceoSign1) {
		super.setField0083(ceoSign1);
	}
	public Date getCeoDate1() {
		return super.getField0084();
	}
	public void setCeoDate1(Date ceoDate1) {
		super.setField0084(ceoDate1);
	}
	public Long getBorrowAmount() {
		return super.getField0085();
	}
	public void setBorrowAmount(Long borrowAmount) {
		super.setField0085(borrowAmount);
	}
	public Long getCommunicationCosts() {
		return super.getField0086();
	}
	public void setCommunicationCosts(Long communicationCosts) {
		super.setField0086(communicationCosts);
	}
	public String getCommuCostsComment() {
		return super.getField0087();
	}
	public void setCommuCostsComment(String commuCostsComment) {
		super.setField0087(commuCostsComment);
	}
	public String getRtxId() {
		return super.getField0090();
	}
	public void setRtxId(String rtxId) {
		super.setField0090(rtxId);
	}
	public String getReimbursementTeamName() {
		return super.getField0091();
	}
	public void setReimbursementTeamName(String reimbursementTeamName) {
		super.setField0091(reimbursementTeamName);
	}
	public Date getReimbursementTeamDate() {
		return super.getField0092();
	}
	public void setReimbursementTeamDate(Date reimbursementTeamDate) {
		super.setField0092(reimbursementTeamDate);
	}
	public String getAddress() {
		return super.getField0093();
	}
	public void setAddress(String address) {
		super.setField0093(address);
	}
	public String getIsDirectVp() {
		return super.getField0096();
	}
	public void setIsDirectVp(String isDirectVp) {
		super.setField0096(isDirectVp);
	}
	public String getBankName() {
		return super.getField0097();
	}
	public void setBankName(String bankName) {
		super.setField0097(bankName);
	}
}
