package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // 1. Создание таблицы User(ов)
        userService.createUsersTable();

        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль (User с именем – name добавлен в базу данных)
        User user = new User("Иван", "Иванов", (byte) 26);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");

        user = new User("Петр", "Петров", (byte) 15);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");

        user = new User("Василий", "Васильев", (byte) 33);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");

        user = new User("Елена", "Журавлева", (byte) 22);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");

        // 3. Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        userService.getAllUsers().forEach(System.out::println);

        // 4. Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // 5. Удаление таблицы
        userService.dropUsersTable();
    }
}
