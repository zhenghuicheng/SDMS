package sdms.serviceImpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scau.zxck.base.utils.DateUtil;
import sdms.dao.admin.PublicNoteDao;
import sdms.dto.admin.PublicNoteDto;
import sdms.entity.admin.PublicNote;
import sdms.service.admin.IPublicNoteService;
import sdms.util.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by cheng on 2017/1/22.
 */
@Service
public class PublicNoteService implements IPublicNoteService{

    @Autowired
    private PublicNoteDao publicNoteDao;

    public boolean addPublicNote(PublicNote publicNote){
        if (null == publicNote){
            return false;
        }
        else{
            publicNote.setPublicTime(DateUtil.getSimpleDate(new Date()));
            publicNote.setStatus(1);
            publicNoteDao.save(publicNote);
            return true;
        }
    }

    @Override
    public Page<PublicNoteDto> pagePublicNote(Integer currentPage, Integer pageSize, String title, String startTime, String endTime) {

        StringBuilder hql = new StringBuilder();
        StringBuilder countHql = new StringBuilder();
        Object[] params = new Object[3];

        hql.append("select p.id as id, p.title as title, p.context as context, p.public_time as publicTime, p.adminId as adminId," +
                "p.status as status, a.name as adminName " +
                "from public_note p join admin a on p.adminId = a.id " +
                "where p.status = 1 ");
        countHql.append("select count(*) from public_note p where p.status = 1");

        if (null != title && !title.equals("")){
            hql.append(" and p.title like ? ");
            countHql.append(" and p.title like ? ");
            params[0] = "%" + title + "%";
        }
        if (null != startTime && !startTime.equals("")){
            hql.append(" and p.public_time >= ? ");
            countHql.append(" and  p.public_time >= ? ");
            params[1] = startTime;
        }
        if (null != endTime && !endTime.equals("")){
            hql.append(" and p.public_time <= ? ");
            countHql.append(" and  p.public_time <= ? ");
            params[2] = endTime;
        }
        hql.append(" order by p.public_time");
       /* String hql = "select p.id as id, p.title as title, p.context as context, p.public_time as publicTime, p.adminId as adminId," +
                "p.status as status, a.name as adminName " +
                "from public_note p join admin a on p.adminId = a.id " +
                "where p.status = 1 order by p.public_time desc";
        String counthql = "select count(*) from PublicNote p where p.status = 1";*/


        List<PublicNoteDto> list = publicNoteDao.pageSQL(hql.toString(), params, currentPage, pageSize, PublicNoteDto.class);
        Long count = publicNoteDao.countSQL(countHql.toString(), params);
        Page<PublicNoteDto> page = new Page<>();
        page.setResults(list);
        page.setTotal(count);

        return page;

    }

    public PublicNote findPublicNoteById(Integer id){
        if( id == null){
            return null;
        }
        else{
            String hql = "select a from PublicNote a where a.id = ?";
            Object[] param = new Object[1];
            param[0] = id;
            List<PublicNote> list = publicNoteDao.find(hql, param);
            if (list == null || list.size() == 0){
                return null;
            }
            else{
                return list.get(0);
            }

        }
    }

    public boolean deletePublicNote(Integer[] id){
        if( id == null || id.length == 0){
            return false;
        }
        else{
            PublicNote publicNote;

            for(int i = 0; i < id.length; i++){
                publicNote = findPublicNoteById(id[i]);
                publicNote.setStatus(0);
                try{
                    publicNoteDao.update(publicNote);
                }
                catch (Exception e){
                    return false;
                }
            }

            return true;
        }
    }

    public boolean updatePublicNote(PublicNote publicNote){
        if ( null == publicNote || null == publicNote.getId()){
            return false;
        }
        else{
            PublicNote oldPublicNote = findPublicNoteById(publicNote.getId());
            if ( null == oldPublicNote){
                return false;
            }
            else{
                oldPublicNote.setContext(publicNote.getContext());
                oldPublicNote.setTitle(publicNote.getTitle());
                publicNoteDao.update(oldPublicNote);
                return true;
            }
        }
    }
}
