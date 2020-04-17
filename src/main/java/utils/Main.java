package utils;

import dao.DaoImplementation;
import entities.Discipline;
import entities.Role;
import entities.Task;
import entities.User;
import enums.Status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    private static DaoImplementation dao = new DaoImplementation();

    public static void main(String[] args) {
        List<Role> roles = rolesFactory();
        dao.toDatabase(roles);
        List<User> users = usersFactory(roles);
        dao.toDatabase(users);
        List<Task> tasks = tasksFactory(users.get(0));
        dao.toDatabase(tasks);
        List<Discipline> disciplines = disciplinesFactory(users,users.get(0));
        dao.toDatabase(disciplines);

        // ⦁	List all users from a discipline (let’s say Applications Management).
        List<User> list= dao.getUserByRoleName();
        System.out.println("Engineers");
        for (User u : list){
            System.out.println(u);
        }

        //        ⦁	List all users that have tasks in TODO status.
        System.out.println("--------------------------------------------------------------------------------");
        List<User> list1= dao.getUsersWithTaskInTodoStatus();
        System.out.println("TODO status");
        for (User u : list1){
            System.out.println(u);
        }

      dao.changeTheHeadOfDiscipline(users.get(3), disciplines.get(2));

       dao.deleteUser(users.get(1));


    }

    public static List<Discipline> disciplinesFactory(List<User> users, User user){
        List<Discipline> list = new ArrayList<>();
        list.add(new Discipline("Application Management", new HashSet<User>(Arrays.asList(users.get(0),users.get(1))), user));
        list.add(new Discipline("Development", new HashSet<>(users), user));
        list.add(new Discipline("Testing", new HashSet<>(users), user));
        return list;
    }

    public static List<Role> rolesFactory(){
        List<Role> list = new ArrayList<>();
        list.add(new Role("Project Manager"));
        list.add(new Role("Engineer"));
        list.add(new Role("Scrum master"));
        return list;
    }

    public static List<Task> tasksFactory(User user){
        List<Task> list = new ArrayList<>();
        list.add(new Task("Write a java class", "Class should contain getters and setters", new Date(1999,9, 2),
                new Date(2020,8,12), Status.TODO, user));
        list.add(new Task("Write a python class", "Class should contain 5 fields", new Date(2009,6, 5),
                new Date(2019,3,16), Status.DONE, user));
        list.add(new Task("Write a javascipt class", "Class should contain a date variable", new Date(2007,5, 8),
                new Date(2018,5,19), Status.PROGRESS, user));
        list.add(new Task("Write a jango class", "Class should contain jango shit in it", new Date(2011,9, 2),
                new Date(2012,8,6), Status.BLOCKED, user));
        return list;
    }

    public static List<User> usersFactory(List<Role> role){
        List<User> list = new ArrayList<>();
        list.add(new User("Dacian", "Rusu", "dacian.rusu@endava.com","dacrusu", new Date(2020, 3, 14), true, new HashSet<Role>(Arrays.asList(role.get(0), role.get(1)))));
        list.add(new User("Nikita", "Ganja", "nikita.ganja@endava.com","nganja", new Date(2020, 3, 14), true, new HashSet<Role>(Arrays.asList(role.get(0), role.get(1)))));
        list.add(new User("Iulian", "Cuciuc", "iulian.cuciuc@endava.com","icuciuc", new Date(2020, 3, 14), true, new HashSet<Role>(Arrays.asList(role.get(0), role.get(1)))));
        list.add(new User("Denis", "Gurduza", "denis.gurduza@endava.com","dgurduza", new Date(2020, 3, 14), true, new HashSet<Role>(Arrays.asList(role.get(0), role.get(1)))));
        list.add(new User("Vasya", "Pupkin", "vas.pup@endava.com","vpupkin", new Date(2020, 1, 10), true, new HashSet<Role>(Arrays.asList(role.get(0)))));
    return list;
    }



}
