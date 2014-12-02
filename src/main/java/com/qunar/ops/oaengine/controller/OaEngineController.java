package com.qunar.ops.oaengine.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.*;
import com.qunar.ops.oaengine.result.dailysubmit.*;
import com.qunar.ops.oaengine.service.IOAEngineService;
import com.qunar.ops.oaengine.service.MailSenderService;
import org.activiti.engine.*;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OaEngineController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected WorkflowManager manager;
    @Autowired
    protected ProcessEngineFactoryBean processEngine;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private IOAEngineService ioaEngineService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/oa/test.html")
    public void index(HttpServletRequest request) {
        // mailSenderService.sender("nuby.zhang@qunar.com", new
        // String[]{"nuby.zhang@qunar.com"}, new
        // String[]{"nuby.zhang@qunar.com"}, "", "");
        Request req = new Request();
        req.setAmountMoney(200l);
        req.setDepartment("技术部");
        req.setDepartmentII("OPS");
        req.setOid("001");
        req.setReport2vp(true);
        req.setTbMoney(100l);
        // Object[] startWorkflow = this.manager.startWorkflow("oa_common",
        // "nuby.zhang", req);
        // ListInfo<TaskInfo> todoList = this.manager.todoList("test", "nuby",
        // 0, 10);
        TaskResult pass = this.manager.pass("5913", "nuby.zhang");
        // this.manager.endorse("5", "nuby", "nuby,abc");
        // List<TaskInfo> back = this.manager.back("nuby", "50962", "xxx");
        // this.manager.cancel("test", "001", "nuby.zhang", "reason");
        System.out.println("====");
    }

    /**
     * login
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/oa/login")
    public String login(HttpServletRequest request) {
        HttpClient client = HttpClientBuilder.create().build();
        String token = request.getParameter("token");
        HttpGet method = new HttpGet(
                "http://qsso.corp.qunar.com/api/verifytoken.php?token=" + token);
        try {
            HttpResponse response = client.execute(method);
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            JSONObject parseObject = JSON.parseObject(result.toString());
            String ret = parseObject.getString("ret");
            if (ret.equals("true")) {
                String userId = parseObject.getJSONObject("data").getString(
                        "userId");
                request.getSession().setAttribute("USER_ID", userId);
            } else {
                return "redirect:/oa/index.html";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("sso 验证失败", e);
        }
        return "redirect:/oa/apply_todo.html";
    }

    /**
     * index
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/oa/index.html")
    public ModelAndView welcom(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId != null) {
            return addApply(request);
        }
        ModelAndView mav = new ModelAndView("/oa/index");
        return mav;
    }

    /**
     * 我的申请报销页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/apply.html")
    public ModelAndView addApply(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("oa/apply");
        return mav;
    }

    /**
     * 我的申请，已经在流程中的页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/apply_todo.html")
    public ModelAndView myApplyTodo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("oa/apply_todo");
        return mav;
    }

    /**
     * 我的申请，已经审批结束的页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/apply_history.html")
    public ModelAndView myApplyHistory(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("oa/apply_history");
        return mav;
    }

    /**
     * 待审批
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/approve_todo.html")
    public ModelAndView approveTodo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("oa/approve_todo");
        return mav;
    }

    /**
     * 已审批
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/approve_history.html")
    public ModelAndView approveHistory(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("oa/approve_history");
        return mav;
    }

    /**
     * 我的申请页面需要获取的基本信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "oa/employeeinfo")
    @ResponseBody
    public BaseResult webEmployeeInfo(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        EmployeeInfo employeeInfo = new EmployeeInfo();
        try {
            employeeInfo = ioaEngineService.getEmployeeInfo(userId);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            logger.warn("ACL限制，获取员工信息失败");
            return BaseResult.getErrorResult(-2, "ACL限制，获取员工信息失败");
        }
        String result[] = new String[]{employeeInfo.getAdName(),
                employeeInfo.getSn(),
                sdf.format(new Date(System.currentTimeMillis())),
                employeeInfo.getDepartmentI(), employeeInfo.getDepartmentIV()};
        return BaseResult.getSuccessResult(result);
    }

    /**
     * 获取工时
     *
     * @param request
     * @param laborRequest
     * @return
     */
    @RequestMapping(value = "oa/laborhour")
    @ResponseBody
    public BaseResult webLaborHour(HttpServletRequest request,
                                   @RequestBody LaborRequest laborRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        String day = laborRequest.getWhichDay();
        Date date = null;
        try {
            date = sdf.parse(day);
        } catch (ParseException e1) {
            e1.printStackTrace();
            String errMsg = "转换时间失败，请检查输入是否为yyyy-MM-dd样式";
            logger.warn(errMsg);
            return BaseResult.getErrorResult(-2, errMsg);
        }
        float laborHour = 0;
        try {
            laborHour = ioaEngineService.getLaborHour(userId, date);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            laborHour = 0;
        }
        String result[] = new String[]{String.valueOf(laborHour)};
        return BaseResult.getSuccessResult(result);
    }

    /**
     * 报销页面传到后端信息,存至各个表
     *
     * @param request
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "oa/data")
    @ResponseBody
    public BaseResult webPostData(HttpServletRequest request,
                                  @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        Map<String, String[][]> tableMap = webRequest.getTableMap();
        boolean createFlag = true;
        FormInfo formInfo = new FormInfo();
        try {
            createFlag = constructFormInfo(formInfo, tableMap, vars);
        } catch (ParseException e) {
            e.printStackTrace();
            String errorMsg = "日期字段填写错误，请检查";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        if (!createFlag) {
            String errorMsg = "没有任何报销内容，请检查";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-1, errorMsg);
        }
        String processKey = "oa_common";
        boolean flag = webRequest.isFlag();

        long id = 0;
        if (flag) {
            try {
                ioaEngineService.createFormAndstart(processKey, userId,
                        formInfo);
            } catch (RemoteAccessException e) {
                e.printStackTrace();
                String errorMsg = "ACL限制，获取员工信息失败";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-1, errorMsg);
            } catch (CompareModelException e) {
                e.printStackTrace();
                String errorMsg = "日期字段填写错误，请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-1, errorMsg);
            } catch (FormNotFoundException e) {
                e.printStackTrace();
                String errorMsg = "表单未找到，请检查！";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-1, errorMsg);
            }
        } else {
            ioaEngineService.createForm(processKey, userId, formInfo);
        }
        return BaseResult.getSuccessResult(String.valueOf(id));
    }


    /**
     * 正在审批中的申请
     *
     * @param request
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "oa/todo")
    @ResponseBody
    public BaseResult getAllMyApplyTodoList(HttpServletRequest request,
                                            @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        String processKey = "oa_common";
        Map<String, String> vars = webRequest.getVars();
        int noSize[] = getPageNoAndSize(vars);
        int pageSize = noSize[0];
        int pageNo = noSize[1];
        String startTime = vars.get("startTime");
        String endTime = vars.get("endTime");
        Date _startTime = null;
        if (startTime != null) {
            try {
                _startTime = sdf.parse(startTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        Date _endTime = null;
        if (endTime != null) {
            try {
                _endTime = sdf.parse(endTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        FormInfoList formInfoList = ioaEngineService.getUserApplyList(
                processKey, userId, _startTime, _endTime, pageNo, pageSize);
        DataResult dataResult;
        try {
            dataResult = getTableInfos(formInfoList, userId, 1);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        return BaseResult.getSuccessResult(dataResult);
    }

    /**
     * 已经结束的申请
     *
     * @param request
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "oa/history")
    @ResponseBody
    public BaseResult getAllMyApplyHistoryList(HttpServletRequest request,
                                               @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        int noSize[] = getPageNoAndSize(vars);
        int pageSize = noSize[0];
        int pageNo = noSize[1];
        String processKey = "oa_common";
        String startTime = vars.get("startTime");
        String endTime = vars.get("endTime");
        Date _startTime = null;
        if (startTime != null) {
            try {
                _startTime = sdf.parse(startTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        Date _endTime = null;
        if (endTime != null) {
            try {
                _endTime = sdf.parse(endTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        FormInfoList formInfoList = ioaEngineService.getUserApplyHisList(
                processKey, userId, _startTime, _endTime, pageNo, pageSize);
        DataResult dataResult;
        try {
            dataResult = getTableInfos(formInfoList, userId, 1);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        return BaseResult.getSuccessResult(dataResult);
    }

    /**
     * 待审批
     *
     * @param request
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "oa/approve_todo")
    @ResponseBody
    public BaseResult getAllApproveTodoList(HttpServletRequest request,
                                            @RequestBody WebRequest webRequest) {
        System.out.println("oa/approve_todo");
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        String processKey = "oa_common";
        // Date startTime, Date endTime,
        // 默认一页显示50条数据
        Map<String, String> vars = webRequest.getVars();
        int noSize[] = getPageNoAndSize(vars);
        int pageSize = noSize[0];
        int pageNo = noSize[1];
        String startTime = vars.get("startTime");
        String endTime = vars.get("endTime");
        String approve_user = vars.get("approveUser");
        Date _startTime = null;
        if (startTime != null) {
            try {
                _startTime = sdf.parse(startTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        Date _endTime = null;
        if (endTime != null) {
            try {
                _endTime = sdf.parse(endTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        FormInfoList formInfoList = null;
        try {
            formInfoList = ioaEngineService.todoList(
                    processKey, "nuby.zhang", _startTime, _endTime, approve_user, pageNo, pageSize);
        } catch (FormNotFoundException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        DataResult dataResult;
        try {
            dataResult = getTableInfos(formInfoList, userId, 2);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        return BaseResult.getSuccessResult(dataResult);
    }

    /**
     * 已经审批结束
     *
     * @param request
     * @param webRequest
     * @return
     */
    @RequestMapping(value = "oa/approve_history")
    @ResponseBody
    public BaseResult getAllApproveHistoryList(HttpServletRequest request,
                                               @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        String processKey = "oa_common";
        Map<String, String> vars = webRequest.getVars();
        int noSize[] = getPageNoAndSize(vars);
        int pageSize = noSize[0];
        int pageNo = noSize[1];
        String startTime = vars.get("startTime");
        String endTime = vars.get("endTime");
        String approve_user = vars.get("approveUser");
        Date _startTime = null;
        if (startTime != null) {
            try {
                _startTime = sdf.parse(startTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        Date _endTime = null;
        if (endTime != null) {
            try {
                _endTime = sdf.parse(endTime);
            } catch (ParseException e) {
                logger.warn(e.getMessage());
                e.printStackTrace();
                return BaseResult.getErrorResult(-3, "时间日期填写错误,转换失败");
            }
        }
        if (isNull(approve_user)){
            approve_user = null;
        }
        FormInfoList formInfoList = null;
        try {
            formInfoList = ioaEngineService.historyList(
                    processKey, "nuby.zhang", _startTime, _endTime, approve_user, pageNo, pageSize);
        } catch (FormNotFoundException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            ApproveResult approveResult = new ApproveResult();
            return BaseResult.getSuccessResult(approveResult);
        }
        DataResult dataResult;
        try {
            dataResult = getTableInfos(formInfoList, userId, 3);
        } catch (RemoteAccessException e) {
            e.printStackTrace();
            String errorMsg = "ACL限制，获取员工信息失败";
            logger.warn(errorMsg);
            return BaseResult.getErrorResult(-2, errorMsg);
        }
        return BaseResult.getSuccessResult(dataResult);
    }

    /**
     * 批量完成任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve_pass")
    @ResponseBody
    public BaseResult approvePass(HttpServletRequest request,
                                  @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formIds = vars.get("formIds");
        String taskIds = vars.get("taskIds");
        List<Long> formIdList = new ArrayList<Long>();
        List<String> taskIdList = new ArrayList<String>();
        String formMsg[] = formIds.split(",");
        for (int i = 0; i < formMsg.length; i++) {
            formIdList.add(Long.valueOf(formMsg[i]));
        }
        String taskMsg[] = taskIds.split(",");
        for (int i = 0; i < taskMsg.length; i++) {
            taskIdList.add(taskMsg[i]);
        }
        String msg = vars.get("reason");
        userId = "nuby.zhang";
        List<Long> errorFormIds = ioaEngineService.batchPass("oa_common", userId, formIdList, taskIdList, msg);
        int size = errorFormIds.size();
        if (size == 0) {
            return BaseResult.getSuccessResult("同意操作成功");
        }
        return BaseResult.getSuccessResult(errorFormIds.toArray());

    }

    /**
     * 批量拒绝任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve_reject")
    @ResponseBody
    public BaseResult approveReject(HttpServletRequest request,
                                    @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formIds = vars.get("formIds");
        String taskIds = vars.get("taskIds");
        String formMsg[] = formIds.split(",");
        int len = formMsg.length;
        String taskMsg[] = taskIds.split(",");
        String msg = vars.get("reason");
        for (int i = 0; i < len; i++) {
            try {
                ioaEngineService.refuse("oa_common", "nuby.zhang", Long.valueOf(formMsg[i]), taskMsg[i], msg);
            } catch (FormNotFoundException e) {
                e.printStackTrace();
                String errorMsg = "任务没有找到,请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            } catch (ActivitiException e) {
                e.printStackTrace();
                String errorMsg = "工作流系统错误,请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            }
        }
        return BaseResult.getSuccessResult("拒绝审批结束");
    }

    /**
     * 批量退回任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve_back")
    @ResponseBody
    public BaseResult approveBack(HttpServletRequest request,
                                  @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formIds = vars.get("formIds");
        String taskIds = vars.get("taskIds");
        String formMsg[] = formIds.split(",");
        int len = formMsg.length;
        String taskMsg[] = taskIds.split(",");
        String msg = vars.get("reason");
        for (int i = 0; i < len; i++) {
            try {
                ioaEngineService.back("oa_common", "nuby.zhang", Long.valueOf(formMsg[i]), taskMsg[i], msg);
            } catch (FormNotFoundException e) {
                e.printStackTrace();
                String errorMsg = "任务没有找到,请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            } catch (ActivitiException e) {
                e.printStackTrace();
                String errorMsg = "没有前置审批节点，无法回退，请选择拒绝";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            }
        }
        return BaseResult.getSuccessResult("回退审批结束");

    }

    /**
     * 批量加签任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve_endorse")
    @ResponseBody
    public BaseResult approveEndorse(HttpServletRequest request,
                                     @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formIds = vars.get("formIds");
        String taskIds = vars.get("taskIds");
        String formMsg[] = formIds.split(",");
        int len = formMsg.length;
        String taskMsg[] = taskIds.split(",");
        String msg = vars.get("reason");
        for (int i = 0; i < len; i++) {
            try {
                ioaEngineService.endorse("oa_common", "nuby.zhang", Long.valueOf(formMsg[i]), taskMsg[i], msg, "");
            } catch (FormNotFoundException e) {
                e.printStackTrace();
                String errorMsg = "任务没有找到,请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            } catch (ActivitiException e) {
                e.printStackTrace();
                String errorMsg = "工作流系统错误,请检查";
                logger.warn(errorMsg);
                return BaseResult.getErrorResult(-3, errorMsg);
            }
        }
        return BaseResult.getSuccessResult("加签结束");

    }

    /**
     * 催办动作
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/push_approve")
    @ResponseBody
    public BaseResult pushApprove(HttpServletRequest request,
                                     @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formId = vars.get("formId");
        ListInfo<ApprovalInfo> approveInfos = ioaEngineService.getApprovalInfo("oa_common", formId, 1, 20);
        long count = approveInfos.getCount();
        if (count == 0) {
            return BaseResult.getSuccessResult("没有获取到审批候选人");
        }
        List<ApprovalInfo> infos = approveInfos.getInfos();
        int size = infos.size();
        ApprovalInfo approvalInfo = infos.get(size - 1);
        Long approveId = approvalInfo.getId();
        ioaEngineService.reminder("oa_common", userId, formId, String.valueOf(approveId), "催办");
        return BaseResult.getSuccessResult("催办成功");

    }

    /**
     * 获取审批各个节点的审批意见
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve_info")
    @ResponseBody
    public BaseResult getApproveInfo(HttpServletRequest request,
                                     @RequestBody WebRequest webRequest) {
        String userId = (String) request.getSession().getAttribute("USER_ID");
        if (userId == null || userId.length() == 0) {
            logger.warn("登陆用户为空，无法获取员工信息");
            return BaseResult.getErrorResult(-3, "登陆用户为空，无法获取员工信息");
        }
        Map<String, String> vars = webRequest.getVars();
        String formId = vars.get("formId");
        ListInfo<ApprovalInfo> approveInfos = ioaEngineService.getApprovalInfo("oa_common", formId, 1, 20);
        long count = approveInfos.getCount();
        if (count == 0) {
            return BaseResult.getSuccessResult("");
        }
        List<ApprovalInfo> infos = approveInfos.getInfos();
        int size = infos.size();
        SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[][] result = new String[size][3];
        for (int i = 0; i < size; i++) {
            ApprovalInfo approveInfo = infos.get(i);
            String memo = approveInfo.getMemo();
            if (isNull(memo)) {
                memo = "同意";
            }
            String info[] = new String[]{
                    approveInfo.getApproveUser(),
                    tdf.format(approveInfo.getTs()),
                    memo
            };
            result[i] = info;
        }
        return BaseResult.getSuccessResult(result);

    }

    private boolean constructFormInfo(FormInfo formInfo,
                                      Map<String, String[][]> tableMap, Map<String, String> vars)
            throws ParseException {
        // 存储各个table的数据。
        String table[][] = tableMap.get("table1");
        int len = table.length;
        List<TaxiFaresInfo> list1 = new ArrayList<TaxiFaresInfo>();
        for (int i = 0; i < len; i++) {
            if ("".equals(table[i][0]) || "".equals(table[i][7])) {
                continue;
            }
            TaxiFaresInfo taxiInfo = new TaxiFaresInfo();
            taxiInfo.setTaxiFaresDate(sdf.parse(table[i][0]));
            taxiInfo.setTaxiFaresAddr(table[i][1]);
            taxiInfo.setTaxiFaresDest(table[i][2]);
            taxiInfo.setTaxiFaresTime(table[i][3]);
            taxiInfo.setTaxiFaresTimeNew(table[i][3]);
            taxiInfo.setTaxiFaresUse(table[i][4]);
            taxiInfo.setTaxiFaresPeerPeople(table[i][5]);
            taxiInfo.setTaxiFaresWorkhour(new BigDecimal(table[i][6]));
            Float money = Float.valueOf(table[i][7]);
            String sMoney = String.valueOf((int) (money * 100));
            taxiInfo.setTaxiFaresAmount(Long.valueOf(sMoney));
            taxiInfo.setComment(table[i][8]);
            list1.add(taxiInfo);
        }
        int size = list1.size();
        TaxiFaresInfo[] taxiInfos = list1
                .toArray(new TaxiFaresInfo[size]);
        formInfo.setTaxiFaresInfo(taxiInfos);

        table = tableMap.get("table2");
        len = table.length;
        List<OvertimeMealsInfo> list2 = new ArrayList<OvertimeMealsInfo>();
        for (int i = 0; i < len; i++) {
            if ("".equals(table[i][0]) || "".equals(table[i][5])) {
                continue;
            }
            OvertimeMealsInfo overInfo = new OvertimeMealsInfo();
            overInfo.setOvertimeMealsDate(sdf.parse(table[i][0]));
            overInfo.setMealsAddr(table[i][1]);
            overInfo.setOvertimeMealsPeerPeople(table[i][2]);
            overInfo.setMealsPersonNum(Long.valueOf(table[i][3]));
            Float money = Float.valueOf(table[i][4]);
            String sMoney = String.valueOf((int) (money * 100));
            overInfo.setOvertimeMealsAmount(Long.valueOf(sMoney));
            overInfo.setPerMealsFee((long) (Float.valueOf(table[i][5]) * 100));
            overInfo.setInvoiceAmount(table[i][6]);
            overInfo.setOvertimeMealsWorkhours(new BigDecimal(table[i][7]));
            overInfo.setOvertimeMealsComment(table[i][8]);
            list2.add(overInfo);
        }
        size = list2.size();
        OvertimeMealsInfo[] overInfos = list2
                .toArray(new OvertimeMealsInfo[size]);
        formInfo.setOvertimeMealsInfo(overInfos);

        table = tableMap.get("table3");
        len = table.length;
        List<HospitalityInfo> list3 = new ArrayList<HospitalityInfo>();
        for (int i = 0; i < len; i++) {
            if ("".equals(table[i][0]) || "".equals(table[i][6])) {
                continue;
            }
            HospitalityInfo hosInfo = new HospitalityInfo();
            hosInfo.setHospitalityDate(sdf.parse(table[i][0]));
            hosInfo.setHospitalityAddr(table[i][1]);
            hosInfo.setBusinessPurpose(table[i][2]);
            hosInfo.setCustomCompany(table[i][3]);
            hosInfo.setCustomName(table[i][4]);
            hosInfo.setHospitalityNum(table[i][5]);
            Float money = Float.valueOf(table[i][6]);
            String sMoney = String.valueOf((int) (money * 100));
            hosInfo.setHospitalityAmount(Long.valueOf(sMoney));
            list3.add(hosInfo);
        }
        size = list3.size();
        HospitalityInfo[] hosInfos = list3
                .toArray(new HospitalityInfo[size]);
        formInfo.setHospitalityInfo(hosInfos);

        table = tableMap.get("table4");
        len = table.length;
        List<EmployeeRelationsFeesInfo> list4 = new ArrayList<EmployeeRelationsFeesInfo>();
        for (int i = 0; i < len; i++) {
            if ("".equals(table[i][0]) || "".equals(table[i][4])) {
                continue;
            }
            EmployeeRelationsFeesInfo employInfo = new EmployeeRelationsFeesInfo();
            employInfo.setEmRelationsDate(sdf.parse(table[i][0]));
            employInfo.setEmRelationsAddress(table[i][1]);
            employInfo.setEmRelationsPeerPeople(table[i][2]);
            employInfo.setActDest(table[i][3]);
            Float money = Float.valueOf(table[i][4]);
            String sMoney = String.valueOf((int) (money * 100));
            employInfo.setEmRelationsFees(Long.valueOf(sMoney));
            employInfo.setEmRelationsFeesComment(table[i][5]);
            list4.add(employInfo);

        }
        size = list4.size();
        EmployeeRelationsFeesInfo[] employInfos = list4
                .toArray(new EmployeeRelationsFeesInfo[size]);
        formInfo.setEmployeeRelationsFeesInfo(employInfos);

        table = tableMap.get("table5");
        len = table.length;
        List<OtherCostsInfo> list5 = new ArrayList<OtherCostsInfo>();
        for (int i = 0; i < len; i++) {
            if ("".equals(table[i][0]) || "".equals(table[i][2])) {
                continue;
            }
            OtherCostsInfo otherInfo = new OtherCostsInfo();
            otherInfo.setOtherCostProject(table[i][0]);
            Float money = Float.valueOf(table[i][1]);
            String sMoney = String.valueOf((int) (money * 100));
            otherInfo.setOtherCostAmount(Long.valueOf(sMoney));
            otherInfo.setOtherCostComment(table[i][2]);
            list5.add(otherInfo);
        }
        size = list5.size();
        // 如果全部数据都为空的话，就不存了。

        if (list1.size() == 0 && list2.size() == 0 && list3.size() == 0
                && list4.size() == 0 && list5.size() == 0) {
            return false;
        }

        OtherCostsInfo[] otherInfos = list5
                .toArray(new OtherCostsInfo[size]);
        formInfo.setOtherCostsInfo(otherInfos);
        // 存储所有数据之和
        formInfo.setSumTaxiFaresAmount((long) (Float.valueOf(vars.get("sum1")) * 100));
        formInfo.setSumOvertimeMealsAmount((long) (Float.valueOf(vars.get("sum2")) * 100));
        formInfo.setSumHospitalityAmount((long) (Float.valueOf(vars.get("sum3")) * 100));
        formInfo.setSumEmployeeRelationsFees((long) (Float.valueOf(vars.get("sum4")) * 100));
        formInfo.setSumOtherAmount((long) (Float.valueOf(vars.get("sum5")) * 100));

        formInfo.setCommunicationCosts((long) (Float.valueOf(vars.get("sum6")) * 100));
        formInfo.setCommuCostsComment(vars.get("remark"));

        formInfo.setMoneyAmount((long) (Float.valueOf(vars.get("sum")) * 100));
        table = tableMap.get("table");
        len = table.length;
        for (int i = 0; i < len; i++) {
            formInfo.setApplyUser(table[i][0]);
            formInfo.setApplyDate((new Date(System.currentTimeMillis())));
            formInfo.setSerialNumber(table[i][1]);
            formInfo.setFirstDep(table[i][3]);
            formInfo.setApplyDep(table[i][4]);
            formInfo.setDepNum(table[i][5]);
            formInfo.setIsDirectVp(table[i][6]);
            formInfo.setBankNumber(table[i][7]);
            formInfo.setBankName(table[i][8]);
            formInfo.setIsBorrow(table[i][9]);
            formInfo.setBorrowSN(table[i][10]);
            if (isNull(table[i][11])) {
                formInfo.setBorrowAmount(0l);
            } else {
                formInfo.setBorrowAmount(Long.valueOf(table[i][11]));
            }
        }
        return true;
    }



    /**
     * 获取当前审批信息
     *
     * @param formInfoList
     * @return
     */
    private ApproveResult getApproveTodoInfos(FormInfoList formInfoList) {
        List<FormInfo> formInfos = formInfoList.getFormInfos();
        ApproveResult approveResult = new ApproveResult();
        long count = formInfoList.getCount();
        if (count == 0) {
            return approveResult;
        }
        List<String[]> infos = new ArrayList<String[]>();
        int size = formInfos.size();
        for (int i = 0; i < size; i++) {
            FormInfo formInfo = formInfos.get(i);
            String[] tableInfo = new String[6];
            tableInfo[0] = String.valueOf(formInfo.getId());
            tableInfo[1] = formInfo.getApplyUser();
            tableInfo[2] = formInfo.getApplyDep();
            tableInfo[3] = sdf.format(formInfo.getApplyDate());
            tableInfo[4] = String.valueOf(formInfo.getMoneyAmount());
            tableInfo[5] = formInfo.getTaskId();
            infos.add(tableInfo);
        }
        approveResult.setCount(count);
        approveResult.setInfos(infos);
        return approveResult;
    }

    /**
     * 获取审批历史信息
     *
     * @param formInfoList
     * @return
     */
    private ApproveResult getApproveHistoryInfos(FormInfoList formInfoList) {
        List<FormInfo> formInfos = formInfoList.getFormInfos();
        ApproveResult approveResult = new ApproveResult();
        long count = formInfoList.getCount();
        if (count == 0) {
            return approveResult;
        }
        List<String[]> infos = new ArrayList<String[]>();
        int size = formInfos.size();
        for (int i = 0; i < size; i++) {
            FormInfo formInfo = formInfos.get(i);
            String[] tableInfo = new String[6];
            tableInfo[0] = String.valueOf(formInfo.getId());
            tableInfo[1] = formInfo.getApplyUser();
            tableInfo[2] = formInfo.getApplyDep();
            tableInfo[3] = sdf.format(formInfo.getApplyDate());
            tableInfo[4] = "不知道结束时间怎么定义";
            tableInfo[5] = String.valueOf(formInfo.getMoneyAmount());
            infos.add(tableInfo);
        }
        approveResult.setCount(count);
        approveResult.setInfos(infos);
        return approveResult;
    }

    /**
     * 我的申请和历史申请表格信息
     *
     * @param formInfoList
     * @param userId
     * @return
     * @throws RemoteAccessException
     */
    private DataResult getTableInfos(FormInfoList formInfoList, String userId, int id) throws RemoteAccessException {
        DataResult dataResult = new DataResult();
        List<FormInfo> formInfos = formInfoList.getFormInfos();
        int size = formInfos.size();
        List<String[]> tableInfos = new ArrayList<String[]>();
        EmployeeInfo employeeInfo = ioaEngineService.getEmployeeInfo(userId);
        String depart = getDepartMent(employeeInfo);
        List<Map<String, String>> varsList = new ArrayList<Map<String, String>>();
        List<Map<String, String[][]>> tableMapList = new ArrayList<Map<String, String[][]>>();
        for (int i = 0; i < size; i++) {
            FormInfo formInfo = formInfos.get(i);
            String tableInfo[] = getEveryTableInfo(formInfo, depart, id);
            tableInfos.add(tableInfo);
            constructTableMap(formInfo, varsList, tableMapList);
        }
        dataResult.setCount((long) size);
        dataResult.setTableMapList(tableMapList);
        dataResult.setVarsList(varsList);
        dataResult.setTableInfos(tableInfos);
        return dataResult;
    }

    /**
     * 统一返回中间不同处理
     * @param formInfo
     * @param depart
     * @param id
     * @return
     */
    private String[] getEveryTableInfo(FormInfo formInfo, String depart, int id) {
        String tableInfo[] = {};
        if (id == 1) {
            tableInfo = new String[]{String.valueOf(formInfo.getId()),
                    formInfo.getSerialNumber(), depart,
                    formInfo.getApplyUser(), sdf.format(formInfo.getApplyDate()),
                    String.valueOf(formInfo.getMoneyAmount())};
        }else if (id == 2) {
            tableInfo = new String[6];
            tableInfo[0] = String.valueOf(formInfo.getId());
            tableInfo[1] = formInfo.getApplyUser();
            tableInfo[2] = formInfo.getApplyDep();
            tableInfo[3] = sdf.format(formInfo.getApplyDate());
            tableInfo[4] = String.valueOf(formInfo.getMoneyAmount());
            tableInfo[5] = formInfo.getTaskId();
        }else if (id == 3){
            tableInfo = new String[6];
            tableInfo[0] = String.valueOf(formInfo.getId());
            tableInfo[1] = formInfo.getApplyUser();
            tableInfo[2] = formInfo.getApplyDep();
            tableInfo[3] = sdf.format(formInfo.getApplyDate());
            tableInfo[4] = "不知道结束时间怎么定义";
            tableInfo[5] = String.valueOf(formInfo.getMoneyAmount());
        }
        return tableInfo;
    }

    /**
     * 构造历史页面需要展示的各种信息
     *
     * @param formInfo
     * @param varsList
     * @param tableMapList
     */
    private void constructTableMap(FormInfo formInfo, List<Map<String, String>> varsList,
                                   List<Map<String, String[][]>> tableMapList) {
        Map<String, String[][]> tableMap = new HashMap<String, String[][]>();
        String table[][] = createTableFirstInfo(formInfo);
        tableMap.put("table", table);
        table = createTableLastInfo(formInfo);
        tableMap.put("table7", table);
        TaxiFaresInfo infos1[] = formInfo.getTaxiFaresInfo();
        int len = infos1.length;
        if (len == 0) {
            table = new String[0][0];
        } else {
            table = new String[len][9];
            for (int i = 0; i < len; i++) {
                table[i][0] = sdf.format(infos1[i].getTaxiFaresDate());
                table[i][1] = infos1[i].getTaxiFaresAddr();
                table[i][2] = infos1[i].getTaxiFaresDest();
                table[i][3] = infos1[i].getTaxiFaresTime();
                table[i][4] = infos1[i].getTaxiFaresUse();
                table[i][5] = infos1[i].getTaxiFaresPeerPeople();
                table[i][6] = String.valueOf(infos1[i].getTaxiFaresWorkhour());
                String eMoney = transformMoney(infos1[i].getTaxiFaresAmount());
                table[i][7] = eMoney;
                table[i][8] = infos1[i].getComment();
            }
        }
        tableMap.put("table1", table);

        OvertimeMealsInfo infos2[] = formInfo.getOvertimeMealsInfo();
        len = infos2.length;
        if (len == 0) {
            table = new String[0][0];
        } else {
            table = new String[len][9];
            for (int i = 0; i < len; i++) {
                table[i][0] = sdf.format(infos2[i].getOvertimeMealsDate());
                table[i][1] = infos2[i].getMealsAddr();
                table[i][2] = infos2[i].getOvertimeMealsPeerPeople();
                table[i][3] = String.valueOf(infos2[i].getMealsPersonNum());
                String money1 = transformMoney(infos2[i].getOvertimeMealsAmount());
                table[i][4] = money1;
                String money2 = transformMoney(infos2[i].getPerMealsFee());
                table[i][5] = money2;
                table[i][6] = String.valueOf(infos2[i].getInvoiceAmount());
                table[i][7] = infos2[i].getOvertimeMealsWorkhours().toString();
                table[i][8] = infos2[i].getOvertimeMealsComment();
            }
        }
        tableMap.put("table2", table);

        HospitalityInfo infos3[] = formInfo.getHospitalityInfo();
        len = infos3.length;
        if (len == 0) {
            table = new String[0][0];
        } else {
            table = new String[len][7];
            for (int i = 0; i < len; i++) {
                table[i][0] = sdf.format(infos3[i].getHospitalityDate());
                table[i][1] = infos3[i].getHospitalityAddr();
                table[i][2] = infos3[i].getBusinessPurpose();
                table[i][3] = infos3[i].getCustomCompany();
                table[i][4] = infos3[i].getCustomName();
                table[i][5] = infos3[i].getHospitalityNum();
                String eMoney = transformMoney(infos3[i].getHospitalityAmount());
                table[i][6] = eMoney;
            }
        }
        tableMap.put("table3", table);

        EmployeeRelationsFeesInfo infos4[] = formInfo.getEmployeeRelationsFeesInfo();
        len = infos4.length;
        if (len == 0) {
            table = new String[0][0];
        } else {
            table = new String[len][6];
            for (int i = 0; i < len; i++) {
                table[i][0] = sdf.format(infos4[i].getEmRelationsDate());
                table[i][1] = infos4[i].getEmRelationsAddress();
                table[i][2] = infos4[i].getEmRelationsPeerPeople();
                table[i][3] = infos4[i].getActDest();
                String eMoney = transformMoney(infos4[i].getEmRelationsFees());
                table[i][4] = eMoney;
                table[i][5] = infos4[i].getEmRelationsFeesComment();
            }
        }
        tableMap.put("table4", table);

        OtherCostsInfo infos5[] = formInfo.getOtherCostsInfo();
        len = infos5.length;
        if (len == 0) {
            table = new String[0][0];
        } else {
            table = new String[len][3];
            for (int i = 0; i < len; i++) {
                table[i][0] = infos5[i].getOtherCostProject();
                String eMoney = transformMoney(infos5[i].getOtherCostAmount());
                table[i][1] = String.valueOf(eMoney);
                table[i][2] = infos5[i].getOtherCostComment();
            }
        }
        tableMap.put("table5", table);

        Map<String, String> vars = new HashMap<String, String>();

        String moneySum1 = transformMoney(formInfo.getSumTaxiFaresAmount());
        vars.put("sum1", moneySum1);
        String moneySum2 = transformMoney(formInfo.getSumOvertimeMealsAmount());
        vars.put("sum2", moneySum2);
        String moneySum3 = transformMoney(formInfo.getSumHospitalityAmount());
        vars.put("sum3", moneySum3);

        String moneySum4 = transformMoney(formInfo.getSumEmployeeRelationsFees());
        vars.put("sum4", moneySum4);
        String moneySum5 = transformMoney(formInfo.getSumOtherAmount());
        vars.put("sum5", moneySum5);
        String moneySum = transformMoney(formInfo.getMoneyAmount());
        vars.put("sum", moneySum);

        String moneySum6 = transformMoney(formInfo.getCommunicationCosts());
        vars.put("sum6", moneySum6);
        vars.put("remark", formInfo.getCommuCostsComment());

        varsList.add(vars);
        tableMapList.add(tableMap);
    }

    /**
     * 历史页面展示信息 第一个table
     *
     * @param formInfo
     * @return
     */
    private String[][] createTableFirstInfo(FormInfo formInfo) {
        String table[][] = new String[1][12];
        String acTable[] = new String[12];
        acTable[0] = formInfo.getApplyUser();
        acTable[1] = formInfo.getSerialNumber();
        acTable[2] = sdf.format(formInfo.getApplyDate());
        acTable[3] = formInfo.getFirstDep();
        acTable[4] = formInfo.getApplyDep();
        acTable[5] = formInfo.getDepNum();
        acTable[6] = formInfo.getIsDirectVp();
        acTable[7] = formInfo.getBankNumber();
        acTable[8] = formInfo.getBankName();
        acTable[9] = formInfo.getIsBorrow();
        acTable[10] = formInfo.getBorrowSN();
        acTable[11] = String.valueOf(formInfo.getBorrowAmount());
        table[0] = acTable;
        return table;
    }

    /**
     * 历史页面展示信息,最后一个table
     *
     * @param formInfo
     * @return
     */
    private String[][] createTableLastInfo(FormInfo formInfo) {
        String table[][] = new String[8][2];
        table[0][0] = formInfo.getDepLeaderOpinion();
        table[0][1] = dateToStr(formInfo.getDepLeaderDate());
        table[1][0] = formInfo.getDepDirectorOpinion();
        table[1][1] = dateToStr(formInfo.getDepDirectorDate());
        table[2][0] = formInfo.getSupDepLeaderOpinion();
        table[2][1] = dateToStr(formInfo.getSupDepLeaderDate());
        table[3][0] = formInfo.getFinancialDirectorOption();
        table[3][1] = dateToStr(formInfo.getFinancialDirectorDate());
        table[4][0] = formInfo.getCeoOption();
        table[4][1] = dateToStr(formInfo.getCeoDate());
        table[5][0] = formInfo.getCeoOption1();
        table[5][1] = dateToStr(formInfo.getCeoDate1());
        table[6][0] = formInfo.getReimbursementTeamName();
        table[6][1] = dateToStr(formInfo.getReimbursementTeamDate());
        table[7][0] = formInfo.getCashierOpinion();
        table[7][1] = dateToStr(formInfo.getCashierDate());
        return table;
    }

    /**
     * 获取部门信息,拼接成五级部门
     *
     * @param employeeInfo
     * @return
     */
    private String getDepartMent(EmployeeInfo employeeInfo) {
        String depart = employeeInfo.getDepartmentI();
        String other = employeeInfo.getDepartmentII();
        if (!isNull(other)) {
            depart += "-" + other;
        }
        other = employeeInfo.getDepartmentIII();
        if (!isNull(other)) {
            depart += "-" + other;
        }
        other = employeeInfo.getDepartmentIV();
        if (!isNull(other)) {
            depart += "-" + other;
        }
        other = employeeInfo.getDepartmentV();
        if (!isNull(other)) {
            depart += "-" + other;
        }
        return depart;
    }

    /**
     * 判断一个字符串是否为空
     *
     * @param value
     * @return
     */
    private boolean isNull(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 将获取到的money转为人民币单位元
     *
     * @param money
     * @return
     */
    private String transformMoney(long money) {
        DecimalFormat ndf = new DecimalFormat("##0.00");
        double sMoney = (double) money / 100;
        String eMoney = ndf.format(sMoney);
        return eMoney;
    }

    /**
     * 获取当前是第几页以及每页的size
     *
     * @param vars
     * @return
     */
    private int[] getPageNoAndSize(Map<String, String> vars) {
        String sStart = vars.get("start");
        String sLen = vars.get("length");
        int start = sStart == null ? 0 : Integer.valueOf(sStart);
        int len = sLen == null ? 0 : Integer.valueOf(sLen);
        int pageSize = len;
        int pageNo = start / len + 1;
        return new int[]{pageSize, pageNo};
    }

    /**
     * 日期转为字符串
     *
     * @param date
     * @return
     */
    private String dateToStr(Date date) {
        String dateStr = "";
        if (date != null) {
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

}
