coursera Principles of Reactive Programming Samples: Futures & Promises, Rx
---------------------------------------------------------------------------

/Adventure contains the sample code for the "adventure game" lessons.
It introduces the `Try[T]` monad that makes failure explicit.

/Socket contains the sample code for the "network programming" lessons.
It introduces the `Future[T]` monad that makes latency and failure explicit.

/Combinators and Extensions contain various combinators and extensions on `Future[T]`
using both `async{ await{} }` and `val p = Promise[T](); ...; p.future`.

Inevitably slides contain typos because we don't have IntelliJ integration in Keynote yet.
When in doubt, consult the code samples here for the correct code.
