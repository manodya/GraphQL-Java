package com.mub.graphql;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mub.graphql.dao.DataService;
import com.mub.graphql.dao.PriceEventDAOI;
import com.mub.graphql.fetcher.*;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

/**
 * Created by manodyas on 2/6/2018.
 */

@Controller
@CrossOrigin
public class TestController {
    private GraphQL graphQL;


    @Value("classpath:test.graphqls")
    private Resource schemaResource;

    static {

    }

    @Autowired
    private AllPriceEventDataFetcher allPriceEventDataFetcher;
    @Autowired
    private PriceEventDataFetcher priceEventDataFetcher;
    @Autowired
    private AllOrdersDataFetcher allOrdersDataFetcher;
    @Autowired
    private ExecutionsDataFetcher executionsDataFetcher;
    @Autowired
    private SingleOrderDataFetcher singleOrderDataFetcher;

    @PostConstruct
    public void setup() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry  =  new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRunTimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        graphQL = newGraphQL(schema).build();

    }

    private RuntimeWiring buildRunTimeWiring() {

        
        return  newRuntimeWiring().
                type("Query", typeWiring -> typeWiring
                        .dataFetcher("allPriceEvents", allPriceEventDataFetcher)
                        .dataFetcher("singlePriceEvent", priceEventDataFetcher)
                        .dataFetcher("allOrders", allOrdersDataFetcher)
                        .dataFetcher("getOrder",  singleOrderDataFetcher)

                ).
                type("Order", typeWiring -> typeWiring.dataFetcher("executionList", executionsDataFetcher))
                .build();
    }



    @RequestMapping(value = "/inquiry/", method = {RequestMethod.POST}, headers = {"Content-type=application/json"})
    @ResponseBody
    public Object processRESTRequest(@RequestBody String query) {
        System.out.println("Query :" + query);
        ExecutionResult result = graphQL.execute(query);
        System.out.println(" @@@@@@@@@@" +result.getErrors());
        return result.getData();
    }

    @RequestMapping(value = "/inquiry/athena", method = {RequestMethod.POST}, headers = {"Content-type=application/json"})
    @ResponseBody
    public Object processAthenaClient(@RequestBody String query) {
        HashMap<String,Object> map = new Gson().fromJson( query, new TypeToken<HashMap<String, Object>>(){}.getType());
         String tempQuery = (String) map.get("query");
        System.out.println("###Query :" + tempQuery);
        ExecutionResult result = graphQL.execute(tempQuery);
        System.out.println(" @@@@@@@@@@" +result.getErrors());
        System.out.println("###Response :" + result.getData());
        return result;
    }
}
