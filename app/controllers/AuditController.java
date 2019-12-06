package controllers;

import io.ebean.Ebean;
import models.Audit;
import models.ErrorMessage;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's audit system.
 */
public class AuditController extends Controller {

    //Simele get request using a user id as a url parameter
    public Result getAuditByUserId(Long userId) {
        try {
            List<Audit> audits = Audit.find.query().where().eq("user_id", userId).findList();;
            if(audits.size() > 0) {
                return ok(Json.toJson(audits));
            } else {
                ErrorMessage errorMessage = new ErrorMessage(404, "No audits found for this user");
                return notFound(Json.toJson(errorMessage));
            }
        }
        catch (Exception ex){
            ErrorMessage error = new ErrorMessage(400, ex.getMessage());
            return badRequest(Json.toJson(error));
        }
    }

    //A simple post request which takes in a body that matches the audit model apart from the id which is auto generated
    //from the DB. I'll leave an example post in the README
    public Result addAudit(Http.Request request){
        try {
            System.out.println("JSON: "+request.body().asJson().toString());
            Audit audit = Ebean.json().toBean(Audit.class, request.body().asJson().toString());
            audit.save();
            return ok("Audit Inserted");
        }
        catch (Exception ex){
            ErrorMessage error = new ErrorMessage(400, ex.getMessage());
            return badRequest(Json.toJson(error));
        }
    }

}