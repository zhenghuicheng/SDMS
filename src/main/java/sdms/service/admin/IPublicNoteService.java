package sdms.service.admin;

import sdms.dto.admin.PublicNoteDto;
import sdms.entity.admin.PublicNote;
import sdms.util.Page;

/**
 * Created by cheng on 2017/1/22.
 */
public interface IPublicNoteService {

    boolean addPublicNote(PublicNote publicNote);

    Page<PublicNoteDto>  pagePublicNote(Integer currentPage, Integer pageSize, String title, String startTime, String endTime);

    PublicNote findPublicNoteById(Integer id);

    boolean deletePublicNote(Integer[] id);

    boolean updatePublicNote(PublicNote publicNote);
}
