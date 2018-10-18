package SQL.Lesson6.Task4;

import java.util.Date;

public class Users {
    private int id;
    private String nickname;
    private String password;
    private String email;
    private Date dates;
    private int carma;
    private String ip;

    public Users(int id, String nickname, String password, String email, Date dates, int carma, String ip) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.dates = dates;
        this.carma = carma;
        this.ip = ip;
    }
}
