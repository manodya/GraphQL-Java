package com.mub.graphql.fetcher;

import com.mub.graphql.bo.Order;
import com.mub.graphql.dao.DataService;
import com.mub.graphql.dao.OrderDAOI;
import com.mub.graphql.dao.PriceEventDAOI;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by manodyas on 2/7/2018.
 */
@Component
public class AllOrdersDataFetcher  implements DataFetcher<List<Order>>{

    private static DataService ds;
    private static OrderDAOI orderDAOI;

    static {
        ds = DataService.getInstance();
        orderDAOI = ds.getDbi().onDemand(OrderDAOI.class);
    }

    @Override
    public List<Order> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return orderDAOI.getOrders();
    }
}
