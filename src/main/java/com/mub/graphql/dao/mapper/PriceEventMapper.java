package com.mub.graphql.dao.mapper;

import com.mub.graphql.bo.PriceEvent;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by manodyas on 2/6/2018.
 */
public class PriceEventMapper implements ResultSetMapper<PriceEvent> {
    @Override
    public PriceEvent map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {

        PriceEvent priceEvent = new PriceEvent();
        priceEvent.setEventId(rs.getInt("eventId"));
        priceEvent.setSymbol(rs.getString("symbol"));
        priceEvent.setTimeStamp(rs.getString("timeStamp"));
        priceEvent.setLastTradePrice(rs.getString("lastTradePrice"));
        priceEvent.setVolume(rs.getString("volume"));
        return priceEvent;
    }
}
