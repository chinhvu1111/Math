package locking_db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangingFileName {
    public static void main(String[] args) {
        File folder=new File("/home/chinhvu/project-intel/Math/Algorithm/src/main/java/leetcode_hard");
        File[] listOfFiles= folder.listFiles();

        for (File file: listOfFiles){
            String oldNameFile=file.getName().replaceAll(".java","");
            if(oldNameFile.startsWith("E")){
                continue;
            }
            int index=oldNameFile.lastIndexOf("_");
            String suffixNameFiles=oldNameFile.substring(index+1);
            String newNameFile="E"+suffixNameFiles+"_"+oldNameFile.substring(0, index);
            String pathParent=file.getParent();

            Path path= Paths.get(file.getAbsolutePath());
            Charset charset= StandardCharsets.UTF_8;
            try {
                String content=new String(Files.readAllBytes(path), charset);
                content=content.replaceAll(oldNameFile, newNameFile);
                Files.write(path, content.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File oldFile=new File(pathParent+"/"+oldNameFile+".java");
            File newFile=new File(pathParent+"/"+newNameFile+".java");

            boolean isSuccess=oldFile.renameTo(newFile);
            if(isSuccess){
                System.out.println("Done!");
            }else{
                System.out.println("Error!");
            }
            System.out.println();
        }
    }
}
