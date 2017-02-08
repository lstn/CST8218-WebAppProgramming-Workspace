package models;

import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import org.mindrot.jbcrypt.*;
import com.avaje.ebean.Model;

@Entity
public class User extends Model {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public String id;

    @Length(min = 3, max = 16)
    public String userName;
    
    @Length(min = 5)
    @NotBlank
    @NotEmpty
    public Email email;
    
    @NotEmpty
    @NotBlank
    public String passwordHash;
    
    public static Finder<Long, User> find = new Finder<>(User.class);
    
    public static User create(String userName, String password) {
        User user = new User();
        user.userName = userName;
        user.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        user.save();
        return user;
    }
    
    public static User authenticate(String userName, String password) {
        User user = User.find.where().eq("userName", userName).findUnique();
        
        if (user != null && BCrypt.checkpw(password, user.passwordHash)) {
        	return user;
        } else {
        	return null;
        }
    }
    
}
