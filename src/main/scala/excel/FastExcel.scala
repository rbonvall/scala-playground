package excel

import java.io.InputStream
import java.util.Iterator

import org.apache.poi.xssf.eventusermodel.XSSFReader
import org.apache.poi.xssf.model.SharedStringsTable
import org.apache.poi.xssf.usermodel.XSSFRichTextString
import org.apache.poi.openxml4j.opc.OPCPackage
import org.xml.sax.Attributes
import org.xml.sax.ContentHandler
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.helpers.XMLReaderFactory

import scala.collection.JavaConverters._

case class ExcelCell(value: String, formula: String)
case class ExcelSheet(cells: Map[String, ExcelCell]) {
  def apply(ref: String) = cells.get(ref).map(_.value).getOrElse("")
}

class FastExcelReader(val fileName: String) extends Iterable[ExcelSheet] {
  val pkg = OPCPackage.open(fileName)
  val reader = new XSSFReader(pkg)
  val sst = reader.getSharedStringsTable
  val parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser")
  val handler = new FastExcelReader.SheetHandler(sst)
  parser.setContentHandler(handler)

  def iterator = reader.getSheetsData.asScala.map { sheet ⇒
    val src = new InputSource(sheet)
    parser.parse(src)
    sheet.close()
    handler.parsedSheet
  }

}

object FastExcelReader {
  class SheetHandler(val sst: SharedStringsTable) extends DefaultHandler {
    var lastContents = ""
    var nextIsString = false
    val cells = scala.collection.mutable.Map[String, ExcelCell]()

    var formula = ""
    var value = ""
    var ref = ""
    var cellType = ""
    var s = ""

    def parsedSheet = ExcelSheet(cells.toMap)

    override def startElement(uri: String, localName: String, name: String, attributes: Attributes) = {
      name match {
        case "c" ⇒
          ref      = attributes.getValue("r")
          cellType = attributes.getValue("t")
          s        = attributes.getValue("s")
          formula = ""
          value = ""
        case _ ⇒
      }
      lastContents = ""
    }

    override def endElement(uri: String, localName: String, name: String) = {
      name match {
        case "f" ⇒
          formula = lastContents
        case "v" ⇒
          if (cellType != null && cellType == "s") {
            val idx = lastContents.toInt
            value = new XSSFRichTextString(sst.getEntryAt(idx)).toString
          }
          else {
            value = lastContents
          }
        case "c" ⇒
          cells += (ref → ExcelCell(value, formula))
        case _ ⇒
      }
    }

    override def characters(ch: Array[Char], start: Int, length: Int) =
      lastContents += new String(ch, start, length)

  }
}



