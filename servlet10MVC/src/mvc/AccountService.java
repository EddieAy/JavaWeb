package mvc;

import exceptions.AppException;
import exceptions.MoneyNotEnough;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();

    public void transfer(String fromActno,String toActno,double money) throws MoneyNotEnough, AppException {

        //开启事务(需要Connection)
        try(Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false);

            Account account = accountDAO.selectByActno(fromActno);
            if (account.getBalance() < money) {
                throw new MoneyNotEnough("对不起，余额不足");
            }
            Account toAccount = accountDAO.selectByActno(toActno);

            account.setBalance(account.getBalance() - money);
            toAccount.setBalance(toAccount.getBalance() + money);

            int count = 0;
            count+=accountDAO.update(account);
            String s = null;
            s.toString();
            count+=accountDAO.update(toAccount);
            if (count != 2) {
                throw new AppException("账户操作异常");
            }

            connection.commit();
        } catch (SQLException e) {
            throw new AppException("账户操作异常");
        }


    }
}
