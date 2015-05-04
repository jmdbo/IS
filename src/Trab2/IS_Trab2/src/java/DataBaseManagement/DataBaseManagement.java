package DataBaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseManagement {

    private Connection connection;
    private Statement statement;

    private boolean openConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:../database/CloudDB;", "sa", "");
            statement = statement = connection.createStatement();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean closeConnection() {
        try {
            connection.close();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean createDB() {
        try {

            openConnection();

            statement.execute("drop table if exists PUBLIC.CUSTOMER;");
            statement.execute("drop table if exists PUBLIC.DEVICE;");
            statement.execute("drop table if exists PUBLIC.MANUFACTURER;");
            statement.execute("drop table if exists PUBLIC.TYPE;");
            statement.execute("drop table if exists PUBLIC.STATE");
            statement.execute("drop table if exists PUBLIC.ERROR");
            statement.execute("drop table if exists PUBLIC.DEVICE_STATE");

            statement.execute("create table PUBLIC.CUSTOMER ("
                    + "USER_NAME VARCHAR(255) PRIMARY KEY,"
                    + "NAME VARCHAR(255),"
                    + "TELEPHONE INTEGER,"
                    + "RESIDENCE VARCHAR(255),"
                    + "HASH VARCHAR(256));");
            
            statement.execute("create table PUBLIC.MANUFACTURER ("
                    + "USER_NAME VARCHAR(255) PRIMARY KEY,"
                    + "NAME VARCHAR(255),"
                    + "TELEPHONE INTEGER,"
                    + "TYPE INTEGER,"
                    + "RESIDENCE VARCHAR(255),"
                    + "HASH VARCHAR(256));");

            statement.execute("create table PUBLIC.TYPE ("  
                    + "TYPE_UID INTEGER PRIMARY KEY,"
                    + "TYPE_DESCRIPTION VARCHAR(255));");

            statement.execute("create table PUBLIC.DEVICE ("   
                    + "SERIAL_NUMBER INTEGER PRIMARY KEY,"
                    + "USER_NAME VARCHAR(255),"
                    + "MODEL VARCHAR(255),"
                    + "FRIENDLY_NAME VARCHAR(255),"
                    + "TYPE_UID INTEGER,"
                    + "IP VARCHAR(255),"
                    + "PORT INTEGER,"
                    + "FOREIGN KEY(USER_NAME) REFERENCES CUSTOMER(USER_NAME),"
                    + "FOREIGN KEY(TYPE_UID) REFERENCES TYPE(TYPE_UID));");

            statement.execute("create table PUBLIC.STATE ("
                    + "STATE_UID INTEGER PRIMARY KEY,"
                    + "STATE_DESCRIPTION VARCHAR(255));");

            statement.execute("create table PUBLIC.ERROR ("
                    + "ERROR_UID INTEGER PRIMARY KEY,"
                    + "ERROR_DESCRIPTION VARCHAR(255));");
            
            statement.execute("create table PUBLIC.DEVICE_STATE ("
                    + "TIME TIMESTAMP,"
                    + "SERIAL_NUMBER INTEGER,"
                    + "STATE_UID INTEGER,"
                    + "ERROR_UID INTEGER,"
                    + "ENERGY_PRODUCTION INTEGER,"
                    + "FOREIGN KEY(STATE_UID) REFERENCES STATE(STATE_UID),"
                    + "FOREIGN KEY(ERROR_UID) REFERENCES ERROR(ERROR_UID),"
                    + "FOREIGN KEY(SERIAL_NUMBER) REFERENCES DEVICE(SERIAL_NUMBER));");

            //initialize type table
            statement.execute("insert into PUBLIC.TYPE VALUES("
                    + "1,"
                    + "'Fotovoltaico');");
            statement.execute("insert into PUBLIC.TYPE VALUES("
                    + "2,"
                    + "'Eolico');");

            //initialize state table
            statement.execute("insert into PUBLIC.STATE VALUES("
                    + "1,"
                    + "'ON');");
            statement.execute("insert into PUBLIC.STATE VALUES("
                    + "2,"
                    + "'OFF');");
            
            //initialize error table
            statement.execute("insert into PUBLIC.ERROR VALUES("
                    + "0,"
                    + "'Sem Erros');");
            statement.execute("insert into PUBLIC.ERROR VALUES("
                    + "1,"
                    + "'Producao de Energia');");
            statement.execute("insert into PUBLIC.ERROR VALUES("
                    + "2,"
                    + "'Extraccao de Dados');");
            statement.execute("insert into manufacturer values ( 'jmdbo', 'Joao Barata', 217793070, 1, 'Mem Martins', 'Lisboa')");

            //--------------------------------------------------------------------------------------------------------------------
            System.out.println("Database has been created");

            closeConnection();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }
    
    /*
     This method inserts a new customer
     Receives the USER_NAME/NAME/TELEPHONE(int)/RESIDENCE
     Returns a boolean
     */
    
    public boolean InsertCustomer(String userName, String name, int telephone, String residence, String hash) {
        try {
            openConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO PUBLIC.CUSTOMER("
                    + " USER_NAME,"
                    + " NAME,"
                    + " TELEPHONE,"
                    + " RESIDENCE,"
                    + "HASH)  VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, telephone);
            preparedStatement.setString(4, residence);
            preparedStatement.setString(5, hash);
            preparedStatement.execute();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }

    /*
     This method associates a device to an user
     Receives SERIAL_NUMBER(int)/USER_NAME/MODEL/FRIENDLY_NAME/TYPE_UID(int)
     Returns a boolean
     */
    public boolean AssociateDevice(int serialNumber, String userName, String model, String friendlyName, int deviceType) {
        try {
            openConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO PUBLIC.DEVICE("
                    + " SERIAL_NUMBER,"
                    + " USER_NAME,"
                    + " MODEL,"
                    + " FRIENDLY_NAME,"
                    + " TYPE_UID)  VALUES (?,?,?,?,?)");
            preparedStatement.setInt(1, serialNumber);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, model);
            preparedStatement.setString(4, friendlyName);
            preparedStatement.setInt(5, deviceType);
            preparedStatement.execute();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }

    /*
     This method returns all associated devices to a specific user
     Receives the userName
     Returns an ArrayList with structure: SERIAL_NUMBER/FRIENDLY_NAME
     */
    public ArrayList<String> getMyDevices(String userName) {
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT SERIAL_NUMBER, FRIENDLY_NAME, TYPE_UID FROM PUBLIC.DEVICE WHERE USER_NAME = '" + userName + "'")) {
            ArrayList<String> myDevices = new ArrayList<>();
            while (rs.next()) {
                myDevices.add(rs.getString(1));
                myDevices.add(rs.getString(2));
                myDevices.add(rs.getString(3));
            }
            closeConnection();
            return myDevices;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
    
    /*
     This method returns all device's types
     Returns an ArrayList with structure: TYPE_UID/TYPE_DESCRIPTION
     */
    public ArrayList<String> getDeviceTypes() {
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT * FROM PUBLIC.TYPE")) {
            ArrayList<String> myTypes = new ArrayList<>();
            while (rs.next()) {
                myTypes.add(rs.getString(1));
                myTypes.add(rs.getString(2));
            }
            closeConnection();
            return myTypes;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
    
    /*
     This method returns all possible states
     Returns an ArrayList with structure: STATE_UID/STATE_DESCRIPTION
     */
    public ArrayList<String> getStates(){
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT * FROM PUBLIC.STATE")) {
            ArrayList<String> myProgramas = new ArrayList<>();
            while (rs.next()) {
                myProgramas.add(rs.getString(1));
                myProgramas.add(rs.getString(2));
            }
            closeConnection();
            return myProgramas;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
    
    /*
     This method returns the device's friendly name
     Receives the device's serial number
     Returns the FRIENDLY_NAME
     */
    public String getMyDeviceFriendlyName(String serialNumber) {
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT FRIENDLY_NAME FROM PUBLIC.DEVICE WHERE SERIAL_NUMBER = " + serialNumber)) {
            rs.next();
            String myFriendlyName = rs.getString(1);
            closeConnection();
            return myFriendlyName;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }

    /*
     This method returns the states stored by a specific device
     Receives the device's serial number
     Returns an ArrayList with structure: TIME/STATE_DESCRIPTION/ERROR_DESCRIPTION/ENERGY_PRODUCTION
     */
    public ArrayList<String> getDeviceStates(String serialNumber) {
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT TIME, STATE_DESCRIPTION, ERROR_DESCRIPTION, ENERGY_PRODUCTION "
            + "FROM DEVICE_STATE, DEVICE, STATE, ERROR "
            + "WHERE DEVICE_STATE.SERIAL_NUMBER = DEVICE.SERIAL_NUMBER "
            + "AND STATE.STATE_UID = DEVICE_STATE.STATE_UID "
            + "AND ERROR.ERROR_UID = DEVICE_STATE.ERROR_UID "
            + "AND DEVICE_STATE.SERIAL_NUMBER = '" + serialNumber + "'")) {
            ArrayList<String> myUsages = new ArrayList<>();
            while (rs.next()) {
                myUsages.add(rs.getString(1));
                myUsages.add(rs.getString(2));
                myUsages.add(rs.getString(3));
                myUsages.add(rs.getString(4));
            }
            closeConnection();
            return myUsages;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
    
    /*
     This method returns the states stored by all devices associated to the user
     Receives the userName
     Returns an ArrayList with structure: TIME/SERIAL_NUMBER/ERROR_DESCRIPTION/STATE_DESCRIPTION/ENERGY_PRODUCTION/TYPE_DESCRIPTION
     */
    public ArrayList<String> getCustomerStates(String userName) {
        openConnection();
        try (ResultSet rs = statement.executeQuery("SELECT TIME, DEVICE_STATE.SERIAL_NUMBER, ERROR_DESCRIPTION, STATE_DESCRIPTION, ENERGY_PRODUCTION, TYPE_DESCRIPTION "
            + "FROM DEVICE_STATE, DEVICE, TYPE, STATE, ERROR "
            + "WHERE DEVICE_STATE.SERIAL_NUMBER = DEVICE.SERIAL_NUMBER "
            + "AND STATE.STATE_UID = DEVICE_STATE.STATE_UID "
            + "AND DEVICE.TYPE_UID = TYPE.TYPE_UID "
            + "AND DEVICE_STATE.ERROR_UID = ERROR.ERROR_UID "
            + "AND DEVICE.USER_NAME = '" + userName + "'")) {
            ArrayList<String> myUsages = new ArrayList<>();
            while (rs.next()) {
                myUsages.add(rs.getString(1));
                myUsages.add(rs.getString(2));
                myUsages.add(rs.getString(3));
                myUsages.add(rs.getString(4));
                myUsages.add(rs.getString(5));
                myUsages.add(rs.getString(6));
            }
            closeConnection();
            return myUsages;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
    
    /*
     This method insert a new device state
     Receives the serial SERIAL_NUMBER/STATE/ERROR/ENERGY_PRODUCTION
     Returns a boolean
     */
    public boolean insertState(int serialNumber, int state, int error, int energyProduction,String ip, int port) throws ParseException {
        try {
            openConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO PUBLIC.DEVICE_STATE("
                    + " TIME,"
                    + " SERIAL_NUMBER,"
                    + " STATE_UID,"
                    + " ERROR_UID,"
                    + " ENERGY_PRODUCTION)  VALUES (?,?,?,?,?)");
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
            java.util.Date date = Calendar.getInstance().getTime();
            preparedStatement.setTimestamp(1, new Timestamp((formatter.parse(formatter.format(date))).getTime()));
            preparedStatement.setInt(2, serialNumber);
            preparedStatement.setInt(3, state);
            preparedStatement.setInt(4, error);
            preparedStatement.setInt(5, energyProduction);
            preparedStatement.execute();
            closeConnection();
            openConnection();
            preparedStatement = this.connection.prepareStatement("UPDATE PUBLIC.DEVICE SET IP=?, PORT=? WHERE SERIAL_NUMBER=?");
            preparedStatement.setString(1, ip);
            preparedStatement.setInt(2, port);
            preparedStatement.setInt(3, serialNumber);
            preparedStatement.execute();
            closeConnection();
            return true;
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }
    /*
    This string receives a userName and a hash and checks if they match with the
    information on the DB. Returns a boolean.
    */
    public boolean checkLogin(String userName, String hash){
        try {
            openConnection();
            ResultSet rs = statement.executeQuery("SELECT HASH FROM PUBLIC.CUSTOMER WHERE USER_NAME = '" + userName + "'");
            rs.next();
            String hashTrue = rs.getString(1);
            closeConnection();
            return hashTrue.equals(hash);
            
        }catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }
    /*This method receives a String with the user name and returns a string with
    the customer real name.
    
    */
    public String getName(String userName){
        try{
            openConnection();
            ResultSet rs = statement.executeQuery("SELECT NAME FROM PUBLIC.CUSTOMER WHERE USER_NAME = '"+userName+"'");
            String name = rs.getString(1);
            closeConnection();
            return name;
        }catch(SQLException ex){
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }
     /*  This string receives a userName and a hash and checks if they match with the
    information on the DB. Returns a boolean.
    */
    public int checkLoginManufacturer(String userName, String hash){
        try {
            openConnection();
            ResultSet rs = statement.executeQuery("SELECT HASH, TYPE FROM PUBLIC.MANUFACTURER WHERE USER_NAME = '" + userName + "'");
            rs.next();
            String hashTrue = rs.getString(1);
            int type = rs.getInt(2);
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, hashTrue);
            if(hashTrue.equals(hash)){
                closeConnection();
                return type;
            }
            closeConnection();
            return 0;
            
        }catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return 0;        
    }
    
    public boolean InsertManufacturer(String userName, String name, int telephone, String residence,int type, String hash) {
        try {
            openConnection();
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO PUBLIC.MANUFACTURER("
                    + " USER_NAME,"
                    + " NAME,"
                    + " TELEPHONE,"
                    + " TYPE,"
                    + " RESIDENCE,"
                    + "HASH)  VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, telephone);
            preparedStatement.setInt(4, type);
            preparedStatement.setString(5, residence);
            preparedStatement.setString(6, hash);
            preparedStatement.execute();
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return false;
    }
    
    public String getConncectionString(int serialNumber){
        try{
            openConnection();
            ResultSet rs = statement.executeQuery("SELECT IP, PORT FROM PUBLIC.DEVICE WHERE SERIAL_NUMBER = " + Integer.toString(serialNumber) + "");
            rs.next();
            String ip = rs.getString(1);
            int port = rs.getInt(2);
            String connection = "http://"+ip+":"+Integer.toString(port);
            closeConnection();
            return connection;
            
            
        }catch (SQLException ex) {
            Logger.getLogger(DataBaseManagement.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        closeConnection();
        return null;
        
    }
 

}
