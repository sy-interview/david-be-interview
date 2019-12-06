package models;

import io.ebean.*;
import play.data.validation.Constraints;
import scala.Int;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name="Audits")
public class Audit extends Model {

    @Id
    @Constraints.Required
    public Long id;
    @Constraints.Required
    public ZonedDateTime request_time;
    @Constraints.Required
    public Long user_id;
    @Constraints.Required
    public String request_body;
    @Constraints.Required
    public String url;
    @Constraints.Required
    public Integer http_status;

    public static final Finder<Long, Audit> find = new Finder<>(Audit.class);

}
