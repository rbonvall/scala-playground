package excel

case class Workbook(fileName: String, sheets: Seq[Sheet])
case class Sheet(name: String, rows: Seq[Seq[Cell]])
case class Cell(coords: String, content: String) {
  val coordParts = coords.split("(?<=[A-Za-z])(?=[0-9])")
  val row = coordParts(1)
  val col = coordParts(0)
}
