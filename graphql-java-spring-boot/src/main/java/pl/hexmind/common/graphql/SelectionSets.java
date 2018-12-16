package pl.hexmind.common.graphql;

import graphql.language.Field;
import graphql.language.SelectionSet;
import graphql.schema.DataFetchingEnvironment;

import java.util.Map;
import java.util.stream.Collectors;

public final class SelectionSets {

    private SelectionSets() {
    }

    public static String join(String name, DataFetchingEnvironment environment) {
        return toMap(environment).get(name)
                .getSelections().stream()
                .filter(s -> s instanceof Field)
                .map(s -> (((Field) s).getName()))
                .collect(Collectors.joining("\n"));
    }

    private static Map<String, SelectionSet> toMap(DataFetchingEnvironment environment) {
        return environment.getFields().stream()
                .collect(Collectors.toMap(Field::getName, Field::getSelectionSet));
    }
}
