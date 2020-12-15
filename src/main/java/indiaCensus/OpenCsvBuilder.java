package indiaCensus;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCsvBuilder <E> implements  ICSVBuilder<E>{
    @Override
    public Iterator<E> givenCSViterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        return getCSVBean(reader,csvClass).iterator();
    }
    @Override
    public List<E> givenCSVList(Reader reader, Class csvClass) throws CensusAnalyserException {
        return getCSVBean(reader,csvClass).parse();
    }
    private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CensusAnalyserException {
        try {
            return new CsvToBeanBuilder<E>(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
        }
        catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
