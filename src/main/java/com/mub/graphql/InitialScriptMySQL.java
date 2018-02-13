package com.mub.graphql;

import com.mub.graphql.dao.DataService;
import org.skife.jdbi.v2.Batch;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

/**
 * Created by manodyas on 2/13/2018.
 */
public class InitialScriptMySQL {
    private static DataService ds;
    private static DBI dbi;
    private static Handle handle;


    private static void executeQuery(String query){
       handle.execute(query);
    }
    private static  void  insertBatch(Batch batch){
        batch.execute();
    }

    public static void main(String[] args) {
        ds = DataService.getInstance();
        dbi = ds.getDbi();
        handle =  dbi.open();
        System.out.println("####Creating Tables.");
        String executionTable = "CREATE TABLE `executions` (\n" +
                "  `executionId` int(11) DEFAULT NULL,\n" +
                "  `orderId` int(11) DEFAULT NULL,\n" +
                "  `qty` varchar(45) DEFAULT NULL,\n" +
                "  `price` varchar(45) DEFAULT NULL,\n" +
                "  `timeStamp` varchar(45) DEFAULT NULL,\n" +
                "  `filledQty` varchar(45) DEFAULT NULL,\n" +
                "  `avgPrice` varchar(45) DEFAULT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

        String ordersTable = "CREATE TABLE `orders` (\n" +
                "  `orderId` int(11) NOT NULL,\n" +
                "  `symbol` varchar(45) DEFAULT NULL,\n" +
                "  `qty` varchar(45) DEFAULT NULL,\n" +
                "  `side` varchar(45) DEFAULT NULL,\n" +
                "  `type` varchar(45) DEFAULT NULL,\n" +
                "  `price` varchar(45) DEFAULT NULL,\n" +
                "  `status` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`orderId`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

        String priceEventTable = "CREATE TABLE `price_event` (\n" +
                "  `eventId` int(11) NOT NULL,\n" +
                "  `symbol` varchar(45) DEFAULT NULL,\n" +
                "  `timeStamp` varchar(45) DEFAULT NULL,\n" +
                "  `lastTradePrice` varchar(45) DEFAULT NULL,\n" +
                "  `volume` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`eventId`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        executeQuery(executionTable);
        executeQuery(ordersTable);
        executeQuery(priceEventTable);
        System.out.println("####Tables Created.");

        System.out.println("####Inserting Data.");
        Batch priceEventBatch = handle.createBatch();
        priceEventBatch.add("INSERT INTO `price_event` (`eventId`,`symbol`,`timeStamp`,`lastTradePrice`,`volume`) VALUES (1,'1010','4444444','58.56','1500');");
        priceEventBatch.add("INSERT INTO `price_event` (`eventId`,`symbol`,`timeStamp`,`lastTradePrice`,`volume`) VALUES (2,'1020','555555','12.56','3000');");
        insertBatch(priceEventBatch);

        Batch orderBatch = handle.createBatch();
        orderBatch.add("INSERT INTO `orders` (`orderId`,`symbol`,`qty`,`side`,`type`,`price`,`status`) VALUES (100,'1010','500','1','2','100.5','0');");
        orderBatch.add("INSERT INTO `orders` (`orderId`,`symbol`,`qty`,`side`,`type`,`price`,`status`) VALUES (101,'1020','800','2','2','80.56','1');");
        insertBatch(orderBatch);

        Batch executionsBatch = handle.createBatch();
        executionsBatch.add("INSERT INTO `executions` (`executionId`,`orderId`,`qty`,`price`,`timeStamp`,`filledQty`,`avgPrice`) VALUES (1,100,'100','100.3','1111111','100','100.3');");
        executionsBatch.add("INSERT INTO `executions` (`executionId`,`orderId`,`qty`,`price`,`timeStamp`,`filledQty`,`avgPrice`) VALUES (2,100,'100','100.4','1111113','200','100.35');");
        executionsBatch.add("INSERT INTO `executions` (`executionId`,`orderId`,`qty`,`price`,`timeStamp`,`filledQty`,`avgPrice`) VALUES (1,101,'450','80.6','222222','450','80.6');");
        executionsBatch.add("INSERT INTO `executions` (`executionId`,`orderId`,`qty`,`price`,`timeStamp`,`filledQty`,`avgPrice`) VALUES (2,101,'350','80.7','222233','800','80.67');");
        insertBatch(executionsBatch);

        System.out.println("####Data Inserted.");


    }
}
