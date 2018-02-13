package com.mub.graphql.dao.mapper;

import com.mub.graphql.bo.Order;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by manodyas on 2/7/2018.
 */
public class OrderMapper implements ResultSetMapper<Order> {
    @Override
    public Order map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setSymbol(rs.getString("symbol"));
        order.setQty(rs.getString("qty"));
        order.setSide(rs.getString("side"));
        order.setType(rs.getString("type"));
        order.setPrice(rs.getString("price"));
        order.setStatus(rs.getString("status"));
        return order;
    }
}
