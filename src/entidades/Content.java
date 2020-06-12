package entidades;

import java.util.Date;

public class Content {
    private int id;
    private int news_agency_id; // private NewsAgency news_agency
    private int user_id; // private User user
    private Date modified;
    private Date created;

    public Content(int id, int news_agency_id, int user_id, java.sql.Date modified, java.sql.Date created) {
        this.id = id;
        this.news_agency_id = news_agency_id;
        this.user_id = user_id;
        this.modified = modified;
        this.created = created;
    }

    public int getId() {
        return this.id;
    }

    public int getNewsAgencyId() {
        return this.news_agency_id;
    }

    public void setNewsAgencyId(int news_agency_id) {
        this.news_agency_id = news_agency_id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public Date getModified() {
        return this.modified;
    }

    public Date getCreated() {
        return this.created;
    }

}
