import shapeless.{HList, ::, HNil}

val product: String :: Int :: Boolean :: HNil =
  "Sunday" :: 1 :: false :: HNil

val first = product.head

val second = product.tail.head

val terceroBoolean = product.tail.tail.head
val rest = product.tail.tail
val ultimoNil = product.tail.tail.tail