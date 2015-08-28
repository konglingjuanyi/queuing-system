package com.qunar.ops.recruit.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentExample;
import com.qunar.ops.recruit.model.StudentForm;
import com.qunar.ops.recruit.util.RecruitControllerUtils;

@Component
public class StudentManageService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	StudentMapper stuMapper;
	public Student createStudent(Row row) throws Exception {
		// TODO Auto-generated method stub
		Student stu=new Student();
		stu.setNoteNo(getStringCellValue(row.getCell(0)));
		stu.setYear(getStringCellValue(row.getCell(1)));
		stu.setPhaseNo(getStringCellValue(row.getCell(2)));
		String time=getStringCellValue(row.getCell(3));
		if("".equals(time)){
			stu.setInterviewTime(null);
		}else{
			stu.setInterviewTime(sdf.parse(time));
		}
		stu.setName(getStringCellValue(row.getCell(4)));
		stu.setSex(getStringCellValue(row.getCell(5)));
		stu.setSchool(getStringCellValue(row.getCell(6)));
		stu.setProfession(getStringCellValue(row.getCell(7)));
		stu.setEducation(getStringCellValue(row.getCell(8)));
		stu.setPhone(getStringCellValue(row.getCell(9)));
		stu.setEmail(getStringCellValue(row.getCell(10)));
		stu.setCardNo(getStringCellValue(row.getCell(11)));
		stu.setQqNo(getStringCellValue(row.getCell(12)));
		stu.setJob(getStringCellValue(row.getCell(13)));
		String workStart=getStringCellValue(row.getCell(14));
		if("".equals(workStart)){
			stu.setWorkStart(null);
		}else{
			stu.setWorkStart(sdf1.parse(workStart));
		}
		String workEnd=getStringCellValue(row.getCell(15));
		if("".equals(workEnd)){
			stu.setWorkEnd(null);
		}else{
			stu.setWorkEnd(sdf1.parse(workEnd));
		}
		String graduateDate=getStringCellValue(row.getCell(16));
		if("".equals(graduateDate)){
			stu.setGraduateDate(null);
		}else{
			stu.setGraduateDate(sdf1.parse(graduateDate));
		}
		String sa=getStringCellValue(row.getCell(17));
		if("".equals(sa)){
			stu.setSalary(Double.valueOf(0));
		}else{
			stu.setSalary(Double.valueOf(sa));
		}
		stu.setAssess(getStringCellValue(row.getCell(18)));
		stu.setFirstTry(getStringCellValue(row.getCell(19)));
		stu.setSecondTry(getStringCellValue(row.getCell(20)));
		stu.setViewRemark(getStringCellValue(row.getCell(21)));
		stu.setLocation(getStringCellValue(row.getCell(22)));
		stu.setOfferState(getStringCellValue(row.getCell(23)));
		stu.setThreeSide(getStringCellValue(row.getCell(24)));
		String pdate=getStringCellValue(row.getCell(25));
		if("".equals(pdate)){
			stu.setPayTime(null);
		}else{
			stu.setPayTime(sdf1.parse(getStringCellValue(row.getCell(25))));
		}
		stu.setThreeState(getStringCellValue(row.getCell(26)));
		stu.setRefuse(getStringCellValue(row.getCell(27)));
		stu.setRefuseReson(getStringCellValue(row.getCell(28)));
		String rdate=getStringCellValue(row.getCell(29));
		if("".equals(rdate)){
			stu.setRefuseDate(null);
		}else{
			stu.setRefuseDate(sdf1.parse(getStringCellValue(row.getCell(29))));
		}
		stu.setBreaker(getStringCellValue(row.getCell(30)));
		stu.setTrain(getStringCellValue(row.getCell(31)));
		stu.setStaffNo(getStringCellValue(row.getCell(32)));
		stu.setInterviewDept(getStringCellValue(row.getCell(33)));
		stu.setFenpeiDept(getStringCellValue(row.getCell(34)));
		stu.setYuanDept(getStringCellValue(row.getCell(35)));
		stu.setOrganize(getStringCellValue(row.getCell(36)));
		stu.setLeader(getStringCellValue(row.getCell(37)));
		stu.setState("0");
		return stu;
	}
	
	public String getStringCellValue(Cell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            double doubleValue = cell.getNumericCellValue();  
                // 是否为数值型  
                double d = cell.getNumericCellValue();  
             if (doubleValue - (int) doubleValue < Double.MIN_VALUE) {   
            	 // 是否为int型  
            	 strCell = Integer.toString((int) doubleValue);  
             } else {   
                 System.out.println("double.....");  
                 // 是否为double型  
                 strCell = Double.toString(cell.getNumericCellValue());  
                 DecimalFormat df = new DecimalFormat("#");  
                 strCell= df.format(cell.getNumericCellValue());  
             } 
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

	public int insertStudentInfo(List<Student> student) {
		// TODO Auto-generated method stub
		if(student.size()>0){
			for(Student stu:student){
				if(isHave(stu)){
					stuMapper.updateByPrimaryKeySelective(stu);
				}else{
					stuMapper.insertSelective(stu);
				}
			}
			return 1;
		}else{
			return -1;
		}
	}
	
	public boolean isHave(Student stu){
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(stu.getYear());
		criteria.andPhaseNoEqualTo(stu.getPhaseNo());
		criteria.andLocationEqualTo(stu.getLocation());
		criteria.andPhoneEqualTo(stu.getPhone());
		criteria.andNameEqualTo(stu.getName());
		List<Student> list=new ArrayList<Student>();
		if(list!=null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public List<Student> getAllStudentInfos(String city, String year, String phase) {
		// TODO Auto-generated method stub
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andPhaseNoEqualTo(phase);
		criteria.andLocationEqualTo(city);
		criteria.andStateNotEqualTo("9");
		return stuMapper.selectByExample(example);
	}

	public Student makeStudentBy(StudentForm stu) {
		Student inter = new Student();
		String noteNo = stu.getNoteNo();
		String phaseNo = stu.getPhaseNo();
		String interviewTime = stu.getInterviewTime();
		String name = stu.getName();
		String sex = stu.getSex();
		String school = stu.getSchool();
		String profession = stu.getProfession();
		String education = stu.getEducation();
		String phone = stu.getPhone();
		String email = stu.getEmail();
		String cardNo = stu.getCardNo();
		String qqNo = stu.getQqNo();
		String job = stu.getJob();
		String workStart = stu.getWorkStart();
		String workEnd = stu.getWorkEnd();
		String graduateDate = stu.getGraduateDate();
		String salary = stu.getSalary();
		String assess = stu.getAssess();
		String firstTry = stu.getFirstTry();
		String secondTry = stu.getSecondTry();
		String viewRemark = stu.getViewRemark();
		String location = stu.getLocation();
		String offerState = stu.getOfferState();
		String threeSide = stu.getThreeSide();
		String payTime = stu.getPayTime();
		String threeState = stu.getThreeState();
		String refuse = stu.getRefuse();
		String refuseReson = stu.getRefuseReson();
		String refuseDate = stu.getRefuseDate();
		String breaker = stu.getBreaker();
		String train = stu.getTrain();
		String staffNo = stu.getStaffNo();
		String interviewDept = stu.getInterviewDept();
		String fenpeiDept = stu.getFenpeiDept();
		String yuanDept = stu.getYuanDept();
		String organize = stu.getOrganize();
		String leader = stu.getLeader();
		String year = stu.getYear();
		
		inter.setRefuse(refuse);
		inter.setOfferState(offerState);
		inter.setPhone(phone);
		inter.setQqNo(qqNo);
		inter.setProfession(profession);
		inter.setSex(sex);
		inter.setAssess(assess);
		inter.setOrganize(organize);
		inter.setNoteNo(noteNo);
		inter.setPhaseNo(phaseNo);
		inter.setBreaker(breaker);
		inter.setCardNo(cardNo);
		inter.setEducation(education);
		inter.setEmail(email);
		inter.setFenpeiDept(fenpeiDept);
		inter.setFirstTry(firstTry);
		inter.setInterviewDept(interviewDept);
		inter.setJob(job);
		inter.setLeader(leader);
		inter.setLocation(location);
		inter.setName(name);
		inter.setYuanDept(yuanDept);
		inter.setYear(year);
		inter.setViewRemark(viewRemark);
		inter.setTrain(train);
		inter.setThreeState(threeState);
		inter.setThreeSide(threeSide);
		inter.setState("0");
		if("".equals(salary) || salary==null){
			inter.setSalary(Double.valueOf(0));
		}else{
			inter.setSalary(Double.valueOf(salary));
		}
		inter.setSchool(school);
		inter.setSecondTry(secondTry);
		inter.setStaffNo(staffNo);
		inter.setRefuse(refuseDate);
		inter.setRefuseReson(refuseReson);
		if("".equals(interviewTime) || interviewTime==null){
			inter.setInterviewTime(null);
		}else{
			inter.setInterviewTime(RecruitControllerUtils.strToDateII(interviewTime));
		}
		if("".equals(workStart) || workStart==null){
			inter.setWorkStart(null);
		}else{
			inter.setWorkStart(RecruitControllerUtils.strToDateII(workStart));
		}
		if("".equals(workEnd) || workEnd==null){
			inter.setWorkEnd(null);
		}else{
			inter.setWorkEnd(RecruitControllerUtils.strToDateII(workEnd));
		}
		if("".equals(graduateDate) || graduateDate==null){
			inter.setGraduateDate(null);
		}else{
			inter.setGraduateDate(RecruitControllerUtils.strToDateII(graduateDate));
		}
		if("".equals(payTime) || payTime==null){
			inter.setPayTime(null);
		}else{
			inter.setPayTime(RecruitControllerUtils.strToDateII(payTime));
		}
		if("".equals(refuseDate) || refuseDate==null){
			inter.setRefuseDate(null);
		}else{
			inter.setRefuseDate(RecruitControllerUtils.strToDateII(refuseDate));
		}
		return inter;
	}

	public int insertOneStudentInfo(Student result) {
		// TODO Auto-generated method stub
		if(result!=null){
			if(isHave(result)){
				stuMapper.updateByPrimaryKeySelective(result);
			}else{
				stuMapper.insertSelective(result);
			}
			return 1;
		}else{
			return -1;
		}
	}

	public void deleteStudentInfoBy(int id) {
		// TODO Auto-generated method stub
		Student stu=new Student();
		stu.setState("9");
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		stuMapper.updateByExampleSelective(stu, example);
	}

	public List<Student> getStudentInofs(String name, String school,
			String profession, String state, String city, String year,
			String phase) {
		StudentExample example = new StudentExample();
		StudentExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		criteria.andPhaseNoEqualTo(phase);
		criteria.andLocationEqualTo(city);
		if(name != null && !name.equals(""))
			criteria.andNameEqualTo(name);
		if(school != null && !school.equals(""))
			criteria.andSchoolEqualTo(school);
		if(profession != null && !profession.equals(""))
			criteria.andProfessionEqualTo(profession);
		if(state != null && !state.equals(""))
			criteria.andStateEqualTo(state);
		return stuMapper.selectByExample(example);
	}

}
