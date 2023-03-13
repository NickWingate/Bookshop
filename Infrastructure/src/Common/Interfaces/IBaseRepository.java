package Common.Interfaces;

import java.util.List;

public interface IBaseRepository<T>  {
    public List<T> GetAll();
    public T Get();
    public boolean Add(T entity);
    public boolean Update(T entity);
    public boolean Delete(T entity);

}
