package com.qunar.ops.oaengine.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.util.OAControllerUtils;

@Component
public class PaymentService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Form0114Manager formManager;
	
	@Value("${qss.url}")
	String qssUrl;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void payment(long formId){
		FormInfo info = this.formManager.getFormInfoHistory(formId);
		if(info == null) return;
		
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setInvoiceCategory(1);
		paymentInfo.setInvoiceType(3);
		paymentInfo.setInvoiceDate(info.getField0005().getTime());
		paymentInfo.setBillId(info.getOid());
		paymentInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0069()));
		//paymentInfo.setFinishDate(sdf.format(info.getField0029()));
		paymentInfo.setFinishDate(new Date().getTime());
		//paymentInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0069()));
		paymentInfo.setRtxId(info.getStartMemberId());
		paymentInfo.setRtxName(info.getField0004());
		
		String dep = info.getField0001()+"."+info.getField0002()+"."+info.getField0003()+"."+info.getField0009()+"."+info.getField0100();
		
		SubPaymentInfo taxiInfo = new SubPaymentInfo();
		taxiInfo.setExpenseType(4);
		taxiInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0068()));
		taxiInfo.setDepartmentCode(dep);
		taxiInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(taxiInfo);
		
		SubPaymentInfo commInfo = new SubPaymentInfo();
		commInfo.setExpenseType(5);
		commInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0099()));
		commInfo.setDepartmentCode(dep);
		commInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(commInfo);
		
		SubPaymentInfo overInfo = new SubPaymentInfo();
		overInfo.setExpenseType(6);
		overInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0065()));
		overInfo.setDepartmentCode(dep);
		overInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(overInfo);
		
		SubPaymentInfo hosInfo = new SubPaymentInfo();
		hosInfo.setExpenseType(7);
		hosInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0066()));
		hosInfo.setDepartmentCode(dep);
		hosInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(hosInfo);
		
		SubPaymentInfo employInfo = new SubPaymentInfo();
		employInfo.setExpenseType(8);
		employInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0067()));
		employInfo.setDepartmentCode(dep);
		employInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(employInfo);
		
		SubPaymentInfo otherInfo = new SubPaymentInfo();
		otherInfo.setExpenseType(9);
		otherInfo.setAmount(OAControllerUtils.centMoneyToYuan(info.getField0070()));
		otherInfo.setDepartmentCode(dep);
		otherInfo.setBillId(info.getOid());
		paymentInfo.getBillDetail().add(otherInfo);
		
		try {
			JSONObject ret = this.invokePostApi(this.qssUrl, paymentInfo);
			if(!ret.getBoolean("ret")){
				logger.error("调用qss支付接口错误："+ret.getString("errmsg")+" id:"+formId);
			}else{
				logger.warn("调用qss支付接口成功");
			}
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error("调用qss支付接口失败："+e.getMessage()+" id:"+formId);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("调用qss支付接口失败："+e.getMessage()+" id:"+formId);
		}
	}
	
	private JSONObject invokePostApi(String apiUrl, PaymentInfo info) throws RemoteAccessException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			String infoJson = JSONObject.toJSONString(info);
			StringEntity se = new StringEntity(infoJson);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			HttpPost httppost = new HttpPost(apiUrl);
			httppost.setHeader("Content-Type", "application/json; charset=UTF-8");
			httppost.setEntity(se);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			String responseBody = httpclient.execute(httppost, responseHandler);
			JSONObject json = JSONObject.parseObject(responseBody);
			if (json == null) {
				throw new RemoteAccessException("responseBody is null",
						EmployeeInfoService.class);
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RemoteAccessException(e.getMessage(),
					EmployeeInfoService.class);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RemoteAccessException(e.getMessage(),
						EmployeeInfoService.class);
			}
		}
	}
	
	
	class PaymentInfo{
		private int invoiceCategory;
		private int invoiceType;
		private long invoiceDate;
		private String billId;
		private String amount;
		private long finishDate;
		private String rtxId;
		private String rtxName;
		private List<SubPaymentInfo> billDetail = new ArrayList<PaymentService.SubPaymentInfo>();
		public int getInvoiceCategory() {
			return invoiceCategory;
		}
		public void setInvoiceCategory(int invoiceCategory) {
			this.invoiceCategory = invoiceCategory;
		}
		public int getInvoiceType() {
			return invoiceType;
		}
		public void setInvoiceType(int invoiceType) {
			this.invoiceType = invoiceType;
		}
		public long getInvoiceDate() {
			return invoiceDate;
		}
		public void setInvoiceDate(long invoiceDate) {
			this.invoiceDate = invoiceDate;
		}
		public String getBillId() {
			return billId;
		}
		public void setBillId(String billId) {
			this.billId = billId;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public long getFinishDate() {
			return finishDate;
		}
		public void setFinishDate(long finishDate) {
			this.finishDate = finishDate;
		}
		public String getRtxId() {
			return rtxId;
		}
		public void setRtxId(String rtxId) {
			this.rtxId = rtxId;
		}
		public String getRtxName() {
			return rtxName;
		}
		public void setRtxName(String rtxName) {
			this.rtxName = rtxName;
		}
		public List<SubPaymentInfo> getBillDetail() {
			return billDetail;
		}
		public void setBillDetail(List<SubPaymentInfo> billDetail) {
			this.billDetail = billDetail;
		}
		
	}
	
	class SubPaymentInfo{
		private int expenseType;
		private String amount;
		private String departmentCode;
		private String billId;
		public int getExpenseType() {
			return expenseType;
		}
		public void setExpenseType(int expenseType) {
			this.expenseType = expenseType;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getDepartmentCode() {
			return departmentCode;
		}
		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}
		public String getBillId() {
			return billId;
		}
		public void setBillId(String billId) {
			this.billId = billId;
		}
		
	}

}
