package Utils;

import android.util.Log;

import com.hx.elb.Order;

import java.sql.*;

/**
 * Created by Hello on 2020/10/13.
 */

public class DBConnection {

    public static void InserIntoOrder(String a, String b, String c) {
        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";

        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！！");
            //3.sql语句
            String sql = "insert into information_order(id,userPhone,context) values('" + a + "','" + b + "','" + c + "')";
            //4.获取用于向数据库发送sql语句的ps
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute(sql);


            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void InserIntoUser( String phone,String password_user) {
    //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
    String url = "jdbc:mysql://114.215.169.84:3306/ELB";
    //连接数据库使用的用户名
    String userName = "root";
    //连接的数据库时使用的密码
    String password = "zzgdqhzwm123";

    Connection connection = null;
    try {
        //1、加载驱动
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
        //2、获取与数据库的连接
        connection = DriverManager.getConnection(url, userName, password);
        Log.e("xxx","lianjiechenggong");
        //3.sql语句
        String sql = "insert into information_user(phoneNumber,password) values('"+phone+"','"+password_user+"')";
        //4.获取用于向数据库发送sql语句的ps
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.execute(sql);
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


    public static boolean SelectUser( String phone,String password_user) {
        boolean isValid = false;
        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            Log.e("xxxx","lianj");
            //3.sql语句
            String sql = "select * from information_user where phoneNumber='" + phone + "' and password='" + password_user + "'";
            //4.获取用于向数据库发送sql语句的ps
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                Log.e("vv",rs.getString("phoneNumber"));
                isValid = true;
            } else {

            }

            rs.close();
            stm.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }



    //读取订单信息

    public static Order[] ReadAllMyOrdersInformation(String phoneNumber) {

        int i = 0;

        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("可以用啦");
            //3.sql语句
            String sql = "select * from information_order where userPhone = "+phoneNumber;
            //4.获取用于向数据库发送sql语句的ps

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);


            i = GetNumberOfMyOrder(phoneNumber);

            Order orders[] = new Order[i];
            Log.e("maileduoshao", "总共有" + i);


            int j = 0;
            while (rs.next()) {

                orders[j] = new Order(rs.getString("id"),rs.getString("userPhone"), rs.getString("context"));
                j++;
                Log.e("zzzzz", "" + j);

            }
            rs.close();
            stm.close();
            connection.close();


            return orders;

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }

    //订单数量（my）

    public static int GetNumberOfMyOrder(String userPhone) {

        int i = 0;

        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            //3.sql语句
            String sql = "select count(*) from information_order where userPhone = "+userPhone;
            //4.获取用于向数据库发送sql语句的ps

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);


            rs.next();

            i = rs.getInt(1);
            rs.close();
            stm.close();
            connection.close();
            return i;

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return i;

    }

    //all orders
    public static Order[] ReadAllOrdersInformation() {

        int i = 0;

        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("可以用啦");
            //3.sql语句
            String sql = "select * from information_order";
            //4.获取用于向数据库发送sql语句的ps

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);


            i = GetNumberOfOrder();

            Order orders[] = new Order[i];
            Log.e("maileduoshao", "总共有" + i);


            int j = 0;
            while (rs.next()) {

                orders[j] = new Order(rs.getString("id"),rs.getString("userPhone"), rs.getString("context"));
                j++;
                Log.e("zzzzz", "" + j);

            }
            rs.close();
            stm.close();
            connection.close();


            return orders;

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return null;

    }

    //订单数量（my）

    public static int GetNumberOfOrder() {

        int i = 0;

        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";

        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            //3.sql语句
            String sql = "select count(*) from information_order";
            //4.获取用于向数据库发送sql语句的ps

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);


            rs.next();

            i = rs.getInt(1);
            rs.close();
            stm.close();
            connection.close();
            return i;

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return i;

    }


    //shanchu


    public static void DeleteOrderById(String Id) {
        String url = "jdbc:mysql://114.215.169.84:3306/ELB";
        //连接数据库使用的用户名
        String userName = "root";
        //连接的数据库时使用的密码
        String password = "zzgdqhzwm123";
        Connection connection = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            //3.sql语句
            String sql = "delete from information_order where id = "+Id;
            //4.获取用于向数据库发送sql语句的ps
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute(sql);
            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

//链接order ，管理员界面，删除order，订单界面