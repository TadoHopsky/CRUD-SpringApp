package webapp.app.crudsringbootmain.DAO;

import java.util.Map;

public interface GeneratorUser {
    Map<Integer, String> generateUsername();

    String getOneRandomUsername();
}
