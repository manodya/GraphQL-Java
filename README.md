# GraphQL-Java
GraphQL engine with graphql-java and MySql db

##Start Application
1. Build the code (For Intellij IDEA)
mvn idea:idea -e
2. Create a new db schema in MySQL db and change the schema name and the credentials in com.mub.graphql.dao.DataService.java file accordingly.
3. Run InitialScriptMySQL.java file to create initial DBs and insert sample data to the tables
4. Start Spring Application by running com.mub.graphql.Application.java 


##Smaple Requests to Check the API

allPriceEvents
{  
   allPriceEvents{  
    eventId 
    symbol
   }
}


singlePriceEvent
{  
   singlePriceEvent(eventId:1){  
    eventId 
    symbol
   }
}

allOrders
{  
   allOrders{  
    orderId 
    symbol 
    executionList{  
        executionId 
        orderId 
        qty
    }
   }
}

getOrder
{  
   getOrder(orderId:101){  
    orderId 
    symbol 
    executionList{  
        executionId 
        orderId
    }
   }
}