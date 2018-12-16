package pl.hexmind.web;

import java.net.URI;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(graphIQLPath())
                .build();
    }

    @NotNull
    private URI graphIQLPath() {
        return UriComponentsBuilder.fromPath("graphiql").build().toUri();
    }

}
