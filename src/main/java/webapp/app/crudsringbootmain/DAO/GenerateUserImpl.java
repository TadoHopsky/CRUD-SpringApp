package webapp.app.crudsringbootmain.DAO;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class GenerateUserImpl implements GeneratorUser {

    public Map<Integer, String> generateUsername() {
        Map<Integer, String> fullInfoMap = new HashMap<>();

        String name = "Алексей, Мария, Иван, Ольга, Дмитрий, Анна, Сергей, Екатерина, Владимир, Наталья, Артем," +
                " Елена, Михаил, Татьяна, Андрей, Юлия, Роман, Виктория, Павел, Анастасия, Константин, Ирина," +
                " Александр, Валерия, Евгений, София, Василий, Ксения, Николай, Вероника";

        String surname = "Иванов, Петров, Сидоров, Смирнов, Кузнецов, Попов, Васильев, Новиков, Федоров, Морозов," +
                " Волков, Алексеев, Лебедев, Семенов, Егоров, Павлов, Козлов, Степанов, Николаев, Орлов, Макаров," +
                " Захаров, Кравцов, Беляев, Гусев, Андреев, Данилов, Сергиев, Тимофеев, Филиппов";

        String[] names = name.split(", ");
        String[] surnames = surname.split(", ");

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String randomName = names[random.nextInt(names.length)];
            String randomSurname = surnames[random.nextInt(names.length)];

            String fullName = randomName + " " + randomSurname;

            fullInfoMap.put(i, fullName);
        }
        return fullInfoMap;
    }

    public String getOneRandomUsername() {
        Map<Integer, String> map = generateUsername();

        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(map.size());

        for (int i = 0; i < map.size(); i++) {
            if (randomNumber == i) {
                return map.get(i);
            }
        }
        return null;
    }
}
