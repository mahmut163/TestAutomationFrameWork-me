package databasetest2;
import com.unitedcoder.configutility.ApplicationConfig;
import databasetest.CategoryObject;
import databasetest.ConnectionManager;
import databasetest.ConnectionType;
import databasetest.DataAccess;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DateBaseTestRunner1 {
    Connection connection=null;
    final static String configFile="config-qa.properties";
    databasetest.CategoryObject categoryObject;
    databasetest.DataAccess dataAccess;

    @BeforeClass
    public void setup(){
        String dbUrl=ApplicationConfig.readFromConfigProperties(configFile,"standalone.dbUrl");
        String dbPort=ApplicationConfig.readFromConfigProperties(configFile,"standalone.dbPort");
        String defaultDatabase=ApplicationConfig.readFromConfigProperties(configFile,"standalone.defaultDatabase");
        String userName=ApplicationConfig.readFromConfigProperties(configFile,"standalone.userName");
        String password=ApplicationConfig.readFromConfigProperties(configFile,"standalone.password");
        connection= databasetest.ConnectionManager.connectToDataBaseServer(dbUrl,dbPort,defaultDatabase,userName,password, ConnectionType.MYSQL);

    }
    @Test(description = "Admin user should be able to insert record into cubecart category table",groups = "insert category")
    public void insertCategoryTest(){
        String currentTimeStamp=String.valueOf(System.currentTimeMillis());
         categoryObject=new CategoryObject("waarimCategory","tarimDescription",
                0,0,0,0,"Category_Title_aliho","Cat_Description_moon",
                "moon",2,2);dataAccess=new databasetest.DataAccess();
        //Assert.assertTrue(dataAccess.insertNewCategory(categoryObject,connection));
        boolean isNewCategoryExist=dataAccess.insertNewCategory(categoryObject,connection);
        Assert.assertTrue(isNewCategoryExist);

    }
    @Test
    public void verifyCatelogyInserTest(){
        dataAccess=new DataAccess();
        boolean isCategoryExist=dataAccess.getInsertedCategoryName(categoryObject.getCat_name(),connection);
        Assert.assertTrue(isCategoryExist);

    }

    @AfterClass
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
    }
}
