import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.Math.sqrt;

public class TrialGroup {
    private String fileName; //nombre del archivo para el grupo
    private int count; //cantidad de valores registrados para el grupo
    private int sum; //suma total de todos los valores del grupo
    private int sumOfSquare; //suma de todos los valores elevados al cuadrado

    public TrialGroup(String fileName){
        this.fileName = fileName;
        readFile(fileName);
    }

    /**
     * Leer archivo a analizar y almacenar los valores en los campos de la clase.
     * @param fileName file name to reading
     */
    public void readFile(String fileName){
        String path = String.format("datafiles/%s.txt", fileName);
        try (FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line=br.readLine())!=null) {
                this.count++; //número total de valores del grupo
                int dataNumber = Integer.parseInt(line);
                this.sum = this.sum + dataNumber; //suma de todos los valores del grupo
                this.sumOfSquare = this.sumOfSquare + (dataNumber*dataNumber); //suma de los valores elevados al cuadrado.
            }
        }
        catch(Exception e){
            System.out.println("Error: El nombre de archivo proporcionado es incorrecto.");
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSumOfSquare() {
        return sumOfSquare;
    }

    public void setSumOfSquare(int sumOfSquare) {
        this.sumOfSquare = sumOfSquare;
    }

    /**
     * Obtener el promedio de los valores registrados para este grupo
     * @return Double cuyo valor se calcula con la suma total de valores dividido en la cantidad de valores.
     */
    public Double getAverage(){
        return (double) (getSum()/getCount());
    }

    /**
     * Obtener la desviación estándar del conjunto de valores del grupo.
     * 1) Calcular promedio de todos los valores del grupo
     * 2) Calcular cuadrado del promedio de todos los valores del grupo
     * 3) Calcular promedio de los cuadrados: suma de los cuadrados dividido por el total de valores del grupo
     * 4) Calcular raíz cuadrada de la diferencia entre promedio de los cuadrados y cuadrado del promedio.
     * @return Double que corresponde a la desviación estandar del grupo.
     */
    public Double getStandardDeviation(){
        double average = getAverage();
        double squareAverage = average*average; //square of the average
        double averageSquares = (double) getSumOfSquare()/getCount(); //average of the squares
        return sqrt(averageSquares - squareAverage); //standard deviation formula
    }
}