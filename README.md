![Header](/src/main/resources/static/sandm.png)

# Series And Movies

### A Spring w/ Thymeleaf project
#### - Made for studies in Cloud Dev @ Jensen Academy, Sweden

## Brief Description

A Spring / Thymeleaf webapp that handles two entities (Series & Movies) with basic CRUD-functionality.
Requirements for the assignment also included:
(Doubles as a list of content).

- UML-Diagrams
    - Class Diagram
    - Sequence Diagram
    - State Diagram
    - Activity Diagram
    - Use Case Diagram
- Docker w/ Docker Compose for the Database
- Automated testing with:
    - jUnit + Mockito
    - Cucumber (rudimentary)
- Successful build on push through GitHub Actions
- Some custom exception handling
- This readme

## UML

They are included in the /static. I am unsure about how correct they are.
Found it a little bit hard to grasp the "right" way to do it since relations between classes,
states or activities are multi-faceted and could, *I feel*, be conveyed in several ways.

My two main concerns was / is that I wonder if, in the activity diagram, should be strictly only "choices",
and not "automatic" stuff like "Show page X" or "Got confirmation". That question leads to the next,
which is whether the separate diagrams should be made to convey the app by that diagram alone,
or in tandem with the other ones. I pretty much know the answer is that "it depends" and that different companies / groups,
have different praxis about this, but there should maybe still be a "general" way?

The other diagram that was a little bit confounding was the class diagram.
I have to go back and study the arrows more. Seems like a thing that is a me-problem,
you know like some people are just blind to numbers or tunes or letters. To me almost every way of "arrowing" can make sense given a change of perspective. 😊

> When a GlobalExceptionHandler listens to an Exception, that is annotated in the entity, validated in the controller, but thrown in the service method, where do i put the arrow,
and does the Handler "listen" which points to the exception, or is the exception thrown in the direction of the Handler?

## Docker w/ Docker Compose

Not much to say here. By the time you're reading this it should be working as planned, and should also be up on my DockerHub.
Docker still feels a little bit alien, but is of course amazing tech. I wanted to wait until the last moment of the project to set it up, so that I didn't have to build it each time I changed code.

## Automated Testing & Build w/ GH Actions

The "automated" part of the testing was not hard to grasp, quite easy to set up and I understand that it is a significant quality-of-life improvement,
instead of always...


>1. Changing the code
>2. Starting the application
>3. Navigating to the right page
>4. Adding an entity
>5. Change page
>6. Updating it
>7. Change page
>8. Removing it
>9. Trying footer link (or some other func)

...in each iteration of code dev.

However, the extensive testing we were shown in this course, with Mockito, MockBeans, Mocks InjectMocks and the intricacies of dependency injection and having to keep in mind both the steering of mocks,
and that it runs towards a h2-db, was new to me. I do like it, for whatever that matters, but I feel far from being able to handle the syntax, and even less apt at thinking in a test-first-fashion.
More practice is due. In my next project I will at least strive to do the above list of test (basic CRUD) first.

The Cucumber Tests are very rudimentary, so I won't mention them, more than saying that I do have some strange hate/love-relation to them,
but I feel that the probably work best as a tests-first, develop new features' framework. As for just the constant feedback of automated testing, the overhead of use comes with no more return than jUnit with half the overhead.

## Some Custom Exception Handling

This was also new to me, and I think, now in retrospect, my favorite part even if it didn't turn out quite as nice as I hoped.
I really made an effort to get specific error messages back to user (specific to what input was invalid), but due to this having to also be ready for release (a.k.a "assignment due date"), I had to compromise.

The app has two entities, but they are handled in the exact same manner so here I will describe the exceptions I wanted to handle in one (but it's the exact same for the other):

The app has a view for Movies were there is a html-form that takes in a name and a rating to save a new Movie.
There is also a separate view for each individual Movie, which contains a html-form to update a Movie (same properties / parameters).
But since the controller-save doesn't need an id due to auto-increment in db, while the update of course needs to be pointed to the right Movie to update, they couldn't be handled the same way.

Exceptions that I aimed to handle:

| Input                    | Save                     | Update                       |  
|--------------------------|--------------------------|------------------------------|
| No Name + No Rating      | `MethodArgumentNotValid` | `MethodArgumentNotValid`     |
| No Name + Invalid Rating | `MethodArgumentNotValid` | `TransactionSystemException` |
| No Name + Ok Rating      | `MethodArgumentNotValid` | `TransactionSystemException` |
| Name +  No rating        | `MethodArgumentNotValid` | `MethodArgumentNotValid`     |
| Name + Invalid rating    | `MethodArgumentNotValid` | `TransactionSystemException` |

So, the **rating** `property` was an `int`, which didn't jive well with the html-form passing a string value of `""`, and also it was annotated with `@Range(1-10)`,
so I think that at least one of the invalid scenario threw a `ConstraintsViolatonException` which I think was a subclass or at least a `getCause` of `TransactionSystemException`,
since it allowed itself to be handled in the Handler that listened to `TransactionSystemException` but I think due to my not-pro code, override som other invalid, or something?

Long story long, I ended up refactoring everything so that whatever invalid from above, user is prompted with:

**Fields may not be empty, and rating must be between 1-10.**

## This Readme

This has been a trial by fire. Before this course I always held the phonetics-course @ Uni, as one of those 80's-movie-montage "rise-to-the-challenge" moments in my life.
This course has now replaced that. We have learned (gotten acquainted with) UML, Docker, Docker Compose, Cucumber (do not mock the cucumber), Mockito, GitHub Workflow / Actions, Thymeleaf, H2-DB and introduced to custom handling of exceptions.
This was also (kind of) the first project where I had planned set-aside time for refactoring, comments and warning-cleaning, which made me feel good about the end product, simple as it is, but with clean enough code that I feel I can own it.
I would have never gotten this far if it wasn't for our Dev in Chief - Master Teacher: Linus Rudbeck, from Distansakademin.
10 / 10. Would adopt.

// Johan Ek
