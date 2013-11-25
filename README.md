Coursera Principles of Reactive Programming Samples: Futures & Promises, Rx
---------------------------------------------------------------------------

`src/test/scala/coursera/adventure` contains the sample code for the "adventure game" lessons.
It introduces the `Try[T]` monad that makes failure explicit.

`src/test/scala/coursera/socket` contains the sample code for the "network programming" lessons.
It introduces the `Future[T]` monad that makes latency and failure explicit.

`src/test/scala/coursera/combinators` and `src/test/scala/coursera/Extensions.scala` contain various combinators and extensions on `Future[T]`
using both `async{ await{} }` and `val p = Promise[T](); ...; p.future`.

`src/test/scala/coursera/geocode` and `src/test/scala/coursera/usgs` contain bindings and code for accessing the usgs
earthquake and the geonames.org reverse geocode Web services. Earthequakes are exposed as an `Observablestream,
while rever geocoding of a single lat/lng pair returns a `Future`.

`src/test/scala/coursera/iterable` shows how to do blocking file IO using iterables.

`src/test/scala/coursera/rx` contains the sample code for all the Rx examples, allowing you to play with `Subscription`,
`Observable[T]` and `Observer[T]`, the various subjects, and (TODO) schedulers.

Inevitably slides contain typos because we don't have IntelliJ integration in Keynote yet.
When in doubt, consult the code samples here for the correct code.

### Running the examples

You can build and run some of the examples with sbt and Eclipse.

With sbt only: In the sbt console, execute `test-only <name of the test you want to run>`,
for instance `test-only coursera.rx.Subscriptions`.

With sbt+Eclipse: In the sbt console, execute `eclipse`, then import the project into eclipse,
right-click the name of the test you're interested in, and choose "Run As" > "Scala JUnit Test".

It would be great if someone that knows about sbt+IntelliJ to add support for that as well.


