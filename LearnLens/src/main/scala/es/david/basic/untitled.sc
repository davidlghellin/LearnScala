
case class Iso[S, A](
                      get: S => A
                      , reverseGet: A => S
                    )

// Definimos las medidas
case class Metros(d: Double)

case class Yardas(d: Double)

val agetYar = (m: Metros) => Yardas(m .d * 1.09361)
val agetMet = (m: Yardas) => Metros(m.d / 1.09361)
val metroToYard: Iso[Metros, Yardas] = Iso(
  agetYar
  , agetMet
)