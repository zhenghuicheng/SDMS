package sdms.service.admin;

import sdms.dto.admin.ProfessionDto;
import sdms.entity.admin.Profession;
import sdms.util.Page;

import java.util.List;

/**
 * Created by cheng on 2017/1/17.
 */
public interface IProfessionService {

    Profession findProfessionByNumber(String number);

    boolean addProfession(Profession profession);

    Page<ProfessionDto>  pageProfession(Integer currentPage, Integer pageSize, Integer departmentId, String name);

    boolean updateProfession(Profession profession);

    boolean deleteProfession(Integer[] id);

    Profession findProfessionById(Integer id);

    List<Profession> listByDepartment(Integer departmentId);

    Long countProfessionByName(String name);

    Long countProfessionByNumber(String number);

    Long countProfessionByDepartmentId(Integer id);
}
