package databasetest2;

import databasetest.CategoryObject;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DataAccess {
        public  boolean getProductName(String productName ,Connection connection){
        boolean isProductExist=false;

        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;
        try {
            cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String productSqlScrip=String.format("select product_id,name,price from cc_CubeCart_inventory where name='%s'",productName);
        try {
            resultSet=statement.executeQuery(productSqlScrip);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // verify result set
        if (resultSet==null){
            System.out.println(" no records found");

            return isProductExist;
        }else {
            try {
                cachedRowSet.populate(resultSet);// we store the result in the cachset
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count=0;
            while(true){
                try {
                    if(!cachedRowSet.next()){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    int product_id = cachedRowSet.getInt("product_id");
                    String name = cachedRowSet.getString("name");
                    double price = cachedRowSet.getDouble("price");
                    System.out.println(String.format("product_id=%d name=%s price=%.2f", product_id, name, price));
                    count = cachedRowSet.getRow();
                    //System.out.println(count);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(count>=1)
                isProductExist=true;
            return isProductExist;
        }


        }
        public boolean getDocName(String docName,Connection connection){
        Boolean isDocumentExist=false;
        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;
            try {
                cachedRowSet=RowSetProvider.newFactory().createCachedRowSet();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement=connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String script=String.format(" select doc_id,doc_name,doc_content from cc_CubeCart_documents where doc_name='%s'",docName);
            try {
                resultSet=statement.executeQuery(script);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (resultSet==null){
                System.out.println("no records found");
            }else {
                try {
                    cachedRowSet.populate(resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            int count2=0;
            while (true){
                try {
                    if (!cachedRowSet.next()){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String doc_name=cachedRowSet.getString("doc_name");
                    int docid=cachedRowSet.getInt("doc_id");
                    String doccontent=cachedRowSet.getString("doc_content");
                    System.out.println(String.format("doc_name=%s,doc-id=%s,doc_content=%s",doc_name,docid,doccontent));
                    count2=cachedRowSet.getRow();

                }catch (SQLException e){
                    e.printStackTrace();

                }


            }
            if (count2>=1)
                isDocumentExist=true;
            return isDocumentExist;

        }
        public boolean getCustomer(String customerEmail, Connection connection){
        boolean isCustomerExist=false;
        Statement statement=null;//define a statement object to execute sql script
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;

        try {
            cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String productSqlScript=String.format("Select customer_id,email from cc_CubeCart_customer where email='%s'",customerEmail);
        try {
            resultSet=statement.executeQuery(productSqlScript);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //veriy the result set
        if(resultSet==null){
            System.out.println("No records Found");
            return isCustomerExist;
        }
        else {
            try {
                cachedRowSet.populate(resultSet);//we store the result in the cached row set
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count=0;
            while(true){
                try {
                    if(!cachedRowSet.next()){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    int customer_id = cachedRowSet.getInt("customer_id");
                    String email = cachedRowSet.getString("email");
                    System.out.println(String.format("customer_id=%d email=%s", customer_id, email));
                    count = cachedRowSet.getRow();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(count>=1)
                isCustomerExist=true;
            return isCustomerExist;
        }
    }
        public boolean insertNewCategory(CategoryObject categoryObject, Connection connection){
        String insertCategoryScript="Insert into cc_CubeCart_category(cat_name,cat_desc,cat_parent_id,cat_image,per_int_ship,hide,seo_meta_title,seo_meta_description," +
                "seo_meta_keywords,priority,status)"+"values(?,?,?,?,?,?,?,?,?,?,?)";
        //Prepared statement

        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement(insertCategoryScript);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            preparedStatement.setString(1,categoryObject.getCat_name());
            preparedStatement.setString(2,categoryObject.getCat_desc());
            preparedStatement.setInt(3,categoryObject.getCatParentId());
            preparedStatement.setInt(4,categoryObject.getCat_image());
            preparedStatement.setDouble(5,categoryObject.getPerIntShip());
            preparedStatement.setInt(6,categoryObject.getHide());
            preparedStatement.setString(7,categoryObject.getSeoMetatitle());
            preparedStatement.setString(8,categoryObject.getSeoMetaDescription());
            preparedStatement.setString(9,categoryObject.getSeoMetaKeywords());
            preparedStatement.setInt(10,categoryObject.getPriority());
            preparedStatement.setInt(11,categoryObject.getStatus());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        int affetedRows=0;
        try {
            affetedRows=preparedStatement.executeUpdate();//insert delete update
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%d rows affected",affetedRows));
        if(affetedRows>=0)
            return true;
        else
            return false;

    }
        public  boolean getInsertedCategoryName(String categoryname ,Connection connection){
        boolean isCategoryExist=false;

        Statement statement=null;
        ResultSet resultSet=null;
        CachedRowSet cachedRowSet=null;
        try {
            cachedRowSet= RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //select cat_name,cat_desc,cat_parent_id,cat_image,per_int_ship," +
        //                "hide,seo_meta_title,seo_meta_description,seo_meta_keywords,priority,status " +
        //                " from cc_CubeCart_category
        String categorySqlScrip=String.format("  select * from cc_CubeCart_category where cat_name='%s'",categoryname);

        try {
            resultSet=statement.executeQuery(categorySqlScrip);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // verify result set
        if (resultSet==null){
            System.out.println(" no records found");

            return isCategoryExist;
        }else {
            try {
                cachedRowSet.populate(resultSet);// we store the result in the cachset
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int count=0;
            while(true){
                try {
                    if(!cachedRowSet.next()){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    String catname=cachedRowSet.getString("cat_name");
                    String catdesc=cachedRowSet.getString("cat_desc");
                    int catparentid=cachedRowSet.getInt("cat_parent_id");
                  //  int catimage=cachedRowSet.getInt("cat_image");
                    double perintship=cachedRowSet.getDouble("per_int_ship");
                  //  int hide=cachedRowSet.getInt("hide");
                    String seomatetitle=cachedRowSet.getString("seo_meta_title");
                   // String SeoMetaDescription=cachedRowSet.getString("seo_meta_description");
                  //  String SeoMetaKeywords=cachedRowSet.getString("seo_meta_keywords");
                   // int priority=cachedRowSet.getInt("priority");
                   // int status=cachedRowSet.getInt("status");
                    System.out.println(String.format("cat_name=%s,cat-desc=%s,cat parent id=%s," +
                                    " perintship=%s,semotitle=%s", catname,
                            catdesc,catparentid,perintship,seomatetitle));
                    count = cachedRowSet.getRow();
                    //System.out.println(count);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(count>=1)
                isCategoryExist=true;
            return isCategoryExist;
        }


    }


    }

