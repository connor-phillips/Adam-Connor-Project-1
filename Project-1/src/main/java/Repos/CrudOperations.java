package Repos;

import Models.Flight;
import collections.MyList;

import java.sql.SQLException;

public interface CrudOperations<E> {

    /*
    CRUD
    1. Create
    2. Read
    3. Update
    4. Delete
     */

    //create
    //save object to database method
    public void save(E e) throws SQLException;

    void save(Flight flight) throws SQLException;

    //read
    //query data from database, fill in empty model object
    public E getItemByID(int id) throws SQLException;
    public MyList<E> getAllItems() throws SQLException;
    public MyList<E> getAllItems(E e) throws SQLException;
    //public ToDoItem getItemByKeyword(String keyword); //SELECT * FROM items WHERE message LIKE "%KEYWORD%"
    //update
    // we will use the save() method for updates
    //delete
    //remove by ID
    public void deleteByID(int id) throws SQLException;
}
