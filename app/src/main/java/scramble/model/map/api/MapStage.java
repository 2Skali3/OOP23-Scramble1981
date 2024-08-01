package scramble.model.map.api;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.impl.MapElementImpl;

public interface MapStage {
    
    /**
     * Metodo per l'inserimento di una nuova colonna all'interno del MapStage. 
     * Questo metodo non cambia il valore di colonne già esistenti, ma appende alla struttura dati utilizzata per la memorizzazione delle colonne la nuova colonna inserita in input 
     * @param column; valore della colonna che stiamo inserendo
     */
    void addColumn(PairImpl<MapElementImpl, MapElementImpl> column);

    /**
     * Getter per una singola colonna del MapStage identificata da index
     * @param index; colonna che si vuole prelevare dalla struttura dati che contiene l'insieme delle colonne del MapStage
     * @return PairImpl di MapElement contenente come priomo elementola il MapElement del ceiling e come secondo elemento il MapElement del Floor
     */
    PairImpl<MapElementImpl, MapElementImpl> getColumn(int index);

    /**
     * @return numero di colonne contenute nel MapStage
     */
    int size();
} 
