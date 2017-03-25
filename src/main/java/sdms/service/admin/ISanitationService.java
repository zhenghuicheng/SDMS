package sdms.service.admin;

import sdms.dto.admin.SanitationDto;
import sdms.entity.admin.Sanitation;
import sdms.util.Page;

/**
 * Created by cheng on 2017/2/2
 */
public interface ISanitationService {

    Sanitation findSanitationById(Integer id);

    boolean addSanitation(Sanitation sanitation);

    boolean deleteSanitation(Integer[] id);

    boolean updateSanitation(Sanitation sanitation);

    Page<SanitationDto> pageSanitation(Integer currentPage, Integer pageSize,
                                       Integer buildingId, Integer dormitoryId,
                                       String startTime, String endTime, Integer degree);

}
