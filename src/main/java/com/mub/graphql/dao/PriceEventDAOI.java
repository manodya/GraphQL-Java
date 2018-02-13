package com.mub.graphql.dao;

import com.mub.graphql.bo.PriceEvent;
import com.mub.graphql.dao.mapper.*;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by manodyas on 2/6/2018.
 */
public interface PriceEventDAOI {
    @SqlQuery("select * from price_event")
    @Mapper(PriceEventMapper.class)
    List<PriceEvent> getAllPriceEvents();

    @SqlQuery("select * from price_event where eventId=:eventId")
    @Mapper(PriceEventMapper.class)
    PriceEvent getPriceEvent(@Bind("eventId") int eventId);


}
