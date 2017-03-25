package sdms.dao.baseDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by cheng on 2016/12/27.
 */
public class BaseDao<T> {

    private SessionFactory sessionFactory;

    public  SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T o){
        return this.getCurrentSession().save(o);
    }

    public void delete(T o){
        this.getCurrentSession().delete(o);
    }

    public void update(T o){

         Session session = this.getCurrentSession();
         session.update(o);
    }

    public List<T> find(String hql){
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List findSQL(String hql, Class T){
        return this.getCurrentSession().createSQLQuery(hql).addEntity(T).list();
    }

    public List findSQL(String hql){
        return this.getCurrentSession().createSQLQuery(hql).list();
    }

    public List<T> find(String hql, Object[] param){
        Query q = this.getCurrentSession().createQuery(hql);
        if(param != null && param.length > 0){
            for (int i = 0; i < param.length; i++){
                q.setParameter(i,param[i]);
            }
        }

        return q.list();
    }

    public List<T> find(String hql, List<Object> param){
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0){
            for (int i = 0; i < param.size(); i++){
                q.setParameter(i,param.get(i));
            }
        }
        return q.list();
    }

    public List<T> page(String hql, Object[] param, Integer page, Integer rows){
        if (page == null || page < 1){
            page = 1;
        }
        if (rows == null || rows < 1){
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0){
            int j = 0;
            for (int i = 0; i < param.length; i++){
                if(param[i] != null){
                    q.setParameter(j, param[i]);
                    j++;
                }

            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List pageSQL(String hql, Object[] param, Integer page, Integer rows, Class V){
        if (page == null || page < 1){
            page = 1;
        }
        if (rows == null || rows < 1){
            rows = 10;
        }
        Query q = this.getCurrentSession().createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(V));
        if (param != null && param.length > 0){
            int j = 0;
            for (int i = 0; i < param.length; i++){
                if(param[i] != null){
                    q.setParameter(j, param[i]);
                    j++;
                }

            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List<T> page(String hql, List<Object> param, Integer page, Integer rows){
        if (page == null || page < 1){
            page = 1;
        }
        if (rows == null || rows < 1){
            rows =1;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0){
            for (int i = 0; i < param.size(); i++){
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows ).setMaxResults(rows).list();
    }

    public Long count(String hql){
        return (Long)this.getCurrentSession().createSQLQuery(hql).uniqueResult();
    }

    public Long count(String hql, Object[] param){
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0){
            int j = 0;
            for (int i = 0; i < param.length; i++){
                if( param[i] != null)
                {
                    q.setParameter(j, param[i]);
                    j++;
                }


            }
        }
        return (Long)q.uniqueResult();
    }

    public Long countSQL(String hql, Object[] param){
        Query q = this.getCurrentSession().createSQLQuery(hql);
        if (param != null && param.length > 0){
            int j = 0;
            for (int i = 0; i < param.length; i++){
                if( param[i] != null){
                    q.setParameter(j, param[i]);
                    j++;
                }

            }
        }
        BigInteger count = (BigInteger) q.uniqueResult();
        return count.longValue();
    }

    public Long count(String hql, List<Object> param){
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0){
            for(int i = 0; i < param.size(); i++){
                q.setParameter(i, param.get(i));
            }
        }
        BigInteger count = (BigInteger) q.uniqueResult();
        return count.longValue();
    }

    public int updateSQL(String hql, Object[] param){
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0){
            for(int i = 0; i < param.length; i++){
                q.setParameter(i, param[i]);
            }
        }

        return q.executeUpdate();
    }

    public Long sumSQL(String hql, Object[] param){
        Query q = this.getCurrentSession().createSQLQuery(hql);
        if (param != null && param.length > 0){
            int j = 0;
            for (int i = 0; i < param.length; i++){
                if( param[i] != null){
                    q.setParameter(j, param[i]);
                    j++;
                }

            }
        }
        BigDecimal count = (BigDecimal) q.uniqueResult();
        return count.longValue();
    }
}
