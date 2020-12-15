package indiaCensus;

import com.opencsv.bean.CsvBindByName;
public class CSVState {
    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCode{" +
                "State Name='" + stateName + '\'' +
                ", StateCode='" + stateCode + '\'' +
                '}';
    }
}
