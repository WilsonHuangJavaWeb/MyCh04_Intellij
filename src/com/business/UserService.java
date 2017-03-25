package com.business;

import com.dao.UserDAO;
import com.persistence.User;

import org.hibernate.service.spi.ServiceException;

import java.util.List;

/**
 * Created by ki264 on 2017/3/25.
 */
public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void addUser(User user) {
        if (user == null) {
            String userName = user.getUserName();
            User u = userDAO.getUserByUserName(userName);
            if (u != null) {
                throw new ServiceException("該使用者已存在，請更換使用者名稱!");
            } else {
                userDAO.makePersistent(user);
            }
        }
    }


    public void updateUser(User user) {
        if (user == null)
            return;
        if (user.getId() == null)
            return;
        userDAO.makePersistent(user);

    }

    public User getUserByID(String ID) {
        if (ID == null) {
            return null;
        }
        return userDAO.getById(ID);
    }

    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    public String getUserIdByName(String userName) {
        return userDAO.getUserByUserName(userName).getId();
    }

    public void deleteUser(User user) {
        if (user == null) {
            return;
        }
        if (user.getId() == null) {
            return;
        }
        userDAO.makePersistent(user);
    }

    public boolean validateUser(String userName, String password) {
        User user = userDAO.getUserByUserName(userName);
        if (user != null) {

            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExist(String userName) {
        User user = userDAO.getUserByUserName(userName);
        return user != null ? true : false;
    }
}
