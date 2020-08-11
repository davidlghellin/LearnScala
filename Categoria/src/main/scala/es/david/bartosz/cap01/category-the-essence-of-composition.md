Veamos como se trata lo equivalente en cada caso tanto en 
Haskell como en Scala

```Haskell
f :: A -> B
```
```scala
val f: A => B
```
................
```Haskell
g :: B -> C
```
```scala
val g: B => C
```
...............
```Haskell
g . f
```
```scala
g compose f
```
..............
```Haskell
f :: A -> B
g :: B -> C
h :: C -> D
h . (g . f) == (h . g) . f == h . g . f
```
```scala
val f: A => B
val g: B => C
val h: C => D

h compose (g compose f) === (h compose g) compose f === h compose g compose f
```
..............
```Haskell
id :: a -> a
id x = x
```
```scala
def identity[A](a: A): A = a
```
..............
```Haskell
f . id == f
id . f == f
```
```scala
f compose identity[A] == f
identity[B] _ compose f == f
```
..............