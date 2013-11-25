Coursera Principles of Reactive Programming Samples: Futures & Promises, Rx
---------------------------------------------------------------------------

`src/test/scala/coursera/adventure` contains the sample code for the "adventure game" lessons.
It introduces the `Try[T]` monad that makes failure explicit.

`src/test/scala/coursera/socket` contains the sample code for the "network programming" lessons.
It introduces the `Future[T]` monad that makes latency and failure explicit.

`src/test/scala/coursera/combinators` and `src/test/scala/coursera/Extensions.scala` contain various combinators and extensions on `Future[T]`
using both `async{ await{} }` and `val p = Promise[T](); ...; p.future`.

Inevitably slides contain typos because we don't have IntelliJ integration in Keynote yet.
When in doubt, consult the code samples here for the correct code.

### Running the examples

You can build and run some of the examples with sbt and Eclipse.

With sbt only: In the sbt console, execute `test-only <name of the test you want to run>`, for instance `test-only coursera.rx.Subscriptions`.

With sbt+Eclipse: In the sbt console, execute `eclipse`, then import the project into eclipse, right-click the name of the test you're interested in, and choose "Run As" > "Scala JUnit Test"
