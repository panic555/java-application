package hr.java.production.model;
import java.io.*;
import java.lang.module.Configuration;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;


public class Database {
    public static Boolean activeConnectionWithDatabase = false;

    public static synchronized Connection connectToDatabase() throws SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/database.properties"));

        String databaseURL =  configuration.getProperty("databaseURL");
        String databaseUsername = configuration.getProperty("database.username");
        String databasePassword = configuration.getProperty("database.password");
        Connection connection = DriverManager
                .getConnection(databaseURL, databaseUsername, databasePassword);
        return connection;
    }

    public static List<Category> getAllCategoriesFromDatabase()
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        List<Category> categoryList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet studentsResultSet = sqlStatement.executeQuery(
        "SELECT * FROM CATEGORY");
        while(studentsResultSet.next()) {
            Category newCategory = getCategoryFromResultSet(studentsResultSet);
            categoryList.add(newCategory);
        }
        connection.close();
        return categoryList;
    }
    public static void insertNewCategoryToDatabase(Category category)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES(?, ?)");

        stmt.setString(1, category.getName());
        stmt.setString(2, category.getDescription());
        stmt.executeUpdate();
        connection.close();
    }
    public static void updateCategoryName(Category categoryToUpdate)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement updateCategories =
                connection.prepareStatement(
                        "UPDATE CATEGORY SET NAME = ? WHERE ID = ?");
        updateCategories.setString(1, categoryToUpdate.getName());
        updateCategories.setLong(2, categoryToUpdate.getId());
        updateCategories.executeUpdate();
        connection.close();
    }
    public static void deleteCategory(Category categoryToDelete)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement deleteCategory =
                connection.prepareStatement(
                        "DELETE FROM CATEGORY WHERE ID = ?");
        deleteCategory.setLong(1, categoryToDelete.getId());
        deleteCategory.executeUpdate();
        connection.close();

    }

    private static Category getCategoryFromResultSet(ResultSet categoryResultSet)
            throws SQLException
    {
        Long id = categoryResultSet.getLong("ID");
        String name = categoryResultSet.getString("NAME");
        String description = categoryResultSet.getString("DESCRIPTION");

        return new Category(id, name, description);
    }

    public static List<Item> getAllItemsFromDatabase(List<Category> categories)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        List<Item> itemList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet itemsResultSet = sqlStatement.executeQuery(
                "SELECT * FROM ITEM");
        while(itemsResultSet.next()) {
            Item newItem = getItemFromResultSet(itemsResultSet, categories);
            itemList.add(newItem);
        }
        connection.close();
        return itemList;
    }
    public static void insertNewItemToDatabase(Item item)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO ITEM (CATEGORY_ID, NAME, WIDTH, HEIGHT, LENGTH, PRODUCTION_COST, SELLING_PRICE)" +
                        " VALUES(?, ?, ?, ?, ?, ?, ?)");

        stmt.setLong(1, item.getCategory().getId());
        stmt.setString(2, item.getName());
        stmt.setBigDecimal(3, item.getWidth());
        stmt.setBigDecimal(4, item.getHeight());
        stmt.setBigDecimal(5, item.getLength());
        stmt.setBigDecimal(6, item.getProductionCost());
        stmt.setBigDecimal(7, item.getSellingPrice());
        stmt.executeUpdate();
        connection.close();
    }
    public static void updateItemName(Item itemToUpdate)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement updateItems =
                connection.prepareStatement(
                        "UPDATE ITEM SET NAME = ? WHERE ID = ?");
        updateItems.setString(1, itemToUpdate.getName());
        updateItems.setLong(2, itemToUpdate.getId());
        updateItems.executeUpdate();
        connection.close();
    }
    public static void deleteItem(Item itemToDelete)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement deleteItem =
                connection.prepareStatement(
                        "DELETE FROM CATEGORY WHERE ID = ?");
        deleteItem.setLong(1, itemToDelete.getId());
        deleteItem.executeUpdate();
        connection.close();

    }

    private static Item getItemFromResultSet(ResultSet itemResultSet, List<Category> categories)
            throws SQLException
    {
        Long id = itemResultSet.getLong("ID");
        Long categoryID = itemResultSet.getLong("CATEGORY_ID");
        String name = itemResultSet.getString("NAME");
        BigDecimal width = itemResultSet.getBigDecimal("WIDTH");
        BigDecimal height = itemResultSet.getBigDecimal("HEIGHT");
        BigDecimal length = itemResultSet.getBigDecimal("LENGTH");
        BigDecimal productionPrice = itemResultSet.getBigDecimal("PRODUCTION_COST");
        BigDecimal sellingPrice = itemResultSet.getBigDecimal("SELLING_PRICE");

        return new Item(id, name, categories.get(categoryID.intValue()-1), width, height, length, productionPrice, sellingPrice);
    }

    public static List<Address> getAllAddressesFromDatabase()
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        List<Address> addressList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet addressResultSet = sqlStatement.executeQuery(
                "SELECT * FROM ADDRESS");
        while(addressResultSet.next()) {
            Address newAddress = getAddressFromResultSet(addressResultSet);
            addressList.add(newAddress);
        }
        connection.close();
        return addressList;
    }
    public static void insertNewAddressToDatabase(Address address)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO ADDRESS (STREET, HOUSE_NUMBER, CITY, POSTAL_CODE) VALUES(?, ?, ?, ?)");

        stmt.setString(1, address.getStreet());
        stmt.setString(2, address.getHouseNumber());
        stmt.setString(3, address.getCity());
        stmt.setString(4, address.getPostalCode());
        stmt.executeUpdate();
        connection.close();
    }
    public static void updateAddressName(Address addressToUpdate)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement updateAddress =
                connection.prepareStatement(
                        "UPDATE ADDRESS SET STREET = ? WHERE ID = ?");
        updateAddress.setString(1, addressToUpdate.getStreet());
        updateAddress.setString(2, addressToUpdate.getId());
        updateAddress.executeUpdate();
        connection.close();
    }
    public static void deleteAddress(Address addressToDelete)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement deleteAddress =
                connection.prepareStatement(
                        "DELETE FROM CATEGORY WHERE ID = ?");
        deleteAddress.setString(1, addressToDelete.getId());
        deleteAddress.executeUpdate();
        connection.close();

    }

    private static Address getAddressFromResultSet(ResultSet addressResultSet)
            throws SQLException
    {
        String id = addressResultSet.getString("ID");
        String street = addressResultSet.getString("STREET");
        String houseNum = addressResultSet.getString("HOUSE_NUMBER");
        String city = addressResultSet.getString("CITY");
        String postalCode = addressResultSet.getString("POSTAL_CODE");

        return new Address(id, street, houseNum, city, postalCode);
    }

    public static List<Factory> getAllFactoriesFromDatabase(Set<Item> items, List<Address> addresses)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        List<Factory> factoryList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet factoriesResultSet = sqlStatement.executeQuery(
                "SELECT * FROM FACTORY");
        while(factoriesResultSet.next()) {
            Factory newFactory = getFactoryFromResultSet(factoriesResultSet, items, addresses);
            factoryList.add(newFactory);
        }
        connection.close();
        return factoryList;
    }
    public static void insertNewFactoryToDatabase(Factory factory)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO FACTORY (NAME, ADDRESS_ID)" +
                        " VALUES(?, ?)");

        stmt.setString(1, factory.getName());
        stmt.setLong(2, Long.parseLong("1"));
        stmt.executeUpdate();
        connection.close();

    }
    public static void updateFactoryName(Factory factoryToUpdate)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement updateFactories =
                connection.prepareStatement(
                        "UPDATE FACTORY SET NAME = ? WHERE ID = ?");
        updateFactories.setString(1, factoryToUpdate.getName());
        updateFactories.setLong(2, factoryToUpdate.getId());
        updateFactories.executeUpdate();
        connection.close();
    }
    public static void deleteFactory(Factory factoryToDelete)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement deleteFactory =
                connection.prepareStatement(
                        "DELETE FROM FACTORY WHERE ID = ?");
        deleteFactory.setLong(1, factoryToDelete.getId());
        deleteFactory.executeUpdate();
        connection.close();

    }


    private static Factory getFactoryFromResultSet(ResultSet factoryResultSet, Set<Item> items, List<Address> address)
            throws SQLException {
        Long id = factoryResultSet.getLong("ID");
        String name = factoryResultSet.getString("NAME");
        String addressID = factoryResultSet.getString("ADDRESS_ID");

        return new Factory(id, name, address.get(Integer.parseInt(addressID)-1), items);
    }

    public static Set<Item> getItemsForTheFactory(List<Item> items, Factory factory) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        Set<Item> itemList = new HashSet<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet factoryItemsResultSet = sqlStatement.executeQuery(
                "SELECT * FROM FACTORY_ITEM");
        while (factoryItemsResultSet.next()) {
            if (factory.getId() == factoryItemsResultSet.getLong("FACTORY_ID")) {
                itemList.add((items.get(factoryItemsResultSet.getInt("ITEM_ID") - 1)));
            }
        }
        connection.close();
        return itemList;
    }
    public static List<Store> getAllStoresFromDatabase(Set<Item> items)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        List<Store> storeList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet storesResultSet = sqlStatement.executeQuery(
                "SELECT * FROM STORE");
        while(storesResultSet.next()) {
            Store newStore = getStoreFromResultSet(storesResultSet, items);
            storeList.add(newStore);
        }
        connection.close();
        return storeList;
    }
    public static void insertNewStoreToDatabase(Store store)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO STORE (NAME, WEB_ADDRESS)" +
                        " VALUES(?, ?)");

        stmt.setString(1, store.getName());
        stmt.setString(2, store.getWebAddress());
        stmt.executeUpdate();
        connection.close();

    }
    public static void updateStoreName(Store storeToUpdate)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement updateStores =
                connection.prepareStatement(
                        "UPDATE STORE SET NAME = ? WHERE ID = ?");
        updateStores.setString(1, storeToUpdate.getName());
        updateStores.setLong(2, storeToUpdate.getId());
        updateStores.executeUpdate();
        connection.close();
    }
    public static void deleteStore(Store storeToDelete)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement deleteStore =
                connection.prepareStatement(
                        "DELETE FROM STORE WHERE ID = ?");
        deleteStore.setLong(1, storeToDelete.getId());
        deleteStore.executeUpdate();
        connection.close();

    }


    private static Store getStoreFromResultSet(ResultSet storeResultSet, Set<Item> items)
            throws SQLException {
        Long id = storeResultSet.getLong("ID");
        String name = storeResultSet.getString("NAME");
        String address = storeResultSet.getString("WEB_ADDRESS");

        return new Store(id, name, address, items);
    }

    public static Set<Item> getItemsForTheStore(List<Item> items, Store store) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        Set<Item> itemList = new HashSet<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet storeItemsResultSet = sqlStatement.executeQuery(
                "SELECT * FROM STORE_ITEM");
        while (storeItemsResultSet.next()) {
            if (store.getId() == storeItemsResultSet.getLong("STORE_ID")) {
                itemList.add((items.get(storeItemsResultSet.getInt("ITEM_ID") - 1)));
            }
        }
        connection.close();
        return itemList;
    }
    public static void insertNewFactoryItemToDatabase(Item item, Long id)
            throws SQLException, IOException
    {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO FACTORY_ITEM (FACTORY_ID, ITEM_ID) VALUES(?, ?)");

        stmt.setLong(1, id + 1);
        stmt.setLong(2, item.getId() + 1);

        stmt.executeUpdate();
        connection.close();
    }

    public static void insertNewStoreItemToDatabase(Item item, Long id)
            throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO STORE_ITEM (STORE_ID, ITEM_ID) VALUES(?, ?)");

        stmt.setLong(1, id + 1);
        stmt.setLong(2, item.getId() + 1);

        stmt.executeUpdate();
        connection.close();
    }
    public static void closeConnectionToDatabase(){
        Connection veza = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Properties configuration = new Properties();
            configuration.load(new FileReader("dat/database.properties"));

            String databaseURL =  configuration.getProperty("databaseURL");
            String databaseUsername = configuration.getProperty("database.username");
            String databasePassword = configuration.getProperty("database.password");
            Connection connection = DriverManager
                    .getConnection(databaseURL, databaseUsername, databasePassword);
            stmt = veza.createStatement();
            rs = stmt.executeQuery("SELECT * FROM STUDENTI");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                veza.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
