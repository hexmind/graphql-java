package pl.hexmind.common.graphql;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
@AllArgsConstructor
public class GraphqlRequest {

    private String query;
    private Map<String, Object> variables;

    public GraphqlRequest(String query) {
        this(query, Collections.emptyMap());
    }
}
