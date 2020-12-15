package indiaCensus;

public class csvBuilderFactory<E>  {
    public ICSVBuilder<E> createBuilder(){
        return  new OpenCsvBuilder<E>();
    }

}
