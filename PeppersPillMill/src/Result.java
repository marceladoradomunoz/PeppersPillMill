import java.util.List;
import static java.lang.Math.abs;

public class Result {
    public Result(){}

    /**
     * Obtener las estadísticas dada la lista de trial group.
     * @return String construído por la concatenación de cada estadística del trial group
     */
    public String getStatistics(List<TrialGroup> trialGroups){
        String statisticsInOutput = "";
        for (TrialGroup trialGroup : trialGroups){
            //Formato de string para estadísticas: nombreArchivoGrupo.txt: promedioDelGrupo desviaciónEstandarDelGrupo
            String statisticsFormat = String.format("%s: %f %f\n",
                    trialGroup.getFileName(), trialGroup.getAverage(), trialGroup.getStandardDeviation());
            statisticsInOutput = statisticsInOutput.concat(statisticsFormat); //concatenación de strings para armar una salida final.
        }
        return statisticsInOutput;
    }

    /**
     * Obtener resultados dada la lista de trial group. Se hacen comparaciones entre dos grupos (de ahora en adelante, el primer grupo y el grupo precedente)
     *
     * Los parámetros de String.format para la variable comparisonsFormat son:
     *
     * currentTrialGroup.getFileName(): Obtiene el nombre del archivo de donde se obtienen los datos para este grupo.
     * nextTrialGroup.getFileName(): Obtiene el nombre del archivo de donde se obtienen los datos para el grupo que precede.
     * getSignificantDifference: Diferencia significativa entre dos grupos (grupo 1 - grupo 2, grupo 1 - grupo 3, grupo 2 - grupo 3)
     *
     * Parámetros del método getSignificantDifference:
     * currentTrialGroup.getAverage(): Promedio de valores del primer grupo
     * nextTrialGroup.getAverage(): Promedio de valores del grupo precedente.
     * currentTrialGroup.getStandardDeviation(): Desviación estándar del primer grupo.
     * nextTrialGroup.getStandardDeviation(): Desviación estándar del grupo precedente.
     * @return String construído por la concatenación de las comparaciones entre los trial group.
     */
    public String getResults(List<TrialGroup> trialGroups){
        String resultsInOutput = "";

        for (int i = 0; i < trialGroups.size(); i++){
            for (int j = i+1; j <= trialGroups.size()-1; j++){
                TrialGroup currentTrialGroup = trialGroups.get(i);
                TrialGroup nextTrialGroup = trialGroups.get(j);
                //Formato de string para comparaciones: nombreArchivoPrimerGrupo.txt vs nombreArchivoSegundoGrupo.txt : diferenciaSignificativa
                String comparisonsFormat = String.format("%s.txt vs %s.txt : %b\n",
                        currentTrialGroup.getFileName(),
                        nextTrialGroup.getFileName(),
                        getSignificantDifference(
                                currentTrialGroup.getAverage(),
                                nextTrialGroup.getAverage(),
                                currentTrialGroup.getStandardDeviation(),
                                nextTrialGroup.getStandardDeviation()
                        )
                );
                resultsInOutput = resultsInOutput.concat(comparisonsFormat); //Se concatena los resultados y se incorpora al string de salida
            }
        }
        return resultsInOutput;
    }

    /**
     * Obtener si existe una diferencia significativa entre dos grupos.
     * Se calcula a partir de la diferencia positiva (valor absoluto) del promedio del primer grupo y el promedio del segundo.
     * Luego, dicha diferencia positiva se verifica si es mayor a la desviación estándar del primer grupo y mayor
     * a la desviación estándar del segundo grupo.
     */
    private Boolean getSignificantDifference(double averageFirstGroup, double averageSecondGroup,
                                             double standardDeviationFirstGroup,
                                             double standardDeviationSecondGroup){
        double positiveDifferenceBetweenTwoGroups = abs(averageFirstGroup - averageSecondGroup);
        return positiveDifferenceBetweenTwoGroups > standardDeviationFirstGroup
                && positiveDifferenceBetweenTwoGroups > standardDeviationSecondGroup;
    }
}
