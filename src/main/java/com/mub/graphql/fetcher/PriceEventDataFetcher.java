package com.mub.graphql.fetcher;

import com.mub.graphql.bo.PriceEvent;
import com.mub.graphql.dao.DataService;
import com.mub.graphql.dao.PriceEventDAOI;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by manodyas on 2/7/2018.
 */
@Component
public class PriceEventDataFetcher  implements DataFetcher<PriceEvent>{

    private static DataService ds;
    private static PriceEventDAOI priceEventDAOI;

    static {
        ds = DataService.getInstance();
        priceEventDAOI = ds.getDbi().onDemand(PriceEventDAOI.class);
    }

    @Override
    public PriceEvent get(DataFetchingEnvironment env) {
        return priceEventDAOI.getPriceEvent((int)env.getArgument("eventId"));
    }
}
