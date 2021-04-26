package dao.impl;

import dao.TransferDao;
import entity.Transfer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utility.DataCredentials.*;

/**
 * Created by Moon on 25/04/2021
 */
public class TransferDaoImpl implements TransferDao {

    @Override
    public int save(Transfer transfer) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql= "INSERT INTO javadevt_genk_08.Transfer (fundFromAccountId, fundToAccountId, createdAt, amount) VALUES (?, ?, ?,?)";

            PreparedStatement ps= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, transfer.getFundFromAccountId());
            ps.setLong(2, transfer.getFundToAccountId());
            ps.setTimestamp(3, getCurrentTimeStamp());
            ps.setDouble(4, transfer.getAmount());

            int result= ps.executeUpdate();
            int generatedId = -1;
            if (result > 0) {
                try {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        generatedId = (int) rs.getLong(1);

                    }

                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                    throw  new RuntimeException(throwables);
                }
            }
            return generatedId;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }


    @Override
    public List<Transfer> getTransfersByFromCustomerId(Long accountFromId) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Transfer WHERE fundFromAccountId = ?");
            ps.setLong(1, accountFromId);
            ResultSet rs= ps.executeQuery();
            List<Transfer> transfers = new ArrayList<>();

            while(rs.next()){
                transfers.add(new Transfer(rs.getLong("id"),
                        rs.getLong("fundFromAccountId"),
                        rs.getLong("fundToAccountId"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getLong("amount")));
            }
            return transfers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }

    @Override
    public List<Transfer> getTransfersByToCustomerId(Long accountToId) {
        try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement ps= conn.prepareStatement("SELECT * FROM Transfer WHERE fundToAccountId = ?");
            ps.setLong(1, accountToId);
            ResultSet rs= ps.executeQuery();
            List<Transfer> transfers = new ArrayList<>();

            while(rs.next()){
                transfers.add(new Transfer(rs.getLong("id"),
                        rs.getLong("fundFromAccountId"),
                        rs.getLong("fundToAccountId"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getLong("amount")));
            }
            return transfers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException(throwables);
        }
    }


}
