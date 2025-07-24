package com.spring.boot.springbootjavaexam2.Service;

import com.spring.boot.springbootjavaexam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String iD, User user) {
        user.setID(iD); // prevent user from changing the iD
        for (User u : users) {
            if (u.getID().equals(iD)) {
                users.set(users.indexOf(u), user);
                return true;
            }
        }

        return false;
    }

    public boolean deleteUser(String iD) {
        for (User u : users) {
            if (u.getID().equals(iD)) {
                users.remove(u);
                return true;
            }
        }

        return false;
    }

    public ArrayList<User> usersAboveBalance(double balance) {
        ArrayList<User> aboveBalanceList = new ArrayList<>();

        for (User u : users) {
            if (u.getBalance() >= balance) {
                aboveBalanceList.add(u);
            }
        }

        return aboveBalanceList;
    }

    public ArrayList<User> usersAboveAge(int age) {
        ArrayList<User> aboveAgeList = new ArrayList<>();

        for (User u : users) {
            if (u.getAge() >= age) {
                aboveAgeList.add(u);
            }
        }

        return aboveAgeList;
    }


}
