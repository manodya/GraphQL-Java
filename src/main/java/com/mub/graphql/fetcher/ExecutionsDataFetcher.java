package com.mub.graphql.fetcher;

import com.mub.graphql.bo.Execution;
import com.mub.graphql.bo.Order;
import com.mub.graphql.dao.DataService;
import com.mub.graphql.dao.OrderDAOI;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by manodyas on 2/7/2018.
 */

@Component
public class ExecutionsDataFetcher implements DataFetcher<List<Execution>> {

    private static DataService ds;
    private static OrderDAOI orderDAOI;

    static {
        ds = DataService.getInstance();
        orderDAOI = ds.getDbi().onDemand(OrderDAOI.class);
    }
    @Override
    public List<Execution> get(DataFetchingEnvironment env) {
        Order order = env.getSource();
        return orderDAOI.getExecutions(order.getOrderId());
    }
}
