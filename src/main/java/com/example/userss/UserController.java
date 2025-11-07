package com.example.userss;

import com.example.global.AuthorNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/users")
    public String getAllUsers() {
        return "Hello World";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Welcome to Spring Boot!";
    }

    @GetMapping("/authors")
    public String getAuthors(@RequestParam(name = "authorName") String name, @RequestParam(name = "fullName") String fullName) {
        return "Author name is: " + name + " fullName=" + fullName;
    }

    @GetMapping("/users/{id}")
    public String loadUser(@PathVariable Long id) {
        log.trace("This is a TRACE message");
        return id + " loaded";
    }

    @GetMapping("/userrr")
    public Author loadUserJson(@RequestBody Author author) {
        return author;
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public String getOrder(
            @PathVariable("userId") int userId,
            @PathVariable("orderId") int orderId) {

        return "User: " + userId + ", Order: " + orderId;
    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable int id) {
        if (id != 1) {
            throw new AuthorNotFoundException("Author not found with id: " + id);
        }

        return new Author("Reza Valipour", 35);
    }
    @PostMapping
    public AuthorRequest createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        return authorRequest;
    }
}