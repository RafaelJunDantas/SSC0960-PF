import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class T2PF {

    public static void main(String[] args) throws CsvException, IOException{
        
        String fileName = "01-01-2021.csv";
        FileReader fileReader = new FileReader(fileName);
        CSVReader reader = new CSVReader(fileReader);
        
        //le o header
        reader.readNext();                      
        
        //le o arquivo e armazena em uma lista de Array de String
        List<String[]> linesList = reader.readAll();        
                                                            
        LinkedList<String> paisesNome = new LinkedList();
        
        //cria uma lista auxiliar com todos os nomes de paises lidos
        linesList.stream()                      
                .forEach(line -> {
                    paisesNome.add(line[3]);
                });
        
        //remover os nomes de paises repetidos da lista auxiliar
        Set<String> set = new LinkedHashSet<>(paisesNome);  
        paisesNome.clear(); 
        paisesNome.addAll(set);
        
        //a partir da lista auxiliar
        //cria a lista de paises que recebera todos os dados
        LinkedList<Pais> paises = new LinkedList();         
        paisesNome.forEach(pais -> paises.add(new Pais(pais)));
        
        //passa os dados lidos do arquivo para a lista de paises
        paises.forEach(pais -> {
            linesList.stream()
                    .filter(line -> line[3].contains(pais.getName()))
                    .forEach(line -> {
                        pais.addConfirmed(Integer.parseInt(line[7]));
                        pais.addDeaths(Integer.parseInt(line[8]));
                        pais.addRecovered(Integer.parseInt(line[9]));
                        pais.addActive(Integer.parseInt(line[10]));
                    });          
        });
        
        int resultado;
        
        //paises.forEach(pais -> System.out.println(pais.getName()));
        
        System.out.println("Os 3 paises com os maiores valores de confirmed:");
        paises.stream()
                .sorted((pais1, pais2) -> pais2.getConfirmed() - pais1.getConfirmed())
                .limit(3)
                .sorted((pais1, pais2) -> pais1.getName().compareTo(pais2.getName()))
                .forEach(pais -> System.out.println(pais.getName()));
        
        System.out.print("\nDentre os 10 paises com maiores valores de active, a soma dos deaths dos 5 paises com menores valores de confirmed: ");
        resultado = paises.stream()
                .sorted((pais1, pais2) -> pais2.getActive() - pais1.getActive())
                .limit(10)
                .sorted((pais1, pais2) -> pais1.getConfirmed() - pais2.getConfirmed())
                .limit(5)
                .map(pais -> pais.getConfirmed())
                .reduce(0, (total, confirmed) -> total + confirmed);
        System.out.print(resultado + "\n");
        
        System.out.print("\nO maior valor de deaths do hemisferio Sul: ");
        System.out.print("\n");
        
        System.out.print("\nO maior valor de deaths do hemisferio Norte: ");
        System.out.print("\n");
        
        System.out.print("\nA soma de active de todos os paises em que confirmed e maior ou igual que 1000000: ");
        resultado = paises.stream()
                .filter(pais -> pais.getConfirmed() >= 1000000)
                .map(pais -> pais.getActive())
                .reduce(0, (total, active) -> total + active);
        System.out.print(resultado);
        
        /*
        
        
        System.out.println("O maior valor de deaths entre as regioes do hemisferio sul:");
        linesList.stream()
                .filter(line -> line[5].contains("-"))
                .sorted((line1, line2) -> Integer.parseInt(line2[8]) - Integer.parseInt(line1[8]))
                .limit(1)
                .forEach(line -> System.out.println("Regiao: " + line[2] + "| " + "Pais: " + line[3]));
       
        System.out.println();
        
        System.out.println("O maior valor de deaths entre as regioes do hemisferio norte:");
        linesList.stream()
                .filter(line -> !line[5].contains("-"))
                .sorted((line1, line2) -> Integer.parseInt(line2[8]) - Integer.parseInt(line1[8]))
                .limit(1)
                .forEach(line -> System.out.println("Regiao: " + line[2] + "| " + "Pais: " + line[3]));
       
        System.out.println();
        
        */
    }
    
}
