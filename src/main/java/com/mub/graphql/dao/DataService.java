package com.mub.graphql.dao;


import org.skife.jdbi.v2.DBI;


import javax.sql.DataSource;

/**
 * Created by manodyas on 1/15/2018.
 */
public class DataService {
    private static DBI dbi;
    private static DataService dataService = null;

    private DataService(){

            dbi = new DBI("jdbc:mysql://localhost:3306/graph","root","password");
    }

    public static DataService getInstance(){
        if(dataService == null){
            dataService = new DataService();
        }
        return dataService;
    }

    public DBI getDbi(){
        return dbi;
    }
}
