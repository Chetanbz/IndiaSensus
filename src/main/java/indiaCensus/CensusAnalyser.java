package indiaCensus;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<CSVState> stateCSVList = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<IndiaCensusCSV> csvBuilder = (new csvBuilderFactory<IndiaCensusCSV>()).createBuilder();
            censusCSVList = csvBuilder.givenList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (RuntimeException e) {
            throw new CensusBuilderException(e.getMessage(), CensusBuilderException.ExceptionType.DATA_IMPROPER);
        } catch (IOException e) {
            if (!(csvFilePath.matches("^.+\\.csv$"))) {
                throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.INVALID_FILE_TYPE);
            }
            throw new CensusBuilderException(e.getMessage(),
                    CensusBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    public int loadIndiaStateData(String csvFilePath) throws CensusBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder<CSVState> csvBuilder = (new csvBuilderFactory<CSVState>()).createBuilder();
            stateCSVList = csvBuilder.givenList(reader,CSVState.class);
            return stateCSVList.size();
        } catch (RuntimeException e) {
            throw new CensusBuilderException(e.getMessage(), CensusBuilderException.ExceptionType.DATA_IMPROPER);
        } catch (IOException e) {
            if (!(csvFilePath.matches("^.+\\.csv$"))) {
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
        } finally {
        }
    }

    public String getSortedStateList() throws CensusBuilderException {
        if(censusCSVList.size() == 0){
            throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.No_DATA);
        }
            Comparator <IndiaCensusCSV> censusCSVComparator = Comparator.comparing(Census -> Census.state);
            this.sort(censusCSVComparator);
            String sortedStateCensusJason = new Gson().toJson(censusCSVList);
            return sortedStateCensusJason;
        }

    public String getSortedPopulousList() throws CensusBuilderException{
        if(censusCSVList.size() == 0){
            throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.No_DATA);
        }
        Comparator <IndiaCensusCSV> censusCSVComparator = Comparator.comparing(Census -> Census.population);
        this.sort(censusCSVComparator);
        String sortedStateCensusJason = new Gson().toJson(censusCSVList);
        return sortedStateCensusJason;
    }
    public String getSortedPopulousDensityList() throws CensusBuilderException {
        if(censusCSVList.size() == 0){
            throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.No_DATA);
        }
        Comparator <IndiaCensusCSV> censusCSVComparator = Comparator.comparing(Census -> Census.densityPerSqKm);
        this.sort(censusCSVComparator);
        String sortedStateCensusJason = new Gson().toJson(censusCSVList);
        return sortedStateCensusJason;
    }

    public String getSortedStateCodeList() throws CensusBuilderException {
        if(stateCSVList.size() == 0){
            throw new CensusBuilderException("Invalid File", CensusBuilderException.ExceptionType.No_DATA);
        }
        Comparator <CSVState> censusCSVComparator = Comparator.comparing(Census -> Census.stateCode);
        this.sortState(censusCSVComparator);
        String sortedStateCensusJason = new Gson().toJson(stateCSVList);
        return sortedStateCensusJason;
    }

    private void sort( Comparator<IndiaCensusCSV> censusCSVComparator) {
        for (int i =0; i<censusCSVList.size(); i++){
            for (int j =0; j < censusCSVList.size()-i-1; j++){
                IndiaCensusCSV censusCSV1 = censusCSVList.get(j);
                IndiaCensusCSV censusCSV2 = censusCSVList.get(j+1);
                if(censusCSVComparator.compare(censusCSV1,censusCSV2)>0){
                    censusCSVList.set(j,censusCSV2);
                    censusCSVList.set(j+1,censusCSV1);
                }
            }
        }
    }

    private void sortState( Comparator<CSVState> censusCSVComparator) {
        for (int i =0; i<stateCSVList.size(); i++){
            for (int j =0; j < stateCSVList.size()-i-1; j++){
                CSVState censusCSV1 = stateCSVList.get(j);
                CSVState censusCSV2 = stateCSVList.get(j+1);
                if(censusCSVComparator.compare(censusCSV1,censusCSV2)>0){
                    stateCSVList.set(j,censusCSV2);
                    stateCSVList.set(j+1,censusCSV1);
                }
            }
        }
    }


}