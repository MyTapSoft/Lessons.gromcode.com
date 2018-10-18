package Core.Lesson35.Model;

import Core.Lesson35.DAO.IdEntity;

public class User extends IdEntity {
    private long id;
    private String name;
    private String password;
    private String country;
    private UserType type;
    private boolean loginStatus;

    public User(String userName, String password, String country, UserType type) {
        this.name = userName;
        this.password = password;
        this.country = country;
        this.type = type;
        loginStatus = false;
        id = idGenerator();
    }

    public User(User user) {
        id = user.getId();
        name = user.getName();
        password = user.getPassword();
        country = user.getCountry();
        type = user.getType();
        loginStatus = user.isLoginStatus();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public UserType getType() {
        return type;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + "," + getName() + "," + getPassword() + "," + getCountry() + "," + String.valueOf(getType() + "," + String.valueOf(isLoginStatus()));
    }

    private long idGenerator() {
        StringBuilder builder = new StringBuilder();
        long result;
        char[] value = password.toCharArray();
        for (char ch : value) {
            builder.append((int) ch);
        }

        while (builder.length() > 18) {
            builder.deleteCharAt(builder.length() - 1);
        }
        result = Long.parseLong(builder.toString());
        value = (name + country + type + password).toCharArray();
        for (char ch : value) {
            result += ch;
        }
        return result;
    }
}
