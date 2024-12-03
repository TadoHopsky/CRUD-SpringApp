package webapp.app.crudsringbootmain.util.batch;

import java.util.Map;

public interface GeneratorUser {
    Map<Integer, String> generateUsername();

    String getOneRandomUsername();
}
