package indiaCensus;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCsvBuilder {
    public static <E>Iterator<E> givenCSViterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true).build();
            return  csvToBean.iterator();
        }
        catch (IllegalStateException e){
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
