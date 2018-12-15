package pl.hexmind.greeting;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class GreetingMutation implements GraphQLMutationResolver {

    private final GreetingRepository greetingRepository;

    public GreetingMutation(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting newGreeting(String message) {
        Greeting greeting = new Greeting();
        greeting.setMessage(message);

        return greetingRepository.save(greeting);
    }
}
