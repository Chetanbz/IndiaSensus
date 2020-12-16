package indiaCensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {

            ICSVBuilder<IndiaCensusCSV> csvBuilder = (new csvBuilderFactory<IndiaCensusCSV>()).createBuilder();
            List<IndiaCensusCSV> indiaCensusCSVList =  csvBuilder.givenList(reader,IndiaCensusCSV.class);
            return  indiaCensusCSVList.size();
        }
        catch (RuntimeException e){
            throw new CensusBuilderException(e.getMessage(), CensusBuilderException.ExceptionType.DATA_IMPROPER);
        }
        catch (IOException e) {
            if(!(csvFilePath.matches("^.+\\.csv$"))) {
                throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.INVALID_FILE_TYPE);
            }
            throw new CensusBuilderException(e.getMessage(),
                    CensusBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
    public int loadIndiaStateData(String csvFilePath) throws CensusBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<CSVState> csvBuilder =(new csvBuilderFactory<CSVState>()).createBuilder();
            Iterator<CSVState> indiaStateCSVIterator =  csvBuilder.givenCSViterator(reader,CSVState.class);
            return  getCount(indiaStateCSVIterator);
        }
        catch (RuntimeException e){
            throw new CensusBuilderException(e.getMessage(), CensusBuilderException.ExceptionType.DATA_IMPROPER);
        }
        catch (IOException e) {
            if(!(csvFilePath.matches("^.+\\.csv$"))) {
                throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.INVALID_FILE_TYPE);
            }
            throw new CensusBuilderException(e.getMessage(),
                    CensusBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    private <E> int getCount(Iterator<E> csvIterator) throws CensusBuilderException {
        try {
            Iterable<E> csvIterable = () -> csvIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } finally { }
    }
}