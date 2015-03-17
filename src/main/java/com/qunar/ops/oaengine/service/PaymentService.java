package com.qunar.ops.oaengine.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		paymentInfo.setInvoice_category(1);
		paymentInfo.setInvoice_type(3);
		paymentInfo.setInvoice_date(sdf.format(info.getField0005()));
		paymentInfo.setBill_id(info.getOid());
		paymentInfo.setBill_no(info.getOid());
		paymentInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0069()));
		paymentInfo.setInvoice_date(sdf.format(info.getField0029()));
		paymentInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0069()));
		paymentInfo.setRtx_id(info.getStartMemberId());
		paymentInfo.setRtx_name(info.getField0004());
		
		String dep = info.getField0001()+","+info.getField0002()+","+info.getField0003()+","+info.getField0009()+","+info.getField0100();
		
		SubPaymentInfo taxiInfo = new SubPaymentInfo();
		taxiInfo.setExpense_type(110);
		taxiInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0068()));
		taxiInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0068()));
		taxiInfo.setBill_id(info.getOid());
		taxiInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(taxiInfo);
		
		SubPaymentInfo overInfo = new SubPaymentInfo();
		overInfo.setExpense_type(112);
		overInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0065()));
		overInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0065()));
		overInfo.setBill_id(info.getOid());
		overInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(overInfo);
		
		SubPaymentInfo hosInfo = new SubPaymentInfo();
		hosInfo.setExpense_type(113);
		hosInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0066()));
		hosInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0066()));
		hosInfo.setBill_id(info.getOid());
		hosInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(hosInfo);
		
		SubPaymentInfo otherInfo = new SubPaymentInfo();
		otherInfo.setExpense_type(115);
		otherInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0070()));
		otherInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0070()));
		otherInfo.setBill_id(info.getOid());
		otherInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(otherInfo);
		
		SubPaymentInfo employInfo = new SubPaymentInfo();
		employInfo.setExpense_type(114);
		employInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0067()));
		employInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0067()));
		employInfo.setBill_id(info.getOid());
		employInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(employInfo);
		
		SubPaymentInfo commInfo = new SubPaymentInfo();
		commInfo.setExpense_type(111);
		commInfo.setAmount(OAControllerUtils.centMoneyToYuanII(info.getField0099()));
		commInfo.setDeduction_amount(OAControllerUtils.centMoneyToYuanII(info.getField0099()));
		commInfo.setBill_id(info.getOid());
		commInfo.setDepartment_code(dep);
		paymentInfo.getSubInfos().add(commInfo);
		
		try {
			this.invokePostApi(this.qssUrl, paymentInfo);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error("调用qss支付接口错误："+e.getMessage());
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
		private int invoice_category;
		private int invoice_type;
		private String invoice_date;
		private String bill_id;
		private String bill_no;
		private float amount;
		private String finish_date;
		private float deduction_amount;
		private String rtx_id;
		private String rtx_name;
		private List<SubPaymentInfo> subInfos = new ArrayList<PaymentService.SubPaymentInfo>();
		public int getInvoice_category() {
			return invoice_category;
		}
		public void setInvoice_category(int invoice_category) {
			this.invoice_category = invoice_category;
		}
		public int getInvoice_type() {
			return invoice_type;
		}
		public void setInvoice_type(int invoice_type) {
			this.invoice_type = invoice_type;
		}
		public String getInvoice_date() {
			return invoice_date;
		}
		public void setInvoice_date(String invoice_date) {
			this.invoice_date = invoice_date;
		}
		public String getBill_id() {
			return bill_id;
		}
		public void setBill_id(String bill_id) {
			this.bill_id = bill_id;
		}
		public String getBill_no() {
			return bill_no;
		}
		public void setBill_no(String bill_no) {
			this.bill_no = bill_no;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public String getFinish_date() {
			return finish_date;
		}
		public void setFinish_date(String finish_date) {
			this.finish_date = finish_date;
		}
		public float getDeduction_amount() {
			return deduction_amount;
		}
		public void setDeduction_amount(float deduction_amount) {
			this.deduction_amount = deduction_amount;
		}
		public String getRtx_id() {
			return rtx_id;
		}
		public void setRtx_id(String rtx_id) {
			this.rtx_id = rtx_id;
		}
		public String getRtx_name() {
			return rtx_name;
		}
		public void setRtx_name(String rtx_name) {
			this.rtx_name = rtx_name;
		}
		public List<SubPaymentInfo> getSubInfos() {
			return subInfos;
		}
		public void setSubInfos(List<SubPaymentInfo> subInfos) {
			this.subInfos = subInfos;
		}
		
		
	}
	
	class SubPaymentInfo{
		private int expense_type;
		private float amount;
		private float deduction_amount;
		private String bill_id;
		private String department_code;
		public int getExpense_type() {
			return expense_type;
		}
		public void setExpense_type(int expense_type) {
			this.expense_type = expense_type;
		}
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public float getDeduction_amount() {
			return deduction_amount;
		}
		public void setDeduction_amount(float deduction_amount) {
			this.deduction_amount = deduction_amount;
		}
		public String getBill_id() {
			return bill_id;
		}
		public void setBill_id(String bill_id) {
			this.bill_id = bill_id;
		}
		public String getDepartment_code() {
			return department_code;
		}
		public void setDepartment_code(String department_code) {
			this.department_code = department_code;
		}
		
	}

}
