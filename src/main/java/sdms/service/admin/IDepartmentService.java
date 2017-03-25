package sdms.service.admin;

import sdms.entity.admin.Department;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/14.
 */
public interface IDepartmentService {

    boolean addDepartment(Department department);

    Department findDepartmentByNumber(String number);

    Page<Department> pageDepartment(Integer currentPage, Integer pageSize, Integer number, String name);

    boolean updateDepartment(Department department);

    Department findDepartmentById(Integer id);

    boolean deleteDepartment(Integer[] id);

    List<Department> listDepartment();

    Long countDepartmentByName(String name);

    Long countDepartmentByNumber(String number);

}
