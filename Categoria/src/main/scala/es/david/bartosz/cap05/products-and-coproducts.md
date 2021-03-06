```Haskell
absurd :: Void -> a
```
```scala
def absurd[A]: Nothing => A
```
................
```Haskell
unit :: a -> ()
unit _ = ()
```
```tut:silent
def unit[A]: A => Unit = _ => ()
```
................
```Haskell
yes :: a -> Bool
yes _ = True
```
```tut:silent
def yes[A]: A => Boolean = _ => true
```
................
```Haskell
no :: a -> Bool
no _ = False
```
```tut:silent
def no[A]: A => Boolean = _ => false
```
................
```Haskell
f . g = id
g . f = id
```
```scala
f compose g == identity _
g compose f == identity _
```
................
```Haskell
fst :: (a, b) -> a
fst (x, y) = x
```
```tut:silent
def fst[A, B]: ((A, B)) => A = {
  case (x, y) => x
}
```
................
```Haskell
snd :: (a, b) -> b
snd (x, y) = y
```
```tut:silent
def snd[A, B]: ((A, B)) => B = {
  case (x, y) => y
}
```
................
```Haskell
fst (x, _) = x
snd (_, y) = y
```
```tut:silent
def fst[A, B]: ((A, B)) => A = {
  case (x, _) => x
}
def snd[A, B]: ((A, B)) => B = {
  case (_, y) => y
}
```
................
```Haskell
p :: c -> a
q :: c -> b
```
```scala
def p: C => A
def q: C => B
```
................
```Haskell
p :: Int -> Int
p x = x

q :: Int -> Bool
q _ = True
```
```tut:silent
def p: Int => Int = x => x

def q: Int => Boolean = _ => true
```
................
```Haskell
p :: (Int, Int, Bool) -> Int
p (x, _, _) = x

q :: (Int, Int, Bool) -> Bool
q (_, _, b) = b
```
```tut:silent
def p: ((Int, Int, Boolean)) => Int = _._1

def q: ((Int, Int, Boolean)) => Boolean = _._3
```
................
```Haskell
p' = p . m
q' = q . m
```
```scala
pᛌ == p compose m
qᛌ == q compose m
```
................
```Haskell
m :: Int -> (Int, Bool)
m x = (x, True)
```
```tut:silent
def m: Int => (Int, Boolean) = x => (x, true)
```
................
```Haskell
p x = fst (m x) = x
q x = snd (m x) = True
```
```tut:silent
def p: Int => Int = x => fst(m(x)) // == x
def q: Int => Boolean = x => snd(m(x)) // == true
```
................
```Haskell
m (x, _, b) = (x, b)
```
```tut:silent
def m: ((Int, Int, Boolean))
    => (Int, Boolean) = {
  case (x, _, b) => (x, b)
}
```
................
```Haskell
fst = p . m'
snd = q . m'
```
```scala
fst == p compose mᛌ
snd == q compose mᛌ
```
................
```Haskell
m' (x, b) = (x, x, b)
```
```tut:silent
def mᛌ: ((Int, Boolean))
    => (Int, Int, Boolean) = {
  case (x, b) => (x, x, b)
}
```
................
```Haskell
m' (x, b) = (x, 42, b)
```
```tut:silent
def mᛌ: ((Int, Boolean))
    => (Int, Int, Boolean) = {
  case (x, b) => (x, 42, b)
}
```
................
```Haskell
m :: c -> (a, b)
m x = (p x, q x)
```
```scala
def m: C => (A, B) = x => (p(x), q(x))
```
................
```Haskell
factorizer :: (c -> a) -> (c -> b) -> (c -> (a, b))
factorizer p q = \x -> (p x, q x)
```
```tut:silent
def factorizer[A, B, C]
    : (C => A) => (C => B) => (C => (A, B)) =
  p => q => x => (p(x), q(x))
```
................
```Haskell
i :: a -> c
j :: b -> c
```
```scala
def i: A => C
def j: B => C
```
................
```Haskell
i' = m . i
j' = m . j
```
```scala
iᛌ == m compose i
jᛌ == m compose j
```
................
```Haskell
data Contact = PhoneNum Int | EmailAddr String
```
```tut:silent
sealed trait Contact
final case class PhoneNum(
    num: Int) extends Contact
final case class EmailAddr(
    addr: String) extends Contact
```
................
```Haskell
helpdesk :: Contact;
helpdesk = PhoneNum 2222222
```
```tut:silent
def helpdesk: Contact = PhoneNum(2222222)
```
................
```Haskell
Either a b = Left a | Right b
```
```tut:silent
sealed trait Either[A, B]
final case class Left[A](
    v: A) extends Either[A, Nothing]
final case class Right[B](
    v: B) extends Either[Nothing, B]
```
................
```Haskell
factorizer :: (a -> c) -> (b -> c) -> Either a b -> c
factorizer i j (Left a)  = i a
factorizer i j (Right b) = j b
```
```tut:silent
def factorizer[A, B, C]
    : (A => C) => (B => C)
    => Either[A, B] => C =
  i => j => {
    case Left(a) => i(a)
    case Right(b) => j(b)
  }
```
................
```Haskell
p = fst . m
q = snd . m
```
```scala
p == fst compose m
q == snd compose m
```
................
```Haskell
p () = fst (m ())
q () = snd (m ())
```
```scala
p(()) == fst(m(()))
q(()) == snd(m(()))
```