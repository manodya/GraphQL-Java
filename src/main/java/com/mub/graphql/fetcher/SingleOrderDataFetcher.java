package com.mub.graphql.fetcher;

import com.mub.graphql.bo.Order;
import com.mub.graphql.bo.PriceEvent;
import com.mub.graphql.dao.DataService;
import com.mub.graphql.dao.OrderDAOI;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by manodyas on 2/12/2018.
 */
@Component
public class SingleOrderDataFetcher  implements DataFetcher<Order> {
    private static DataService ds;
    private static OrderDAOI orderDAOI;

    static {
        ds = DataService.getInstance();
        orderDAOI = ds.getDbi().onDemand(OrderDAOI.class);
    }
    @Override
    public Order get(DataFetchingEnvironment env) {
        return orderDAOI.getOrder((int)env.getArgument("orderId"));
    }
}
