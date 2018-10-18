package SQL.Lesson6.Task4;

import java.util.Date;

public class TorumPollsOptionsotes {
    private int id;
    private TorumPollsOptions idPollsOptions;
    private Users idUser;
    private Date dates;
    private String ip;

    public TorumPollsOptionsotes(int id, TorumPollsOptions idPollsOptions, Users idUser, Date dates, String ip) {
        this.id = id;
        this.idPollsOptions = idPollsOptions;
        this.idUser = idUser;
        this.dates = dates;
        this.ip = ip;
    }
}
