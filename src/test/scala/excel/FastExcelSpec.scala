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
      assert(s("A1") === "a")

      assert(s("F1") === "h")
      assert(s("F2") === "m")
      assert(s("F3") === "no")
      assert(s("F4") === "")
      assert(s("F7") === "")

      assert(s("G1") === "text")
      assert(s("G2") === "3.14159")
      assert(s("G3") === "1")
      assert(s("G4") === "#REF!")
    }
  }


}
