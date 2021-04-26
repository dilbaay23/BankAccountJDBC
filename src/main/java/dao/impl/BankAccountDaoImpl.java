package dao.impl;

import dao.BankAccountDao;
import entity.BankAccount;
import entity.Customer;


import java.sql.*;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 25/04/2021
 */

public class BankAccountDaoImpl implements BankAccountDao {


    @Override
    public int save(BankAccount bankAccount) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO javadevt_genk_08.BankAccount (customerId, balance) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, bankAccount.getCustomerId());
            ps.setDouble(2, bankAccount.getBalance());
            int result = ps.executeUpdate();
            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


        @Override
        public BankAccount getAccountByCustomerId (Long customerId){
            try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM BankAccount WHERE customerId=?");
                ps.setLong(1, customerId);
                ResultSet rs = ps.executeQuery();
                BankAccount account = null;

                while (rs.next()) {
                    account = new BankAccount(rs.getLong("id"),
                            rs.getLong("customerId"),
                            rs.getDouble("balance"));
                }
                return account;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException(throwables);
            }
        }

    @Override
    public int updateBalance(BankAccount account) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "UPDATE javadevt_genk_08.BankAccount SET balance=? WHERE id=?";

            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setLong(2,account.getId());
            int result= ps.executeUpdate();
            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public int deleteAccount(BankAccount account) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.BankAccount WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,account.getId());
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    @Override
    public int deleteAccountById(int id) {

        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql= "DELETE FROM javadevt_genk_08.BankAccount WHERE id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setLong(1,id);
            return ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

}
