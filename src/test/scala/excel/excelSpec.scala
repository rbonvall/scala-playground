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

}
