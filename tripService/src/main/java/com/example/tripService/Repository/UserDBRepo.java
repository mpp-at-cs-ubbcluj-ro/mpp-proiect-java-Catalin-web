package com.example.tripService.Repository;

import com.example.tripService.Domain.User;
import com.example.tripService.Utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@org.springframework.stereotype.Repository
public class UserDBRepo implements Repository<Integer, User> {
    @Autowired
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    @Override
    public void adauga(User user) {
        logger.traceEntry("saving user {} ",user);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into user(email, parola) values (?,?)")){
            preStmt.setString(1, user.getEmail());
            preStmt.setString(2, user.getParola());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void sterge(User user) {
        logger.traceEntry("deleting user {} ",user);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from user where id=?")){
            preStmt.setInt(1, user.getId());
            int result=preStmt.executeUpdate();
            logger.trace("Deleted {} instances",result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public User cautaId(Integer id) {
        List<User> userLst = getAll();
        for (User u:userLst){
            if (u.getId().equals(id))
                return u;
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        logger.traceEntry("get all user");
        Connection con=dbUtils.getConnection();
        List<User> userLst = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from user"))
        {
            try(ResultSet result = preStmt.executeQuery())
            {
                while(result.next())
                {
                    Integer id = result.getInt("id");
                    String email = result.getString("email");
                    String parola = result.getString("parola");

                    User user = new User(id,email,parola);
                    userLst.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(userLst);
        return userLst;
    }

    @Override
    public void update(User user, User nouUser) {
        logger.traceEntry("updating user {} into {}",user,nouUser);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update user set email=?, parola=? where id=?")){
            preStmt.setString(1, nouUser.getEmail());
            preStmt.setString(2, nouUser.getParola());
            preStmt.setInt(3,user.getId());

            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("DB Error"+e);
        }
    }
}
