package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import models.Person;
import models.User;
import play.data.Form;
import java.util.List;

import static play.libs.Json.*;

@SuppressWarnings("unused")
public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

    @Transactional
    public Result addPerson() {
        Person person = Form.form(Person.class).bindFromRequest().get();
        JPA.em().persist(person);
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getUsers() {
        @SuppressWarnings("unchecked")
		List<User> users = (List<User>) JPA.em().createQuery("select * from User").getResultList();
        return ok(toJson(users));
    }
}
