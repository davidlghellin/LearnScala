package es.david.basic
// Properties:
/*
for all s in S:reverseGet(get(s))=s
for all a in A:get(reverseGet(a))=a
 */
case class Iso[S, A](
                      get: S => A
                      , reverseGet: A => S
                    )
// {
//  def modify(m: A => A): S => S
//
//  def reverse: Iso[A, S]
//
//  def compose[B](other: Iso[A, B]): Iso[S, B]
//}