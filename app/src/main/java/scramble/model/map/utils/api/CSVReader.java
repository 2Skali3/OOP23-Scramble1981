package scramble.model.map.utils.api;

import java.util.ArrayList;

public interface CSVReader<X> {

    /**
     * Funzione per la lettura di csv
     * @param fileRelativePath; path relativo del file che si vuole leggere
     * @return ArrayList contenente i dati decodificati del csv. Ogni elemento della lista corrisponde ad una linea decodificata del csv. 
     */
    ArrayList<X> readCSV(String fileRelativePath);
    
} 