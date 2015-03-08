package excel

import org.apache.poi.xssf.usermodel._
import scala.collection.JavaConverters._
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
      xssfSheet ← xssfWorkbook.asScala.toSeq
      name = xssfSheet.getSheetName
      rows = for {
        xssfRow ← xssfSheet.asScala.toSeq
        rowCells = for {
          c ← xssfRow.asScala.toSeq
          xssfCell = c.asInstanceOf[XSSFCell]
        } yield Cell(xssfCell.getReference, xssfCell.getStringCellValue)
      } yield rowCells
    } yield Sheet(name, rows)
    Workbook(fileName, sheets)
  }
}
