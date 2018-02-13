package com.mub.graphql.dao.mapper;

import com.mub.graphql.bo.Execution;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by manodyas on 2/7/2018.
 */
public class ExecutionMapper implements ResultSetMapper<Execution> {
    @Override
    public Execution map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        Execution execution = new Execution();
        execution.setExecutionId(rs.getInt("executionId"));
        execution.setOrderId(rs.getInt("orderId"));
        execution.setQty(rs.getString("qty"));
        execution.setPrice(rs.getString("price"));
        execution.setTimeStamp(rs.getString("timeStamp"));
        execution.setFilledQty(rs.getString("filledQty"));
        execution.setAvgPrice(rs.getString("avgPrice"));
        return execution;
    }
}
