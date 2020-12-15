package indiaCensus;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader).withType(IndiaCensusCSV.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            int namOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return namOfEnteries;
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.DATA_IMPROPER);
        }
        catch (IOException e) {
            if(!(csvFilePath.matches("^.+\\.csv$"))) {
                throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE);
            }
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<CSVState> csvToBean = new CsvToBeanBuilder<CSVState>(reader).withType(CSVState.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<CSVState> censusCSVIterator = csvToBean.iterator();
            Iterable<CSVState> csvIterable = () -> censusCSVIterator;
            int namOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return namOfEnteries;
        }
        catch (RuntimeException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.DATA_IMPROPER);
        }
        catch (IOException e) {
            if(!(csvFilePath.matches("^.+\\.csv$"))) {
                throw new CensusAnalyserException("Invalid File", CensusAnalyserException.ExceptionType.INVALID_FILE_TYPE);
            }
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

}