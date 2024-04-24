import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
    assert(ClimateService.isClimateRelated("The IPCC reports on global warming and climate change") == true)

  }

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }
  test("parseRawData - empty list should return empty list") {
  assert(ClimateService.parseRawData(List.empty) == List.empty)
}


  //@TODO
  test("filterDecemberData") {
  val records = List(
    Some(CO2Record(2023, 12, 400.0)), 
    Some(CO2Record(2023, 11, 410.0)), 
    Some(CO2Record(2023, 10, 420.0))  
  )

  val filteredRecords = ClimateService.filterDecemberData(records) 
  assert(filteredRecords.length == 2) 
  assert(!filteredRecords.exists(_.month == 12)) 
}

test("getMinMax - valid input") {
  val records = List(
    CO2Record(2003, 1, 355.2),
    CO2Record(2003, 2, 360.2),
    CO2Record(2003, 3, 358.6)
  )

  assert(ClimateService.getMinMax(records) == (355.2, 360.2))
}

test("getMinMaxByYear - valid input") {
  val records = List(
    CO2Record(2003, 1, 355.2),
    CO2Record(2003, 2, 360.2),
    CO2Record(2003, 3, 358.6),
    CO2Record(2004, 1, 362.1),
    CO2Record(2004, 2, 364.5)
  )

  assert(ClimateService.getMinMaxByYear(records, 2003) == (355.2, 360.2))
}


}
