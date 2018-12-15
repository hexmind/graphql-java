package pl.hexmind.greeting;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class GreetingQuery implements GraphQLQueryResolver {

    private final GreetingRepository greetingRepository;

    public GreetingQuery(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String id) {
        return greetingRepository.find(id);
    }
}
