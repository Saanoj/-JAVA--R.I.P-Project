package excelToBDD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    private String file;
    private String line;

    private String excel;
    private String user;
    private String pass;
    private String bdname;



    public Config(String file){
        this.file = file;
        read();
    }


    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getBdname() {
        return bdname;
    }

    private String[] buffer;
    private String[] arrayForConfig = new String[4];
    private ArrayList<String> array = new ArrayList<>(Arrays.asList(arrayForConfig));

    public String getExcel() {
        return excel;
    }

    public void read(){
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i = 0;(line = bufferedReader.readLine()) != null ;i++) {
                buffer = line.split(":");
                array.set(i,buffer[1]);//prend ce qu'il y a après les deux points
            }
            bufferedReader.close();
            this.excel = array.get(0);
            this.user = array.get(1);
            if(array.get(2).equals("null")){
                this.pass = "";
            }else{
                this.pass = array.get(2);
            }
            this.bdname = array.get(3);
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            file + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + file + "'");
        }
    }
}
