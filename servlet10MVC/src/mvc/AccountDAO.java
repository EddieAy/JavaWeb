package mvc;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public int insert(Account act){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into bank(actno,balance) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,act.getActno());
            preparedStatement.setDouble(2,act.getBalance());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return count;
    };
    public int deleteById(Long id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from bank where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return count;
    };
    public int update(Account act){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "update bank set balance = ?,actno = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,act.getBalance());
            preparedStatement.setString(2,act.getActno());
            preparedStatement.setLong(3,act.getId());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return count;
    };

    public Account selectByActno(String actno){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account act = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select id,actno,balance from bank where actno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,actno);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Long id = resultSet.getLong("id");
                Double balance = resultSet.getDouble("balance");
                act = new Account(id,actno,balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return act;
    };
    public List<Account> selectAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Account> res = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select id,actno,balance from bank";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String actno = resultSet.getString("actno");
                Double balance = resultSet.getDouble("balance");
                Account act = new Account(id,actno,balance);
                res.add(act);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        return res;
    };
}
