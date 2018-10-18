package Core.Lesson27;

import java.util.LinkedList;
import java.util.List;

public class UserRepository {
    private List<User> list = new LinkedList<>();

    public User save(User user) {
        if (user != null) {
            list.add(user);
        }
        return user;
    }

    public User update(User user) {
        if (user != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == user.getId()) {
                    list.set(i, user);
                    return list.get(i);
                }
            }
        }
        return null;
    }

    public void delete(User user) {
            list.remove(user);
    }

    public User findById(long id) {
        for (User aList : list) {
            if (aList.getId() == id) {
                return aList;
            }
        }
        return null;
    }
}
