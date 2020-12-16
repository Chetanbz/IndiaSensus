package indiaCensus;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
    public   Iterator<E> givenCSViterator(Reader reader, Class csvClass) throws CensusBuilderException;
    public List<E> givenList(Reader reader, Class csvClass) throws  CensusBuilderException;

}
