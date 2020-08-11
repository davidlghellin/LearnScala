package es.david.mail

object TaskMail {
  type Task[F[_], A] = A => F[A]
}
