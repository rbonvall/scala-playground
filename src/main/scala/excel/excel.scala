package excel

import org.apache.poi.xssf.usermodel._
import org.apache.poi.ss.usermodel.{Cell ⇒ SSCell}
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
  private def processCell(cell: XSSFCell) = {
    import SSCell._
    val value = cell.getCellType match {
      case CELL_TYPE_BLANK   ⇒ ""
      case CELL_TYPE_BOOLEAN ⇒ if (cell.getBooleanCellValue) "=TRUE()" else "=FALSE()"
      case CELL_TYPE_ERROR   ⇒ "=ERROR()"
      case CELL_TYPE_FORMULA ⇒ "=" + cell.getCellFormula
      case CELL_TYPE_NUMERIC ⇒ cell.getNumericCellValue.toString
      case CELL_TYPE_STRING  ⇒ cell.getStringCellValue
    }
    Cell(cell.getReference, value)
  }

  def readWorkbook(fileName: String) = {
    val fileStream = new FileInputStream(new File(fileName))
    val xssfWorkbook = new XSSFWorkbook(fileStream)
    val sheets = for {
      xssfSheet ← xssfWorkbook.asScala.toSeq
      name = xssfSheet.getSheetName
      rows = for {
        xssfRow ← xssfSheet.asScala.toSeq
        rowCells = for {
          c ← xssfRow.asScala.toSeq
        } yield processCell(c.asInstanceOf[XSSFCell])
      } yield rowCells
    } yield Sheet(name, rows)
    fileStream.close
    Workbook(fileName, sheets)
  }
}
