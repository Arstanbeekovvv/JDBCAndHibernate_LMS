package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private Connection connection = Util.getConnection();
    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                    create table if not exists Users(
                    id serial primary key,
                    first_name varchar,
                    last_name varchar,
                    age int
                    );
                    """);
            statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "drop table users;";
        try{
            connection.createStatement().executeUpdate(query);
            System.out.println("Successfully deleted table!");
        }catch (SQLException  e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = """
                insert into users (first_name, last_name, age)
                values(?,?,?);
                """;
        int execute = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            execute = ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(execute != 0? "successfully saved user" + name : "not all success!");
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "select * from users;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "truncate table users;";
        try {
            connection.createStatement().executeUpdate(query);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}