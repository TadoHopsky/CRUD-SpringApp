package webapp.app.crudsringbootmain.util;

import java.util.Map;

public interface GeneratorUser {
    Map<Integer, String> generateUsername();

    String getOneRandomUsername();
}
