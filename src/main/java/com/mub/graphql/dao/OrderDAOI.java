package com.mub.graphql.dao;

import com.mub.graphql.bo.Execution;
import com.mub.graphql.bo.Order;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.mub.graphql.dao.mapper.*;

import java.util.List;

/**
 * Created by manodyas on 2/7/2018.
 */
public interface OrderDAOI {
    @SqlQuery("select * from orders")
    @Mapper(OrderMapper.class)
    List<Order> getOrders();

    @SqlQuery("select * from executions where orderId = :orderId")
    @Mapper(ExecutionMapper.class)
    List<Execution> getExecutions(@Bind("orderId") int orderId);


    @SqlQuery("select * from orders where orderId=:orderId")
    @Mapper(OrderMapper.class)
    Order getOrder(@Bind("orderId") int orderId);


}
