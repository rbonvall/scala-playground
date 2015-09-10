package excel

import org.scalatest.FunSpec

class FastExcelSpec extends FunSpec {

  describe("FastExcelReader") {
    it("works") {
      val workbook = new FastExcelReader("src/test/resources/example.xlsx")
      // workbook.foreach { sheet ⇒
      //   println( sheet.cells.mkString("\n") )
      //   println()
      // }
      val sheets = workbook.toList
      assert(sheets.length === 2)
      val s = sheets(0)
      assert(s("A1") === Some("a"))

      assert(s("F1") === Some("h"))
      assert(s("F2") === Some("m"))
      assert(s("F3") === Some("no"))
      assert(s("F4") === Some(""))
      assert(s("F7") === None)

      assert(s("G1") === Some("text"))
      assert(s("G2") === Some("3.14159"))
      assert(s("G3") === Some("1"))
      assert(s("G4") === Some("#REF!"))
    }
  }


}
