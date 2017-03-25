package sdms.service.admin;

import java.io.IOException;

/**
 * Created by cheng on 2017/3/7.
 */
public interface IDataBaseService {

    String save(String path) throws IOException, InterruptedException;

    int backup(String path, String fileName) throws IOException, InterruptedException;
}
