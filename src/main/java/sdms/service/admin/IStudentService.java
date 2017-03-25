package sdms.service.admin;

import sdms.dto.admin.StudentDto;
import sdms.entity.admin.Student;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/19.
 */
public interface IStudentService {

    Student findStudentById(Integer id);

    Student findStudentByNumber(String number);

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    Page<StudentDto> pageStudent(Integer currentPage, Integer pageSize, Integer departmentId,
                                 Integer grade, Integer professionId, Integer classNumber,
                                 Integer sex, Integer dormitoryId, String name, String number);

    boolean updatePasswordByAdmin(Integer id);

    boolean updatePassword(Integer id, String oldPassword, String newPassword);

    boolean updateDormitory(Integer id);

    boolean deleteStudent(Integer[] id);

    List<Student> findStudentByDormitoryId(Integer dormitoryId);

    Integer updateStudentDormitory(Integer studentId, Integer dormitoryId);

    Long countStudentByNumber(String number);

    boolean addStudentByExcel(List<List<Object>> list, Integer professionId) throws  NullPointerException;

    List<String> findStudentVerifyExcel(List<List<Object>> list) throws NullPointerException;

    List<String> findStudentVerityExcelDataBase(List<List<Object>> list) throws NullPointerException;

    boolean updateStudentPhone(Student student);

    StudentDto findStudentDtoById(Integer id);

    int updateIntelligentDistribution(Integer[] studentId);

    Long countStudentByProfessionId(Integer professionId);

}
