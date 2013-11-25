package coursera.rx

import rx.lang.scala.subjects.AsyncSubject
import rx.lang.scala.Observable
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

object ToObservable {

   def apply[T](future: Future[T]): Observable[T] = {
     val subject = AsyncSubject[T]()

     future.onSuccess{
       case s => {
        subject.onNext(s);
        subject.onCompleted()
        }
     }

     future.onFailure{
       case e => {
        subject.onError(e)
       }
     }

     subject
   }
}
