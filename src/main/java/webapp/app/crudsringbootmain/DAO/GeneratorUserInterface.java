package webapp.app.crudsringbootmain.DAO;

import java.util.Map;

public interface GeneratorUserInterface {
    Map<Integer, String> generateUsername();

    String getOneRandomUsername();
}
