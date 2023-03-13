package Common.Interfaces;

import Entities.User;

public interface IUserRepository extends IBaseRepository<User> {
    public boolean DeleteById(int id);
}
