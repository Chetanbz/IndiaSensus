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
            Iterator<IndiaCensusCSV> censusCSVIterator = getIterator(reader,IndiaCensusCSV.class);
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
            Iterator<CSVState> censusCSVIterator = getIterator(reader,CSVState.class);
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
    private <E> Iterator<E> getIterator(Reader reader, Class csvClass) throws  CensusAnalyserException{
        CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
        return  csvToBean.iterator();
    }
}