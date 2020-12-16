package indiaCensus;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_TYPE = "./src/main/resources/IndiaStateCensusData.txt";
    private static final String DELIMINATOR_MISS = "D:\\eclipse-java-2020-09-R-win32-x86_64\\Week3,Workspace Eclipse\\SensusIndia\\src\\test\\resources\\IndiaStateCensusDataDelimininatorMiss.csv";
    //***
    private static final String INDIA_STATE_CODE = "D:\\eclipse-java-2020-09-R-win32-x86_64\\Week3,Workspace Eclipse\\SensusIndia\\src\\test\\resources\\IndiaStateCode.csv";
    private static final String DELIMINATOR_MISS_State_Code ="D:\\eclipse-java-2020-09-R-win32-x86_64\\Week3,Workspace Eclipse\\SensusIndia\\src\\test\\resources\\IndiaStateCodeDilimatorMiss.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusBuilderException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_FILE_TYPE);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.INVALID_FILE_TYPE,e.type);
        }

    }
    @Test
    public void givenIndiaCensusData_WithDeliminatorMiss_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaCensusData(DELIMINATOR_MISS);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.DATA_IMPROPER,e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WithHeaderrMiss_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaCensusData(DELIMINATOR_MISS);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.DATA_IMPROPER,e.type);
        }
    }
    //************
    @Test
    public void givenStateCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusBuilderException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenState_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_PATH);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndiaStateData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaStateData(WRONG_FILE_TYPE);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.INVALID_FILE_TYPE,e.type);
        }

    }
    @Test
    public void givenIndiaStateData_WithDeliminatorMiss_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaStateData(DELIMINATOR_MISS_State_Code);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.DATA_IMPROPER,e.type);
        }
    }
    @Test
    public void givenIndiaStateData_WithHeaderrMiss_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusBuilderException.class);
            censusAnalyser.loadIndiaStateData(DELIMINATOR_MISS_State_Code);
        } catch (CensusBuilderException e) {
            Assert.assertEquals(CensusBuilderException.ExceptionType.DATA_IMPROPER,e.type);
        }
    }
   @Test
    public void givenIndiaCensuseData_ShouldSortBasedonState() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData =   censusAnalyser.getSortedStateList();
            IndiaCensusCSV [] censusCSV = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[0].state);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenIndiaStateData_ShouldSortBasedonStateCode() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE);
            String sortedCensusData =   censusAnalyser.getSortedStateCodeList();
            CSVState [] censusCSV = new Gson().fromJson(sortedCensusData,CSVState[].class);
            Assert.assertEquals("AD",censusCSV[0].stateCode);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenIndiaCensuseData_ShouldSortBasedOnPopulous() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData =   censusAnalyser.getSortedPopulousList();
            IndiaCensusCSV [] censusCSV = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh",censusCSV[numOfRecords-1].state);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenIndiaCensuseData_ShouldSortBasedOnDensity() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData =   censusAnalyser.getSortedPopulousDensityList();
            IndiaCensusCSV [] censusCSV = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar",censusCSV[numOfRecords-1].state);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenIndiaCensuseData_ShouldSortBasedOnArea() {
        try{
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData =   censusAnalyser.getSortedPopulousAreaList();
            IndiaCensusCSV [] censusCSV = new Gson().fromJson(sortedCensusData,IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan",censusCSV[numOfRecords-1].state);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
