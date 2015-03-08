package excel

import org.scalatest.FunSpec

class excelSpec extends FunSpec {

  describe("Cell") {
    it("extracts the row and the column from the coordinates") {
      val cell = Cell("ABC1234", "Whatevah")
      assert(cell.col === "ABC")
      assert(cell.row === "1234")
    }
  }

  describe("readWorkbook") {
    it("sorta works") {
      val wb = excel.readWorkbook("src/test/resources/sample.xlsx")
      assert(wb.sheets.size === 3)
      val sh = wb.sheets.head
      println(sh.rows map { _.map(_.content).mkString("|")} mkString "\n")
      assert(sh.name === "RuleDef")
      assert(sh.rows.size === 10)
      assert(sh.rows.head.size === 12)
      val cl = sh.rows(9)(11)
      assert(cl.coords === "L10")
      //assert(cl.content === "Y")
    }
  }

}
