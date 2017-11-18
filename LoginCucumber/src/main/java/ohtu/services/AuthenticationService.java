package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        
        
        // käyttäjätunnuksen on oltava merkeistä a-z koostuva vähintään 3 merkin pituinen merkkijono, joka ei ole vielä käytössä
        // käyttäjätunnus vähintään 3 merkkiä
        if(username.length() < 3){
            return true;
        }

        // käyttäjätunnut vain aakkosia a-z
         if(!username.matches("[a-zA-Z]+")){
            return true;
        }
         
         // salasanan on oltava pituudeltaan vähintään 8 merkkiä ja sen tulee sisältää vähintään yksi numero tai erikoismerkki 
         if(password.length() < 8){
            return true;
        }

        // vähintään yksi numero tai erikoismerkki 
         if(password.matches("[a-zA-Z]+")){
            return true;
        }
        
        return false;
    }
}
