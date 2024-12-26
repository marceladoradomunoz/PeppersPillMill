import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TrialGroup> trialGroups = new ArrayList<>();

        int numberDatafiles = 1; //Numero de archivos a analizar
        while (numberDatafiles <= 3){
            showMenuDatafiles(numberDatafiles);
            String fileName = scanner.next();

            if (isCorrectReadingFile(fileName)){ //Validar si el archivo es correcto (si existe y es correcto)
                TrialGroup trialGroup = new TrialGroup(fileName); //Generar instancia TrialGroup
                trialGroups.add(trialGroup); //Agregar instancia al ArraList de tipo TrialGroup
                numberDatafiles++;
            }
        }
        showMenuOutputFile();
        String fileNameOutput = scanner.next();
        writeOutputFile(fileNameOutput, trialGroups); //Generar archivo de salida y escribir estadísticas y comparaciones
    }

    /**
     * Imprimir mensaje por pantalla para solicitar nombre de archivo a analizar.
     * @param index es el número de archivo a analizar
     */
    private static void showMenuDatafiles(int index){
        String menuFiles = String.format("Ingrese nombre de archivo %s para analizar, sin agregar extensión.", index);
        System.out.println(menuFiles);
    }

    /**
     * Imprimir mensaje por pantalla para solicitar el nombre del archivo de salida.
     */
    private static void showMenuOutputFile(){
        System.out.print("Escribe un nombre para el archivo de salida: ");
    }

    /**
     * Validar si el nombre de archivo concuerda con un archivo existente, si no es un directorio y que tenga contenido.
     * @param fileName es el nombre del archivo a analizar.
     * @return true si efectivamente se puede usar dicho archivo. Retorna false en caso contrario.
     */
    private static boolean isCorrectReadingFile(String fileName){
        File file = new File("datafiles/".concat(fileName).concat(".txt"));
        boolean condition = file.exists() && !file.isDirectory() && file.length() > 0;
        if (!condition){
            System.out.println("El archivo es incorrecto o no existe. Por favor, reingrese el nombre de archivo.");
        }
        return condition;
    }

    /**
     * Generar archivo de salida y escribir sobre él.
     * @param fileName es el nombre del archivo
     * @param trialGroups es la lista de grupos de estudio.
     * Cada elemento está compuesto de nombre de archivo, cantidad de registros, sumatoria de valores, suma de cuadrados de esos valores.
     */
    private static void writeOutputFile(String fileName, List<TrialGroup> trialGroups){
        String finalPath = String.format("datafiles/%s.txt", fileName);
        Result result = new Result();
        try (FileWriter fichero = new FileWriter(finalPath)) {
            PrintWriter pw = new PrintWriter(fichero);
            String dataToWritting = String.format("Statistics:\n" +
                    "%s\n" +
                    "Results:\n" +
                    "%s", result.getStatistics(trialGroups), result.getResults(trialGroups));
            pw.print(dataToWritting);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}