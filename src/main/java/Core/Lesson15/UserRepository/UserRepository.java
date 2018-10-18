package Core.Lesson15.UserRepository;

public class UserRepository {
    private User[] users = new User[10];

    public User[] getUsers() {
        return users;
    }

    public User save(User user) {
        if (user != null) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) {
                    users[i] = user;
                    return user;
                }
            }
        }
        return null;
    }

    public User update(User user) {
        if (user != null) {
            for (int i = 0; i < users.length; i++) {
                if (user.getId() == users[i].getId() && !user.equals(users[i])) {
                    users[i] = user;
                    return user;
                }

            }
        }

        return null;
    }

    public void delete(long id) {
        for (int i = 0; i < users.length; i++) {
            if (userFindById(id).equals(users[i])) {
                users[i] = null;
                break;
            }
        }
    }

    public User userFindById(long id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        return null;
    }
}
