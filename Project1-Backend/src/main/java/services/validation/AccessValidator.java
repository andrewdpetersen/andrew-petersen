package services.validation;

import models.People;
import services.PeopleService;

public class AccessValidator {

    public int accessLevel(int people_id){
        People person = PeopleService.getPersonById(people_id);
        return person.getAccess_level();
    }

    public void newUserAccessLevel(String username,String password){
        People person = new People();
        person.setUsername(username);
        person.setPassword(password);
        person.setAccess_level(0);
    }

    /**
     * This method is incomplete- add regex to check pattern
     * @param username
     * @return
     */
    public boolean validUsername(String username){
        return false;
    }

    /**
     * his method is incomplete- add regex to check pattern
     * @param password
     * @return
     */
    public boolean validPassword(String password){
        return false;
    }
}
