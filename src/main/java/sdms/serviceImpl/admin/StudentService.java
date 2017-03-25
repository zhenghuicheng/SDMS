package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdms.dao.admin.DormitoryDao;
import sdms.dao.admin.StudentDao;
import sdms.dto.admin.StudentDto;
import sdms.entity.admin.Building;
import sdms.entity.admin.Dormitory;
import sdms.entity.admin.Profession;
import sdms.entity.admin.Student;
import sdms.service.admin.IBuildingService;
import sdms.service.admin.IDormitoryService;
import sdms.service.admin.IProfessionService;
import sdms.service.admin.IStudentService;
import sdms.util.CommonsUtil;
import sdms.util.Page;

import java.util.*;

/**
 * Created by cheng on 2017/1/19.
 */
@Service
public class StudentService implements IStudentService{

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private DormitoryDao dormitoryDao;

    @Autowired
    private IDormitoryService dormitoryService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IProfessionService professionService;



    @Override
    public Student findStudentById(Integer id) {
        if (null == id){
            return null;
        }
        else{
            String hql = "select a from Student a where a.id = ?";
            Object[] params = new Object[1];
            params[0] = id;
            List<Student> list = studentDao.find(hql, params);
            if (list == null || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    @Override
    public Student findStudentByNumber(String  number) {
        if( null == number){
            return null;
        }
        else{
            String hql = "select s from Student s where s.number = ?";
            Object[] param = new Object[1];
            param[0] = number;
            List<Student> list = studentDao.find(hql, param);
            if( null == list || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }
        }
    }

    public boolean addStudent(Student student) {
        if (null == student) {
            return false;
        } else {
            try {
                student.setPassword(CommonsUtil.enPassword(student.getNumber()));
                student.setDormitoryId(0);
                student.setStatus(1);
                student.setPhone("0");
                studentDao.save(student);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean updateStudent(Student student){
        if (null == student){
            return false;
        }
        else{
            Student oldStudent = findStudentById(student.getId());
            if(null == oldStudent){
                return false;
            }
            else{
                oldStudent.setNumber(student.getNumber());
                oldStudent.setName(student.getName());
                oldStudent.setPhone(student.getPhone());
                studentDao.update(oldStudent);
                return true;
            }

        }
    }

    public Page<StudentDto> pageStudent(Integer currentPage, Integer pageSize, Integer departmentId,
                                        Integer grade, Integer professionId,
                                        Integer classNumber, Integer sex, Integer dormitoryId, String name, String number){

        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        Object[] param = new Object[8];
        hql.append("select s.name as name, s.id as id, s.number as number, s.sex as sex, s.phone as phone, " +
                "s.profession_id as professionId, t.name as professionName, " +
                "s.dormitory_id as dormitoryId, s.class_number as classNumber, s.status as status, s.grade as grade from " +
                " (select p.id, p.name, p.departmentId from profession p) t join student s on t.id = s.profession_id " +
                "where s.status = 1");
        countHql.append("select count(*) from" +
                " (select p.id, p.name, p.departmentId from profession p) t join student s on t.id = s.profession_id " +
                "where s.status = 1");

        if ( null != departmentId && departmentId != 0){
            hql.append(" and t.departmentId = ? ");
            countHql.append(" and t.departmentId = ? ");
            param[0] = departmentId;
        }
        if( null != grade && grade != 0){
            hql.append(" and s.grade = ?" );
            countHql.append(" and s.grade = ?");
            param[1] = grade;
        }
        if( null != professionId && professionId != 0){
            hql.append(" and  s.profession_id = ?");
            countHql.append(" and s.profession_id = ?");
            param[2] = professionId;
         }
        if( null != classNumber && classNumber != 0){
            hql.append(" and s.class_number = ?");
            countHql.append(" and s.class_number = ? ");
            param[3] = classNumber;
        }
        if( null != sex && sex != 0){
            hql.append(" and s.sex = ?");
            countHql.append(" and s.sex = ? ");
            if( sex == 1){
                param[4] = 1;
            }else{
                param[4] = 0;
            }
        }
        if( null != dormitoryId && dormitoryId != -1){
            if( dormitoryId == 0){
                hql.append(" and s.dormitory_id = ? ");
                countHql.append(" and s.dormitory_id = ?");
                param[5] = 0;
            }
            else{
                hql.append(" and s.dormitory_id != ? ");
                countHql.append(" and s.dormitory_id != ? ");
                param[5] = 0;
            }
        }
        if( null != name && !name.equals("")){
            hql.append(" and s.name like ?");
            countHql.append(" and s.name like ? ");
            param[6] = "%" + name + "%";
        }
        if( null != number && !number.equals("")){
            hql.append(" and s.number like ? ");
            countHql.append(" and s.number like ? ");
            param[7] = "%" + number + "%";
        }

        List<StudentDto> list = studentDao.pageSQL(hql.toString(), param, currentPage, pageSize, StudentDto.class);
        Long count = studentDao.countSQL(countHql.toString(), param);
        Page<StudentDto> page = new Page<>();
        page.setResults(list);
        page.setTotal(count);
        page.setCurPageNo(currentPage);

        return page;

    }

    public boolean deleteStudent(Integer[] id){
        if(null == id){
            return false;
        }
        else{
            Student student;
            for(int i = 0; i < id.length; i++){
                student = findStudentById(id[i]);
                if(null == student || student.getDormitoryId() != 0){
                    return false;
                }
                else{
                    student.setStatus(0);
                    studentDao.update(student);
                }
            }

            return true;
        }
    }

    public boolean updatePasswordByAdmin(Integer id){
        if( null == id || id == 0 ){
            return false;
        }
        else{
            Student student = findStudentById(id);

            if( null == student){
                return false;
            }
            else{
                student.setPassword(CommonsUtil.enPassword(student.getNumber()));
                studentDao.update(student);
                return true;
            }
        }
    }

    /**
     * 退宿
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public boolean updatePassword(Integer id, String oldPassword, String newPassword){
        if(null == id || 0 == id){
            return false;
        }
        if(null == oldPassword || oldPassword.equals("")){
            return false;
        }
        if(null == newPassword || newPassword.equals("")){
            return false;
        }
        Student student = findStudentById(id);
        if(null == student){
            return false;
        }
        oldPassword = CommonsUtil.enPassword(oldPassword);
        if( !student.getPassword().equals(oldPassword)){
            return false;
        }
        else{
            newPassword = CommonsUtil.enPassword(newPassword);
            student.setPassword(newPassword);
            studentDao.update(student);
            return true;
        }
    }

    public boolean updateDormitory(Integer id){
        if(null == id){
            return false;
        }
        else{
            Student student = findStudentById(id);
            if(null == student){
                return false;
            }
            else{
                if(student.getDormitoryId() == 0){
                    return false;
                }
                else{
                    Dormitory dormitory = dormitoryService.findDormitoryById(student.getDormitoryId());
                    if(null == dormitory){
                        return false;
                    }
                    student.setDormitoryId(0);
                    if((dormitory.getStudentNumber() - 1) < 0){
                        return false;
                    }
                    dormitory.setStudentNumber(dormitory.getStudentNumber() - 1);
                    studentDao.update(student);
                    dormitoryDao.update(dormitory);
                }
            }
        }

        return true;
    }

    public List<Student> findStudentByDormitoryId(Integer dormitoryId){
        if(null == dormitoryId){
            return null;
        }
        else{
            String hql = "select a from Student a  where a.dormitoryId = ?";
            Object[] param = new Object[1];
            param[0] = dormitoryId;
            List<Student> list = studentDao.find(hql, param);
            if(null == list || list.size() == 0){
                return null;
            }
            else{
                return list;
            }
        }
    }

    /**
     * 分配宿舍
     */
    public Integer updateStudentDormitory(Integer studentId, Integer dormitoryId){
        Student student = findStudentById(studentId);
        Dormitory dormitory = dormitoryService.findDormitoryById(dormitoryId);
        if(null == student || student.getDormitoryId() != 0 || null == dormitory){
            return 0;
        }
        if(dormitory.getStudentNumber() >= dormitory.getBedNumber()){
            return 1;
        }
        student.setDormitoryId(dormitoryId);
        dormitory.setStudentNumber(dormitory.getStudentNumber() + 1);
        studentDao.update(student);
        dormitoryDao.update(dormitory);
        return 2;

    }

    public Long countStudentByNumber(String number){
        String hql = "select count(*) from Student s where s.status = 1 and s.number = ?";
        Object[] param = new Object[1];
        param[0] = number;
        return studentDao.count(hql, param);
    }

    public boolean addStudentByExcel(List<List<Object>> list, Integer professionId) throws  NullPointerException{

        if(list.size() == 0){
            return false;
        }
        else{
            for(int i = 0; i < list.size(); i++){
                List studentList = list.get(i);
                if(null == studentList){
                    return false;
                }
                else{

                    Student student = new Student();
                    student.setDormitoryId(0);
                    student.setProfessionId(professionId);
                    student.setGrade(Integer.parseInt(studentList.get(0).toString()));
                    student.setClassNumber(Integer.parseInt(studentList.get(1).toString()));
                    student.setNumber(studentList.get(2).toString());
                    student.setName(studentList.get(3).toString());
                    if(studentList.get(4).toString().equals("男")){
                        student.setSex(1);
                    }
                    else{
                        student.setSex(0);
                    }
                    student.setPhone(studentList.get(5).toString());
                    student.setStatus(1);
                    student.setPassword(CommonsUtil.enPassword(student.getNumber()));

                    studentDao.save(student);

                }
            }
        }

        return true;
    }

    public List<String> findStudentVerifyExcel(List<List<Object>> list) throws NullPointerException{
        if(null == list || list.size() == 0){
            return null;
        }
        else{
            List<String> repeatList = new ArrayList<>();
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i < list.size(); i++){
                List item = list.get(i);
                if(null != item){
                    if(set.contains(item.get(2).toString())){
                        repeatList.add(item.get(2).toString());
                    }
                    else{
                        set.add(item.get(2).toString());
                    }
                }
            }
            return repeatList;
        }
    }

    public List<String> findStudentVerityExcelDataBase(List<List<Object>> list) throws NullPointerException{
        if(null == list || list.size() == 0){
            return null;
        }
        else{
            List<String> repeatList = new ArrayList<>();
            HashSet<String> set = new HashSet<>();
            for(int i = 0; i < list.size(); i++){
                List item = list.get(i);
                if(null != item){
                    Student student = findStudentByNumber(item.get(2).toString());
                    if(null == student){
                        set.add(item.get(2).toString());
                    }
                    else{
                        repeatList.add(item.get(2).toString());
                    }
                }
            }
            return repeatList;
        }
    }

    public boolean updateStudentPhone(Student student){
        if (null == student){
            return false;
        }
        else{
            Student oldStudent = findStudentById(student.getId());
            if(null == oldStudent){
                return false;
            }
            else{
                oldStudent.setPhone(student.getPhone());
                studentDao.update(oldStudent);
            }
        }

        return true;
    }

    public StudentDto findStudentDtoById(Integer id){
        StringBuilder hql = new StringBuilder();
        hql.append("select s.name as name, s.id as id, s.number as number, s.sex as sex, s.phone as phone, " +
                "s.profession_id as professionId, t.name as professionName, " +
                "s.dormitory_id as dormitoryId, s.class_number as classNumber, s.status as status, s.grade as grade from " +
                " (select p.id, p.name, p.departmentId from profession p) t join student s on t.id = s.profession_id " +
                "where s.status = 1 and s.id = ? ");
        Object[] param = new Object[1];
        param[0] = id;
        List<StudentDto> list = studentDao.pageSQL(hql.toString(), param, 1, 10, StudentDto.class);
        if( null == list || list.size() == 0){
            return new StudentDto();
        }
        else{
            return list.get(0);
        }
    }

    public int updateIntelligentDistribution(Integer[] studentId){

        List<Student> studentBoy = new ArrayList<>();
        List<Student> studentGirl = new ArrayList<>();
        List<Building> buildingBoy = null;
        List<Building> buildingGirl = null;
        Student lastStudent;
        Profession profession;
        /*int studentBoyCount = 0;
        int studentGirlCount = 0;*/

        /*
        判断学生是否已经分配，还有学生同属一个年级和专业
         */
        for(int i = 0; i < studentId.length; i++){
            Student student = findStudentById(studentId[i]);
            if (null == student || student.getDormitoryId() != 0){
                return 0;
            }

            if(studentBoy.size() != 0){
                lastStudent = studentBoy.get(studentBoy.size() - 1);
                if(student.getProfessionId().intValue() != lastStudent.getProfessionId().intValue() || student.getGrade().intValue() != lastStudent.getGrade().intValue()){
                    return 0;
                }
            }
            if(studentGirl.size() != 0){
                lastStudent = studentGirl.get(studentGirl.size() - 1);
                if(student.getProfessionId().intValue() != lastStudent.getProfessionId().intValue() || student.getGrade().intValue() != lastStudent.getGrade().intValue()){
                    return 0;
                }
            }

            if(student.getSex() == 1){
                studentBoy.add(student);
            }
            else{
                studentGirl.add(student);
            }
        }

        if(studentBoy.size() != 0){

            //统计剩余床位
            int bedSumBoy = 0;

            Collections.sort(studentBoy, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    Long s1 = Long.parseLong(o1.getNumber());
                    Long s2 = Long.parseLong(o2.getNumber());
                    if(s1 > s2){
                        return 1;
                    }
                    if(s1 == s2){
                        return 0;
                    }
                    return -1;
                }
            });
            profession = professionService.findProfessionById(studentBoy.get(0).getProfessionId());
            buildingBoy = buildingService.listBuildingByProfessionAndSex(profession.getDepartmentId(), studentBoy.get(0).getSex());
            if (null == buildingBoy || buildingBoy.size() == 0) {
                return 1;
            }

            //统计剩余床位
            for(int i = 0; i < buildingBoy.size(); i++){
                bedSumBoy += dormitoryService.countDormitoryRemainBedNumber(buildingBoy.get(i).getId());
            }

            //如果床位不够的话，返回值3;
            if(bedSumBoy < studentBoy.size()){
                return 3;
            }
        }

        if(studentGirl.size() != 0){
            //统计剩余床位
            int bedSumGirl = 0;
            Collections.sort(studentGirl, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    Long s1 = Long.parseLong(o1.getNumber());
                    Long s2 = Long.parseLong(o2.getNumber());
                    if(s1 > s2){
                        return 1;
                    }
                    if(s1 == s2){
                        return 0;
                    }
                    return -1;
                }
            });

            profession = professionService.findProfessionById(studentGirl.get(0).getProfessionId());

            buildingGirl = buildingService.listBuildingByProfessionAndSex(profession.getDepartmentId(), studentGirl.get(0).getSex());

            if(null == buildingGirl || buildingGirl.size() == 0){
                return 2;
            }

            //统计剩余床位
            for(int i = 0; i < buildingGirl.size(); i++){
                bedSumGirl += dormitoryService.countDormitoryRemainBedNumber(buildingGirl.get(i).getId());
            }

            //如果床位不够的话,返回值为4
            if(bedSumGirl < studentGirl.size()){
                return 4;
            }
        }

        if(studentBoy.size() != 0){
            updateIntelligent(buildingBoy, studentBoy);
        }

        if(studentGirl.size() != 0){
            updateIntelligent(buildingGirl, studentGirl);
        }

        return 5;

        /*Profession profession = professionService.findProfessionById(studentBoy.get(0).getProfessionId());

        List<Building> buildingBoy = buildingService.listBuildingByProfessionAndSex(profession.getDepartmentId(), studentBoy.get(0).getSex());

        List<Building> buildingGirl = buildingService.listBuildingByProfessionAndSex(profession.getDepartmentId(), studentGirl.get(0).getSex());

        if (null == buildingBoy || buildingBoy.size() == 0) {
            return 1;
        }
        if(null == buildingGirl || buildingGirl.size() == 0){
            return 2;
        }

        //统计剩余床位
        int bedSumBoy = 0;
        int bedSumGirl = 0;

        for(int i = 0; i < buildingBoy.size(); i++){
            bedSumBoy += dormitoryService.countDormitoryRemainBedNumber(buildingBoy.get(i).getId());
        }

        for(int i = 0; i < buildingGirl.size(); i++){
            bedSumGirl += dormitoryService.countDormitoryRemainBedNumber(buildingGirl.get(i).getId());
        }

        if(bedSumBoy < studentBoy.size()){
            return 3;
        }
        if(bedSumGirl < studentGirl.size()){
            return 4;
        }

        updateIntelligent(buildingBoy, studentBoy);
        updateIntelligent(buildingGirl, studentGirl);*/


        //为男生分配宿舍
       /*for(int i = 0; i < buildingBoy.size(); i++ ){

           if(studentBoyCount >= studentBoy.size()){
               break;
           }

            List<Dormitory> list = dormitoryService.listDormitoryByBuildingId(buildingBoy.get(i).getId());

            if(null == list || list.size() == 0){
                return 0;
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(studentBoyCount < studentBoy.size()){
                        Dormitory dormitory = list.get(j);
                        if(dormitory.getStudentNumber() == dormitory.getBedNumber()){
                            break;
                        }
                        else{
                            int residueBedNumber = dormitory.getBedNumber() - dormitory.getStudentNumber();
                            for(int k = 0; k < residueBedNumber; k++){
                                if(studentBoyCount < studentBoy.size()){
                                    Student student = studentBoy.get(studentBoyCount);
                                    student.setDormitoryId(dormitory.getId());
                                    dormitory.setStudentNumber(dormitory.getStudentNumber() + 1);
                                    studentDao.update(student);
                                    studentBoyCount++;
                                }else{
                                    break;
                                }
                            }
                            dormitoryDao.update(dormitory);
                        }
                    }
                    else{
                        break;
                    }

                }
            }
        }*/

        //为女生分配宿舍

    }


    //智能分配宿舍，主要是往数据库写数据
    public int updateIntelligent(List<Building> building, List<Student> studentList){

        int studentBoyCount = 0;

        for(int i = 0; i < building.size(); i++ ){

            if(studentBoyCount >= studentList.size()){
                break;
            }

            List<Dormitory> list = dormitoryService.listDormitoryByBuildingId(building.get(i).getId());

            if(null == list || list.size() == 0){
                return 0;
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(studentBoyCount < studentList.size()){
                        Dormitory dormitory = list.get(j);
                        if(dormitory.getStudentNumber() == dormitory.getBedNumber()){
                            continue;
                        }
                        else{
                            int residueBedNumber = dormitory.getBedNumber() - dormitory.getStudentNumber();
                            for(int k = 0; k < residueBedNumber; k++){
                                if(studentBoyCount < studentList.size()){
                                    Student student = studentList.get(studentBoyCount);
                                    student.setDormitoryId(dormitory.getId());
                                    dormitory.setStudentNumber(dormitory.getStudentNumber() + 1);
                                    studentDao.update(student);
                                    studentBoyCount++;
                                }else{
                                    break;
                                }
                            }
                            dormitoryDao.update(dormitory);
                        }
                    }
                    else{
                        break;
                    }

                }
            }
        }

        return 5;
    }

    public Long countStudentByProfessionId(Integer professionId){
        String hql = "select count(*) from Student s where s.status = 1 and s.professionId = ?";
        Object[] param = new Object[1];
        param[0] = professionId;
        return studentDao.count(hql, param);
   }



}
