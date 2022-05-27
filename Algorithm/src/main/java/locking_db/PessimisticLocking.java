package locking_db;

import java.sql.*;

public class PessimisticLocking {

    Connection connection;

    public static void pessimisticUpdate(String query){

    }

    public static void main(String[] args) {


        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://10.110.82.7:4000/cus_history?useSSL=false&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
        try {
            Class.forName(myDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection= DriverManager.getConnection(myUrl, "tungdd", "IKtN3nlg2Ea22PQLazgp")){
            String queryT1 ="SELECT * FROM test.test_account WHERE id=1 LOCK IN SHARE MODE";
            String queryUpdateT1 ="UPDATE test.test_account SET amount=1001 WHERE id=1";
            String queryGetT1 ="SELECT * FROM test.test_account WHERE id=1";

            String queryGetT2 ="SELECT * FROM test.test_account WHERE id=2";
            String nameTransaction1="Transaction-1";
            connection.setAutoCommit(false);

            //Checking change result
//            PreparedStatement prepareStatementOriginal=connection.prepareStatement(queryGetT2);
//            ResultSet originalresultSet=prepareStatementOriginal.executeQuery();
//            printInfor(originalresultSet, nameTransaction1);
            //

//            PreparedStatement preparedStatement=connection.prepareStatement(queryT1);
//            ResultSet resultSet=preparedStatement.executeQuery();
//            System.out.println("SELECT FOR UPDATE");
//            printInfor(resultSet, nameTransaction1);
//
//            System.out.println("UPDATING....");
//            PreparedStatement preparedStatementSecond=connection.prepareStatement(queryUpdateT1);
//            preparedStatementSecond.executeUpdate();
//
//            System.out.println("SELECT FOR CHECKING:");
//            PreparedStatement prepareStatementThird=connection.prepareStatement(queryGetT1);
//            ResultSet resultSet1=prepareStatementThird.executeQuery();
//            printInfor(resultSet1, nameTransaction1);

            //Checking change result
//            PreparedStatement prepareStatementExternalNew=connection.prepareStatement(queryGetT2);
//            ResultSet externalNewresultSet=prepareStatementExternalNew.executeQuery();
//            printInfor(externalNewresultSet, nameTransaction1);
            //

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printInfor(ResultSet resultSet, String nameTransation) throws SQLException {
        while (resultSet.next()){
            Double amount=resultSet.getDouble("amount");
            System.out.println(nameTransation+" : "+amount);
        }
    }

}
