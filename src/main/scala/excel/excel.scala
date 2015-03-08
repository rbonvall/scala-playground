package excel

import org.apache.poi.xssf.usermodel._
import java.io.{FileInputStream, File}

case class Cell(coords: String, content: String) {
  val coordParts = coords.split("(?<=[A-Za-z])(?=[0-9])")
  val row = coordParts(1)
  val col = coordParts(0)
}
case class Sheet(name: String, rows: Seq[Seq[Cell]])
case class Workbook(fileName: String, sheets: Seq[Sheet])

object excel {
  def readWorkbook(fileName: String) = {
    val file: FileInputStream = new FileInputStream(new File(fileName))
    val xssfWorkbook: XSSFWorkbook = new XSSFWorkbook(file)
    val sheets = for {
      n ← 0 until xssfWorkbook.getNumberOfSheets
      xssfSheet = xssfWorkbook.getSheetAt(n)
      name = xssfSheet.getSheetName
      rows = for {
        i ← 0 until xssfSheet.getPhysicalNumberOfRows
        xssfRow = xssfSheet.getRow(i)
        rowCells = for {
          j ← 0 until xssfRow.getPhysicalNumberOfCells
          xssfCell = xssfRow.getCell(j)
        } yield Cell(xssfCell.getReference, xssfCell.getStringCellValue)
      } yield rowCells
    } yield Sheet(name, rows)
    Workbook(fileName, sheets)
  }
}
