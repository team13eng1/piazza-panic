package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class kitchenChangerAPI {
    private String filePath = "assets/kitchenTemp.tmx";
    private FileHandle handle;
    private String fileContent;
    private int cvsStart = 0;
    private boolean incCVS = true;
    public kitchenChangerAPI(){
        handle = Gdx.files.local(filePath);
        FileHandle handle2 = Gdx.files.internal("assets/Kitchen.tmx");
        handle.writeString(handle2.readString(), false);
        fileContent = "";
    }
    public void readFile(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(handle.read()));
        String str = "";
        try {
            while ((str = reader.readLine()) != null) {
                if (incCVS &&str.contains("<data encoding=\"csv\">")){
                    cvsStart++; incCVS = false;
                }
                else {if (incCVS){cvsStart ++;}}

                fileContent += str;
                fileContent += "\n";

            }
            System.out.println(fileContent);
        }
        catch (Exception err){
            System.out.println(err.getMessage());
        }
    }

    public void editCVSFile(int col ,int row, String newVal){
        String newData = "";
        int line = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(handle.read()));
        String str = "";
        try {
            while ((str = reader.readLine()) != null) {
                if (line == col + cvsStart){
                    String[] lined = str.split(",");
                    lined[row] = newVal;


                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < lined.length; i++) {
                        sb.append(lined[i] + ",");
                    }


                    newData += sb.toString();
                    newData += "\n";
                    line++;


                }
                else {
                    line ++;
                    newData += str;
                    newData += "\n";
                }




            }

        }
        catch (Exception err){
            System.out.println(err.getMessage());
        }

        handle.writeString(newData, false);




    }
}

