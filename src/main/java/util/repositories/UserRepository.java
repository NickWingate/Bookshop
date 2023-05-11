package main.java.util.repositories;

import main.java.domain.entities.Book;
import main.java.domain.entities.User;
import main.java.util.interfaces.ICSVWriter;
import main.java.util.interfaces.IUserParser;
import main.java.util.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private IUserParser _userParser;
    private ICSVWriter<User> _userWriter;
    private String _storeFilePath;

    public UserRepository(IUserParser userParser,
                          ICSVWriter<User> userWriter,
                          String storeFilePath) {
        _userParser = userParser;
        _userWriter = userWriter;
        _storeFilePath = storeFilePath;
    }

    @Override
    public List<User> GetAll() {
        return _userParser.ParseFile(new File(_storeFilePath));
    }

    @Override
    public User Get(String id) {
        var users = GetAll();

        for (var user : users) {
            if (user.getId() == Integer.valueOf(id)){
                return user;
            }
        }

        return null;
    }

    @Override
    public boolean Add(User entity) {
        var users = GetAll();

        users.add(entity);

        return Save(users);
    }

    @Override
    public boolean Update(String id, User entity) {
        var users = GetAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == Integer.valueOf(id)){
                users.set(i, entity);
            }
        }

        return Save(users);
    }

    @Override
    public boolean Delete(String id) {
        var users = GetAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == Integer.valueOf(id)){
                users.remove(i);
            }
        }

        return Save(users);
    }

    private boolean Save(List<User> users) {
        return _userWriter.WriteToFile(_storeFilePath, users);
    }
}
